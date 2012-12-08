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
}
)();
(function($){
        var backBtn = $('<div class="backToTop"><a href="javascript:void(0);" title="返回顶部"></a></div>').css({ 'display': 'none' }).appendTo($("body")).click(function () {
	        $("html, body").animate({ scrollTop : 0 }, 300);
	    });
        window.onscroll = function () {
            var st = $(document).scrollTop();
            (st > 800) ? backBtn.show() : backBtn.hide();
        };
})(jQuery);
