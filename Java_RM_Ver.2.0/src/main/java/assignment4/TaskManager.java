package assignment4;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import assignment3.Task;

//------------------------------------------------------------------------
//タスク管理の基本機能の統合
//------------------------------------------------------------------------
/* 目的
 * タスクの登録・一覧表示・削除機能を持つ簡易タスク管理プログラム
 * ユーザーからの入力を受け取り、タスクを管理するためのリストを操作します
 * これまで学んだタスクやリスト操作、条件分岐を統合
 * */
 
/**
 * タスク管理の基本機能の統合クラス。
 * <p>
 * このクラスは、タスクの登録、一覧表示、削除機能を提供する簡易タスク管理プログラム。
 * ユーザーから入力を受け取り、タスクを管理するためのリストを操作する。
 * </p>
 * @author inoueshota
 * @version 1.0
 */
public class TaskManager {
	// タスクを保持するリスト
	private List<Task> tasks = new ArrayList<>();
	
	/**
	 * プログラムのエントリーポイント
	 * @param args コマンドライン引数
	 */
	public static void main(String[] args) {
		TaskManager manager = new TaskManager();
		manager.run();
	}
	
	/**
	 * タスク管理システムのメインループを実行する。
	 * ユーザーに対してメニューを表示し、選択に応じた処理を実行する。
	 */
	public void run() {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("===== タスク管理システム =====");
			System.out.println("1. タスク登録");
			System.out.println("2. タスク一覧表示");
			System.out.println("3. タスク削除");
			System.out.println("4. 終了");
			System.out.print("選択： ");
			// ユーザー入力の受付
			int choice = scanner.nextInt();
			System.out.println("===== タスク管理システム =====");
			scanner.nextLine(); // 改行消費
			// 選択された機能に応じた処理
			switch (choice) {
				case 1:
					registerTask(scanner);
					break;
				case 2:
					showTasks();
					break;
				case 3:
					deleteTask(scanner);
					break;
				case 4:
					System.out.println("終了します。");
					scanner.close();
					return;
				default:
					System.out.println("無効な選択です。");
			}
		}
	}
	/**
	 * 新しいタスクを登録する。
	 * <p>
	 * ユーザにタスクのタイトルと期限を入力させ、新しいタスクオブジェクトを作成してリストに追加する。
	 * </p>
	 * @param scanner ユーザー入力用のScannerオブジェクト
	 */
	private void registerTask(Scanner scanner) {
		System.out.println("⭐⭐⭐⭐⭐ タスク登録 ⭐⭐⭐⭐⭐");
		System.out.print("タスクのタイトルを入力： ");
		String title = scanner.nextLine(); //タイトルの入力を受け付ける
		System.out.print("タスクの期限を入力（YYYY-MM-DD）： ");
		String deadline = scanner.nextLine(); // 期限の入力を受け付ける
		// タスクオブジェクトの生成とリストへの追加
		Task newTask = new Task(title, deadline);
		tasks.add(newTask);
		System.out.println("タスクを登録しました。");
		System.out.println("⭐⭐⭐⭐⭐ タスク登録 ⭐⭐⭐⭐⭐");
		scanner.nextLine(); // 改行消費
	}
	/**
	 * 登録された全てのタスク一覧を表示します。
	 * 	<p>
	 * タスクが存在しない場合は、その旨をユーザーに伝える。
	 * </p>
	 */
	private void showTasks() {
		if (tasks.isEmpty()) {
			System.out.println("タスクはありません");
			return;
		}
		System.out.println("~~~~~~~~~~~~~~~ タスク一覧 ~~~~~~~~~~~~~~~");
		// 各タスクの情報を表示するループ
		for (int i = 0; i < tasks.size(); i++) {
			Task t = tasks.get(i);
			// タスクIDの取得方法を変更　(i + 1)→t.getId()
			System.out.println(t.getId() + ". " + t.getTitle() + "（期限： " + t.getDeadline()
				+ ", 完了： " + t.isCompleted() + ")");
			System.out.println("~~~~~~~~~~~~~~~ タスク一覧 ~~~~~~~~~~~~~~~");
		}
	}
	/**
	 * 指定された番号のタスクを削除する。
	 * <p>
	 * まずタスク一覧を表示し、ユーザーに削除するタスクの番号を入力させる。
	 * 入力された番号が有効な場合、対応するタスクをリストから削除する。
	 * </p>
	 * @param scanner ユーザー入力用のScannerオブジェクト
	 */
	private void deleteTask(Scanner scanner) {
		// 現在のタスク一覧を表示
		showTasks();
		if (tasks.isEmpty()) return;
		
		System.out.print("削除するタスクの番号を入力： ");
		int index = scanner.nextInt();
		scanner.nextLine(); // 改行消費
		// 入力番号のチェック
		if (index < 1 || index > tasks.size()) {
			System.out.println("無効な番号です");
			return;
		}
		// タスクの削除処理
		tasks.remove(index - 1);
		System.out.println("タスクを削除しました。");
	}
}
