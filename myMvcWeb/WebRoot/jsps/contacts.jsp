<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<c:if test="${empty sessionScope.user}">
		<c:redirect url="/index.jsp"></c:redirect>
	</c:if>
	
	<link rel="stylesheet" href='<c:url value="/css/table.css"></c:url>'>
		
	<script type="text/javascript" src='<c:url value="/js/contact.js"></c:url>'>
	</script>
	
	<script type="text/javascript">
		var path = '<c:url value="/" />';
	</script>
  </head>
  
  <body>
  	<br/>
  	<div>
  		<button onclick="_add()">添加联系人</button> &nbsp;&nbsp;
  		<button onclick="_del()">删除联系人</button> &nbsp;&nbsp;
  		<button onclick="_query()">查询联系人</button>
  	</div>
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
  		<c:forEach items="${contacts}" var="contact">
			<tr>
			<td><input type="checkbox" name="childChk"  onclick="subchk(this)" /></td>
			<td>${contact.name}</td>	
			<td>${contact.age}</td>	
			<td>${contact.tel}</td>
			<td>${contact.uuid}</td>
  		</tr>
  		</c:forEach>
  	</table>
  	
  	<!-- 下面的form和iframe是用于做"_del()"功能的myAjax -->
  	<form name="form" target="df" action='<c:url value="/DelServlet" ></c:url>' method="post">
  		<input type="hidden" name="ids" id="ids" />
  	</form>
  	<iframe name="df" style="display: none;"></iframe>
  	</div>
  </body>
</html>
