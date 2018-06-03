<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String findId = (String)request.getAttribute("findId");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
</head>

<link href="https://fonts.googleapis.com/css?family=Do+Hyeon|Didact+Gothic" rel="stylesheet">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" />

<body id="popup">
	<h3 id="find-title">아이디 찾기</h3>
	<hr id="part-partition">
	
	<p id="find-result">
		회원님의 아이디는<br>
		<span>" <%=findId %> "</span><br>
		입니다
	</p>
	
	<input type="button" id="close-btn" value="닫기" onclick="self.close();">
</body>
</html>