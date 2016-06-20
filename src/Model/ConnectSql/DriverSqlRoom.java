package Model.ConnectSql;

import Model.Room.Room;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by Damian on 2016-06-02.
 */
public class DriverSqlRoom {

    private Connection myConnect;
    private PreparedStatement preparedStatement;

    public DriverSqlRoom(){

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

    public ArrayList<Room> getRoomList(){
        String sql = "select id,numer_sali  from pensum.sala";
        ArrayList<Room> roomArrayList = null;
        connectWithDataBase();
        try{
            preparedStatement = myConnect.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            roomArrayList = new ArrayList<>();
            while (resultSet.next()){
                roomArrayList.add(new Room(resultSet.getInt(1), resultSet.getString(2)));
            }
        }catch (Exception ex){

        }finally {
            closeDriverSql();
        }

        return roomArrayList;
    }

    public ArrayList<String> getRoomNameList(){
        String sql = "select numer_sali  from pensum.sala";
        ArrayList<String> roomArrayList = null;
        connectWithDataBase();
        try{
            preparedStatement = myConnect.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            roomArrayList = new ArrayList<>();
            while (resultSet.next()){
                roomArrayList.add(resultSet.getString(1));
            }
        }catch (Exception ex){

        }finally {
            closeDriverSql();
        }

        return roomArrayList;
    }

    public int getIdRoomByName(String choiceRoom){
        String sql = "select id from pensum.sala where numer_sali = '"+ choiceRoom + "' limit 1";
        connectWithDataBase();
        int idRoom = 0;
        try{
            preparedStatement = myConnect.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                idRoom = resultSet.getInt(1);
            }

        } catch (Exception ex){

        }finally {
            closeDriverSql();
        }
        return idRoom;
    }

    public String getRoomById(int idRoom){
        String sql = "select numer_sali from pensum.sala where id= ? limit 1";
        connectWithDataBase();
        String nameRoom = null;
        try{
            preparedStatement = myConnect.prepareStatement(sql);
            preparedStatement.setInt(1,idRoom);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                nameRoom = resultSet.getString(1);
            }
        } catch (Exception ex){

        }finally {
            closeDriverSql();
        }
        return nameRoom;
    }


}
