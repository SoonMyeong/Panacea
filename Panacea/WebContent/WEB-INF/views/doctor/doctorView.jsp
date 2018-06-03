<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ page import="com.panacea.doctor.model.vo.*" %>

<%
	Doctor d = (Doctor)request.getAttribute("doctor");
	String profile = d.getDoctorProfile();
	String etc = profile.substring(profile.lastIndexOf("."));
	
	if(!profile.equals(d.getDoctorId())){
		profile= profile.substring(0,d.getDoctorId().length())+etc;
	}
	
	System.out.println(d.getPartName());
%>

<style>


</style>

<div id="subnav-wrapper">
   <span id="subnav-title">마이페이지</span>
   <div id="sub_menu">
       <p class="submenu updateView"><a href="<%=request.getContextPath()%>/<%=loginType %>/<%=loginType %>Mypage?userId=<%=loginDoctor.getDoctorId() %>">개인정보변경</a></p>
       <p class="submenu reservationList"><a href="<%=request.getContextPath()%>/reservation/<%=loginType %>ReservationList?userId=<%=loginDoctor.getDoctorId() %>">예약현황/차트작성</a></p>
   		   		<p class="submenu chartList"><a href="<%=request.getContextPath()%>/chart/<%=loginType %>ChartList?userId=<%=loginDoctor.getDoctorId() %>">차트리스트</a></p>
   </div>
</div>

<!-- 유효성 검사  -->
	<script>
	function fn_updateVaildate(){
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
		
		return true;
	}
	</script>
	
	<!-- 비밀번호 변경 팝업, 회원탈퇴 -->
	<script>
	function fn_update_password(){
		var url = "<%=request.getContextPath()%>/doctor/updatePassword";	
		var title = "updatePassword";
		var status = "left=500px, top=200px, width=400px, height=210px";
		
		var userId = $("#userId").val().trim();
		
		var popup = window.open(url,title,status);
		
		var updatePasswordFrm = document.updatePasswordFrm;
		
		updatePasswordFrm.userId.value = userId;
		
		//팝업과 form연결
		updatePasswordFrm.target = title;
		updatePasswordFrm.action = url;
		updatePasswordFrm.submit();

	}
	</script>
	
	<script>
	$(function(){
		$("[name=up_file]").change(function(){
			//$(this).val()은 선택한 파일명
			if($(this).val()==""){
				$("#fname").show();
			}else{
				$("#fname").hide();
			}
		});
	});
	</script>

<div id="content-wrapper">
	<form name="doctor_update_form" action="<%=request.getContextPath()%>/doctor/updateEnd" method="post" enctype="multipart/form-data" onsubmit="return fn_updateVaildate();">
		<table id="tbl-update">
            <tr>
                <td colspan="2"><h3>개인정보 변경</h3></td>
            </tr>
            <tr>
                <td>이 름 : </td>
                <td><input type="text" id="userName" name="userName" value="<%=d.getDoctorName() %>" readOnly></td>

            </tr>
            <tr>
                <td>아이디 : </td>
                <td><input type="text" id="userId" name="userId" value="<%=d.getDoctorId() %>" readOnly></td>
            </tr>
            
             <tr>
                <td>진료과</td>
                <td>
               		<select name="partName">
               			<optgroup label="외과">
               				<option value="정형외과" <%="정형외과".equals(d.getPartName())?"selected":""%>>정형외과</option>
               				<option value="신경외과" <%="신경외과".equals(d.getPartName())?"selected":""%>>신경외과</option>
               				<option value="흉부외과" <%="흉부외과".equals(d.getPartName())?"selected":""%>>흉부외과</option>
               				<option value="혈관외과" <%="혈관외과".equals(d.getPartName())?"selected":""%>>혈관외과</option>
               			</optgroup>
               			<optgroup label="내과">
               				<option value="일반내과" <%="일반내과".equals(d.getPartName())?"selected":""%>>일반내과</option>
               				<option value="호흡기내과" <%="호흡기내과".equals(d.getPartName())?"selected":""%>>호흡기내과</option>
               				<option value="소화내과" <%="소화내과".equals(d.getPartName())?"selected":""%>>소화외과</option>
               				<option value="신장내과" <%="신장내과".equals(d.getPartName())?"selected":""%>>신장외과</option>
               			</optgroup>
               			
               			<option disabled></option>
               			<option value="가정의학과" <%="가정의학과".equals(d.getPartName())?"selected":""%>>가정의학과</option>
						<option value="산부인과" <%="산부외과".equals(d.getPartName())?"selected":""%>>산부인과</option>
						<option value="성형외과" <%="성형외과".equals(d.getPartName())?"selected":""%>>성형외과</option>
						<option value="비뇨기과" <%="비뇨기과".equals(d.getPartName())?"selected":""%>>비뇨기과</option>
						<option value="피부과" <%="피부과".equals(d.getPartName())?"selected":""%>>피부과</option>
						<option value="정신건강의학과" <%="정신건강의학과".equals(d.getPartName())?"selected":""%>>정신건강의학과</option>
						<option value="안과" <%="안과".equals(d.getPartName())?"selected":""%>>안과</option>
						<option value="치과" <%="치과".equals(d.getPartName())?"selected":""%>>치과</option>
               		</select>
                </td>
            </tr>
            
            <tr>
                <td>프로필사진 </td>
				<td style="position:relative;">
					<%if(d.getDoctorProfile()!=null){ %>
						<!-- 파일태그에 value속성은 임의로 변경할 수 없음 -->
						<input type="file" name="up_file"/>
						<span id="fname"><%=profile %></span>
						<!-- 파일변경대비 기존파일이름 필드 -->
						<input type="hidden" name="old_file" value="<%=d.getDoctorProfile()%>"/>
					<%}else { %>
						<input type="file" name="up_file"/>
					<%} %>
				</td>
                
            </tr>
            <tr>
                <td>전화번호</td>
                <td><input type="text" name="phone" value="<%=d.getPhone()%>"></td>
            </tr>
              <tr>
                <td>주소</td>
                <td><input type="text" name="address" value="<%=d.getAddress()%>"></td>
            </tr>
            <tr>
              <td colspan="2">
              	<span>
	              	<input  type="submit" value="수정">&nbsp;
		    	  	<input  type="button" value="비밀번호변경" onclick="fn_update_password();"> 
	    	  	</span>
	    	  </td>
            </tr>
        </table>
      </form>   
      <form name="updatePasswordFrm" method="post">
			<input type="hidden" name="userId" />
	  </form>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>