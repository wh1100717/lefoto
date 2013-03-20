Lefoto
=====
    集图片浏览、分享和自动搜集为一身的图片社区网站，专注于强化分类图片的概念，提供用户浏览内容的优质性
------

![github](https://raw.github.com/wh1100717/lefoto/master/web/resource/images/intro/website.png) 

    项目技术概要：
        ○ 后台采用Spring MVC 3.1.1 + Hibernate 3 框架
        ○ 数据库采用MySQL
        ○ JDK为1.6
        ○ 模版使用freemarker
        ○ 前端已经搭建Extjs4.1框架(可用于管理后台开发),或者用另外的模版进行后台开发也可
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
