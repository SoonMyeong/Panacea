<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.panacea.chart.model.vo.*,java.text.*,java.util.*" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<% 
	Chart c = (Chart)request.getAttribute("chart");
%>
<div id="subnav-wrapper">
   <span id="subnav-title">마이페이지</span>
   <div id="sub_menu">
       <p class="submenu updateView"><a href="<%=request.getContextPath()%>/<%=loginType %>/<%=loginType %>Mypage?userId=<%=loginPatient.getPatientId() %>">개인정보변경</a></p>
       <p class="submenu reservationList"><a href="<%=request.getContextPath()%>/reservation/<%=loginType %>ReservationList?userId=<%=loginPatient.getPatientId() %>">예약 현황</a></p>
       <p class="submenu chartList"><a href="<%=request.getContextPath()%>/chart/<%=loginType%>ChartList?userId=<%=loginPatient.getPatientId()%>">차트기록 보기</a></p>
   </div>
</div>
<style>
table#tbl-update{
    /* border: 2px solid gray; */
    border-top: 2px solid gray;
    border-bottom: 2px solid gray;
    border-spacing: 30px;
  	margin: 0 auto;
  	margin-top:50px;
    font-family: 'Do Hyeon', sans-serif;
}
table#tbl-update tr td span{
margin-left: 60px;
}
table#tbl-update tr td span input{
font-family: 'Do Hyeon', sans-serif;
}
</style>

<div id="content-wrapper">

	<table id="tbl-update">
            <tr>
                <td><h3>차트 상세보기</h3></td>
            </tr>
            <tr>
                <td>차트번호 : </td>
                <td><input type="text" id="cno" name="cno" value="<%=c.getChart_no()%>" readOnly></td>

            </tr>
            <tr>
                <td>의사이름 : </td>
                <td><input type="text" id="patientName" name="doctorName" value="<%=c.getDoctor_name()%>" readOnly></td>
            </tr>
            
            <tr>
                <td>의사 파트 : </td>
                <td><input type="text" name="partName" value="<%=c.getPart_name()%>" readOnly></td>
                
            </tr>
            <tr>
                <td>진단명 </td>
                <td><input type="text" name="diseaseName" value="<%=c.getDisease_name() %>" readOnly></td>
            </tr>
            <tr>
                <td>진단내용 </td>
                <td><input type="text" name="diseaseComment" value="<%=c.getChart_comment()%>" readOnly></td>
            </tr>
        </table>

</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>