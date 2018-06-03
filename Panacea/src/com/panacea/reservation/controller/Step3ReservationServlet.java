package com.panacea.reservation.controller;

import java.io.IOException;
import java.util.Calendar;

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
 * Servlet implementation class Step3ReservationServlet
 */
@WebServlet("/reservation/step3Reservation")
public class Step3ReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Step3ReservationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String partId = request.getParameter("partId");
		String doctorId = request.getParameter("doctorId");

		MedicalPart part = null;
		try {
			part = new PartService().selectPart(partId);
		} catch (PartException e) {
			e.printStackTrace();
			throw new ServletException("진료 예약 - 파트 검색 오류");
		}
		Doctor doctor = null;
		try {
			doctor = new DoctorService().selectDoctorbyId(doctorId);
		} catch (DoctorException e) {
			e.printStackTrace();
			throw new ServletException("진료 예약 - 의사 검색 오류");
		}
		
		request.setAttribute("part", part);
		request.setAttribute("doctor", doctor);
		
		request.setAttribute("selectedMenu", "reservation");
		request.setAttribute("selectedSubMenu", "step3");
		request.getRequestDispatcher("/WEB-INF/views/reservation/reservation_step3.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
