<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<c:if test="${empty sessionScope.user}">
		<c:redirect url="/index.jsp"></c:redirect>
	</c:if>
	<link rel="stylesheet" href='<c:url value="/css/table1.css"></c:url>'>
		
	<script type="text/javascript" src='<c:url value="/js/contact.js"></c:url>'>
	</script>
	
  </head>
  
  <body>
  	<br/>
  	<h1>联系人查询结果：</h1>
  	<br/>
  	<div>
  	<table id="tb">
  		<tr>
			<th>选择<input type="checkbox" id="parentChk" onclick="chk(this)"/></th>
			<th>姓名</th>	
			<th>年龄</th>	
			<th>电话</th>
			<th>ID</th>
  		</tr>
  		<c:forEach items="${contactsQ}" var="contactQ">
			<tr>
			<td><input type="checkbox" name="childChk"  onclick="subchk(this)" /></td>
			<td>${contactQ.name}</td>	
			<td>${contactQ.age}</td>	
			<td>${contactQ.tel}</td>
			<td>${contactQ.uuid}</td>
  		</tr>
  		</c:forEach>
  	</table>
  	</div>
  </body>
</html>
