package assignment5;

import assignment3.Task;

//------------------------------------------------------------------------
//カテゴリ機能の追加（継承）「個人用タスク」
//------------------------------------------------------------------------
/* 目的
* 「仕事用タスク」と「個人用タスク」のカテゴリをクラスで分ける
* 継承を活用し、コードの再利用性を高める
* */

public class PersonalTask extends Task{
	
	//--------------------------------------------------------
	// parsonalTaskクラス独自のフィールド
	//--------------------------------------------------------	
	private String note;	// メモ
	
	//--------------------------------------------------------
	// コンストラクタ
	//--------------------------------------------------------	
	public PersonalTask(String title, String deadline, String note) {
		super(title, deadline);
		this.note = note;
	}
	//--------------------------------------------------------
	// ゲッター
	//--------------------------------------------------------		
	public String getNote() {
		return note;
	}
	
	@Override
	public void printTaskInfo() {
		System.out.println("[個人用]タスクID： " + getId()
				+ ",タイトル： " + getTitle()
				+ ",期限： " + getDeadline()
				+ ",完了： " + getCompleted()
				+ ",メモ： " + note);
	}

}
