<!doctype html>
<html lang="en">
    <head><#include "/back/layout/head.ftl"></head>
    <body>
        <header id="header">
            <hgroup>
                <h1 class="site_title"><a href="index.html">Lefoto Back Manage</a></h1>
                <h2 class="section_title">Album Manage</h2>
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
                    <a class="current">AlbumManage</a>
                </article>
                <button class="btn btn-mini btn-primary" onclick="showAlbumCreation()" style="float: right; margin: 4px 20px 0 0">创建相册</button>
            </div>
        </section><!-- end of secondary bar -->
        <#include "/back/layout/aside.ftl">

        <section id="main" class="column">
            <!-- 创建分类 -->
            <article id="albumCreation"class="module" style="display: none;float: right;width: 200px;height: 50px;">
                <form class="submit_link" action="/back/album/add.html" method="post">
                    <input type="text" name ="albumName" style="width: 100px;margin-top: 7px;">
                    <input type="submit" value="创建" class="alt_btn">
                </form>
            </article>
            <div class="clear"></div>
            <!-- END创建分类 -->
            
            <#if message == "">
            <h4 class="alert_info">Welcome to the Album admin panel template, this place will be userd for delivering the target information.</h4>
            <#else>
            <h4 class="alert_warning">${message}</h4>
            </#if>
            
            <article class="module width_full">
                <header><h3 class="tabs_involved">相册管理</h3>
                    <ul class="tabs">
                        <li><a href="#tab1">Posts</a></li>
                        <li><a href="#tab2">Comments</a></li>
                    </ul>
                </header>

                <div class="tab_container">
                    <div id="tab1" class="tab_content">
                        <table class="tablesorter" cellspacing="0"> 
                            <thead> 
                                <tr> 
                                    <th></th> 
                                    <th>相册名</th> 
                                    <th>分类</th> 
                                    <th>创建时间</th> 
                                    <th>操作</th> 
                                </tr> 
                            </thead> 
                            <tbody> 
                                <#list albums as album>
                                <tr>
                                    <td><input type="checkbox"></td>
                                    <td>${album.name}</td>
                                    <td>${album.category_id}</td>
                                    <td>${album.create_time}</td>
                                    <td>
                                        <input type="image" src="/src/plugins/backtemplate/images/icn_edit.png" title="Edit">
                                        <input class="albumDelete" value="${album.name}" type="image" src="/src/plugins/backtemplate/images/icn_trash.png" title="Trash">
                                    </td> 
                                </tr> 
                                </#list>
                            </tbody> 
                        </table>
                    </div><!-- end of #tab1 -->

                </div><!-- end of .tab_container -->

            </article><!-- end of content manager article -->
        </section>
        <script	type="text/javascript">
            function showAlbumCreation(){
                if($("#albumCreation").is(":visible")){
                    $("#albumCreation").fadeOut('slow');
                }else{
                    $("#albumCreation").fadeIn('slow');
                }
            }
            $(document).ready(function(){
                $('.albumDelete').click(function(){
                    var albumName = $(this).val();
                    var data = { albumName:albumName};
                    $.confirm({
                        'title'		: 'Delete Confirmation',
                        'message'	: 'You are about to delete this item. <br />It cannot be restored at a later time! Continue?',
                        'buttons'	: {
                            'Yes'	: {
                                'class'	: 'blue',
                                'action': function(){
                                    $.ajax({
                                        type:'post',//可选get
                                        url:'/back/album/delete.html',//这里是接收数据的PHP程序
                                        data : data,
                                        success:function(msg){
                                            //这里是ajax提交成功后，PHP程序返回的数据处理函数。msg是返回的数据，数据类型在dataType参数里定义！
                                            location.href ="/back/albumManage.html"
                                        },
                                        error:function(){
                                        }
                                    })
                                }
                            },
                            'No'	: {
                                'class'	: 'gray',
                                'action': function(){}	// Nothing to do in this case. You can as well omit the action property.
                            }
                        }
                    });
                });
            });
        </script>
    </body>
</html>