#!/bin/sh
git checkout master
git add .
echo 请输入本次提交的注释 如输入空白字符则使用"commit"
read -t 600 var
#会读取一行到var变量
if [ -z $var ];then
echo 未输入注释 空白注释
git commit -am "commit"
else
echo commit is $var
git commit -am "$var"
fi
git push origin master
sleep 15