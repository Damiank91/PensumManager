package Model.ConnectSql;

import Model.Employee.Employee;
import Model.Employee.EmployeeTable;
import Model.Subject.Subject;
import View.MessagePanel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Damian on 2016-04-21.
 */
public class DriverSqlEmployee {

    private Connection myConnect;
    private PreparedStatement preparedStatement;
    MessagePanel messagePanel = new MessagePanel();


    public DriverSqlEmployee(){

    }

    /**
     * metoda łącząca się z bazą danych
     * jeżeli nie może się połączyć wyświetlany jest komunikat
     */
    private void connectWithDataBase(){
        try{
            myConnect = DriverManager.getConnection(ConnectSql.getDbUrl(),ConnectSql.getUSER(), ConnectSql.getPASSWORD());

        } catch(Exception ex){
            System.err.println("nie można połączyć się z bazą");
            System.err.println(ex.toString());
            messagePanel.showErrorMessage("Nie można połączyć się z bazą danych!");
        }
    }


    /**
     * zamykanie połączenia z bazą
     */
    private void closeDriverSql(){
        try{
            myConnect.close();
        } catch (Exception ex){
            messagePanel.showErrorMessage("Nie można zamknąć połączenia z bazą danych");
        }
    }



    /**
     * metoda pobierająca listę pracowników. Zbiór zawężony o podane przez użytkownika parametry
     * @param searchValues
     * @return
     */
    public ArrayList<EmployeeTable> getSearchEmployeeTable(Map<String, String> searchValues ){
        ArrayList<EmployeeTable> employeeTableArrayList = new ArrayList<>();
        String sql = createSqlQueryEmployeeSearch(searchValues);
        connectWithDataBase();
        try {
            preparedStatement = myConnect.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                employeeTableArrayList.add(
                        new EmployeeTable(resultSet.getInt(1),resultSet.getString(2), resultSet.getString(3), resultSet.getString(4)));
            }
        }catch (Exception ex){
            System.err.println("Nie udało się pobrać konkretnych pracowników");
        } finally {
            closeDriverSql();
        }
        return employeeTableArrayList;
    }


    /**
     * metoda zapisująca nowego pracownika
     */
    public void insertEmployee(Employee employee){
        String sql = "insert into pensum.pracownik" +
                "(imie_pracownika, nazwisko_pracownika, data_urodzenia, plec, czy_Kierownik, id_katedry, pensum, insert_time)" +
                "values" +
                "(?,?,?,?,?,?,?,now());";
        connectWithDataBase();
        try{
            preparedStatement = myConnect.prepareStatement(sql);
            preparedStatement.setString(1,employee.getName());
            preparedStatement.setString(2,employee.getSurname());
            preparedStatement.setString(3,employee.getBirthDate());
            preparedStatement.setString(4, String.valueOf(employee.getGender()));
            preparedStatement.setBoolean(5, employee.isManager());
            preparedStatement.setInt(6, employee.getIdCathedral());
            preparedStatement.setInt(7, employee.getPensum());
            preparedStatement.executeUpdate();
        }catch (Exception ex){
            System.err.println(ex);
        } finally {
            closeDriverSql();
        }
    }


    /**
     * metoda przygotowująca zapytanie sql w zależności od liczby i rodzaju szukanych parametrów
     * @param valueSearch
     * @return
     */
    private String createSqlQueryEmployeeSearch(Map<String, String> valueSearch){
        String sqlQuery = "select p.id_pracownika, p.imie_pracownika, p.nazwisko_pracownika, ka.nazwa_katedry " +
                "from pensum.pracownik p " +
                "inner join pensum.katedra ka on ka.id_katedry = p.id_katedry";

        String tempKey;
        String key;
        String value;
        String tempSqlQuery;
        int iterator = 0;

        if(valueSearch.size() == 1){

                //pobieram nazwę klucza, zwracana wartość: [wartosc]
                tempKey = valueSearch.keySet().toString();

                //usuwam zewnętrzne znaki
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
        } else if(valueSearch.size() == 3) {
            for (Map.Entry<String, String> entry : valueSearch.entrySet()) {
                key = entry.getKey();
                value = valueSearch.get(key);

                if (iterator == 0) {
                    sqlQuery = sqlQuery + " where " + key + " like '%" + value + "%' and ";
                } else if(iterator == 1) {
                    sqlQuery = sqlQuery + key + " like '%" + value + "%' and ";
                } else {
                    sqlQuery = sqlQuery + key + " = '" + value + "'";
                }
                iterator++;
            }
        }
        return sqlQuery;
    }


    /**
     * metoda pobiera z bazy pracownika po jego id
     * @param idEmployee
     * @return
     */
    public Employee getEmployeeById(int idEmployee){
        Employee employee = null;
        String sql = "select * from pracownik where id_pracownika = ?";

        connectWithDataBase();
        try{
            preparedStatement = myConnect.prepareStatement(sql);
            preparedStatement.setInt(1, idEmployee);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                employee = new Employee(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5).charAt(0),
                        resultSet.getBoolean(6),
                        resultSet.getInt(7),
                        resultSet.getInt(8),
                        resultSet.getString(9)
                        );
            }
        }catch (Exception ex){
            System.out.println(ex);
        }finally {
            closeDriverSql();
        }
        return employee;
    }


    /**
     * Metoda aktualizująca dane pracownika w bazie
     * @param employee
     */
    public void updateEmployee(Employee employee){
        String sql = "update pensum.pracownik" +
                "set imie_pracownika = ?," +
                "nazwisko_pracownika = ?, " +
                "data_urodzenia = ?, " +
                "plec = ?, " +
                "czy_Kierownik = ?,"+
                "id_katedry = ?," +
                "pensum = ?," +
                "change_time = now() +" +
                "where id_pracownika = ?";
        connectWithDataBase();
        try{
            preparedStatement = myConnect.prepareStatement(sql);
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2,employee.getSurname());
            preparedStatement.setString(3, String.valueOf(employee.getGender()));
            preparedStatement.setBoolean(4, employee.isManager());
            preparedStatement.setInt(5, employee.getIdCathedral());
            preparedStatement.setInt(6, employee.getPensum());
            preparedStatement.setInt(7, employee.getIdEmploye());
            preparedStatement.executeUpdate();
        }catch (Exception ex){

        }finally {
            closeDriverSql();
        }
    }


    /**
     * metoda usuwająca wskazanego pracownika w bazie
     * @param idEmployee
     */
    public void deleteEmployee(int idEmployee){
        String sql = "delete from pensum.pracownik where id_pracownika = ?";
        connectWithDataBase();
        try{
            preparedStatement = myConnect.prepareStatement(sql);
            preparedStatement.setInt(1,idEmployee);
            preparedStatement.executeUpdate();
        }catch (Exception ex){

        }finally {
            closeDriverSql();
        }
    }


    /**
     * wyszukuje pracownika
     * @param employee- imie nazwisko pensum
     * @return jednego pracownika
     */
    public int getEmployeeByValue(Employee employee){
        String sql = "select id_pracownika from pracownik where imie_pracownika = '" + employee.getName() + "' and nazwisko_pracownika = '" + employee.getSurname() + "' and pensum = " + employee.getPensum();
        int idEmployee = 0;
        connectWithDataBase();
        try{
            preparedStatement = myConnect.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){

                idEmployee = resultSet.getInt(1);

            }
        }catch (Exception ex){
            System.out.println(ex);
        }finally {
            closeDriverSql();
        }
        return idEmployee;
    }

    /**
     * metoda pobierająca wszystkich dostępnych pracowników
     * @return
     */
    public ArrayList<String> getEmployee(){
        String sql = "select concat(imie_pracownika, ' ', nazwisko_pracownika) from pensum.pracownik";
        ArrayList<String> employeeArrayList = null;
        connectWithDataBase();
        try{
            preparedStatement = myConnect.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            employeeArrayList = new ArrayList<>();
            while(resultSet.next()){
                employeeArrayList.add(resultSet.getString(1));
            }
        }catch (Exception ex){
            System.err.print(ex);
        } finally {
            closeDriverSql();
        }
        return  employeeArrayList;
    }

    public int getEmployeeByNameSurname(String employee){
        String[] value = employee.split(" ");
        String sql = "select id_pracownika from pracownik where imie_pracownika = '" + value[0] + "' and nazwisko_pracownika = '" +value[1] + "' limit 1";

        int idEmployee = 0;
        connectWithDataBase();
        try{
            preparedStatement = myConnect.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){

                idEmployee = resultSet.getInt(1);

            }
        }catch (Exception ex){
            System.out.println(ex);
        }finally {
            closeDriverSql();
        }
        return idEmployee;
    }

    public void saveEmloyeeSubject(ArrayList<Subject> employeeSubjectList, int idEmployee){
        String sql = "insert into pensum.przedmioty_prowadzacego " +
                "( id_pracownika, id_przedmiotu, insert_time) values " +
                "(?,?, now());";
        for(int i=0; i < employeeSubjectList.size(); i++){
            connectWithDataBase();
            try{

                preparedStatement = myConnect.prepareStatement(sql);
                preparedStatement.setInt(1, idEmployee);
                preparedStatement.setInt(2, employeeSubjectList.get(i).getId_subject());
                preparedStatement.executeUpdate();


            }catch (Exception ex){
                System.err.print(ex);
            }finally {
                closeDriverSql();
            }
        }
    }
}

