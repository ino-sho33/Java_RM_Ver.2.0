<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="assignment3.Task" %>
<!DOCTYPE html>
<html>
<head>
    <title>タスク編集</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <h1>タスク編集</h1>

    <%
        // EditTaskServlet から受け取った task
        Task task = (Task) request.getAttribute("task");
        if (task == null) {
            // 直接このJSPにアクセスされた場合など
            response.sendRedirect("taskList.jsp");
            return;
        }
    %>

	<!-- 更新フォーム -->
	<form action="editTask" method="post" style="display:inline; margin-right:10px;">
	    <!-- タスクIDを hidden で送る -->
	    <input type="hidden" name="taskId" value="<%= task.getId() %>">
	
	    <div>
	        <label>タイトル：</label>
	        <input type="text" name="title" value="<%= task.getTitle() %>" required>
	    </div>
	    <div>
	        <label>期限：</label>
	        <input type="date" name="deadline" value="<%= task.getDeadline() %>" required>
	    </div>
	    <div>
	        <label>完了：</label>
	        <input type="checkbox" name="completed" value="1" <%= task.isCompleted() ? "checked" : "" %>>
	    </div>
	
	    <button type="submit" class="update-btn">更新</button>
	</form>
	
	<!-- キャンセルボタン (一覧へ戻る) -->
	<form action="taskList.jsp" method="get" style="display:inline;">
	    <button type="submit" class="cancel-btn">キャンセル</button>
	</form>

</body>
</html>
