<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>Shop 3</title>
</head>
<body>
<h2>Shop 3</h2>
<p>Welcome, ${username} | <a href="<c:url value='/home' />">Home</a> | <a href="<c:url value='/logout' />">Logout</a></p>

<h3>Available Products</h3>
<table border="1">
    <tr>
        <th>Name</th>
        <th>Price</th>
        <th>Action</th>
    </tr>
    <c:forEach var="product" items="${products}">
        <tr>
            <td>${product.name}</td>
            <td>${product.price}</td>
            <td>
                <form action="<c:url value='/add-to-cart' />" method="post">
                    <input type="hidden" name="productId" value="${product.id}" />
                    <input type="number" name="quantity" value="1" min="1" />
                    <button type="submit">Add to Cart</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

<p>Items in Cart: 
    <c:choose>
        <c:when test="${sessionScope.cart != null}">
            ${fn:length(sessionScope.cart)}
        </c:when>
        <c:otherwise>0</c:otherwise>
    </c:choose>
</p>

<p><a href="<c:url value='/invoice' />">Go to Checkout</a></p>
</body>
</html>
