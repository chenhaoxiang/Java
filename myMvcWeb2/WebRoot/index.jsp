<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<title>mvc示例演示</title>
  </head>
  
  <body>
  	<br/>
  	<a href='<c:url value="/StudServlet?cmd=query"></c:url>'>学生信息查询</a>
  	<hr/>
  	<form action="<c:url value='/StudServlet?cmd=save'/>" method="post">
  		姓名:<input type="text" name="name" /><br/>
  		<fieldset style="width: 200px">
  			<legend>图书1</legend>
  			书名:<input type="text" name="bookname" /><br/><br/>
  			价格:<input type="text" name="price" />
  		</fieldset>
  		<fieldset style="width: 200px">
  			<legend>图书2</legend>
  			书名:<input type="text" name="bookname" /><br/><br/>
  			价格:<input type="text" name="price" />
  		</fieldset><br/>
  		<input type="submit" value="保存">
  	</form>
  	<br/>
  	<a href="<c:url value='/StudServlet'/>">默认请求</a>
  </body>
</html>
