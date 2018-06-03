package com.panacea.review.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.panacea.chart.model.exception.ChartException;
import com.panacea.chart.model.service.ChartService;
import com.panacea.reservation.model.exception.ReservationException;
import com.panacea.reservation.model.service.ReservationService;
import com.panacea.reservation.model.vo.Reservation;

/**
 * Servlet implementation class ReviewFormListServlet
 */
@WebServlet("/review/reviewFormList")
public class ReviewFormListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewFormListServlet() {
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
			System.out.println("cPage="+cPage);
		} catch(NumberFormatException e){
			cPage = 1;
		}			
		
		String userId = request.getParameter("userId");
		List<Reservation> list = null;
		try {
			list = new ReservationService().patientSelectReservationReviewList(userId,cPage,numPerPage);
		} catch (ReservationException e) {
			e.printStackTrace();
			throw new ServletException("진료 예약 오류");
		}
		System.out.println("list@PatientReservationListServlet="+list);
		
		int totalReservationCount = 0;
		try {
			totalReservationCount = new ReservationService().patientReservationReviewCount(userId);
		} catch (ReservationException e) {
			e.printStackTrace();
			throw new ServletException("진료 예약 오류");
		}
		int totalPage = (int)Math.ceil((double)totalReservationCount/numPerPage);
		System.out.println("totalReservationCount="+totalReservationCount+", totalPage="+totalPage);
		
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
			pageBar += "<a href='"+request.getContextPath()+"/review/reviewFormList?cPage="+(pageNo-1)+"&userId="+userId+"'>[이전]</a> ";
		}
			
		// pageNo section
		// 보통 !(빠져나가는 조건식)으로 많이 쓴다.
//		while(pageNo<=pageEnd && pageNo<=totalPage){
		while(!(pageNo>pageEnd || pageNo > totalPage)){
			
			if(cPage == pageNo ){
				pageBar += "<span class='cPage'>"+pageNo+"</span> ";
			} 
			else {
				pageBar += "<a href='"+request.getContextPath()+"/review/reviewFormList?cPage="+pageNo+"&userId="+userId+"'>"+pageNo+"</a> ";
			}
			pageNo++;
		}
		
		//[다음] section
		if(pageNo > totalPage){
			//pageBar += "<span>[다음]</span>";
		} else {
			pageBar += "<a href='"+request.getContextPath()+"/review/reviewFormList?cPage="+pageNo+"&userId="+userId+"'>[다음]</a>";
		}
		
		request.setAttribute("list", list);
		request.setAttribute("pageBar", pageBar);
		request.getRequestDispatcher("/WEB-INF/views/review/reviewFormList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
