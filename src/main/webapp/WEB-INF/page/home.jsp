<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Strona główna</title>
</head>
<body>
<h1>Strona główna</h1>
<p>
${message}<br/>
<a href="${pageContext.request.contextPath}/tree/add.html">Dodaj dział</a><br/>
<a href="${pageContext.request.contextPath}/tree/list.html">Zobacz hierarchie firmy</a><br/>
</p>
</body>
</html>