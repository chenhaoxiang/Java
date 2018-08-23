<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<title>带邮箱激活的注册模块</title>
  	
  	<script type="text/javascript">
  		function reg(){
  			 //省略: 表单数据格式校验--还有ajax实现的验证码
  			 document.forms[0].action="<c:url value='RegServlet'/>";
  			 document.forms[0].submit();
  		}
  		function login(){
  			 document.forms[0].action="<c:url value='LoginServlet'/>";
  			 document.forms[0].submit();
  		}
  	</script>
  	
  </head>
  
  <body>
  	<c:if test="${!empty sessionScope.error}">
  		登录失败!
  		<c:remove var="error" scope="session"/>
  	</c:if>
  	
  	<c:if test="${empty sessionScope.user}" var="boo">
  		<form action="" method="post">
	  		姓名:<input type="text" name="name" /> <br/>
            密码:<input type="password" name="pwd" /> <br/>
            邮箱:<input type="text" name="email" />-登录不用填写邮箱 <br/>
            <input type="button" value="注册" onclick="reg();"/> &nbsp;
         	<input type="button" value="登录" onclick="login();"/> &nbsp; 
        	<input type="reset" value="重置"/> 
  		</form>
  	</c:if>
  	<c:if test="${!boo}">
  		登录成功<br/>
  		欢迎你，亲爱的${user.name}
  	</c:if>
  </body>
</html>
