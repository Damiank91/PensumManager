package Model.ConnectSql;

import Model.Student.Student;
import Model.Student.StudentTable;
import View.MessagePanel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Damian on 2016-05-07.
 */
public class DriverSqlStudent {

    private Connection myConnect;
    private PreparedStatement preparedStatement;
    MessagePanel messagePanel = new MessagePanel();

    public DriverSqlStudent(){

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

    public void insertStudent(Student student){
        connectWithDataBase();
        String sql = "insert into pensum.studenci (imie_studenta, nazwisko_studenta, data_urodzenia, plec, id_kierunku, tryb, semestr_studiow, insert_time)" +
                " values " +
                "(?,?,?,?,?,?,?, now());";
        try{
            preparedStatement = myConnect.prepareStatement(sql);

           preparedStatement.setString(1, student.getNameStudent()); //imie_studenta
           preparedStatement.setString(2, student.getSurnameStudent()); //nazwisko_studenta
           preparedStatement.setString(3, student.getBornDate()); //data_urodzenia
           preparedStatement.setString(4, student.getGender());
           preparedStatement.setInt(5, student.getIdKierunku()); //id_kierunku
           preparedStatement.setString(6, student.getTrybu());
            preparedStatement.setInt(7, student.getSemestrStudiow());

            preparedStatement.executeUpdate();
        } catch (Exception ex){

        } finally {
            closeDriverSql();
        }

    }


    public ArrayList<String> getListeKierunkow(){
        ArrayList<String> listaKierunkow = new ArrayList<>();
        String sql = "select nazwa_kierunku from pensum.kierunki";
        connectWithDataBase();
        try{
            preparedStatement = myConnect.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                listaKierunkow.add(resultSet.getString(1));
            }
        }catch (Exception ex){

        } finally {
            closeDriverSql();
        }
        return listaKierunkow;
    }


    public int getIdKierunku(String nazwaKierunku){
        String sql = "select id_kierunku from pensum.kierunki where nazwa_kierunku = '" + nazwaKierunku + "'";
        int idKierunku = 0;
        connectWithDataBase();
        try {
            preparedStatement = myConnect.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                idKierunku = resultSet.getInt(1);
            }
        }catch (Exception ex){

        }finally {
            closeDriverSql();
        }
        return idKierunku;
    }


    public void deleteStudent(int idStudent){
        String sql = "delete from pensum.studenci where id_studenta = ?";
        connectWithDataBase();
        try{
            preparedStatement = myConnect.prepareStatement(sql);
            preparedStatement.setInt(1,idStudent);
            preparedStatement.executeUpdate();
        }catch (Exception ex){

        }finally {
            closeDriverSql();
        }
    }

    public ArrayList<StudentTable> searchStudent(Map<String,String> searchValue){
        ArrayList<StudentTable> studentTableArrayList = null;
        String sql = createSqlQueryStudentSearch(searchValue);
        connectWithDataBase();
        try{
            preparedStatement = myConnect.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            studentTableArrayList = new ArrayList<>();
            while(resultSet.next()){
                studentTableArrayList.add(
                        new StudentTable(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),
                                resultSet.getString(4), resultSet.getInt(5)));
            }
        }catch (Exception ex){

        } finally {
            closeDriverSql();
        }

        return studentTableArrayList;
    }

    private String createSqlQueryStudentSearch(Map<String, String> valueSearch){
        String sqlQuery = "select s.id_studenta, s.imie_studenta, s.nazwisko_studenta, k.nazwa_kierunku, s.semestr_studiow " +
                "from pensum.studenci s " +
                "inner join pensum.kierunki k on s.id_kierunku = k.id_kierunku ";
        String tempKey;
        String key;
        String value;
        String tempSqlQuery;
        int iterator = 0;

        if(valueSearch.size() == 1){

            //pobieram nazw� klucza, zwracana warto��: [wartosc]
            tempKey = valueSearch.keySet().toString();

            //usuwam zewn�trzne znaki
            key = tempKey.substring(1, tempKey.length() - 1);
            value = valueSearch.get(key);

            sqlQuery = sqlQuery + " where " + key + " like '%" + value + "%'";
        } else if(valueSearch.size() == 2){

            for(Map.Entry<String, String> entry : valueSearch.entrySet()){
                key = entry.getKey();
                value = valueSearch.get(key);

                if(iterator != 1){
                    sqlQuery = sqlQuery + " where " + key + " like '%" + value + "%' and ";
                } else {
                    sqlQuery = sqlQuery + key + " like '%" + value + "%'";
                }
                iterator++;
            }
        } else if(valueSearch.size() != 0 ) {
            for (Map.Entry<String, String> entry : valueSearch.entrySet()) {
                key = entry.getKey();
                value = valueSearch.get(key);

                if (iterator == 0) {
                    sqlQuery = sqlQuery + " where " + key + " like '%" + value + "%' and ";
                } else if( (valueSearch.size() - 1) == iterator ) {
                    sqlQuery = sqlQuery + key + " = '" + value + "'";
                } else {
                    sqlQuery = sqlQuery + key + " like '%" + value + "%' and ";
                }
                iterator++;
            }
        }
        return sqlQuery;
    }
}
