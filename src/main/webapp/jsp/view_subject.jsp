<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.final_task_epam.model.entity.Subject" %>
<%@ page import="com.example.final_task_epam.model.dao.implement.SubjectDaoImplement" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: Компас
  Date: 8/27/2022
  Time: 4:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Title</title>

</head>
<body>
<form>
    <% List<Subject> subjects = SubjectDaoImplement.getAllSubjects();
        request.setAttribute("subjects", subjects);%>

    <c:forEach var="subject" items="${subjects}">
        <li>
            <ur>
                <c:out value="${subject.subjectId}."/>
                <c:out value="${subject.name}"/>
            </ur>
        </li>
    </c:forEach>
</form>

<form id="addSubject" action="/ControllerServlet" method="post">

    <input id="subject_name" name="subject_name" placeholder="Subject"/>

    <button type="submit" name="command" value="add_subject">Add Subject</button>

</form>
</body>
</html>
