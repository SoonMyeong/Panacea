package com.panacea.part.controller;

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
import com.panacea.part.model.exception.PartException;
import com.panacea.part.model.service.PartService;
import com.panacea.part.model.vo.MedicalPart;

/**
 * Servlet implementation class PartDoctorViewServlet
 */
@WebServlet("/part/partDoctorView")
public class PartDoctorViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PartDoctorViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String partId = request.getParameter("partId");
		
		MedicalPart part = null;
		try {
			part = new PartService().selectPart(partId);
		} catch (PartException e) {
			e.printStackTrace();
			throw new ServletException("파트 검색 오류");
		}
		ArrayList<Doctor> partDoctors = null;
		try {
			partDoctors = new DoctorService().selectPartDoctors(partId);
		} catch (DoctorException e) {
			e.printStackTrace();
			throw new ServletException("의사 검색 오류");
		}
		
		request.setAttribute("part", part);
		request.setAttribute("partDoctors", partDoctors);
		request.setAttribute("selectedMenu", "info");
		request.setAttribute("selectedSubMenu", "part-doctor");
		request.getRequestDispatcher("/WEB-INF/views/info/part_doctor_view.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
