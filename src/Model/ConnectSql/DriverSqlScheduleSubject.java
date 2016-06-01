package Model.ConnectSql;

import View.Employee.MessagePanelEmployee;
import View.ScheduleSubject.MessagePanelScheduleSubject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * Created by Damian on 2016-06-01.
 */
public class DriverSqlScheduleSubject {

    private Connection myConnect;
    private PreparedStatement preparedStatement;
    private MessagePanelScheduleSubject messagePanelScheduleSubject;

    public DriverSqlScheduleSubject(){

    }

    /**
     * metoda ³¹cz¹ca siê z baz¹ danych
     * je¿eli nie mo¿e siê po³¹czyæ wyœwietlany jest komunikat
     */
    private void connectWithDataBase(){
        try{
            myConnect = DriverManager.getConnection(ConnectSql.getDbUrl(), ConnectSql.getUSER(), ConnectSql.getPASSWORD());
            messagePanelScheduleSubject = new MessagePanelScheduleSubject();
        } catch(Exception ex){
            System.err.println("nie mo¿na po³¹czyæ siê z baz¹");
            System.err.println(ex.toString());
            messagePanelScheduleSubject.showErrorMessage("Nie mo¿na po³¹czyæ siê z baz¹ danych!");
        }
    }

    /**
     * zamykanie po³¹czenia z baz¹
     */
    private void closeDriverSql(){
        try{
            myConnect.close();
        } catch (Exception ex){
            messagePanelScheduleSubject.showErrorMessage("Nie mo¿na zamkn¹æ po³¹czenia z baz¹ danych");
        }
    }
}
