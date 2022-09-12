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
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${local}"/>
<fmt:setBundle basename="localization" var="lang"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div id="site_content">
    <h2><c:if test="${subject_error!=null}"><fmt:message key="${subject_error}" bundle="${lang}"/></c:if><br></h2>
    <h2><c:if test="${faculty_error!=null}"><fmt:message key="${faculty_error}" bundle="${lang}"/></c:if><br></h2>
    <h2><c:if test="${capacity_error!=null}"><fmt:message key="${capacity_error}" bundle="${lang}"/></c:if><br></h2>

  <div id="content">


    <form action="/ControllerServlet" method="post">
      <input type="hidden" name="command" value="add_faculty"/>

     <input type="text" name="faculty_name" placeholder="<fmt:message key="faculty_name" bundle="${lang}" />" value="${facultyName}"/>
        <input type="number" name="capacity" placeholder="<fmt:message key="capacity" bundle="${lang}" />"/> ${error}
        <input type="number" name="budget_places" placeholder="<fmt:message key="budget_places" bundle="${lang}" />"/><br>

      <% List<Subject> subjects = SubjectDaoImplement.getAllSubjects();
        request.setAttribute("subjects", subjects);%>

      <c:forEach items="${subjects}" var="subject">
        <input  type="checkbox" name="subject_id"
               value="${subject.subjectId}"/><c:out value="${subject.name}"/>
        <br/>
      </c:forEach>
        <h4><fmt:message key="state_of_faculty" bundle="${lang}" /></h4>
        <input type="radio" id="state" name="state"
               value="open" >
        <label for="state"><fmt:message key="open_faculty" bundle="${lang}" /></label>
        <input type="radio" id="state" name="state"
               value="closed" >
        <label for="state"><fmt:message key="close_faculty" bundle="${lang}" /></label>


      <input type="submit" value="<fmt:message key="add_faculty" bundle="${lang}" />"/>
    </form>
  </div>
</div>
</body>
</html>
