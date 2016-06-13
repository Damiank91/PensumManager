package Model.ConnectSql;

import Model.ScheduleSubject.ScheduleSubject;
import View.MessagePanel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by Damian on 2016-06-01.
 */
public class DriverSqlScheduleSubject {

    private Connection myConnect;
    private PreparedStatement preparedStatement;
    MessagePanel messagePanel = new MessagePanel();

    public DriverSqlScheduleSubject(){

    }

    /**
     * metoda ��cz�ca si� z baz� danych
     * je�eli nie mo�e si� po��czy� wy�wietlany jest komunikat
     */
    private void connectWithDataBase(){
        try{
            myConnect = DriverManager.getConnection(ConnectSql.getDbUrl(), ConnectSql.getUSER(), ConnectSql.getPASSWORD());

        } catch(Exception ex){
            System.err.println("nie mo�na po��czy� si� z baz�");
            System.err.println(ex.toString());
            messagePanel.showErrorMessage("Nie mo�na po��czy� si� z baz� danych!");
        }
    }

    /**
     * zamykanie po��czenia z baz�
     */
    private void closeDriverSql(){
        try{
            myConnect.close();
        } catch (Exception ex){
            messagePanel.showErrorMessage("Nie mo�na zamkn�� po��czenia z baz� danych");
        }
    }



    public ArrayList<ScheduleSubject> getScheduleSubjectForEmployeeById(int idEmployee){
       String sql = "select * from pensum.plan_zajec where id_pracownika = " + idEmployee;
        connectWithDataBase();
        ArrayList<ScheduleSubject> scheduleSubjectArrayList = null;
        try {
            preparedStatement = myConnect.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            scheduleSubjectArrayList = new ArrayList<>();
            while (resultSet.next()){
                scheduleSubjectArrayList.add(new ScheduleSubject(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getInt(4), resultSet.getInt(5), resultSet.getInt(6)));

            }
        }catch (Exception ex){

        }finally {
            closeDriverSql();
        }

        return scheduleSubjectArrayList;
    }

    public ArrayList<ScheduleSubject> getScheduleSubjectForRoomById(int idRoom){
        String sql = "select * from pensum.plan_zajec where id_sali= " + idRoom;
        connectWithDataBase();
        ArrayList<ScheduleSubject> scheduleSubjectArrayList = null;
        try {
            preparedStatement = myConnect.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            scheduleSubjectArrayList = new ArrayList<>();
            while (resultSet.next()){
                scheduleSubjectArrayList.add(new ScheduleSubject(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getInt(4), resultSet.getInt(5), resultSet.getInt(6)));

            }
        }catch (Exception ex){

        }finally {
            closeDriverSql();
        }

        return scheduleSubjectArrayList;
    }

    public void saveScheduleSubject(ScheduleSubject scheduleSubject){
        String sql = "insert into pensum.plan_zajec" +
                " (id_pracownika, id_sali, id_przedmiotu, id_dnia_tygodnia, id_godziny, insert_time) " +
                "values (?,?,?,?,?,now());";

        connectWithDataBase();
        try{
            preparedStatement = myConnect.prepareStatement(sql);
            preparedStatement.setInt(1, scheduleSubject.getIdEmployee());
            preparedStatement.setInt(2, scheduleSubject.getIdRoom());
            preparedStatement.setInt(3, scheduleSubject.getIdSubject());
            preparedStatement.setInt(4, scheduleSubject.getIdRoom());
            preparedStatement.setInt(5, scheduleSubject.getIdTimeWork());
            preparedStatement.executeUpdate();
        } catch (Exception ex){

        } finally {
            closeDriverSql();
        }



    }


}
