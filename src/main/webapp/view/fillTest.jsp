<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="resources.messages"/>
<fmt:setLocale value="${language}" scope="session"/>
<html>
<head>
    <title>
        <fmt:message key="locale.fill.test.title"></fmt:message>
    </title>
</head>
<body>
<c:set var="currentPage" value="path.page.fill.test" scope="session"/>
<c:import url="/view/header.jsp"></c:import>
<br/>
<h3>
 <form name="PublishTest" method="post" action="/webapp/controller">
     <input type="hidden" name="command" value="publish_test">
     <h3>${test.getTestName()}</h3>
     <br/>
     <fmt:message key="locale.fill.test.test.questions"></fmt:message>
     <br/>
        <c:forEach var="question" items="${test.getQuestions()}">
                <br/>
                <fmt:message key="locale.fill.test.question"></fmt:message>
                <input name="questionText" type="text" required>
                <br/>
                <fmt:message key="locale.fill.test.answers"></fmt:message>
                <br/>
                <c:forEach var="answer" items="${question.getAnswers()}">
                    <br/>
                    <fmt:message key="locale.fill.test.wrong.answer"></fmt:message> <input name="answerText" type="text" required>
                    <br/>
                </c:forEach>
             <input name="correctAnswerText" type="text" required>
            <br/>
        </c:forEach>
     <br/>
     <input name="<fmt:message key="locale.fill.test.save"></fmt:message>" type="submit">
 </form>
</h3>
</body>
</html>
