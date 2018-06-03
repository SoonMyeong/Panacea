package com.panacea.common.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.panacea.admin.model.exception.AdminException;
import com.panacea.admin.model.service.AdminService;
import com.panacea.doctor.model.exception.DoctorException;
import com.panacea.doctor.model.service.DoctorService;
import com.panacea.patient.model.exception.PatientException;
import com.panacea.patient.model.service.PatientService;

/**
 * Servlet implementation class FindPwCheckServlet
 */
@WebServlet("/common/findPwCheck")
public class FindPwCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindPwCheckServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String findType = request.getParameter("findType");
		String userId = request.getParameter("userId");
		String userName = request.getParameter("userName");
		String phone = request.getParameter("phone");
		
		int result = 0;
		
		if(findType.equals("patient")) {
			try {
				result = new PatientService().findCheck(userId, userName, phone);
			} catch (PatientException e) {
				e.printStackTrace();
				throw new ServletException("환자 비밀번호체크 오류");
			}
		} else if(findType.equals("doctor")) {
			try {
				result = new DoctorService().findCheck(userId, userName, phone);
			} catch (DoctorException e) {
				e.printStackTrace();
				throw new ServletException("의사 비밀번호체크 오류");
			}
		} else if(findType.equals("admin")) {
			try {
				result = new AdminService().findCheck(userId, userName, phone);
			} catch (AdminException e) {
				e.printStackTrace();
				throw new ServletException("관리자 비밀번호체크 오류");
			}
		}
		
		response.setContentType("application/json; charset=utf-8");
		  
		new Gson().toJson(result,response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
