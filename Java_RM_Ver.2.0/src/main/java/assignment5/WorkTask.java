package assignment5;

import assignment3.Task;

//------------------------------------------------------------------------
//カテゴリ機能の追加（継承）「仕事用タスク」
//------------------------------------------------------------------------
/* 目的
 * 「仕事用タスク」と「個人用タスク」のカテゴリをクラスで分ける
 * 継承を活用し、コードの再利用性を高める
 * */

public class WorkTask extends Task{
	//--------------------------------------------------------
	// WorkTaskクラス独自のフィールド
	//--------------------------------------------------------	
	private String projectName;	// プロジェクト名
	
	//--------------------------------------------------------
	// コンストラクタ
	//--------------------------------------------------------	
	public WorkTask(String title, String deadline, String projectName) {
		super(title, deadline);
		this.projectName = projectName;
	}
	//--------------------------------------------------------
	// ゲッター
	//--------------------------------------------------------		
	public String getProjectName() {
		return projectName;
	}
	
	@Override
	public void printTaskInfo() {
		System.out.println("[仕事用]タスクID： " + getId()
				+ ",タイトル： " + getTitle()
				+ ",期限： " + getDeadline()
				+ ",完了： " + isCompleted()
				+ ",プロジェクト： " + projectName);
	}
}
