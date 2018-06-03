<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/views/common/header.jsp" %>


<div id="subnav-wrapper">
	<span id="subnav-title">로그인</span>
	<div id="sub_menu">
		<p class="submenu login"><a href="<%=request.getContextPath()%>/common/login">로그인</a></p>
	    <p class="submenu joinView"><a href="<%=request.getContextPath()%>/patient/join">회원가입</a></p>
	</div>
</div>
	
	<!-- 아이디 중복검사 -->
	<script>
	$(function(){
		$("#btn-idValid").click(function(){
			var id= $("#userId").val().trim();
			
			if(id.length<4){
				alert("아이디는 4글자이상 가능합니다.");
			}else{
			
				$.ajax({
					url:"<%=request.getContextPath()%>/patient/checkDuplicate",
					dataType:"json",
					data:"id="+id,
					type:"get",
					success:function(data){
						console.log(data);
						var html="";
						if(data==null){
							$("#idValid").attr("value","1");
							console.log($("#idValid").val());
		                	html += "<span style='color:green;'>사용 가능한 아이디 입니다.</span>";
			                $("#id-check").html(html);
			            }else{
			            	$("#idValid").attr("value","0");
			            	console.log($("#idValid").val());
			            	html += "<span style='color:red;'>이미 사용중인 아이디 입니다.</span>";
			                $("#id-check").html(html);
						}
					},
					error : function(jqxhr,textStatus,errorThrown){
						console.log("ajax 에러!");
						console.log(jqxhr);
						console.log(textStatus);
						console.log(errorThrown);
					}
				});
			}
		});
		
		$("#self-address").change(function(){
			if($(this).is(":checked")){
				console.log($(this).val());
				$("#address").css("display","none");
				$("#self-address-input").css("display","block");	
			} else {
				console.log($(this).val());
				$("#address").css("display","block");
				$("#self-address-input").css("display","none");
				$("#address-city").val("-");
				$("#address-local").val("-");
			}
		});
		
	});
	</script>
	
	<!-- 유효성 검사  -->
	<script>
	function fn_joinVaildate(){
		
		var regExp6 = /^[a-zA-z]{4,}$/;
		if(!regExp6.test($("#userId").val())){
			alert("아이디는 영문자로 4글자 이상 가능합니다");
			$("#userId").val("");
			$("#userId").focus();
			return false;
		}
		
		if($("#idValid").val()!='1'){
	    	alert("아이디 중복검사를 실시하세요.");
	    	return false;
	    }
	 	
	 	var regExp = /^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&*+=-]).*$/g;
		if(!regExp.test($("#userPw").val())){
			alert("비밀번호는 8~15글자이며 특수문자,영대소문자 포함하여야 합니다");
			$("#userPw").val("");
			$("#userPw").focus();
			return false;
		}
	 	
	 	if($("#userPw").val().trim()!=$("#userPw_chk").val().trim()){
	    	alert("두 비밀번호가 같지 않습니다.");
	    	$("#userPw_chk").val("");
	    	$("#userPw_chk").focus();
	    	return false;
	    }
	 	
	 	var regExp0 = /^[가-힣]{2,}$/;
		if(!regExp0.test($("#userName").val())){
			alert("2글자 이상인 한글 이름으로 입력하세요");
			$("#userName").val("");
			$("#userName").focus();
			return false;
		}
	 	
	 	var regExp2 = /^[0-9]{2}((0[13578]|1[02])(0[1-9]|1[0-9]|2[0-9]|3[01])|(0[469]|11)(0[1-9]|1[0-9]|2[0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))$/;
	 	if(!regExp2.test($("#ssd1").val().trim())){
	 		alert("주민등록번호 앞자리를 확인해주세요");
			$("#ssd1").val("");
			$("#ssd1").focus();
			return false;
	 	}
	 	
	 	var regExp3 = /^[1-4]{1}$/;
	 	if(!regExp3.test($("ssd2").val().trim())){
	 		alert("주민등록번호 앞자리를 확인해주세요");
			$("#ssd2").val("");
			$("#ssd2").focus();
			return false;
	 	}
	 	
	 	var regExp4 = /^(010|070)[0-9]{8}$/;
		if(!regExp4.test($("#phone").val().trim())){
			alert("전화번호를 다시 입력해주세요");
			$("#phone").val("");
			$("#phone").focus();
			return false;
		}
		
		if($("#self-address").is(":checked")){
			if($("#self-address-input").val().trim().length < 5){
				alert("주소를 입력하세요");
				return false;
			}
			var regExp5 = /^[가-힣]{2,4}시 [가-힣]{2,4}구$/;
			if(!regExp5.test($("#self-address-input").val())){
				alert("주소를 다시 입력하세요");
				return false;
			}
		} else {
			if($("#address-city").val() == "-" || $("address-local").val() == "-"){
				alert("주소를 선택하세요");
				return false;
			}
		}
			 	
		return true;
	}
	</script>
	
	
