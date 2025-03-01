package assignment11;

import java.io.IOException;

import assignment3.Task;
import assignment9.TaskDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EditTaskServlet
 */
@WebServlet("/editTask")
public class EditTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditTaskServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private TaskDAO taskDAO = new TaskDAO();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * taskId を受け取り、 TaskDAO.getTaskById(taskId) でタスク情報を取得
	 * request.setAttribute("task", task); でスコープに格納
	 * editTask.jsp へフォワード
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	// パラメータからtaskIdを取得
    	String taskIdStr = request.getParameter("taskId");
    	if (taskIdStr == null) {
    		// タスクIDがない場合は一覧へリダイレクト
    		response.sendRedirect("taskList.jsp");
    		return;
    	}
    	int taskId = Integer.parseInt(taskIdStr);
    	
    	// DBからタスクを取得
    	Task task = taskDAO.getTaskById(taskId);
    	if (task == null) {
    		// タスクが存在しない場合、一覧へリダイレクト
    		response.sendRedirect("taskList.jsp");
    		return;
    	}
    	
    	// リクエストスコープにセット
    	request.setAttribute("task", task);
    	
    	// editTask.jspへフォワード
    	request.getRequestDispatcher("/editTask.jsp").forward(request, response);
	}
    /**
     * taskId, title, deadline, completed をフォームから取得
     * taskDAO.updateTask(...) で更新
     * 処理後 taskList.jsp にリダイレクト
     */
    // ▽ POST:更新処理を行い、一覧へリダイレクト
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	
    	// フォームから取得
    	request.setCharacterEncoding("UTF-8");
    	int taskId = Integer.parseInt(request.getParameter("taskId"));
    	String title = request.getParameter("title");
    	String deadline = request.getParameter("deadline");
    	// 完了フラグ（チェックボックスやラジオボタンの設計による）
    	String completedStr = request.getParameter("completed");
    	boolean completed = (completedStr != null && completedStr.equals("1"));
    	
    	// DB更新
    	taskDAO.updateTask(taskId, title, deadline, completed);
    	
    	// 一覧へリダイレクト
    	response.sendRedirect("taskList.jsp");
    }
}
