<%--
  Created by IntelliJ IDEA.
  User: Компас
  Date: 8/25/2022
  Time: 3:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>user menu</h1>
<form  action="/ControllerServlet" method="post">
    <button type="submit" name="command" value="view_all_faculties">View all faculties</button><br>
</form>

<form  action="/ControllerServlet" method="post">
    <input type="hidden" name="id" value="${sessionScope.user.userId}"/>
    <button type="submit" name="command" value="view_all_candidates">Cabinet of application</button><br>
</form>


</body>
</html>
