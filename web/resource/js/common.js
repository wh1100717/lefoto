
//渐变警告
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
        return self;
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
    
    //将数据和模版绑定
    //参数 data 【存放json对的array数据】
    $.fn.ToHTML = function(data, def){
        var target = $(this);
        //console.log(target[0]);
        //生成模版函数
        var tempFunc = target[0]['tempFunc'] || (target[0]['tempFunc'] = (function(html){
            html = html.replace(/([\'|\\])/gm,"\\$1")   //转义掉 \ 和 '
                        .replace(new RegExp('{([^{}]*)}','gim'), "'+(typeof data[\"$1\"] != 'undefined' ? data[\"$1\"] : '')+'")  //转化为包括变量的字符串
                        .replace(/[\n\r]/gm,'');    //去除回车换行
            html = ["return '", html ,"';"].join('');
            return new Function('data',html);
        })(target.html()));

        var html = (function(tempFunc, data, def){
            var len = data.length;
            if(len) {
                for(var i = 0; i < len ; i++) {
                    data[i] = tempFunc(data[i]);//通过模版函数生成html
                }
            } else data[0] = def || '';
            return data.join('');
        })(tempFunc, data, def)
        return target.html(html);
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