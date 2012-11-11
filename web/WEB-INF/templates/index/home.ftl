<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
<html>  
    <head>  
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">  
        <title>Insert title here</title>  
    </head>  
    <body>
        <#if user>
        ${user.email}用户，您好
        <#else>
        您还没有登录
        </#if>
    </body>  
</html>  