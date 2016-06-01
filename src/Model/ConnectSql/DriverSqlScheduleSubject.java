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
     * metoda ��cz�ca si� z baz� danych
     * je�eli nie mo�e si� po��czy� wy�wietlany jest komunikat
     */
    private void connectWithDataBase(){
        try{
            myConnect = DriverManager.getConnection(ConnectSql.getDbUrl(), ConnectSql.getUSER(), ConnectSql.getPASSWORD());
            messagePanelScheduleSubject = new MessagePanelScheduleSubject();
        } catch(Exception ex){
            System.err.println("nie mo�na po��czy� si� z baz�");
            System.err.println(ex.toString());
            messagePanelScheduleSubject.showErrorMessage("Nie mo�na po��czy� si� z baz� danych!");
        }
    }

    /**
     * zamykanie po��czenia z baz�
     */
    private void closeDriverSql(){
        try{
            myConnect.close();
        } catch (Exception ex){
            messagePanelScheduleSubject.showErrorMessage("Nie mo�na zamkn�� po��czenia z baz� danych");
        }
    }
}
