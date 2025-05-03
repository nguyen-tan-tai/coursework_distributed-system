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
			<h3 class="text-center">Local Report</h3>
			<form action="${context}" method="GET">
			<input type="hidden" name="action" value="report" />
			<table class="table filter">
				<tbody>
					<tr>
						<td>Type of report</td>
						<td>
							<select name="type">
								<option value="a-n">Activity - By Activity Name</option>
								<option value="a-t">Activity - By Activity Type</option>
								<option value="s-n">Sale - By Item Name</option>
								<option value="s-t">Sale - By Item Type</option>
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
						<td>
							<input class="btn" onclick="javascript: window.location = '${contextPath}/localReport.dis';" type="button" value="Back"/>
							<input class="btn" type="submit" value="Report"/>
						</td>
					</tr>
				</tbody>
			</table>
			</form>
			<hr/>
			<table class="table table-striped table-bordered">
				<tr>
					<td>#</td>
					<td>Item Name</td>
					<td>Item Type</td>
					<td>Total Quantity</td>
					<td>Price</td>
					<td>Total Sale</td>
				</tr>
				<c:forEach varStatus="i" var="r" items="${reports}">
					<tr>
						<td>${i.index + 1}</td>
						<td>${r.n}</td>
						<td>${r.t}</td>
						<td>${r.q}</td>
						<td>${r.p}</td>
						<td>${r.m}</td>
					</tr>
					<c:set var="test" value="${test + r.m}" />
				</c:forEach>
				<tr>
					<td colspan="5">Total</td>
					<td>${test}</td>
				</tr>
			</table>
		</div>
		<!--/row-->
	<!-- Content end here -->
	<jsp:include page="../commons/footer.jsp"></jsp:include>
</body>
</html>