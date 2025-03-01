package assignment9;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import assignment3.Task;

/**
 * タスク管理のためのDAOクラス。
 * データベースを操作し、タスクの作成、取得、更新、削除（CRUD）を行う。
 */
public class TaskDAO {

    /** データベースの接続URL */
    private static final String URL = "jdbc:mysql://localhost:3306/Java_RM_Ver2?serverTimezone=UTC";
    /** データベースのユーザー名 */
    private static final String USER = "devuser01";
    /** データベースのパスワード */
    private static final String PASS = "3906Testosterone1139";

    /** JDBCドライバーのロード（明示的に実行） */
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("✅ MySQL JDBC Driver successfully loaded!");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("❌ JDBC Driver not found!", e);
        }
    }

    /**
     * タスクをデータベースに追加する。
     */
    public void createTask(Task task, int userId) {
        String sql = "INSERT INTO tasks (title, deadline, completed, category, user_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, task.getTitle());
            pstmt.setString(2, task.getDeadline());
            pstmt.setBoolean(3, task.isCompleted());
            pstmt.setString(4, "general");
            pstmt.setInt(5, userId);

            pstmt.executeUpdate();
            System.out.println("✅ タスク追加成功: " + task.getTitle());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 指定されたIDのタスクを取得する。
     */
    public Task getTaskById(int taskId) {
        String sql = "SELECT id, title, deadline, completed, category, user_id FROM tasks WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, taskId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Task task = new Task(rs.getString("title"), rs.getString("deadline"));
                    task.setCompleted(rs.getBoolean("completed"));
                    return task;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 指定されたIDのタスク情報を更新する。
     */
    public void updateTask(int taskId, String newTitle, String newDeadline, boolean newCompleted) {
        String sql = "UPDATE tasks SET title = ?, deadline = ?, completed = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newTitle);
            pstmt.setString(2, newDeadline);
            pstmt.setBoolean(3, newCompleted);
            pstmt.setInt(4, taskId);
            pstmt.executeUpdate();
            System.out.println("✅ タスク更新成功: ID " + taskId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 指定されたIDのタスクをデータベースから削除する。
     */
    public void deleteTask(int taskId) {
        String sql = "DELETE FROM tasks WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, taskId);
            pstmt.executeUpdate();
            System.out.println("✅ タスク削除成功: ID " + taskId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * タスクの完了状態を更新する。
     */
    public void updateTaskCompletion(int taskId, boolean completed) {
        String sql = "UPDATE tasks SET completed = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setBoolean(1, completed);
            pstmt.setInt(2, taskId);
            pstmt.executeUpdate();
            System.out.println("✅ タスク完了状態更新: ID " + taskId + " -> " + completed);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 期限順にタスクを取得する。
     */
    public List<Task> getTasksOrderByDeadline() {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT id, title, deadline, completed, category, user_id FROM tasks ORDER BY deadline ASC";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Task task = new Task(rs.getString("title"), rs.getString("deadline"));
                task.setCompleted(rs.getBoolean("completed"));
                tasks.add(task);
            }
            System.out.println("✅ タスク一覧取得成功: " + tasks.size() + "件");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }
}
