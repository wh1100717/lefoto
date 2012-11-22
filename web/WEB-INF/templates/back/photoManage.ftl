<!doctype html>
<html lang="en">
    <head><#include "/back/layout/head.ftl"></head>
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
            <h4 class="alert_warning">A Warning Alert</h4>
            <h4 class="alert_error">An Error Message</h4>
            <h4 class="alert_success">A Success Message</h4>
            <article class="module width_full">
                <header><h3>Basic Styles</h3></header>
                <div class="module_content">
                    <h1>Header 1</h1>
                    <h2>Header 2</h2>
                    <h3>Header 3</h3>
                    <h4>Header 4</h4>
                    <p>Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Cras mattis consectetur purus sit amet fermentum. Maecenas faucibus mollis interdum. Maecenas faucibus mollis interdum. Cras justo odio, dapibus ac facilisis in, egestas eget quam.</p>
                    <p>Donec id elit non mi porta <a href="#">link text</a> gravida at eget metus. Donec ullamcorper nulla non metus auctor fringilla. Cras mattis consectetur purus sit amet fermentum. Aenean eu leo quam. Pellentesque ornare sem lacinia quam venenatis vestibulum.</p>
                    <ul>
                        <li>Donec ullamcorper nulla non metus auctor fringilla. </li>
                        <li>Cras mattis consectetur purus sit amet fermentum.</li>
                        <li>Donec ullamcorper nulla non metus auctor fringilla. </li>
                        <li>Cras mattis consectetur purus sit amet fermentum.</li>
                    </ul>
                </div>
            </article><!-- end of styles article -->
            <div class="spacer"></div>
        </section>
    </body>
</html>