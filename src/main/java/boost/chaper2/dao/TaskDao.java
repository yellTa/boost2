package boost.chaper2.dao;

import boost.chaper2.config.DatabaseManager;
import boost.chaper2.dto.Task;

import java.sql.*;

public class TaskDao {

    public void saveData(Task task) {
        Connection conn = null;
        PreparedStatement ps = null;

        String sql = "INSERT INTO tasks (title, date, owner, priority) VALUES(?,?,?,?)";
        try {
            conn = DatabaseManager.getInstance().getConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, task.getTitle());
            ps.setDate(2, task.getDate());
            ps.setString(3, task.getOwner());
            ps.setInt(4, task.getPriority());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.getInstance().closeConnection(conn, ps);
        }
    }
}
