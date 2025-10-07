<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Shops</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/
style.css" />
</head>
<body>
	<jsp:include page="fragments/header.jsp" />
	<div class="container">
		<h2>Shops</h2>
		<ul>
			<c:forEach var="s" items="${shops}">
				<li><a href="${pageContext.request.contextPath}/shop/${s}">${s}</a></li>
			</c:forEach>
		</ul>
	</div>
</body>
</html>

