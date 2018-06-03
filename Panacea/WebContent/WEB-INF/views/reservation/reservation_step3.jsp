<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.panacea.part.model.vo.*,
				java.util.*" %>

<%
	MedicalPart selectedPart = (MedicalPart)request.getAttribute("part");
	Doctor selectedDoctor = (Doctor)request.getAttribute("doctor");
%>

<%
	Calendar c = Calendar.getInstance();
	int currYear = c.get(Calendar.YEAR);
	int currMonth = c.get(Calendar.MONTH)+1;
	int currDay = c.get(Calendar.DAY_OF_MONTH);
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
		<div id="select-date-time">
			<h3 id="date-time-title">예약날짜를 선택하세요</h3>
			<span id="reservation-notice">*당일 예약은 불가합니다.<br></span>
			<span id="reservation-notice">*최대 3개월까지 예약 가능합니다.<br></span>
			<input type="date" name="date" id="date">
		
			<br><br><br><br>
			
			<h3 id="date-time-title">예약시간을 선택하세요</h3>
	 		<select id="time" name="time">
	 			<option value="-" selected disabled>------시간선택------</option>
				<option value="10:00">10:00</option>
				<option value="10:30">10:30</option>
				<option value="11:00">11:00</option>
				<option value="11:30">11:30</option>
				<option value="12:00">12:00</option>
				<option value="12:30">12:30</option>
				<option value="14:00">14:00</option>
				<option value="14:30">14:30</option>
				<option value="15:00">15:00</option>
				<option value="15:30">15:30</option>
				<option value="16:00">16:00</option>
				<option value="16:30">16:30</option>
				<option value="17:00">17:00</option>
				<option value="17:30">17:30</option>
			</select>
		</div>
		
		<div id="reservation-result">
		</div>
	</div>
</div>

<script>
	
$("#date").change(function(){
	
	$("#time").children().each(function(){
		$(this).removeAttr("disabled");
		if($(this).val() == "-"){
			$(this).attr("disabled","disabled");
		}
	});
	$("#time").val("");
	$("#reservation-result").html("");
	
	var year = $(this).val().substring(0,4);
	var month = $(this).val().substring(5,7);
	var day = $(this).val().substring(8,10);
	
	if(<%=currYear %> > year){
		alert("예약날짜를 확인하세요");
		$(this).val("");
	} else if(<%=currYear%> < year){
		alert("최대 3개월까지 진료 예약 가능합니다.");
		$(this).val("");
	} else if(<%=currMonth%> > month){
		alert("예약날짜를 확인하세요");
		$(this).val("");
	} else if(<%=currMonth%>+3 < month ){
		alert("최대 3개월까지 진료 예약 가능합니다.");
		$(this).val("");
	} else if(<%=currMonth%>==month && <%=currDay%>+1 > day){
		alert("예약날짜를 확인하세요");
		$(this).val("");
	}
	
	$.ajax({
		url:"<%=request.getContextPath()%>/reservation/checkTime",
		   dataType:"json",
		   data:"patientId=<%=loginPatient.getPatientId()%>&doctorId=<%=selectedDoctor.getDoctorId()%>&date="+$(this).val(),
		   type:"get",
		   success:function(data){
			   for(var i = 0; i < data.length; i++){
					console.log(data[i]);
					
					$("#time").children().each(function(){
						if($(this).val() == data[i]){
							console.log("disabled 시킴");
							$(this).attr("disabled","disabled");
						}
					});
				}
			   $("#time").val("-");
		   },
		   error : function(jqxhr,textStatus,errorThrown){
		      console.log("ajax 에러!");
		      console.log(jqxhr);
		      console.log(textStatus);
		      console.log(errorThrown);
		   }
	});
	
});


$("#time").change(function(){
	console.log("시간선택함");
	var html = '<h3 id="result-title">예약 내용 확인</h3>';
	html+= '<hr id="part-partition"><br>';
	html+= '<span id="part-result"><%=selectedPart.getPartName() %></span>';
	html+= '<br>';
	html+= '<span id="doctor-result"><%=selectedDoctor.getDoctorName() %></span>선생님';
	html+= '<br>';
	html+= '<span id="date-time-result">'+$("#date").val()+' '+$("#time").val()+'</span>';
	html+= '<br>';
	html+= '<input type="button" id="final-reservation-btn" value="예약하기" onclick="reservation();" />';
	
	$("#reservation-result").html(html);
});

function reservation(){
	
	if($("#date").val() == "" || $("#time").val() == ""){
		if($("#date").val() == "")
			alert("예약날짜를 선택하세요.");
		if($("#time").val() == "")
			alert("예약시간을 선택하세요");
		return;
	}
	location.href="<%=request.getContextPath()%>/reservation/insertReservation?partId=<%=selectedPart.getPartId()%>&doctorId=<%=selectedDoctor.getDoctorId()%>&date="+$("#date").val()+"&time="+$("#time").val();
}

</script>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>