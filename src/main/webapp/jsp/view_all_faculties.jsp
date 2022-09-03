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
<html>
<head>
    <title>Title</title>
</head>
<body>
<form>

    <% List<Faculty> faculties = FacultyDaoImplement.getAllFaculties();
        request.setAttribute("faculties", faculties);%>
<%--    <form  action="/ControllerServlet" method="post">--%>
<%--        <input type="hidden" name="type" value="1"/>--%>
<%--        <button type="submit" name="command" value="view_all_faculties">Sort by A-Z</button><br>--%>
<%--    </form>--%>

<%--    <form  action="/ControllerServlet" method="post">--%>
<%--        <input type="hidden" name="type" value="2"/>--%>
<%--        <button type="submit" name="command" value="view_all_faculties">Sort by Z-A</button><br>--%>
<%--    </form>--%>

<%--    <form  action="/ControllerServlet" method="post">--%>
<%--        <input type="hidden" name="type" value="3"/>--%>
<%--        <button type="submit" name="command" value="view_all_faculties">Sort by Capacity</button><br>--%>
<%--    </form>--%>

<%--    <form  action="/ControllerServlet" method="post">--%>
<%--        <input type="hidden" name="type" value="4"/>--%>
<%--        <button type="submit" name="command" value="view_all_faculties">Sort by Budget Places</button><br>--%>
<%--    </form>--%>

    <c:forEach var="faculty" items="${requestScope.faculties}">
        <li><a style="color: darkcyan" href="view_faculty.jsp?id=${faculty.id}"><c:out value="${faculty.name}"/> </a></li>

    </c:forEach>


</form>

<c:if test="${sessionScope.user.role=='ADMIN'}">
    <h2><a href="add_faculty.jsp">Add Faculty</a></h2>
</c:if>

</div>
</body>
</html>
