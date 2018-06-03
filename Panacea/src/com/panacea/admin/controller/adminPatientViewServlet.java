package com.panacea.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.panacea.admin.model.exception.AdminException;
import com.panacea.admin.model.service.AdminService;
import com.panacea.patient.model.vo.Patient;

/**
 * Servlet implementation class adminPatientViewServlet
 */
@WebServlet("/admin/adminPatientView")
public class adminPatientViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public adminPatientViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pid = request.getParameter("pid");
		
		Patient p = null;
		try {
			p = new AdminService().selectPatient(pid);
		} catch (AdminException e) {
			e.printStackTrace();
			throw new ServletException("관리자 오류");
		}
		
		request.setAttribute("patient", p);
		request.setAttribute("selectedMenu", "mypage");
		request.setAttribute("selectedSubMenu", "patientList");
		request.getRequestDispatcher("/WEB-INF/views/admin/adminPatientView.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
