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

<script>
$(function(){
	$("#searchBtn")
	$(".part-wrapper").on("click",function(){
		var partId = $(this).attr("id");
		location.href="<%=request.getContextPath()%>/part/partDoctorView?partId="+partId;
	});
});

function searchValidate(){
	if($("#searchDoctor").val().trim().length <= 0){
		alert("의사명을 입력하세요");
		$("#searchDoctor").focus();
		return false;
	}
	return true;
}
</script>

<div id="content-wrapper">
	
	<div id="doctor-search">	
		<h3 id="search-title">의사명으로 검색하세요</h3>
		<form action="<%=request.getContextPath() %>/doctor/searchDoctor" method="get">
			<input type="text" name="doctorName" id="searchDoctor" placeholder="의사명을 입력하세요">
			<input type="submit" value="검색" id="searchBtn" onclick="return searchValidate();">
		</form>
	</div>
	
	<h3 id="part-title">내과</h3>
	<hr id="part-partition">
	
	<div class="part-wrapper" id="P1D2">
		<div class="part-view">
		    <img alt="호흡기내과" src="<%=request.getContextPath()%>/img/info/part/P1D2.png">
		</div>
		<div class="hidden">호흡기<br>내과</div>
    </div>
    
    <div class="part-wrapper" id="P1D3">
	    <div class="part-view">
	        <img alt="소화내과" src="<%=request.getContextPath()%>/img/info/part/P1D3.png">
	    </div>
		<div class="hidden">소화<br>내과</div>
	</div>
	
	<div class="part-wrapper" id="P1D4">
	    <div class="part-view">
	        <img alt="신장내과" src="<%=request.getContextPath()%>/img/info/part/P1D4.png">
	    </div>
		<div class="hidden">신장<br>내과</div>
	</div>
	
	<div class="part-wrapper" id="P1D1">
	    <div class="part-view">
	        <img alt="일반내과" src="<%=request.getContextPath()%>/img/info/part/P1D1.png">
	    </div>
		<div class="hidden">일반<br>내과</div>
	</div>
	
	<h3 id="part-title">외과</h3>
	<hr id="part-partition">
	
	<div class="part-wrapper" id="P2D1">
	    <div class="part-view">
	        <img alt="정형외과" src="<%=request.getContextPath()%>/img/info/part/P2D1.png">
	    </div>
		<div class="hidden">정형<br>외과</div>
	</div>
	
	<div class="part-wrapper" id="P2D2">
	    <div class="part-view">
	        <img alt="신경외과" src="<%=request.getContextPath()%>/img/info/part/P2D2.png">
	    </div>
		<div class="hidden">신경<br>외과</div>
	</div>
	
	<div class="part-wrapper" id="P2D3">
	    <div class="part-view">
	        <img alt="흉부외과" src="<%=request.getContextPath()%>/img/info/part/P2D3.png">
	    </div>
		<div class="hidden">흉부<br>외과</div>
	</div>
	
	<div class="part-wrapper" id="P2D4">
	    <div class="part-view">
	        <img alt="혈관외과" src="<%=request.getContextPath()%>/img/info/part/P2D4.png">
	    </div>
		<div class="hidden">혈관<br>외과</div>
	</div>
    
    <br><br><br>
	<hr id="part-partition">
	<br>
    
    <div class="part-wrapper" id="P3">
	    <div class="part-view">
	        <img alt="가정의학과" src="<%=request.getContextPath()%>/img/info/part/P3.PNG">
	    </div>
		<div class="hidden">가정<br>의학과</div>
	</div>
	
	<div class="part-wrapper" id="P4">
	    <div class="part-view">
	        <img alt="산부인과" src="<%=request.getContextPath()%>/img/info/part/P4.png">
	    </div>
		<div class="hidden">산부<br>인과</div>
	</div>
	
	<div class="part-wrapper" id="P5">
	    <div class="part-view">
	        <img alt="성형외과" src="<%=request.getContextPath()%>/img/info/part/P5.PNG">
	    </div>
		<div class="hidden">성형<br>외과</div>
	</div>
	
	<div class="part-wrapper" id="P6">
	    <div class="part-view">
	        <img alt="비뇨기과" src="<%=request.getContextPath()%>/img/info/part/P6.png">
	    </div>
		<div class="hidden">비뇨<br>기과</div>
	</div>
    
    <br><br>
    
	<div class="part-wrapper" id="P7">
	    <div class="part-view">
	        <img alt="피부과" src="<%=request.getContextPath()%>/img/info/part/P7.png">
	    </div>
		<div class="hidden">피부과</div>
	</div>
	
	<div class="part-wrapper" id="P8">
	    <div class="part-view">
	        <img alt="정신건강의학과" src="<%=request.getContextPath()%>/img/info/part/P8.png">
	    </div>
		<div class="hidden">정신건강<br>의학과</div>
	</div>
	
	<div class="part-wrapper" id="P9">
	    <div class="part-view">
	        <img alt="안과" src="<%=request.getContextPath()%>/img/info/part/P9.png">
	    </div>
		<div class="hidden">안과</div>
	</div>
	
	<div class="part-wrapper" id="P10">
	    <div class="part-view">
	        <img alt="치과" src="<%=request.getContextPath()%>/img/info/part/P10.png">
	    </div>
		<div class="hidden">치과</div>
	</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>