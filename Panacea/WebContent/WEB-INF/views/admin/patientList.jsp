<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.panacea.patient.model.vo.*, java.util.*" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<% 
	List<Patient> list = (List<Patient>)request.getAttribute("list"); 
	System.out.println("list@patientList.jsp="+list);	
	
	
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
		var pid = $(this).attr("pid");
		//제목 tr을 선택한 경우
		if(pid==undefined) return;
		location.href="<%=request.getContextPath()%>/admin/adminPatientView?pid="+pid;
		
	});
	
});
</script>

<div id="content-wrapper">
<div id="mypage-wrapper">

	<h2>환자 관리</h2>
	<table id="tbl-mypageList">
		<tr>
			<th>환자 아이디</th>
			<th>환자 이름</th>
			<th>주민등록번호</th>
			<th>주소</th>
		</tr>
		
			<%
		if(list!=null && ! list.isEmpty()){
			for(Patient p : list) { 			
			%>
				<tr pid="<%=p.getPatientId() %>">
					<td><%=p.getPatientId() %></td>
					<td><%=p.getPatientName() %></td>
					<td><%=p.getSsd() %>******</td>
					<td><%=p.getAddress()%></td>
				</tr>
			 
		<%}
		}else{ %>
		<tr>
			<td class="none" colspan="4">등록된 회원이 없습니다.</td>
		</tr>
	<%} %>
		
	</table>
	<div class="pagination">
		<%=request.getAttribute("pageBar") %>
	</div>
	
</div>
</div>



<%@ include file="/WEB-INF/views/common/footer.jsp" %>