DB 放的是数据库的数据
my-xxl-job 执行器节点一
my-xxl-job-http 执行器节点二
nginx-1.16.0 调度集群的nginx
xxl-job 源码
startJobJar-8080.bat 调度集群节点一的启动文件 端口是8080
startJobJar-8088.bat 调度集群节点二的启动文件 端口是8088
startJobServiceJar.bat 启动一个服务，作用：视作一个远程服务

注：1. xxl-job里面的admin项目中也有个jar包，那个是调度中心
	2. xxl-job的源码地址：https://gitee.com/xuxueli0323/xxl-job、https://github.com/xuxueli/xxl-job/