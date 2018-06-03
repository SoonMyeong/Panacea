<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.panacea.doctor.model.vo.*, java.util.*" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<% 
	List<Doctor> list = (List<Doctor>)request.getAttribute("list"); 
	System.out.println("list@doctorList.jsp="+list);	
	
	
%>


<div id="subnav-wrapper">
   <span id="subnav-title">마이페이지</span>
   <div id="sub_menu">
       <p class="submenu patientList"><a href="<%=request.getContextPath()%>/<%=loginType %>/patientList">환자 관리</a></p>
		<p class="submenu doctorList"><a href="<%=request.getContextPath()%>/<%=loginType %>/doctorList">의사 관리</a></p>
		<p class="submenu adminView"><a href="<%=request.getContextPath()%>/admin/adminView">관리자정보</a></p>
   </div>
</div>

<script>
$(function(){
	
	$('#tbl-mypageList tr').click(function(){
		var did = $(this).attr("did");
		//제목 tr을 선택한 경우
		if(did==undefined) return;
		location.href="<%=request.getContextPath()%>/admin/adminDoctorView?did="+did;
		
	});
	
});
function fn_Join(){
	location.href="<%=request.getContextPath()%>/admin/adminDoctorJoin";
}
</script>

<div id="content-wrapper">
<div id="mypage-wrapper">
	<h2>의사 관리</h2>
	<table id="tbl-mypageList">
		<tr>
			<th>의사 아이디</th>
			<th>의사 이름</th>
			<th>의사 프로필</th>
			<th>파트 아이디</th>
			<th>파트 명</th>
			<th>전화번호</th>
			<th>주민등록번호</th>
			<th>주소</th>
		</tr>
		
			<%
		if(list!=null && ! list.isEmpty()){
			for(Doctor d : list) { 			
			%>
				<tr did="<%=d.getDoctorId() %>">
					<td><%=d.getDoctorId() %></td>
					<td><%=d.getDoctorName() %></td>
					<td><%=d.getDoctorProfile() %></td>
					<td><%=d.getPartId()%></td>
					<td><%=d.getPartName()%></td>
					<td><%=d.getPhone() %></td>
					<td><%=d.getSsd()%></td>
					<td><%=d.getAddress() %></td>
				</tr>
			 
		<%}
		}else{ %>
		<tr>
			<td colspan="8">등록된 회원이 없습니다.</td>
		</tr>
	<%} %>
	</table>
	<input id="admin-btn" type="button" id="register" value="의사 등록" onclick="fn_Join();" />
	<br />
	<div class="pagination" id="doctorPageBar">
		<%=request.getAttribute("pageBar") %>
	</div>
</div>
</div>



<%@ include file="/WEB-INF/views/common/footer.jsp" %>