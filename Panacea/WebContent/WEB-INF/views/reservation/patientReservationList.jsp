<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.panacea.reservation.model.vo.*, java.util.*,java.text.*,java.sql.Timestamp" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<% 
	List<Reservation> list = (List<Reservation>)request.getAttribute("list"); 
	System.out.println("list@patientReservationlist.jsp="+list);	
	
	
%>


<div id="subnav-wrapper">
   <span id="subnav-title">마이페이지</span>
   <div id="sub_menu">
       <p class="submenu updateView"><a href="<%=request.getContextPath()%>/<%=loginType %>/<%=loginType %>Mypage?userId=<%=loginPatient.getPatientId() %>">개인정보변경</a></p>
       <p class="submenu reservationList"><a href="<%=request.getContextPath()%>/reservation/<%=loginType %>ReservationList?userId=<%=loginPatient.getPatientId() %>">예약 현황</a></p>
      <p class="submenu chartList"><a href="<%=request.getContextPath()%>/chart/<%=loginType%>ChartList?userId=<%=loginPatient.getPatientId()%>">차트기록 보기</a></p>
   </div>
</div>

<style>


</style>

<script>
$(function(){
	
	$('#tbl-mypageList tr').click(function(){
		var rno = $(this).attr("rno");
		//제목 tr을 선택한 경우
		if(rno==undefined) return;
		location.href="<%=request.getContextPath()%>/reservation/<%=loginType %>ReservationView?rno="+rno;
		
	});
	
});
</script>

<div id="content-wrapper">
<div id="mypage-wrapper">
	<h2>예약 현황</h2>
	<table id="tbl-mypageList">
		<tr>
			<th>담당 의사</th>
			<th>진료과</th>
			<th>예약 날짜</th>
			<th>예약 시간</th>
		</tr>
		
			<%
		if(list!=null && ! list.isEmpty()){
			for(Reservation r : list) { 
				if(r.getStatus().equals("N")&&r.getCheck_date()>=0){
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
					SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm", Locale.KOREA);
					
			%>
				<tr rno="<%=r.getReservationNo() %>">
					<td><span id="doctor-name"><%=r.getDoctor_name() %>&nbsp;&nbsp;</span>선생님</td>
					<td><%=r.getPartName() %></td>
					<td><%=sdf.format(r.getReservationDate())%></td>
					<td><%=sdf1.format(r.getReservationDate())%></td>
				</tr>
			<%} 
			}%>
		<%} else{ %>
		<tr>
			<td class="none" colspan="5">작성된 글이 없습니다.</td>
		</tr>
	<%} %>
		
	</table>
	<div class='pagination'>
		<%=request.getAttribute("pageBar") %>
	</div>
</div>
</div>



<%@ include file="/WEB-INF/views/common/footer.jsp" %>