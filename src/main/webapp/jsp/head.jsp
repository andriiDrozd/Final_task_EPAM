<%--
  Created by IntelliJ IDEA.
  User: Компас
  Date: 9/5/2022
  Time: 2:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--<c:set var="currentContext" scope="session"--%>
<%--       value="${pageContext.servletContext.contextPath}"/>--%>

<%--<c:if test="${not empty param.language}">--%>
<%--    <c:set var="language"--%>
<%--           value="${param.language}"--%>
<%--           scope="session"/>--%>
<%--</c:if>--%>

<html>
<head>
    <title>Title</title>
</head>
<body>
<%--<fmt:setLocale value="en"/>--%>
<fmt:setBundle basename="localization_ua" var="lang"/>

<fmt:message key="email" bundle="${lang}"/><br/>
<fmt:message key="name" bundle="${lang}"/><br/>
<fmt:message key="surname" bundle="${lang}"/><br/>
</body>
</html>
