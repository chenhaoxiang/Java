<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<title>演示利用Filter实现自动登录</title>
  </head>
  
  <body>
  	<h2>主页-演示利用Filter实现自动登录</h2>
  	<c:if test="${!empty sessionScope.error}">
  		<font color="red">${sessionScope.error}</font>
  		<c:remove var="error" scope="session"/>
  	</c:if>
  	
  	<c:if test="${empty sessionScope.user}" var="boo">
  		<form action="<c:url value='/LoginServlet'/>" method="post">
  			Name:<input type="text" name="name"><br/><br/>
            <!-- 这里为了演示清楚，就直接用text了 -->
            &nbsp;Pwd:<input type="text" name="pwd"><br/>
            <fieldset>
            	<legend>自动登录</legend>
            	<input type="radio" name="time" value="0" checked="checked">不自动登录<br/>
           		<input type="radio" name="time" value="1" >1分钟<br/>
           		<input type="radio" name="time" value="60" >1小时<br/>
           		<input type="radio" name="time" value="86400" >1天<br/>
           		<input type="radio" name="time" value="604800" >1周<br/>
            	<input type="radio" name="time" value="2592000" >一个月<br/>
            </fieldset>
            	<input type="submit" value="登录">
		</form>  		
  	</c:if>
  	<c:if test="${!boo}">
  		${user.name},欢迎回来。
  		<a href="<c:url value='/CancelAutoLoginServlet'/>">取消自动登录</a>
  		
  	</c:if>
  </body>
</html>
