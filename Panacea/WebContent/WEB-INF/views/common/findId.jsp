<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디찾기</title>
</head>

<link href="https://fonts.googleapis.com/css?family=Do+Hyeon|Didact+Gothic" rel="stylesheet">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" />
<script src="<%=request.getContextPath() %>/js/jquery-3.3.1.js"></script>

<script>
function findIdValidate(){
	
	if($("#userName").val().trim().length < 2){
		alert("이름을 다시 입력해주세요");
		$("#userName").val("");
		$("#userName").focus();
		return false;
	}
	
	var regExp = /^[0-9]{11}$/;
	if(!regExp.test($("#phone").val().trim())){
		alert("전화번호를 다시 입력해주세요");
		$("#phone").val("");
		$("#phone").focus();
		return false;
	}
	
	return true;
}
</script>

<body id="popup">
	<h3 id="find-title">아이디 찾기</h3>
	<hr id="part-partition">
	
	<form action="<%=request.getContextPath()%>/common/findIdEnd" method="post">
	<table id="find-table">
		<tr>
			<td colspan="2">
				<input type="radio" name="findType" id="patient" value="patient" checked >
				<label for="patient">환자</label>
				<input type="radio" name="findType" id="doctor" value="doctor" >
				<label for="doctor">의사</label>
				<input type="radio" name="findType" id="admin" value="admin" >
				<label for="admin">관리자</label>
			</td>
		</tr>
		<tr>
			<th>이름</th>
			<td>
				<input type="text" name="userName" id="userName">
			</td>
		</tr>
		<tr>
			<th>전화번호</th>
			<td>
				<input type="text" name="phone" id="phone" placeholder="'-'없이 숫자만 입력하세요.">
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" id="find-submit" value="아이디 찾기" onclick="return findIdValidate();">
			</td>
		</tr>
	</table>
	</form>
</body>
</html>