<div id="content-wrapper">
	
	<form name="patient_join_form" action="<%=request.getContextPath()%>/patient/joinEnd" method="post" onsubmit="return fn_joinVaildate();">
		<table id="join-table">
			<tr>
				<td colspan="2">
					<span id="join-notice">*환자만 회원가입 할 수 있습니다.</span>
					<br>
					<span id="join-notice">*모든 항목은 필수사항입니다.</span>
				</td>
			</tr>
			<tr>
 				<td class="la">아이디 :</td>
				<td>
					<input type="text" id="userId" name="userId" > &nbsp;&nbsp;
					<input type="button" id="btn-idValid" value="중복체크"><br>
					<span id="id-check"></span>
					<input type="hidden" id="idValid" value="0" />
				</td>
			</tr>
			<tr>
				<td class="la">비밀번호 :</td>
				<td><input type="password" id="userPw" name="userPw" ></td>
			</tr>
			<tr>
				<td class="la">비밀번호 확인 :</td>
				<td><input type="password" id="userPw_chk" ></td>
			</tr>
			<tr>
				<td class="la">이름 :</td>
				<td><input type="text" id="userName" name="userName"></td>
			</tr>
			<tr>
				<td class="la">주민등록번호 :</td>
				<td>
					<input type="text" id="ssd1" name ="ssd1" placeholder="930318">&nbsp;-&nbsp;
					<input type="text" id="ssd2" name="ssd2" placeholder="1" >
				</td>
			</tr>
			<tr>
				<td class="la">전화번호 :</td>
				<td><input type="text" id="phone" name="phone" placeholder="'-'없이 입력"></td>
			</tr>
			<tr>
				<td class="la">주소 :</td>
				<td>
					<input type="checkbox" name="self-address" id="self-address">
					<label for="self-address" id="self-address-label">직접입력</label><br>
					<div id="self-address-input">
						<input type="text" name="self-address-input" id="self-address-input" placeholder="○○시 ○○구">
					</div>
					<div id="address">
					<select name="address-city" id="address-city">
						<option value="-" selected disabled>--시 선택--</option>
						<option value="서울시">서울</option>
					</select>
					<select name="address-local" id="address-local">
						<option value="-" selected disabled>--구 선택--</option>
						<option value="종로구">종로구</option>
						<option value="중구">중구</option>
						<option value="용산구">용산구</option>
						<option value="성동구">성동구</option>
						<option value="광진구">광진구</option>
						<option value="동대문구">동대문구</option>
						<option value="중랑구">중랑구</option>
						<option value="성북구">성북구</option>
						<option value="강북구">강북구</option>
						<option value="도봉구">도봉구</option>
						<option value="노원구">노원구</option>
						<option value="은평구">은평구</option>
						<option value="서대문구">서대문구</option>
						<option value="마포구">마포구</option>
						<option value="양천구">양천구</option>
						<option value="강서구">강서구</option>
						<option value="구로구">구로구</option>
						<option value="금천구">금천구</option>
						<option value="영등포구">영등포구</option>
						<option value="동작구">동작구</option>
						<option value="관악구">관악구</option>
						<option value="서초구">서초구</option>
						<option value="강남구">강남구</option>
						<option value="송파구">송파구</option>
						<option value="강동구">강동구</option>
					</select>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="회원가입"> &nbsp;
					<input type="reset" value="초기화">
				</td>
			</tr>
		</table>
	</form>
	
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>