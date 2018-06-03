<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ page import="com.panacea.admin.model.vo.*,
				java.util.*" %>

<% Admin list = (Admin)request.getAttribute("list"); %>


<div id="subnav-wrapper">
   <span id="subnav-title">마이페이지</span>
   <div id="sub_menu">
       <p class="submenu patientList"><a href="<%=request.getContextPath()%>/<%=loginType %>/patientList">환자 관리</a></p>
		<p class="submenu doctorList"><a href="<%=request.getContextPath()%>/<%=loginType %>/doctorList">의사 관리</a></p>
       <p class="submenu adminView"><a href="<%=request.getContextPath()%>/admin/adminView">관리자정보</a></p>
   </div>
</div>

<script>
<%-- function fn_update_password(){
	var url = "<%=request.getContextPath()%>/patient/updatePassword";	
	var title = "updatePassword";
	var status = "left=500px, top=200px, width=400px, height=210px";
	
	var userId = $("#patientId").val().trim();
	
	var popup = window.open(url,title,status);
	
	var updatePasswordFrm = document.updatePasswordFrm;
	
	updatePasswordFrm.userId.value = userId;
	
	//팝업과 form연결
	updatePasswordFrm.target = title;
	updatePasswordFrm.action = url;
	updatePasswordFrm.submit();
	
	//팝업과 form연결
	updatePasswordFrm.target = title;
	updatePasswordFrm.action = url;
	updatePasswordFrm.submit();
} --%>
	
</script>


<div id="content-wrapper">
   	<div id="mypage-wrapper">
	    <h2>관리자정보</h2>
	       <br>
	       <table id="tbl-mypageList">
	           <tr>
	               <th id="wrapper">아이디</th>
	               <th id="wrapper">이름</th>
	               <th id="wrapper">주민번호</th>
	               <th id="wrapper">핸드폰</th>
	               <th id="wrapper">주소</th>
	           </tr>
	           <%if(list==null){ %>
				<tr>
					<td colspan="5" align="center">데이터가 존재하지 않습니다.</td>
				</tr>
		<%} else { 
			
		%>		
			<tr>
				<td id="wrapper"><%=list.getAdminId()%></td>			
				<td id="wrapper"><%=list.getAdminName()%></td>
				<td id="wrapper"><%=list.getSsd()%>******</td>
				<td id="wrapper"><input type="text" name="phone" value="<%=list.getPhone()%>"></td>
				<td id="wrapper"><%=list.getAddress()%></td>
			</tr>
		<%
		} %>   
        </table>	 
    </div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>