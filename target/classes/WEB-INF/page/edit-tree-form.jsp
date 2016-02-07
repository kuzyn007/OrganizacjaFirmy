<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<?xml version="1.0" encoding="UTF-8" ?>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit tree page</title>
</head>
<body>
<h1>Edytuj dział</h1>
<p>Tutaj możesz edytować istniejący dział.</p>
<p>${message}</p>
<form:form method="POST" commandName="tree" action="${pageContext.request.contextPath}/tree/edit/${tree.id}.html">
<table>
<tbody>
	<tr>
		<td>Nazwa działu:</td>
		<td><form:input path="nazwa" /></td>
	</tr>
	<tr>
		<td>Dział do którego przynależy:</td>
		<td><form:input path="numerid" /></td>
	</tr>
	<tr>
		<td><input type="submit" value="Edytuj" /></td>
		<td></td>
	</tr>
</tbody>
</table>
</form:form>

<p><a href="${pageContext.request.contextPath}/index.html">Strona główna</a></p>
</body>
</html>