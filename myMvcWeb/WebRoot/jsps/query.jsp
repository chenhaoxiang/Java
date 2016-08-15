<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<script type="text/javascript">
  		function query(){
  			document.forms['queryForm'].submit();
  		}
  	</script>
  	
  </head>
  
  <body style="text-align: center;">
  	<h2>需要查询的联系人信息(选填):</h2>
  	<form  name="queryForm" action='<c:url value="/QueryServlet"></c:url>'  method="post">
  		<table border="1px" style="color: red">
  		<tr><td>姓名：</td><td><input type="text" name="name"/></td></tr>
  		<tr><td>年龄：</td><td><input type="text" name="age"/></td></tr>
  		<tr><td>电话：</td><td><input type="text" name="tel"/></td></tr>
  		<tr><td><input type="button" onclick="query()" value="查询"> </td><td> <input type="button" onclick="window.close()" value="取消"> </td></tr>
  		</table>
  	</form>
  	
  </body>
</html>
