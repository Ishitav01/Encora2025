<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Dashboards</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
</head>
<body>
<div class="container">
<h1>Dashboard Page</h1>
    <h2>Welcome, <%= session.getAttribute("username") %> 👋</h2>
    <a class="button" href="profile">Profile</a>
    <a class="button" href="dashboard">Dashboard</a>
    <a class="button" href="logout">Logout</a>
</div>
</body>
</html>
