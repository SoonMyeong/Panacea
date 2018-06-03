<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ page import="com.panacea.admin.model.vo.*,
				java.util.*" %>

<% List<Admin>list = (List<Admin>)request.getAttribute("list"); %>

<div id="subnav-wrapper">
   <span id="subnav-title">마이페이지</span>
   <div id="sub_menu">
		<p class="submenu patientList"><a href="<%=request.getContextPath()%>/<%=loginType %>/patientList">환자 관리</a></p>
		<p class="submenu doctorList"><a href="<%=request.getContextPath()%>/<%=loginType %>/doctorList">의사 관리</a></p>
		<p class="submenu adminView"><a href="<%=request.getContextPath()%>/admin/adminView">관리자정보</a></p>
   </div>
</div>

<!-- 유효성 검사  -->
	<script>
	function fn_updateVaildate(){
		
		return true;
	}
	</script>
	<script>
		$(function(){
			$(".submenu").each(function(){
				if($(this).hasClass("<%=selected_submenu%>")){
					$(this).css({
						"background-image": "url('../img/subnav_back.png')",
				    	"background-repeat": "no-repeat",
				    	"background-position": "right"
					});
					$(this).find("a").css({
						"color": "#3954ad",
				    	"text-decoration": "underline"
					});
				}
			});
		});
	</script>
	

<script>
$(function(){
	
	$('#tbl-mypageList tr').click(function(){
		var aid = $(this).attr("aid");
		//제목 tr을 선택한 경우
		if(aid==undefined) return;
		location.href="<%=request.getContextPath()%>/admin/adminUpdate?userId="+aid;
		
	});
	
});
</script>


<div id="content-wrapper">
	<div id="mypage-wrapper">
        <h2>관리자정보</h2>
        <table id="tbl-mypageList">
            <tr>
                <th id="wrapper">아이디</th>
                <th id="wrapper">이름</th>
                <th id="wrapper">주민번호</th>
                <th id="wrapper">핸드폰</th>
                <th id="wrapper">주소</th>
            </tr>
            <%if(list==null || list.isEmpty()){ %>

		<%} else { 
			for(Admin a : list){
		%>		
			<tr aid="<%=a.getAdminId()%>">
				<td id="wrapper">
					<%=a.getAdminId()%>
				</td>			
				<td id="wrapper"><%=a.getAdminName()%></td>
				<td id="wrapper"><%=a.getSsd()%></td>
				<td id="wrapper"><%=a.getPhone()%></td>
				<td id="wrapper"><%=a.getAddress()%></td>
			</tr>
		<%}
		} %>
            
        </table> 
	     <div class="pagination">
		<%=request.getAttribute("pageBar") %>
		</div>
	</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>