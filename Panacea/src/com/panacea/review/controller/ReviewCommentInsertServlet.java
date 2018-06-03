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
 * Servlet implementation class ReviewCommentInsertServlet
 */
@WebServlet("/review/reviewCommentInsert")
public class ReviewCommentInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewCommentInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 파라미터값 핸들링
		 String reviewCommentWriter = request.getParameter("reviewCommentWriter");
		 String reviewCommentContent = request.getParameter("reviewCommentContent");
		 int reviewRef = Integer.parseInt(request.getParameter("reviewRef"));
		 int reviewCommentLevel = Integer.parseInt(request.getParameter("reviewCommentLevel"));
		 int reviewCommentRef = Integer.parseInt(request.getParameter("reviewCommentRef"));
		 int cPage = Integer.parseInt(request.getParameter("cPage"));
		
		 
		 ReviewComment rc = new ReviewComment();
		 rc.setCommentWriter(reviewCommentWriter);
		 rc.setCommentContent(reviewCommentContent);
		 rc.setCommentRef(reviewCommentRef);
		 rc.setCommentLevel(reviewCommentLevel);
		 rc.setReviewNo(reviewRef);
		 System.out.println("rc@Servlet = "+ rc);
		 
		 int result = 0;
		try {
			result = new ReviewService().insertReviewComment(rc);
		} catch (ReviewException e) {
			e.printStackTrace();
			throw new ServletException("후기 오류");
		}
		 
		 String view = "/WEB-INF/views/common/msg.jsp";
			String msg = "";
			String loc = "/review/reviewView?reviewNo="+reviewRef+"&cPage="+cPage;//부모게시글의 번호로 다시 돌아오기
			if(result>0) {
				msg = "댓글 등록 성공";
			}else {
				msg = "댓글 등록 실패";
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
