<%--
  Created by IntelliJ IDEA.
  User: Компас
  Date: 9/6/2022
  Time: 7:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<c:if test="${local == null}">
  <fmt:setLocale value="en"/>
</c:if>
<c:if test="${local != null}">
  <fmt:setLocale value="${local}"/>
</c:if>
<fmt:setBundle basename="localization" var="lang"/>
<html>
<head>
    <title>Welcome to the Admission Committe</title>
</head>
<body>
<center>
<h2>Please, select the language!</h2>
<c:if test="${local == null}">
    <fmt:setLocale value="en"/>
</c:if>
<c:if test="${local != null}">
    <fmt:setLocale value="${local}"/>
</c:if>
<fmt:setBundle basename="localization" var="lang"/>

<form id="login" action="/ControllerServlet" method="post">
    <select id="lang" name="local">
        <option value="en">EN</option>
        <option value="uk">UA</option>
    </select>
    <button type="submit" name="command" value="choose_language">Submit</button>


</form>
</center>
</body>
</html>
