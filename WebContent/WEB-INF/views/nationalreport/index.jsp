<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<jsp:include page="../commons/script.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="../commons/header.jsp"></jsp:include>
	<!-- Content start here -->
	<div>
		<div class="well well-small">
			<h3>NATION RACQUET AND HEALTH CLUB</h3>
			<p>Administrative panel</p>
		</div>
		<div class="row-fluid">
			<table class="table table-striped table-border">
				<tr>
					<th>#</th>
					<th>Code</th>
					<th>Name</th>
					<th>Adress</th>
					<th>Phone</th>
					<th></th>
				</tr>
			<c:forEach var="c" varStatus="i" items="${centers}">
				<tr>
					<td>${i.count}</td>
					<td>${c.code}</td>
					<td>${c.name}</td>
					<td>${c.address}</td>
					<td>${c.phone}</td>
					<td>
						<a class="btn btn-primary" href="http://${c.ip}:8080/dis/index.dis" target="_blank" ${c.code == center ? 'disabled' : ''}>Administrate</a>						
					</td>
				</tr>			
			</c:forEach>
			<c:if test="${fn:length(centers) == 0}">
				<tr><td colspan="5">There is no center</td></tr>
			</c:if>
			</table>
		</div>
		<!--/row-->
	</div>
	<!-- Content end here -->
	<jsp:include page="../commons/footer.jsp"></jsp:include>
</body>
</html>