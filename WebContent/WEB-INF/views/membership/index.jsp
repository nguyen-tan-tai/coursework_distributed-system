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
			$('.search-toggle').click(function(){
				$('#search').toggleClass('search-hide');
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
		
		<div class="search-hide" id="search">
			<div class="search-toggle"></div>
			<div class="search-form">
				<form action="" method="GET">
					<div class="span4">
						<span></span>
					</div>
					<div class="span8">
						<table class="form">
							<tbody>
								<tr><td>Code</td><td><input name="code" type="text"/></td></tr>
								<tr><td>Name</td><td><input name="code" type="text"/></tr>
								<tr><td></td><td><input type="submit" class="btn btn-primary" value="Search"></td></tr>
							</tbody>
						</table>
					</div>
				</form>
			</div>
		</div>
		<div class="clearfix">
			<p>${actionMessage.info}</p>
			<table class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th>No.</th>
						<th>Member ID</th>
						<th>Name</th>
						<th>Code</th>
						<th>Address</th>
						<th>Phone</th>
						<th>Email</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${fn:length(members) == 0}">
							<tr>
								<td colspan="8">There is no record in database</td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach varStatus="i" var="m" items="${members}">
								<tr>
									<td>${i.index + 1}</td>
									<td>${m.id}</td>
									<td>${m.fname} ${m.lname}</td>
									<td>${m.code}</td>
									<td>${m.address}</td>
									<td>${m.phone}</td>
									<td>${m.email}</td>
									<td><a href="?action=edit&task=view&id=${m.id}"><img class="icon" src="image/edit.png" alt="Edit" /></a>
									<a href="?action=delete&id=${m.id}"><img class="icon" src="image/delete.png" alt="Delete" /></a></td>
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