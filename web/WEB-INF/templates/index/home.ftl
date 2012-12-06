<!DOCTYPE html>
<html>  
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">  
        <title>Lefoto</title>
        <link href="/lefoto/src/css/common2.css" rel="stylesheet" />
        <script src="/lefoto/src/js/jquery-1.8.0.min.js" type="text/javascript"></script>
        <script src="/lefoto/src/js/waterFall-1.0.1.js?v=0.0.0.1" type="text/javascript"></script>
        <script src="/lefoto/src/js/sy.js" type="text/javascript"></script>
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

            /*瀑布流start*/
            .waterFall { display: block; width: 960px; margin: 0 auto; margin-top: 30px; position: relative; }
            .col { float: left; overflow-x: hidden; }
            .detectDiv { clear: both; text-align: center; color: #000; height: 32px; line-height: 32px; padding-bottom: 20px; }
            .loading { display: inline-block; padding-left: 32px; background: url(/lefoto/src/images/loading2.gif) 0 0 scroll transparent no-repeat; }
            /*瀑布流end*/
            .iTop { padding: 7px 0 20px 0;}
            .iMid { display: block; text-align: center;}
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
        </style>
    </head>  
    <body>
        <input name="cateId" type="hidden" value="${cateId}" />
        <input name="type" type="hidden" value="${type}" />
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
                <div id="wf"></div>
            </div>
        </div>
        <script id="view" type="text/html">
            <div class="item">
                <div class="">
                    <div class="iTop clearfix">
                        <img width="36" height="36" src="http://le-face.b0.upaiyun.com/{face}" />
                        <p>{userName}</p>
                        <p>搞笑00000+10086</p>
                        <a href="javascript:;" class="Es" style="z-index:9999;">
                            <span class="num">{commentCount}</span>
                        </a>
                    </div>
                    <a style="height:{height}px;" class="iMid" href="javascript:;">
                        <img height="{height}" style="max-width: 420px;" src="http://lefoto.b0.upaiyun.com{url}" />
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
                    </div>
                    <div id="i_{id}" class="comments" style="display:none;">
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
                    </div>
                </div>
            </div>
        </script>
        
        <script type="text/javascript">
            function toList(id){
                var comm_list = $('#i_'+id);
                console.log(comm_list.length);
                if(comm_list.is(':visible')){comm_list.hide();}
                else{
                    comm_list.show();
                }
                return false;
            }
            new waterFall({
                id: 'wf',//瀑布流ID
                url:'/lefoto/index/getPhoto.html',//数据请求接口，返回json格式
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