package com.panacea.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.panacea.admin.model.exception.AdminException;
import com.panacea.admin.model.service.AdminService;
import com.panacea.doctor.model.vo.Doctor;

/**
 * Servlet implementation class adminDoctorViewServlet
 */
@WebServlet("/admin/adminDoctorView")
public class adminDoctorViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public adminDoctorViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String did = request.getParameter("did");
		
		Doctor d = null;
		try {
			d = new AdminService().selectDoctor(did);
		} catch (AdminException e) {
			e.printStackTrace();
			throw new ServletException("관리자 오류");
		}
		
		request.setAttribute("doctor", d);
		request.setAttribute("selectedMenu", "mypage");
		request.setAttribute("selectedSubMenu", "doctorList");
		request.getRequestDispatcher("/WEB-INF/views/admin/adminDoctorView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
