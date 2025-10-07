<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Session Expired</title>
</head>
<body>
	<div class="container">
		<h2>Session Expired</h2>
		<p>
			Your session has expired. Please <a
				href="${pageContext.request.contextPath}/">login again</a>.
		</p>
	</div>
</body>
</html>
