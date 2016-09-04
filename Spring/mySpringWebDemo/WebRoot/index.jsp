<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>基于Spring框架的Web应用演示</title>
  </head>
  
  <body>
    <h2>添加学生信息</h2>
    <form action="<c:url value='/servlet/StudServlet'/>" method="post">
		姓名:<input type="text" name="studName"><br/>
		图书:<input type="text" name="bookName"><br/>
		<input type="submit" value="添加学生">
    </form>
</html>
