<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
<h2>Welcome, ${sessionScope.customer.name}</h2>

<p><a href="shop1">Go to Shop 1</a></p>
<p><a href="shop2">Go to Shop 2</a></p>
<p><a href="shop3">Go to Shop 3</a></p>

<p><a href="logout">Logout</a></p>
</body>
</html>
