var meme = [];

var foo = function duyh (argument) {
	// body...
};

if (false) {
	document.write("What does this do");
};

$(document).ready(function(){
	$("button").mouseenter(function(){
		$(this).animate({
			opacity: 0.5
		}, 'fast');
	});

	$("button").mouseleave(function(){
		$(this).animate({
			opacity: 1.0
		}, 'fast');
	});

	$("button").click(function(){
		$("#resume").animate({
			height: 'toggle'
		});
	});
});