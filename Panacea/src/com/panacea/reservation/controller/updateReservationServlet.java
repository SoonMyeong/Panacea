package com.panacea.reservation.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.panacea.reservation.model.exception.ReservationException;
import com.panacea.reservation.model.service.ReservationService;

/**
 * Servlet implementation class updateReservationServlet
 */
@WebServlet("/reservation/updateReservation")
public class updateReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateReservationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int rno = Integer.parseInt(request.getParameter("rno"));
		String date = request.getParameter("date");
		String time = request.getParameter("time");
		String userId = request.getParameter("userId");
		
		int result=0;
		try {
			result= new ReservationService().updateReservation(rno,date+time);
		}catch(ReservationException e) {
			e.printStackTrace();
			throw new ServletException("예약시간변경 오류");
		}
		
		
		String view="";
		String loc="";
		String msg="";
		
		if(result>0) {
			view = "/WEB-INF/views/common/msg.jsp";
			msg = "예약시간 변경이 완료되었습니다.";
			loc = "/reservation/patientReservationList?userId="+userId;
		}else {
			view = "/WEB-INF/views/common/msg.jsp";
			msg = "개인정보 변경 중 에러가 발생했습니다. 다시 시도해주세요.";
			loc = "/reservation/patientReservationList?userId="+userId;
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
