package Model.Employee;

import View.Employee.MessagePanelEmployee;

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
    private static final String DB_URL="jdbc:mysql://localhost:3306/pensum_menager";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private Connection myConnect;
    private PreparedStatement preparedStatement;
    private MessagePanelEmployee messagePanelEmployee;


    public DriverSqlEmployee(){

    }

    /**
     * metoda łącząca się z bazą danych
     * jeżeli nie może się połączyć wyświetlany jest komunikat
     */
    private void connectWithDataBase(){
        try{
            myConnect = DriverManager.getConnection(DB_URL,USER, PASSWORD);
            messagePanelEmployee = new MessagePanelEmployee();
        } catch(Exception ex){
            System.err.println("nie można połączyć się z bazą");
            System.err.println(ex.toString());
            messagePanelEmployee.showErrorMessage("Nie można połączyć się z bazą danych!");
        }
    }


    /**
     * zamykanie połączenia z bazą
     */
    private void closeDriverSql(){
        try{
            myConnect.close();
        } catch (Exception ex){
            messagePanelEmployee.showErrorMessage("Nie można zamknąć połączenia z bazą danych");
        }
    }


    /**
     * metoda pobierająca z bazy wszystkie przedmioty
     * @return subjectList
     */
    public ArrayList<String> getSubjectList(){
        ArrayList<String> subjectList = null;
        connectWithDataBase();
        try{
            String sql = "select nazwa_przedmiotu from pensum_Menager.przedmioty";
            preparedStatement = myConnect.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            subjectList = new ArrayList<>();
            while (resultSet.next()){
                subjectList.add(resultSet.getString("nazwa_przedmiotu"));
            }
        } catch (Exception ex){
            System.err.print("Nie można pobrać przedmiotów");
            messagePanelEmployee.showErrorMessage("Nie można pobrać listy przedmiotów");
        }finally {
            closeDriverSql();
        }
        return subjectList;
    }


    /**
     * metoda pobierająca listę katedr SGGW Wzim
     */
    public ArrayList<String> getCathedralList(){
        ArrayList<String> cathedralList = null;
        String sql = "select nazwa_katedry from pensum_Menager.katedra";
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
            messagePanelEmployee.showErrorMessage("Nie udało się pobrać listy katedr");
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
        String sql = "select id_katedry from pensum_Menager.katedra where nazwa_katedry = ?";
        connectWithDataBase();
        try{
            preparedStatement = myConnect.prepareStatement(sql);
            preparedStatement.setString(1,nameCathedral);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                idCathedral = resultSet.getInt("id_katedry");
            }

        }catch (Exception ex){
            System.err.println("Nie udało się pobrać id katedry");
        } finally {
            closeDriverSql();
        }
        return idCathedral;
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
        String sql = "insert into pensum_Menager.pracownik" +
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
        }
    }


    /**
     * metoda przygotowująca zapytanie sql w zależności od liczby i rodzaju szukanych parametrów
     * @param valueSearch
     * @return
     */
    private String createSqlQueryEmployeeSearch(Map<String, String> valueSearch){
        String sqlQuery = "select p.id_pracownika, p.imie_pracownika, p.nazwisko_pracownika, ka.nazwa_katedry " +
                "from pensum_Menager.pracownik p " +
                "inner join pensum_Menager.katedra ka on ka.id_katedry = p.id_katedry";

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
            preparedStatement.setInt(1,idEmployee);
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
     * metoda zwracajaca nazwę katedry po jego id
     * @param idCathedral
     * @return
     */
    public String getCathedralById(int idCathedral){
        String sql = "select nazwa_katedry from pensum_menager.katedra where id_katedry = ?";
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
            System.err.println("Nie można pobrać nazwy katedry po jej id");
        }finally {
            closeDriverSql();
        }
        return cathedralName;
    }

    public void updateEmployee(Employee employee){
        String sql = "update pensum_menager.pracownik" +
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


    public void deleteEmployee(int idEmployee){
        String sql = "delete from pensum_menager.pracownik where id_pracownika = ?";
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
}

