package com.panacea.notice.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.panacea.notice.model.exception.NoticeException;
import com.panacea.notice.model.service.NoticeService;
import com.panacea.notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeViewServlet
 */
@WebServlet("/notice/noticeView")
public class NoticeViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1. 파라미터 변수에 담기
				int noticeNo = Integer.parseInt(request.getParameter("no"));
				
				//2. 업무로직요청
				Notice n = null;
				try {
					n = new NoticeService().selectOne(noticeNo);
				} catch (NoticeException e) {
					e.printStackTrace();
					throw new ServletException("공지 오류");
				}
				System.out.println("notice@NoticeViewServlet="+n);
				
				//3. view단 처리위임
				String view = "";
				if(n != null) {
					request.setAttribute("notice", n);
					view = "/WEB-INF/views/notice/noticeView.jsp";
				}
				else {
					request.setAttribute("msg", "조회한 공지사항이 존재하지 않습니다.");
					request.setAttribute("loc", "/notice/noticeList");
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