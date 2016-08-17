分页,是一种将所有数据分段展示给用户的技术.用户每次看到的不 是全部数据,而是其中的一部分,如果在其中没有找到自己想要的内容,用户可以通过指定页码或是点上/下一页的方式进行翻页。

本例演示静态分页，也就是先设置好每页显示10行，再根据总行数，来算出总页数，并且只显示10个页码。增加查询功能，并且查询后的页面也进行分页。页码也进行分页 ！

查询功能的实现，需要我们在servlet向后台传输一个person,封装我们的查询条件


相关算法(技术):


```
总行数(num): select count(1) from stud;

每页显示的行数(n): 固定值---已知的一个常量

页数: pageSize= num/n +( (num%n==0)?0:1 )

当前页号: currentPage

当前要显示的页面数据的起始行号和终止行号
startN: (currentPage-1)*pageSize

如何显示从startN开始的pageSize条记录
select * from stud limit startN, pageSize;

增加了：
开始的页码：
showStart
结束的页码：
showEnd
```
