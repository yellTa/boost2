package boost.chaper2.dao;

import boost.chaper2.dto.Task;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;
import org.json.JSONObject;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

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

    public String readData() {
        String todoSql = "SELECT * FROM tasks WHERE progress='todo'";
        String doneSql = "SELECT * FROM tasks WHERE progress='done'";
        String doingSql = "SELECT * FROM tasks WHERE progress='doing'";

        JSONObject object = new JSONObject();

        try (Connection conn = getConnection()) {
            //todo
            List<Task> tasks = new ArrayList<>();
            try (PreparedStatement ps = conn.prepareStatement(todoSql)) {
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    int userId = rs.getInt("user_id");
                    String title = rs.getString("title");
                    java.sql.Date date = rs.getDate("date");
                    String owner = rs.getString("owner");
                    int priority = rs.getInt("priority");
                    String progress = rs.getString("progress");

                    Task task = new Task(userId, title, date, owner, priority, progress);

                    tasks.add(task);

                }

                object.put("todo", tasks);

            } catch (SQLException e) {

            }
            //done
            tasks = new ArrayList<>();
            try (PreparedStatement ps = conn.prepareStatement(doneSql)) {
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    int userId = rs.getInt("user_id");
                    String title = rs.getString("title");
                    java.sql.Date date = rs.getDate("date");
                    String owner = rs.getString("owner");
                    int priority = rs.getInt("priority");
                    String progress = rs.getString("progress");

                    Task task = new Task(userId, title, date, owner, priority, progress);

                    tasks.add(task);

                }
                object.put("done", tasks);


            } catch (SQLException e) {

            }

            //doing
            tasks = new ArrayList<>();
            try (PreparedStatement ps = conn.prepareStatement(doingSql)) {
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    int userId = rs.getInt("user_id");
                    String title = rs.getString("title");
                    java.sql.Date date = rs.getDate("date");
                    String owner = rs.getString("owner");
                    int priority = rs.getInt("priority");
                    String progress = rs.getString("progress");

                    Task task = new Task(userId, title, date, owner, priority, progress);

                    tasks.add(task);

                }

                object.put("doing", tasks);

            } catch (SQLException e) {

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return object.toString();
    }

    public void saveData(Task task) {
        String sql = "INSERT INTO tasks (title, date, owner, priority) VALUES(?,?,?,?)";
        Date today = Date.valueOf(LocalDate.now());
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, task.getTitle());
            ps.setDate(2, new java.sql.Date(today.getTime()));
            ps.setString(3, task.getOwner());
            ps.setInt(4, task.getPriority());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
