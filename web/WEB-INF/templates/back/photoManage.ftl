<!doctype html>
<html lang="en">
    <head>
        <link rel="stylesheet" type="text/css" href="/lefoto/src/plugins/uploadifive/uploadifive.css">
        <#include "/back/layout/head.ftl">
        <script src="/lefoto/src/plugins/uploadifive/jquery.uploadifive.min.js" type="text/javascript"></script>
    </head>
    <body>
        <header id="header">
            <hgroup>
                <h1 class="site_title"><a href="index.html">Lefoto Back Manage</a></h1>
                <h2 class="section_title">Photo Manage</h2>
                <div class="btn_view_site"><a href="/lefoto/index/show.html">Lefoto</a></div>
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
                    <a class="current">PhotoManage</a>
                </article>
            </div>
        </section><!-- end of secondary bar -->
        <#include "/back/layout/aside.ftl"> 
        <section id="main" class="column">
            <form>
                <input id="file_upload" type="file" name="file_upload" multiple="true"/>
            </form>
        </section>
        <script	type="text/javascript">
            $(function() {
                $('#file_upload').uploadifive({
                    'uploadScript' : '/lefoto/back/photo/upload.html'
                    // Put your options here
                });
            });
        </script>
    </body>
</html>