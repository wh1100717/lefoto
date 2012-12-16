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
    var getLikeUsersUrl = '/photo/getUpUsers.html';//获取喜欢
    var addPhotoLikeUrl = '/photo/upPhoto.html';//添加喜欢
    var cancelPhotoLikeUrl = '/photo/cancelUpPhoto.html';//取消喜欢
    var loadCommentUrl = 'xx.html';//获取评论

    var iCur = -1;
    var leAToggle = 0;
    
    var YES_UP = '点击喜欢+1';
    var NO_UP = '取消喜欢';
    function loadComment(id){
        var data = {
            photoId: id
        };
        $.get(loadCommentUrl,data,function(response){
            if(response == 'success'){
                //insertNewComment();
            }else{
                alert(response);
            }
        });
    }
    function doClick(target) {
        var parent = target.closest('.tabs'),
            pparent = parent.closest('.tabsWrap'),
            item  = target.closest('.item'),
            loading = parent.find('.doLoading'),
            likeDiv = parent.find('div.like'),
            shareDiv = parent.find('div.share'),
            itemId = target.attr('rel');

        if(target.hasClass('like')) { //喜欢按钮被点击
            if(pparent.height() > 40 && iCur == 1){
                pparent.stop().animate({'height': '40px'},300);
                return false;
            }
            if(likeDiv.length == 0) {
                loading.show();
                var data = {
                    photoId: itemId
                };
                $.get(getLikeUsersUrl, data, function(response){
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
                return false;
            }
            likeDiv.hide();
            shareDiv.show();
            iCur = 2;
        }else if(target.hasClass('comment')) { //评论按钮被点击
            if(pparent.height() > 40){
                pparent.stop().animate({'height': '40px'},300);
            }
            var commentRange = item.find('div.comments');
            //var loc = commentRange.offset();
            //console.log(loc.top);
            //console.log($(document).scrollTop());
            if(commentRange.is(':visible')){
                commentRange.hide();
            }else{
                loadComment();
                commentRange.show();
                //$('html,body').animate({'scrollTop': '0px'}, 500);
            }
            iCur = 0;
            return false;
        }
        target.closest('.tabsWrap').animate({'height': '80px'}, 300);
        return false;
    }
    var doAddPhotoLike = function(id){
        var data = {
            photoId:id
        };
        $.post(addPhotoLikeUrl,data,function(response){
            if(response == 'success'){
                
            }else{
                alert(response);
            }
        });
    }
    var doCancelPhotoLike = function(id){
        var data = {
            photoId:id
        };
        $.post(cancelPhotoLikeUrl,data,function(response){
            if(response == 'success'){
                
            }else{
                alert(response);
            }
        });
    }
    var doDown = function(pEle){ 
        var pos = pEle.position();
        var startX = pos.left + 25;
        var startY = pos.top + 25;
        var downEle = $('<div class="downEle"></div>').appendTo(pEle.closest('.iMid'))
                    .css({'top':startY+'px','left':startX+'px'})
                    .animate({'top':'+=500px'},1000,function() {
                         downEle.remove();
                    });
                    
    }
    var showSmile = function(_iMid){
        var le = $('.leMid', _iMid);
        var leA = le.find('.leA');
        var iMid = $(_iMid),
            iMidW = iMid.width(),
            iMidH = iMid.height();
        var eW = le.width(),
            eH = le.height();
        var posL = (iMidW - eW)/2,
            posT = (iMidH - eH)/2;
        //console.log(posT);
        //console.log(posL);
        le.css({'top': posT + 'px','left': posL + 'px'});
        if(leA.attr('data-up') == '0'){
            leA.html(YES_UP);
        }else if(leA.attr('data-up') == '1'){
            leA.html(NO_UP);
        }
        le.fadeIn(200);
    };
    $(document).delegate('.iMid','mouseenter',function(){
        var box = $('.tabsWrap', this);
        if(box.height() > 40){

        } else {
            box.stop().animate({'height': '40px'},300);
        }
        showSmile(this);
    }).delegate('.iMid','mouseleave',function(){
        var box = $('.tabsWrap', this);
        var le = $('.leMid', this);
        if(box.height() > 40){

        } else{
            box.stop().animate({'height': '0px'},300);
        }
        le.hide();
    }).delegate('a.icon', 'click', function() {
        var target = $(this);
        doClick(target);
    }).delegate('li.isList', 'mouseenter', function() {
        $('ul',this).show();
    }).delegate('li.isList', 'mouseleave', function() {
        $('ul',this).hide();
    }).delegate('a.leA','click',function(){
        var target = $(this);
        var id = target.attr('rel');
        var pEle = target.closest('.leMid');
        console.log(leAToggle);
        if(leAToggle){
            leAToggle = false;
            doCancelPhotoLike(id);
            target.html(YES_UP);
        }else{
            leAToggle = true;
            doDown(pEle);
            doAddPhotoLike(id);
            target.html(NO_UP);
        }
    });
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
