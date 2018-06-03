package com.panacea.review.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.panacea.review.model.exception.ReviewException;
import com.panacea.review.model.service.ReviewService;
import com.panacea.review.model.vo.ReviewComment;

/**
 * Servlet implementation class ReviewCommentDeleteServlet
 */
@WebServlet("/review/reviewCommentDelete")
public class ReviewCommentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewCommentDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 int commentNo = Integer.parseInt(request.getParameter("commentNo"));
	 int reviewNo = Integer.parseInt(request.getParameter("reviewNo"));
	 int cPage = Integer.parseInt(request.getParameter("cPage"));
	 System.out.println(commentNo);
	 
	  int result = 0;
		System.out.println(commentNo);
		
		int refCommentCnt = 0;
		try {
			refCommentCnt = new ReviewService().selectRefCommentCnt(commentNo);
		} catch (ReviewException e) {
			e.printStackTrace();
			throw new ServletException("후기 오류");
		}
		
		if(refCommentCnt > 0) {
		  try {
			result = new ReviewService().deleteComment(commentNo);
		} catch (ReviewException e) {
			e.printStackTrace();
			throw new ServletException("후기 오류");
		}
		}else {
		  try {
			result = new ReviewService().deleteCommentNoRef(commentNo);
		} catch (ReviewException e) {
			e.printStackTrace();
			throw new ServletException("후기 오류");
		}
		}
	 
	 String view = "/WEB-INF/views/common/msg.jsp";
	 String msg = "";
	 String loc = "/review/reviewView?reviewNo="+reviewNo+"&cPage="+cPage;
	 if(result>0) {
		 msg = "댓글삭제 성공";
	 }else {
		 msg = "댓글삭제 실패";
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
