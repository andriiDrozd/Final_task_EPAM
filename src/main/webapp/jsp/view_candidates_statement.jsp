<%--
  Created by IntelliJ IDEA.
  User: Компас
  Date: 8/30/2022
  Time: 5:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="header.jsp"%>
<fmt:setLocale value="${local}"/>
<fmt:setBundle basename="localization" var="lang"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table id="myTable" class="display" border="1" cellpadding="20%">
    <thead>
    <tr>
        <th>No</th>
        <th><fmt:message key="candidate" bundle="${lang}" /></th>

        <th><fmt:message key="total_rating" bundle="${lang}" /></th>

    </tr>
    </thead>
    <tbody>

    <c:forEach items="${requestScope.candidates}" var="candidate">
<%--        <c:if test="${candidate.candidateState.ordinalNumber==2}">--%>
        <tr>
            <td><c:out value="${candidate.rating_position}"/> </td>
            <td>
                 <c:out value="${candidate.name}  "/><c:out value="${candidate.surname}"/> </a>
            </td>

            <td><c:out value="${candidate.totalRating}"/></td>

        </tr>
<%--        </c:if>--%>
    </c:forEach>

<h1><fmt:message key="${requestScope.faculty_closed}" bundle="${lang}" /></h1>
    </tbody>
</table>

</body>
</html>
