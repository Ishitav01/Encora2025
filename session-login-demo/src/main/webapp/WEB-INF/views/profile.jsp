<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Profile</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
</head>
<body>
<div class="container">
    <h2>Welcome, <%= session.getAttribute("username") %> </h2>
    <h1>Profile Page</h1>
    <a class="button" href="profile">Profile</a>
    <a class="button" href="dashboard">Dashboard</a>
    <a class="button" href="logout">Logout</a>
</div>
</body>
</html>
