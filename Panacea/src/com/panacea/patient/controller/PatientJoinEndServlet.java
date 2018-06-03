package com.panacea.patient.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.panacea.patient.model.exception.PatientException;
import com.panacea.patient.model.service.PatientService;
import com.panacea.patient.model.vo.Patient;

/**
 * Servlet implementation class JoinEndServlet
 */
@WebServlet(name="PatientJoinEndServlet", urlPatterns="/patient/joinEnd")
public class PatientJoinEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PatientJoinEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userId = request.getParameter("userId");
		String userPw = request.getParameter("userPw");
		String userName = request.getParameter("userName");
		String ssd1 = request.getParameter("ssd1");
		String ssd2 = request.getParameter("ssd2");
		String ssd = ssd1 + "-" + ssd2;
		String phone = request.getParameter("phone");
		String isSelfAddress = request.getParameter("self-address");
		
		String address = "";
		if(isSelfAddress != null && isSelfAddress.equals("on")) {
			address = request.getParameter("self-address-input");
		} else {
			String address1 = request.getParameter("address-city");
			String address2 = request.getParameter("address-local");
			address = address1 + " " + address2;
		}
		
		int result =0;
		
		Patient p = new Patient(userId,userPw,userName,ssd,phone,address);
		try {
			result = new PatientService().insertPatient(p);
		} catch (PatientException e) {
			e.printStackTrace();
			throw new ServletException("환자 회원가입 오류");
		}
		
		String view="";
		String loc="";
		String msg="";
		
		if(result>0) {
			view = "/WEB-INF/views/common/msg.jsp";
			msg = "가입을 환영합니다. 로그인 해주세요.";
			loc = "/common/login";
			
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			request.getRequestDispatcher(view).forward(request, response);
		}else {
			view = "/WEB-INF/views/common/msg.jsp";
			msg = "회원가입 도중 에러가 발생했습니다. 다시 시도해주세요.";
			loc = "/patient/join";
			
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
