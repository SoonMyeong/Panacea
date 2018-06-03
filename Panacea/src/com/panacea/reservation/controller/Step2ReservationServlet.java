package com.panacea.reservation.controller;

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
 * Servlet implementation class Step2ReservationServlet
 */
@WebServlet("/reservation/step2Reservation")
public class Step2ReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Step2ReservationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String partId = request.getParameter("selectedPart");
		
		MedicalPart part = null;
		try {
			part = new PartService().selectPart(partId);
		} catch (PartException e) {
			e.printStackTrace();
			throw new ServletException("진료 예약 - 파트 검색 오류");
		}
		ArrayList<Doctor> partDoctors = null;
		try {
			partDoctors = new DoctorService().selectPartDoctors(partId);
		} catch (DoctorException e) {
			e.printStackTrace();
			throw new ServletException("진료 예약 - 의사 검색 오류");
		}
		
		request.setAttribute("partName", part.getPartName());
		request.setAttribute("partDoctors", partDoctors);
		request.setAttribute("selectedMenu", "reservation");
		request.setAttribute("selectedSubMenu", "step2");
		request.getRequestDispatcher("/WEB-INF/views/reservation/reservation_step2.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
