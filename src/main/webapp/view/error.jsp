<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="messages"/>
<fmt:setLocale value="${language}" scope="session"/>
<html>
<head>
    <title><fmt:message key="locale.error.title"></fmt:message> </title>
</head>
<body>
 <fmt:message key="locale.error.request.form"></fmt:message> ${pageContext.errorData.requestURI} <fmt:message key="locale.error.is.failed"></fmt:message>
<br/>
<fmt:message key="locale.error.servlet.name"></fmt:message> ${pageContext.errorData.servletName}
<br/>
<fmt:message key="locale.error.status.code"></fmt:message> ${pageContext.errorData.statusCode}
<br/>
<fmt:message key="locale.error.exception"></fmt:message> ${pageContext.errorData.throwable}
</body>
</html>
