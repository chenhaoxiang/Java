<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<script type="text/javascript">
  		function changImg(){
  			var img = document.getElementById("img");
  			var time = new Date().getTime();
  			img.src='ImgServlet?time='+time;
  		}
  		
  		function addUser(){
  			window.location.href='jsps/addUser.jsp';
  		}
  	</script>
  
  </head>
  
  <body style="text-align: center;">
  	<h1>这是主页</h1>
  	
  		
  	<form action='<c:url value="LoginServlet"></c:url>' method="post" >
		<table align="center">
		<tr> <td> 姓名：<input type="text" name="name"/> </td></tr>
		<tr> <td> 密码：<input type="password" name="pwd" /></td></tr>
		<tr> <td> <input id="img" type="image" src='ImgServlet'/>&nbsp;&nbsp;&nbsp;<input style="color: red" type="button" value="看不清" onclick="changImg()"> </td></tr>
		<tr> <td> 验证码：<input type="text" name="code" /> </td></tr>
		<tr> <td > <input type="button" value="注册" onclick="addUser()">&nbsp;&nbsp;&nbsp;&nbsp;
				   <input type="submit" value="登录" ></td></tr>
		</table>
  	</form>
  	
  </body>
</html>
