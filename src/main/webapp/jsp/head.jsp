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
<%--<% if(session.getAttribute("lang")==null) {--%>
<%--    session.setAttribute("lang",1);--%>
<%--}--%>
<%--%>--%>

<c:if test="${param.id==1}">
<fmt:setLocale value="en"/>
<fmt:setBundle basename="page_en_US" var="lang"/>
</c:if>

<c:if test="${param.id==0||param.id==null}">

    <fmt:setLocale value="uk"/>
    <fmt:setBundle basename="page_uk_UA" var="lang"/>
</c:if>

<a href="?id=1">English</a>
<a href="?id=0">Ukraine</a>
<%--<button type="submit" name="id" value="null">EN</button><br>--%>
<%--<button type="submit" name="id" value="1">UA</button><br>--%>

<%--<a href="?page/page_en_US">English</a>--%>
<%--<a href="?page/page_uk_UA">Ukraine</a>--%>
<%--    <fmt:setLocale value="${param.locale}"/>--%>
<%--<fmt:setBundle basename="localization" var="lang"/>--%>



<%--<fmt:setLocale value="${param.locale}"/>--%>


</body>
</html>
