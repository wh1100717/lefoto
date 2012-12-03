<!DOCTYPE html>
<html>  
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">  
        <title>Lefoto</title>
        <script src="/lefoto/src/js/jquery-1.8.0.min.js" type="text/javascript"></script>
        <style type="text/css">
            html{ overflow: auto;}
            html,body,div,span,applet,object,iframe,h1,h2,h3,h4,h5,h6,p,blockquote,pre,a,abbr,acronym,address,big,cite,code,del,dfn,em,font,img,ins,kbd,q,s,samp,small,strike,strong,sub,sup,tt,var,b,u,i,center,dl,dt,dd,ol,ul,li,fieldset,form,label,legend,table,caption,tbody,tfoot,thead,tr,th,td,input{margin:0;padding:0;border:0 none; outline:0; vertical-align: baseline;}
            body { font-family: "微软雅黑"; font-size: 12px; color: #333; background-color: #E7EAD4;}
            ul,li { list-style: none;}
            a { text-decoration: none;}
            .clearfix:after { content: "."; display: block; height: 0; visibility: hidden; clear: both;}
            .fl { float: left;}
            .fr { float:right;}
            .mask { display: block; position: absolute; left: 0; top: 0; width: 100%; height: 100%; background-color: #000; opacity: 0.5; filter: alpha(opacity=50);}
            
            .le-content { width: 100%; position: relative;}
            .navbar,.body-wrap { width: 960px;}
            .body-wrap { margin: 0 auto; min-height: 500px; }
            
            .le-header { height: 180px; overflow: hidden; background-color: #000; border-bottom: 3px #FFF solid;}
            .le-iupload { position: relative; width: 960px; height: 180px; margin: 0 auto; background: url(/lefoto/src/images/top_logo.png) #000 no-repeat scroll center bottom;}
            .le-iupload a { position: absolute; bottom: 7px; right:260px; display: block; width: 120px; height: 29px;}
            .le-img-iupload-btn { position: absolute; top: 130px; left: 55%;}
            
            .mside { width: 420px; margin: 0 auto;}
            .lside { padding: 3px; border: 1px #EEE solid; border-radius: 3px; background-color: #FFF;}
            .mside ul li,.rside ul li { float: left; text-align: center; height: 28px; line-height: 28px;}
            .mside ul li a,.rside ul li a { color: #EEE;}
            .mside ul li a:hover,.rside ul li a:hover { color: #FFF;}
            .mside ul li { width: 80px;}
            .rside ul li { width:40px;}
            .lside input { vertical-align: top; margin-right: -1px; float: left; border: 0 none; background-color: transparent; outline: none;}
            input.sbox { height: 22px; line-height: 22px; width: 180px; font-weight: 800;}
            input.sbtn { display: block; cursor: pointer; height: 28px; width: 36px; margin: -3px 0;}
            
            .navbarwrap { width: 100%; background-color: green;}
            .navbar { position: relative; margin: 0 auto; padding: 5px 0; background-color: green;}
            
            /*瀑布流*/
            .waterfall { width: 960px; margin: 0 auto; margin-top: 10px; position: relative; }
            .col { float: left; padding: 7px; width: 300px; vertical-align: top; overflow-x: hidden; }
            .item { position: relative; background-color: #CCC; width: 100%; height: auto; margin-bottom: 20px; box-shadow: 0 1 4px #808080; border: 1px #E1E1E1 solid; border-radius: 3px; overflow: hidden; }
            .img-desc { width: 220px; padding-top: 7px; padding-right: 7px; font-size: 12px; display: block; line-height: 1.5;  text-align: right; background-color: white; }
            .item-img { display: block; width: 300px; min-height: 200px; opacity: 0; filter: alpha(opacity=0); }
            .detectDiv { clear: both; text-align: center; color: #000; height: 32px; line-height: 32px; padding-bottom: 20px; }
            .loading { display: inline-block; padding-left: 32px; background: url(/lefoto/src/images/loading2.gif) 0 0 scroll transparent no-repeat; }
        
            .itop { background-color: #FFF;}
            
            .ibar { position: absolute; left: 90px; bottom: 10px; width: 200px; }
            .ibar > span { position: relative; margin: 0 6px; display: block; float: left; width: 60px; height: 22px; line-height: 22px; text-align: center;}
            .ibar-a { position: absolute; left: 0; top: 0; display: block; width: 100%; height: 100%; font-size: 12px; color: #808080;}
            .ibar > span:hover .mask { opacity: 0.8; filter: alpha(opscity=80); }
            .ibar > span:hover .ibar-a { color: #999;}
        </style>
    </head>  
    <body>
        <script id="itempl" type="text/html">
            <div class="item">
                <div class="itop clearfix">
                    <a href="javascript:;"><img class="fl" style="height: 48px; width: 48px;" src="" /></a>
                    <span class="img-desc fr">楼主，亮了～～</span>
                </div>
                <div class="imid">
                    <a href="javascript:alert('还木有呢');"><img width="300" height="300" src="" alt="" /></a>
                </div>
                <div class="ibar" style="display: block;">
                    <span>
                        <span class="mask"></span>
                        <a class="ibar-a" href="javascript:;">采集</a>
                    </span>
                    <span>
                        <span class="mask"></span>
                        <a class="ibar-a" href="javascript:;">评论</a>
                    </span>
                </div>
            </div>
        </script>
        <div class="le-header">
            <div class="le-iupload">
                <a href="javascript:;">
                    <img src="/lefoto/src/images/upload_btn.png" alt="" />
                </a>
            </div>
        </div>
        <div class="le-content">
            <div class="navbarwrap">
                <div class="navbar clearfix">
                    <div class="lside fl">
                        <form action="" method="get">
                            <input autocomplete="off" class="sbox" name="kw" type="text" value="搜搜看吧" onfocus="if(this.value=='搜搜看吧'){this.value='';};" onblur="if(this.value==''){this.value='搜搜看吧';};" x-webkit-speech />
                            <input class="sbtn" type="submit" value="搜搜" />
                        </form>
                    </div>
                    <div class="rside fr">
                        <ul>
                            <li><a href="/lefoto/index/show.html">登录</a></li>
                            <li><a href="/lefoto/index/show.html">注册</a></li>
                        </ul>
                    </div>
                    <div class="mside">
                        <ul>
                            <li><a href="/lefoto/index/show.html?cateId=1">搞笑</a></li>
                            <li><a href="/lefoto/index/show.html?cateId=2">萌宠</a></li>
                            <li><a href="/lefoto/index/show.html?cateId=3">童真</a></li>
                            <li><a href="/lefoto/index/show.html?cateId=4">美女</a></li>
                            <li><a href="/lefoto/index/show.html?cateId=5">随便看看</a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="body-wrap">
                <div id="waterFall" class="wafterfall">
                    <div class="col"></div>
                    <div class="col"></div>
                    <div class="col"></div>
                    <div id="detectDiv" class="detectDiv"><span class="loading">正在很费力的加载...</span></div>
                </div>
            </div>
        </div>
        <input name="cateId" type="hidden" value="${cateId}" />
        <script type="text/javascript">
            function getElementByClassName(tag, className) {
                var eles = [], tag = tag || '*';
                var tags = document.getElementsByTagName(tag);
                var reg = new RegExp('\\b' + className + '\\b');
                console.log(reg);
                for(var i=0; i<tags.length; i++) {
                        if(reg.test(tags[i].className)) {
                            eles.push(tags[i]);
                        }
                }
                return eles;
            }
            function imgShow(imgEle) {
                $(imgEle).animate({ 'opacity': '1' }, 500);
            }
            var waterFall = {
                url:'/lefoto/index/getPhoto.html',//请求数据的接口
                data: [],//保存请求返回的数据，json数组
                startIndex: 0,
                perPage: 10, //每次请求返回的数据量
                page: 1,//请求的当前页
                cols: undefined,
                loadFinish: false,
                isLoading: false,
                //加载数据前检测是否能继续加载数据
                loadDetect: function() {
                    var scrollTop = document.documentElement.scrollTop || document.body.scrollTop,
                        offsetTop = document.getElementById('detectDiv').offsetTop,
                        H = window.innerHeight || document.documentElement.clientHeight;
                        
                        if(!waterFall.loadFinish && !waterFall.isLoading && offsetTop - scrollTop <= H) {
                            waterFall.getData();
                        } else {
                            //console.log(waterFall.loadFinish);
                        }
                },
                //滚动监听
                scroll: function() {
                    var wf = this;
                    window.onscroll = function() {
                        wf.loadDetect();
                    }
                    return this;
                },
                //取高度最小的列
                minHeightColumn2: function() {
                    //缓存列标签元素，下次使用时可直接调用，不用再次获取
                    var columns = waterFall.cols || (waterFall.cols = getElementByClassName('div','col')), minHCol = columns[0];
                    for(var i=0; i<columns.length; i++) {
                        if(minHCol.offsetHeight > columns[i].offsetHeight) {
                            minHCol = columns[i];
                        }
                    }
                    return $(minHCol);
                },
                minHeightColumn: function() {
                    //缓存列标签元素，下次使用时可直接调用，不用再次获取
                    var columns = waterFall.cols || (waterFall.cols = $('.col')), minHCol = undefined;
                    columns.each(function() {
                        minHCol = (minHCol == undefined) ? $(this) : minHCol.height() > $(this).height() ? $(this) : minHCol;
                    })
                    return minHCol;
                },
                //添加请求返回的数据
                append: function(data) {
                    for(var i=0; i < data.length; i++) {
                        //var itemHtml = '<div class="item"><img height="' + data[i].height + '" class="item-img" src="http://lefoto.b0.upaiyun.com' + data[i].url + '" onload="imgShow(this)" /><span>' + data[i].description + '</span></div>';
                        var itemHtml = '<div class="item">'+
                                        '<div class="itop clearfix">'+
                                            '<a href="javascript:;"><img class="fl" style="height: 48px; width: 48px;" src="http://le-face.b0.upaiyun.com/'+data[i].face+'" /></a>'+
                                            '<span class="img-desc fr">'+data[i].description+'</span>'+
                                        '</div>'+
                                        '<div class="imid">'+
                                            '<a href="javascript:alert(\'还木有呢\');"><img width="300" height="'+data[i].height+'" src="http://lefoto.b0.upaiyun.com'+data[i].url+'" onload="imgShow(this)" alt="" /></a>'+
                                        '</div>'+
                                        '<div class="ibar" style="display: block;">'+
                                            '<span>'+
                                                '<span class="mask"></span>'+
                                                '<a class="ibar-a" href="javascript:;">采集</a>'+
                                            '</span>'+
                                            '<span>'+
                                                '<span class="mask"></span>'+
                                                '<a class="ibar-a" href="javascript:;">评论</a>'+
                                            '</span>'+
                                        '</div>'+
                                    '</div>';
                        
                        
                        
                        //添加到高度最小的那一列
                        waterFall.minHeightColumn2().append(itemHtml);
                    }
                    return this;
                },
                //向服务器请求数据
                getData: function() {
                    var reqUrl = waterFall.url;
                    var paras = {
                        lastPhotoId: waterFall.startIndex,
                        size: waterFall.perPage,
                        cateId:$('input[name=cateId]').val()
                    };
                    waterFall.isLoading = true;//设置正在载入状态
                    $.get(reqUrl,paras, function(response) {
                        var response = eval('(' + response + ')');
                        var data = response.data;
                        waterFall.startIndex = data[data.length - 1].id;
                        waterFall.data = data;
                        waterFall.append(data);
                        //设置非载入状态
                        waterFall.isLoading = false;
                    });
                    return this;
                },
                //初始化瀑布流
                init: function() {
                    waterFall.scroll().getData();
                    return this;
                }
            };
            waterFall.init();
        </script>
    </body>  
</html>  