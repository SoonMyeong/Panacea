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
 * Servlet implementation class DoctorUpdateChartView
 */
@WebServlet("/chart/doctorUpdateChartView")
public class DoctorUpdateChartView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoctorUpdateChartView() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int cno = Integer.parseInt(request.getParameter("cno"));
		System.out.println("cno="+cno);
		Chart c = null;
		try {
			c = new ChartService().selectChart(cno);
		} catch (ChartException e) {
			e.printStackTrace();
			throw new ServletException("차트 오류");
		}
		
		System.out.println("chart@updateChartView="+c);
		request.setAttribute("selectedMenu", "mypage");
		request.setAttribute("selectedSubMenu", "chartList");
		request.setAttribute("chart", c);
		request.getRequestDispatcher("/WEB-INF/views/chart/doctorUpdateChartView.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
