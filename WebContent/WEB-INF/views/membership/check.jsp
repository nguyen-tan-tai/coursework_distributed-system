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
			<li class="tab active"><a href="#" class="disabled"><span>Check</span></a></li>
		</ul>
		<div>
		<form action="${context}" method="get">
			<table class="form table table-striped">
				<tbody>
					<tr>
						<td>Code</td>
						<td>
							<input type="text" name="code" id="code" />
							<input type="hidden" name="action" value="check" />
							<input type="hidden" name="task" value="check" />
						</td>
					</tr>
				</tbody>
			</table>
			<div style="text-align: center; padding: 15px 0">
				<input class="btn" onclick="javascript: window.location = '${contextPath}/membership.dis';" type="button" value="Back"/>
				<input class="btn" type="submit" value="Check"/>
			</div>
		</form>
		</div>
		<!--/row-->
	<!-- Content end here -->
	<jsp:include page="../commons/footer.jsp"></jsp:include>
</body>
</html>