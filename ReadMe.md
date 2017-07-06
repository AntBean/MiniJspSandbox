# 原理
JSP sandbox原理是利用jvm的bootclasspath和instrumentation特性，用自定义的rt.jar代替系统提供的rt.jar，并对其中的一些api进行intrument。
经对比测试，该沙盒在小马的检出率和误报率都优于主流厂商。

1. 编译时，编译工具里需要指定我们自定义的rt.jar。指定方法如下：
`javac -bootclasspath C:\Users\GaoJianli\workspace\patches\rt.jar TaintTest.java`

使用ant编译的，则是通过为build.xml中的javac字段添加选项来指定，方法如下：
`<project name="TaintTest" default="compile">
<property name="basedir" location="C:/Users/GaoJianli/workspace/TaintTest" />
<property name="mybootpath" location="C:/Users/GaoJianli/workspace/patches/rt.jar" />
 <target name="compile">
     <javac srcdir="${basedir}/src" destdir="${basedir}/classes" bootclasspath="${mybootpath}">
     </javac>
 </target>
</project>`


2. 运行时，也需要指定我们自定义的rt.jar。指定方法如下：
`java -Xbootclasspath/p:C:\Users\GaoJianli\workspace\patches\rt.jar TaintTest`


# 详细说明

### TaintString
1. 自定义的String类实现。编译生成的String.class和String$CaseInsensitiveComparator.class替换掉系统提供的rt.jar中的相应的class，重新打包。重打包的jar的路径作为tomcat编译和运行的bootclasspath的值

2. 通过jdk提供的instrumentation机制，调用ASM库，动态对敏感api进行hook。使用了两个instrumentation特性：
   a. premain中进行transform，并注意允许retransform
   b. *注意，对所有在premain之前已经加载的class，需要通过retransform机制对我们想hook的class强制重新加载，这样才能hook到我们想hook的api*。这一点花了我一整天的时间在定位Runtime.exec没被hook住的原因


### Tomcat
tomcat则是我们修改过的tomcat实现。包括:
1. 修改了build.xml，指定了我们的bootclasspath的位置 
2. 修改了tomcat中的源码，调用setTaint和getTaint函数
3. 启动tomcat的命令如下
`java -Xbootclasspath/p:"C:\Users\GaoJianli\workspace\patches\rt.jar" -javaagent:"C:\Mogong\export\taintagent.jar" -Djdk.tls.ephemeralDHKeySize=2048 -Djava.util.logging.config.file="C:\Users\GaoJianli\workspace\apache-tomcat-7.0.72-src\output\build\conf\logging.properties" -Djava.util.logging.manager=org.apache.juli.ClassLoaderLogManager   -Djava.endorsed.dirs="C:\Users\GaoJianli\workspace\apache-tomcat-7.0.72-src\output\build\endorsed" -classpath "C:\Users\GaoJianli\workspace\apache-tomcat-7.0.72-src\output\build\bin\bootstrap.jar;C:\Users\GaoJianli\workspace\apache-tomcat-7.0.72-src\output\build\bin\tomcat-juli.jar;C:\Mogong\lib\asm-5.0.4\lib\all\asm-all-5.0.4.jar" -Dcatalina.base="C:\Users\GaoJianli\workspace\apache-tomcat-7.0.72-src\output\build" -Dcatalina.home="C:\Users\GaoJianli\workspace\apache-tomcat-7.0.72-src\output\build" -Djava.io.tmpdir="C:\Users\GaoJianli\workspace\apache-tomcat-7.0.72-src\output\build\temp" org.apache.catalina.startup.Bootstrap  start`
4. 将test目录中的文件放到C:\Users\GaoJianli\workspace\apache-tomcat-7.0.72-src\output\build\webapps\ROOT目录下，然后访问http://localhost:8080/1.jsp即可触发

注意，使用的系统jdk版本和openjdk版本均为jdk 1.7。

