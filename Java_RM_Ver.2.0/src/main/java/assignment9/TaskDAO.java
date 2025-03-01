package assignment9;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import assignment3.Task;

/**
 * タスク管理のためのDAOクラス。
 * データベースを操作し、タスクの作成、取得、更新、削除（CRUD）を行う。
 */
public class TaskDAO {
    
    /** データベースの接続URL */
    private static final String URL = "jdbc:mysql://localhost:3306/Java_RM_Ver.2.0";
    /** データベースのユーザー名 */
    private static final String USER = "devuser01";
    /** データベースのパスワード */
    private static final String PASS = "3906Testosterone1139";
    
    /**
     * タスクをデータベースに追加する。
     *
     * @param task   追加するタスクオブジェクト
     * @param userId ユーザーID
     */
    public void createTask(Task task, int userId) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // JDBCドライバーのロード
            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            String sql = "INSERT INTO tasks (title, deadline, completed, category, user_id) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, task.getTitle());
                pstmt.setString(2, task.getDeadline());
                pstmt.setBoolean(3, task.getCompleted());
                pstmt.setString(4, "general"); // カテゴリをデフォルトで"general"に設定
                pstmt.setInt(5, userId);
                pstmt.executeUpdate(); // クエリ実行
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 指定されたIDのタスクを取得する。
     *
     * @param taskId 取得するタスクのID
     * @return 取得したタスクのオブジェクト（存在しない場合はnull）
     */
    public Task getTaskById(int taskId) {
        String sql = "SELECT id, title, deadline, completed, category, user_id FROM tasks WHERE id = ?";
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // JDBCドライバーのロード
            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, taskId);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        Task task = new Task(rs.getString("title"), rs.getString("deadline"));
                        task.setCompleted(rs.getBoolean("completed"));
                        return task;
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 指定されたIDのタスク情報を更新する。
     *
     * @param taskId      更新対象のタスクID
     * @param newTitle    新しいタイトル
     * @param newDeadline 新しい締切日
     * @param newCompleted 完了状態（true: 完了、false: 未完了）
     */
    public void updateTask(int taskId, String newTitle, String newDeadline, boolean newCompleted) {
        String sql = "UPDATE tasks SET title = ?, deadline = ?, completed = ? WHERE id = ?";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // JDBCドライバーのロード
            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, newTitle);
                pstmt.setString(2, newDeadline);
                pstmt.setBoolean(3, newCompleted);
                pstmt.setInt(4, taskId);
                pstmt.executeUpdate(); // クエリ実行
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 指定されたIDのタスクをデータベースから削除する。
     *
     * @param taskId 削除するタスクのID
     */
    public void deleteTask(int taskId) {
        String sql = "DELETE FROM tasks WHERE id = ?";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // JDBCドライバーのロード
            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, taskId);
                pstmt.executeUpdate(); // クエリ実行
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}