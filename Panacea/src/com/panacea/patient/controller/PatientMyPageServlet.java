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
 * Servlet implementation class PatientMyPageServlet
 */
@WebServlet("/patient/patientMypage")
public class PatientMyPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PatientMyPageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String userId = request.getParameter("userId");
		
		Patient p = null;
		try {
			p = new PatientService().selectPatient(userId);
		} catch (PatientException e) {
			e.printStackTrace();
			throw new ServletException("환자 오류");
		}
		
		request.setAttribute("patient", p);
		request.setAttribute("selectedMenu", "mypage");
		request.setAttribute("selectedSubMenu", "updateView");
		request.getRequestDispatcher("/WEB-INF/views/patient/patientView.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
