<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>list</title>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="http://lib.sinaapp.com/js/jquery/1.9.1/jquery-1.9.1.min.js"></script>
<script>
function junp(pageNo,pageSize){
	$('form').attr("action","/demo/list?page.pageNo="+pageNo+"&page.pageSize="+pageSize);
	$('form').submit();
}
</script>
</head>
<body>
	<form action="" method="get">
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
					<td colspan="5">第${page.pageNo}/${page.pageNumber}页，共${page.count}条&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;跳转<input
						type="text" id="pageNo" /><input type="button"
						onclick="jump($('#pageNo').val(),${page.pageSize})" /></td>
				</tr>
			</tfoot>
		</table>
	</form>
</body>
</html>
