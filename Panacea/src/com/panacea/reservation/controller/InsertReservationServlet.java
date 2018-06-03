package com.panacea.reservation.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.panacea.patient.model.vo.Patient;
import com.panacea.reservation.model.exception.ReservationException;
import com.panacea.reservation.model.service.ReservationService;

/**
 * Servlet implementation class InsertReservationServlet
 */
@WebServlet("/reservation/insertReservation")
public class InsertReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertReservationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String partId = request.getParameter("partId");
		String doctorId = request.getParameter("doctorId");
		String date = request.getParameter("date");
		String time = request.getParameter("time");
		
		HttpSession session = request.getSession(false);
		Patient patient = (Patient)session.getAttribute("loginPatient");
		String patientId = patient.getPatientId();
		
		int result = 0;
		try {
			result = new ReservationService().insertReservation(patientId, partId, doctorId, date+time);
		} catch (ReservationException e) {
			e.printStackTrace();
			throw new ServletException("진료 예약 오류");
		}
		
		String view = "/WEB-INF/views/common/msg.jsp";
		String loc = "";
		String msg = "";
		
		if(result > 0) {
			loc = "/";
			msg = "예약 되셨습니다.";
		} else {
			loc = "/reservation/step1Reservation";
			msg = "예약이 실패하였습니다. 다시 예약해주세요.";
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher(view).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
