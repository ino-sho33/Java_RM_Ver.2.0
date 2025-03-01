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
	
	private TaskDAO taskDAO = new TaskDAO();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		// Ajaxから送信されたタスクIDと完了状態を取得
		String taskIdStr = request.getParameter("taskId");
		String completedStr = request.getParameter("completed");
		
		if (taskIdStr != null && completedStr != null) {
			int taskId = Integer.parseInt(taskIdStr);
			boolean completed = Boolean.parseBoolean(completedStr);
			
			// DB更新
			taskDAO.updateTaskCompletion(taskId, completed);
			
			// レスポンスとして成功メッセージを返す
			response.setContentType("text/plain; charset=UTF-8");
			response.getWriter().write("更新成功");
		} else {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "パラメータが不足しています。");
		}
	}
}
