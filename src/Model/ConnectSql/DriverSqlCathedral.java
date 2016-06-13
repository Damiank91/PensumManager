package Model.ConnectSql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by Damian on 2016-06-02.
 */
public class DriverSqlCathedral {

    private Connection myConnect;
    private PreparedStatement preparedStatement;

    public DriverSqlCathedral(){

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





    /**
     * metoda pobieraj¹ca listê katedr SGGW Wzim
     */
    public ArrayList<String> getCathedralList(){
        ArrayList<String> cathedralList = null;
        String sql = "select nazwa_katedry from pensum.katedra";
        connectWithDataBase();
        try {

            preparedStatement = myConnect.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            cathedralList = new ArrayList<>();
            while (resultSet.next()){
                cathedralList.add(resultSet.getString("nazwa_katedry"));
            }
        } catch (Exception ex){
            System.err.println(ex);
        } finally {
            closeDriverSql();
        }
        return cathedralList;
    }

    /**
     * @param nameCathedral
     * @return idCatedry
     */
    public int getIdCathedral(String nameCathedral){
        int idCathedral = 0;
        String sql = "select id_katedry from pensum.katedra where nazwa_katedry = ?";
        connectWithDataBase();
        try{
            preparedStatement = myConnect.prepareStatement(sql);
            preparedStatement.setString(1,nameCathedral);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                idCathedral = resultSet.getInt("id_katedry");
            }

        }catch (Exception ex){
            System.err.println("Nie uda³o siê pobraæ id katedry");
        } finally {
            closeDriverSql();
        }
        return idCathedral;
    }

    /**
     * metoda zwracajaca nazwê katedry po jego id
     * @param idCathedral
     * @return
     */
    public String getCathedralById(int idCathedral){
        String sql = "select nazwa_katedry from pensum.katedra where id_katedry = ?";
        String cathedralName = null;
        connectWithDataBase();
        try{
            preparedStatement = myConnect.prepareStatement(sql);
            preparedStatement.setInt(1, idCathedral);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                cathedralName = resultSet.getString(1);
            }
        }catch (Exception ex){
            System.err.println("Nie mo¿na pobraæ nazwy katedry po jej id");
        }finally {
            closeDriverSql();
        }
        return cathedralName;
    }
}
