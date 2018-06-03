<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	Cookie[] cookies = request.getCookies();
	
	boolean saveId = false;
	boolean saveType = false;
	String savedUserId = "";
	String savedUserType = "";
	
	if(cookies != null){
		
		for(Cookie c : cookies){
			String key = c.getName();
			String value = c.getValue();
			
			if("saveId".equals(key)){
				saveId = true;
				savedUserId = value;
			}
			if("saveType".equals(key)){
				saveType = true;
				savedUserType = value;
			}
		}
	}
%>

<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div id="subnav-wrapper">
	<span id="subnav-title">로그인</span>
	<div id="sub_menu">
	    <p class="submenu login"><a href="<%=request.getContextPath()%>/common/login">로그인</a></p>
	    <p class="submenu joinView"><a href="<%=request.getContextPath()%>/patient/join">회원가입</a></p>
	</div>
</div>

<div id="content-wrapper">
	
	<form action="<%=request.getContextPath() %>/common/loginEnd" method="post">
	<table id="login-table">
		<tr>
			<td colspan="2">
				<input type="radio" name="loginType" id="patient" value="patient" <%=("patient".equals(savedUserType)||!saveType)?"checked":"" %> >
				<label for="patient">환자</label>
				<input type="radio" name="loginType" id="doctor" value="doctor" <%="doctor".equals(savedUserType)?"checked":"" %> >
				<label for="doctor">의사</label>
				<input type="radio" name="loginType" id="admin" value="admin" <%="admin".equals(savedUserType)?"checked":"" %> >
				<label for="admin">관리자</label>
			</td>
		</tr>
		<tr>
			<th>ID</th>
			<td><input type="text" id="userId" name="userId" value="<%=saveId?savedUserId:"" %>" ></td>
		</tr>
		<tr>
			<th>PW</th>
			<td><input type="password" id="userPw" name="userPw"></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="checkbox" name="saveId" id="saveId" <%=saveId?"checked":"" %> >
				<label for="saveId">아이디저장</label>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="로그인" id="loginBtn" onclick="return loginValidate()">
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<button type="button" id="findId">아이디찾기</button>
				<button type="button" id="findPw">비밀번호찾기</button>
			</td>
		</tr>
	</table>
	</form>
	
</div>

<script>
	function loginValidate(){
		
		if($("#userId").val().trim().length == 0){
			alert("아이디를 입력하세요");
			$("#userId").focus();
			return false;
		}
		if($("#userPw").val().trim().length == 0){
			alert("비밀번호를 입력하세요");
			$("#userPw").focus();
			return false;
		}
		
		return true;
	}
	
	$("#findId").on("click",function(){
		var url = "<%=request.getContextPath()%>/common/findId";
		var title = "아이디 찾기";
		var status = "left=400px, top=200px, width=500px, height=350px";
		
		var popup = window.open(url, title, status);
	});
	
	$("#findPw").on("click",function(){
		var url = "<%=request.getContextPath()%>/common/findPw";
		var title = "비밀번호 찾기";
		var status = "left=400px, top=200px, width=500px, height=350px";
		
		var popup = window.open(url, title, status);
	});
</script>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>