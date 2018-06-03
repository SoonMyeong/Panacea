package com.panacea.patient.controller;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.panacea.patient.model.exception.PatientException;
import com.panacea.patient.model.service.PatientService;

/**
 * Servlet implementation class PatientDeleteEndServlet
 */
@WebServlet("/patient/deleteEnd")
public class PatientDeleteEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PatientDeleteEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		String userId = request.getParameter("id");
		
//		Enumeration enum_session = session.getAttributeNames();
//		String is_name ="";
//		String is_value="";
//		while(enum_session.hasMoreElements()) {
//			is_name=enum_session.nextElement().toString();
//			is_value=session.getAttribute(is_name).toString();
//			System.out.println("name@PatientDeleteEndServlet="+is_name);
//			System.out.println("value@PatientDeleteEndServlet="+is_value);
//		}
		
		
		if(session !=null) {
//			Patient p = (Patient)session.getAttribute("loginPatient");
			
			
			int result = 0;
			try {
				result = new PatientService().deletePatient(userId);
			} catch (PatientException e) {
				e.printStackTrace();
				throw new ServletException("환자 오류");
			}
			
			String view ="/WEB-INF/views/common/msg.jsp";
			String msg="";
			String loc="/";
			
			if(result>0) {
				msg="회원탈퇴 성공!";
				session.invalidate();
				
			}else {
				msg="회원 탈퇴 실패.. 관리자에게 문의해주세요.";
			}
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			
			RequestDispatcher reqDispatcher = request.getRequestDispatcher(view);
			reqDispatcher.forward(request, response);
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
