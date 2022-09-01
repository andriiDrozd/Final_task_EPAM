<%@ page import="com.example.final_task_epam.model.entity.Subject" %>
<%@ page import="com.example.final_task_epam.model.dao.implement.SubjectDaoImplement" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Компас
  Date: 8/27/2022
  Time: 4:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div id="site_content">




  <div id="content">


    <form action="/ControllerServlet" method="post">
      <input type="hidden" name="command" value="add_faculty"/>

     <input type="text" name="faculty_name" placeholder="Faculty name"/>
        <input type="number" name="capacity" placeholder="Capacity"/> ${error}
        <input type="number" name="budget_places" placeholder="Budget places"/><br>

      <% List<Subject> subjects = SubjectDaoImplement.getAllSubjects();
        request.setAttribute("subjects", subjects);%>

      <c:forEach items="${subjects}" var="subject">
        <input  type="checkbox" name="subject_id"
               value="${subject.subjectId}"/><c:out value="${subject.name}"/>
        <br/>
      </c:forEach>
        <h4>State of Faculty</h4>
        <input type="radio" id="state" name="state"
               value="open" >
        <label for="state">Open</label>
        <input type="radio" id="state" name="state"
               value="closed" >
        <label for="state">Closed</label>


      <input type="submit" value="Add faculty"/>
    </form>
  </div>
</div>
</body>
</html>
