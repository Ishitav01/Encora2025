<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Cart</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/
style.css" />
</head>
<body>
	<jsp:include page="fragments/header.jsp" />
	<div class="container">
		<h2>Your Cart</h2>
		<c:if test="${empty items}">
			<p>Your cart is empty.</p>
		</c:if>
		<c:if test="${not empty items}">
			<table>
				<tr>
					<th>Product</th>
					<th>Price</th>
					<th>Qty</th>
					<th>Subtotal</th>
					<th>Action</th>
				</tr>
				<c:forEach var="entry" items="${items}">
					<c:set var="product" value="${entry.key}" />
					<c:set var="quantity" value="${entry.value}" />
					<tr>
						<td>${product.name}</td>
						<td>${product.price}</td>
						<td>${quantity}</td>
						<td>${product.price * quantity}</td>
						<td>
							<form action="${pageContext.request.contextPath}/cart/remove"
								method="post">
								<input type="hidden" name="productId" value="${product.id}" />
								<button type="submit">Remove</button>
							</form>
						</td>
					</tr>
				</c:forEach>
			</table>
			<h3>Total: ${total}</h3>
			<form action="${pageContext.request.contextPath}/checkout"
				method="post">
				<button type="submit" class="button">Checkout</button>
			</form>
		</c:if>
	</div>
</body>
</html>

