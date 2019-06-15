<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
	<title>Add Credit Card Details</title>
	<link href="common.css" rel="stylesheet">
</head>

<body>

	<div class="header">
		<h2>Add Credit Card Details</h2>
	</div>
	<form id="logoutForm" method="POST" action="/logout">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
	<ul>
		<li><a href="/list-cards">Dashboard</a></li>
		<li><a href="#" onclick="document.forms['logoutForm'].submit()">Logout</a></li>
	</ul>

	<div class="container">
		<form:form method="POST" action="${contextPath}/add-card" modelAttribute="cardDetailsForm">

			<div class="form-group ${error != null ? 'has-error' : ''}">
				
				<label for="ccNumber">Number:</label> 
				<input type="text" id="ccNumber" name="ccNumber" size="21" placeholder="XXXX-XXXX-XXXX-XXXX" /> 
				<form:errors path="ccNumber"></form:errors>
				<br>
				<label for="name">Name:</label> 
				<input type="text" id="name" name="name" size="20" /> 
				<form:errors path="name"></form:errors>
				<br>
				<label for="expMonthYear">Expiry date (MM/YYYY):</label> 
				<input type="text" id="expMonthYear" name="expMonthYear" size="7" placeholder="MM/YYYY" /> 
				<form:errors path="expMonthYear"></form:errors>
				<br>
				<span>${error}</span>
				<div class="form-controls">
					<button class="btn-primary" type="submit">Submit</button>
				</div>
			</div>

		</form:form>
	</div>
</body>
</html>

