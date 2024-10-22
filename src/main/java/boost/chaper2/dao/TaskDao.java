package boost.chaper2.dao;

import boost.chaper2.DatabaseConnector;
import boost.chaper2.dto.Task;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;
import org.json.JSONObject;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class TaskDao {
    public String readData() {
        String sql = "SELECT * FROM tasks";
        ObjectMapper objectMapper = new ObjectMapper();

        List<Task> todoList = new ArrayList<>();
        List<Task> doingList = new ArrayList<>();
        List<Task> doneList = new ArrayList<>();

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int userId = rs.getInt("user_id");
                String title = rs.getString("title");
                Date date = rs.getDate("date");
                String owner = rs.getString("owner");
                int priority = rs.getInt("priority");
                String progress = rs.getString("progress");

                Task task = new Task(userId, title, date, owner, priority, progress);
                if(progress.equals("todo")){
                    todoList.add(task);
                }else if(progress.equals("doing")){
                    doingList.add(task);
                }else if(progress.equals("done")){
                    doneList.add(task);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ObjectNode rootNode = objectMapper.createObjectNode();
        JsonNode todoNode = objectMapper.valueToTree(todoList);
        rootNode.set("todo", todoNode);

        JsonNode doingNode = objectMapper.valueToTree(doingList);
        rootNode.set("doing", doingNode);

        JsonNode doneNode = objectMapper.valueToTree(doneList);
        rootNode.set("done", doneNode);

        return rootNode.toString();
    }

    public void saveData(Task task) {
        String sql = "INSERT INTO tasks (title, date, owner, priority) VALUES(?,?,?,?)";
        Date today = Date.valueOf(LocalDate.now());
        try (Connection conn = DatabaseConnector.getConnection();
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
