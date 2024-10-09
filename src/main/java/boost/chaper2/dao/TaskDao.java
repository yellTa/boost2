package boost.chaper2.dao;

import boost.chaper2.dto.Task;
import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;

import java.sql.*;
import java.util.Enumeration;

public class TaskDao {
    private static Connection conn;

    static {
        String url = "jdbc:mysql://localhost:3306/tables_in_connectdb";
        String user = "boost";
        String password = "boost";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveData(Task task) {
        PreparedStatement ps = null;

        String sql = "INSERT INTO tasks (title, date, owner, priority) VALUES(?,?,?,?)";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, task.getTitle());
            ps.setDate(2, task.getDate());
            ps.setString(3, task.getOwner());
            ps.setInt(4, task.getPriority());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeConnection() {
        if (conn != null) {
            try {
                if (!conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
            }
        }
    }

    public static void cleanUpSQLDriver() {
        try {
            AbandonedConnectionCleanupThread.checkedShutdown();
            Enumeration<Driver> drivers = DriverManager.getDrivers();

            while (drivers.hasMoreElements()) {
                Driver driver = drivers.nextElement();
                try {
                    DriverManager.deregisterDriver(driver);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
