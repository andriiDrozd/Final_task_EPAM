<%--
  Created by IntelliJ IDEA.
  User: Компас
  Date: 9/21/2022
  Time: 1:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

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

<%int i =1;%>
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

        <%
    int count = (Integer) request.getAttribute("count");
    int limit = (Integer) request.getAttribute("limit");
    for (int k = 0; k<=(count/limit); k++) {

%>

    <h2><a href="/ControllerServlet?command=pagination&start=<%=k%>">page <%=k+1%></a></h2>

        <%
    }
%>
</body>
</html>
