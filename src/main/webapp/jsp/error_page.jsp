<%--
  Created by IntelliJ IDEA.
  User: Компас
  Date: 9/8/2022
  Time: 8:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="/jsp/head.jsp" %>

<fmt:setLocale value="${local}"/>
<fmt:setBundle basename="localization" var="lang"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1><fmt:message key="something_went_wrong" bundle="${lang}"/></h1>
<c:if test="${empty error}">
    <c:if test="${requestScope['javax.servlet.error.status_code']==404}">
        <c:set var="error" value="not_found_error"/>
    </c:if>
    <c:if test="${requestScope['javax.servlet.error.status_code']==503}">
        <c:set var="error" value="internal_error"/>
    </c:if>
</c:if>
<c:if test="${not empty error}">
    <p style="color: firebrick"><fmt:message key="${error}" bundle="${lang}"/></p>
</c:if>
<c:remove var="error" scope="session"/>
</body>
</html>
