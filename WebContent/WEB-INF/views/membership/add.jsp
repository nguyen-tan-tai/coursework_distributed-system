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
			<c:if test="${member != null}">
				<li class="tab active"><a href="#" class="disabled"><span>Update</span></a></li>
			</c:if>
		</ul>
		<div>
			<form action="${context}" method="get">
				<table class="form">
					<tbody>
					<c:if test="${member != null}">
						<tr>
							<td>Reference Id</td><td>
								${member.id}
								<input type="hidden" name="id" value="${member.id}"/>
								<input type="hidden" name="action" value="edit"/>
								<input type="hidden" name="task" value="update"/>
							</td>
						</tr>
						<tr>
							<td>Member Code</td><td><input type="hidden" name="code" value=""/></td>
						</tr>
					</c:if>
					<c:if test="${member == null}">
						<tr>
							<td>Member Code</td><td>
								Auto generate
								<input type="hidden" name="action" value="add"/>
								<input type="hidden" name="task" value="save"/>	
							</td>
						</tr>
						<tr>
							<td>Member Code</td><td><input type="text" name="code" value="M"/>${member.code}</td>
						</tr>
					</c:if>

						<tr>
							<td>First Name</td><td><input type="text" name="fname" value="${member.fname}"/></td>
						</tr>
						<tr>
							<td>Last Name</td><td><input type="text" name="lname" value="${member.lname}"/></td>
						</tr>
						<tr>
							<td>Address</td><td><input type="text" name="address" value="${member.address}"/></td>
						</tr>
						<tr>
							<td>Phone</td><td><input type="text" name="phone" value="${member.phone}"/></td>
						</tr>
						<tr>	
							<td>Email</td><td><input type="text" name="email" value="${member.email}"/></td>
						</tr>
						<tr>	
							<td>Type</td><td>
								<select name="type">
									<c:forEach items="${MemberType}">
										<option value=""></option>
									</c:forEach>
								</select>
						</tr>
						<tr>
							<td colspan="2" class="button-group"><input class="btn" onclick="javascript: window.location = '${contextPath}/membership.dis';" type="button" value="Cancel"/><input type="submit" class="btn btn-warning" value="${member == null ? 'Save' : 'Update'}"/></td>
						</tr>
						
					</tbody>
				</table>
			</form>
		</div>
		<!--/row-->
	<!-- Content end here -->
	<jsp:include page="../commons/footer.jsp"></jsp:include>
</body>
</html>