package Model.ConnectSql;

import Model.DaysWeek.DaysWeek;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by Damian on 2016-06-02.
 */
public class DriverSqlDaysWeek {

    private Connection myConnect;
    private PreparedStatement preparedStatement;

    public DriverSqlDaysWeek(){

    }

    private void connectWithDataBase(){
        try{
            myConnect = DriverManager.getConnection(ConnectSql.getDbUrl(), ConnectSql.getUSER(), ConnectSql.getPASSWORD());
        } catch(Exception ex){
            System.err.println("nie mo¿na po³¹czyæ siê z baz¹");
            System.err.println(ex.toString());
        }
    }

    /**
     * zamykanie po³¹czenia z baz¹
     */
    private void closeDriverSql(){
        try{
            myConnect.close();
        } catch (Exception ex){
            System.err.print(ex);
        }
    }

    public ArrayList<DaysWeek> getDayWeek(){
        String sql = "select id, nazwa_dnia from pensum._dict_dni_tygodnia";
        connectWithDataBase();
        ArrayList<DaysWeek> daysWeekArrayList = null;
        try {
            preparedStatement = myConnect.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            daysWeekArrayList = new ArrayList<>();
            while (resultSet.next()){
                daysWeekArrayList.add(new DaysWeek(resultSet.getInt(1), resultSet.getString(2)));
            }
        }catch (Exception ex){

        }finally {
            closeDriverSql();
        }
        return daysWeekArrayList;
    }

    public ArrayList<String> getDayWeekName(){
        String sql = "select nazwa_dnia from pensum._dict_dni_tygodnia";
        connectWithDataBase();
        ArrayList<String> daysWeekArrayList = null;
        try {
            preparedStatement = myConnect.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            daysWeekArrayList = new ArrayList<>();
            while (resultSet.next()){
                daysWeekArrayList.add(resultSet.getString(1));
            }
        }catch (Exception ex){

        }finally {
            closeDriverSql();
        }
        return daysWeekArrayList;
    }

    public int getDayWeekByName(String dayName){
        String sql = "select id from pensum._dict_dni_tygodnia where nazwa_dnia ='" + dayName + "'";
        connectWithDataBase();
        int idDay = 0;
        try {
            preparedStatement = myConnect.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                idDay = resultSet.getInt(1);
            }
        }catch (Exception ex){

        }finally {
            closeDriverSql();
        }
        return idDay;
    }
}
