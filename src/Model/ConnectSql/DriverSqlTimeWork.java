package Model.ConnectSql;

import Model.TimeWork.TimeWork;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by Damian on 2016-06-02.
 */
public class DriverSqlTimeWork {

    private Connection myConnect;
    private PreparedStatement preparedStatement;

    public DriverSqlTimeWork(){

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

    public ArrayList<TimeWork> getTimeWork(){
        String sql = "select id, name_godzina from pensum._dict_godziny ";
        connectWithDataBase();
        ArrayList<TimeWork> timeWorkArrayList = null;
        try{
            preparedStatement = myConnect.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            timeWorkArrayList = new ArrayList<>();
            while (resultSet.next()){
                timeWorkArrayList.add(new TimeWork(resultSet.getInt(1), resultSet.getString(2)));
            }
        }catch (Exception ex){

        }finally {
            closeDriverSql();
        }
        return timeWorkArrayList;
    }

    public ArrayList<String> getTimeWorkName(){
        String sql = "select name_godzina from pensum._dict_godziny ";
        connectWithDataBase();
        ArrayList<String> timeWorkArrayList = null;
        try{
            preparedStatement = myConnect.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            timeWorkArrayList = new ArrayList<>();
            while (resultSet.next()){
                timeWorkArrayList.add(resultSet.getString(1));
            }
        }catch (Exception ex){

        }finally {
            closeDriverSql();
        }
        return timeWorkArrayList;
    }

    public int getTimeWorkByName(String TimeWorkName){
        String sql = "select id from pensum._dict_godziny where name_godzina = '" + TimeWorkName + "'";
        connectWithDataBase();
        int idTimeWork = 0;
        try{
            preparedStatement = myConnect.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                idTimeWork = resultSet.getInt(1);
            }
        }catch (Exception ex){

        }finally {
            closeDriverSql();
        }
        return idTimeWork;
    }

}
