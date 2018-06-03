package com.panacea.review.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.panacea.review.model.exception.ReviewException;
import com.panacea.review.model.service.ReviewService;
import com.panacea.review.model.vo.Review;
import com.panacea.review.model.vo.ReviewComment;

/**
 * Servlet implementation class ReviewViewServlet
 */
@WebServlet("/review/reviewView")
public class ReviewViewServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      int reviewNo = 0;
      int numPerPage = 10;
      int cPage;
      try {
         cPage = Integer.parseInt(request.getParameter("cPage"));
      }catch(NumberFormatException e) {
         cPage = 1;
      }
      
      try {
         reviewNo = Integer.parseInt(request.getParameter("reviewNo"));
      } catch(NumberFormatException e) {
         request.setAttribute("msg", "조회한 게시글이 존재하지 않습니다.");
         request.setAttribute("loc", "/review/reviewList");
         request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp")
              .forward(request, response);
         return;
      }
      System.out.println("reviewNo = "+ reviewNo);
      
      ReviewService ReviewService = new ReviewService();
      
      //쿠키검사
      Cookie[] cookies = request.getCookies();
      String reviewCookieVal = "";
      boolean hasRead = false;
            
      if(cookies!=null) {
         for(Cookie c: cookies) {
            String name = c.getName();
            String value = c.getValue();
                  
            if("reviewCookie".equals(name)) {
               reviewCookieVal = value;
               if(reviewCookieVal.contains("|"+reviewNo+"|")) {
                  hasRead = true;
                  break;
               }
                     
            }
         }
      }
            //게시글 읽음여부 
            if(!hasRead) {
               //조회수 증가
               try {
				ReviewService.increaseReadCount(reviewNo);
			} catch (ReviewException e) {
				e.printStackTrace();
				throw new ServletException("후기 오류");
			}
               
               //쿠키생성
               Cookie reviewCookie = new Cookie("reviewCookie", reviewCookieVal+"|"+reviewNo+"|");
               //reviewCookie.setPath("/mvc/review");//작성안하면, 자동으로 현재경로로 셋팅됨.
               reviewCookie.setMaxAge(60*60*24);//작성안하면, 브라우져에서 영구저장.
               System.out.println("reviewCookie생성 : "+reviewCookie.getValue());
               response.addCookie(reviewCookie);
               
               
            }
            
      
      
      //리뷰가져오기
       Review review = null;
		try {
			review = new ReviewService().selectReviewView(reviewNo);
		} catch (ReviewException e) {
			e.printStackTrace();
			throw new ServletException("후기 오류");
		}
       
       
       
       //댓글 가져오기
       ArrayList<ReviewComment> mostLikeCommentList = null;
		try {
			mostLikeCommentList = new ReviewService().selectMostLikeComment(reviewNo);
		} catch (ReviewException e) {
			e.printStackTrace();
			throw new ServletException("후기 오류");
		}
		
       ArrayList<ReviewComment> commentList = null;
		try {
			commentList = new ReviewService().selectReviewComment(reviewNo, cPage, numPerPage);
		} catch (ReviewException e) {
			e.printStackTrace();
			throw new ServletException("후기 오류");
		}
       int totalCommentCnt = 0;
		try {
			totalCommentCnt = new ReviewService().selectCommentCnt(reviewNo);
		} catch (ReviewException e) {
			e.printStackTrace();
			throw new ServletException("후기 오류");
		}
       int totalPage = (int)Math.ceil((double)totalCommentCnt/numPerPage);
       System.out.println("commentList@Servlet"+commentList);
       
       String pageBar = "";
       int pageBarSize = 5;
       
       int pageNo = ((cPage -1)/pageBarSize) * pageBarSize+1;
       int pageEnd = pageNo + pageBarSize-1;
       
       if(pageNo == 1) {
          
       }else {
         pageBar += "<a href='"+request.getContextPath()+"/review/reviewView?reviewNo="+reviewNo+"&cPage="+(pageNo-1)+"'> < </a> ";
      }
            
         // pageNo section
         // 보통 !(빠져나가는 조건식)으로 많이 쓴다.
//         while(pageNo<=pageEnd && pageNo<=totalPage){
      while(!(pageNo>pageEnd || pageNo > totalPage)){
            
         if(cPage == pageNo ){
            pageBar += "<span class='cPage'>"+pageNo+"</span> ";
         } 
         else {
            pageBar += "<a href='"+request.getContextPath()+"/review/reviewView?reviewNo="+reviewNo+"&cPage="+pageNo+"'>"+pageNo+"</a> ";
         }
            pageNo++;
         }
         
         //[다음] section
         if(pageNo > totalPage){
            //pageBar += "<span>[다음]</span>";
         } else {
            pageBar += "<a href='"+request.getContextPath()+"/review/reviewView?reviewNo="+reviewNo+"&cPage="+pageNo+"'> > </a>";
         }   
        
        String view = "";
        if(review != null) {
           view = "/WEB-INF/views/review/reviewView.jsp";
           request.setAttribute("Review", review);
           request.setAttribute("pageBar", pageBar);
           request.setAttribute("cPage", cPage);
           request.setAttribute("commentlist", commentList);
           request.setAttribute("mostLikeCommentList", mostLikeCommentList);
        }else {
           view = "/WEB-INF/views/common/msg.jsp";
           request.setAttribute("msg", "조회결과가 없습니다.");
           request.setAttribute("loc", "/review/reviewList");
        }
        
        RequestDispatcher reqDispatcher = request.getRequestDispatcher(view);
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