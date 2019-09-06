<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="messages"/>
<fmt:setLocale value="${language}" scope="session"/>
<html>
<head>
    <title><fmt:message key="locale.update.profile.title"></fmt:message> </title>
</head>
<body>
<c:set var="currentPage" value="path.page.update.profile" scope="session"/>
<c:import url="/view/header.jsp"></c:import>
<br/>
<h3><fmt:message key="locale.update.profile.update.profile"></fmt:message> </h3>
<hr/>
${registered}
<hr/>
${user.getUsername()}<fmt:message key="locale.update.profile.hello"></fmt:message>
<hr/>
<form name="UpdateProfile" method="post" action="/webapp/controller">
    <input type="hidden" name="command" value="update_profile">
    <fmt:message key="locale.update.profile.login"></fmt:message>
    <br/>
    <input type="text" name="login" value="${user.getUsername()}" readonly />
    <br/>
    <fmt:message key="locale.update.profile.old.password"></fmt:message>
    <br/>
    <input type="password" name="oldPassword" value=""/>
    <br/>
    <fmt:message key="locale.update.profile.new.password"></fmt:message>
    <br/>
    <input type="password" name="newPassword" value=""/>
    <br/>
    <fmt:message key="locale.update.profile.new.password.repeat"></fmt:message>
    <br/>
    <input type="password" name="repeatNewPassword" value=""/>
    <input type="submit" name="<fmt:message key="locale.update.profile.post.changes"></fmt:message>">
</form>
</body>
</html>