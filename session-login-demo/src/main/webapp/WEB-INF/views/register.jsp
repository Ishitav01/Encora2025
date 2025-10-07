<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Register</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css" />
</head>
<body>
	<div class="container">
		<h2>Register</h2>
		<c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>
		<form action="${pageContext.request.contextPath}/register"
			method="post">
			<input type="text" name="username" placeholder="Username" required /><br />
			<input type="password" name="password" placeholder="Password"
				required /><br /> <input type="submit" value="Register" />
		</form>
	</div>
</body>
</html>
