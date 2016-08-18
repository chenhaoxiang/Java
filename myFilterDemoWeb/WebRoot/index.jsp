<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  </head>
  
  <body>
  	湖南城市学院
  		<br/>
      <hr/>
      <form action="<c:url value='/LoginServlet'/>" method="post">
         Name:<input type="text" name="name"><br/>
         <input type="submit" value="登录">
      </form>
  </body>
</html>
