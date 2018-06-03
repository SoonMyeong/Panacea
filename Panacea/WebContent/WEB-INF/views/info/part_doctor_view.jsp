<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,
				com.panacea.part.model.vo.*" %>

<%
	MedicalPart part = (MedicalPart)request.getAttribute("part");
	ArrayList<Doctor> partDoctors = (ArrayList<Doctor>)request.getAttribute("partDoctors");
%>

<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div id="subnav-wrapper">
	<span id="subnav-title">병원 소개</span>
	<div id="sub_menu">
	    <p class="submenu infoView"><a href="<%=request.getContextPath()%>/info/infoView">병원 소개</a></p>
	    <p class="submenu part-doctor"><a href="<%=request.getContextPath()%>/info/partView">진료과/의료진</a></p>
	    <p class="submenu directionsView"><a href="<%=request.getContextPath()%>/info/directionsView">오시는길</a></p>
	</div>
</div>

<div id="content-wrapper">
	
	<div id="part-introduce">
		<h3 id="part-title"><%=part.getPartName() %></h3>
		<hr id="part-partition">
		<p><%=part.getPartIntroduce() %></p>
	</div>
	
	<div id="part-doctors">
		<h3 id="part-title"><%=part.getPartName() %> 의료진</h3>
		<hr id="part-partition">
		
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
							<%if(loginType != null && loginType.equals("patient")){ %>
							<input type="button" id="reservation-btn" value="예약하기" onclick="location.href='<%=request.getContextPath() %>/reservation/step3Reservation?partId=<%=part.getPartId() %>&doctorId=<%=partDoctors.get(i).getDoctorId() %>';" />
							<%} %>
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

<%@ include file="/WEB-INF/views/common/footer.jsp" %>