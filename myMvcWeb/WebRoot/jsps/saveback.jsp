<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  </head>
  
  <body>
  	<!-- 一种myAjax的传值方案 -->
  	<input type="text" id="id" value='<c:out value="${sessionScope.id}"></c:out>'>
  	<script type="text/javascript">
		var id = document.getElementById("id").value;
		parent.relsave(id);
  	</script>
  </body>
</html>
