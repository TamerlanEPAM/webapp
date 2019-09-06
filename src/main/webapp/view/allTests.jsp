<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="messages"/>
<fmt:setLocale value="${language}" scope="session"/>

<html>
<head>

    <title>
        <fmt:message key="locale.all.tests.title"/>
    </title>
</head>
<body>
<c:set var="currentPage" value="path.page.all.tests" scope="session"/>
<c:import url="/view/header.jsp"></c:import>
<br/>
<c:forEach var="test"  items="${tests}">
    <br/>
    Test:
    <form name="take" method="get" action="/webapp/controller">
    <table>
        <tr>
            <td><fmt:message key="locale.all.tests.test.name"></fmt:message> </td>
            <td><fmt:message key="locale.all.tests.teacher.name"></fmt:message> </td>
        </tr>
        <tr>
            <td><input name="testName" type="text" readonly value="<c:out value="${test.getTestName()}"></c:out>" style="border:none"></td>
            <td><input name="teacherName" type="text" readonly value="<c:out value="${test.getTeacherName()}"></c:out>" style="border:none"></td>
            <td><input name="questionQuantity" type="hidden" value="<c:out value="${test.getQuestionQuantity()}"></c:out>" style="border:none"></td>
            <td><input name="testId" type="hidden" value="<c:out value="${test.getId()}"></c:out>" style="border:none"></td>
            <td><input name="language" type="hidden" value="<c:out value="${test.getLanguage()}"></c:out>" style="border:none"></td>
            <td><input name="isTestPublished" type="hidden" value="<c:out value="${test.isTestPublished()}"></c:out>" style="border:none"></td>
        </tr>
        <tr>
            <td>
                <input name="take" type="submit" value="take">
                <input name="command" type="hidden" value="take">
            </td>
        </tr>
    </table>
    </form>
</c:forEach>
</body>
</html>