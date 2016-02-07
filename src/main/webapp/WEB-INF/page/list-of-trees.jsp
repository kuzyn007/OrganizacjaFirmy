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

<c:forEach var="tree" items="${trees}">
	[<a href="${pageContext.request.contextPath}/tree/edit/${tree.id}.html">Edytuj</a>/
	<a href="${pageContext.request.contextPath}/tree/delete/${tree.id}.html">Usuń</a>]
	<c:forEach var="i" begin="1" end="${tree.level}">
	&nbsp;&nbsp;&nbsp;
	</c:forEach>
	-&nbsp;${tree.name} 
	<br />
</c:forEach>

<p><a href="${pageContext.request.contextPath}/index.html">Strona główna</a></p>

</body>
</html>