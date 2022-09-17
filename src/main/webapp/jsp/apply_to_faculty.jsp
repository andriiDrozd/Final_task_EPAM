<%@ page import="com.example.final_task_epam.util.Parameter" %>
<%@ page import="com.example.final_task_epam.model.dao.implement.FacultyDaoImplement" %>
<%@ page import="com.example.final_task_epam.model.entity.Faculty" %>
<%@ page import="com.example.final_task_epam.model.dao.implement.SubjectDaoImplement" %>
<%@ page import="com.example.final_task_epam.model.entity.Subject" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Компас
  Date: 8/28/2022
  Time: 2:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="header.jsp"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Hi!</h1>
<div id="content">

    <%Faculty faculty=(Faculty)session.getAttribute("faculty");
    session.setAttribute("faculty",faculty);%>


    <h1>Faculty-<c:out value="${faculty.name}"/></h1>

    <form id="infoMessage" action="/ControllerServlet" method="post">
        <input name="command" type="hidden" value="apply_to_faculty"/>

    <%   int facultyId = Integer.parseInt(request.getParameter(Parameter.ID));
        List<Subject> subject = SubjectDaoImplement.getRequiredSubjects(facultyId);
        request.setAttribute("subject", subject);%>

        <c:forEach items="${subject}" var="subject">
            <input class="register_field" name="grade" placeholder="${subject.name}"/><br>
        </c:forEach>

        <input type="hidden" name="id" value="${faculty.id}"/>
        <button type="submit" name="command" value="apply_to_faculty">Registry</button>
    </form>
    ${error.marks}
</div>
</body>
</html>
