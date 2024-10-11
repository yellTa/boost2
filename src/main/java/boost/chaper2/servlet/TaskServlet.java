package boost.chaper2.servlet;

import boost.chaper2.dao.TaskDao;
import boost.chaper2.dto.Task;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/task")
public class TaskServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        StringBuilder jsonRead = new StringBuilder();

        String line;
        try (BufferedReader reader = request.getReader()) {
            while ((line = reader.readLine()) != null) {
                jsonRead.append(line);
            }
        }

        String jsonString = jsonRead.toString();
        ObjectMapper objectMapper = new ObjectMapper();
        Task task = objectMapper.readValue(jsonString, Task.class);

        TaskDao dao = new TaskDao();
        dao.saveData(task);

    }
}

