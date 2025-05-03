<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="navbar navbar-inverse navbar-fixed-top same-min-width">
	<div class="navbar-inner">
		<div class="container-fluid">
			<button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
			</button>
			<a class="brand" href="#">NATION RACQUET AND HEALTH CLUB</a>
			<a class="logout" href="login.dis?submit=logout"><img src="image/logout.png"/></a>
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
		<div class="left-side">
			<div class="bar">
				<div id="close"></div>
				<span class=bar-title><a href="account.dis"><span class="name"><c:out value="${sessionScope.login.code}"></c:out></span></a> 
				<span class="role">${sessionScope.login.positionLabel}</span></span>
			</div>
			<div class="well sidebar-nav">
				<ul class="nav nav-list">
					<li class="nav-header">Portal</li>
					<li><a href="index.dis">Task Center</a></li>

					<li class="nav-header">Membership Center</li>
					<li><a href="membership.dis">Membership Management</a></li>
					<li class="nav-child"><a href="membership.dis?action=check"><span class="check">Check member</span></a></li>
					<li class="nav-child"><a href="membership.dis?action=add"><span class="add">Add member</span></a></li>
					<li class="nav-child"><a href="membership.dis?action=list"><span class="list">List member</span></a></li>
					
					<li class="nav-header">Activity Center</li>
					<li><a href="activityOrder.dis">Activity Management</a></li>
					<li class="nav-child"><a href="activityOrder.dis?action=add"><span class="add">New Booking</span></a></li>
					<li class="nav-child"><a href="activityOrder.dis?action=list"><span class="list">List Booking</span></a></li>
					
					<li class="nav-header">Sale Center</li>
					<li><a href="sell.dis">Sale Management</a></li>
					<li class="nav-child"><a href="sell.dis?action=check"><span class="add">New Sale</span></a></li>
					<li class="nav-child"><a href="sell.dis?action=list"><span class="list">List Sales</span></a></li>
					
				<c:if test="${sessionScope.login.position > 1}">
					<li class="nav-header">Local Manager</li>
					<li><a href="localReport.dis">Local Report Center</a></li>
					<li class="nav-child"><a href="localReport.dis?action=report"><span class="report">Local Sale Report</span></a></li>
					<li class="nav-child"><a href="localReport.dis?action=statistic"><span class="statistic">Local Staff Statistic</span></a></li>
					
				</c:if>
				<c:if test="${sessionScope.login.position > 2}">
					<li class="nav-header">National Manager</li>
					<li><a href="nationalReport.dis">National Report Center</a></li>
					<li class="nav-child"><a href="nationalReport.dis?action=report"><span class="report">National Sale Report</span></a></li>
					<li class="nav-child"><a href="nationalReport.dis?action=statistic"><span class="statistic">National Staff Statistic</span></a></li>
					
				</c:if>
				</ul>
			</div>
			<!--/.well -->
		</div>
		<!--/span-->
		<div class="content">