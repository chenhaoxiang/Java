<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<title>学生信息查询</title>
  	<style type="text/css">
  		table,tr,td,th{
  			border: 1px;
  			border-style: solid;
  			margin: auto;
  		}
  	</style>
  </head>
  
  <body style="text-align: center;">
  	<h2>学生信息</h2>
  	<table>
		<tr> <th>ID</th> <th>姓名</th></tr>
		<c:forEach items="${studs}" var="stud">
  		<tr> <td>${stud.id }</td> <td>${stud.name }</td> </tr>
  		</c:forEach>
  	</table>
  </body>
</html>
