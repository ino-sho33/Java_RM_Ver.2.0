<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="assignment9.TaskDAO" %>
<%@ page import="assignment3.Task" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>タスク一覧</title>
    <!-- ⭐︎外部CSSファイルを読み込み -->
    <link rel="stylesheet" href="css/style.css">
    <script>
    function toggleCompletion(taskId, currentStatus) {
        var newStatus = currentStatus ? 0 : 1; // boolean → int に変換 (MySQL用)

        var xhr = new XMLHttpRequest();
        xhr.open("POST", "updateTask", true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4 && xhr.status === 200) {
                alert("タスクの完了状態を更新しました。");
                location.reload();
            }
        };
        xhr.send("taskId=" + encodeURIComponent(taskId) + "&completed=" + encodeURIComponent(newStatus));
    }
    </script>
</head>
<body>
    <h1>タスク一覧</h1>
    
    <!-- ▽ 新規タスク追加フォーム ▽ -->
    <form class="add-task-form" action ="addTask" method="post">
    	<label>タスク名： <input type="text" name="title" required></label><br>
    	<label>期限：<input type="date" name="deadline" required></label><br>
    	<!-- 今回はuseerId=1　に固定しておく。実際はログイン中のユーザーのIDを設定 -->
    	<input type="hidden" name="userId" value="1">
    	<button type="submit">タスクを追加</button>
    </form>
    <hr>
    
    <%
        // DBからタスクリストを取得
        TaskDAO dao = new TaskDAO();
        java.util.List<Task> taskList = dao.getAllTasks(); //全件取得
    %>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>タイトル</th>
            <th>期限</th>
            <th>完了</th>
            <th>操作</th>
        </tr>
        <%
            for (Task t : taskList) {
                // IDを取得するメソッドをTaskクラスに用意している前提
                // ここでは setId/getId の実装が必要
        %>
        <tr class="<%= t.isCompleted() ? "completed-row" : "" %>">
            <td><%= t.getId() %></td>
            <td><%= t.getTitle() %></td>
            <td><%= t.getDeadline() %></td>
            <td><%= t.isCompleted() ? "完了済" : "未完了" %></td>
            <td>
                <button onclick="toggleCompletion(<%= t.getId() %>, <%= t.isCompleted() %>)">
                    完了切替
                </button>
                
                <!-- 編集ボタン（GETリクエストでeditTaskへ） -->
                <form action="editTask" method="get" style="display:inline;">
                	<input type="hidden" name="taskId" value="<%= t.getId() %>">
                	<button type="submit" class="edit-btn">
                		編集
                	</button>
                </form>
               
                <!-- 削除ボタン（GETリクエストでDeleteTaskServletにtaskIdを送る） -->
                <form action="deleteTask" method="get" style="display:inline;">
                	<input type="hidden" name="taskId" value="<%=t.getId() %>">
                	<button type="submit" onclick="return confirm('本当に削除しますか?');">
                		削除
                	</button>
                </form>
            </td>
        </tr>
        <%
            }
        %>
    </table>
</body>
</html>
