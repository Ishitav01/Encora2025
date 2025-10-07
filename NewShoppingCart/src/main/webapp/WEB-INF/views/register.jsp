<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
    <h2>Register</h2>
    <c:if test="${not empty error}">
        <p style="color:red">${error}</p>
    </c:if>
    <form action="register" method="post">
        <label>Username:</label>
        <input type="text" name="username" required>
        <br>
        <label>Password:</label>
        <input type="password" name="password" required>
        <br>
        <button type="submit">Register</button>
    </form>
    <p>Already have an account? <a href="<c:url value='/' />">Login</a></p>
</body>
</html>
