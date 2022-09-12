<%@ page import="com.example.final_task_epam.model.entity.Candidate" %>
<%@ page import="java.util.Set" %>
<%@ page import="com.example.final_task_epam.util.Parameter" %><%--
  Created by IntelliJ IDEA.
  User: Компас
  Date: 8/29/2022
  Time: 12:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


${error}
<form  action="/ControllerServlet" method="post">

        ${requestScope.error}
    <table id="myTable" class="display" border="1" cellpadding="20%">
        <thead>
        <tr>
            <th>No</th>
            <th>Candidate</th>
            <th>Faculty</th>
            <th>Total rating</th>
            <th>Status</th>
            <th>Faculty State</th>
        </tr>
        </thead>
        <tbody>
<%int i =1;%>
${error}
        <c:forEach items="${requestScope.candidates}" var="candidate">
            <tr>
                <td><%=i++%></td>
                <td>
                    <a href="${pageContext.request.contextPath}/ControllerServlet?command=view_candidate&id=${candidate.id}"
                       style="color: black"><c:out value="${candidate.name}  "/><c:out value="${candidate.surname}"/> </a>
                </td>
                <td><c:out value="${candidate.facultyName}"/></td>
                <td><c:out value="${candidate.totalRating}"/></td>
                <td><c:out value="${candidate.candidateState}"/></td>
                <td><c:out value="${candidate.facultyState}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
        ${requestScope.error}
   <h2> ${requestScope.faculty_state_error}</h2>
    <c:if test="${sessionScope.user.role=='ADMIN'}">
    <h2><a href="jsp/admin_menu.jsp">Back to Main Menu</a></h2>
    </c:if>
        ${requestScope.error}
</body>
</html>
