<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="messages"/>
<fmt:setLocale value="${language}" scope="session"/>
<html>
<head>
    <title>
        <fmt:message key="locale.create.test.title"></fmt:message>
    </title>
</head>
<body>
<c:set var="currentPage" value="path.page.create.test" scope="session"/>
<c:import url="/view/header.jsp"></c:import>
<br/>
<form name="CreateTest" method="post" action="/webapp/controller">
    <input type="hidden" name="command" value="create_test"/>
    <br/>
    <fmt:message key="locale.create.test.name"></fmt:message>
     <br/>
    <input name="testName" type="text">
    <br/>
    <fmt:message key="locale.create.test.question.quantity"></fmt:message>
    <br/>
    <input name="questionQuantity" type="number">
    <br/>
    <select name="language" hidden>
    <option value="RU">RU</option>
    <option value="EN" selected>EN</option>
    </select>
    <br/>
    <input type="submit" value="<fmt:message key="locale.create.test.create.button"></fmt:message>">
</form>
</body>
</html>
