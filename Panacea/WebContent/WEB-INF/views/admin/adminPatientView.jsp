<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ page import="com.panacea.patient.model.vo.*" %>

<% Patient p = (Patient)request.getAttribute("patient"); %>

<style>


</style>

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
	function fn_updateVaildate(){
		
		return true;
	}
	</script>
	
	<!-- 비밀번호 변경 팝업, 회원탈퇴 -->
	<script>
	function fn_update_password(){
		var url = "<%=request.getContextPath()%>/patient/updatePassword";	
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
	function fn_deletePatient(){
		confirm("정말로 탈퇴를 진행하시겠습니까..?") ? location.href='<%=request.getContextPath()%>/patient/deleteEnd?id=<%=p.getPatientId()%>' : "";
	}
	</script>


<div id="content-wrapper">
	<form name="patient_update_form" action="<%=request.getContextPath()%>/patient/updateEnd" method="post" onsubmit="return fn_updateVaildate();">
		<table id="tbl-update">
            <tr>
                <td colspan="2"><h3>개인정보 변경</h3></td>
            </tr>
            <tr>
                <td>이 름 : </td>
                <td><input type="text" id="userName" name="userName" value="<%=p.getPatientName() %>" readOnly></td>

            </tr>
            <tr>
                <td>아이디 : </td>
                <td><input type="text" id="userId" name="userId" value="<%=p.getPatientId() %>" readOnly></td>
            </tr>
            
            <tr>
                <td>핸드폰 번호 : </td>
                <td><input type="text" name="phone" value="<%=p.getPhone()%>"></td>
                
            </tr>
            <tr>
                <td>주 소 : </td>
                <td><input type="text" name="address" value="<%=p.getAddress()%>"></td>
            </tr>
            <tr>
              <td colspan="2">
              	<span>
	              	<input  type="submit" value="수정">&nbsp;
		    	  	<input  type="button" value="비밀번호변경" onclick="fn_update_password();"> 
		    	  	<input type="button" value="탈퇴" onclick="fn_deletePatient();">
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