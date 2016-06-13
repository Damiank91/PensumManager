package Model.ConnectSql;

/**
 * Created by Damian on 2016-05-07.
 */
public class ConnectSql {

    private static final String DB_URL="jdbc:mysql://localhost:3306/pensum";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public ConnectSql(){

    }

    public static String getDbUrl() {
        return DB_URL;
    }

    public static String getUSER() {
        return USER;
    }

    public static String getPASSWORD() {
        return PASSWORD;
    }
}
