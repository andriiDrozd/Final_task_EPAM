<%--
  Created by IntelliJ IDEA.
  User: Компас
  Date: 8/29/2022
  Time: 4:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>View Candidate</title>
</head>
<body>

        <h1>Application information</h1>
        <div style="margin-left: 30%">
            <p class="personalData">applicant_name: <c:out value="${requestScope.candidate.surname}"/> <c:out value="${requestScope.candidate.name}"/> </p>

            <p class="personalData">faculty: <c:out value="${requestScope.candidate.facultyName}"/>
            </p>
            <p class="personalData">subject
                <c:forEach items="${requestScope.candidate.subjects}" var="subject" varStatus="loop"><c:out value="${subject.name}"/> <font
                        color="#008b8b"><c:out value="${subject.mark}"/></font><c:if test="${!loop.last}">, </c:if></c:forEach>
            </p>
            <p class="personalData">Candidate state:<c:out value="${requestScope.candidate.candidateState}"/></p>

            </table>
            <c:if test="${sessionScope.user.role=='ADMIN'}">


            <form  action="/ControllerServlet" method="post">
                <input type="hidden" name="candidate_id" value="${requestScope.candidate.id}"/>
                <input type="hidden" name="candidateState" value="2"/>
                <button type="submit" name="command" value="change_candidate_state_to_enroll">Enroll</button><br>

            </form>
            <form  action="/ControllerServlet" method="post">
                <input type="hidden" name="candidate_id" value="${requestScope.candidate.id}"/>
                <input type="hidden" name="candidateState" value="3"/>
                <button type="submit" name="command" value="change_candidate_state_to_enroll">Not Enroll</button><br>
            </form>
            <form  action="/ControllerServlet" method="post">
                <button type="submit" name="command" value="view_all_candidates">Back to Candidates list</button><br>
            </form>
            </c:if>
        </div>
    </div>

</div>
</body>
</html>
