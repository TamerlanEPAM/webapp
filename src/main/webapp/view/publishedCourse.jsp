<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="messages"/>
<fmt:setLocale value="${language}" scope="session"/>
<html>
<head>
    <title><fmt:message key="locale.published.tests.title"></fmt:message> </title>
</head>
<body>
<c:set var="currentPage" value="path.page.published.course" scope="session"/>
<c:import url="/view/header.jsp"></c:import>
<br/>
<form name="PublishedLectures">
    <input type="hidden" name="course_name" value="${course.getCourseName()}">
    <h3>${course.getCourseName()}</h3>
    <br/>
    <fmt:message key="locale.published.tests.text"></fmt:message>
    <br/>
    <c:forEach var="lecture" items="${course.getLectures()}">
        <form name="lecture">
            <br/>
            <input name="text" type="text" value="${lecture.getText()}" readonly>
            <br/>
            <c:forEach var="question" items="${lecture.getQuestions()}">
                <br/>
                <input name="isTrue" type="radio" value="${question.isTrue()}" readonly><input name="question" type="text" value="${question.setQuestion()}" readonly>
                <br/>
            </c:forEach>
            <br/>
        </form>
    </c:forEach>
    <br/>
    <a href="/webapp/view/mainTeacher.jsp"><fmt:message key="locale.published.tests.main.page"></fmt:message> </a>
</form>
</body>
</html>
