# 云博客

## 快速运行
1. 安装必备工具  
JDK，Maven
2. 克隆代码到本地  
3. 运行命令创建数据库脚本
```sh
mvn flyway:migrate
```
4. 运行打包命令
```sh
mvn package
```
5. 运行项目  
```sh
java -jar target/blog-0.0.1-SNAPSHOT.jar
```
6. 访问项目
```
http://localhost:8080
```

## 资料
[Spring 文档](https://spring.io/guides)    
[Spring Web](https://spring.io/guides/gs/serving-web-content/)   
[es](https://elasticsearch.cn/explore)    
[Github deploy key](https://developer.github.com/v3/guides/managing-deploy-keys/#deploy-keys)    
[Bootstrap](https://v3.bootcss.com/getting-started/)    
[Github OAuth](https://developer.github.com/apps/building-oauth-apps/creating-an-oauth-app/)    
[Spring](https://docs.spring.io/spring-boot/docs/2.0.0.RC1/reference/htmlsingle/#boot-features-embedded-database-support)    
[菜鸟教程](https://www.runoob.com/mysql/mysql-insert-query.html)    
[Thymeleaf](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#setting-attribute-values)    
[Spring Dev Tool](https://docs.spring.io/spring-boot/docs/2.0.0.RC1/reference/htmlsingle/#using-boot-devtools)  
[Spring MVC](https://docs.spring.io/spring/docs/5.0.3.RELEASE/spring-framework-reference/web.html#mvc-handlermapping-interceptor)  
[Markdown 插件](http://editor.md.ipandao.com/)   
[UFfile SDK](https://github.com/ucloud/ufile-sdk-java)  
[Count(*) VS Count(1)](https://mp.weixin.qq.com/s/Rwpke4BHu7Fz7KOpE2d3Lw)  

## 工具
[Git](https://git-scm.com/download)   
[Visual Paradigm](https://www.visual-paradigm.com)    
[Flyway](https://flywaydb.org/getstarted/firststeps/maven)  
[Lombok](https://www.projectlombok.org)    
[ctotree](https://www.octotree.io/)   
[Table of content sidebar](https://chrome.google.com/webstore/detail/table-of-contents-sidebar/ohohkfheangmbedkgechjkmbepeikkej)    
[One Tab](https://chrome.google.com/webstore/detail/chphlpgkkbolifaimnlloiipkdnihall)    
[Live Reload](https://chrome.google.com/webstore/detail/livereload/jnihajbhpnppcggbcgedagnkighmdlei/related)  
[Postman](https://chrome.google.com/webstore/detail/coohjcphdfgbiolnekdpbcijmhambjff)

```bash
mvn flyway:migrate
mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate
```
##关于MBG自动生成
一：在resources下做一个generatorConfig.xml配置，其中配置中参考：
~~~xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE generatorConfiguration
        PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
        "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">

<generatorConfiguration>

    <context id="DB2Tables" targetRuntime="MyBatis3">

        <plugin type="org.mybatis.generator.plugins.RowBoundsPlugin"></plugin>
        <jdbcConnection driverClass="com.mysql.jdbc.Driver"
                        connectionURL="jdbc:mysql://127.0.0.1:3306/blog"
                        userId="root"
                        password="gates">
        </jdbcConnection>

        <javaTypeResolver>
            <property name="forceBigDecimals" value="false"/>
        </javaTypeResolver>

        <javaModelGenerator targetPackage="com.cloud.blog.model" targetProject="src/main/java">
            <property name="enableSubPackages" value="true"/>
            <property name="trimStrings" value="true"/>
        </javaModelGenerator>

        <sqlMapGenerator targetPackage="mapper" targetProject="src/main/resources">
            <property name="enableSubPackages" value="true"/>
        </sqlMapGenerator>

        <javaClientGenerator type="XMLMAPPER" targetPackage="com.cloud.blog.mapper"
                             targetProject="src/main/java">
            <property name="enableSubPackages" value="true"/>
        </javaClientGenerator>

        <table tableName="blog_user" domainObjectName="User"></table>
        <table tableName="blog_show" domainObjectName="Show"></table>
        <table tableName="blog_content" domainObjectName="Content"></table>
        <table tableName="blog_comment" domainObjectName="Comment"></table>
<!--        <table tableName="question" domainObjectName="Question"></table>-->
<!--        <table tableName="comment" domainObjectName="Comment"></table>-->
<!--        <table tableName="notification" domainObjectName="Notification"></table>-->
<!--        <table tableName="nav" domainObjectName="Nav"></table>-->
<!--        <table tableName="ad" domainObjectName="Ad"></table>-->
    </context>
</generatorConfiguration>
~~~
二：application.properties配置
~~~properties
mybatis.configuration.map-underscore-to-camel-case=true
mybatis.type-aliases-package=life.majiang.community.mapper
mybatis.mapper-locations=classpath:mapper/*.xml
~~~
三：执行命令
~~~bash
mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate
~~~
总结：MBG生成的自己认为就是生成一个模板引擎，根据数据库中的内容可以生成实体类--其中也附带了一些特殊方法，生成的mapper.xml文件
相当于给我们提供了很多操作数据库的方法

### 本项目注意点

#### 1.浏览器变化

在你的浏览器中，你会发现网络页面可以根据你的浏览器大小变化后而自动改变原来的格式
所以，你可以给你想要添加这种效果的标签中添加一个类属性：class="col-lg-12 col-md-12 col-sm-12 col-xs-12"
这个类属性来源于BootStrap，当然你可以直接去网站下载，也可以使用本项目中static直接提供的

#### 2.这个项目使用模板引擎thymeleaf

虽然这个项目使用模板引擎，在实际开发中不建议使用任何模板引擎。
否则，您会发现在前端和后端分离在你的开发团队中有着本质性的问题 

#### 3.项目使用的数据库

项目使用数据库管理系统Mysql ，账号和密码配置除了在yaml文件中有，在Pom.xml文件中也存在，如果改为自己的数据库，需要手动更改

#### 4.项目使用的github授权登陆

如果你也有这个想法，不要忘记更改application.properties中的![1571122039648](C:\Users\张玉雷\AppData\Roaming\Typora\typora-user-images\1571122039648.png)

这个来源于我们自己注册的github app，注册完成后，直接更换为你自己的就好