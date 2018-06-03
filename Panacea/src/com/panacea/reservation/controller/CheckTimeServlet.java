package com.panacea.reservation.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.panacea.reservation.model.exception.ReservationException;
import com.panacea.reservation.model.service.ReservationService;

/**
 * Servlet implementation class CheckTimeServlet
 */
@WebServlet("/reservation/checkTime")
public class CheckTimeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckTimeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String patientId = request.getParameter("patientId");
		String doctorId = request.getParameter("doctorId");
		String date = request.getParameter("date");
		System.out.println(date);
		
		ArrayList<String> time_list = null;
		try {
			time_list = new ReservationService().checkTime(patientId, doctorId, date);
		} catch (ReservationException e) {
			e.printStackTrace();
			throw new ServletException("진료 예약 오류");
		}
		
		response.setContentType("application/json; charset=utf-8");
		  
		new Gson().toJson(time_list,response.getWriter());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
