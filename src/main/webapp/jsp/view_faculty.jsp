<%@ page import="com.example.final_task_epam.util.Parameter" %>
<%@ page import="com.example.final_task_epam.model.entity.Faculty" %>
<%@ page import="com.example.final_task_epam.model.dao.implement.FacultyDaoImplement" %>
<%@ page import="com.example.final_task_epam.model.entity.Subject" %>
<%@ page import="com.example.final_task_epam.model.dao.implement.SubjectDaoImplement" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Компас
  Date: 8/28/2022
  Time: 11:31 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<% int facultyId = Integer.parseInt(request.getParameter(Parameter.ID));
    Faculty faculty = FacultyDaoImplement.getById(facultyId);
    session.setAttribute("faculty", faculty);%>

<h1>Faculty name - <c:out value="${faculty.name}"/></h1>


<h2> Faculty capacity -  <c:out value="${faculty.capacity}"/>  </h2>
<input type="hidden" name="capacity" value="${faculty.capacity}"/>
<h2> Budget places - first <c:out value="${faculty.budgetPlaces}"/> will be invited to Budget</h2>
<input type="hidden" name="budgetPlaces" value="${faculty.budgetPlaces}"/>
<h2>Required subject</h2>
<% List<Subject> subject = SubjectDaoImplement.getRequiredSubjects(facultyId);
    request.setAttribute("subject", subject);%>

<c:forEach var="subjectList" items="${subject}">
    <br> <c:out value="${subjectList.name}"/>
</c:forEach>

<%--<c:if test="${user.role=='candidate' }">--%>
<%--&& enrollment.state=='OPENED'--%>
<c:if test="${faculty.state==1}">
<c:if test="${sessionScope.user.role!='ADMIN'}">
<form class="submitButton" action="/ControllerServlet" method="post">
    <input type="hidden" name="command" value="apply"/>
    <input type="hidden" name="id" value="${faculty.id}"/>
    <input type="hidden" name="faculty" value="${faculty}"/>
    <button type="submit" name="command" value="apply">Registry</button>
    <br>
</form>
</c:if>
</c:if>
<c:if test="${faculty.state==2}">
    <h2>Faculty closed</h2>
</c:if>

<form action="/ControllerServlet" method="post">
    <input type="hidden" name="facultyId" value="${faculty.id}"/>
    <button type="submit" name="command" value="view_statement_of_faculty">View statement</button>
    <br>
</form>
<c:if test="${sessionScope.user.role=='ADMIN'}">
<form action="/ControllerServlet" method="post">
    <input type="hidden" name="facultyId" value="${faculty.id}"/>
    <input type="hidden" name="state" value="2"/>
    <button type="submit" name="command" value="close_faculty">Close faculty</button>
    <br>
</form>

    <form action="/ControllerServlet" method="post">
        <input type="hidden" name="facultyId" value="${faculty.id}"/>
        <input type="hidden" name="state" value="1"/>
        <button type="submit" name="command" value="close_faculty">Open faculty</button>
        <br>
    </form>
    <form action="/ControllerServlet" method="post">
        <input type="hidden" name="facultyId" value="${faculty.id}"/>
        <button type="submit" name="command" value="delete_faculty">Delete faculty</button>
        <br>
    </form>

</c:if>
<h1>${error}</h1>
<%--</c:if>--%>

</body>
</html>
