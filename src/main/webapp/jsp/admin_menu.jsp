<%--
  Created by IntelliJ IDEA.
  User: Компас
  Date: 8/25/2022
  Time: 4:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>admin_menu</h1>



<h2><a href="jsp/view_subject.jsp">View Subject</a></h2>

<form id="login" action="/ControllerServlet" method="post">

<button type="submit" name="command" value="view_all_candidates">View all candidates</button><br>

</form>

<form  action="/ControllerServlet" method="post">
    <button type="submit" name="command" value="view_all_faculties">View all faculties</button><br>
</form>
</body>
</html>
