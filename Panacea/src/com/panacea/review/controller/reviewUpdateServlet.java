package com.panacea.review.controller;

import java.io.IOException;

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
 * Servlet implementation class reviewUpdateServlet
 */
@WebServlet("/review/reviewUpdate")
public class reviewUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public reviewUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int reviewNo = Integer.parseInt(request.getParameter("no"));
		System.out.println(reviewNo);
		Review review = null;
		try {
			review = new ReviewService().selectReviewView(reviewNo);
		} catch (ReviewException e) {
			e.printStackTrace();
			throw new ServletException("후기 오류");
		}
		
		//3. view단 처리위임
		String view = "";
		
		if(review != null) {
			request.setAttribute("review", review);
			view = "/WEB-INF/views/review/reviewUpdateForm.jsp";
		}
		else {
			request.setAttribute("msg", "조회한 게시글이 존재하지 않습니다.");
			request.setAttribute("loc", "/review/reviewList");
			view = "/WEB-INF/views/common/msg.jsp";
		}
		RequestDispatcher reqDispatcher 
			= request.getRequestDispatcher(view);	
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
