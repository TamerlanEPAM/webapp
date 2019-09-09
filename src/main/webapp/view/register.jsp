<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="resources.messages"/>
<fmt:setLocale value="${language}" scope="session"/>
<html>
<head>
    <title><fmt:message key="locale.register.title"></fmt:message> </title>
</head>
<body>
<c:set var="currentPage" value="path.page.register" scope="session"/>
<c:import url="/view/header.jsp"></c:import>
<br/>
<form name="RegistrationForm" method="post" action="controller">
    <input type="hidden" name="command" value="registration"/>
    <fmt:message key="locale.register.login"></fmt:message>
    <br/>
    <input type="text" name="login" value="" />
    <br/>
    <fmt:message key="locale.register.password"></fmt:message>
    <br/>
    <input type="password" name="password" value=""/>
    <br/>
    <fmt:message key="locale.register.password.repeat"></fmt:message>
    <br/>
    <input type="password" name="passwordRepeat" value=""/>
    <br/>
    <fmt:message key="locale.register.choose.role"></fmt:message>
    <br/>
    <select name="role">
        <option value="student"><fmt:message key="locale.register.student"></fmt:message> </option>
        <option value="teacher"><fmt:message key="locale.register.teacher"></fmt:message> </option>
    </select>
    ${errorLoginPassMessage}
    <br/>
    ${wrongAction}
    <br/>
    ${nullPage}
    <br/>
    <input type="submit" value="<fmt:message key="locale.register.register"></fmt:message>"/>
    <br/>
</form><hr/>
</body>
</html>
