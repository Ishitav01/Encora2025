<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="nav">
	<a href="${pageContext.request.contextPath}/home">Home</a> | <a
		href="${pageContext.request.contextPath}/shops">Shops</a> | <a
		href="${pageContext.request.contextPath}/cart">Cart</a> |
	<c:choose>
		<c:when test="${not empty sessionScope.username}">
			<a href="${pageContext.request.contextPath}/logout">Logout</a>
			<span> | Welcome ${sessionScope.username}</span>
		</c:when>
		<c:otherwise>
			<a href="${pageContext.request.contextPath}/">Login</a> |
<a href="${pageContext.request.contextPath}/register">Register</a>
		</c:otherwise>
	</c:choose>
</div>
<hr />
