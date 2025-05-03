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
			
			$('.datepicker').datepicker();
		});
	</script>
</head>
<body>
	<jsp:include page="../commons/header.jsp"></jsp:include>
	<!-- Content start here -->
		<ul class="tabs">
			<li class="tab"><a href="?action=list"><span>List</span></a></li>
			<li class="tab"><a href="?action=add"><span>Add</span></a></li>
			<c:if test="${ao  != null}">
				<li class="tab active"><a href="#" class="disabled"><span>Update</span></a></li>
			</c:if>
		</ul>
		<div>
			<form action="${context}" method="GET">
				<table class="form">
					<tbody>
						<tr>
							<c:if test="${ao != null}">
							<td>Reference Id</td><td>
								${ao.id}
								<input type="hidden" name="id" value="${ao.id}"/>
								<input type="hidden" name="action" value="edit"/>
								<input type="hidden" name="task" value="update"/>
							</td>
							</c:if>
							<c:if test="${ao == null}">
							<td>Reference Id</td><td>
								Auto generate
								<input type="hidden" name="action" value="add"/>
								<input type="hidden" name="task" value="save"/>
							</td>
							</c:if>					
						</tr>
						<tr>
							<td>Member</td>
							<td>
								<select name="member">
									<c:forEach var="m" items="${allMembers}">
										<c:if test="${ao != null}">
											<option ${m.id == ao.member.id ? 'selected' : ''} value="${m.id}">${m.fname} ${m.lname}</option>
										</c:if>
										<c:if test="${ao == null}">
											<option value="${m.id}">${m.fname} ${m.lname}</option>
										</c:if>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<td>Activity</td>
							<td>	
								<select name="activity">
									<c:forEach var="a" items="${allActivities}">
										<c:if test="${ao != null}">
											<option ${a.id == ao.activity.id ? 'selected' : ''} value="${a.id}">${a.name}</option>
										</c:if>
										<c:if test="${ao == null}">
											<option value="${a.id}">${a.name}</option>
										</c:if>
									</c:forEach>
								</select>
						</tr>
						<tr>
							<td>Booking Time</td><td><input class="datepicker" type="text" name="btime" value="<fmt:formatDate pattern="MM/dd/yyyy" value="${ao.bookTime}" />"/></td>
						</tr>
						<tr>
							<td>Start Time</td><td><input class="datepicker" type="text" name="stime" value="<fmt:formatDate pattern="MM/dd/yyyy" value="${ao.startTime}" />"/></td>
						</tr>
						<tr>
							<td>End Time</td><td><input class="datepicker" type="text" name="etime" value="<fmt:formatDate pattern="MM/dd/yyyy" value="${ao.endTime}" />"/></td>
						</tr>
						<tr>
							<td>Quantity</td><td><input type="text" name="quantity" value="${ao.quantity}"/></td>
						</tr>
						<tr>
							<td colspan="2" class="button-group"><input class="btn" onclick="javascript: window.location = '${contextPath}/activityOrder.dis';" type="button" value="Cancel"/><input type="submit" class="btn btn-warning" value="${ao == null ? 'Save' : 'Update'}"/></td>
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