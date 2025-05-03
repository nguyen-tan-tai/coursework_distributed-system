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
		</ul>
	
		<div class="clearfix">
			<p>${actionMessage.info}</p>
			<table class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th>No.</th>
						<th>MemberCode</th>
						<th>MemberName</th>
						<th>Item</th>
						<th>Sell Time</th>
						<th>Quantity</th>
						<th>Price</th>
						<th>Staff</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${fbs.size() == 0}">
							<tr>
								<td colspan="11">There is no record in database</td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach varStatus="i" var="f" items="${fbs}">
								<tr>
									<td>${i.index + 1}</td>
									<td>${f.member.code}</td>
									<td>${f.member.fname} ${a.member.lname}</td>
									<td>${f.fb.name}</td>
									<td><fmt:formatDate pattern="yyyy-MM-dd" value="${f.sellTime}" /></td>
									<td>${f.quantity}</td>
									<td>${f.fb.price}</td>
									<td>${f.staff.code}</td>
									<td><a href="?action=edit&task=view&id=${f.id}"><img class="icon" src="image/edit.png" alt="Edit" /></a>
									<a href="?action=delete&id=${f.id}"><img class="icon" src="image/delete.png" alt="Edit" /></a></td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
		<!--/row-->
	<!-- Content end here -->
	<jsp:include page="../commons/footer.jsp"></jsp:include>
</body>
</html>