<%--
  Created by IntelliJ IDEA.
  User: Компас
  Date: 8/25/2022
  Time: 2:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="/jsp/head.jsp" %>

<html>
<head>
    <title>Title</title>
</head>
<body>


<h2>${error}</h2>
<centre>
    <table>

        <form id="registry" action="/ControllerServlet" method="post">

            <input type="hidden" name="command" value="registry"/>

            <input id="email" type="email" placeholder="<fmt:message key="email"/>"  name="email" required value=${email}>
            <c:if test="${errorList.get(0)!=null}"><c:out value="${errorList.get(0)}"></c:out> </c:if><br>
            <input id="name" type="text" placeholder="Name"  name="name" required value=${name}>
            <c:if test="${errorList.get(1)!=null}"><c:out value="${errorList.get(1)}"></c:out> </c:if><br>
            <input id="surname" type="text" placeholder="Surname"  name="surname" required value=${surname}>
            <c:if test="${errorList.get(2)!=null}"><c:out value="${errorList.get(2)}"></c:out> </c:if><br>
            <input id="region" type="text" placeholder="Region"  name="region" required value=${region}>
            <c:if test="${errorList.get(3)!=null}"><c:out value="${errorList.get(3)}"></c:out> </c:if><br>
            <input id="city" type="text" placeholder="City"  name="city" required value=${city}>
            <c:if test="${errorList.get(4)!=null}"><c:out value="${errorList.get(4)}"></c:out> </c:if><br>
            <input id="eductionInstitution" type="text" placeholder="Eduction Institution"
                   name="eductionInstitution" required value=${eductionInstitution}>
            <c:if test="${errorList.get(5)!=null}"><c:out value="${errorList.get(5)}"></c:out> </c:if><br>
            <input id="password" type="password" placeholder="Password"  name="password" required value=${password}>
            <c:if test="${errorList.get(6)!=null}"><c:out value="${errorList.get(6)}"></c:out> </c:if><br>
            <input id="confirmPassword" type="password" placeholder="Confirm password"  required name="confirmPassword"><br>

            <input type="submit" value='Registry'>

        </form>
    </table>

</centre>
</body>
</html>
