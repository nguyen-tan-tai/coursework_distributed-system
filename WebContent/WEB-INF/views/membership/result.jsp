<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<jsp:include page="../commons/script.jsp"></jsp:include>
	<script>
		var p = '${param.action}';
		if(p == ''){
			p = 'list';
		}
		$(document).ready(function(){
			$('.tabs').children().each(function(){
				if($(this).find('a').attr('href').indexOf(p) > -1){
					$(this).parent().children().removeClass('active');
					$(this).addClass('active');
				}
			});
		});
	</script>
</head>
<body>
	<jsp:include page="../commons/header.jsp"></jsp:include>
	<!-- Content start here -->
		<ul class="tabs">
			<li class="tab"><a href="?action=list"><span>List</span></a></li>
			<li class="tab"><a href="?action=add"><span>Add</span></a></li>
			<li class="tab active"><a href="#" class="disabled"><span>Result</span></a></li>
		</ul>
		<div>
		<c:if test="${member != null}">
			<h3 class="text-center">Customer Information</h3>
			<table class="form table table-striped">
				<tbody>
					<tr>
						<td>ID</td><td>${member.code} </td>
					</tr>
					<tr>
						<td>First Name</td><td>${member.fname}</td>
					</tr>
					<tr>
						<td>Last Name</td><td>${member.lname}</td>
					</tr>
					<tr>
						<td>Address</td><td>${member.address}</td>
					</tr>
					<tr>
						<td>Phone</td><td>${member.phone}</td>
					</tr>
					<tr>	
						<td>Email</td><td>${member.email}</td>
					</tr>
					<tr>
						<td colspan="2" class="button-group">
							<input class="btn" onclick="javascript: window.location = '${contextPath}/membership.dis';" type="button" value="Back"/>
						</td>
					</tr>
				</tbody>
			</table>
		</c:if>
		<c:if test="${member == null}">
			<h3 class="text-center">${actionMessage.msg}</h3>
			<input class="btn" onclick="javascript: window.location = '${contextPath}/membership.dis';" type="button" value="Back"/>			
		</c:if>
		</div>
		<!--/row-->
	<!-- Content end here -->
	<jsp:include page="../commons/footer.jsp"></jsp:include>
</body>
</html>