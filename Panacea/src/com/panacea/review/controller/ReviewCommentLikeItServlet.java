package com.panacea.review.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.panacea.review.model.exception.ReviewException;
import com.panacea.review.model.service.ReviewService;

/**
 * Servlet implementation class ReviewCommentLikeItServlet
 */
@WebServlet("/review/likeit")
public class ReviewCommentLikeItServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewCommentLikeItServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
				int result = 0;
				int commentNo = Integer.parseInt(request.getParameter("commentNo"));
				int reviewNo = Integer.parseInt(request.getParameter("reviewNo"));
				int cPage = Integer.parseInt(request.getParameter("cPage"));
				System.out.println("commentNo="+ commentNo);
				System.out.println("reviewNo="+ reviewNo);
					
				//쿠키검사
				Cookie[] cookies = request.getCookies();
				String likeCookieVal = "";
				
				boolean hasRead = false;
						
				if(cookies!=null) {
					for(Cookie c: cookies) {
						String name = c.getName();
						String value = c.getValue();
								
						if("likeCookie".equals(name)) {
							likeCookieVal = value;
							if(likeCookieVal.contains("|"+commentNo+"|")) {
								hasRead = true;
								break;
							}
									
						}
					}
				}
						//좋아요 누름처리 여부
				if(!hasRead) {
					//좋아요 증가
					try {
						result = new ReviewService().updateLike(commentNo);
					} catch (ReviewException e) {
						e.printStackTrace();
						throw new ServletException("후기 오류");
					}
					
					//쿠키생성
					Cookie likeCookie = new Cookie("likeCookie", likeCookieVal+"|"+commentNo+"|");
					//reviewCookie.setPath("/mvc/review");//작성안하면, 자동으로 현재경로로 셋팅됨.
					likeCookie.setMaxAge(60*60*24);//작성안하면, 브라우져에서 영구저장.
					System.out.println("likeCookie생성 : "+likeCookie.getValue());
					
					response.addCookie(likeCookie);
							
				}
				String view = "/WEB-INF/views/common/msg.jsp";
				String loc = "/review/reviewView?reviewNo="+reviewNo+"&cPage="+cPage;
				if(result>0) {
					request.setAttribute("msg", "좋아요 성공");
					request.setAttribute("loc", loc);
				}else {
					request.setAttribute("msg", "이미 좋아요를 누르셨습니다");
					request.setAttribute("loc", loc);
				}
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
