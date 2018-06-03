<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
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
		<div id="reservation-form">
			<form action="<%=request.getContextPath() %>/reservation/step2Reservation" method="get">
				<h3 id="select-title">진료과를 선택하세요</h3>
				<select id="selectedPart" name="selectedPart">
					<option selected disabled>진료과를 선택하세요</option>
					
					<optgroup label="내과">
						<option value="P1D1">일반내과</option>
						<option value="P1D2">호흡기내과</option>
						<option value="P1D3">소화내과</option>
						<option value="P1D4">신장내과</option>
					</optgroup>
					
					<optgroup label="외과">
						<option value="P2D1">정형외과</option>
						<option value="P2D2">신경외과</option>
						<option value="P2D3">흉부외과</option>
						<option value="P2D4">혈관외과</option>
					</optgroup>
					
					<option disabled></option>
					<option value="P3">가정의학과</option>
					<option value="P4">산부인과</option>
					<option value="P5">성형외과</option>
					<option value="P6">비뇨기과</option>
					<option value="P7">피부과</option>
					<option value="P8">정신건강의학과</option>
					<option value="P9">안과</option>
					<option value="P10">치과</option>
				</select>
				<input id="submit-step1" type="submit" value="다음단계로" onclick="return selectPartValidate();">
			</form>
		</div>
		<div id="reservation-description">
		</div>
	</div>
</div>

<script>
function selectPartValidate(){
	console.log($("[name=selectedPart]").val());
	if($("[name=selectedPart]").val() == null){
		alert("진료과를 선택하세요.");
		return false;
	}
	return true;
}

$("[name=selectedPart]").change(function(){
	var partId = $(this).val();
	console.log(partId);
	
	$.ajax({
		url:"<%=request.getContextPath()%>/part/selectPart",
		   dataType:"json",
		   data:"partId="+partId,
		   type:"get",
		   success:function(data){
		      var html = "<h3 id='part-title'>"+ data.partName + "</h3>"
		      html += data.partIntroduce;
			  $("#reservation-description").html(html);
		   },
		   error : function(jqxhr,textStatus,errorThrown){
		      console.log("ajax 에러!");
		      console.log(jqxhr);
		      console.log(textStatus);
		      console.log(errorThrown);
		   }
		});
});
</script>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>