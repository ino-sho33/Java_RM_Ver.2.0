package assignment11;

import java.io.IOException;

import assignment9.TaskDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteTaskServlet
 */
@WebServlet("/deleteTask")
public class DeleteTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteTaskServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int taskId = Integer.parseInt(request.getParameter("taskId"));
		
		TaskDAO dao = new TaskDAO();
		dao.deleteTask(taskId);
		
		// 削除後、一覧画面にリダイレクト
		response.sendRedirect("taskList.jsp");
	}
}
