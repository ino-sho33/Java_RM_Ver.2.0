package assignment11;

import java.io.IOException;

import assignment3.Task;
import assignment9.TaskDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddTaskServlet
 */
// @WebServlet("/addTask")はassignment11.AddTaskServlet.javaを作成時にマッピング済みなので明示的に記述なしない。
public class AddTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddTaskServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    // POSTでタスク情報を受け取り、DBに追加
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String title = request.getParameter("title");
		String deadline = request.getParameter("deadline");
		// ユーザーIDは簡易的にフォームから受け取る
		int userId = Integer.parseInt(request.getParameter("userId"));
		
		// 新規タスク用コンストラクタ
		Task newTask = new Task(title, deadline);
		
		TaskDAO dao = new TaskDAO();
		dao.createTask(newTask, userId);
		
		response.sendRedirect("taskList.jsp");
	}
}
