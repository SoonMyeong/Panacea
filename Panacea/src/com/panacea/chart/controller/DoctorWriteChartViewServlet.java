package com.panacea.chart.controller;

import java.io.IOException;
import java.util.List;

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
@WebServlet("/chart/doctorWriteChartView")
public class DoctorWriteChartViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoctorWriteChartViewServlet() {
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
			throw new ServletException("차트 오류");
		}
		
		request.setAttribute("selectedMenu", "mypage");
		request.setAttribute("selectedSubMenu", "reservationList");
		request.setAttribute("reservation", r);
		request.getRequestDispatcher("/WEB-INF/views/chart/doctorWriteChatView.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
