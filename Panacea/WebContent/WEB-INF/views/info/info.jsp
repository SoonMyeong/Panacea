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
	<img src="<%=request.getContextPath() %>/img/info/info.jpg" alt="파나시아병원" id="info-img">
	<div id="info-content">
		<h2>파나시아 병원</h2>
		<p>
		1985년부터 21세기 병원문화를 이끌어갈 첨단의 병원 환경을 구현하여 현재
		1200병상 규모에 400여명의 의사와 730여명의 간호사등 교직원 1900여명이 종사하는 
		상급종합병원이다
		</p>
		<p>
		전문적인 치료와 환자의 빠른 쾌유를 위하여 25개의 전문 진료과를 보유하고 있으며 
		효율적 치료를 위해 20개의 전문센터와 95개의 세부클리닉을 운영하고 있습니다
		</p>
		<p>
		1985년 개원과 동시에 [의학연구소]를 개소하였고 국가로부터[폐 호흡기질환 연구센트]
		로 지정받는 개가를 올리는 등 두각을 나타내였다 이후에도 암센터 소아과 물리치료 재활치료등
		많은것이 개소하여 의과대학 학생들을 위한 교육에도 정진하여 대학병원으로서 소임을 다하고 있다
		</p>
		<p>
		우리 파나시아 병원은 인간사랑 정신을 구현하기 위하여 개원과 동시에 의료봉사단을 조직하여
		서울을 포함 부천 인천 강원도 지역의 주민들을 무료로 진료해주고 있다
		</p>
		<p>
		2000년부터는 의료봉사 활동 범위를 해외로 확대하여 [파나시아 동호회]와
		[한미봉사회] 사단법인을 출범시켜 해외의 의료봉사 활동을 활발히 전개해 오고 있다
		</p>
    </div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>