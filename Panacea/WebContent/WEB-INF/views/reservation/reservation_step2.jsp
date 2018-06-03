<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>

<%
	String partName = (String)request.getAttribute("partName");
	ArrayList<Doctor> partDoctors = (ArrayList<Doctor>)request.getAttribute("partDoctors");
%>

<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div id="subnav-wrapper">
	<span id="subnav-title">진료예약</span>
	<div id="sub_menu">
	    <p class="submenu step1"><a href="">STEP1 :진료과</a></p>
	    <p class="submenu step2"><a href="">STEP2 :의료진</a></p>
	    <p class="submenu step3"><a href="">STEP3 :예약시간</a></p>
	</div>
</div>

<div id="content-wrapper">
	<div class="stepmenu" id="step1">STEP1 :진료과</div>
	<div class="stepmenu" id="step2">STEP2 :의료진</div>
	<div class="stepmenu" id="step3">STEP3 :예약시간</div>
	
	<div id="reservation-content">
		<br>
		<h3 id="part-title"><%=partName %> 의료진</h3>
		<hr id="part-partition">

		<div id="reservation-doctors">			
			<%if(partDoctors != null && !partDoctors.isEmpty()) { %>
			<table>
				<%for(int i = 0; i < partDoctors.size(); i++){
					if(i%3 == 0) {%>
					<tr>
					<%} %>
						<td>
							<div class="part-doctor-introduce">
								<img id="profile" alt="프로필" src="<%=request.getContextPath()%>/upload/doctor/<%=partDoctors.get(i).getDoctorProfile()%>">
								<span id="doctor-name"><%=partDoctors.get(i).getDoctorName() %></span>&nbsp;선생님
								<p><%=partDoctors.get(i).getDoctorIntoduce() %></p>
							</div>
							<div class="reservation-btn">
								<input type="button"  value="예약하기" onclick="selectPartDoctor('<%=partDoctors.get(i).getPartId()%>','<%=partDoctors.get(i).getDoctorId()%>');" />
							</div>
						</td>
					<%if(i%3 == 2 || i == partDoctors.size()-1) {%>
					</tr>
					<%}
				} %>
			</table>
			<%} %>
		</div>
	</div>
</div>

<script>
function selectPartDoctor(partId,doctorId){
	location.href="<%=request.getContextPath()%>/reservation/step3Reservation?partId="+partId+"&doctorId="+doctorId;
}
</script>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>