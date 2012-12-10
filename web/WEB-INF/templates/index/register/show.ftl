<html>
    <head>
        <title>Lefoto Registration</title>
        <meta http-equiv="content-type" content="text/html; charset=utf-8">
        <link rel="stylesheet" href="http://cdnstatic.visualizeus.com/css/reset.v1.css,grid.v1.css,typography.v1.css,base.v2915.less,default.v2951.less" type="text/css" media="screen, projection">
        <meta http-equiv="content-type" content="text/html; charset=utf-8">
    </head>
    <body class="clean" >
        <div class="container">
            <a class="sideLink" href="/login.html">Have an account? <b>Sign in</b></a>
            <div id="smallContent" class="span-6 centered_column textOnly">
                <a id="logo" href="http://vi.sualize.us/" title="Go to the Home page">
                    <img src="http://cdnstatic.visualizeus.com/css/images/logowhiteVisualizeUs.png" width="222" height="59" alt="Visualize Us">
                </a>
                <h1 class="alone">Welcome to Lefoto</h1>
                <h2 class="alone" style="padding:0 3em; margin-top: 0.5em;">Start now to discover new beautiful pictures  and collect your favourites.</h2>
                <div class="social_login">
                    <a class="login facebook button pushable" rel="popup" href="http://vi.sualize.us/login/facebook/?popup">
                        Sign in with Renren           <span class="icons facebook"></span>
                    </a>
                    <a class="login twitter button pushable" rel="popup" href="http://vi.sualize.us/login/twitter/?popup">
                        Sign in with Weibo            <span class="icons twitter"></span>
                    </a>
                    <p>
                        We'll never post without your permission.            <br>
                        <a id="expandTrad" href="http://vi.sualize.us/register/?full">Don't you have social account?</a>
                    </p>
                </div>
                <div class="traditional" style="display: none;">
                    <span class="or">or</span>
                    <form id="loginf" class="wide" action="/register/add.html" method="post" autocomplete="off">
                        <fieldset>
                            <label for="email">Email address</label>
                            <input tabindex="4" type="text" class="txt required " id="email" name="email" size="40" maxlength="40">
                            <ul class="help">
                                <li>your invitation will be sent to this account.</li>
                                <li>We hate spam too.</li>
                            </ul>
                        </fieldset>
                        <fieldset>
                            <label for="username">Nick Name</label>
                            <input tabindex="1" type="text" class="txt required " id="nickName" name="userName" size="22" maxlength="15">
                            <ul class="help">
                                <li id="isValid">use only letters, numbers or underscore</li>
                                <li id="availability"></li>
                            </ul>
                        </fieldset>
                        <fieldset>
                            <label for="password">Password</label>
                            <input tabindex="2" type="password" class="txt required " id="password" name="password" size="20">
                            <ul class="help">
                                <li id="passwordLength">at least 6 characters long</li>
                            </ul>
                        </fieldset>
                        <fieldset>
                            <label for="passconf">Password, again</label>
                            <input tabindex="3" type="password" class="txt required" id="passconf" name="passconf" size="20">
                            <ul class="help">
                                <li id="passwordLength">just to make sure</li>
                            </ul>
                        </fieldset>
                        <fieldset>
                            <button style="background-color: #6BA20B;" class="button login" tabindex="5" type="submit" name="submitted" value="submitted">Request your invitation &nbsp;»</button>
                            <ul class="help">
                                <li>signing up means that you've read and accepted the <a href="/help/terms/" title="Read the Terms of Use now">Terms of Use</a></li>
                            </ul>
                        </fieldset>
                    </form>
                </div> <!-- traditional -->
            </div> <!-- mainContent-->
        </div> <!--container-->
        <script src="/src/js/jquery-1.8.0.min.js" type="text/javascript"></script>
        <script type="text/javascript" src="http://cdnstatic.visualizeus.com/js/jquery.cookie.v1.js,register.v2659.js"></script>