<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.panacea.patient.model.vo.*,
				com.panacea.doctor.model.vo.*,
				com.panacea.admin.model.vo.*" %>
<%
	String selected_menu = (String)request.getAttribute("selectedMenu");
	String selected_submenu = (String)request.getAttribute("selectedSubMenu");
%>
<%
	String loginType = (String)session.getAttribute("loginType");
	System.out.println("sessionLoginType="+loginType);

	Patient loginPatient = null;
	Doctor loginDoctor = null;
	Admin loginAdmin = null;	

	boolean isLogin = false;
	
	if("patient".equals(loginType)){
		loginPatient = (Patient)session.getAttribute("loginPatient");
		/* System.out.println("sessionLoginId="+loginPatient.getPatientId()); */
		isLogin = true;
	} else if("doctor".equals(loginType)){
		loginDoctor = (Doctor)session.getAttribute("loginDoctor");
		/* System.out.println("sessionLoginId="+loginDoctor.getDoctorId()); */
		isLogin = true;
	} else if("admin".equals(loginType)){
		loginAdmin = (Admin)session.getAttribute("loginAdmin");
		/* System.out.println("sessionLoginId="+loginAdmin.getAdminId()); */
		isLogin = true;
	}

	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PANACEA</title>
</head>

<link href="https://fonts.googleapis.com/css?family=Do+Hyeon|Didact+Gothic" rel="stylesheet">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" />
<script src="<%=request.getContextPath() %>/js/jquery-3.3.1.js"></script>

<script>
	$(function(){
		$(".menu").each(function(){
			if($(this).hasClass("<%=selected_menu%>")){
				$(this).find("a").css({
					"border-bottom":"5px solid #00165e",
					"padding-bottom": "10px",
					"color":"#00165e"
				});
			}
		});
		
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
		
		$(".stepmenu").each(function(){
			var div_id = $(this).attr("id");
			
			if(div_id == "step1" && div_id == "<%=selected_submenu%>"){
				$(this).css({
					"background":"#fbfbfb",
					"color":"#6480db",
					"border-top":"1px solid #6480db",
					"border-left":"1px solid #6480db",
					"border-right":"1px solid #6480db"
				});
				$("#step2").css({
					"background": "#2f4488",
					"color": "white"
				});
				$("#step3").css({
					"background": "#04185b",
					"color": "white"
				});
				$("#reservation-content").css("border-color", "#6480db");
			}
			if(div_id == "step2" && div_id == "<%=selected_submenu%>"){
				$(this).css({
					"background":"#fbfbfb",
					"color":"#2f4488",
					"border-top":"1px solid #2f4488",
					"border-left":"1px solid #2f4488",
					"border-right":"1px solid #2f4488"
				});
				$("#step1").css({
					"background": "#6480db",
					"color": "white"
				});
				$("#step3").css({
					"background": "#04185b",
					"color": "white"
				});
				$("#reservation-content").css("border-color", "#2f4488");
			}
			if(div_id == "step3" && div_id == "<%=selected_submenu%>"){
				$(this).css({
					"background":"#fbfbfb",
					"color":"#04185b",
					"border-top":"1px solid #04185b",
					"border-left":"1px solid #04185b",
					"border-right":"1px solid #04185b"
				});
				$("#step1").css({
					"background": "#6480db",
					"color": "white"
				});
				$("#step2").css({
					"background": "#2f4488",
					"color": "white"
				});
				$("#reservation-content").css("border-color", "#04185b");
			}
		});
	});
</script>
	
<body>
	<header id = "header">
		<div id="title-wrapper">
			<a href="<%=request.getContextPath()%>">
				<img class="logo" src="<%=request.getContextPath() %>/img/logo.png" alt="logo_panacea" />
				<h1 id="title">Panacea</h1>
			</a>
		</div>
		
  		<nav id="nav-wrapper">
    		<ul class="main-nav">
	        	<li class="menu info"><a href="<%=request.getContextPath()%>/info/infoView">병원소개</a></li>
	        	<li class="menu reservation"><a href="<%=request.getContextPath()%>/reservation/step1Reservation">진료예약</a></li>
	        	<li class="menu notice"><a href="<%=request.getContextPath()%>/notice/noticeList">공지사항</a></li>
	        	<li class="menu review"><a href="<%=request.getContextPath()%>/review/reviewList">진료후기</a></li>
	        	<%if(!isLogin){ %>
		        	<li class="menu login" id="partition"><a href="<%=request.getContextPath()%>/common/login">로그인</a></li>
		        	<li class="menu join"><a href="<%=request.getContextPath()%>/patient/join">회원가입</a></li>
		        <%} else { %>
		        	<li id="partition"><a href="<%=request.getContextPath()%>/common/logout">로그아웃</a></li>
		        	
		        	<% if("patient".equals(loginType) && loginPatient !=null){%>
						<li class="menu mypage">	
							<a href="<%=request.getContextPath()%>/<%=loginType %>/<%=loginType %>Mypage?userId=<%=loginPatient.getPatientId() %>">마이페이지</a>
						</li>
					<%} else if("doctor".equals(loginType) && loginDoctor !=null){%>
						<li class="menu mypage">
							<a href="<%=request.getContextPath()%>/<%=loginType %>/<%=loginType %>Mypage?userId=<%=loginDoctor.getDoctorId() %>">마이페이지</a>
						</li>
					<%}else if("admin".equals(loginType) && loginAdmin !=null){%>
						<li class="menu mypage">
							<a href="<%=request.getContextPath()%>/<%=loginType %>/patientList">마이페이지</a>
						</li>
					<%} %>
				<%} %>
    		</ul>
    		<hr>
  		</nav>
	</header>
	
	<section id="content-section">