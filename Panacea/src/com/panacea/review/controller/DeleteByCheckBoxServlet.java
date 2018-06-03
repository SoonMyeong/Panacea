package com.panacea.review.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.panacea.review.model.exception.ReviewException;
import com.panacea.review.model.service.ReviewService;

/**
 * Servlet implementation class DeleteByCheckBoxServlet
 */
@WebServlet("/review/deleteByChk")
public class DeleteByCheckBoxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteByCheckBoxServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String reviewNo = request.getParameter("reviewNo");
		String [] reviewNoArr = reviewNo.split(",");
		
		int result = 0;
		for(int i = 0; i<reviewNoArr.length; i++) {
			try {
				result = new ReviewService().reiewDelete(Integer.parseInt(reviewNoArr[i]));
			} catch (ReviewException e) {
				e.printStackTrace();
				throw new ServletException("후기 오류");
			}
		}
		if(result>0) {
			request.setAttribute("msg", "게시판삭제 성공");
			request.setAttribute("loc", "/review/reviewList");
		}else {
			request.setAttribute("msg", "게시판삭제 실패");
			request.setAttribute("loc", "/review/reviewList");
		}
		request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
