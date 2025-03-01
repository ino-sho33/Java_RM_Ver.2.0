package assignment3;



//------------------------------------------------------------------------
//タスククラスの作成
//------------------------------------------------------------------------
/* 目的
 * Taskクラスを定義し、タイトル・締切日・完了状態を保持
 * メソッドを使ってタスク情報を操作
 * オブジェクト指向の基本に触れる
 * */

public class Task {
	
	//------------------------------------------------------------------------
	//タスクID自動生成
	//------------------------------------------------------------------------
	/* 目的
	 * タスク登録時に一意のIDを自動生成
	 * コンストラクタでIDを割り当てる
	 * */
	//--------------------------------------------------------
	// フィールド
	//--------------------------------------------------------
	private int id;				//自動生成されるID
	private String title;		// タスク名
	private String deadline;	// 期限日
	private boolean completed;	// 完了：true・未完了；false
	
	//--------------------------------------------------------
	// コンストラクタ
	//--------------------------------------------------------
	// 新しいタスクを作成するとき用（DBにINSERTする前提）
	public Task(String title, String deadline) {
		this.title = title;
		this.deadline = deadline;
		this.completed = false;
	}
	
	// DBから読み込む時用のコンストラクタ
	public Task(int id, String title, String deadline, boolean completed) {
		this.id = id;
		this.title = title;
		this.deadline = deadline;
		this.completed = completed;
	}
	
	//--------------------------------------------------------
	// idのゲッター
	//--------------------------------------------------------
	public int getId() {
		return id;
	}
	
	//--------------------------------------------------------
	// titleのゲッターとセッター
	//--------------------------------------------------------
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	//--------------------------------------------------------
	// deadlineのゲッターとセッター
	//--------------------------------------------------------
	public String getDeadline() {
		return deadline;
	}
	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

	//--------------------------------------------------------
	// completedのゲッターとセッター
	//--------------------------------------------------------
	public boolean isCompleted() {
		return completed;
	}
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	
	//--------------------------------------------------------
	// タスク情報を表示するメソッド
	//--------------------------------------------------------
	public void printTaskInfo() {
		System.out.println("タイトル： " + title + ", 期限： " + deadline + ", 完了： " + completed);
	}
	
	//--------------------------------------------------------
	// タスクを完了にするメソッド
	//--------------------------------------------------------
	public void completeTask() {
		this.completed = true;
	}
	
	//--------------------------------------------------------
	// 簡単な動作テスト用mainメソッド
	//--------------------------------------------------------
	public static void main(String[] args) {
		// タスククラスのインスタンス生成
		Task task = new Task("レポート提出", "2025-03-01");
		// タスククラスのタスク情報を表示するメソッド
		task.printTaskInfo();
		// タスククラスのタスクを完了にするメソッド
		task.completeTask();
		// タスククラスのタスク情報を表示するメソッド
		task.printTaskInfo();
	}
}
