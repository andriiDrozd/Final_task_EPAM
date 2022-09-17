<%@ page import="com.example.final_task_epam.model.dao.implement.FacultyDaoImplement" %>
<%@ page import="com.example.final_task_epam.model.entity.Faculty" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.final_task_epam.model.dao.FacultyDao" %>
<%@ page import="com.example.final_task_epam.util.Parameter" %><%--
  Created by IntelliJ IDEA.
  User: Компас
  Date: 8/28/2022
  Time: 10:15 AM
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
<form>
    <center>
    <h2><c:if test="${error!=null}"><fmt:message key="${error}" bundle="${lang}"/></c:if><br></h2>
    <form  action="/ControllerServlet" method="post">
        <fmt:message key="sort_by" bundle="${lang}"/>
        <label>
    <select name="type" class="select-css">
        <option value="1"> A-Z</option>
        <option value="2"> Z-A</option>
        <option value="3"> <fmt:message key="capacity" bundle="${lang}"/></option>
        <option value="4"><fmt:message key="budget_places" bundle="${lang}"/></option>
    </select>
</label>
        <button type="submit" name="command" value="view_all_faculties"><fmt:message key="submit" bundle="${lang}"/></button><br>
    </form>

    <c:forEach var="faculty" items="${requestScope.list_of_faculties}">
       <h3> <li><a style="color: darkcyan" href="/jsp/view_faculty.jsp?id=${faculty.id}"> <c:out value="${faculty.name}"/></a></li></h3>
                    <h4><fmt:message key="capacity" bundle="${lang}"/>: <c:out value="${faculty.capacity}"/>,
                        <fmt:message key="budget_places" bundle="${lang}"/>: <c:out value="${faculty.budgetPlaces}"/></h4>
    </c:forEach>


</form>

<c:if test="${sessionScope.user.role=='ADMIN'}">
    <h2><a href="/jsp/add_faculty.jsp"><fmt:message key="add_faculty" bundle="${lang}"/></a></h2>
</c:if>
</center>
</div>
</body>
</html>
