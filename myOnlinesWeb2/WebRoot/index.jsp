<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>在线人信息管理与统计</title>
</head>

<body>
	<h2>在线人信息管理与统计</h2>

	<c:if test="${empty sessionScope.user}" var="boo">
		<h3>会员登录:</h3>
		<form action='<c:url value='/LoginServlet'></c:url>' method="post">
			姓名:<input type='text' name="name"><br /> <input type="submit"
				value="注册">
		</form>
	</c:if>

	<c:if test="${!boo}">
  		亲爱的${user.name}
  		<c:if test="${user.admin}" var="bo">
  			-管理员
  		</c:if>
		<c:if test="${!bo}">
  			-会员
  		</c:if>
  		,欢迎来到...<br />
	</c:if>

	<br />
	<a href="<c:url value='/servlet/ShowServlet'/>">显示所有在线用户</a>
	<br />
	<a href="<c:url value='/servlet/LogOutServlet'/>">安全退出</a>
	
	<br/>网站访问量:${count}

</body>
</html>
