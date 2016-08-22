<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>带邮箱激活的注册模块</title>
    
    <script type="text/javascript">
		var tm;    	
		var i=5;
		function time(){
			i--;
			div1.innerHTML = i+"秒钟以后，自动去登录!";
			if(i<1){
				window.clearInterval(tm);//将定时器清除
				window.location.href="<c:url value='index.jsp'/>";		
			}
		}
		    	
    	onload=function(){
    		tm = window.setInterval(time, 1000);
    	};
    </script>

  </head>
  
  <body>
   <c:if test="${count==-1}">
             服务器异常，请重新激活！<br/>
      <a href="<c:url value='/index.jsp'/>">登录</a>
      <br/>
      <div id="div1">
      </div>
   </c:if>   
   <c:if test="${count==0}">
             激活地址错误，请使用正确的激活地址！<br/>
      <a href="<c:url value='/index.jsp'/>">登录</a>
      <br/>
      <div id="div1">
      </div>
   </c:if>   
   <c:if test="${count==1}">
            你已经激活过，请勿重复激活！<br/>
      <a href="<c:url value='/index.jsp'/>">登录</a>
      <br/>
      <div id="div1">
      </div>
   </c:if>   
   <c:if test="${count==2}">
            激活成功，欢迎去登录！<br/>
      <a href="<c:url value='/index.jsp'/>">登录</a>
      <br/>
      <div id="div1">
      </div>
   </c:if>   
  </body>
</html>
