package assignment2;


//--------------------------------------------
//タスクの一覧表示（仮データ）
//--------------------------------------------
/* 目的
 * 配列やループ処理の理解
 * タスクデータを仮で用意して一覧表示
 * if文を使用して条件付きの表示を行う
 * */

public class TaskListDemo {
	
	public static void main(String[] args) {
		// 仮のタスクデータ（タイトル、期限、完了フラグ）
		String[] taskTitles = {"書類作成", "ミーティング準備", "メール返信", "プログラム修正"};		// タスク名
		String[] taskDeadlines = {"2025-03-01", "2025-03-02", "2025-03-05", "2025-03-10"};	// 期限日
		boolean[] taskCompleted = {false, false, true, false};								// 完了：true・未完了：false
		
		System.out.println("=== タスク一覧 ===");
		for (int i = 0; i < taskTitles.length; i++) {
			// 完了していないタスク名、期限日を表示
			if (!taskCompleted[i]) {
				System.out.println("タスク" + (i + 1) + ":" + taskTitles[i] 
						+ " (期限：" + taskDeadlines[i] + ")");
			}
		}	
	}
}
