<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="messages"/>
<fmt:setLocale value="${language}" scope="session"/>

<html>
<head>

    <title><fmt:message key="locale.main.student.title"></fmt:message> </title>
</head>
<body>
<c:set var="currentPage" value="path.page.main.student" scope="session"/>

        <c:import url="/web/view/header.jsp"></c:import>
        <br/>
        <fmt:message key="locale.main.student.hello" />

               <c:out value="${registered}"></c:out>
               <c:out value="${updated}"></c:out>
              <c:out value="${user.getUsername()}"></c:out>
        <hr/>
        <form name="LogoutForm" method="post" action="/webapp/controller">
                <input type="hidden" name="command" value="Logout"/>
                <input type="submit" value="<fmt:message key="locale.main.student.exit"></fmt:message> "/>
        </form>
        <form name="GetTests" method="get" action="/webapp/controller">
                <input type="hidden" name="command" value="get_tests"/>
                <input type="submit" value="<fmt:message key="locale.main.student.get.tests"></fmt:message> "/>
        </form>
        <form name="DeleteProfile" method="get" action="/webapp/controller">
                <input type="hidden" name="command" value="delete_profile"/>
                <input type="submit" value="<fmt:message key="locale.main.student.delete.profile"></fmt:message>"/>
        </form>


        <a href = "/webapp/view/updateProfile.jsp"><fmt:message key="locale.main.student.update.profile"></fmt:message> </a>


</body>
</html>