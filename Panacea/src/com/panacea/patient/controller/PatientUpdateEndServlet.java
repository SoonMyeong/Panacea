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
 * Servlet implementation class PatientUpdateEndServlet
 */
@WebServlet("/patient/updateEnd")
public class PatientUpdateEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PatientUpdateEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userId = request.getParameter("userId");
		String userName = request.getParameter("userName");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		int result =0;
		
		Patient p = new Patient(userId,userName,phone,address);
		
		try {
			result = new PatientService().updatePatient(p);
		} catch (PatientException e) {
			e.printStackTrace();
			throw new ServletException("환자 오류");
		}
		
		System.out.println("result@PatientUpdateServlet="+result);
		
		String view="";
		String loc="";
		String msg="";
		
		if(result>0) {
			view = "/WEB-INF/views/common/msg.jsp";
			msg = "개인정보 변경이 완료되었습니다.";
			loc = "/";
			
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			request.getRequestDispatcher(view).forward(request, response);
		}else {
			view = "/WEB-INF/views/common/msg.jsp";
			msg = "개인정보 변경 중 에러가 발생했습니다. 다시 시도해주세요.";
			loc = "/";
			
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
