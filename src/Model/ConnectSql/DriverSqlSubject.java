package Model.ConnectSql;

import Model.Subject.Subject;
import Model.Subject.SubjectTable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Damian on 2016-06-02.
 */
public class DriverSqlSubject {

    private Connection myConnect;
    private PreparedStatement preparedStatement;

    public DriverSqlSubject() {

    }

    private void connectWithDataBase() {
        try {
            myConnect = DriverManager.getConnection(ConnectSql.getDbUrl(), ConnectSql.getUSER(), ConnectSql.getPASSWORD());
        } catch (Exception ex) {
            System.err.println("nie mo�na po��czy� si� z baz�");
            System.err.println(ex.toString());
        }
    }

    /**
     * zamykanie po��czenia z baz�
     */
    private void closeDriverSql() {
        try {
            myConnect.close();
        } catch (Exception ex) {
            System.err.print(ex);
        }
    }

    /**
     * metoda pobieraj�ca z bazy wszystkie przedmioty
     *
     * @return subjectList
     */
    public ArrayList<String> getSubjectListByCathedral(String choiceCathedral) {
        ArrayList<String> subjectList = null;
        connectWithDataBase();
        try {
            String sql = "select id_zajec, nazwa_zajec " +
                    "from pensum.zajecia z  " +
                    "inner join pensum.katedra k on k.id_katedry = z.id_katedry " +
                    " where k.nazwa_katedry = '" + choiceCathedral + "'";
            preparedStatement = myConnect.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            subjectList = new ArrayList<>();
            while (resultSet.next()) {
                subjectList.add(resultSet.getString(2));
            }
        } catch (Exception ex) {
            System.err.print("Nie mo�na pobra� przedmiot�w");
        } finally {
            closeDriverSql();
        }
        return subjectList;
    }

    public Subject getSubjectListByName(String choiceCathedral) {
        Subject subject = null;
        connectWithDataBase();
        try {
            String sql = "select id_zajec,nazwa_zajec " +
                    "from pensum.zajecia " +
                    "where nazwa_zajec = '" + choiceCathedral + "' limit 1";
            preparedStatement = myConnect.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                subject = new Subject(resultSet.getInt(1), resultSet.getString(2));
            }
        } catch (Exception ex) {
            System.err.print("Nie mo�na pobra� przedmiot�w");
        } finally {
            closeDriverSql();
        }
        return subject;
    }

    public ArrayList<Subject> getSubjectList() {
        String sql = "select id_przedmiotu, nazwa_przedmiotu from pensum.przedmioty";
        connectWithDataBase();
        ArrayList<Subject> subjectArrayList = null;
        try {
            preparedStatement = myConnect.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            subjectArrayList = new ArrayList<>();
            while (resultSet.next()) {
                subjectArrayList.add(new Subject(resultSet.getInt(1), resultSet.getString(2)));
            }
        } catch (Exception ex) {

        } finally {
            closeDriverSql();
        }
        return subjectArrayList;
    }

    public String getSubjectNameById(int idSubject) {
        String sql = "select nazwa_zajec from pensum.zajecia where id_zajec =" + idSubject;
        connectWithDataBase();
        String subjectName = null;
        try {
            preparedStatement = myConnect.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                subjectName = resultSet.getString(1);
            }
        } catch (Exception ex) {

        } finally {
            closeDriverSql();
        }
        return subjectName;
    }

    public ArrayList<String> getEmployeeSubjectByIdEmployee(int idEmployee) {
        String sql = "select z.nazwa_zajec " +
                "from pensum.przedmioty_prowadzacego pp " +
                "inner join pensum.zajecia z on pp.id_przedmiotu = z.id_zajec " +
                "where pp.id_pracownika = ? ";
        ArrayList<String> subjectList = null;
        connectWithDataBase();
        try {
            preparedStatement = myConnect.prepareStatement(sql);
            preparedStatement.setInt(1, idEmployee);
            ResultSet resultSet = preparedStatement.executeQuery();
            subjectList = new ArrayList<>();
            while (resultSet.next()) {
                subjectList.add(resultSet.getString(1));
            }
        } catch (Exception ex) {

        } finally {
            closeDriverSql();
        }
        return subjectList;
    }

    public int getSubjectNameByName(String nameSubject) {
        String sql = "select id_zajec from pensum.zajecia where nazwa_zajec = '" + nameSubject + "'";
        connectWithDataBase();
        int subjectId = 0;
        try {
            preparedStatement = myConnect.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                subjectId = resultSet.getInt(1);
            }
        } catch (Exception ex) {

        } finally {
            closeDriverSql();
        }
        return subjectId;
    }

    public void deleteEmployeeSubject(int idEmployee) {
        String sql = "delete from pensum.przedmioty_prowadzacego where id_pracownika = " + idEmployee;
        connectWithDataBase();
        try {
            preparedStatement = myConnect.prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (Exception ex) {

        } finally {
            closeDriverSql();
        }
    }

    public void saveSubject(Subject subject) {
        String sql = "INSERT INTO pensum.zajecia (nazwa_zajec,id_katedry,insert_time) VALUES (?, ?, now());";
        connectWithDataBase();
        try {
            preparedStatement = myConnect.prepareStatement(sql);
            preparedStatement.setString(1, subject.getName_subject());
            preparedStatement.setInt(2, subject.getId_cathedral());
            preparedStatement.executeUpdate();

        } catch (Exception ex) {

        } finally {
            closeDriverSql();
        }
    }

    public void deleteSubject(int idSubject) {
        String sql = "delete from pensum.zajecia where id_zajec = ?";
        connectWithDataBase();
        try{
            preparedStatement = myConnect.prepareStatement(sql);
            preparedStatement.setInt(1,idSubject);
            preparedStatement.executeUpdate();
        }catch (Exception ex){

        }finally {
            closeDriverSql();
        }
    }

    public ArrayList<SubjectTable> searchSubject(Map<String, String> valueSearch) {
        String sql = createSqlSearchSubject(valueSearch);
        connectWithDataBase();
        ArrayList<SubjectTable> subjectTableArrayList = null;
        try{
            preparedStatement = myConnect.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            subjectTableArrayList = new ArrayList<>();
            while (resultSet.next()){
                subjectTableArrayList.add(new SubjectTable(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3)));
            }
        }catch (Exception ex){

        }finally {
            closeDriverSql();
        }
        return subjectTableArrayList;
    }

    private String createSqlSearchSubject(Map<String, String> valueSearch) {
        String sqlQuery = "select z.id_zajec, z.nazwa_zajec, k.nazwa_katedry " +
                "from pensum.zajecia z " +
                "inner join pensum.katedra k on k.id_katedry = z.id_katedry";
        String tempKey;
        String key;
        String value;
        String tempSqlQuery;
        int iterator = 0;

        if (valueSearch.size() == 1) {

            //pobieram nazw� klucza, zwracana warto��: [wartosc]
            tempKey = valueSearch.keySet().toString();

            //usuwam zewn�trzne znaki
            key = tempKey.substring(1, tempKey.length() - 1);
            value = valueSearch.get(key);

            sqlQuery = sqlQuery + " where " + key + " like '" + value + "'";
        } else if (valueSearch.size() == 2) {

            for (Map.Entry<String, String> entry : valueSearch.entrySet()) {
                key = entry.getKey();
                value = valueSearch.get(key);

                if (iterator != 1) {
                    sqlQuery = sqlQuery + " where " + key + " like '" + value + "' and ";
                } else {
                    sqlQuery = sqlQuery + key + " like '" + value + "'";
                }
                iterator++;
            }
        }
        return sqlQuery;
    }


}
