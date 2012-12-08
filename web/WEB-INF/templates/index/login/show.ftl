<html>
    <head>
        <title>Lefoto · Login</title>
        <!-- Meta Tags -->
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <!-- CSS -->
        <link href="https://secure.wufoo.com/stylesheets/admin/css/index.607.css" rel="stylesheet">
        <link href="https://secure.wufoo.com/stylesheets/system/login/css/index.607.css" rel="stylesheet">
    </head>

    <body id="public" class="login">
        <div id="overlay"></div>
        <div id="lightbox" class="done" style="visibility: visible; margin-top: -210.5px; margin-left: -411px;">
            <div id="lbContent" class="clearfix">

                <form id="loginForm" name="f" action="/login/submit.html" method="post">
                    <div class="info">
                        <h2>Lefoto Welcomes You!</h2>
                        <div>Hey friend, please log in.</div>
                    </div>

                    <ul>
                        <li><div>
                                <label class="desc" for="email">Email Address</label>
                                <input id="email" name="email" class="field text large" type="text" value="">
                            </div></li>

                        <li><div>
                                <label class="desc" for="password">Password</label>
                                <input id="password" name="password" class="field text large" type="password" value="">
                            </div></li>
                        <li><span>
                                <input id="remember" name="remember" class="checkbox field" type="checkbox" value="1">
                                <label class="choice" for="remember">Remember me.</label>
                            </span></li>
                        <li>
                            <button type="submit" id="saveForm" class="button">
                                <img src="https://secure.wufoo.com/images/icons/door_in.png" alt=""> Login</button>
                            <a href="http://www.wufoo.com" class="negative button">
                                <img src="https://secure.wufoo.com/images/icons/cross.png" alt=""> Cancel</a>
                        </li>
                    </ul>

                    <br>

                    <a href="https://secure.wufoo.com/login/forgot/" title="Don't worry, we'll get you a new one.">
                        Forget your password?</a>

                    | 

                    <a href="https://secure.wufoo.com/docs/login/" title="Read our Login FAQ" target="_blank">
                        Login FAQ</a>
                </form>

                <div id="noaccount">
                    <a href="/register/show.html">
                        <h2>Don't have an account? <b>No worries.</b><span>Create one now.</span> <b>It's FREE!</b></h2>
                    </a>
                </div>
            </div>
        </div>
    </body>
</html>