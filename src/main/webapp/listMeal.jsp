<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://example.com/functions" prefix="f" %>
<html>
<head>
</head>
<title>Title</title>
<body>
<link href="../css/listMeal.css" rel="stylesheet" type="text/css">
<form method="post" action="${pageContext.request.contextPath}/meals/getAllMeals">
    <input type="number" name="calories" min="0" value="${calories}"/>
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
                <td><a href="${editUrl}">${meal.description}</a></td>
                <td>${meal.calories}</td>
                <td>${meal.exceed}</td>
            </tr>
        </c:forEach>
    </table>
    <input type="submit">Enter calories norm
</form>
</body>
</html>
