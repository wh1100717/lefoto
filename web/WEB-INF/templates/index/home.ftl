<!DOCTYPE html>
<html>  
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">  
        <title>Lefoto</title>
        <link href="/src/css/common2.css" rel="stylesheet" />
        <script src="/src/js/jquery-1.8.0.min.js" type="text/javascript"></script>
        <style type="text/css">
            html{ overflow: auto;}
            html,body,div,span,applet,object,iframe,h1,h2,h3,h4,h5,h6,p,blockquote,pre,a,abbr,acronym,address,big,cite,code,del,dfn,em,font,img,ins,kbd,q,s,samp,small,strike,strong,sub,sup,tt,var,b,u,i,center,dl,dt,dd,ol,ul,li,fieldset,form,label,legend,table,caption,tbody,tfoot,thead,tr,th,td,input{margin:0;padding:0;border:0 none; outline:0; vertical-align: baseline;}
            body { font-family: "微软雅黑"; font-size: 12px; color: #333; background-color: #FFF;}
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
            .le-iupload { position: relative; width: 960px; height: 180px; margin: 0 auto; background: url(/src/images/top_logo.png) #000 no-repeat scroll center bottom;}
            .le-iupload a { position: absolute; bottom: 7px; right:260px; display: block; width: 120px; height: 29px;}
            .le-img-iupload-btn { position: absolute; top: 130px; left: 55%;}

            .mside { width: 420px; margin: 0 auto;}
            .lside { padding: 3px; border: 1px #EEE solid; background-color: #FFF;}
            .mside ul li,.rside ul li { float: left;  text-align: center; height: 30px; line-height: 30px; padding: 5px 0; margin: -5px 0;}
            .mside ul li a,.rside ul li a { color: #EEE; display: block; width: 100%; height: 30px;}
            .mside ul li:hover,.rside ul li:hover,li.selected { background-color: #558000;}
            .mside ul li:hover a,.rside ul li:hover a { color: #FFF;}
            .mside ul li { width: 80px; }
            .rside ul li { width:40px;}
            .lside input { vertical-align: top; margin-right: -1px; float: left; border: 0 none; background-color: transparent; outline: none;}
            input.sbox { height: 22px; line-height: 22px; width: 180px; font-weight: 800;}
            input.sbtn { display: block; cursor: pointer; height: 28px; width: 36px; margin: -3px 0;}

            .navbarwrap { width: 100%; background-color: green;}
            .navbar { position: relative; margin: 0 auto; padding: 5px 0; background-color: green;}

            /*瀑布流start*/
            .waterFall { display: block; width: 960px; margin: 0 auto; margin-top: 30px; position: relative; }
            .col { float: left; overflow-x: hidden; }
            .detectDiv { clear: both; text-align: center; color: #000; height: 32px; line-height: 32px; padding-bottom: 20px; }
            .loading { display: inline-block; padding-left: 32px; background: url(/src/images/loading2.gif) 0 0 scroll transparent no-repeat; }
            /*瀑布流end*/
            .iTop { padding: 7px 0 20px 0;}
            .iMid { text-align: center; overflow: hidden; width: 420px;}
            .iTop p { margin-bottom: 5px;}
            .item { position: relative; padding: 7px 15px; width: 420px; background-color: #FFF; border: 1px #E1E1E1 solid; box-shadow: 2px 2px 4px #E1E1E1;}
            .Es { position: absolute; top: 15px; right: 15px; display: block; width: 45px; background: url(http://ipic.tuita.cc/img/theme/theme_canon/temp_bg.png?v=1346141329) right -32px no-repeat;}
            .Es:hover { background-color: #f1f1f1;}
            .Es span.num { display: inline-block; padding: 0 7px; line-height: 20px; height: 20px; border: 1px #E1E1E1 solid; border-width: 1px 0 1px 1px;}
            a.img { display: block; }
            .iTop img { float: left; }
            .iTop p { margin-left: 40px; line-height: 1.2;}
            
            .ibar { padding: 10px 0;}
            .ibar-a { display: block; font-size: 12px; color: #808080; background-color: #F2F0F0; padding: 3px 10px; border-radius: 3px; margin: 0 7px;}

            .comments { background-color: #FFF; border:1px #EEE solid; }
            .UL_list li { position: relative; padding: 7px 10px; height: 32px; border-bottom: 1px solid #EDEDED; background-color: #FAFAFA; }
            .Li_p { margin-left: 36px;}
            .fwrap { padding: 7px 10px; position: relative;}
            .editor { resize: none; outline: none; width: 392px; height: 23px; padding: 3px; font-size: 12px; line-height: 1.1em; border: 1px solid #DDD; overflow: hidden; background: whiteSmoke;-webkit-transition: width .25s ease-in-out;-moz-transition: width .25s ease-in-out;
transition: width .25s ease-in-out;}
            .subbtn { display: block;  position: absolute; right: 15px; top: 10px; height: 25px; line-height: 25px;}
            /*图片上悬浮按钮*/
            .mask { display: block; position: absolute; z-index: -1; background-color: #000; filter:alpha(opacity=50); opacity: 0.5; width: 100%; height: 100%;}
            .tabsWrap { position: absolute; z-index: 999; bottom: 0px; left: 0; height: 0px; width: 420px;}
            .tabs { position: absolute;}
            .tabs > ul li { float: left; height: 40px; width: 140px; text-align: center;}
            .tabs > ul li a { display: block; padding-top: 10px; width: 100%; color: #DDD; height: 30px; box-shadow: 1px 0 0 rgba(255, 255, 255, 0.15) inset,-1px 0 0 black inset,0 2px 0 rgba(255, 255, 255, 0.15),0 1px 0 black;}
        
            .like ul li { float: left; margin-left: 3px;}
            .share ul li { float: left; width: 25%;  font-size: 14px; height: 40px; line-height: 40px; text-align: center;}
            .share ul li a {color: #DDD;}
            .doLoading { height: 40px; background: url(/src/images/loading2.gif) no-repeat center center scroll;}
        </style>
    </head>  
    <body>
        <input name="cateId" type="hidden" value="${cateId}" />
        <div class="le-header">
            <div class="le-iupload">
                <a href="javascript:;">
                    <img src="/src/images/upload_btn.png" alt="" />
                </a>
            </div>
        </div>
        <div class="le-content">
            <div id="navbar" class="navbarwrap">
                <div class="navbar clearfix">
                    <div class="lside fl">
                        <form action="" method="get">
                            <input autocomplete="off" class="sbox" name="kw" type="text" value="搜搜看吧" onfocus="if(this.value=='搜搜看吧'){this.value='';};" onblur="if(this.value==''){this.value='搜搜看吧';};" x-webkit-speech />
                            <input class="sbtn" type="submit" value="搜搜" />
                        </form>
                    </div>
                    <div class="rside fr">
                        <ul>
                            <li><a href="/login.html">登录</a></li>
                            <li><a href="/register.html">注册</a></li>
                        </ul>
                    </div>
                    <div class="mside">
                        <ul>
                            <li <#if cateId == 1>class="selected"</#if>><a href="/index.html?cateId=1">搞笑</a></li>
                            <li <#if cateId == 2>class="selected"</#if>><a href="/index.html?cateId=2">萌宠</a></li>
                            <li <#if cateId == 3>class="selected"</#if>><a href="/index.html?cateId=3">童真</a></li>
                            <li <#if cateId == 4>class="selected"</#if>><a href="/index.html?cateId=4">美女</a></li>
                            <li><a href="/index.html?cateId=5">随便看看</a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="body-wrap">
                <div id="wf"></div>
            </div>
        </div>
        <script id="view" type="text/html">
            <div class="item" id="item_{id}">
                <div class="">
                    <div class="iTop clearfix" style="position:relative;">
                        <img width="36" height="36" src="http://imgf.lefoto.me{face}" />
                        <p>{userName}</p>
                        <p>搞笑00000+10086</p>
                        <a href="javascript:;" class="Es" style="z-index:999;">
                            <span class="num">{commentCount}</span>
                        </a>
                        <#if delete == 1>
                            <a style="position:absolute; display: block; background-color: green; color: #FFF; padding: 5px 10px; top: 70px; right: 0px;" href="javascript:deleteImg({id},${cateId})">删除</a>
                        </#if>
                    </div>
                    <div class="iMid" style="height:{height}px; position: relative;">
                        <a class="img" href="#" style="height:{height}px; display:block;">
                            <img height="{height}" style="max-width: 420px; opacity: 0.2; fliter:alpha(opacity=20);" src="http://img.lefoto.me{url}" onload="imgShow(this);" />
                        </a>
                        <div class="tabsWrap">
                            <div class="mask"></div>
                            <div class="tabs">
                                <ul class="tabAs clearfix">
                                    <li>
                                        <a class="icon comment" href ="javascript:;"><span>评论</span></a>
                                    </li>
                                    <li>
                                        <a class="icon like" href="javascript:;"><span>喜欢</span></a>
                                    </li>
                                    <li>
                                        <a class="icon share" href="javascript:;"><span>分享</span></a>
                                    </li>
                                </ul>
                                <div class="hide share">
                                    <ul class="clearfix">
                                        <li>
                                            <a href="javascript:;">weibo</a>
                                        </li>
                                        <li>
                                            <a href="javascript:;">renren</a>
                                        </li>
                                        <li>
                                            <a href="javascript:;">QQ</a>
                                        </li>
                                        <li>
                                            <a href="javascript:;">douban</a>
                                        </li>
                                    </ul>
                                </div>
                                <div class="doLoading hide"></div>
                            </div>
                        </div>
                    </div>
                    <!--
                    <a style="height:{height}px;" class="iMid" href="javascript:;">
                        <img height="{height}" style="max-width: 420px; opacity: 0.2; fliter:alpha(opacity=20);" src="http://lefoto.b0.upaiyun.com{url}" onload="imgShow(this);" />
                    </a>
                    <div class="ibar clearfix">
                        <a class="ibar-a fr" href="javascript:;">
                            <span>喜欢</span>
                        </a>
                        <a class="ibar-a fr" href="javascript:;">
                            <span>采集</span>
                        </a>
                        <a class="ibar-a fr" href="#" onclick="return toList('{id}')">
                            <span>评论</span>
                        </a>
                    </div>-->
<!--                    <div id="i_{id}" class="comments" style="display:none;">
                        <ul class="UL_list">
                            <li>
                                <a class="fl" href="">
                                    <img style="height:32px; width: 32px;" src="" />
                                </a>
                                <p class="Li_p">。。。。。。。hahh哈哈</p>
                            </li>
                            <li>
                                <a class="fl" href="">
                                    <img style="height:32px; width: 32px;" src="" />
                                </a>
                                <p class="Li_p">哦哦哦哦哦 a amamamm</p>
                            </li>
                        </ul>
                        <div class="fwrap">
                            <form>
                                <textarea class="editor" name="" ></textarea>
                                <a href="#" class="subbtn">评论</a>
                            </form>
                        </div>
                    </div>-->
                </div>
            </div>
        </script>
        <script src="/src/js/waterFall-1.0.1.js?v=0.0.0.1" type="text/javascript"></script>
        <script src="/src/js/sy.js?v=0.0.0.1" type="text/javascript"></script>
        <script type="text/javascript">
            function deleteImg(id){
                var data = {
                    photoId:id,
                    cateId: $('input[name=cateId]').val()
                };
                var url = '/photo/deletePhotoByAdmin.html';
                $.post(url,data,function(response){
                    if(response == 'success'){
                        $('#item_'+id).remove();
                    }else{
                        alert('删除失败');
                    }
                });
            }
            $('#navbar').scrollFix();
            new waterFall({
                id: 'wf',//瀑布流ID
                url:'/index/getPhoto.html',//数据请求接口，返回json格式
                size: 10,//每次请求要加载的数据条数
                colWidth: 460,//列宽
                colAmount: 2,//列数
                view:'view',
                params: { //请求数据时可向服务器发送附带参数
                    cateId: $('input[name=cateId]').val(),
                    type: 2
                }
            }).show();
        </script>
        
    </body>  
</html>  