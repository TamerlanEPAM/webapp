<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle basename="resources.messages"/>
<%--<fmt:setLocale value="en_US" scope="session"/>--%>
<html>
<body>


<form name="changeLanguage" method="post" action="controller">
<input type="hidden" name="command" value="change_language">
<input type="hidden" name="page" value="${currentPage}">
    <input type="radio" id="english" value="en_US" name="language" checked>
    <label for="english">english</label>
    <input type="radio" id="française" value="fr_FR" name="language">
    <label for="française">française</label>
    <input type="submit" value="change language">
</form>

</body>
</html>
