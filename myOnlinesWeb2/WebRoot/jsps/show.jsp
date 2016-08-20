<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>在线人信息管理</title>
    <style type="text/css">
       table{
          border: 1px solid red;
          border-collapse: collapse;/*设置单线-线合并*/
          width: 80%;
       }
       td{
          border: 1px solid red;
          padding:3px;
       }
       .header{
         background: gray;
       }
    </style>
  </head>
  
  <body>
	<h2>以下是所有在线用户信息</h2>   
    <table>
       <tr class="header" >
          <td>姓名</td>  <td>上线时间</td>  <td>最后访问时间</td> <td>IP</td> <td>操作</td>
       </tr>
       
       <c:forEach items="${requestScope.onLines}" var="map">
       	<tr>
       		<td>
       			<c:if test="${empty map.user}" var="boo">
					游客       			
       			</c:if>
       			<c:if test="${!boo}">
       				${map.user.name}
       			</c:if>
       		</td>
       		<td>${map.creationTime}  </td>
            <td>${map.lastAccessTime}</td>
            <td>${map.ip }           </td>
       		
       		<td>
       			<c:if test="${!boo&&user.admin}">
       				 <a href="<c:url value='/servlet/KickOutServlet?id=${map.id}'/> " >踢出${map.user.name}</a>
       			</c:if>
       		</td>
       	</tr>
       </c:forEach>
    </table>
       <br/>网站访问量:${count}
  </body>
</html>
