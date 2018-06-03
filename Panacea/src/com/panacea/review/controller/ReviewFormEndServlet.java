package com.panacea.review.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.panacea.reservation.model.exception.ReservationException;
import com.panacea.reservation.model.service.ReservationService;
import com.panacea.review.model.exception.ReviewException;
import com.panacea.review.model.service.ReviewService;
import com.panacea.review.model.vo.Review;

/**
 * Servlet implementation class ReviewFormEndServlet
 */
@WebServlet("/review/reviewFormEnd")
public class ReviewFormEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewFormEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String reviewTitle = request.getParameter("title");
		int reviewGrade = Integer.parseInt(request.getParameter("grade"));
		String reviewWriter = request.getParameter("writer");
		String reviewContent = request.getParameter("content");
		
		int rno = Integer.parseInt(request.getParameter("rno"));
		
		
		System.out.println("title : "+reviewTitle);
		System.out.println("grade : "+reviewGrade);
		System.out.println("writer : "+reviewWriter);
		System.out.println("content : "+reviewContent);
		
		Review review = new Review();
		review.setReviewTitle(reviewTitle);
		review.setPatientId(reviewWriter);
		review.setGrade(reviewGrade);
		review.setReviewContent(reviewContent);
		
		
		int result = 0;
		int result2= 0;
		try {
			result = new ReviewService().insertReview(review);
		} catch (ReviewException e) {
			e.printStackTrace();
			throw new ServletException("후기 오류");
		}
		
		try {
			result2= new ReservationService().deleteReservation(rno);
		}catch(ReservationException e) {
			e.printStackTrace();
			throw new ServletException("리뷰등록 후 예약목록 삭제 오류");
		}
		
		String view = "/WEB-INF/views/common/msg.jsp";
		String msg = "";
		String loc = "";
		if(result>0 && result2>0) {
			msg = "후기등록 성공 후기 게시판으로 이동합니다";
			loc = "/review/reviewList";
			
		}else {
			msg="후기등록 실패";
			loc="/review/reviewForm";
		}
		request.setAttribute("loc", loc);
		request.setAttribute("msg", msg);
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
