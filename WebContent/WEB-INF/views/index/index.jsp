<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="salary" scope="session" value="${2000*2}"/>
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
			<p>This system provide administrative<c:out value="${salary}"/></p>
		</div>
		<div class="row-fluid">
			<div class="span3 thumbnail">
				<h4>Membership Management</h4>
				<p>This function allow you manage memberships</p>
				<p>
					<a class="btn btn-info" href="membership.dis">View details »</a>
				</p>
			</div>
			<!--/span-->
			<div class="span3 thumbnail">
				<h4>Activity Management</h4>
				<p>This function allow you manage booking activity</p>
				<p>
					<a class="btn btn-info" href="sell.dis">View details »</a>
				</p>
			</div>
			<!--/span-->
			<div class="span3 thumbnail">
				<h4>Sale System</h4>
				<p>This function allow you manage selling food and beverage</p>
				<p>
					<a class="btn btn-info" href="sell.dis">View details »</a>
				</p>
			</div>
			<div class="span3 thumbnail">
				<h4>Local Report</h4>
				<p>This function provide local report/statistic about sale and activity</p>
				<p>
					<a class="btn btn-info" href="localReport.dis">View details »</a>
				</p>
			</div>
			<!--/span-->			
			
		</div>
		<!--/row-->
		<div class="row-fluid">
			<div class="span3 thumbnail">
				<h4>National Report</h4>
				<p>This function provide national report/statistic about sale and activity</p>
				<p>
					<a class="btn btn-info" href="nationalReport.dis">View details »</a>
				</p>
			</div>
			<!--/span-->
		</div>
	</div>
	<!-- Content end here -->
	<jsp:include page="../commons/footer.jsp"></jsp:include>
</body>
</html>