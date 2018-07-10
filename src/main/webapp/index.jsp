<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Java Enterprise (Topjava)</title>
</head>
<body>
<h3>Project <a href="https://github.com/JavaWebinar/topjava" target="_blank">Java Enterprise (Topjava)</a></h3>
<hr>
<form method="get" action="${pageContext.request.contextPath}/users/">
    <label for="userSelect"></label>
    <select name="userSelect" id="userSelect">
        <option value="1">Admin</option>
        <option value="2">Not admin</option>
    </select>
    <button type="submit">Meals</button>
</form>
</body>
</html>
