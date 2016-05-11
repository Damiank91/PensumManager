package Model.ConnectSql;

import Model.Student.Student;
import Model.Student.StudentTable;
import View.Employee.MessagePanelEmployee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Damian on 2016-05-07.
 */
public class DriverSqlStudent {

    private Connection myConnect;
    private PreparedStatement preparedStatement;
    private MessagePanelEmployee messagePanelEmployee;

    public DriverSqlStudent(){

    }

    /**
     * metoda ³¹cz¹ca siê z baz¹ danych
     * je¿eli nie mo¿e siê po³¹czyæ wyœwietlany jest komunikat
     */
    private void connectWithDataBase(){
        try{
            myConnect = DriverManager.getConnection(ConnectSql.getDbUrl(), ConnectSql.getUSER(), ConnectSql.getPASSWORD());
            messagePanelEmployee = new MessagePanelEmployee();
        } catch(Exception ex){
            System.err.println("nie mo¿na po³¹czyæ siê z baz¹");
            System.err.println(ex.toString());
            messagePanelEmployee.showErrorMessage("Nie mo¿na po³¹czyæ siê z baz¹ danych!");
        }
    }


    /**
     * zamykanie po³¹czenia z baz¹
     */
    private void closeDriverSql(){
        try{
            myConnect.close();
        } catch (Exception ex){
            messagePanelEmployee.showErrorMessage("Nie mo¿na zamkn¹æ po³¹czenia z baz¹ danych");
        }
    }

    public void insertStudent(Student student){

    }

    public ArrayList<String> getGroupList(){
        ArrayList<String> groupList = new ArrayList<>();
        return groupList;
    }

    public ArrayList<String> getListeKierunkow(){
        ArrayList<String> listaKierunkow = new ArrayList<>();
        return listaKierunkow;
    }

    public ArrayList<String> getListeTrybow(){
        ArrayList<String> listaTrybow = new ArrayList<>();
        return listaTrybow;
    }

    public int getIdGroup(String nameGroup){
        int idGroup = 0;
        return idGroup;
    }

    public int getIdKierunku(String nazwaKierunku){
        int idKierunku = 0;
        return idKierunku;
    }

    public int getIdTrybu(String nameTrybu){
        int idTrybu = 0;
        return idTrybu;
    }

    public void deleteStudent(int id){

    }

    public ArrayList<StudentTable> searchStudent(Map<String,String> searchValue){
        ArrayList<StudentTable> studentTableArrayList = null;
        return studentTableArrayList;
    }
}
