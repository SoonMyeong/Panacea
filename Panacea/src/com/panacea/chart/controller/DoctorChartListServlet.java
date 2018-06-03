package com.panacea.chart.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.panacea.chart.model.exception.ChartException;
import com.panacea.chart.model.service.ChartService;
import com.panacea.chart.model.vo.Chart;


/**
 * Servlet implementation class DoctorChartListServlet
 */
@WebServlet("/chart/doctorChartList")
public class DoctorChartListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoctorChartListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1. 파라미터값 변수에 담기
		int numPerPage = 10;//한페이지당 수
		int cPage;//요청페이지
		try{
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch(NumberFormatException e){
			cPage = 1;
		}		
		
		
		String userId = request.getParameter("userId");	
		List<Chart> list = new ArrayList<>();
		
		try {
			list = new ChartService().selectDoctor(userId,cPage,numPerPage);
		} catch (ChartException e) {
			e.printStackTrace();
			throw new ServletException("차트 오류");
		}	
		System.out.println("list@charListServlet="+list);
		
		
		int totalChartCount = 0;
		try {
			totalChartCount = new ChartService().doctorChartCount(userId);
		} catch (ChartException e) {
			e.printStackTrace();
			throw new ServletException("차트 오류");
		}
		int totalPage = (int)Math.ceil((double)totalChartCount/numPerPage);
		System.out.println("totalChartListCount="+totalChartCount+", totalPage="+totalPage);
		
		
		
		//2.3 페이지바구성
		String pageBar = "";	
		int pageBarSize = 10;
		//(공식3)시작페이지 번호 세팅
		//cPage=5,pageBarSize=5 -> 1
		//cPage=6,pageBarSize=5 -> 6
		int pageNo = ((cPage - 1)/pageBarSize) * pageBarSize +1;
		//종료페이지 번호 세팅
		int pageEnd = pageNo+pageBarSize-1;
		System.out.println("pageStart["+pageNo+"] ~ pageEnd["+pageEnd+"]");
		
		//[이전] section
		if(pageNo == 1 ){
			//pageBar += "<span>[이전]</span>"; 
		}
		else {
			pageBar += "<a href='"+request.getContextPath()+"/chart/doctorChartList?cPage="+(pageNo-1)+"&userId="+userId+"'>[이전]</a> ";
		}
			
		// pageNo section
		// 보통 !(빠져나가는 조건식)으로 많이 쓴다.
//		while(pageNo<=pageEnd && pageNo<=totalPage){
		while(!(pageNo>pageEnd || pageNo > totalPage)){
			
			if(cPage == pageNo ){
				pageBar += "<span class='cPage'>"+pageNo+"</span> ";
			} 
			else {
				pageBar += "<a href='"+request.getContextPath()+"/chart/doctorChartList?cPage="+pageNo+"&userId="+userId+"'>"+pageNo+"</a> ";
			}
			pageNo++;
		}
		
		//[다음] section
		if(pageNo > totalPage){
			//pageBar += "<span>[다음]</span>";
		} else {
			pageBar += "<a href='"+request.getContextPath()+"/chart/doctorChartList?cPage="+pageNo+"&userId="+userId+"'>[다음]</a>";
		}
		
			
		request.setAttribute("selectedMenu", "mypage");
		request.setAttribute("selectedSubMenu", "chartList");
		request.setAttribute("pageBar", pageBar);
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("/WEB-INF/views/chart/doctorChartList.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
