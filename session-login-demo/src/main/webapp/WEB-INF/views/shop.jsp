<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Shop</title>
</head>
<body>
<h2>Shop Items</h2>

<form action="" method="post">
    <table border="1">
        <tr>
            <th>Select</th>
            <th>Item</th>
            <th>Price</th>
        </tr>
        <c:forEach var="item" items="${products}">
            <tr>
                <td>
                    <input type="checkbox" name="cartItems" value="${item.itemno}"/>
                </td>
                <td>${item.itemdesc}</td>
                <td>${item.itemprice}</td>
            </tr>
        </c:forEach>
    </table>
    <br/>
    <button type="submit" formaction="shop?shopid=invoice">Checkout</button>
</form>

<h3>Current Cart</h3>
<c:if test="${not empty sessionScope.cart}">
    <ul>
        <c:forEach var="item" items="${sessionScope.cart}">
            <li>${item.itemdesc} - ${item.itemprice}</li>
        </c:forEach>
    </ul>
</c:if>

<p><a href="home">Back to Home</a></p>
</body>
</html>
