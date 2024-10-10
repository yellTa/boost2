package boost.chaper2.dao;

import boost.chaper2.dto.Task;
import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;

import java.sql.*;
import java.util.Enumeration;

public class TaskDao {
    private static String url = "jdbc:mysql://localhost:3306/tables_in_connectdb";
    private static String user = "boost";
    private static String password = "boost";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }


    public void saveData(Task task) {
        String sql = "INSERT INTO tasks (title, date, owner, priority) VALUES(?,?,?,?)";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, task.getTitle());
            ps.setDate(2, task.getDate());
            ps.setString(3, task.getOwner());
            ps.setInt(4, task.getPriority());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
