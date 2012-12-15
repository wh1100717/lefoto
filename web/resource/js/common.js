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
var addCommentUrl = '';//添加评论地址
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
$(document).delegate('.btn-addComment','click',function(){ //评论按钮事件绑定
    var target = $(this);
    var id = target.attr('rel');
    var content = target.closest('.fwrap').find('textarea[name=content]').val();
    if($.trim(content).length == 0){return ;}
    var comment = {};
    comment['content'] = encodeURI(content);
    comment['id'] = id;
    doAddComment(comment);
});
function doAddComment(comment){
    $.post(addCommentUrl,comment,function(response){
        if(response == 'success'){
            
        } else {
            alert(response);
        }
    })
}
