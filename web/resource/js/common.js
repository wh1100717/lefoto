(function($){
        var backToTopEle = $('<div class="backToTop"><a href="javascript:void(0);" title="返回顶部"></a></div>')
	    .appendTo($("body")).css({ 'display': 'none' }).click(function () {
	        $("html, body").animate({ scrollTop : 0 }, 300);
	    }), backToTopFun = function () {
	        var st = $(document).scrollTop();
	        (st > 2000) ? backToTopEle.show() : backToTopEle.hide();
	    };
        $(window).bind("scroll", backToTopFun);
})($);
