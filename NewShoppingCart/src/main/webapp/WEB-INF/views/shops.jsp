<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Shops</title>
</head>
<body>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h2>Shops</h2>

<c:set var="currentShop" value="" />

<c:forEach var="product" items="${products}">
    <c:if test="${product.shop != currentShop}">
        <h3>${product.shop}</h3>
        <table border="1">
            <tr>
                <th>Name</th><th>Price</th><th>Quantity</th><th>Action</th>
            </tr>
        </table>
        <c:set var="currentShop" value="${product.shop}" />
    </c:if>
    <table border="1">
        <tr>
            <td>${product.name}</td>
            <td>${product.price}</td>
            <td>
                <form action="<c:url value='/addToCart' />" method="post">
                    <input type="hidden" name="productId" value="${product.id}">
                    <input type="number" name="quantity" value="1" min="1">
            </td>
            <td>
                    <button type="submit">Add to Cart</button>
                </form>
            </td>
        </tr>
    </table>
</c:forEach>

<p><a href="<c:url value='/invoice' />">View Cart / Invoice</a></p>


</body>
</html>
