Lefoto
=====
    Lefoto是一个集图片浏览、分享和自动搜集为一身的图片社区网站，其专注于强化分类图片的概念，提供用户浏览内容的优质性

[点击进入Lefoto网站](http://www.lefoto.me)  |  [点击进入ImageGrab项目](https://github.com/wh1100717/ImageGrab)

![github](https://raw.github.com/wh1100717/lefoto/master/web/resource/images/intro/website.png) 

    项目技术概要：
        ○ 后台采用Spring MVC 3.1.1 + Hibernate 3 框架
        ○ 数据库采用MySQL
        ○ JDK为1.6
        ○ 模版使用freemarker
        ○ 前端已经搭建Extjs4.1框架(可用于管理后台开发),或者用另外的模版进行后台开发也可
        ○ 后台采用MVC框架
        ○ 图片通过又拍云CDN加速
        ○ 自建缓存，对图片及用户数据的获取进行优化
        ○ 实现对纯图、堆糖、花瓣、91美图、我喜欢等图片网站的定时定点抓图以及描述功能，具体代码分写到项目ImageGrab项目中 
------
    项目搭建：
        ○ 在netbeans的团队开发中选择Git--克隆
        ○ 资源库URL输入：https://github.com/wh1100717/ImageGrab.git , 用户名密码输入自己github的帐号密码即可,克隆到本地任意位置
        ○ 右键点击该项目--属性，找到“库”，删除所有web/WEB-INF/lib/下的jar文件后添加jar文件，把lib文件夹下的所有jar文件再添加上
        ○ 本地安装mysql程序，在web/WEB-INF/下创建jdbc.properties文件，按如下格式填写
            jdbc.driverClassName=com.mysql.jdbc.Driver
            jdbc.url=jdbc:本地数据库地址?useUnicode=true&amp;characterEncoding=UTF-8
            jdbc.username=用户名
            jdbc.password=密码
            hibernate.dialect=org.hibernate.dialect.MySQL5Dialect
            hibernate.show_sql=true
        ○ 将web/WEB-INF/下的lefoto.sql数据库备份文件导入到本地数据库中
        ○ 构建项目并尝试启动 
------
    项目进程：
        ○ 主体功能已完成
            java后台功能实现 By Eric
            前台index页面、瀑布流脚本 By Bobo
            前端管理后台页面 By Eric
        ○ 未完成内容
            前端大量页面（准确的说是除了首页以外的页面）
            后台大量页面（准确的说管理后台就是随便搭的....）
            LOGO及部分页面设计
            后台程序抓图任务部分网站未完成
            http请求接口文档撰写
        ○ 未来展望
            该项目主要是做着玩，没有实际的目的和模式。
            但是我希望能做成一个给第三方小型开发者提供接口的小平台，当一个iOS或Android应用或者小网站需要类似的图片分享和优质图片资源的
            时候不需要自己搭设服务器，不需要自己写后台程序，不需要考虑规模和优化，也不需要考虑图片CDN加速，直接通过我提供的接口就可以实
            现免费的图片资源上传、分享和浏览等功能，从而把精力着重放在个人创新的方面来从而使自己的应用或者网站脱颖而出。
------
