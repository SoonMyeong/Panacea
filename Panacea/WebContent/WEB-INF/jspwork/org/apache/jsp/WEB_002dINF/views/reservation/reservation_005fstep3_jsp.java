/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.0.50
 * Generated at: 2018-05-17 09:34:31 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.reservation;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.panacea.part.model.vo.*;
import java.util.*;
import com.panacea.patient.model.vo.*;
import com.panacea.doctor.model.vo.*;
import com.panacea.admin.model.vo.*;

public final class reservation_005fstep3_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(2);
    _jspx_dependants.put("/WEB-INF/views/common/header.jsp", Long.valueOf(1526547638000L));
    _jspx_dependants.put("/WEB-INF/views/common/footer.jsp", Long.valueOf(1525785638000L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("com.panacea.admin.model.vo");
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("java.util");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("com.panacea.part.model.vo");
    _jspx_imports_packages.add("com.panacea.doctor.model.vo");
    _jspx_imports_packages.add("com.panacea.patient.model.vo");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

final java.lang.String _jspx_method = request.getMethod();
if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET POST or HEAD");
return;
}

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");

	MedicalPart selectedPart = (MedicalPart)request.getAttribute("part");
	Doctor selectedDoctor = (Doctor)request.getAttribute("doctor");

      out.write("\r\n");
      out.write("\r\n");

	Calendar c = Calendar.getInstance();
	int currYear = c.get(Calendar.YEAR);
	int currMonth = c.get(Calendar.MONTH)+1;
	int currDay = c.get(Calendar.DAY_OF_MONTH);

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");

	String selected_menu = (String)request.getAttribute("selectedMenu");
	String selected_submenu = (String)request.getAttribute("selectedSubMenu");

      out.write('\r');
      out.write('\n');

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

	

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<title>PANACEA</title>\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<link href=\"https://fonts.googleapis.com/css?family=Do+Hyeon|Didact+Gothic\" rel=\"stylesheet\">\r\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.print(request.getContextPath() );
      out.write("/css/style.css\" />\r\n");
      out.write("<script src=\"");
      out.print(request.getContextPath() );
      out.write("/js/jquery-3.3.1.js\"></script>\r\n");
      out.write("\r\n");
      out.write("<script>\r\n");
      out.write("\t$(function(){\r\n");
      out.write("\t\t$(\".menu\").each(function(){\r\n");
      out.write("\t\t\tif($(this).hasClass(\"");
      out.print(selected_menu);
      out.write("\")){\r\n");
      out.write("\t\t\t\t$(this).find(\"a\").css({\r\n");
      out.write("\t\t\t\t\t\"border-bottom\":\"5px solid #00165e\",\r\n");
      out.write("\t\t\t\t\t\"padding-bottom\": \"10px\",\r\n");
      out.write("\t\t\t\t\t\"color\":\"#00165e\"\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t$(\".submenu\").each(function(){\r\n");
      out.write("\t\t\tif($(this).hasClass(\"");
      out.print(selected_submenu);
      out.write("\")){\r\n");
      out.write("\t\t\t\t$(this).css({\r\n");
      out.write("\t\t\t\t\t\"background-image\": \"url('../img/subnav_back.png')\",\r\n");
      out.write("\t\t\t    \t\"background-repeat\": \"no-repeat\",\r\n");
      out.write("\t\t\t    \t\"background-position\": \"right\"\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t\t$(this).find(\"a\").css({\r\n");
      out.write("\t\t\t\t\t\"color\": \"#3954ad\",\r\n");
      out.write("\t\t\t    \t\"text-decoration\": \"underline\"\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t$(\".stepmenu\").each(function(){\r\n");
      out.write("\t\t\tvar div_id = $(this).attr(\"id\");\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\tif(div_id == \"step1\" && div_id == \"");
      out.print(selected_submenu);
      out.write("\"){\r\n");
      out.write("\t\t\t\t$(this).css({\r\n");
      out.write("\t\t\t\t\t\"background\":\"#fbfbfb\",\r\n");
      out.write("\t\t\t\t\t\"color\":\"#6480db\",\r\n");
      out.write("\t\t\t\t\t\"border-top\":\"1px solid #6480db\",\r\n");
      out.write("\t\t\t\t\t\"border-left\":\"1px solid #6480db\",\r\n");
      out.write("\t\t\t\t\t\"border-right\":\"1px solid #6480db\"\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t\t$(\"#step2\").css({\r\n");
      out.write("\t\t\t\t\t\"background\": \"#2f4488\",\r\n");
      out.write("\t\t\t\t\t\"color\": \"white\"\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t\t$(\"#step3\").css({\r\n");
      out.write("\t\t\t\t\t\"background\": \"#04185b\",\r\n");
      out.write("\t\t\t\t\t\"color\": \"white\"\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t\t$(\"#reservation-content\").css(\"border-color\", \"#6480db\");\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tif(div_id == \"step2\" && div_id == \"");
      out.print(selected_submenu);
      out.write("\"){\r\n");
      out.write("\t\t\t\t$(this).css({\r\n");
      out.write("\t\t\t\t\t\"background\":\"#fbfbfb\",\r\n");
      out.write("\t\t\t\t\t\"color\":\"#2f4488\",\r\n");
      out.write("\t\t\t\t\t\"border-top\":\"1px solid #2f4488\",\r\n");
      out.write("\t\t\t\t\t\"border-left\":\"1px solid #2f4488\",\r\n");
      out.write("\t\t\t\t\t\"border-right\":\"1px solid #2f4488\"\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t\t$(\"#step1\").css({\r\n");
      out.write("\t\t\t\t\t\"background\": \"#6480db\",\r\n");
      out.write("\t\t\t\t\t\"color\": \"white\"\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t\t$(\"#step3\").css({\r\n");
      out.write("\t\t\t\t\t\"background\": \"#04185b\",\r\n");
      out.write("\t\t\t\t\t\"color\": \"white\"\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t\t$(\"#reservation-content\").css(\"border-color\", \"#2f4488\");\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tif(div_id == \"step3\" && div_id == \"");
      out.print(selected_submenu);
      out.write("\"){\r\n");
      out.write("\t\t\t\t$(this).css({\r\n");
      out.write("\t\t\t\t\t\"background\":\"#fbfbfb\",\r\n");
      out.write("\t\t\t\t\t\"color\":\"#04185b\",\r\n");
      out.write("\t\t\t\t\t\"border-top\":\"1px solid #04185b\",\r\n");
      out.write("\t\t\t\t\t\"border-left\":\"1px solid #04185b\",\r\n");
      out.write("\t\t\t\t\t\"border-right\":\"1px solid #04185b\"\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t\t$(\"#step1\").css({\r\n");
      out.write("\t\t\t\t\t\"background\": \"#6480db\",\r\n");
      out.write("\t\t\t\t\t\"color\": \"white\"\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t\t$(\"#step2\").css({\r\n");
      out.write("\t\t\t\t\t\"background\": \"#2f4488\",\r\n");
      out.write("\t\t\t\t\t\"color\": \"white\"\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t\t$(\"#reservation-content\").css(\"border-color\", \"#04185b\");\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t});\r\n");
      out.write("</script>\r\n");
      out.write("\t\r\n");
      out.write("<body>\r\n");
      out.write("\t<header id = \"header\">\r\n");
      out.write("\t\t<div id=\"title-wrapper\">\r\n");
      out.write("\t\t\t<a href=\"");
      out.print(request.getContextPath());
      out.write("\">\r\n");
      out.write("\t\t\t\t<img class=\"logo\" src=\"");
      out.print(request.getContextPath() );
      out.write("/img/logo.png\" alt=\"logo_panacea\" />\r\n");
      out.write("\t\t\t\t<h1 id=\"title\">Panacea</h1>\r\n");
      out.write("\t\t\t</a>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t\r\n");
      out.write("  \t\t<nav id=\"nav-wrapper\">\r\n");
      out.write("    \t\t<ul class=\"main-nav\">\r\n");
      out.write("\t        \t<li class=\"menu info\"><a href=\"");
      out.print(request.getContextPath());
      out.write("/info/infoView\">병원소개</a></li>\r\n");
      out.write("\t        \t<li class=\"menu reservation\"><a href=\"");
      out.print(request.getContextPath());
      out.write("/reservation/step1Reservation\">진료예약</a></li>\r\n");
      out.write("\t        \t<li class=\"menu notice\"><a href=\"");
      out.print(request.getContextPath());
      out.write("/notice/noticeList\">공지사항</a></li>\r\n");
      out.write("\t        \t<li class=\"menu review\"><a href=\"");
      out.print(request.getContextPath());
      out.write("/review/reviewList\">진료후기</a></li>\r\n");
      out.write("\t        \t");
if(!isLogin){ 
      out.write("\r\n");
      out.write("\t\t        \t<li class=\"menu login\" id=\"partition\"><a href=\"");
      out.print(request.getContextPath());
      out.write("/common/login\">로그인</a></li>\r\n");
      out.write("\t\t        \t<li class=\"menu join\"><a href=\"");
      out.print(request.getContextPath());
      out.write("/patient/join\">회원가입</a></li>\r\n");
      out.write("\t\t        ");
} else { 
      out.write("\r\n");
      out.write("\t\t        \t<li id=\"partition\"><a href=\"");
      out.print(request.getContextPath());
      out.write("/common/logout\">로그아웃</a></li>\r\n");
      out.write("\t\t        \t\r\n");
      out.write("\t\t        \t");
 if("patient".equals(loginType) && loginPatient !=null){
      out.write("\r\n");
      out.write("\t\t\t\t\t\t<li class=\"menu mypage\">\t\r\n");
      out.write("\t\t\t\t\t\t\t<a href=\"");
      out.print(request.getContextPath());
      out.write('/');
      out.print(loginType );
      out.write('/');
      out.print(loginType );
      out.write("Mypage?userId=");
      out.print(loginPatient.getPatientId() );
      out.write("\">마이페이지</a>\r\n");
      out.write("\t\t\t\t\t\t</li>\r\n");
      out.write("\t\t\t\t\t");
} else if("doctor".equals(loginType) && loginDoctor !=null){
      out.write("\r\n");
      out.write("\t\t\t\t\t\t<li class=\"menu mypage\">\r\n");
      out.write("\t\t\t\t\t\t\t<a href=\"");
      out.print(request.getContextPath());
      out.write('/');
      out.print(loginType );
      out.write('/');
      out.print(loginType );
      out.write("Mypage?userId=");
      out.print(loginDoctor.getDoctorId() );
      out.write("\">마이페이지</a>\r\n");
      out.write("\t\t\t\t\t\t</li>\r\n");
      out.write("\t\t\t\t\t");
}else if("admin".equals(loginType) && loginAdmin !=null){
      out.write("\r\n");
      out.write("\t\t\t\t\t\t<li class=\"menu mypage\">\r\n");
      out.write("\t\t\t\t\t\t\t<a href=\"");
      out.print(request.getContextPath());
      out.write('/');
      out.print(loginType );
      out.write("/patientList\">마이페이지</a>\r\n");
      out.write("\t\t\t\t\t\t</li>\r\n");
      out.write("\t\t\t\t\t");
} 
      out.write("\r\n");
      out.write("\t\t\t\t");
} 
      out.write("\r\n");
      out.write("    \t\t</ul>\r\n");
      out.write("    \t\t<hr>\r\n");
      out.write("  \t\t</nav>\r\n");
      out.write("\t</header>\r\n");
      out.write("\t\r\n");
      out.write("\t<section id=\"content-section\">");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<div id=\"subnav-wrapper\">\r\n");
      out.write("\t<span id=\"subnav-title\">진료예약</span>\r\n");
      out.write("\t<div id=\"sub_menu\">\r\n");
      out.write("\t    <p class=\"submenu step1\"><a href=\"\">STEP1 :진료과</a></p>\r\n");
      out.write("\t    <p class=\"submenu step2\"><a href=\"\">STEP2 :의료진</a></p>\r\n");
      out.write("\t    <p class=\"submenu step3\"><a href=\"\">STEP3 :예약시간</a></p>\r\n");
      out.write("\t</div>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<div id=\"content-wrapper\">\r\n");
      out.write("\t<div class=\"stepmenu\" id=\"step1\">STEP1 :진료과</div>\r\n");
      out.write("\t<div class=\"stepmenu\" id=\"step2\">STEP2 :의료진</div>\r\n");
      out.write("\t<div class=\"stepmenu\" id=\"step3\">STEP3 :예약시간</div>\r\n");
      out.write("\t\r\n");
      out.write("\t<div id=\"reservation-content\">\r\n");
      out.write("\t\t<div id=\"select-date-time\">\r\n");
      out.write("\t\t\t<h3 id=\"date-time-title\">예약날짜를 선택하세요</h3>\r\n");
      out.write("\t\t\t<span id=\"reservation-notice\">*당일 예약은 불가합니다.<br></span>\r\n");
      out.write("\t\t\t<span id=\"reservation-notice\">*최대 3개월까지 예약 가능합니다.<br></span>\r\n");
      out.write("\t\t\t<input type=\"date\" name=\"date\" id=\"date\">\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t\t<br><br><br><br>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t<h3 id=\"date-time-title\">예약시간을 선택하세요</h3>\r\n");
      out.write("\t \t\t<select id=\"time\" name=\"time\">\r\n");
      out.write("\t \t\t\t<option value=\"-\" selected disabled>------시간선택------</option>\r\n");
      out.write("\t\t\t\t<option value=\"10:00\">10:00</option>\r\n");
      out.write("\t\t\t\t<option value=\"10:30\">10:30</option>\r\n");
      out.write("\t\t\t\t<option value=\"11:00\">11:00</option>\r\n");
      out.write("\t\t\t\t<option value=\"11:30\">11:30</option>\r\n");
      out.write("\t\t\t\t<option value=\"12:00\">12:00</option>\r\n");
      out.write("\t\t\t\t<option value=\"12:30\">12:30</option>\r\n");
      out.write("\t\t\t\t<option value=\"14:00\">14:00</option>\r\n");
      out.write("\t\t\t\t<option value=\"14:30\">14:30</option>\r\n");
      out.write("\t\t\t\t<option value=\"15:00\">15:00</option>\r\n");
      out.write("\t\t\t\t<option value=\"15:30\">15:30</option>\r\n");
      out.write("\t\t\t\t<option value=\"16:00\">16:00</option>\r\n");
      out.write("\t\t\t\t<option value=\"16:30\">16:30</option>\r\n");
      out.write("\t\t\t\t<option value=\"17:00\">17:00</option>\r\n");
      out.write("\t\t\t\t<option value=\"17:30\">17:30</option>\r\n");
      out.write("\t\t\t</select>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<div id=\"reservation-result\">\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<script>\r\n");
      out.write("\t\r\n");
      out.write("$(\"#date\").change(function(){\r\n");
      out.write("\t\r\n");
      out.write("\t$(\"#time\").children().each(function(){\r\n");
      out.write("\t\t$(this).removeAttr(\"disabled\");\r\n");
      out.write("\t\tif($(this).val() == \"-\"){\r\n");
      out.write("\t\t\t$(this).attr(\"disabled\",\"disabled\");\r\n");
      out.write("\t\t}\r\n");
      out.write("\t});\r\n");
      out.write("\t$(\"#time\").val(\"\");\r\n");
      out.write("\t$(\"#reservation-result\").html(\"\");\r\n");
      out.write("\t\r\n");
      out.write("\tvar year = $(this).val().substring(0,4);\r\n");
      out.write("\tvar month = $(this).val().substring(5,7);\r\n");
      out.write("\tvar day = $(this).val().substring(8,10);\r\n");
      out.write("\t\r\n");
      out.write("\tif(");
      out.print(currYear );
      out.write(" > year){\r\n");
      out.write("\t\talert(\"예약날짜를 확인하세요\");\r\n");
      out.write("\t\t$(this).val(\"\");\r\n");
      out.write("\t} else if(");
      out.print(currYear);
      out.write(" < year){\r\n");
      out.write("\t\talert(\"최대 3개월까지 진료 예약 가능합니다.\");\r\n");
      out.write("\t\t$(this).val(\"\");\r\n");
      out.write("\t} else if(");
      out.print(currMonth);
      out.write(" > month){\r\n");
      out.write("\t\talert(\"예약날짜를 확인하세요\");\r\n");
      out.write("\t\t$(this).val(\"\");\r\n");
      out.write("\t} else if(");
      out.print(currMonth);
      out.write("+3 < month ){\r\n");
      out.write("\t\talert(\"최대 3개월까지 진료 예약 가능합니다.\");\r\n");
      out.write("\t\t$(this).val(\"\");\r\n");
      out.write("\t} else if(");
      out.print(currMonth);
      out.write("==month && ");
      out.print(currDay);
      out.write("+1 > day){\r\n");
      out.write("\t\talert(\"예약날짜를 확인하세요\");\r\n");
      out.write("\t\t$(this).val(\"\");\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\t$.ajax({\r\n");
      out.write("\t\turl:\"");
      out.print(request.getContextPath());
      out.write("/reservation/checkTime\",\r\n");
      out.write("\t\t   dataType:\"json\",\r\n");
      out.write("\t\t   data:\"patientId=");
      out.print(loginPatient.getPatientId());
      out.write("&doctorId=");
      out.print(selectedDoctor.getDoctorId());
      out.write("&date=\"+$(this).val(),\r\n");
      out.write("\t\t   type:\"get\",\r\n");
      out.write("\t\t   success:function(data){\r\n");
      out.write("\t\t\t   for(var i = 0; i < data.length; i++){\r\n");
      out.write("\t\t\t\t\tconsole.log(data[i]);\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t$(\"#time\").children().each(function(){\r\n");
      out.write("\t\t\t\t\t\tif($(this).val() == data[i]){\r\n");
      out.write("\t\t\t\t\t\t\tconsole.log(\"disabled 시킴\");\r\n");
      out.write("\t\t\t\t\t\t\t$(this).attr(\"disabled\",\"disabled\");\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t   $(\"#time\").val(\"-\");\r\n");
      out.write("\t\t   },\r\n");
      out.write("\t\t   error : function(jqxhr,textStatus,errorThrown){\r\n");
      out.write("\t\t      console.log(\"ajax 에러!\");\r\n");
      out.write("\t\t      console.log(jqxhr);\r\n");
      out.write("\t\t      console.log(textStatus);\r\n");
      out.write("\t\t      console.log(errorThrown);\r\n");
      out.write("\t\t   }\r\n");
      out.write("\t});\r\n");
      out.write("\t\r\n");
      out.write("});\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("$(\"#time\").change(function(){\r\n");
      out.write("\tconsole.log(\"시간선택함\");\r\n");
      out.write("\tvar html = '<h3 id=\"result-title\">예약 내용 확인</h3>';\r\n");
      out.write("\thtml+= '<hr id=\"part-partition\"><br>';\r\n");
      out.write("\thtml+= '<span id=\"part-result\">");
      out.print(selectedPart.getPartName() );
      out.write("</span>';\r\n");
      out.write("\thtml+= '<br>';\r\n");
      out.write("\thtml+= '<span id=\"doctor-result\">");
      out.print(selectedDoctor.getDoctorName() );
      out.write("</span>선생님';\r\n");
      out.write("\thtml+= '<br>';\r\n");
      out.write("\thtml+= '<span id=\"date-time-result\">'+$(\"#date\").val()+' '+$(\"#time\").val()+'</span>';\r\n");
      out.write("\thtml+= '<br>';\r\n");
      out.write("\thtml+= '<input type=\"button\" id=\"final-reservation-btn\" value=\"예약하기\" onclick=\"reservation();\" />';\r\n");
      out.write("\t\r\n");
      out.write("\t$(\"#reservation-result\").html(html);\r\n");
      out.write("});\r\n");
      out.write("\r\n");
      out.write("function reservation(){\r\n");
      out.write("\t\r\n");
      out.write("\tif($(\"#date\").val() == \"\" || $(\"#time\").val() == \"\"){\r\n");
      out.write("\t\tif($(\"#date\").val() == \"\")\r\n");
      out.write("\t\t\talert(\"예약날짜를 선택하세요.\");\r\n");
      out.write("\t\tif($(\"#time\").val() == \"\")\r\n");
      out.write("\t\t\talert(\"예약시간을 선택하세요\");\r\n");
      out.write("\t\treturn;\r\n");
      out.write("\t}\r\n");
      out.write("\tlocation.href=\"");
      out.print(request.getContextPath());
      out.write("/reservation/insertReservation?partId=");
      out.print(selectedPart.getPartId());
      out.write("&doctorId=");
      out.print(selectedDoctor.getDoctorId());
      out.write("&date=\"+$(\"#date\").val()+\"&time=\"+$(\"#time\").val();\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\r\n");
      out.write("\t</section>\r\n");
      out.write("\t\r\n");
      out.write("\t<footer id = \"footer\">\r\n");
      out.write("\t\t<img class=\"footer-logo\" src=\"");
      out.print(request.getContextPath() );
      out.write("/img/logo.png\" alt=\"logo_panacea\">\r\n");
      out.write("\t\t<h3 id=\"footer-title\">Panacea</h3>\r\n");
      out.write("\t    &nbsp;&nbsp;\r\n");
      out.write("\t    <p id = \"footer-content\">\r\n");
      out.write("\t    \t강남구 역삼동 파나시아병원 <br />\r\n");
      out.write("\t    \t ⓒPanacia Corp.\r\n");
      out.write("\t    </p>\r\n");
      out.write("\t           \r\n");
      out.write("\t</footer>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
