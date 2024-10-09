package boost.chaper2.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseManager {

    private static DatabaseManager instance;
    private static String dburl = "jdbc:mysql://localhost:3306/tables_in_connectdb";
    private static String dbUser = "boost";
    private static String dbpasswd = "boost";

    private DatabaseManager(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static synchronized  DatabaseManager getInstance(){
        if(instance == null){
            instance = new DatabaseManager();
        }
        return instance;
    }


    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dburl, dbUser, dbpasswd);
    }

    public void closeConnection(Connection conn, PreparedStatement ps ){
        try {
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
