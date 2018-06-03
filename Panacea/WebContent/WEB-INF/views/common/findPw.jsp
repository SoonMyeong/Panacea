<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
</head>

<link href="https://fonts.googleapis.com/css?family=Do+Hyeon|Didact+Gothic" rel="stylesheet">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" />
<script src="<%=request.getContextPath() %>/js/jquery-3.3.1.js"></script>

<script>
var findType;
var userId;
var userName;
var phone;

function findCheckValidate(){
	
	if($("#userId").val().trim().length < 4){
		alert("아이디를 다시 입력하세요");
		$("#userId").val("");
		$("#userId").focus();
		return false;
	}
	
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

function findCheck(){
	
	var bool = findCheckValidate();
	
	$("[name=findType]").each(function(){
		if($(this).is(":checked")){
			findType = $(this).val();
		}
	});
	console.log(findType);
	userId = $("#userId").val().trim();
	userName = $("#userName").val().trim();
	phone = $("#phone").val().trim();
	
	if(bool){
		$.ajax({
			url:"<%=request.getContextPath()%>/common/findPwCheck",
			dataType:"json",
			data:"findType="+findType+"&userId="+userId+"&userName="+userName+"&phone="+phone,
			type:"post",
			success:function(data){
				
				if(data == 1){
					$("#find-title").text("비밀번호 변경");
					$("#find-wrapper").css("display","none");
					$("#pw-wrapper").css("display","initial");
				} else {
					alert("검색된 회원이 없습니다.");
					self.close();
				}
			},
			error:function(jqxhr,textStatus,errorThrown){
				console.log("ajax 에러!");
				console.log(jqxhr);
				console.log(textStatus);
				console.log(errorThrown);
			}
		});
	}
	
}

function findPwValidate(){
	
	var regExp = /^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&*+=-]).*$/g;
	if(!regExp.test($("#password").val())){
		alert("비밀번호는 8~15글자이며 특수문자,영대소문자 포함하여야 합니다");
		$("#userPw").val("");
		$("#userPw").focus();
		return false;
	}
	
	if($("#password").val() != $("#new_password").val()){
		alert("비밀번호와 비밀번호 확인이 동일하지 않습니다");
		$("#new_password").val("");
		$("#new_password").focus();
		return false;
	}
	
	$("#findType2").attr("value",findType);
	$("#userId2").attr("value",userId);
	return true;
}

</script>

<body id="popup">
	<h3 id="find-title">비밀번호 찾기</h3>
	<hr id="part-partition">
	
	<div id="find-wrapper">
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
			<th>아이디</th>
			<td>
				<input type="text" name="userId" id="userId">
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
				<input type="button" id="find-submit" value="비밀번호 찾기" onclick="findCheck();">
			</td>
		</tr>
	</table>
	</div>
	
	<div id="pw-wrapper">
		<form action="<%=request.getContextPath()%>/common/findPwEnd" method="post">
		<input type="hidden" name="findType" id="findType2">
		<input type="hidden" name="userId" id="userId2">
		<table id="find-table">
			<tr>
				<th>비밀번호</th>
				<td>
					<input type="password" name="password" id="password">
				</td>
			</tr>
			<tr>
				<th>비밀번호 확인</th>
				<td>
					<input type="password" name="new_password" id="new_password">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" id="find-update-submit" value="비밀번호 수정" onclick="return findPwValidate();">
				</td>
			</tr>
		</table>
		</form>
	</div>
	
</body>
<style>
div#pw-wrapper{
	display: none;
}
</style>
</html>
