<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자에게 문의하세요</title>
</head>

<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" />

<body id="error-body">
	<fieldset id="error-wrapper">
		<legend>
			<p id="error-title">WARNING</p>
		</legend>
			<img src="<%=request.getContextPath() %>/img/error.png" alt="warning">
			<img src="<%=request.getContextPath() %>/img/error.png" alt="warning">
			<img src="<%=request.getContextPath() %>/img/error.png" alt="warning">
			<h1 id="error-title">Panacea Error</h1>
			<p>관리자에게 문의하세요</p>
			<p><span id="e-msg"><%=exception.getMessage() %></span></p>
	</fieldset>
</body>
</html>