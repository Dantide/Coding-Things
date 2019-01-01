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
			opacity: 1.0
		}, 'fast');
	});

	$("button").mouseleave(function(){
		$(this).animate({
			opacity: 0.5
		}, 'fast');
	});

	$("button").click(function(){
		$("#resume").animate({
			height: 'toggle'
		});

		$(this).click(function(){
            $(this).text(function(i, v){
               return v === 'Hide this Resume' ? 'Show this Resume' : 'Hide this Resume'
            })
        });
        // Look Over this
	});
});