<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Invoice</title>
</head>
<body>
<h2>Invoice</h2>

<p>Customer: ${sessionScope.customer.name}</p>
<p>Date: ${bill.billdate}</p>

<table border="1">
    <tr>
        <th>Item</th>
        <th>Price</th>
        <th>Quantity</th>
    </tr>
    <c:forEach var="tx" items="${bill.transactions}">
        <tr>
            <td>${tx.item.itemdesc}</td>
            <td>${tx.item.itemprice}</td>
            <td>${tx.qty}</td>
        </tr>
    </c:forEach>
</table>

<p>Total: ${bill.total}</p>

<p><a href="home">Back to Home</a></p>
</body>
</html>
