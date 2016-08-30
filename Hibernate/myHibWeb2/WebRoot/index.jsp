<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Hibernate中表之间的一对多关系</title>
<style type="text/css">
table {
	border: 1px solid gray;
	border-collapse: collapse;
	width: 60%;
}

td {
	border: 1px solid gray;
	padding: 5px;
}
</style>
</head>

<body>
	<h3>通过学院id查询学院表，把该学院的学生信息也同时输出来</h3>
	<form action="<c:url value='/DemoServlet?cmd=queryDeptById'/>"
		method="post">
		<table>
			<tr>
				<td>学院编号<font color="red">*</font>
				</td>
				<td><input type="text" name="deptId"></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="查询">
				</td>
			</tr>
		</table>
	</form>

	<c:if test="${!empty sessionScope.map }">
		<h3>查询结果</h3>
		  学院名称:${map.deptName}
		  <table>
			<tr>
				<td>学号</td>
				<td>姓名</td>
				<td>年龄</td>
				<td>学院编号</td>
			</tr>
			<c:forEach items="${map.qlist}" var="stud">
				<tr>
					<td>${stud.sId}</td>
					<td>${stud.sName}</td>
					<td>${stud.sAge}</td>
					<td>${stud.dept.dId}</td>
				</tr>
			</c:forEach>
		</table>
		<c:remove var="map"/>
	</c:if>
	
	<h3>添加学生/学院</h3>
	<form action="<c:url value='/DemoServlet?cmd=addDept'/>" method="post">
		<table>
			<tr>
				<td>学院编号<font color="red">*</font>
				</td>
				<td><input type="text" name="deptId"></td>
			</tr>
			<tr>
				<td>学院名称
				</td>
				<td><input type="text" name="deptName"></td>
			</tr>
			<tr>
				<td align="center">学生学号<font color="red">*</font>
				</td>
				<td align="center"><input type="text" name="studId"></td>
			</tr>
			<tr>
				<td align="center">学生姓名<font color="red">*</font>
				</td>
				<td align="center"><input type="text" name="studName"></td>
			</tr>
			<tr>
				<td align="center">学生年龄<font color="red">*</font>
				</td>
				<td align="center"><input type="text" name="studAge"></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="存储"></td>
			</tr>
		</table>
	</form>

</body>
</html>
