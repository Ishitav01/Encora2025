<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Cart</title>
</head>
<body>
<h2>Your Cart</h2>

<table border="1">
    <tr>
        <th>Product</th>
        <th>Price</th>
        <th>Qty</th>
        <th>Subtotal</th>
    </tr>
    <c:forEach var="item" items="${cartItems}">
        <tr>
            <td>${item.product.name}</td>
            <td>${item.product.price}</td>
            <td>${item.quantity}</td>
            <td>${item.product.price * item.quantity}</td>
        </tr>
    </c:forEach>
</table>

<p><b>Total: ${total}</b></p>

<a href="checkout">Proceed to Checkout</a> | <a href="home">Back to Home</a>
</body>
</html>
