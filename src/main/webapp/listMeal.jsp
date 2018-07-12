<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://example.com/functions" prefix="f" %>
<html>
<head>
</head>
<title>Title</title>
<body>
<link href="${pageContext.request.contextPath}/css/listMeal.css" rel="stylesheet" type="text/css">
<form method="get" action="${pageContext.request.contextPath}/meals/getAllMeals">
    <input type="date" name="startDate" value="${startDate}" id="startDate">
    <label for="startDate">Start date</label>
    <br>
    <input type="date" name="endDate" value="${endDate}" id="endDate">
    <label for="endDate">End Date</label>
    <br>
    <input type="time" name="startTime" value="${startTime}" id="startTime">
    <label for="startTime">Start time</label>
    <br>
    <input type="time" name="endTime" value="${endTime}" id="endTime">
    <label for="startTime">End time</label>
    <br>

    <a href="${pageContext.request.contextPath}/meals/add">Add meal</a>
    <table>
        <tr>
            <th>Date/Time</th>
            <th>Description</th>
            <th>Calories</th>
            <th>exceed</th>
        </tr>
        <c:forEach items="${meals}" var="meal">
            <tr class="${meal.exceed ? 'matches' : 'notMatches'}">
                <td>${f:formatLocalDateTime(meal.dateTime)}</td>
                <c:url var="editUrl" value="/meals/edit">
                    <c:param name="mealId" value="${meal.id}"/>
                </c:url>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td>${meal.exceed}</td>
                <td><a href="${pageContext.request.contextPath}/meals/update?id=${meal.id}">Update</a></td>
                <td><a href="${pageContext.request.contextPath}/meals/delete?id=${meal.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
    <input type="submit">Enter calories norm
</form>
</body>
</html>
