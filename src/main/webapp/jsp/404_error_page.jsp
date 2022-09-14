<%--
  Created by IntelliJ IDEA.
  User: Компас
  Date: 9/14/2022
  Time: 4:37 PM
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

<h1> 404 ERROR</h1>

<fmt:setLocale value="${local}"/>
<fmt:setBundle basename="localization" var="lang"/>

<html>
<head>
  <title>Title</title>
</head>
<body>
<h1><fmt:message key="something_went_wrong" bundle="${lang}"/></h1>
<h2><c:if test="${access_error!=null}"><fmt:message key="${access_error}" bundle="${lang}"/></c:if><br></h2>
</body>
</html>
