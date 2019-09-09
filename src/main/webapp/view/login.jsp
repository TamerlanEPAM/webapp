<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="messages"/>
<fmt:setLocale value="${language}" scope="session"/>
<html>
<head>
    <title><fmt:message key="locale.login.title"></fmt:message> </title>
</head>
<body>
<c:set var="currentPage" value="path.page.login" scope="session"/>
<c:import url="webapp/view/header.jsp"></c:import>
<br/>
<form name="LoginForm" method="post" action="/webapp/controller">
    <input type="hidden" name="command" value="Login"/>
    <fmt:message key="locale.login.login"></fmt:message>
    <br/>
    <input type="text" name="login" value=""/>
    <br/>
    <fmt:message key="locale.login.password"></fmt:message>
    <br/>
    <input type="password" name="password" value=""/>
    <br/>
    ${errorLoginPassMessage}
    <br/>
    ${wrongAction}
    <br/>
    ${nullPage}
    <br/>
    <input type="submit" value="<fmt:message key="locale.login.to.login"></fmt:message>"/>
<br/>
<br/>
<br/>
    <a href="/webapp/view/register.jsp"><fmt:message key="locale.login.registration"></fmt:message> </a>
</form><hr/>
</body>
</html>