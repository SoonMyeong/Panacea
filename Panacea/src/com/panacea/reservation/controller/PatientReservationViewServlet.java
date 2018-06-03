package com.panacea.reservation.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.panacea.reservation.model.exception.ReservationException;
import com.panacea.reservation.model.service.ReservationService;
import com.panacea.reservation.model.vo.Reservation;

/**
 * Servlet implementation class PatientReservationViewServlet
 */
@WebServlet("/reservation/patientReservationView")
public class PatientReservationViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PatientReservationViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int rno = Integer.parseInt(request.getParameter("rno"));
		
		Reservation r = null;
		try {
			r = new ReservationService().selectReservation(rno);
		} catch (ReservationException e) {
			e.printStackTrace();
			throw new ServletException("진료 예약 오류");
		}
		
		
		request.setAttribute("reservation", r);
		request.getRequestDispatcher("/WEB-INF/views/reservation/patientReservationView.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
