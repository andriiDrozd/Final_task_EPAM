<%--
  Created by IntelliJ IDEA.
  User: Компас
  Date: 8/25/2022
  Time: 4:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<fmt:setLocale value="${local}"/>
<fmt:setBundle basename="localization" var="lang"/>
<%--<fmt:bundle basename="page">--%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<center>
<h1><fmt:message key="admin_menu" bundle="${lang}"/></h1>



<h2><a href="jsp/view_subject.jsp"><fmt:message key="view_subject" bundle="${lang}" /></a></h2>

<form id="login" action="/ControllerServlet" method="post">

<button type="submit" name="command" value="view_all_candidates"><fmt:message key="view_all_candidates" bundle="${lang}"/></button><br>

</form>

<form  action="/ControllerServlet" method="post">
    <button type="submit" name="command" value="view_all_faculties"><fmt:message key="view_all_faculties" bundle="${lang}"/></button><br>
</center>
</form>
</body>
</html>
<%--</fmt:bundle>--%>