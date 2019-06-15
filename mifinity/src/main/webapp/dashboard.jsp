<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
	<title>Welcome Page</title>
	<link href="common.css" rel="stylesheet">
</head>
<body>
	<div class="container">
		<c:if test="${pageContext.request.userPrincipal.name != null}">
			<form id="logoutForm" method="POST" action="/logout">
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
			</form>

			<div class="header">
				<h2>Welcome ${pageContext.request.userPrincipal.name}</h2>
			</div>
			<ul>
				<li><a href="#" onclick="document.forms['logoutForm'].submit()">Logout</a></li>
			</ul>
			<div class="cards-list">
				<table class="cards-table">
					<h2>List of Cards</h2>
					<thead>
						<tr>
							<th>Name</th>
							<th>Number</th>
							<th>MM/YYYY</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${cards}" var="card">
							<tr>
								<td>${card.name}</td>
								<td>${card.ccNumber}</td>
								<td>${card.expMonthYear}</td>

								<td><a type="button" class="btn btn-warning" href="/delete-card/${card.id}">Delete</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="add-card">
					<a class="button" href="/add-card">Add a Card</a>
				</div>
			</div>
		</c:if>
	</div>

</body>
</html>