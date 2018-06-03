<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.panacea.reservation.model.vo.*, java.util.*,java.text.*,java.sql.Timestamp" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<% 
	List<Reservation> list = (List<Reservation>)request.getAttribute("list"); 
	System.out.println("list@patientReservationlist.jsp="+list);	
	
	
%>


<div id="subnav-wrapper">
   <span id="subnav-title"></span>
   <div id="sub_menu">
       <p><a href="<%=request.getContextPath()%>/review/reviewList">게시판으로 돌아가기</a></p>
   </div>
</div>

<script>
$(function(){
	
	$('#tbl-review tr').click(function(){
		var rno = $(this).attr("rno");
		//제목 tr을 선택한 경우
		if(rno==undefined) return;
		location.href="<%=request.getContextPath()%>/review/reviewForm?rno="+rno;
		
	});
	
});
</script>

<div id="content-wrapper">
<div id="container-reviewList">
	<h2>후기 작성 가능 목록</h2>
	<table id="tbl-review">
		<tr>
			<th>담당의사</th>
			<th>진료 날짜</th>
			<th>진료 시간</th>
			<th>진료과</th>
		</tr>
		
			<%
		if(list!=null && ! list.isEmpty()){
			for(Reservation r : list) {
					SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
					 SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm", Locale.KOREA);
					
			%>
				<tr id="hover" rno="<%=r.getReservationNo() %>">
					<td><span id="doctor-name"><%=r.getDoctor_name() %></span>&nbsp;&nbsp;선생님</td>
					<td><%=sdf1.format(r.getReservationDate())%></td>
					<td><%=sdf2.format(r.getReservationDate())%></td>
					<td><%=r.getPartName() %></td>
				</tr>
			<% }%>
		<%}  else if (list != null &&list.isEmpty()) { %>
  		<tr>
			<td class="none" colspan = "5">작성 가능한 후기가 없습니다</td>
		</tr>
		<%} %>
		
	</table>
	<div class='pagination'>
		<%=request.getAttribute("pageBar") %>
	</div>
</div>
</div>



<%@ include file="/WEB-INF/views/common/footer.jsp" %>