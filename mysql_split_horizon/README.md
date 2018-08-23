
分表分库  

当项目比较大的时候，基本上都会进行分表分库的  
后面就讲讲什么时候需要分库，什么时候需要分表  

# 什么时候需要分库
## 垂直分割
垂直拆分就是要把表按模块划分到不同数据库表中（当然原则还是不破坏第三范式），这种拆分在大型网站的演变过程中是很常见的。当一个网站还在很小的时候，只有小量的人来开发和维护，各模块和表都在一起，当网站不断丰富和壮大的时候，也会变成多个子系统来支撑，这时就有按模块和功能把表划分出来的需求。其实，相对于垂直切分更进一步的是服务化改造，说得简单就是要把原来强耦合的系统拆分成多个弱耦合的服务，通过服务间的调用来满足业务需求看，因此表拆出来后要通过服务的形式暴露出去，而不是直接调用不同模块的表，淘宝在架构不断演变过程，最重要的一环就是服务化改造，把用户、交易、店铺、宝贝这些核心的概念抽取成独立的服务，也非常有利于进行局部的优化和治理，保障核心模块的稳定性  
垂直拆分用于分布式场景。  
当大团队在做电商项目的时候，基本上都会将一个项目进行拆分，拆分成n个小项目  
这样做的好处就是，基于逆向服务架构，会拆分多个小项目，每个小项目都有自己单独的数据库，这样的话小项目之间互不影响。  
这样叫做垂直分割。  
比如：  
会员数据库、订单数据库、支付数据库等等这样来分  
可以减低开发团队之间的耦合度。就比如，某个团队把一个数据库弄挂了，对另外的团队基本没有影响。假如全部用的一个数据库，是不是全部都挂了，所有用到那个数据库的团队项目进度都要延期    


# 什么时候需要分表
## 水平分割  
上面谈到垂直切分只是把表按模块划分到不同数据库，但没有解决单表大数据量的问题，而水平切分就是要把一个表按照某种规则把数据划分到不同表或数据库里。例如像计费系统，通过按时间来划分表就比较合适，因为系统都是处理某一时间段的数据。而像SaaS应用，通过按用户维度来划分数据比较合适，因为用户与用户之间的隔离的，一般不存在处理多个用户数据的情况，简单的按user_id范围来水平切分

为什么需要分表，就比如，一个表，几十年的数据全部在那个表中，查找，无论你加索引还是怎么，查询都需要很长的时间。  
这个时候就需要做一个分表、分表的规则，一般按照业务需求来定。没有统一的分法。  
比如:
我们的业务场景，主要是存放日志的，日志是需要按照每年存放的  
这个时候分表的话，就根据年份来分  

再如腾讯的QQ号，根据什么来分表  
如果是根据位数，最大的缺点是分部不均匀！  
另外如会员系统，通过手机号登录。会员表中  
可以通过手机号前三位分表(有一些项目是这样做的，没多大问题)，比如136 138 155等，但是都不是怎么均匀  

最好通过水平分割(取模算法)来分割  

假如我们需要把一个表分成3个表，我们可以把一个是数字的字段，比如int主键(userid)。  
这个时候，我们可以对表中数据的userid字段对3进行取模，然后对于不同的余数进行分表  
这里的取模字段不能用自动增长的  
实现取模算法，我们需要有专门的一张表存放对应的userid字段(用来取模的字段)。  
在插入行时，先生成id，然后在该表中取出对应的userid，然后赋值过去  

是否需要分表，这个依据项目经验和实际业务情况来的。一般MySQL单表1000W左右的数据是没有问题的（前提是应用系统和数据库等层面设计和优化的比较好）  
当然，如果需要分表，肯定是需要提前计划半年或者一年计划的。  


通俗理解垂直分割和水平分割：水平拆分行，行数据拆分到不同表中， 垂直拆分列，表数据拆分到不同表中    

# 水平分割取模算法案例

使用取模算法分表的最大好处就是，可以非常均匀的分配  

首先创建三张表 user0 / user1 /user2 , 然后我再创建 uuid表，该表的作用就是提供自增的id。  
创建数据库: split_horizon
```sql
create table user0(
	id int unsigned primary key ,
	name varchar(32) not null default '',
	pwd  varchar(32) not null default ''
)engine=myisam charset utf8;

create table user1(
	id int unsigned primary key ,
	name varchar(32) not null default '',
	pwd  varchar(32) not null default ''
)engine=myisam charset utf8;

create table user2(
	id int unsigned primary key ,
	name varchar(32) not null default '',
	pwd  varchar(32) not null default ''
)engine=myisam charset utf8;


create table uuid(
	id int unsigned primary key auto_increment
)engine=myisam charset utf8;
```

功能就是 注册分表，以及进行分表查询  
项目很简单，看下了解下分表是怎么回事就好，  

## Service代码
```java
package com.uifuture.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2018/2/5.
 * Time: 下午 10:28.
 * Explain:
 */
@Service
public class UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 注册的代码
     * @param name
     * @param pwd
     * @return
     */
    public String regit(String name,String pwd){
        //1.生成userid ，-  先获取到 自定增长ID
        String insertUUidSql = "insert into uuid values(null)";//插入空数据,这里的id是自动增长的
        jdbcTemplate.update(insertUUidSql);//执行
        Long userid = jdbcTemplate.queryForObject("select last_insert_id()", Long.class);//查询到最近的添加的主键id
        //2.存放具体的那张表中 - 也就是判断存储表名称
        String tableName = "user" + userid % 3;
        //3.插入到具体的表中去- 注册数据
        String insertUserSql = "INSERT INTO " + tableName + " VALUES ('" + userid + "','" + name + "','" + pwd + "');";
        System.out.println("insertUserSql:" + insertUserSql);
        jdbcTemplate.update(insertUserSql);
        return "success";
    }

    /**
     * 通过userid查询name
     * @param userid
     * @return
     */
    public String get(Long userid) {
        //具体哪张表
        String tableName = "user" + userid % 3;
        String sql = "select name from " + tableName + " where id="+userid;
        System.out.println("SQL:" + sql);
        return jdbcTemplate.queryForObject(sql, String.class);//执行查询出name
    }


}

```
## Controller代码
```java
package com.uifuture.controller;

import com.uifuture.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2018/2/5.
 * Time: 下午 10:44.
 * Explain:
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 演示注册的入口
     * @param name
     * @param pwd
     * @return
     */
    @RequestMapping("/regit")
    public String regit(String name, String pwd) {
        return userService.regit(name, pwd);
    }

    /**
     * 演示获取name
     * @param id
     * @return
     */
    @RequestMapping("/get")
    public String get(Long id) {
        return userService.get(id);
    }

}

```
其他的代码就见项目啦


分表之后也有些缺点。  
1.分页查询  
2.查询非常受限制  
比如我不查询那个分表的关键字段  

所以一般会有主表和次表  
主表存放所有的数据。次表根据具体业务需求进行分表  
还有mycar中间件具有分表功能，可以学学  

取模算法的缺点，如果我们的表发生改变，需要我们重新分，很麻烦  
(哈哈，可以使用阿里云的rds云数据库，这样就不用我们关心读写分离，分表分库等等。rds是二次开发的数据库，所以在性能上来说，比其他的关系型数据库是快很多的。可以自己去了解下)  


