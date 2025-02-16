package assignment6;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import assignment3.Task;
import assignment5.PersonalTask;
import assignment5.WorkTask;

//------------------------------------------------------------------------
//タスク検索機能の追加
//------------------------------------------------------------------------
/* 目的
* タイトルや期限などの条件でタスクをフィルタリング
* オブジェクト指向の設計を応用し、機能拡張
* */

/**
 * TaskManagerWithSearchクラスは、タスク管理システムにタスク検索機能を追加したものです。<br>
 * タイトルや期限によるフィルタリング機能を提供し、タスクの一覧表示も行います。
 * <p>
 * このクラスは、WorkTaskやPersonalTaskなどTaskのサブクラスのオブジェクトを管理し、
 * ユーザーの入力に基づいてタスクを検索・表示します。
 * </p>
 * 
 * <p>
 * 実行例：
 * <pre>
 * ====== タスク管理システム ======
 * 1.タスク検索（タイトル）
 * 2.タスク検索（期限）
 * 3.タスク一覧表示
 * 4.終了
 * 選択：
 * </pre>
 * </p>
 * 
 * @author inoueshota
 * @version 1.0
 */

public class TaskManagerWithSearch {
	// タスクを保持するリスト。Taskのサブクラスのインスタンスを格納する。
	private List<Task> tasks = new ArrayList<>();
	/**
	 * プログラムのエントリーポイント。
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		TaskManagerWithSearch manager = new TaskManagerWithSearch();
		manager.demoData(); // デモ用の仮データを作成
		manager.run();		// タスク管理システムを実行
	}
	/**
	 * デモ用の仮データを作成。
	 * <p>
	 * WorkTaskおよびPersonalTaskのインスタンスを生成し、タスクリストに追加する。
	 * </p>
	 */
	public void demoData() {
		tasks.add(new WorkTask("会議資料作成", "2025-03-01", "新製品プロジェクト"));
		tasks.add(new PersonalTask("買い物", "2025-02-28", "スーパーで食材を購入"));
		tasks.add(new WorkTask("契約書確認", "2025-03-05", "クライアントA"));
		tasks.add(new PersonalTask("ジョギング", "2025-02-20", "朝5時から"));
	}
	/**
	 * タスク管理システムのメインループを実行。
	 * <p>
	 * ユーザーからの入力に基づいて、タスクの検索（タイトルまたは期限）、一覧表示、または終了を行う。
	 * </p>
	 */
	public void run() {
		Scanner scanner = new Scanner(System.in);
		while(true) {
			// メニューの表示
			System.out.println("====== タスク管理システム ======");
			System.out.println("1. タスク検索（タイトル）");
			System.out.println("2. タスク検索（期限）");
			System.out.println("3. タスク一覧表示");
			System.out.println("4. 終了");
			System.out.print("選択： ");
			// ユーザー入力の受付
			int choice = scanner.nextInt();
			scanner.nextLine(); // 改行文字の消費
			
			// ユーザーの選択に応じた処理の実行
			switch (choice) {
				case 1:
					// タイトルによるタスク検索
					System.out.print("検索キーワード（タイトル）: ");
					String keyword = scanner.nextLine();
					List<Task> resultTitle = searchByTitle(keyword);
					printTasks(resultTitle);
					break;
				case 2:
					// 期限によるタスク検索
					System.out.print("検索する期限（YYYY-MM-DD）: ");
					String deadline = scanner.nextLine();
					List<Task> resultDeadline = searchByDeadline(deadline);
					printTasks(resultDeadline);
					break;
				case 3:
					// 全タスクの一覧表示
					printTasks(tasks);
					break;
				case 4:
					// プログラムの終了
					System.out.println("終了します。");
					scanner.close();
					return;
				default:
					// 無効な入力があった場合の処理
					System.out.println("無効な選択です。");
			}		
		}
	}
	
	/**
	 * タイトルに指定したキーワードが含まれるタスクを検索する。
	 * 
	 * @param keyword 検索キーワード（タイトルに含まれる文字列）
	 * @return キーワードを含むタスクリスト
	 */
	public List<Task> searchByTitle(String keyword){
		List<Task> result = new ArrayList<>();
		// タスクリスト内の各タスクに対してキーワードを含むかチェック
		for (Task t: tasks){
			if (t.getTitle().contains(keyword)) {
				result.add(t);
			}
		}
		return result;
	}
	
	/**
	 * 指定した期限と一致するタスクを検索。
	 * @param deadline 検索する期限（YYYY-MM-DD形式）
	 * @return 指定した期限と一致するタスクのリスト
	 */
	public List<Task> searchByDeadline(String deadline){
		List<Task> result = new ArrayList<>();
		// タスクリスト内の各タスクの期限と一致するかチェック
		for (Task t: tasks) {
			if (t.getDeadline().equals(deadline)) {
				result.add(t);
			}
		}
		return result;
	}
	
	/**
	 * 指定されたタスクリストのタスク情報を表示する。
	 * 
	 * @param list
	 */
	private void printTasks(List<Task> list) {
		// リストが空の場合はメッセージを表示して終了
		if(list.isEmpty()) {
			System.out.println("該当するタスクはありません。");
			return;
		}
		// 各タスクの情報を表示
		for (Task t: list) {
			t.printTaskInfo();
		}
	}
}
