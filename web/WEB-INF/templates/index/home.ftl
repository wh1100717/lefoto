<!DOCTYPE html>
<html>  
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta property="wb:webmaster" content="6af8d63927570237" />
        <title>Lefoto</title>
        <script src="${RESOURCE_DIR}/src/js/jquery-1.8.0.min.js" type="text/javascript"></script>
        <script src="${RESOURCE_DIR}/src/js/common.js" type="text/javascript"></script>
        <!--        <script src="http://tjs.sjs.sinajs.cn/open/api/js/wb.js?appkey=434637113" type="text/javascript" charset="utf-8"></script>-->
        <link href="${RESOURCE_DIR}/src/css/common2.css" rel="stylesheet" />
        <link href="${RESOURCE_DIR}/src/css/sy.css" rel="stylesheet" />
        <style type="text/css">
            .ibar { padding: 10px 0;}
            .ibar-a { display: block; font-size: 12px; color: #808080; background-color: #F2F0F0; padding: 3px 10px; border-radius: 3px; margin: 0 7px;}
            .comments { background-color: #FFF; border:1px #EEE solid; margin-top:20px;}
            .fwrap,.list .comLi { padding: 7px; }
            .list .comLi { position: relative; border-bottom: 1px solid #EDEDED; background-color: #FAFAFA; }

            .fwrap { overflow: hidden;}
            .fwrap .f { position: relative; overflow: hidden; height: 50px; }
            .f .editor { float:right; font-family: "微软雅黑"; resize: none; outline: none; width: 288px; border: 1px solid #DDD; height: 40px; padding: 3px; padding-right:56px;  font-size: 12px; line-height: 1.5; background: whiteSmoke;}
            .f a.subbtn { display: block; background-color: #EEE; padding-left: 7px; width: 46px; position: absolute; right: 1px; top: 1px; height: 46px; line-height: 46px;}
            /*hover action tabs*/
            .mask { display: block; position: absolute; z-index: -1; background-color: #000; filter:alpha(opacity=50); opacity: 0.5; width: 100%; height: 100%;}
            .tabsWrap { position: absolute; z-index: 999; bottom: 0px; left: 0; height: 0px; width: 420px;}
            .tabs { position: absolute;}
            .tabs > ul li { float: left; height: 40px; width: 140px; }
            .tabs > ul li a { display: block; padding-top: 10px; width: 100%; color: #DDD; height: 30px; box-shadow: 1px 0 0 rgba(255, 255, 255, 0.15) inset,-1px 0 0 black inset,0 2px 0 rgba(255, 255, 255, 0.15),0 1px 0 black;}
            .tabs > ul li a:hover { background-color: rgba(0,0,0,0.6);}

            .like ul li { float: left; margin-left: 3px;}
            .share ul li { float: left; width: 25%;  font-size: 14px; height: 40px; line-height: 40px; text-align: center;}
            .share ul li a {color: #DDD;}
            .tabs .loading, .comments .loading,.list li.loading { height: 40px; background: url(${RESOURCE_DIR}/src/images/loading2.gif) transparent no-repeat center center scroll;}

            /*navmenu downlist*/
            .isList { position: relative;}
            .isList ul { display: block; background-color:#444; position: absolute; top: 40px; left: 0; z-index: 9999; width: 150px;}
            .isList ul li { height: 30px; line-height: 30px; width:100%; padding: 0; margin: 0;}
            .isList ul li a { display: block; height: 100%; width: 100%;}
            .isList ul li a:hover { background-color:#333;}

            /*le一下*/
            .isLike a.leA { background-image: url(${RESOURCE_DIR}/src/images/smile_pink50.png) !important;}
            .leMid {position: absolute; z-index: 1000; background:url(${RESOURCE_DIR}/src/images/like_bg.png) }
            .leA {overflow:hidden; color: #DDD; text-indent: 500px; display: block; height: 100px; width: 100px; line-height: 100px; text-align: center; background: url(${RESOURCE_DIR}/src/images/smile_white50.png) transparent no-repeat scroll center center;}

            .downEle { position: absolute; z-index: 9999; border-bottom: 10px; -webkit-transition: -webkit-transform 0.3s ease-in-out; height: 50px; width: 50px; background-color: green;  transform: rotate(-60deg) scale(0.5);}
            /*tab icon*/
            a.comment { background:url(${RESOURCE_DIR}/src/images/icon_comment.png) transparent center center scroll no-repeat;}
            a.like { background:url(${RESOURCE_DIR}/src/images/icon_like.png) transparent center center scroll no-repeat;}
            a.share { background:url(${RESOURCE_DIR}/src/images/icon_zhuancai.png) transparent center center scroll no-repeat;}
            a.icon { position:relative;}
            a.icon span { position:absolute; top:10px; right:90px; display:inline-block; font-size:16px; font-weight:800; color:#999; text-align:right;}

            /*avatar*/
            .lcol .iTop a.tx,.rcol .iTop a.tx { position:absolute;  }
            .iTop a.tx img {border-radius: 5px;}
            .lcol .iTop a.tx{  left:-90px; }
            .rcol .iTop a.tx{  right:-90px;}

            /*login*/
            .login { position:absolute; z-index:1000; width:365px; background-color:#FFF; right:-20px; top:40px; border:rgb(51,44,43) solid; border-width:0 2px 2px 2px;}
            .loginH { padding:10px 0 0 20px;}
            .loginB { padding:7px;}
            .loginB a { float:right; display:block; height:28px; padding:0 15px; line-height:28px; background-color:#DDD; margin-left:5px;}
            .loginB a.dealing { background-color:#808080; color:#CCCCCC; cursor:default;}

            .login ul li { margin-bottom:10px;}
            .login li.li-last {height:22px;line-height:22px;}
            .login li.li-last span { display:inline-block;  padding-left:16px; margin-right:30px; }
            .login li.li-last a { color:#808080;}
            .login li.li-last span input { margin-left:-14px; }
            .loginH input.in { height:28px; padding-left:3px; border:1px #CCC solid; width:320px;}
            .loginH p.desc { margin-bottom:7px; font-size:14px; font-weight:800;}
            .login .loginH .waring { color:red; font-size:12px; font-weight:400; margin-left:30px;}


            p.Li_p { margin-left: 40px; width:300px;font-weight:800; line-height:1.5;}

            li.comLi { position:relative;}
            li.comLi .img36 { height:36px; width:36px;}
            li.comLi .at { overflow:hidden; text-indent:-500px; display:none; position:absolute; top:9px; right:10px; height:36px; width:36px; background:url(${RESOURCE_DIR}/src/images/at.png) scroll 0 0 transparent no-repeat;}
            li.comLi:hover .at { display:block;}
            li.comLi .at:hover {background-image:url(${RESOURCE_DIR}/src/images/at_hover.png);}


            /*has login*/
            .rside a.tx { display:block; margin-right:20px;}
        </style>
        <script type="text/javascript">
            var user;
        </script>
        <#if user != null>
        <script type="text/javascript">
            user = {
                id:'${user.id}',
                name:'${user.userName}',
                face:'http://imgf.lefoto.me/${user.face}'
            }
        </script>
        </#if>
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
                <!--
                <div class="lside fl">
                    <form action="" method="get">
                        <input autocomplete="off" class="sbox" name="kw" type="text" value="搜搜看吧" onfocus="if(this.value=='搜搜看吧'){this.value='';};" onblur="if(this.value==''){this.value='搜搜看吧';};" x-webkit-speech />
                        <input class="sbtn" type="submit" value="搜搜" />
                    </form>
                </div>
                -->
                <div class="rside fr">
                    <ul id="_in">
                        <li><a onclick="weiboLogin()"><img src="http://www.sinaimg.cn/blog/developer/wiki/LOGO_32x32.png"/></a></li>
                        <li><a class="G" href="javascript:;">登录</a></li>
                        <li><a href="/register.html">注册</a></li>
                    </ul>
                    <div class="login" style="display:none;">
                        <form id="_lf">
                            <div class="loginH">
                                <ul>
                                    <li>
                                        <p class="desc">用户名或邮箱<span id="loginMsg" class="waring"></span></p>
                                        <input class="in noime" name="email" type="text" />
                                    </li>
                                    <li>
                                        <p class="desc">密码</p>
                                        <input class="in noime" name="password" type="password" />
                                    </li>
                                    <li class="li-last">
                                        <span><input name="auto" type="checkbox" checked="true" />自动登录</span>
                                        <a href="">忘记密码？</a>
                                    </li>
                                </ul>
                            </div>
                            <div class="loginB clearfix">
                                <a href="javascript:;" class="btn-ok">确定</a>
                                <a class="cancel" href="javascript:;">取消</a>
                            </div>
                        </form>
                    </div>
                    <!--
                    <a href="javascript:;" class="tx"><img style="height:24px; width:24px;" src="http://imgf.lefoto.me/$!{user.face}" /></a>-->
                    <div class="info hide">
                        <h3>
                            <a href="javascript:;">
                                <img style="height:48px; width:48px;" src="http://imgf.lefoto.me/$!{user.face}" />
                                <span>$!{user.name}</span>
                            </a>
                        </h3>
                        <ul>
                            <li><a href="javascript:;">个人主页</a></li>
                            <li><a href="javascript:;">设置</a></li>
                            <li class="line"></li>
                            <li><a href="javascript:;">退出</a></li>
                        </ul>
                    </div>
                </div>
                <div class="mside">
                    <ul>
                        <li <#if cateId == 2>class="selected"</#if>><a href="/index.html?cateId=2&type=2">萌宠</a></li>
                        <li <#if cateId == 1>class="selected"</#if>><a href="/index.html?cateId=1&type=2">搞笑</a></li>
                        <li <#if cateId == 3>class="selected"</#if>><a href="/index.html?cateId=3&type=2">童真</a></li>
                        <li <#if cateId == 4>class="selected"</#if>><a href="/index.html?cateId=4&type=2">美女</a></li>
                        <li <#if cateId == 5>class="selected"</#if>><a href="/index.html?cateId=5&type=2">漫画</a></li>
                        <li class="isList">
                            <a href="/index.html?cateId=${cateId}&type=2">随便看看</a>
                            <ul style="display:none;">
                                <li><a href="/index.html?cateId=${cateId}&type=0">最新图片</a></li>
                                <li><a href="/index.html?cateId=${cateId}&type=1">最热图片</a></li>
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
    <!--评论部分模板-->
    <script id="comment_temp" type="text/html">
        <li class="comLi clearfix">
            <a class="fl" href="">
                <img class="img36" src="http://imgf.lefoto.me{userFace}" />
            </a>
            <p class="Li_p"><a class="un">{userName}</a>: {content}</p>
            <a href="javascript:;" title="@Ta一下吧" class="at">{userName}</a>
        </li>
    </script>
    <!--瀑布流图片展示模板-->
    <script id="view" type="text/html">
        <div class="item" id="item_{id}">
            <div style="padding-bottom:50px;">
                <div class="iTop clearfix" style="position:relative;">
                    <a class="tx" title="{userName}"><img width="60" height="60" src="http://imgf.lefoto.me{face}" /></a>
                    <p>{description}</p>
                    <#if delete == 1>
                    <a style="z-index:9;position:absolute; display: block; background-color: green; color: #FFF; padding: 5px 10px; top: 70px; right: 0px;" href="javascript:deleteImg({id},${cateId})">删除</a>
                    </#if>
                </div>
                <div class="iMid" style="height:{height}px; position: relative;">
                    <a class="img" href="#" style="height:{height}px; display:block;">
                        <img height="{height}" style="max-width: 420px; opacity: 0.2; fliter:alpha(opacity=20);" src="http://img{url_tag}.lefoto.me{url}" onload="imgShow(this);" />
                    </a>
                    <div class="leMid" style="display:none;">
                        <a rel="{id}" data-up="{up}" class="leA"></a>
                    </div>
                    <div class="tabsWrap">
                        <div class="mask"></div>
                        <div class="tabs">
                            <ul class="tabAs clearfix">
                                <li>
                                    <a rel="{id}" class="icon comment" href ="javascript:;"><span>{commentCount}</span></a>
                                </li>
                                <li>
                                    <a rel="{id}" class="icon like" href="javascript:;"><span>{upCount}</span></a>
                                </li>
                                <li>
                                    <a rel="{id}" class="icon share" href="javascript:;"><span>{forwardCount}</span></a>
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
                            <div class="loading hide"></div>
                        </div>
                    </div>
                </div>
                <div class="comments" style="display:none;">
                    <ul class="list"></ul>
                    <div class="fwrap">
                        <div class="f">
                            <img style="height:48px; width:48px; position:absolute;" src="http://imgf.lefoto.me/$!{user.face}" />
                            <textarea spellcheck="false" class="editor" name="content" ></textarea>
                            <a rel="{id}" href="javascript:;" class="subbtn btn-addComment">评论</a>
                            <input name="pid" type="hidden" value="{id}" />
                            <input name="userId" type="hidden" value="{userId}" />
                            <ul class="atUL" style="display: none;">
                                <li>
                                    <p>{name}</p>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </script>
    <script src="${RESOURCE_DIR}/src/js/waterFall-1.0.1.js?v=0.0.0.2" type="text/javascript"></script>
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
                    $('#item_'+id).fadeOut(500);
                }else{
                    alert('删除失败');
                }
            });
        }
            
        $('#navbar').scrollFix();
        new waterFall({
            id: 'wf',//瀑布流ID
            url:'/photo/getPhotos.html',//数据请求接口，返回json格式
            size: 10,//每次请求要加载的数据条数
            colWidth: 450,//列宽
            colAmount: 2,//列数
            view:'view',
            params: { //请求数据时可向服务器发送附带参数
                cateId: $('input[name=cateId]').val(),
                type: $('input[name=type]').val()
            }
        }).show();
        $('#wf .col').eq(0).addClass('lcol');
        $('#wf .col').eq(1).addClass('rcol');
        function weiboLogin(){
            WB2.login(function(){
                //callback function
            });
        }
    </script>
    <#include "/layout/foot.ftl">
</body>  
</html>  
