<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.panacea.reservation.model.vo.*,java.text.*,java.util.*" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<% 
	Reservation r = (Reservation)request.getAttribute("reservation"); 
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.KOREA);
%>
<div id="subnav-wrapper">
   <span id="subnav-title">마이페이지</span>
   <div id="sub_menu">
       <p class="submenu updateView"><a href="<%=request.getContextPath()%>/<%=loginType %>/<%=loginType %>Mypage?userId=<%=loginDoctor.getDoctorId() %>">개인정보변경</a></p>
       <p class="submenu reservationList"><a href="<%=request.getContextPath()%>/reservation/<%=loginType %>ReservationList?userId=<%=loginDoctor.getDoctorId() %>">예약현황/차트작성</a></p>
   		<p class="submenu chartList"><a href="<%=request.getContextPath()%>/chart/<%=loginType %>ChartList?userId=<%=loginDoctor.getDoctorId() %>">차트리스트</a></p>
   </div>
</div>

<div id="content-wrapper">
	<form action="<%=request.getContextPath()%>/chart/insertEndChart" method="post">
	<table id="tbl-update">
		<tr>
			<td colspan="2">
				<h3>차트 작성</h3>
				<input type="hidden" name="doctorId" value="<%=r.getDoctorId() %>" />
				<input type="hidden" name="patientId" value="<%=r.getPatientId()%>" />
			</td>
		</tr>
		<tr>
			<td>예약 번호</td>
			<td><input type="text" name="rNo" id="" value="<%=r.getReservationNo() %>" readOnly /></td>
		</tr>
		<tr> 
			<td>환자이름</td>
			<td><input type="text" name="rName" id="" value="<%=r.getDoctor_name() %>" /></td>
		</tr>
		<tr>
			<td>환자주민번호</td>
			<td><input type="text" name="rSsd" id="" value="<%=r.getPatient_ssd() %>******" /></td>
		</tr>
		<tr>
			<td>예약날짜</td>
			<td><input type="text" name="rDate" id="" value="<%=sdf.format(r.getReservationDate()) %>" /></td>
		</tr>
		<tr>
			<td>진단명</td>
			<td><input type="text" name="diseaseName" id="disease-name" /></td>
		</tr>
		<tr>
			<td>진단내용</td>
			<td><textarea name="diseaseComment" id="disease-comment" cols="30" rows="10"></textarea></td>
		</tr>
		<tr>
			<td colspan="2">
				<span>
					<input type="submit" value="등록" /> &nbsp;
					<input type="reset" value="초기화" />
				</span>
			</td>
		</tr>
	</table>
	</form>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>