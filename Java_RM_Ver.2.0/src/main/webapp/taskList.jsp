<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="assignment9.TaskDAO" %>
<%@ page import="assignment3.Task" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>タスク一覧</title>
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
    <%
        // DBからタスクリストを取得
        TaskDAO dao = new TaskDAO();
        java.util.List<Task> taskList = dao.getTasksOrderByDeadline(); // 期限順取得
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
        <tr>
            <td><%= t.getId() %></td>
            <td><%= t.getTitle() %></td>
            <td><%= t.getDeadline() %></td>
            <td><%= t.isCompleted() %></td>
            <td>
                <button onclick="toggleCompletion(<%= t.getId() %>, <%= t.isCompleted() %>)">
                    完了切替
                </button>
            </td>
        </tr>
        <%
            }
        %>
    </table>
</body>
</html>
