<!DOCTYPE html>
<html>  
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">  
        <title>Lefoto</title>
        <script src="${RESOURCE_DIR}/src/js/jquery-1.8.0.min.js" type="text/javascript"></script>
        <link href="${RESOURCE_DIR}/src/css/common2.css" rel="stylesheet" />
        <link href="${RESOURCE_DIR}/src/css/sy.css" rel="stylesheet" />
        <style type="text/css">
            .ibar { padding: 10px 0;}
            .ibar-a { display: block; font-size: 12px; color: #808080; background-color: #F2F0F0; padding: 3px 10px; border-radius: 3px; margin: 0 7px;}
            .comments { background-color: #FFF; border:1px #EEE solid; }
            .UL_list li { position: relative; padding: 7px 10px; height: 32px; border-bottom: 1px solid #EDEDED; background-color: #FAFAFA; }
            .Li_p { margin-left: 36px;}
            .fwrap { padding: 7px 10px; position: relative;}
            .editor { resize: none; outline: none; width: 392px; height: 23px; padding: 3px; font-size: 12px; line-height: 1.1em; border: 1px solid #DDD; overflow: hidden; background: whiteSmoke;-webkit-transition: width .25s ease-in-out;-moz-transition: width .25s ease-in-out;
transition: width .25s ease-in-out;}
            .subbtn { display: block;  position: absolute; right: 15px; top: 10px; height: 25px; line-height: 25px;}
            /*hover action tabs*/
            .mask { display: block; position: absolute; z-index: -1; background-color: #000; filter:alpha(opacity=50); opacity: 0.5; width: 100%; height: 100%;}
            .tabsWrap { position: absolute; z-index: 999; bottom: 0px; left: 0; height: 0px; width: 420px;}
            .tabs { position: absolute;}
            .tabs > ul li { float: left; height: 40px; width: 140px; text-align: center;}
            .tabs > ul li a { display: block; padding-top: 10px; width: 100%; color: #DDD; height: 30px; box-shadow: 1px 0 0 rgba(255, 255, 255, 0.15) inset,-1px 0 0 black inset,0 2px 0 rgba(255, 255, 255, 0.15),0 1px 0 black;}
        
            .like ul li { float: left; margin-left: 3px;}
            .share ul li { float: left; width: 25%;  font-size: 14px; height: 40px; line-height: 40px; text-align: center;}
            .share ul li a {color: #DDD;}
            .doLoading { height: 40px; background: url(${RESOURCE_DIR}/src/images/loading2.gif) no-repeat center center scroll;}
            
            /*navmenu downlist*/
            .isList { position: relative;}
            .isList ul { display: block; position: absolute; top: 40px; left: 0; z-index: 9999; width: 150px;}
            .isList ul li { height: 30px; line-height: 30px; background-color: #558000; width:100%; padding: 0; margin: 0;}
            .isList ul li a { display: block; height: 100%; width: 100%;}
            .isList ul li a:hover { background-color: #408000;}
        </style>
    </head>  
    <body>
        <input name="cateId" type="hidden" value="${cateId}" />
        <input name="type" type="hidden" value="${type}" />
        <div class="le-header">
            <div class="le-iupload">
                <a href="javascript:;">
                    <img src="${RESOURCE_DIR}/src/images/upload_btn.png" alt="" />
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
                            <li class="isList">
                                <a href="/index.html?cateId=${cateId}&&type=2">随便看看</a>
                                <ul style="display:none;">
                                    <li><a href="/index.html?cateId=${cateId}&&type=0">最新图片</a></li>
                                    <li><a href="/index.html?cateId=${cateId}&&type=1">最热图片</a></li>
                                </ul>
                            </li>
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
                <div style="padding-bottom:50px;">
                    <div class="iTop clearfix" style="position:relative;">
                        <img width="36" height="36" src="http://imgf.lefoto.me{face}" />
                        <p>{userName}</p>
                        <p>搞笑00000+10086</p>
                        <a href="javascript:;" class="Es" style="z-index:999;">
                            <span class="num">{commentCount}</span>
                        </a>
                        <#if delete == 1>
                            <a style="z-index:999;position:absolute; display: block; background-color: green; color: #FFF; padding: 5px 10px; top: 70px; right: 0px;" href="javascript:deleteImg({id},${cateId})">删除</a>
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
                                        <a class="icon comment" href ="javascript:;"><span>采集</span></a>
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
        <script src="${RESOURCE_DIR}/src/js/waterFall-1.0.1.js?v=0.0.0.1" type="text/javascript"></script>
        <script src="${RESOURCE_DIR}/src/js/sy.js?v=0.0.0.1" type="text/javascript"></script>
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
                    type: $('input[name=type]').val()
                }
            }).show();
        </script>
        
    </body>  
</html>  
