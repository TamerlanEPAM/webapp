<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="messages"/>
<fmt:setLocale value="${language}" scope="session"/>

<html>
<head>
    <title><fmt:message key="locale.mark.title"></fmt:message> </title>
</head>
<body>
<c:set var="currentPage" value="path.page.my.mark" scope="session"/>
<c:import url="/view/header.jsp"></c:import>
<br/>
<table style="border: black">
    <tr>
        <td><fmt:message key="locale.mark.test.name"></fmt:message> </td>
        <td><fmt:message key="locale.mark.teacher.name"></fmt:message> </td>
        <td><fmt:message key="locale.mark.mark"></fmt:message> </td>
        <td><fmt:message key="locale.mark.date"></fmt:message> </td>
    </tr>
    <tr>
        <td>${testName}</td>
        <td>${teacherName}</td>
        <td>${mark}</td>
        <td>${date}</td>
    </tr>
</table>
<a href="/titled1_war_exploded/view/mainStudent.jsp"><fmt:message key="locale.mark.main.page"></fmt:message> </a>
</body>
</html>
