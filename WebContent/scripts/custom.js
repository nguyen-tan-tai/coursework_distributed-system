$(document).ready(function() {
	var a = window.location.pathname;
	a = a.substr(a.lastIndexOf("/") + 1, a.lastIndexOf("dis"));
	$('.sidebar-nav .nav li a').each(function(){
		if(a.indexOf($(this).attr('href')) > -1){
			$(this).parent().addClass('active');
		}
	});

	startTime();
	$('#close').click(function(){
		if($('.content-wrap').hasClass('hide-left-side')){
			$('.content-wrap').removeClass('hide-left-side');
		} else{
			$('.content-wrap').addClass('hide-left-side');			
		}
	});
});

function startTime(){
	document.getElementById('clock').innerHTML = new XDate().toString('dddd, dd MMMM yyyy hh:mm:ss');
	t=setTimeout(function(){startTime();}, 1000);
}