<%--
  Created by IntelliJ IDEA.
  User: Компас
  Date: 8/25/2022
  Time: 2:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<fmt:setLocale value="${local}"/>
<fmt:setBundle basename="localization" var="lang"/>

<html>
<head>
    <title>Title</title>
</head>
<center>
<h1><fmt:message key="login" bundle="${lang}"/></h1>
<body>
<form id="login" action="/ControllerServlet" method="post">


 <h2><c:if test="${loginError!=null}"><fmt:message key="${loginError}" bundle="${lang}"/></c:if><br></h2>



    <input id="email" type="email" name="email" placeholder="<fmt:message key="Email" bundle="${lang}"/>" required value=${email}  ><br>
    <input id="password" type="password" name="password" placeholder="<fmt:message key="password" bundle="${lang}"/>" required><br>
    <button type="submit" name="command" value="login"><fmt:message key="login" bundle="${lang}"/></button><br>

</form>

<%--<form id="view_faculty" action="/ControllerServlet" method="post">--%>
<%--    <button type="submit" name="command" value="view_faculty">View all faculties</button><br>--%>
<%--</form>--%>

<form  action="/ControllerServlet" method="post">
    <button type="submit" name="command" value="view_all_faculties"><fmt:message key="view_all_faculties" bundle="${lang}"/></button><br>
</form>

<h2><a href="jsp/registration_user.jsp"><fmt:message key="registry" bundle="${lang}"/></a></h2>
</center>

<form  action="/ControllerServlet" method="post">
    <button type="submit" name="command" value="pagination">Pagination</button><br>
</form>
</body>
</html>
<%--</fmt:bundle>--%>