<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <script type="text/javascript">
  		function changImg(){
  			var img = document.getElementById("img");
  			var time = new Date().getTime();
  			var path ='<c:out value="${pageContext.request.contextPath}"/>';//返回：/项目名
  			img.src=path+'/ImgServlet?time='+time;
  			//var path = '<c:url value="/" />';//返回：/项目名/
  			//img.src=path+'ImgServlet?time='+time;
  			//alert(path);
  		}
  	</script>
  </head>
  <body	style="text-align: center;">
  <h1>请认真填写注册信息:</h1>
  	<form action='<c:url value="/AddUserServlet"></c:url>' method="post" >
		<table align="center">
		<tr> <td> 姓名：<input type="text" name="name"/> </td></tr>
		<tr> <td> 密码：<input type="password" name="pwd" /></td></tr>
		<tr> <td> <input id="img" type="image" src='${pageContext.request.contextPath}/ImgServlet'/>&nbsp;&nbsp;&nbsp;<input style="color: red" type="button" value="看不清" onclick="changImg()"> </td></tr>
		<tr> <td> 验证码：<input type="text" name="code" /> </td></tr>
		<tr> <td >  <input type="submit" value="提交" ></td></tr>
		</table>
  	</form>
  </body>
</html>
