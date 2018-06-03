package com.panacea.review.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.panacea.review.model.exception.ReviewException;
import com.panacea.review.model.service.ReviewService;
import com.panacea.review.model.vo.Review;

/**
 * Servlet implementation class reviewList
 */
@WebServlet("/review/reviewList")
public class ReviewListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewListServlet() {
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
			  
		//2.업무로직처리
		   //2.1 현재페이지의 회원구하기
			 List<Review> list = null;
			try {
				list = new ReviewService().selectReviewList(cPage, numPerPage);
			} catch (ReviewException e) {
				e.printStackTrace();
				throw new ServletException("후기 오류");
			}
		     System.out.println("list@BoardListServlet="+list);
		     
		     int totalReviewCount = 0;
			try {
				totalReviewCount = new ReviewService().selectReviewCount();
			} catch (ReviewException e) {
				e.printStackTrace();
				throw new ServletException("후기 오류");
			}
				//(공식2)전체페이지수 구하기
				int totalPage = (int)Math.ceil((double)totalReviewCount/numPerPage);
				System.out.println("totalBoardCount="+totalReviewCount+", totalPage="+totalPage);
		     
		    String pageBar = "";	
			int pageBarSize = 5;
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
				pageBar += "<div class = 'prevBtn'><a href='"+request.getContextPath()+"/review/reviewList?cPage="+(pageNo-1)+"'> < </a></div> ";
			}
					
				// pageNo section
				// 보통 !(빠져나가는 조건식)으로 많이 쓴다.
//				while(pageNo<=pageEnd && pageNo<=totalPage){
			while(!(pageNo>pageEnd || pageNo > totalPage)){
					
				if(cPage == pageNo ){
					pageBar += "<span class='cPage'>"+pageNo+"</span> ";
				} 
				else {
					pageBar += "<a href='"+request.getContextPath()+"/review/reviewList?cPage="+pageNo+"'>"+pageNo+"</a> ";
				}
					pageNo++;
				}
				
				//[다음] section
				if(pageNo > totalPage){
					//pageBar += "<span>[다음]</span>";
				} else {
					pageBar += "<a href='"+request.getContextPath()+"/review/reviewList?cPage="+pageNo+"'> > </a>";
				}
			
				RequestDispatcher reqDispatcher = request.getRequestDispatcher("/WEB-INF/views/review/reviewList.jsp");
				request.setAttribute("list",list);
				request.setAttribute("pageBar",pageBar);	
				//request.setAttribute("cPage",cPage);		
				reqDispatcher.forward(request, response);
		     
		   
	  
	  
	  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
