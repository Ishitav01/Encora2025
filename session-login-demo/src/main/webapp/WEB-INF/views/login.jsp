<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Login</title>
</head>
<body>
<h2>Login</h2>

<c:if test="${not empty error}">
    <p style="color:red">${error}</p>
</c:if>

<form action="login" method="post">
    <label>Username:</label>
    <input type="text" name="username" required/><br/>
    <label>Password:</label>
    <input type="password" name="password" required/><br/>
    <button type="submit">Login</button>
</form>
</body>
</html>
