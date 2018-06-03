<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String userId = (String)request.getAttribute("userId");
	System.out.println("userid@doctorUpdatePassword="+userId); 
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>비밀번호 변경</title>

<link href="https://fonts.googleapis.com/css?family=Do+Hyeon|Didact+Gothic" rel="stylesheet">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" />

<script src="<%=request.getContextPath()%>/js/jquery-3.3.1.js"></script>
<script>

function fn_passwordValidate(){
	if($("#new_password").val().trim() != $("#new_password_chk").val().trim()){
		alert("비밀번호가 동일하지 않습니다.");
		$("#new_password_chk").focus();
		return false;
	}
	
	var regExp2 = /^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&*+=-]).*$/g; 
	
	if(!regExp2.test($("#new_password").val().trim())){
    	alert("비밀번호는 8~15글자이며 특수문자,영대소문자 포함하여야 합니다");
    	return false;
    }
	
	
	return true;
	
}
</script>

</head>
<body id="updatePw">
<div id="updatePW-container">
		<form action="<%=request.getContextPath() %>/doctor/updatePasswordEnd" method="post" onsubmit="return fn_passwordValidate();">
			<label for="now_password">현재 비밀번호</label>
			<input type="password" name="now_password" id="now_password" /><br />
			<label for="new_password">새 비밀번호</label>
			<input type="password" name="new_password" id="new_password" /><br />
			<label for="new_password_chk">새 비밀번호 확인</label>
			<input type="password" name="new_password_chk" id="new_password_chk" /><br />
			<input type="submit" value="비밀번호변경"  id="btn-submit"/>
			<input type="hidden" name="userId" value=<%=userId%>>
		</form>
	</div>
</body>
</html>