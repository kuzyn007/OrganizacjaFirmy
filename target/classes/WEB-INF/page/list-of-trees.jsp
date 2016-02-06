<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<?xml version="1.0" encoding="UTF-8" ?>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hierarchia</title>
</head>
<body>
<h1>Hierarchia</h1>
<table border="1px" cellpadding="0" cellspacing="0" >
<thead>
<tr>
<th width="10%">id</th><th width="15%">Nazwa</th><th width="10%">Dział</th><th width="10%">Opcje</th>
</tr>
</thead>
<tbody>
<c:forEach var="tree" items="${trees}">
<tr>
	<td>${tree.id}</td>
	<td>${tree.nazwa}</td>
	<td>${tree.numerid}</td>
	<td>
	<a href="${pageContext.request.contextPath}/tree/edit/${tree.id}.html">Edytuj</a><br/>
	<a href="${pageContext.request.contextPath}/tree/delete/${tree.id}.html">Usuń</a><br/>
	</td>
</tr>
</c:forEach>
</tbody>
</table>

<p><a href="${pageContext.request.contextPath}/index.html">Strona główna</a></p>

</body>
</html>