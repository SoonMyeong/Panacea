package com.panacea.common.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.panacea.admin.model.exception.AdminException;
import com.panacea.admin.model.service.AdminService;
import com.panacea.admin.model.vo.Admin;
import com.panacea.doctor.model.exception.DoctorException;
import com.panacea.doctor.model.service.DoctorService;
import com.panacea.doctor.model.vo.Doctor;
import com.panacea.patient.model.exception.PatientException;
import com.panacea.patient.model.service.PatientService;
import com.panacea.patient.model.vo.Patient;

/**
 * Servlet implementation class loginEndServlet
 */
@WebServlet(name = "LoginEndServlet", urlPatterns="/common/loginEnd")
public class LoginEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String loginType = request.getParameter("loginType");
		String userId = request.getParameter("userId");
		String userPw = request.getParameter("userPw");
		String saveId = request.getParameter("saveId");
		int result = 0;
		
		if(saveId != null) {
				
			Cookie c_id = new Cookie("saveId",userId);
			Cookie c_type = new Cookie("saveType",loginType);
			c_id.setMaxAge(7*60*60*24);
			c_type.setMaxAge(7*60*60*24);
			c_id.setPath("/");
			c_type.setPath("/");
			response.addCookie(c_id);
			response.addCookie(c_type);
			
		} else {
			
			Cookie del_c_id = new Cookie("saveId",userId);
			Cookie del_c_type = new Cookie("saveType",loginType);
			del_c_id.setMaxAge(0);
			del_c_type.setMaxAge(0);
			del_c_id.setPath("/");
			del_c_type.setPath("/");
			response.addCookie(del_c_id);
			response.addCookie(del_c_type);
		}
		
		String view = "";
		String msg = "";
		String loc = "";
		
//		String Referer = request.getHeader("Referer");
//		String Origin = request.getHeader("Origin");
//		String url = request.getRequestURL().toString();
//		String uri = request.getRequestURI();
//		
//		if(Origin == null) {
//			Origin = url.replace(uri, "");
//		}
//		
//		loc = Referer.replace(Origin+request.getContextPath(),"");
		
		HttpSession session = request.getSession();
		if("patient".equals(loginType)) {
			
			try {
				result = new PatientService().loginPatient(userId,userPw);
			} catch (PatientException e) {
				e.printStackTrace();
				throw new ServletException("환자 로그인 오류");
			}
			
			if(result > 0) {
				Patient p = null;
				try {
					p = new PatientService().selectPatient(userId);
				} catch (PatientException e) {
					e.printStackTrace();
					throw new ServletException("환자 로그인 오류");
				}
				session.setAttribute("loginPatient", p);
			}
			
		} else if("doctor".equals(loginType)) {
			
			try {
				result = new DoctorService().loginDoctor(userId,userPw);
			} catch (DoctorException e) {
				e.printStackTrace();
				throw new ServletException("의사 로그인 오류");
			}

			if(result > 0) {
				Doctor d = null;
				try {
					d = new DoctorService().selectDoctor(userId);
				} catch (DoctorException e) {
					e.printStackTrace();
					throw new ServletException("의사 로그인 오류");
				}
				session.setAttribute("loginDoctor", d);
			}
			
		} else if("admin".equals(loginType)) {
			
			try {
				result = new AdminService().loginAdmin(userId,userPw);
			} catch (AdminException e) {
				e.printStackTrace();
				throw new ServletException("관리자 로그인 오류");
			}
			
			if(result > 0) {
				Admin a = null;
				try {
					a = new AdminService().selectAdmin(userId);
				} catch (AdminException e) {
					e.printStackTrace();
					throw new ServletException("관리자 로그인 오류");
				}
				session.setAttribute("loginAdmin", a);
			}
		}
		
		if(result > 0) {
			session.setAttribute("loginType", loginType);
			session.setMaxInactiveInterval(60*60);
			response.sendRedirect(request.getContextPath() + loc);
			
		} else {
			view = "/WEB-INF/views/common/msg.jsp";
			msg = "존재하지 않는 아이디이거나 비밀번호를 잘못 입력하셨습니다.";
			loc = "/common/login";
			
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			request.getRequestDispatcher(view).forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
