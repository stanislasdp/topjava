<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://example.com/functions" prefix="f" %>
<html>
<head>
</head>
<title>Title</title>
<body>
<h2>${param.action == 'create' ? 'Create meal' : 'Edit meal'}</h2>
<jsp:useBean id="meal" type="ru.javawebinar.topjava.dto.MealWithExceed" scope="request"/>
<form method="post" action="${meal.id == null ? "meals/add" : "meals/edit"}">
    <input type="hidden" name="id" value="${meal.id}">
    <input type="datetime-local" value="${meal.dateTime}" name="dateTime" required>
    <br>
    <input type="text" value="${meal.description}" name="description" required>
    <br>
    <input type="number" min="0" value="${meal.calories}" name="calories" required>
    <br>
    <button type="submit">Save</button>
</form>
</body>
</html>
