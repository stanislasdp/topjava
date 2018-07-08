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
<select name="userSelect">
    <option value="Home"><a href="home.php">Home</a></option>
    <option value="Contact"><a href="contact.php">Contact</a></option>
    <option value="Sitemap"><a href="sitemap.php">Sitemap</a></option>
</select>
<ul>
    <li><a href="users">Users</a></li>
    <li><a href = "<c:url value = "/meals/getAllMeals"/>">Meals</a></li>
</ul>
</body>
</html>
