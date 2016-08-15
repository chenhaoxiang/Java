<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<script type="text/javascript">
  		function save(){
  			//这里应该要先校验数据的有效性，但这是为了演示MVC，这里就略过了。
  			//拿到表单对象，然后提交
  			document.forms['contact'].submit();
  		}
  		
  		//回调
  		function relsave(id){
  			//把提交数据和后台返回的id这些数据，在表格后增加一行
  			var name = document.getElementsByName("name")[0].value;
  			var age = document.getElementsByName("age")[0].value;
        	var tel = document.getElementsByName("tel")[0].value;
        	//JSON---封装好数据
        	var obj = new Object();
        	obj.name=name;
        	obj.age=age;
        	obj.tel=tel;
        	obj.id=id;
        	//窗口关闭的时候的返回值
        	window.returnValue=obj;
			window.close();  			
  		}  		
  	</script>
  	
  	
  </head>
  
  <body style="text-align: center;">
  	<h2>联系人信息:</h2>
  	<form target="form" name="contact" action='<c:url value="/SaveServlet"></c:url>'  method="post">
  		<table border="1px" style="color: red">
  		<tr><td>姓名：</td><td><input type="text" name="name"/></td></tr>
  		<tr><td>年龄：</td><td><input type="text" name="age"/></td></tr>
  		<tr><td>电话：</td><td><input type="text" name="tel"/></td></tr>
  		<tr><td><input type="button" onclick="save()" value="保存"> </td><td> <input type="button" onclick="window.close()" value="取消"> </td></tr>
  		</table>
  	</form>
  	
  	<!-- 表单提交后，服务器返回的内容在这里面显示 -->
  	<iframe name="form" style="display: none;"></iframe>
  	
  </body>
</html>
