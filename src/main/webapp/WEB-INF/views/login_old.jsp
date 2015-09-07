<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>


<head>
	<!--  <meta name="_csrf" content="${_csrf.token}"/>-->
	<!-- default header name is X-CSRF-TOKEN -->
	<!-- <meta name="_csrf_header" content="${_csrf.headerName}"/> -->
	<!-- ... -->
</head>
<html>
	<body>
		<h1 id="banner">Welcome secure web</h1>
		<div id="login-box">

			<h3>Login with Username and Password</h3>

			<c:if test="${not empty error}">
				<div class="error">${error}</div>
			</c:if>
			<c:if test="${not empty msg}">
				<div class="msg">${msg}</div>
			</c:if>

			<form name='loginForm' action="<c:url value='/login' />" method='POST'>
				<input type="hidden" name="${_csrf.parameterName}"   value="${_csrf.token}"/>
				<div>
					<label for="username">Username</label>
					<input type="text" name="username" id="username" required >
				</div>
				<div>
					<label for="password">Password</label>
					<input type="password" name="password" id="password" required>
				</div>
				<button type="submit">Sign in</button>
			</form>
		</div>
	</body>
</html>