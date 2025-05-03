<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<jsp:include page="../commons/script.jsp"></jsp:include>
	<style>
		.filter td{
			padding: 2px 10px;
		}
		
		.filter select, .table input{
			margin: 0;
		}
	</style>
</head>
<body>
	<jsp:include page="../commons/header.jsp"></jsp:include>
	<!-- Content start here -->
	<div>
		<div class="well well-small">
			<h3>NATIONAL REPORT</h3>
		</div>
		<div class="row-fluid">
		<form action="${context}" method="GET">
			<input type="hidden" name="action" value="report" />
			<table class="table filter">
					<tbody>
						<tr>
							<td>Type of report</td>
							<td>
								<select name="type">
									<option value="activity">Activity</option>
									<option value="sale">Sale</option>
								</select>
							</td>
							<td></td>
						</tr>
						<tr>
							<td>Year</td>
							<td>
								<select name="year">
								<c:forEach begin="2013" end="2030" var="y">
									<c:if test="${y == year}">
										<option value="${y}" selected="selected">${y}</option>								
									</c:if>
									<c:if test="${y != year}">
										<option value="${y}">${y}</option>
									</c:if>
								</c:forEach>
								</select>
							</td>
							<td></td>
						</tr>
						<tr>
							<td>Month</td>
							<td>
								<select name="month">
								<c:forEach begin="0" end="12" var="m">
									<c:if test="${m == month}">
										<option value="${m}" selected="selected">${m}</option>								
									</c:if>
									<c:if test="${m != month}">
										<option value="${m}">${m}</option>
									</c:if>
								</c:forEach>
								</select>
							</td>
							<td></td>
						</tr>
						<tr>
							<td>Week</td>
							<td>
								<select name="week">
								<c:forEach begin="0" end="4" var="w">
									<c:if test="${w == week}">
										<option value="${w}" selected="selected">${w}</option>								
									</c:if>
									<c:if test="${w != week}">
										<option value="${w}">${w}</option>
									</c:if>
								</c:forEach>
								</select>
							</td>
							<td></td>
						</tr>
						<tr>
							<td></td>
							<td>
								<input class="btn" onclick="javascript: window.location = '${contextPath}/localReport.dis';" type="button" value="Back"/>
								<input class="btn" type="submit" value="Report"/>
							</td>
							<td></td>
						</tr>
					</tbody>
				</table>
			</form>
			<table class="table table-striped table-border">
				<tr>
					<th>#</th>
					<th>Member Code</th>
					<th>Member Name</th>
					<th>Activity</th>
					<th>Quantity</th>
					<th>Price</th>
					<th>Total</th>
				</tr>
			<c:forEach var="m" varStatus="i" items="${reports}">
				<tr>
					<td>${i.count}</td>
					<td>${m.mcode}</td>
					<td>${m.mname}</td>
					<td>${m.a}</td>
					<td>${m.p}</td>
					<td>${m.q}</td>
					<td>${m.total}</td>
				</tr>			
			</c:forEach>
			<c:if test="${fn:length(reports) == 0}">
				<tr><td colspan="5">There is no report</td></tr>
			</c:if>
			</table>
		</div>
		<!--/row-->
	</div>
	<!-- Content end here -->
	<jsp:include page="../commons/footer.jsp"></jsp:include>
</body>
</html>