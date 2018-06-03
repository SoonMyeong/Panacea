<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.panacea.reservation.model.vo.*,java.text.*,java.util.*" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<% 
	Reservation r = (Reservation)request.getAttribute("reservation"); 
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
%>


<%
	Calendar c = Calendar.getInstance();
	int currYear = c.get(Calendar.YEAR);
	int currMonth = c.get(Calendar.MONTH)+1;
	int currDay = c.get(Calendar.DAY_OF_MONTH);
%>


<script>
$(function(){
	$("#date").change(function(){
		
		$("#time").children().each(function(){
			$(this).removeAttr("disabled");
			if($(this).val() == "-"){
				$(this).attr("disabled","disabled");
			}
		});
		$("#time").val("");
		
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
			   data:"doctorId=<%=r.getDoctorId()%>&date="+$(this).val(),
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
});
</script>

<script>
function fn_reservation(){
	
	if($("#date").val() == "" || $("#time").val() == ""){
		if($("#date").val() == "")
			alert("예약날짜를 선택하세요.");
		if($("#time").val() == "")
			alert("예약시간을 선택하세요");
		return false;
	}
	return true;
}
function fn_delete(){
	confirm("정말로 예약을 취소하시겠습니까..?") ? location.href='<%=request.getContextPath()%>/reservation/deleteEnd?rno=<%=r.getReservationNo()%>&userId=<%=r.getPatientId()%>' : "";
}

</script>

<div id="subnav-wrapper">
   <span id="subnav-title">마이페이지</span>
   <div id="sub_menu">
       <p class="submenu updateView"><a href="<%=request.getContextPath()%>/<%=loginType %>/<%=loginType %>Mypage?userId=<%=loginPatient.getPatientId() %>">개인정보변경</a></p>
       <p class="submenu reservationList"><a href="<%=request.getContextPath()%>/reservation/<%=loginType %>ReservationList?userId=<%=loginPatient.getPatientId() %>">예약 현황</a></p>
       <p class="submenu chartList"><a href="<%=request.getContextPath()%>/chart/<%=loginType%>ChartList?userId=<%=loginPatient.getPatientId()%>">차트기록 보기</a></p>
   </div>
</div>


<div id="content-wrapper">

<form action="<%=request.getContextPath()%>/reservation/updateReservation" method="post" onsubmit="return fn_reservation();">
	<table id="tbl-update">
            <tr>
                <td colspan="2"><h3>예약시간 변경</h3></td>
                <input type="hidden" name="userId" value="<%=r.getPatientId()%>" />
            </tr>
            <tr>
                <td>예약번호 : </td>
                <td><input type="text" id="rno" name="rno" value="<%=r.getReservationNo() %>" readOnly></td>

            </tr>
            <tr>
                <td>의사이름 : </td>
                <td><input type="text" id="doctorName" name="doctorName" value="<%=r.getDoctor_name() %>" readOnly></td>
            </tr>
            
            <tr>
                <td>진료 파트 : </td>
                <td><input type="text" name="doctorIntroduce" value="<%=r.getDoctor_introduce()%>"></td>
                
            </tr>
            <tr>
            	<td>현재 예약날짜 :</td>
            	<td><input type="text" name="" id="" value="<%=sdf.format(r.getReservationDate())%>" readOnly /></td>
            </tr>
            <tr>
            <td>변경할 예약 날짜:</td>
            <td><input type="date" name="date" id="date"></td>
            </tr>
            <tr>		
			<td>변경할 예약 시간 :</td>
			<td>
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
			</td>
            </tr>
            
            <tr>
              <td colspan="2">
              	<span>
	              	<input  type="submit" value="수정">&nbsp;
		    	  	<input type="button" value="예약 취소" onclick="fn_delete();">
	    	  	</span>
	    	  </td>
            </tr>
        </table>
	</form>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>