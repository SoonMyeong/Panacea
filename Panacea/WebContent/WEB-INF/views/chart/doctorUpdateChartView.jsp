<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.panacea.chart.model.vo.*,java.text.*,java.util.*" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<% 
	Chart c = (Chart)request.getAttribute("chart");
	System.out.println("chart@updateChartView.jsp="+c);
%>
<div id="subnav-wrapper">
   <span id="subnav-title">마이페이지</span>
   <div id="sub_menu">
       <p class="submenu updateView"><a href="<%=request.getContextPath()%>/<%=loginType %>/<%=loginType %>Mypage?userId=<%=loginDoctor.getDoctorId() %>">개인정보변경</a></p>
       <p class="submenu reservationList"><a href="<%=request.getContextPath()%>/reservation/<%=loginType %>ReservationList?userId=<%=loginDoctor.getDoctorId() %>">예약현황/차트작성</a></p>
   		<p class="submenu chartList"><a href="<%=request.getContextPath()%>/chart/<%=loginType %>ChartList?userId=<%=loginDoctor.getDoctorId() %>">차트리스트</a></p>
   </div>
</div>

<div id="content-wrapper">

<form action="<%=request.getContextPath()%>/chart/updateEndChart" method="post">
	<table id="tbl-update">
            <tr>
                <td colspan="2"><h3>차트 변경</h3></td>
            </tr>
            <tr>
                <td>차트번호 : </td>
                <td><input type="text" id="cno" name="cno" value="<%=c.getChart_no()%>" readOnly></td>
				<input type="hidden" name="userId" value="<%=c.getDoctor_id()%>" />
            </tr>
            <tr>
                <td>환자이름 : </td>
                <td><input type="text" id="patientName" name="patientName" value="<%=c.getPatient_name()%>" readOnly></td>
            </tr>
            
            <tr>
                <td>환자 주민번호 : </td>
                <td><input type="text" name="patientSsd" value="<%=c.getPatient_ssd()%>******" readOnly></td>
                
            </tr>
            <tr>
                <td>진단명 </td>
                <td><input type="text" name="diseaseName" value="<%=c.getDisease_name() %>"></td>
            </tr>
            <tr>
            <tr>
                <td>진단내용 </td>
                <td><textarea><%=c.getChart_comment()%></textarea></td>
            </tr>
            <tr>
              <td colspan="2">
              	<span>
	              	<input  type="submit" value="수정">&nbsp;
	    	  	</span>
	    	  </td>
            </tr>
        </table>
</form>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>