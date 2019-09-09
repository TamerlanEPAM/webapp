<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="resources.messages"/>
<fmt:setLocale value="${language}" scope="session"/>
<html>
<head>
    <title> <fmt:message key="locale.main.teacher.title"></fmt:message> </title>
</head>
<body>
<c:set var="currentPage" value="path.page.main.teacher" scope="session"/>
<c:import url="/view/header.jsp"></c:import>
<br/>
<h3><fmt:message key="locale.main.teacher.welcome.teacher"></fmt:message> </h3>
<hr/>
${user.getUsername()}<fmt:message key="locale.main.teacher.hello"></fmt:message>
<hr/>
<br/>
<form name="LogoutForm" method="post" action="/webapp/controller">
    <input type="hidden" name="command" value="Logout"/>
    <table>
        <tr>
            <td><input type="submit" value="<fmt:message key="locale.main.teacher.log.out"></fmt:message>"/></td>
            <td> <a href="/webapp/view/createTest.jsp"><fmt:message key="locale.main.teacher.create.test"></fmt:message> </a></td>
        </tr>
    </table>
</form>
</body>
</html>
