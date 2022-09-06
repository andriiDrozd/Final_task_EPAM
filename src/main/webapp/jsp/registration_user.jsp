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

<c:if test="${local == null}">
    <fmt:setLocale value="en"/>
</c:if>
<c:if test="${local != null}">
    <fmt:setLocale value="${local}"/>
</c:if>
<fmt:setBundle basename="localization" var="lang"/>
<%--<fmt:bundle basename="page">--%>
<%--<html lang="${param.lang}">--%>
<%--<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>--%>
<head>
    <title>Title</title>
</head>
<body>
<center>
<h2>${error}</h2>

<%--    <a href="?lang=en_US">English</a>--%>
<%--    <a href="?lang=uk_UA">Ukraine</a>--%>
    <table>

        <form id="registry" action="/ControllerServlet" method="post">

            <input type="hidden" name="command" value="registry"/>

            <input id="email" type="email" placeholder="<fmt:message key="Email" bundle="${lang}"/>"  name="email" required value=${email}>
            <c:if test="${errorList.get(0)!=null}"><c:out value="${errorList.get(0)}"></c:out> </c:if><br>
            <input id="name" type="text" placeholder="<fmt:message key="name" bundle="${lang}"/>"  name="name" required value=${name}>
            <c:if test="${errorList.get(1)!=null}"><c:out value="${errorList.get(1)}"></c:out> </c:if><br>
            <input id="surname" type="text" placeholder="<fmt:message key="surname" bundle="${lang}"/>"  name="surname" required value=${surname}>
            <c:if test="${errorList.get(2)!=null}"><c:out value="${errorList.get(2)}"></c:out> </c:if><br>
            <input id="region" type="text" placeholder="<fmt:message key="region" bundle="${lang}"/>"  name="region" required value=${region}>
            <c:if test="${errorList.get(3)!=null}"><c:out value="${errorList.get(3)}"></c:out> </c:if><br>
            <input id="city" type="text" placeholder="<fmt:message key="city" bundle="${lang}"/>"  name="city" required value=${city}>
            <c:if test="${errorList.get(4)!=null}"><c:out value="${errorList.get(4)}"></c:out> </c:if><br>
            <input id="eductionInstitution" type="text" placeholder="<fmt:message key="eductionInstitution" bundle="${lang}"/>"
                   name="eductionInstitution" required value=${eductionInstitution}>
            <c:if test="${errorList.get(5)!=null}"><c:out value="${errorList.get(5)}"></c:out> </c:if><br>
            <input id="password" type="password" placeholder="<fmt:message key="password" bundle="${lang}" />"  name="password" required value=${password}>
            <c:if test="${errorList.get(6)!=null}"><c:out value="${errorList.get(6)}"></c:out> </c:if><br>
            <input id="confirmPassword" type="password" placeholder="<fmt:message key="confirmPassword" bundle="${lang}"/>"  required name="confirmPassword"><br>
<%--            <button type="submit" name="command" value="registry">Login</button><br>--%>
            <input type="submit" value="<fmt:message key="registry" bundle="${lang}"/>">

        </form>
    </table>
<%--    </fmt:bundle>--%>
    <%@include file="/jsp/footer.jsp" %>

</centre>
</body>
</html>
