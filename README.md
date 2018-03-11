[![license](https://img.shields.io/badge/gradle-4.6-brightgreen.svg)](https://gradle.org)
[![license](https://img.shields.io/github/license/mashape/apistatus.svg)](https://opensource.org/licenses/mit-license.php)

#  [Jwt](https://github.com/jwtk/jjwt)  integration  with springboot

Druid-Spring-Boot-Starter 帮助你集成通用 [Jwt](https://github.com/jwtk/jjwt) 到 Spring Boot。

Druid-Spring-Boot-Starter will help you use [Jwt](https://github.com/jwtk/jjwt) with Spring Boot.

## How to use

### maven

在pom.xml加入nexus资源库（解决中国访问慢的问题,已经加入中央仓库）

Add the following nexus repository(fix china access slow problem,already append to central nexus.)  to your pom.xml:

    <repositories>
        <repository>
            <id>nexus</id>
            <name>nexus</name>
            <url>http://maven.cuisongliu.com/content/groups/public</url>
            <releases>
                <enabled>true</enabled>
            </releases>
            <snapshots>
                <enabled>false</enabled>
            </snapshots>
        </repository>
    </repositories>

在pom.xml加入依赖

Add the following dependency to your pom.xml:
    
    <dependency>
       <groupId>com.cuisongliu</groupId>
       <artifactId>jwt-spring-boot-starter</artifactId>
       <version>1.2</version>
     </dependency>

### gradle

在build.gradle加入nexus资源库（解决中国访问慢的问题,已经加入中央仓库）

Add the following nexus repository(fix china access slow problem,already append to central nexus.)  to your build.gradle:

    allprojects {
        repositories {
            mavenLocal()
            maven { url "http://maven.cuisongliu.com/content/groups/public" }
            mavenCentral()
            jcenter()
        }
    }
    
在build.gradle加入依赖

Add the following dependency to your build.gradle:
    
    compile "com.cuisongliu:jwt-spring-boot-starter:1+"

需要增加fastjson支持,加入即可.
Need to increase fastjson support, can be added.
    
### springboot properties set

在application.properties 或者application.yml加入 

at  application.properties or application.yml append some properties.

| properties | IsNull? | Defaults |
| :------|:------|:------|
|spring.jwt.header|yes|Authorization|
|spring.jwt.secret|yes|defaultSecret|
|spring.jwt.expiration|yes|604800L|
|spring.jwt.authPath|yes|null|
|spring.jwt.md5Key|yes|randomKey|


## Example


     spring:
         jwt:
            header: Authorization
            secret: defaultSecret
            expiration: 604800L
            authPath:
               - /api
            md5Key: randomKey
            

## Acknowledgments

 [Jwt](https://github.com/jwtk/jjwt).