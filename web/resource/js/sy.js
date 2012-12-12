(function(){
    var doms = {};
    function tempToHTML(id, data, def){
        var dom = doms[id] == undefined ? (doms[id] = document.getElementById(id)) : doms[id]; //缓存dom模版，保存在doms对象中
        var parseObj = dom[id] || (dom[id] = (function(html){
            html = html.replace(/([\'|\\])/gm,"\\$1")   //转义掉 \ 和 '
                        .replace(new RegExp('{([^{}]*)}','gim'), "'+(typeof data[\"$1\"] != 'undefined' ? data[\"$1\"] : '')+'")  //转化为包括变量的字符串
                        .replace(/[\n\r]/gm,'');    //去除回车换行
            html = ["return '", html ,"';"].join('');
            //console.log(html);
            return new Function('data',html);
        })(dom.innerHTML));
        var len = data.length;
        if(len){
            for(var i = 0, ar = data[i]; i < len ; i++) {
                data[i] = parseObj(ar);
            }
        } else data[0] = def || '';
        return data.join('');
        //dom.innerHTML = data.join('');
    }
    window.tempToHTML = tempToHTML;
})();
(function($){
    //返回顶部start
    var backToTopEle = $('<div class="backToTop"><a href="javascript:void(0);" title="返回顶部"></a></div>')
        .css({ 'display': 'none' })
        .appendTo($("body")).click(function () {
            $("html, body").animate({ scrollTop : 0 }, 300);
        }), backToTopFun = function () {
            var st = $(document).scrollTop();
            (st > 2000) ? backToTopEle.show() : backToTopEle.hide();
        };
    $(window).bind("scroll", backToTopFun);
    //返回顶部end
})(jQuery);
(function($){
    var getLikeUrl = '/data/like.cshtml';//获取喜欢
    var iCur = -1;
    function doLoading(){

    }
    function loadOK(){

    }
    function doClick(target) {
        var parent = target.closest('.tabs'),
            pparent = parent.closest('.tabsWrap'),
            loading = parent.find('.doLoading'),
            likeDiv = parent.find('div.like'),
            shareDiv = parent.find('div.share');

        if(target.hasClass('like')) { //喜欢按钮被点击
            if(pparent.height() > 40 && iCur == 1){
                pparent.stop().animate({'height': '40px'},300);
                return;
            }
            if(likeDiv.length == 0) {
                loading.show();
                $.get(getLikeUrl, {}, function(response){
                    $(response).appendTo(parent);
                    loading.hide();
                });
            } else {
                likeDiv.show();
            }
            shareDiv.hide();
            iCur = 1;
        } else if(target.hasClass('share')) { //分享按钮被点击
            if(pparent.height() > 40 && iCur == 2){
                pparent.stop().animate({'height': '40px'},300);
                return;
            }
            likeDiv.hide();
            shareDiv.show();
            iCur = 2;
        }
        target.closest('.tabsWrap').animate({'height': '80px'}, 300);
    }
    $(document).delegate('.iMid','mouseenter',function(){
        var box = $('.tabsWrap', this);
        console.log('ojojo');
        if(box.height() > 40){

        } else {
            box.stop().animate({'height': '40px'},300);
        }
    }).delegate('.iMid','mouseleave',function(){
        var box = $('.tabsWrap', this);
        if(box.height() > 40){

        } else{
            box.stop().animate({'height': '0px'},300);
        }
    }).delegate('a.icon', 'click', function() {
        var target = $(this);
        console.log(this);
        doClick(target);
    });;
    
    //$(document)
})(jQuery);
function toList(id){
    var comm_list = $('#i_'+id);
    console.log(comm_list.length);
    if(comm_list.is(':visible')){comm_list.hide();}
    else{
        comm_list.show();
    }
    return false;
}
//自定义jQuery扩展函数
$.fn.scrollFix = function(){
    var self = $(this);
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
