<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://example.com/functions" prefix="f" %>
<html>
<head>
</head>
<title>Title</title>
<body>
<form method="post" action="${pageContext.request.contextPath}/meals/edit">
    <input type="hidden" name="id" value="${meal.id}">
    <input type="datetime-local" value="${meal.dateTime}" name="dateTime">
    <br>
    <input type="text" value="${meal.description}" name="description">
    <br>
    <input type="number" min="0" value="${meal.calories}" name="calories">
    <br>
    <input type="submit">Update
</form>
</body>
</html>
