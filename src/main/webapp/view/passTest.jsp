<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="messages"/>
<fmt:setLocale value="${language}" scope="session"/>
<html>
<head>
    <title><fmt:message key="locale.pass.test.title"></fmt:message> </title>
</head>
<body>
<c:set var="currentPage" value="path.page.pass.test" scope="session"/>
<c:import url="/view/header.jsp"></c:import>
<br/>
<h1>${test.getTestName()}</h1>
<br/>
<form name="PassTest" method="get" action="/webapp/controller">
    <input type="hidden" name="command" value="pass_test"/>
    <input type="hidden" name="testName" value="${test.getTestName()}">
    <input type="hidden" name="teacherName" value="${test.getTeacherName()}">

<c:forEach var="question" items="${test.getQuestions()}">
    <h3>${question.getQuestionText()}"</h3>
    <input type="hidden" name="questionText" value="${question.getQuestionText()}">
    <select name="selectedAnswer">
        <c:forEach var="answer" items="${question.getAllAnswers()}">
            <option type="text" value="${answer.getAnswerText()}" readonly>${answer.getAnswerText()}</option>
        </c:forEach>
    </select>
</c:forEach>
    <input type="submit" value="<fmt:message key="locale.pass.test.submit"></fmt:message> ">
</form>
</body>
</html>
