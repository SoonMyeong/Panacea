<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>

<%
	String searchDoctorName = (String)request.getAttribute("searchDoctorName");
	ArrayList<Doctor> searchDoctors = (ArrayList<Doctor>)request.getAttribute("searchDoctors");
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
	
	<h3 id="part-title">"<%=searchDoctorName %>"선생님 검색결과</h3>
	<hr id="part-partition">
		
	<%if(searchDoctors != null && !searchDoctors.isEmpty()) { %>
	<table id="search-doctors">
		<%for(int i = 0; i < searchDoctors.size(); i++){
			if(i%3 == 0) {%>
			<tr>
			<%} %>
				<td>
					<div class="part-doctor-introduce">
						<img id="profile" alt="프로필" src="<%=request.getContextPath()%>/upload/doctor/<%=searchDoctors.get(i).getDoctorProfile()%>">
						<span id="part-name"><%=searchDoctors.get(i).getPartName() %></span><br>
						<span id="doctor-name"><%=searchDoctors.get(i).getDoctorName() %></span>&nbsp;선생님
						<p><%=searchDoctors.get(i).getDoctorIntoduce() %></p>
						<%if(loginType != null && loginType.equals("patient")){ %>
							<input type="button" id="reservation-btn" value="예약하기" onclick="location.href='<%=request.getContextPath() %>/reservation/step3Reservation?partId=<%=searchDoctors.get(i).getPartId() %>&doctorId=<%=searchDoctors.get(i).getDoctorId() %>';" />
						<%} %>
					</div>
				</td>
			<%if(i%3 == 2 || i == searchDoctors.size()-1) {%>
			</tr>
			<%}
		} %>
	</table>
	<%} %>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>