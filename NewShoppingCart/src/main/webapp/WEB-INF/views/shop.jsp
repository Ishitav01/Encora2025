<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>${shopName}</title>
</head>
<body>
    <h2>Welcome to ${shopName}</h2>
    <table border="1" cellpadding="5" cellspacing="0">
        <tr>
            <th>Name</th>
            <th>Price</th>
            <th>Add to Cart</th>
        </tr>
        <c:forEach var="p" items="${products}">
            <tr>
                <td>${p.name}</td>
                <td>${p.price}</td>
                <td>
                    <form action="<c:url value='/cart/add' />" method="post">
                        <input type="hidden" name="productId" value="${p.id}" />
                        <input type="number" name="quantity" value="1" min="1" />
                        <button type="submit">Add to Cart</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table><br>
    
    <h3>Cart</h3>
    <p>
        Items in cart: 
        <c:set var="cart" value="${sessionScope.cart}" />
        <c:choose>
            <c:when test="${cart != null}">
                ${fn:length(cart)}
            </c:when>
            <c:otherwise>0</c:otherwise>
        </c:choose>
    </p>
    <br>
   <p><a href="<c:url value='/invoice'/>">Go to Checkout / Complete Purchase</a></p>

    <p><a href="<c:url value='/home' />">Back to Home</a></p><br>
 
</body>
</html>
