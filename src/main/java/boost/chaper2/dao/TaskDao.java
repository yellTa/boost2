package boost.chaper2.dao;

import boost.chaper2.dto.Task;

import java.sql.*;

public class TaskDao {

    private static String dburl = "jdbc:mysql://localhost:3306/tables_in_connectdb";
    private static String dbUser = "boost";
    private static String dbpasswd = "boost";

    public void saveData(Task task) {

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(Exception e){
            e.printStackTrace();
        }
        String sql = "Insert into tasks (title, date, owner, priority) values(?,?,?,?)";
        try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
        PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, task.getTitle());
            ps.setDate(2, task.getDate());
            ps.setString(3, task.getOwner());
            ps.setInt(4, task.getPriority());

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
