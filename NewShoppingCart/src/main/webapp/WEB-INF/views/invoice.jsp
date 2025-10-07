<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Invoice</title>
</head>
<body>
<h2>Invoice</h2>

<c:if test="${not empty error}">
    <p style="color:red">${error}</p>
</c:if>

<table border="1">
    <tr>
        <th>Product</th>
        <th>Shop</th>
        <th>Price</th>
        <th>Quantity</th>
        <th>Total</th>
    </tr>
    <c:forEach var="item" items="${items}">
        <tr>
            <td>${item.name}</td>
            <td>${item.shop}</td>
            <td>${item.price}</td>
            <td>${item.quantity}</td>
            <td>${item.total}</td>
        </tr>
    </c:forEach>
</table>

<p><b>Grand Total:</b> ${total}</p>

<form action="${pageContext.request.contextPath}/complete-purchase" method="post">
    <button type="submit">Complete Purchase</button>
</form>

<p><a href="${pageContext.request.contextPath}/home">Back to Home</a></p>
</body>
</html>
