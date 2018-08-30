# CheckFee

## 编译篇

```
#在当前目录执行以下两条命令，没有maven的请自行下载
#需要连接网络下载依赖
$mvn clean
$mvn assembly:assembly
```
等待编译完成后，进入target目录可以看到CheckElec-1.0.jar
执行


## 使用篇
```
#参看参数说明
$java -jar CheckElec-1.0.jar -h 

#命令示例
#-b 代表楼栋
#-l 代表宿舍所在区域
#-t 代表接收信息的email地址
#-r 代表房间号
#-w 代表电量低于多少提示，默认值为10
$java -jar CheckElec-1.0.jar -b "沁苑12舍" -l "东区" -t "cszhenyuhu@foxmail.com" -r "201" -w 200

```

## 高级用法

可以将此jar包上传至服务器，为了避免字符集乱码问题。
先讲~/.bashrc中的字符集设置成utf-8
```$xslt
export LANG="zh_CN.UTF-8"
export LC_CTYPE="zh_CN.UTF-8"
```
然后source 一下
```$xslt
# source ~/.bashrc
```

使用crontab 定时查询电费
```$xslt
$crontab -e
#注意执行命令写在脚本里，并且Java命令用绝对路径，在脚本首行写一句 source ~/.bashrc
0 9 * * * /home/feiwang/execCheckElec.sh > /home/feiwang/log.txt
```
脚本示例
```$xslt
#!/bin/sh
source ~/.bashrc
/home/feiwang/DecaTestSuite/jdk1.8.0_171/bin/java -cp /home/feiwang/ -jar CheckElec-1.0.jar -b "沁苑12舍" --location "东
区" -t "cszhenyuhu@foxmail.com" -r "201" -w 15
/home/feiwang/DecaTestSuite/jdk1.8.0_171/bin/java -cp /home/feiwang/ -jar CheckElec-1.0.jar -b "沁苑9舍" --location "东>区" -t "1715123874@qq.com" -r "612" -w 15
```



 

