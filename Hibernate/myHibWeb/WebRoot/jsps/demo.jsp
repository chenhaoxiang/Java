<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>利用Hibernate进行单表的增删改查</title>
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

<script type="text/javascript" src="<c:url value='/js/ajax.js'/>"></script>

<script type="text/javascript">
	var path = "<c:url value='/'/>";
</script>

<script type="text/javascript">
	var d = new Date();
	function query() {
		var studId = document.getElementsByName("studId")[1].value;
		studId = studId.trim();
		var studName = document.getElementsByName("studName")[1].value;
		studName = studName.trim();
		var deptId = document.getElementsByName("deptId")[1].value;
		deptId = deptId.trim();

		//ajax提交
		var ajax = new Ajax();
		var url = path + "/DemoServlet";
		var params = "cmd=queryStudents&studId=" + studId + "&studName="
				+ studName + "&deptId=" + deptId;
		ajax.post(url, params, function(data) {
			if (data == "1") {
				//这个返回来输出的页面的是子页面！
				window.parent.window.location.href = path + "?time="
						+ d.getTime();
			}
		});
	}
</script>


</head>

<body>
	<table>
		<tr>
			<td>学号</td>
			<td>姓名</td>
			<td>年龄</td>
			<td>学院编号</td>
			<td>操作</td>
		</tr>
		<c:forEach items="${list}" var="stud">
			<tr>
				<td>${stud.sId}</td>
				<td>${stud.sName}</td>
				<td>${stud.sAge}</td>
				<td>${stud.deptId}</td>
				<td><a
					href="<c:url value='/DemoServlet?cmd=delStudent&studId=${stud.sId}'/>">删除</a>
				</td>
			</tr>
		</c:forEach>
	</table>

	<h3>添加一个学生信息</h3>
	<form action="<c:url value='/DemoServlet?cmd=addStudent'/>"
		method="post">
		<table>
			<tr>
				<td>学号<font color="red">*</font>
				</td>
				<td><input type="text" name="studId"></td>
			</tr>
			<tr>
				<td>姓名<font color="red">*</font>
				</td>
				<td><input type="text" name="studName"></td>
			</tr>
			<tr>
				<td>年龄<font color="red">*</font>
				</td>
				<td><input type="text" name="age"></td>
			</tr>
			<tr>
				<td>学院编号<font color="red">*</font>
				</td>
				<td><input type="text" name="deptId"></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit"
					value="添加/修改"></td>
			</tr>
		</table>
	</form>


	<hr />
	<h3>学生查询</h3>
	<table>
		<tr>
			<td>学号</td>
			<td><input type="text" name="studId"></td>
		</tr>
		<tr>
			<td>姓名</td>
			<td><input type="text" name="studName"></td>
		</tr>
		<tr>
			<td>学院编号</td>
			<td><input type="text" name="deptId"></td>
		</tr>
		<tr>
			<td colspan="2" align="center"><input type="button"
				onclick="query();" value="查询"></td>
		</tr>
	</table>

	<c:if test="${!empty sessionScope.qlist }">
		<h3>查询结果</h3>
		<table>
			<tr>
				<td>学号</td>
				<td>姓名</td>
				<td>年龄</td>
				<td>学院编号</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${qlist}" var="stud">
				<tr>
					<td>${stud.sId}</td>
					<td>${stud.sName}</td>
					<td>${stud.sAge}</td>
					<td>${stud.deptId}</td>
					<td>
						<a href="<c:url value='/DemoServlet?cmd=delStudent&studId=${stud.sId}'/>">删除</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>


</body>
</html>
