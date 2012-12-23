
//输入框警告
;(function($){
    $.fn.showWaring = function(msg) {
        var target = $(this);
        var targetW = target.outerWidth(),
            targetH = target.outerHeight(),
            targetTop = target.position().top,
            targetLeft = target.position().left;
        var waring;
        var css = {
            'display':'block',
            'position':'absolute',
            'top': targetTop + 'px',
            'left': targetLeft + 'px',
            'width':(targetW - 0) + 'px',
            'height': (targetH - 0) + 'px',
            'line-height': targetH + 'px',
            'text-align':'center',
            'opacity':'0',
            'background-color':'#f60',
            'color':'#EEE',
            'z-index': 9999
        };
        if(target.siblings('.__waring').length == 0){
            waring = $('<div class="__waring">' + msg + '</div>').css(css).insertAfter(target);
        } else {
            waring = $(target.siblings('.__waring')[0]);
            waring.css('z-index','9999');
        }
        waring.stop().animate({'opacity':'1'},1000,function() {
            waring.stop().animate({'opacity':'0'},1000,function(){
                waring.css('z-index','-1');
            });
        });
    }
    
    /*
     * 自定义jQuery扩展函数部分
     */
    
    //，当滚动距离超过元素的位置时（即document.scrollTop大于元素的offsetTop时），元素将被固定
    $.fn.scrollFix = function(){
        var self = $(this);
        //console.log(this);
        var top = self.offset().top;
        $(window).scroll(function(){
            var scrollTop = $(document).scrollTop();
            if(top < scrollTop && !self.hasClass('fixedbar')){
                self.addClass('fixedbar'); 
            }else if(top > scrollTop && self.hasClass('fixedbar')){
                self.removeClass('fixedbar'); 
            }
        })
    }
    
    $.fn.getValueAndToObject = function(selector){
        var data;
        $(this).find(selector).each(function(){
            var name = $(this).attr('name');
            var value = $(this).attr('encode') ? encodeURI($(this).val()) : $(this).val();
            if(name != undefined) {
                data[name] = value;
            }
        });
        return data;
    }
})(jQuery);

//返回顶部
(function($){
        var backToTopEle = $('<div class="backToTop"><a href="javascript:void(0);" title="返回顶部"></a></div>')
	    .appendTo($("body")).css({ 'display': 'none' }).click(function () {
	        $("html, body").animate({ scrollTop : 0 }, 300);
	    }), backToTopFun = function () {
	        var st = $(document).scrollTop();
	        (st > 2000) ? backToTopEle.show() : backToTopEle.hide();
	    };
        $(window).bind("scroll", backToTopFun);
})(jQuery);