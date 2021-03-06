/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.0.50
 * Generated at: 2018-05-17 09:02:31 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.patient;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.panacea.patient.model.vo.*;
import com.panacea.doctor.model.vo.*;
import com.panacea.admin.model.vo.*;

public final class join_jsp extends org.apache.jasper.runtime.HttpJspBase
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
    _jspx_imports_packages.add("javax.servlet.http");
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
      out.write("    \r\n");
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
      out.write("\r\n");
      out.write("<div id=\"subnav-wrapper\">\r\n");
      out.write("\t<span id=\"subnav-title\">로그인</span>\r\n");
      out.write("\t<div id=\"sub_menu\">\r\n");
      out.write("\t\t<p class=\"submenu login\"><a href=\"");
      out.print(request.getContextPath());
      out.write("/common/login\">로그인</a></p>\r\n");
      out.write("\t    <p class=\"submenu joinView\"><a href=\"");
      out.print(request.getContextPath());
      out.write("/patient/join\">회원가입</a></p>\r\n");
      out.write("\t</div>\r\n");
      out.write("</div>\r\n");
      out.write("\t\r\n");
      out.write("\t<!-- 아이디 중복검사 -->\r\n");
      out.write("\t<script>\r\n");
      out.write("\t$(function(){\r\n");
      out.write("\t\t$(\"#btn-idValid\").click(function(){\r\n");
      out.write("\t\t\tvar id= $(\"#userId\").val().trim();\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\tif(id.length<4){\r\n");
      out.write("\t\t\t\talert(\"아이디는 4글자이상 가능합니다.\");\r\n");
      out.write("\t\t\t}else{\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t\t$.ajax({\r\n");
      out.write("\t\t\t\t\turl:\"");
      out.print(request.getContextPath());
      out.write("/patient/checkDuplicate\",\r\n");
      out.write("\t\t\t\t\tdataType:\"json\",\r\n");
      out.write("\t\t\t\t\tdata:\"id=\"+id,\r\n");
      out.write("\t\t\t\t\ttype:\"get\",\r\n");
      out.write("\t\t\t\t\tsuccess:function(data){\r\n");
      out.write("\t\t\t\t\t\tconsole.log(data);\r\n");
      out.write("\t\t\t\t\t\tvar html=\"\";\r\n");
      out.write("\t\t\t\t\t\tif(data==null){\r\n");
      out.write("\t\t\t\t\t\t\t$(\"#idValid\").attr(\"value\",\"1\");\r\n");
      out.write("\t\t\t\t\t\t\tconsole.log($(\"#idValid\").val());\r\n");
      out.write("\t\t                \thtml += \"<span style='color:green;'>사용 가능한 아이디 입니다.</span>\";\r\n");
      out.write("\t\t\t                $(\"#id-check\").html(html);\r\n");
      out.write("\t\t\t            }else{\r\n");
      out.write("\t\t\t            \t$(\"#idValid\").attr(\"value\",\"0\");\r\n");
      out.write("\t\t\t            \tconsole.log($(\"#idValid\").val());\r\n");
      out.write("\t\t\t            \thtml += \"<span style='color:red;'>이미 사용중인 아이디 입니다.</span>\";\r\n");
      out.write("\t\t\t                $(\"#id-check\").html(html);\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\terror : function(jqxhr,textStatus,errorThrown){\r\n");
      out.write("\t\t\t\t\t\tconsole.log(\"ajax 에러!\");\r\n");
      out.write("\t\t\t\t\t\tconsole.log(jqxhr);\r\n");
      out.write("\t\t\t\t\t\tconsole.log(textStatus);\r\n");
      out.write("\t\t\t\t\t\tconsole.log(errorThrown);\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t$(\"#self-address\").change(function(){\r\n");
      out.write("\t\t\tif($(this).is(\":checked\")){\r\n");
      out.write("\t\t\t\tconsole.log($(this).val());\r\n");
      out.write("\t\t\t\t$(\"#address\").css(\"display\",\"none\");\r\n");
      out.write("\t\t\t\t$(\"#self-address-input\").css(\"display\",\"block\");\t\r\n");
      out.write("\t\t\t} else {\r\n");
      out.write("\t\t\t\tconsole.log($(this).val());\r\n");
      out.write("\t\t\t\t$(\"#address\").css(\"display\",\"block\");\r\n");
      out.write("\t\t\t\t$(\"#self-address-input\").css(\"display\",\"none\");\r\n");
      out.write("\t\t\t\t$(\"#address-city\").val(\"-\");\r\n");
      out.write("\t\t\t\t$(\"#address-local\").val(\"-\");\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t\r\n");
      out.write("\t});\r\n");
      out.write("\t</script>\r\n");
      out.write("\t\r\n");
      out.write("\t<!-- 유효성 검사  -->\r\n");
      out.write("\t<script>\r\n");
      out.write("\tfunction fn_joinVaildate(){\r\n");
      out.write("\t\tvar userId = $(\"#userId\").val().trim();\r\n");
      out.write("\t\tif(userId.length<4){\r\n");
      out.write("\t\t\talert(\"아이디는 4글자이상 가능합니다.\");\r\n");
      out.write("\t\t\t$(\"#userId\").val(\"\");\r\n");
      out.write("\t\t\t$(\"#userId\").focus();\r\n");
      out.write("\t\t\treturn false;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tif($(\"#idValid\").val()!='1'){\r\n");
      out.write("\t    \talert(\"아이디 중복검사를 실시하세요.\");\r\n");
      out.write("\t    \treturn false;\r\n");
      out.write("\t    }\r\n");
      out.write("\t \t\r\n");
      out.write("\t \tvar regExp = /^.*(?=^.{8,15}$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&*+=-]).*$/g;\r\n");
      out.write("\t\tif(!regExp.test($(\"#userPw\").val())){\r\n");
      out.write("\t\t\talert(\"비밀번호는 8~15글자이며 특수문자,영대소문자 포함하여야 합니다\");\r\n");
      out.write("\t\t\t$(\"#userPw\").val(\"\");\r\n");
      out.write("\t\t\t$(\"#userPw\").focus();\r\n");
      out.write("\t\t\treturn false;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t \t\r\n");
      out.write("\t \tif($(\"#userPw\").val().trim()!=$(\"#userPw_chk\").val().trim()){\r\n");
      out.write("\t    \talert(\"두 비밀번호가 같지 않습니다.\");\r\n");
      out.write("\t    \t$(\"#userPw_chk\").val(\"\");\r\n");
      out.write("\t    \t$(\"#userPw_chk\").focus();\r\n");
      out.write("\t    \treturn false;\r\n");
      out.write("\t    }\r\n");
      out.write("\t \t\r\n");
      out.write("\t \tvar regExp0 = /^[가-힣]{2,}$/;\r\n");
      out.write("\t\tif(!regExp0.test($(\"#userName\").val())){\r\n");
      out.write("\t\t\talert(\"2글자 이상인 한글 이름으로 입력하세요\");\r\n");
      out.write("\t\t\t$(\"#userName\").val(\"\");\r\n");
      out.write("\t\t\t$(\"#userName\").focus();\r\n");
      out.write("\t\t\treturn false;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t \t\r\n");
      out.write("\t \tvar regExp2 = /^[0-9]{2}((0[13578]|1[02])(0[1-9]|1[0-9]|2[0-9]|3[01])|(0[469]|11)(0[1-9]|1[0-9]|2[0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))$/;\r\n");
      out.write("\t \tif(!regExp2.test($(\"#ssd1\").val().trim())){\r\n");
      out.write("\t \t\talert(\"주민등록번호 앞자리를 확인해주세요\");\r\n");
      out.write("\t\t\t$(\"#ssd1\").val(\"\");\r\n");
      out.write("\t\t\t$(\"#ssd1\").focus();\r\n");
      out.write("\t\t\treturn false;\r\n");
      out.write("\t \t}\r\n");
      out.write("\t \t\r\n");
      out.write("\t \tvar regExp3 = /^[1-4]{1}$/;\r\n");
      out.write("\t \tif(!regExp3.test($(\"ssd2\").val().trim())){\r\n");
      out.write("\t \t\talert(\"주민등록번호 앞자리를 확인해주세요\");\r\n");
      out.write("\t\t\t$(\"#ssd2\").val(\"\");\r\n");
      out.write("\t\t\t$(\"#ssd2\").focus();\r\n");
      out.write("\t\t\treturn false;\r\n");
      out.write("\t \t}\r\n");
      out.write("\t \t\r\n");
      out.write("\t \tvar regExp4 = /^(010|070)[0-9]{8}$/;\r\n");
      out.write("\t\tif(!regExp4.test($(\"#phone\").val().trim())){\r\n");
      out.write("\t\t\talert(\"전화번호를 다시 입력해주세요\");\r\n");
      out.write("\t\t\t$(\"#phone\").val(\"\");\r\n");
      out.write("\t\t\t$(\"#phone\").focus();\r\n");
      out.write("\t\t\treturn false;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tif($(\"#self-address\").is(\":checked\")){\r\n");
      out.write("\t\t\tif($(\"#self-address-input\").val().trim().length < 5){\r\n");
      out.write("\t\t\t\talert(\"주소를 입력하세요\");\r\n");
      out.write("\t\t\t\treturn false;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t} else {\r\n");
      out.write("\t\t\tif($(\"#address-city\").val() == \"-\" || $(\"address-local\").val() == \"-\"){\r\n");
      out.write("\t\t\t\talert(\"주소를 선택하세요\");\r\n");
      out.write("\t\t\t\treturn false;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\t \t\r\n");
      out.write("\t\treturn true;\r\n");
      out.write("\t}\r\n");
      out.write("\t</script>\r\n");
      out.write("\t\r\n");
      out.write("\t\r\n");
      out.write("<div id=\"content-wrapper\">\r\n");
      out.write("\t\r\n");
      out.write("\t<form name=\"patient_join_form\" action=\"");
      out.print(request.getContextPath());
      out.write("/patient/joinEnd\" method=\"post\" onsubmit=\"return fn_joinVaildate();\">\r\n");
      out.write("\t\t<table id=\"join-table\">\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td colspan=\"2\">\r\n");
      out.write("\t\t\t\t\t<span id=\"join-notice\">*환자만 회원가입 할 수 있습니다.</span>\r\n");
      out.write("\t\t\t\t\t<br>\r\n");
      out.write("\t\t\t\t\t<span id=\"join-notice\">*모든 항목은 필수사항입니다.</span>\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write(" \t\t\t\t<td class=\"la\">아이디 :</td>\r\n");
      out.write("\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t<input type=\"text\" id=\"userId\" name=\"userId\" > &nbsp;&nbsp;\r\n");
      out.write("\t\t\t\t\t<input type=\"button\" id=\"btn-idValid\" value=\"중복체크\"><br>\r\n");
      out.write("\t\t\t\t\t<span id=\"id-check\"></span>\r\n");
      out.write("\t\t\t\t\t<input type=\"hidden\" id=\"idValid\" value=\"0\" />\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td class=\"la\">비밀번호 :</td>\r\n");
      out.write("\t\t\t\t<td><input type=\"password\" id=\"userPw\" name=\"userPw\" ></td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td class=\"la\">비밀번호 확인 :</td>\r\n");
      out.write("\t\t\t\t<td><input type=\"password\" id=\"userPw_chk\" ></td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td class=\"la\">이름 :</td>\r\n");
      out.write("\t\t\t\t<td><input type=\"text\" id=\"userName\" name=\"userName\"></td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td class=\"la\">주민등록번호 :</td>\r\n");
      out.write("\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t<input type=\"text\" id=\"ssd1\" name =\"ssd1\" placeholder=\"930318\">&nbsp;-&nbsp;\r\n");
      out.write("\t\t\t\t\t<input type=\"text\" id=\"ssd2\" name=\"ssd2\" placeholder=\"1\" >\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td class=\"la\">전화번호 :</td>\r\n");
      out.write("\t\t\t\t<td><input type=\"text\" id=\"phone\" name=\"phone\" placeholder=\"'-'없이 입력\"></td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td class=\"la\">주소 :</td>\r\n");
      out.write("\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t<input type=\"checkbox\" name=\"self-address\" id=\"self-address\">\r\n");
      out.write("\t\t\t\t\t<label for=\"self-address\" id=\"self-address-label\">직접입력</label><br>\r\n");
      out.write("\t\t\t\t\t<div id=\"self-address-input\">\r\n");
      out.write("\t\t\t\t\t\t<input type=\"text\" name=\"self-address-input\" id=\"self-address-input\" placeholder=\"○○시 ○○구\">\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t<div id=\"address\">\r\n");
      out.write("\t\t\t\t\t<select name=\"address-city\" id=\"address-city\">\r\n");
      out.write("\t\t\t\t\t\t<option value=\"-\" selected disabled>--시 선택--</option>\r\n");
      out.write("\t\t\t\t\t\t<option value=\"서울시\">서울</option>\r\n");
      out.write("\t\t\t\t\t</select>\r\n");
      out.write("\t\t\t\t\t<select name=\"address-local\" id=\"address-local\">\r\n");
      out.write("\t\t\t\t\t\t<option value=\"-\" selected disabled>--구 선택--</option>\r\n");
      out.write("\t\t\t\t\t\t<option value=\"종로구\">종로구</option>\r\n");
      out.write("\t\t\t\t\t\t<option value=\"중구\">중구</option>\r\n");
      out.write("\t\t\t\t\t\t<option value=\"용산구\">용산구</option>\r\n");
      out.write("\t\t\t\t\t\t<option value=\"성동구\">성동구</option>\r\n");
      out.write("\t\t\t\t\t\t<option value=\"광진구\">광진구</option>\r\n");
      out.write("\t\t\t\t\t\t<option value=\"동대문구\">동대문구</option>\r\n");
      out.write("\t\t\t\t\t\t<option value=\"중랑구\">중랑구</option>\r\n");
      out.write("\t\t\t\t\t\t<option value=\"성북구\">성북구</option>\r\n");
      out.write("\t\t\t\t\t\t<option value=\"강북구\">강북구</option>\r\n");
      out.write("\t\t\t\t\t\t<option value=\"도봉구\">도봉구</option>\r\n");
      out.write("\t\t\t\t\t\t<option value=\"노원구\">노원구</option>\r\n");
      out.write("\t\t\t\t\t\t<option value=\"은평구\">은평구</option>\r\n");
      out.write("\t\t\t\t\t\t<option value=\"서대문구\">서대문구</option>\r\n");
      out.write("\t\t\t\t\t\t<option value=\"마포구\">마포구</option>\r\n");
      out.write("\t\t\t\t\t\t<option value=\"양천구\">양천구</option>\r\n");
      out.write("\t\t\t\t\t\t<option value=\"강서구\">강서구</option>\r\n");
      out.write("\t\t\t\t\t\t<option value=\"구로구\">구로구</option>\r\n");
      out.write("\t\t\t\t\t\t<option value=\"금천구\">금천구</option>\r\n");
      out.write("\t\t\t\t\t\t<option value=\"영등포구\">영등포구</option>\r\n");
      out.write("\t\t\t\t\t\t<option value=\"동작구\">동작구</option>\r\n");
      out.write("\t\t\t\t\t\t<option value=\"관악구\">관악구</option>\r\n");
      out.write("\t\t\t\t\t\t<option value=\"서초구\">서초구</option>\r\n");
      out.write("\t\t\t\t\t\t<option value=\"강남구\">강남구</option>\r\n");
      out.write("\t\t\t\t\t\t<option value=\"송파구\">송파구</option>\r\n");
      out.write("\t\t\t\t\t\t<option value=\"강동구\">강동구</option>\r\n");
      out.write("\t\t\t\t\t</select>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td colspan=\"2\">\r\n");
      out.write("\t\t\t\t\t<input type=\"submit\" value=\"회원가입\"> &nbsp;\r\n");
      out.write("\t\t\t\t\t<input type=\"reset\" value=\"초기화\">\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t</table>\r\n");
      out.write("\t</form>\r\n");
      out.write("\t\r\n");
      out.write("</div>\r\n");
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
