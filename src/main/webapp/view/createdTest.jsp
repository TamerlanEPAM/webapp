<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="messages"/>
<fmt:setLocale value="${language}" scope="session"/>

<html>
<head>
    <title>
    <fmt:message key="locale.created.test.title"></fmt:message>
    </title>
</head>
<body>
<c:set var="currentPage" value="path.page.created.test" scope="session"/>
<c:import url="/view/header.jsp"></c:import>
<br/>
<h1><fmt:message key="locale.created.test.successfully.created"></fmt:message> </h1>
<form name="PublishTest" method="post" action="/titled1_war_exploded/controller">
    <h3>${test.getTestName()}</h3>
    <br/>
    <fmt:message key="locale.created.test.questions"></fmt:message>
    <br/>
    <c:forEach var="question" items="${test.getQuestions()}">
        <br/>
        <fmt:message key="locale.created.test.question"></fmt:message>
        <input name="questionText" type="text" readonly value="${question.getQuestionText()}">
        <br/>
        <fmt:message key="locale.created.test.answers"></fmt:message>
        <br/>
        <c:forEach var="answer" items="${question.getAnswers()}">
            <br/>
            <input name="answerText" type="text" readonly value="${answer.getAnswerText()}">
            <br/>
        </c:forEach>
        <input name="correctAnswerText" type="text" readonly value="${question.getCorrectAnswer().getAnswerText()}">
        <br/>
    </c:forEach>
    <br/>
    <a href="/titled1_war_exploded/view/mainTeacher.jsp"><fmt:message key="locale.created.test.href.main.page"></fmt:message> </a>
</form>
</body>
</html>
