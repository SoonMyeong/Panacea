package com.panacea.common.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.panacea.admin.model.exception.AdminException;
import com.panacea.admin.model.service.AdminService;
import com.panacea.doctor.model.exception.DoctorException;
import com.panacea.doctor.model.service.DoctorService;
import com.panacea.patient.model.exception.PatientException;
import com.panacea.patient.model.service.PatientService;

/**
 * Servlet implementation class FindIdEndServlet
 */
@WebServlet("/common/findIdEnd")
public class FindIdEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindIdEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String findType = request.getParameter("findType");
		String userName = request.getParameter("userName");
		String phone = request.getParameter("phone");
		
		String findId = "";
		
		if(findType.equals("patient")) {
			try {
				findId = new PatientService().findId(userName, phone);
			} catch (PatientException e) {
				e.printStackTrace();
				throw new ServletException("환자 아이디찾기 오류");
			}
		} else if(findType.equals("doctor")) {
			try {
				findId = new DoctorService().findId(userName, phone);
			} catch (DoctorException e) {
				e.printStackTrace();
				throw new ServletException("의사 아이디찾기 오류");
			}
		} else if(findType.equals("admin")) {
			try {
				findId = new AdminService().findId(userName, phone);
			} catch (AdminException e) {
				e.printStackTrace();
				throw new ServletException("관리자 아이디찾기 오류");
				
			}
		}
		
		request.setAttribute("findId", findId);
		request.getRequestDispatcher("/WEB-INF/views/common/findIdEnd.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
