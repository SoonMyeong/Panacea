<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.panacea.reservation.model.vo.*, java.util.*,java.text.*,java.sql.Timestamp" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<% 
	List<Reservation> list = (List<Reservation>)request.getAttribute("list"); 
	System.out.println("list@doctorReservationlist.jsp="+list);	
	
	
%>


<div id="subnav-wrapper">
   <span id="subnav-title">마이페이지</span>
   <div id="sub_menu">
       <p class="submenu updateView"><a href="<%=request.getContextPath()%>/<%=loginType %>/<%=loginType %>Mypage?userId=<%=loginDoctor.getDoctorId() %>">개인정보변경</a></p>
       <p class="submenu reservationList"><a href="<%=request.getContextPath()%>/reservation/<%=loginType %>ReservationList?userId=<%=loginDoctor.getDoctorId() %>">예약현황/차트작성</a></p>
   		<p class="submenu chartList"><a href="<%=request.getContextPath()%>/chart/<%=loginType %>ChartList?userId=<%=loginDoctor.getDoctorId() %>">차트리스트</a></p>
   </div>
</div>

<script>
$(function(){
	
	$('#tbl-mypageList tr').click(function(){
		var rno = $(this).attr("rno");
		var cd = $(this).attr("cd");
		//제목 tr을 선택한 경우
		if(rno==undefined || cd>0 ) {
			alert("아직 차트를 작성하실 수 없습니다.");
			return;
		}
		location.href="<%=request.getContextPath()%>/chart/<%=loginType %>WriteChartView?rno="+rno;
		
	});
	
});
</script>

<div id="content-wrapper">
<div id="mypage-wrapper">
	<h2>예약 현황</h2>
	<table id="tbl-mypageList">
		<tr>
			<th>환자 이름</th>
			<th>환자 주민번호</th>
			<th>환자 전화번호</th>
			<th>예약 날짜</th>
			<th>예약 시간</th>
			<th>차트작성</th>
		</tr>
		
		<%
		if(list!=null && ! list.isEmpty()){
			for(Reservation r : list) { 
				if(r.getStatus().equals("N")){
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
					SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm", Locale.KOREA);
			%>
				<tr rno="<%=r.getReservationNo() %>" cd="<%=r.getCheck_date()%>">
					<td><%=r.getPatient_name() %></td>
					<td><%=r.getPatient_ssd() %>******</td>
					<td><%=r.getPatient_phone() %></td>
					<td><%=sdf.format(r.getReservationDate())%></td>
					<td><%=sdf1.format(r.getReservationDate())%></td>
					
					<%if(r.getCheck_date()>=0){ %>
					<td>불가능</td>
					<%}else{ %>
						<td style="color:red;">가능</td>
					<%} %>
				</tr>
			
		<%} 
		}} else{ %>
		<tr>
			<td class="none" colspan="6">작성된 글이 없습니다.</td>
		</tr>
	<%} %>
		
	</table>
	<div class="pagination">
		<%=request.getAttribute("pageBar") %>
	</div>
</div>
</div>


<%@ include file="/WEB-INF/views/common/footer.jsp" %>