/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.0.50
 * Generated at: 2018-05-17 10:42:13 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.notice;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.panacea.patient.model.vo.*;
import com.panacea.doctor.model.vo.*;
import com.panacea.admin.model.vo.*;
import java.util.*;
import com.panacea.notice.model.vo.*;

public final class noticeFinder_jsp extends org.apache.jasper.runtime.HttpJspBase
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
    _jspx_imports_packages.add("com.panacea.doctor.model.vo");
    _jspx_imports_packages.add("com.panacea.patient.model.vo");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_packages.add("com.panacea.notice.model.vo");
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
      out.write("    ");
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
      out.write("    \r\n");
      out.write("    \t");

			List<Notice> list = (List<Notice>)request.getAttribute("list");

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<div id=\"subnav-wrapper\">\r\n");
      out.write("\t<span id=\"subnav-title\">공지사항 검색</span>\r\n");
      out.write("\t<div id=\"sub_menu\">\r\n");
      out.write("\t \t<div id=\"search-container\">\r\n");
      out.write("\t\t검색타입:\r\n");
      out.write("\t\t<select name=\"searchType\" id=\"searchType\">\r\n");
      out.write("\t\t\t<option value=\"admin_Id\">아이디</option>\r\n");
      out.write("\t\t\t<option value=\"notice_Title\">제목</option>\r\n");
      out.write("\t\t\t<option value=\"notice_Date\">날짜</option>\r\n");
      out.write("\t\t</select>\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<div id=\"search-admin_Id\">\r\n");
      out.write("\t\t\t<form action=\"");
      out.print(request.getContextPath());
      out.write("/notice/noticeFinder\">\r\n");
      out.write("\t\t\t\t<input type=\"hidden\" name=\"searchType\" value=\"admin_Id\" />\r\n");
      out.write("\t\t\t\t<input type=\"search\" name=\"searchKeyword\" size=\"20\" class=\"aa\" placeholder=\"검색할 아이디를 입력하세요\" required/>\r\n");
      out.write("\t\t\t\t<button type=\"submit\">검색</button>\r\n");
      out.write("\t\t\t</form>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<div id=\"search-notice_Title\">\r\n");
      out.write("\t\t\t<form action=\"");
      out.print(request.getContextPath());
      out.write("/notice/noticeFinder\">\r\n");
      out.write("\t\t\t\t<input type=\"hidden\" name=\"searchType\" value=\"notice_Title\" />\r\n");
      out.write("\t\t\t\t<input type=\"search\" name=\"searchKeyword\" size=\"20\" class=\"aa\" placeholder=\"검색할 제목을 입력하세요\" required/>\r\n");
      out.write("\t\t\t\t<button type=\"submit\">검색</button>\r\n");
      out.write("\t\t\t</form>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<div id=\"search-notice_Date\">\r\n");
      out.write("\t\t\t<form action=\"");
      out.print(request.getContextPath());
      out.write("/notice/noticeFinder\">\r\n");
      out.write("\t\t\t\t<input type=\"hidden\" name=\"searchType\" value=\"notice_Date\" />\r\n");
      out.write("\t\t\t\t<input type=\"search\" name=\"searchKeyword\" size=\"20\" class=\"aa\" placeholder=\"검색할 날짜을 입력하세요\" required/>\r\n");
      out.write("\t\t\t\t<button type=\"submit\">검색</button>\r\n");
      out.write("\t\t\t</form>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\r\n");
      out.write("\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("\t<script>\r\n");
      out.write("\t\t$(function(){\r\n");
      out.write("\t\t\t$(\".submenu\").each(function(){\r\n");
      out.write("\t\t\t\tif($(this).hasClass(\"");
      out.print(selected_submenu);
      out.write("\")){\r\n");
      out.write("\t\t\t\t\t$(this).css({\r\n");
      out.write("\t\t\t\t\t\t\"background-image\": \"url('../img/subnav_back.png')\",\r\n");
      out.write("\t\t\t\t    \t\"background-repeat\": \"no-repeat\",\r\n");
      out.write("\t\t\t\t    \t\"background-position\": \"right\"\r\n");
      out.write("\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\t$(this).find(\"a\").css({\r\n");
      out.write("\t\t\t\t\t\t\"color\": \"#3954ad\",\r\n");
      out.write("\t\t\t\t    \t\"text-decoration\": \"underline\"\r\n");
      out.write("\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t});\r\n");
      out.write("\t</script>\r\n");
      out.write("<style>\r\n");
      out.write("div#notice-container{\r\n");
      out.write("\twidth:700px;\r\n");
      out.write("\tmargin:0 auto; \r\n");
      out.write("\ttext-align:center;\r\n");
      out.write("}\r\n");
      out.write("div#notice-container h2{margin:10px 0;}\r\n");
      out.write("table#tbl-notice{\r\n");
      out.write("   width:100%;\r\n");
      out.write("   margin:0 auto; \r\n");
      out.write("   border-top: 1px solid #ccc;\r\n");
      out.write("   border-spacing: 1px;\r\n");
      out.write("   border-collapse: separate;\r\n");
      out.write("   clear:both;\r\n");
      out.write("   \r\n");
      out.write("   \r\n");
      out.write("}\r\n");
      out.write("table#tbl-notice th, table#tbl-notice td {\r\n");
      out.write(" \r\n");
      out.write("    padding: 2px;\r\n");
      out.write("   font-size:15px;\r\n");
      out.write("    \r\n");
      out.write("    border-bottom: 1px solid #ccc;\r\n");
      out.write("} \r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("div#search-container{padding:5px; margin:0 0 20px 0; background:white; }\r\n");
      out.write("div#search-notice_Title, div#search-notice_Date {display: none;}\r\n");
      out.write("div#search-admin_Id{display:inline-block;}\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("input#btn-add{float:right; margin:0 0 15px;}\r\n");
      out.write("input#btn-delete{float:right; margin:0 0 15px;}\r\n");
      out.write("\r\n");
      out.write(".box{\r\n");
      out.write("width:70px;\r\n");
      out.write("text-align:left;\r\n");
      out.write("}\r\n");
      out.write(".center{\r\n");
      out.write("width:70px;\r\n");
      out.write("text-align:center;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".num{\r\n");
      out.write("width:50px;\r\n");
      out.write("text-align:center;\r\n");
      out.write("}\r\n");
      out.write(".title{\r\n");
      out.write("width:300px;\r\n");
      out.write("display:inline-block;\r\n");
      out.write("overflow: hidden;\r\n");
      out.write("text-overflow: ellipsis;\r\n");
      out.write("white-space:nowrap;\r\n");
      out.write("} \r\n");
      out.write(".writer{\r\n");
      out.write("width:100px;\r\n");
      out.write("}\r\n");
      out.write(".file{\r\n");
      out.write("width:100px;\r\n");
      out.write("}\r\n");
      out.write(".date{\r\n");
      out.write("width:100px;\r\n");
      out.write("}\r\n");
      out.write("</style>\r\n");
      out.write("\r\n");
      out.write("<script>\r\n");
      out.write("$(function(){\r\n");
      out.write("\t\r\n");
      out.write("\t$(\"#searchType\").change(function(){\r\n");
      out.write("\t\t$(\"#search-admin_Id\").hide();\t\r\n");
      out.write("\t\t$(\"#search-notice_Title\").hide();\t\r\n");
      out.write("\t\t$(\"#search-notice_Date\").hide();\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t//$(\"#search-\"+$(this).val()).show();\r\n");
      out.write("\t\t$(\"#search-\"+$(this).val()).css('display','inline-block');\r\n");
      out.write("\t});\t\r\n");
      out.write("});\r\n");
      out.write("\r\n");
      out.write("</script>\r\n");
      out.write("<div id=\"content-wrapper\">\r\n");
      out.write("<div id=\"notice-container\">\r\n");
      out.write("\t<section id=\"memberList-container\">\r\n");
      out.write("\t<h2>공지사항</h2>\r\n");
      out.write("\t\r\n");
      out.write("\t");
      out.write('\r');
      out.write('\n');
      out.write('	');
if(loginAdmin!=null ){ 
      out.write("\r\n");
      out.write("\t<input type=\"button\" value=\"글쓰기\" id=\"btn-add\" onclick=\"fn_insertNotice();\" />\r\n");
      out.write("\t<input type=\"button\" value=\"삭제\" id=\"btn-delete\" onclick=\"fn_deleteNotice();\" />\r\n");
      out.write("\t<script>\r\n");
      out.write("\tfunction fn_insertNotice(){\r\n");
      out.write("\t\tlocation.href=\"");
      out.print(request.getContextPath());
      out.write("/notice/noticeForm\";\r\n");
      out.write("\t}\r\n");
      out.write("\t</script>\r\n");
} 
      out.write("\r\n");
      out.write("\t<table id=\"tbl-notice\" class=\"tal\">\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t<th class=\"box\">전체<input type=\"checkbox\" name=\"checkAll\" id=\"th_checkAll\" onclick=\"checkAll();\"/></th>\r\n");
      out.write("\t\t\t<th class=\"num\">번호</th>\r\n");
      out.write("\t\t\t<th class=\"title\">제목</th>\r\n");
      out.write("\t\t\t<th class=\"writer\">작성자</th>\r\n");
      out.write("\t\t\t<th class=\"file\">첨부파일</th>\r\n");
      out.write("\t\t\t<th class=\"date\">작성일</th>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t");
 for(Notice n : list){ 
      out.write("\r\n");
      out.write("        <tr>\r\n");
      out.write("         <td class=\"center\"><input type=\"checkbox\" name=\"checkRow\" value=\"");
      out.print( n.getNoticeNo() );
      out.write("\" /></td>\r\n");
      out.write("            <td>");
      out.print( n.getNoticeNo() );
      out.write("</td>\r\n");
      out.write("            <td class=\"title\" >\r\n");
      out.write("                <a href=\"");
      out.print(request.getContextPath() );
      out.write("/notice/noticeView?no=");
      out.print( n.getNoticeNo() );
      out.write("\" >\r\n");
      out.write("                    ");
      out.print( n.getNoticeTitle() );
      out.write("\r\n");
      out.write("                </a>\r\n");
      out.write("            </td>\r\n");
      out.write("            <td>");
      out.print( n.getAdminId() );
      out.write("</td>\r\n");
      out.write("            <td>\r\n");
      out.write("            ");
 if(n.getNoticeFile() != null){ 
      out.write("\r\n");
      out.write("            <img alt=\"첨부파일\" src=\"");
      out.print(request.getContextPath() );
      out.write("/img/file.png\">\r\n");
      out.write("            ");
 }
      out.write("\r\n");
      out.write("            </td>\r\n");
      out.write("            <td>");
      out.print( n.getNoticeDate() );
      out.write("</td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        ");
 } 
      out.write("\r\n");
      out.write("     \r\n");
      out.write("        \r\n");
      out.write("\t</table>\r\n");
      out.write("\t   \t<div id=\"pageBar\">");
      out.print(request.getAttribute("pageBar"));
      out.write("</div>\r\n");
      out.write("</div>\r\n");
      out.write("</div>   \r\n");
      out.write("    \r\n");
      out.write(" <script>\r\n");
      out.write("function checkAll(){\r\n");
      out.write("    if( $(\"#th_checkAll\").is(':checked') ){\r\n");
      out.write("      $(\"input[name=checkRow]\").prop(\"checked\", true);\r\n");
      out.write("    }else{\r\n");
      out.write("      $(\"input[name=checkRow]\").prop(\"checked\", false);\r\n");
      out.write("    }\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function fn_deleteNotice(){\r\n");
      out.write("var test =new Array();\r\n");
      out.write("\r\n");
      out.write("$(\"input[name=checkRow]:checked\").each(function(){\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\ttest += $(this).val()+\",\";\r\n");
      out.write("\r\n");
      out.write("\t\r\n");
      out.write("\r\n");
      out.write("});\r\n");
      out.write("alert(test);\r\n");
      out.write("\r\n");
      out.write("location.href=\"");
      out.print(request.getContextPath());
      out.write("/notice/noticeDelete?no=\"+test;\r\n");
      out.write("\r\n");
      out.write("\t//1. 배열에 넣어서 한방에 서블릿으로 보낸다\r\n");
      out.write("\t\r\n");
      out.write("\t//2. 서블릿에서 배열을 받아서 \r\n");
      out.write("\t//3. 그 배열의 인덱스만큼 포문을 돌려서 삭제한다.\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("</script>   \r\n");
      out.write("\r\n");
      out.write("    \r\n");
      out.write("    ");
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
