<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<title>在线人信息与网站点击量的实现</title>
  </head>
  
  <body>
    <h3>在线人信息与网站点击量的实现</h3>
    <a href='<c:url value="servlet/ShowServlet"></c:url>'>查看在线人信息</a>
   	<hr/>
   	<form action='<c:url value="servlet/LoginServlet"></c:url>' method="post">
   		 姓名:<input type="text" name="name" />
         <input type="submit" value="登录">
   	</form>
   	
    <br/>点击量:${count}
  </body>
</html>
