<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String msg = (String)request.getAttribute("msg");
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
	<h3 id="find-title">비밀번호 변경</h3>
	<hr id="part-partition">
	
	<p id="find-result">
		회원님의 비밀번호 변경을<br>
		<span>" <%=msg %> "</span><br>
		하였습니다
	</p>
	
	<input type="button" id="close-btn" value="닫기" onclick="self.close();">
</body>
</html>