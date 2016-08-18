<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	 <title>过滤器应用示例---敏感词过滤</title>
  </head>
  
  <body>
     <h2>过滤器应用示例---敏感词过滤</h2>
     <form action="<c:url value='/NoteServlet'/>" method="post">
     	用户名:<input type="text" name="name" /><br/><br/>
		<fieldset>
			<legend>留言板</legend>
			<textarea name="note" rows="10" cols="20"></textarea>
		</fieldset>
		<input type="submit" value="留言" />     
     </form>
  </body>
</html>
