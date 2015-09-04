<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>list</title>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="http://lib.sinaapp.com/js/jquery/1.9.1/jquery-1.9.1.min.js"></script>
</head>
<body>
	<form action="/web/demo/list" method="post">
		<input id="pageNo" value="1" /> <input id="pageSize" value="7" />
		<table>
			<thead>
				<tr>
					<th>序号</th>
					<th>ID</th>
					<th>email</th>
					<th>年龄</th>
					<th>性别</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.list}" var="demo" varStatus="status">
					<tr>
						<td>${status.count}</td>
						<td>${demo.id}</td>
						<td>${demo.email}</td>
						<td>${demo.age}</td>
						<td>${demo.sex}</td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="5">第${page.pageNo}/${page.pageNumber}页，共${page.count}条&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
						type="text" name="pageNo" /><input type="submit" value="跳转 ">
						<input name="pageSize" type="hidden" value="${page.pageSize}">
				</tr>
			</tfoot>
		</table>
	</form>
</body>
</html>
