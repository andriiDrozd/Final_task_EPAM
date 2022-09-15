<%--
  Created by IntelliJ IDEA.
  User: Компас
  Date: 8/25/2022
  Time: 3:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<center>
<fmt:setLocale value="${local}"/>
<fmt:setBundle basename="localization" var="lang"/>
<h1><fmt:message key="user_menu" bundle="${lang}"/></h1>
<form  action="/ControllerServlet" method="post">
    <button type="submit" name="command" value="view_all_faculties"><fmt:message key="view_all_faculties" bundle="${lang}"/></button><br>
</form>

<form  action="/ControllerServlet" method="post">
    <input type="hidden" name="id" value="${sessionScope.user.userId}"/>
    <button type="submit" name="command" value="view_all_candidates"><fmt:message key="cabinet_of_application" bundle="${lang}"/></button><br>
</form>
    <form  action="/ControllerServlet" method="post">
        <button type="submit" name="command" value="logout">logout</button><br>
    </form>
</center>
</body>
</html>
