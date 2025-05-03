<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<jsp:include page="../commons/script.jsp"></jsp:include>
	<style>
		ul{
			margin: 0 auto;
			padding: 0;
			width: 220px;
			list-style: none;
		}
		ul li{
			margin: 0;
		}
		ul li .btn{
			float: right;
		}	
	</style>
</head>
<body>
	<div class="navbar navbar-inverse navbar-fixed-top same-min-width">
	<div class="navbar-inner">
		<div class="container-fluid">
			<button type="button" class="btn btn-navbar" data-toggle="collapse"
				data-target=".nav-collapse">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="brand" href="#">NATION RACQUET AND HEALTH CLUB</a>
			<div class="nav-collapse collapse">
				<p class="navbar-text pull-right">
					<span id="clock"></span>
				</p>
			</div>
		</div>
	</div>
</div>

<div class="container-fluid same-min-width">
	<div class="row-fluid content-wrap">
		<div class="hero-unit">
			<form action="${context}" method="post">
				<c:if test="${actionMessage.errorName != null}">
					<p class="text-center alert">${actionMessage.errorName}</p>
				</c:if>
				<c:if test="${actionMessage.errorDesc != null}">
					<p class="text-center alert">${actionMessage.errorDesc}</p>
				</c:if>
				<ul>
					<li><input type="text" name="username" value="UKGRH00004"/></li>
					<li><input type="text" name="password" value="123456"/></li>
					<li><input type="submit" name="submit" class="btn btn-primary" value="Login"/></li>
				</ul>
			</form>
	<!-- Content end here -->
	<jsp:include page="../commons/footer.jsp"></jsp:include>
</body>
</html>