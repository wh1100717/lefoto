<!DOCTYPE html>
<html>  
    <head>
        <#include "/back/layout/head.ftl">
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
            .tabs > ul li a { display: block; background-color: black; padding-top: 10px; width: 100%; color: #DDD; height: 30px; box-shadow: 1px 0 0 rgba(255, 255, 255, 0.15) inset,-1px 0 0 black inset,0 2px 0 rgba(255, 255, 255, 0.15),0 1px 0 black;}
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


            p.Li_p { margin-left: 40px; width:300px;font-weight:800; line-height:1.5;}

            li.comLi { position:relative;}
            li.comLi .img36 { height:36px; width:36px;}
            li.comLi .at { overflow:hidden; text-indent:-500px; display:none; position:absolute; top:9px; right:10px; height:36px; width:36px; background:url(${RESOURCE_DIR}/src/images/at.png) scroll 0 0 transparent no-repeat;}
            li.comLi:hover .at { display:block;}
            li.comLi .at:hover {background-image:url(${RESOURCE_DIR}/src/images/at_hover.png);}


            /*has login*/
            .rside a.tx { display:block; margin-right:20px;}
        </style>
    </head>  
    <body>
        <header id="header">
            <hgroup>
                <h1 class="site_title"><a href="index.html">Lefoto Back Manage</a></h1>
                <h2 class="section_title">Grab Photo Manage</h2>
                <div class="btn_view_site"><a href="/index.html">Lefoto</a></div>
            </hgroup>
        </header>
        <!-- end of header bar -->
        <section id="secondary_bar">
            <div class="user">
                <p>Eric (<a href="#">3 Messages</a>)</p>
                <!-- <a class="logout_user" href="#" title="Logout">Logout</a> -->
            </div>
            <div class="breadcrumbs_container">
                <article class="breadcrumbs">
                    <a href="index.html">Website Admin</a>
                    <div class="breadcrumb_divider"></div>
                    <a class="current">GrabPhotoManage</a>
                </article>
            </div>
        </section><!-- end of secondary bar -->
        <#include "/back/layout/aside.ftl"> 

        <section id="main" class="column">
            <div class="le-content">
                <div id="navbar" class="navbarwrap">
                </div>
                <div class="body-wrap">
                    <div id="wf"></div>
                </div>
            </div>
        </section>

        <!--瀑布流图片展示模板-->
        <script id="view" type="text/html">
            <div class="item" id="item_{id}">
                <div style="padding-bottom:50px;">
                    <div class="iMid" style="height:{height}px; position: relative;">
                        <a class="img" href="#" style="height:{height}px; display:block;">
                            <img height="{height}" style="max-width: 420px; opacity: 0.2; fliter:alpha(opacity=20);" src="http://img{url_tag}.lefoto.me{url}" onload="imgShow(this);" />
                        </a>
                        <div class="tabsWrap">
                            <div class="mask"></div>
                            <div class="tabs">
                                <ul class="tabAs clearfix">
                                    <li style="width:70px"><a rel="{id}" href ="javascript:changeType({id},2);"><span>萌宠</span></a></li>
                                    <li style="width:70px"><a rel="{id}"  href="javascript:changeType({id},1);"><span>搞笑</span></a></li>
                                    <li style="width:70px"><a rel="{id}"  href="javascript:changeType({id},3);"><span>童真</span></a></li>
                                    <li style="width:70px"><a rel="{id}"  href="javascript:changeType({id},4);"><span>美女</span></a></li>
                                    <li style="width:70px"><a rel="{id}"  href="javascript:changeType({id},5);"><span>漫画</span></a></li>
                                    <li style="width:70px"><a rel="{id}"  href="javascript:deleteImg({id});"><span>删除</span></a></li>
                                </ul>
                                <div class="loading hide"></div>
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
                    photoId:id
                };
                var url = '/photo/deleteGrabPhotoByAdmin.html';
                $.post(url,data,function(response){
                    if(response == 'success'){
                        $('#item_'+id).fadeOut(500);
                    }else{
                        alert('删除失败');
                    }
                });
            }
            function changeType(id, cateId){
                var data ={
                    photoId : id,
                    cateId : cateId
                }
                var url = "/photo/changeGrabPhotoTypByAdmin.html";
                $.post(url, data, function(response){
                    if(response == "success"){
                        $("#item_" + id).fadeOut(200);
                    }else{
                        alert("失败");
                    }
                });
            }
            
            $('#navbar').scrollFix();
            new waterFall({
                id: 'wf',//瀑布流ID
                url:'/photo/getGrabPhotos.html',//数据请求接口，返回json格式
                size: 10,//每次请求要加载的数据条数
                colWidth: 450,//列宽
                colAmount: 2,//列数
                view:'view',
                params: { //请求数据时可向服务器发送附带参数
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
