package com.panacea.chart.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.panacea.chart.model.exception.ChartException;
import com.panacea.chart.model.service.ChartService;
import com.panacea.chart.model.vo.Chart;
import com.panacea.reservation.model.exception.ReservationException;
import com.panacea.reservation.model.service.ReservationService;

/**
 * Servlet implementation class DoctorInsertChartServlet
 */
@WebServlet("/chart/insertEndChart")
public class DoctorInsertChartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoctorInsertChartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int rno = Integer.parseInt(request.getParameter("rNo"));
		String rname = request.getParameter("rName"); //환자이름
		String rssd = request.getParameter("rSsd"); //환자 주민번호
		String diseaseName= request.getParameter("diseaseName"); //진단명
		String disaeseComment = request.getParameter("diseaseComment");//진단내용
		
		String doctorId = request.getParameter("doctorId");
		String patientId = request.getParameter("patientId");
		
		Chart c = new Chart(doctorId,patientId,diseaseName,disaeseComment);
		
		int result = 0;
		try {
			result = new ChartService().insertChart(c);
		} catch (ChartException e) {
			e.printStackTrace();
			throw new ServletException("차트 오류");
		}
		
		String view="";
		String loc="";
		String msg="";
		
		if(result>0) {
			view = "/WEB-INF/views/common/msg.jsp";
			msg = "차트 작성완료!";
			loc = "/chart/doctorChartList?userId="+doctorId;

			int delete = 0;
			try {
				delete = new ReservationService().deleteReservation(rno);
			} catch (ReservationException e) {
				e.printStackTrace();
				throw new ServletException("차트 오류");
			}
			
			if(delete>0) {
				request.setAttribute("msg", msg);
				request.setAttribute("loc", loc);
				request.getRequestDispatcher(view).forward(request, response);
			}else {
				view = "/WEB-INF/views/common/msg.jsp";
				msg = "예약 정리 도중 에러가 발생했습니다. 다시 시도해주세요.";
				loc = "/WEB-INF/views/reservation/doctorReservationList?userId="+rno+".jsp";
				
				request.setAttribute("msg", msg);
				request.setAttribute("loc", loc);
				request.getRequestDispatcher(view).forward(request, response);
			}
		}else {
			view = "/WEB-INF/views/common/msg.jsp";
			msg = "차트 작성 도중 에러가 발생했습니다. 다시 시도해주세요.";
			loc = "/WEB-INF/views/reservation/doctorReservationList?userId="+rno+".jsp";
			
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			request.getRequestDispatcher(view).forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
