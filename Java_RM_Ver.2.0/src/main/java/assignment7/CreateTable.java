package assignment7;

public class CreateTable {
	
	//------------------------------------------------------------------------
	//タスクテーブルの作成（データベース設計）
	//------------------------------------------------------------------------
	/**
	 * 目的
	 * MySQLなどRDBMS上でタスク情報を保持するテーブルを作成
	 * SQLでDBを操作する基礎を学ぶ
	 */
	
	//-------------------------------
	//データベース作成SQL
	//-------------------------------
	//DATABASE `Java_RM_Ver.2.0`;
	//USE `Java_RM_Ver.2.0`;
	
	//-------------------------------
	//タスクテーブル作成
	//-------------------------------
	/* CREATE TABLE tasks (
	 * 	id INT AUTO_INCREMENT PRIMARY KEY,
	 * 	title VARCHAR(255) NOT NULL,
	 * 	deadline DATE,
	 * 	completed BOOLEAN NOT NULL DEFAULT FALSE,
	 * 	category VARCHAR(50)
	 * );
	 */
	
	/* 
	 * ポイント：
	 * category列は「仕事用」「個人用」などの区別に使う想定。
	 * ここでは簡単のために継承構造をDB上は「category」という文字列で管理する例を示しています。
	 * 実際の運用ではテーブル分割やカラム設計をどうするか検討する
	 */
	
	
	
	
	
	
	
	
	
	

}
