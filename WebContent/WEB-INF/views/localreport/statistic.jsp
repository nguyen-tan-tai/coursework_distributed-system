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
		<div class="wrap">
			<h3 class="text-center">Local Statistic</h3>
			<form action="${context}" method="GET">
			<input type="hidden" name="action" value="statistic" />
			<table class="table filter">
				<tbody>
					<tr>
						<td>Type of report</td>
						<td>
							<select name="type">
								<option value="0" ${type == 0 ? 'selected' : ''}>Activity & Sale</option>
								<option value="1" ${type == 1 ? 'selected' : ''}>Activity</option>
								<option value="2" ${type == 2 ? 'selected' : ''}>Sale</option>
							</select>
						</td>
						<td>Staff</td>
						<td>
							<select name="staff">
								<c:if test="${staff == 0}">
									<option value="0" selected="selected">All</option>								
								</c:if>
								<c:if test="${staff != 0}">
									<option value="0">All</option>
								</c:if>
								<c:forEach var="s" items="${staffs}">
									<c:if test="${staff == s.id}">
										<option value="${s.id}" selected="selected">(${s.code}) ${s.fname} ${s.lname}</option>								
									</c:if>
									<c:if test="${staff != s.id}">
										<option value="${s.id}">(${s.code}) ${s.fname} ${s.lname}</option>								
									</c:if>
							</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td>Year</td>
						<td>
							<select name="year">
							<c:forEach begin="2013" end="2030" var="y">
								<c:if test="${year == 0}">
									<option value="0" selected="selected">All</option>								
								</c:if>
								<c:if test="${year != 0}">
									<option value="0">All</option>
								</c:if>
								<c:if test="${y == year}">
									<option value="${y}" selected="selected">${y}</option>								
								</c:if>
								<c:if test="${y != year}">
									<option value="${y}">${y}</option>
								</c:if>
							</c:forEach>
							</select>
						</td>
						<td>Month</td>
						<td>
							<select name="month">
							<c:forEach begin="0" end="12" var="m">
								<c:if test="${m == month}">
									<option value="${m}" selected="selected">${m == 0 ? 'All' : m}</option>								
								</c:if>
								<c:if test="${m != month}">
									<option value="${m}">${m == 0 ? 'All' : m}</option>
								</c:if>
							</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td>Week</td>
						<td>
							<select name="week">
								<c:forEach begin="0" end="4" var="w">
								<c:if test="${w == week}">
									<option value="${w}" selected="selected">${w == 0 ? 'All' : w}</option>								
								</c:if>
								<c:if test="${w != week}">
									<option value="${w}">${w == 0 ? 'All' : w}</option>
								</c:if>
							</c:forEach>
							</select>
						</td>
						<td>
						</td>
						<td>
						</td>
					</tr>
					<tr>
						<td colspan="4">
							<input class="btn" onclick="javascript: window.location = '${contextPath}/localReport.dis';" type="button" value="Back"/>
							<input class="btn" type="submit" value="Report"/>
						</td>
					</tr>
				</tbody>
			</table>
			</form>
			<hr/>
			<table class="table table-striped table-bordered">
				<c:if test="${fn:length(statistics) > 0}">
				<tr>
					<td>#</td>
					<td>Staff Name</td>
					<td>Type</td>
					<td>Item Name</td>
					<td>Month</td>
					<td>Total Quantity</td>
					<td>Price</td>
					<td>Total Income</td>
				</tr>
				<c:forEach varStatus="i" var="s" items="${statistics}">
					<tr>
						<td>${i.index + 1}</td>
						<td>${s.n}</td>
						<td>${s.t}</td>
						<td>${s.an}</td>
						<td>${s.month}</td>
						<td>${s.q}</td>
						<td>${s.p}</td>
						<td>${s.total}</td>
					</tr>
					<c:set var="test" value="${test + s.total}" />
				</c:forEach>
				<tr>
					<td colspan="7">Total</td>
					<td>${test}</td>
				</tr>
				</c:if>
				<c:if test="${fn:length(statistics) == 0}">
					<tr><td colspan="7">adfa</td></tr>
				</c:if>
			</table>
		</div>
		<!--/row-->
	<!-- Content end here -->
	<jsp:include page="../commons/footer.jsp"></jsp:include>
</body>
</html>