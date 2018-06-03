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
@WebServlet(name="FindPwEndServlet", urlPatterns="/common/findPwEnd")
public class FindPwEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindPwEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String findType = request.getParameter("findType");
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		
		int result = 0;
		
		if(findType.equals("patient")) {
			try {
				result = new PatientService().findPw(userId, password);
			} catch (PatientException e) {
				e.printStackTrace();
				throw new ServletException("환자 비밀번호찾기 오류");
			}
		} else if(findType.equals("doctor")) {
			try {
				result = new DoctorService().findPw(userId, password);
			} catch (DoctorException e) {
				e.printStackTrace();
				throw new ServletException("의사 비밀번호찾기 오류");
			}
		} else if(findType.equals("admin")) {
			try {
				result = new AdminService().findPw(userId, password);
			} catch (AdminException e) {
				e.printStackTrace();
				throw new ServletException("관리자 비밀번호찾기 오류");
			}
		}
		
		if(result > 0) {
			request.setAttribute("msg", "성공");
		} else {
			request.setAttribute("msg", "실패");
		}
		request.getRequestDispatcher("/WEB-INF/views/common/findPwEnd.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
