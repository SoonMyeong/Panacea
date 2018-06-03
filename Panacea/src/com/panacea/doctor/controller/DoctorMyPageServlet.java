package com.panacea.doctor.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.panacea.doctor.model.exception.DoctorException;
import com.panacea.doctor.model.service.DoctorService;
import com.panacea.doctor.model.vo.Doctor;

/**
 * Servlet implementation class PatientMyPageServlet
 */
@WebServlet("/doctor/doctorMypage")
public class DoctorMyPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoctorMyPageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String userId = request.getParameter("userId");
		
		Doctor d = null;
		try {
			d = new DoctorService().selectDoctor(userId);
		} catch (DoctorException e) {
			e.printStackTrace();
			throw new ServletException("의사 오류");
		}
		
		request.setAttribute("doctor", d);
		request.setAttribute("selectedMenu", "mypage");
		request.setAttribute("selectedSubMenu", "updateView");
		request.getRequestDispatcher("/WEB-INF/views/doctor/doctorView.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
