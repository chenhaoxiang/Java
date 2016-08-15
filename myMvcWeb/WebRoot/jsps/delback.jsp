<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  </head>
  
  <body>
  	<%--另一种myAjax的传值方案--在<c:out>中利用el表达式把值读取并输出，赋给js中的变量 --%>
  	<script type="text/javascript">
  // var boo = '<c:out value="${succ}" />';  
  //这种方式赋的值是字符串，如果后台返回的是false，那么此处为："false"---在js中是看成“非空”即true
  		var boo =<c:out value="${succ}" />;//这种方式才是传的布尔型变量
  		parent.realdel(boo);
  	</script>
  	
  </body>
</html>
