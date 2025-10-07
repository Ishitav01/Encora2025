<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>Home</title>
    <script>
        var sessionTimeout = 5 * 60 * 1000; // match server session timeout
        var timeoutHandle;

        function startSessionTimer() {
            timeoutHandle = setTimeout(function() {
                console.log("Session expired. Redirecting to login page.");
                window.location.href = "<c:url value='/' />?sessionExpired=true";
            }, sessionTimeout);
        }

        function resetSessionTimer() {
            clearTimeout(timeoutHandle);
            startSessionTimer();
        }

        window.onload = startSessionTimer;
        document.onmousemove = resetSessionTimer;
        document.onkeypress = resetSessionTimer;
        document.onclick = resetSessionTimer;
        document.onscroll = resetSessionTimer;
    </script>
</head>
<body>
    <h2>Welcome, ${username}</h2>
    <a href="<c:url value='/logout' />">Logout</a>

    <h3>Shops</h3>
    <ul>
        <li><a href="<c:url value='/shop1' />">Shop 1</a></li>
        <li><a href="<c:url value='/shop2' />">Shop 2</a></li>
        <li><a href="<c:url value='/shop3' />">Shop 3</a></li>
    </ul>

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

    <p><a href="<c:url value='/invoice' />">Go to Checkout / Invoice</a></p>
</body>
</html>
