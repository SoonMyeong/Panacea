package com.panacea.review.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.panacea.review.model.exception.ReviewException;
import com.panacea.review.model.service.ReviewService;
import com.panacea.review.model.vo.Review;

/**
 * Servlet implementation class ReviewUpdateEndServlet
 */
@WebServlet("/review/reviewUpdateEnd")
public class ReviewUpdateEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewUpdateEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int reviewNo = Integer.parseInt(request.getParameter("reviewNo"));
		String reviewTitle = request.getParameter("title");
		String reviewWriter = request.getParameter("writer");
		int grade = Integer.parseInt(request.getParameter("grade"));
		String reviewContent = request.getParameter("content");
		
		Review review = new Review();
		review.setReviewNo(reviewNo);
		review.setReviewTitle(reviewTitle);
		review.setPatientId(reviewWriter);
		review.setGrade(grade);
		review.setReviewContent(reviewContent);
		
		int result = 0;
		try {
			result = new ReviewService().updateReview(review);
		} catch (ReviewException e) {
			e.printStackTrace();
			throw new ServletException("후기 오류");
		}
		String view = "/WEB-INF/views/common/msg.jsp";
		String msg = "";
		String loc = "";
		if(result >0) {
			msg = "후기게시판 수정 성공!";
			loc = "/review/reviewView?reviewNo="+reviewNo;
		}else {
			msg="후기게시판 수정 실패!";
			loc = "/";
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
