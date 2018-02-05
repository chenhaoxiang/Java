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
