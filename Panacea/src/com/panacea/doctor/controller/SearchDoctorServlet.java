package com.panacea.doctor.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.panacea.doctor.model.exception.DoctorException;
import com.panacea.doctor.model.service.DoctorService;
import com.panacea.doctor.model.vo.Doctor;

/**
 * Servlet implementation class SearchDoctorServlet
 */
@WebServlet("/doctor/searchDoctor")
public class SearchDoctorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchDoctorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String doctorName = request.getParameter("doctorName");
		
		ArrayList<Doctor> searchDoctors = null;
		try {
			searchDoctors = new DoctorService().searchDoctorbyName(doctorName);
		} catch (DoctorException e) {
			e.printStackTrace();
			throw new ServletException("의사 검색 오류");
		}
		
		request.setAttribute("searchDoctorName", doctorName);
		request.setAttribute("searchDoctors", searchDoctors);
		request.setAttribute("selectedMenu", "info");
		request.setAttribute("selectedSubMenu", "part-doctor");
		request.getRequestDispatcher("/WEB-INF/views/info/doctor_view.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
