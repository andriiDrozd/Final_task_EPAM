<%--
  Created by IntelliJ IDEA.
  User: Компас
  Date: 8/25/2022
  Time: 2:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

            <input id="email" type="email" placeholder="E-mail" required name="email" value=${email}><br>

            <input id="name" type="text" placeholder="Name" required name="name" value=${name}><br>

            <input id="surname" type="text" placeholder="Surname" required name="surname" value=${surname}><br>

            <input id="region" type="text" placeholder="Region" required name="region" value=${region}><br>

            <input id="city" type="text" placeholder="City" required name="city" value=${city}><br>

            <input id="eductionInstitution" type="text" placeholder="Eduction Institution" required
                   name="eductionInstitution" value=${eductionInstitution}><br>

            <input id="password" type="password" placeholder="Password" required name="password" value=${password}><br>

            <input id="confirmPassword" type="password" placeholder="Confirm password" name="confirmPassword"><br>

            <input type="submit" value='Registry'>

        </form>
    </table>

</centre>
</body>
</html>
