<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="com.panacea.chart.model.vo.*, java.util.*" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<% 
	List<Chart> list = (List<Chart>)request.getAttribute("list");
	System.out.println("list@charList.jsp"+list);
%>

<div id="subnav-wrapper">
   <span id="subnav-title">마이페이지</span>
   <div id="sub_menu">
       <p class="submenu updateView"><a href="<%=request.getContextPath()%>/<%=loginType %>/<%=loginType %>Mypage?userId=<%=loginPatient.getPatientId() %>">개인정보변경</a></p>
       <p class="submenu reservationList"><a href="<%=request.getContextPath()%>/reservation/<%=loginType %>ReservationList?userId=<%=loginPatient.getPatientId() %>">예약 현황</a></p>
           <p class="submenu chartList"><a href="<%=request.getContextPath()%>/chart/<%=loginType%>ChartList?userId=<%=loginPatient.getPatientId()%>">차트기록 보기</a></p>
   </div>
</div>

<script>
$(function(){
	
	$('#tbl-mypageList tr').click(function(){
		var cno = $(this).attr("cno");
		if(cno==undefined) {
			return;
		}
		location.href="<%=request.getContextPath()%>/chart/<%=loginType %>UpdateChartView?cno="+cno;
		
	});
	
});
</script>

<div id="content-wrapper">
<div id="mypage-wrapper">
	<h3>차트 리스트</h3>
	<table id="tbl-mypageList">
		<tr>
			<th>환자 이름</th>
			<th>환자 주민번호</th>
			<th>담당 의사</th>
			<th>진료과</th>
			<th>진단명</th>
		</tr>
		
		<%
		if(list!=null && ! list.isEmpty()){
			for(Chart c : list) { 		
			%>
				<tr cno="<%=c.getChart_no() %>">
					<td><%=c.getPatient_name() %></td>
					<td><%=c.getPatient_ssd() %>******</td>
					<td><span id="doctor-name"><%=c.getDoctor_name() %></span>&nbsp;&nbsp;선생님</td>
					<td><%=c.getPart_name() %></td>
					<td><%=c.getDisease_name() %></td>
				</tr>
			<%} %>
		<%} else{ %>
		<tr>
			<td class="none" colspan="5">작성된 글이 없습니다.</td>
		</tr>
	<%} %>
   </table>
   	<div id='pageBar'>
		<%=request.getAttribute("pageBar") %>
	</div>
</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>