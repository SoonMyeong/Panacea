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

/**
 * Servlet implementation class DoctorUpdateEndChartView
 */
@WebServlet("/chart/updateEndChart")
public class DoctorUpdateEndChartView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoctorUpdateEndChartView() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int cno = Integer.parseInt(request.getParameter("cno"));
		String userId = request.getParameter("userId");
		String diseaseName = request.getParameter("diseaseName");
		String diseaseComment = request.getParameter("diseaseComment");
		
		Chart c = new Chart();
		c.setDoctor_id(userId);
		c.setChart_no(cno);
		c.setDisease_name(diseaseName);
		c.setChart_comment(diseaseComment);
		
		int result = 0;
		try {
			result = new ChartService().updateChart(c);
		} catch (ChartException e) {
			e.printStackTrace();
			throw new ServletException("차트 오류");
		}
		System.out.println("result@DoctorUpdateEndServlet"+result);
		
		String view="";
		String loc="";
		String msg="";
		
		if(result>0) {
			view = "/WEB-INF/views/common/msg.jsp";
			msg = "수정이 완료되었습니다.";
			loc = "/chart/doctorChartList?userId="+userId;
			
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			request.getRequestDispatcher(view).forward(request, response);
		}else {
			view = "/WEB-INF/views/common/msg.jsp";
			msg = "수정 에러 발생! 다시 시도하세요.";
			loc = "/chart/doctorChartList?userId="+userId;
			
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
