<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>过滤器应用示例---全站压缩</title>
  </head>
  
  <body>
    <h2>过滤器应用示例---全站压缩</h2>
    <a href="<c:url value='/FirstGzipServlet'/>">请求第一个servlet--网页压缩输出的基本原理</a> <br/><br/>
    <a href="<c:url value='/servlet/SecondServlet'/>">请求第二个servlet--用过滤器压缩输出字节流数据</a><br/><br/>
    <a href="<c:url value='/servlet/ThirdServlet'/>">请求第三个servlet--用过滤器压缩输出字符流数据</a><br/><br/>
  </body>
</html>
