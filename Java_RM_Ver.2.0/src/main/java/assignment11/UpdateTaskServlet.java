package assignment11;

import java.io.IOException;

import assignment9.TaskDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/updateTask")
public class UpdateTaskServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int taskId = Integer.parseInt(request.getParameter("taskId"));
        boolean completed = request.getParameter("completed").equals("1"); // 0 or 1 を boolean に変換

        System.out.println("🔹 受け取ったtaskId: " + taskId);
        System.out.println("🔹 受け取ったcompleted: " + completed);

        TaskDAO dao = new TaskDAO();
        dao.updateTaskCompletion(taskId, completed);

        response.setContentType("text/plain");
        response.getWriter().write("success");
    }
}
