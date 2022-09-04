<%--
  Created by IntelliJ IDEA.
  User: Компас
  Date: 8/25/2022
  Time: 2:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<h1>Log In</h1>
<body>
<form id="login" action="/ControllerServlet" method="post">

    <h1>${loginError}</h1>

    <input id="email" type="email" name="email" placeholder="E-mail" required  value=${email} ><br>
    <input id="password" type="password" name="password" placeholder="Password" required><br>
    <button type="submit" name="command" value="login">Login</button><br>
</form>

<%--<form id="view_faculty" action="/ControllerServlet" method="post">--%>
<%--    <button type="submit" name="command" value="view_faculty">View all faculties</button><br>--%>
<%--</form>--%>

<form  action="/ControllerServlet" method="post">
    <button type="submit" name="command" value="view_all_faculties">View all faculties</button><br>
</form>

<h2><a href="jsp/registration_user.jsp">Registry</a></h2>
</body>
</html>
