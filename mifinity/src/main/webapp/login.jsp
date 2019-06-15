<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<title>Log in</title>
	<link href="<c:url value="common.css" />" rel="stylesheet">
</head>

<body>

	<div class="container">
		<form:form method="POST" action="/login" modelAttribute="loginForm">
			<h2 class="form-heading">Log in</h2>

			<div class="form-group ${error != null ? 'has-error' : ''}">
				<span>${message}</span> 
				<input name="username" type="text" class="form-control" placeholder="Username" /> 
				<input name="password" type="password" class="form-control" placeholder="Password" /> 
				<span>${error}</span> 
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

				<div class="form-controls">
					<button class="btn-primary" type="submit">Log In</button>
				</div>
				<h4 class="text-center">
					<a href="/registration">Create an account</a>
				</h4>
			</div>
		</form:form>
	</div>

</body>
</html>