package execution;


import assignment3.Task;
import assignment9.TaskDAO;

public class ExecutionClass {

	public static void main(String[] args) {
		
		int userId = 6;
		
		Task task = new Task("Java", "2025-03-31");
		
		task.setCompleted(true);
		
		TaskDAO dao = new TaskDAO();
		
		dao.createTask(task, userId);
		
	}
}
