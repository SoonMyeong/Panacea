<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ page import="com.panacea.doctor.model.vo.*" %>



<div id="subnav-wrapper">
   <span id="subnav-title">마이페이지</span>
   <div id="sub_menu">
		<p class="submenu patientList"><a href="<%=request.getContextPath()%>/<%=loginType %>/patientList">환자 관리</a></p>
		<p class="submenu doctorList"><a href="<%=request.getContextPath()%>/<%=loginType %>/doctorList">의사 관리</a></p>
		<p class="submenu adminView"><a href="<%=request.getContextPath()%>/admin/adminView">관리자정보</a></p>
   </div>
</div>

<!-- 유효성 검사  -->
	<script>
	function fn_joinVaildate(){
		var fileName = $("[name=up_file]").val();
		var doctorId = $("#userId").val();
		var result= fileName.substring(fileName.lastIndexOf("\\")+1,fileName.lastIndexOf("."));
		var resultJPG = result+".jpg";
		var resultPNG = result+".png";
		var checkNameJPG = doctorId+".jpg";
		var checkNamePNG = doctorId+".png";
		
		
		if(result!="" && checkNameJPG!=resultJPG
				|| result!="" && checkNamePNG!=resultPNG){
			
			alert("파일이름은 아이디와 동일하게! 확장자는 jpg,png 만 가능");
			return false;
		}
		
	 	if($("#userPw").val().trim()!=$("#passwordChk").val().trim()){
	    	alert("두 비밀번호가 같지 않습니다.");
	    	return false;
	    }
	 	
	 	if(result==""){
	 		alert("사진을 업로드 하셔야 합니다.");
	 		return false;
	 	}
		
		return true;
	}
	</script>
		
<style>
table#tbl-join{
    /* border: 2px solid gray; */
    border-top: 2px solid gray;
    border-bottom: 2px solid gray;
    border-spacing: 30px;
  	margin: 0 auto;
  	margin-top:50px;
  	margin-bottom:50px;
    font-family: 'Do Hyeon', sans-serif;
}
table#tbl-join tr td span{
margin-left: 60px;
}
table#tbl-join tr td span input{
font-family: 'Do Hyeon', sans-serif;
}
span#fname{
	position: absolute;
	left:17px;
	top:2px;
	width:285px;
	background: #fbfbfb;
}
</style>

<div id="content-wrapper">
	<form name="doctor_join_form" action="<%=request.getContextPath()%>/admin/doctorJoinEnd" method="post" enctype="multipart/form-data" onsubmit="return fn_joinVaildate();">
		<table id="tbl-update">
            <tr>
                <td colspan="2"><h3>의사 등록</h3></td>
            </tr>
            <tr>
                <td>아이디 : </td>
                <td><input type="text" id="userId" name="userId"></td>
            </tr>
            
            <tr>
                <td>비밀번호 : </td>
                <td><input type="password" id="userPw" name="userPw"></td>
            </tr>
            
             <tr>
                <td>비밀번호 확인 : </td>
                <td><input type="password" id="passwordChk" name="passwordChk"></td>
            </tr>           
            
            <tr>
                <td>이 름 : </td>
                <td><input type="text" id="userName" name="userName"></td>
            </tr>
            
             <tr>
                <td>주민등록번호 : </td>
                <td><input type="text" id="ssd" name="ssd"></td>
            </tr>
            
             <tr>
                <td>파트명</td>
                <td>
               		<select name="partName">
               			<optgroup label="외과">
               				<option value="정형외과-P2D1" >정형외과</option>
               				<option value="신경외과-P2D2" >신경외과</option>
               				<option value="흉부외과-P2D3" >흉부외과</option>
               				<option value="가정의학과-P3" >가정의학과</option>
               				<option value="산부인과-P4" >산부인과</option>
               				<option value="성형외과-P5" >성형외과</option>
               				<option value="비뇨기과-P6" >비뇨기과</option>
               				<option value="피부과-P7">피부과</option>
               			</optgroup>
               			<optgroup label="내과">
               				<option value="안과-P9">안과</option>
               				<option value="치과-P10" >치과</option>
               				<option value="일반내과-P1D1" >일반내과</option>
               				<option value="호흡기내과-P1D2" >호흡기내과</option>
               				<option value="소화내과-P1D3" >소화외과</option>
               				<option value="신장내과-P1D4" >신장외과</option>
               				<option value="정신건강의학과-P8" >정신건강의학과</option>
               			</optgroup>
               		</select>
                </td>
            </tr>
            
            <tr>
            	<td>상세 파트</td>
            	<td><input type="text" name="introduce"  /></td>
            </tr>
            
            <tr>
                <td>프로필사진 </td>
				<td>
					<input type="file" name="up_file"/>
				</td>
                
            </tr>
            <tr>
                <td>전화번호</td>
                <td><input type="text" name="phone"></td>
            </tr>
              <tr>
                <td>주소</td>
                <td><input type="text" name="address"></td>
            </tr>
            <tr>
              <td colspan="2">
              	<span>
	              	<input  type="submit" value="등록">&nbsp;
	    	  	</span>
	    	  </td>
            </tr>
        </table>
      </form>   
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>