<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
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
	<img id="map" src="<%=request.getContextPath() %>/img/info/map.png" alt="지도">
	<div id="description">
		<h3>서울광역시 강남구 역삼동</h3>
		<h2>파나시아 병원</h2>
    	<h4>예약번호 : 02-123-4567</h4>
    	<br>
		<h3>● "버스"로 오시는 경우</h3>
		&nbsp;&nbsp;&nbsp;역삼역 방향 : 147번, 463번, 4211번, 6000번, 6020번
		<h3>● "지하철"로 오시는 경우</h3>
		&nbsp;&nbsp;&nbsp;지하철 2호선 역삼역 1번 출구 도보 5분
		<br><br>
	</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>