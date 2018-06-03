package com.panacea.notice.controller;

import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class NoticeFinder
 */
@WebServlet("/notice/noticeFinder")
public class NoticeFinderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeFinderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//1.파라미터변수처리
		String searchType = request.getParameter("searchType");
		String searchKeyword= request.getParameter("searchKeyword");
		System.out.printf("searchType=%s, searchKeyword=%s\n",searchType,searchKeyword);
		
		int cPage;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		}catch(NumberFormatException e) {
			//cPage 파라미터가 값이 없거나, 부정 입력된 경우대비
			cPage=1;
		}
		System.out.println("cPage="+cPage);
		
		//2. 비지니스로직
		int numPerPage = 10;
		//2.1 전체 게시물 수
		int totalMember = 0;
	    switch (searchType) {
	    case "admin_Id"	:try {
				totalMember = new NoticeService().selectNoticeCountByAdminId(searchKeyword);
			} catch (NoticeException e) {
				e.printStackTrace();
				throw new ServletException("공지 오류");
			}break;
	    case "notice_Title" :try {
				totalMember = new NoticeService().selectNoticeTitle(searchKeyword);
			} catch (NoticeException e) {
				e.printStackTrace();
				throw new ServletException("공지 오류");
			}break;
	  /*  case "noticeDate"	:totalMember = new NoticeService().selectNoticeDate(searchKeyword);break;*/
	    }
	    int totalPage = (int)Math.ceil((double)totalMember/numPerPage);
	    
	    //System.out.println("totalMember="+totalMember+", totalPage="+totalPage);
	    
	    //3.2 현재페이지의 회원구하기
	    List<Notice> list = null;
	    switch (searchType) {
	    case "admin_Id"	:try {
				list = new NoticeService().selectNoticeByAdminId(searchKeyword, cPage, numPerPage);
			} catch (NoticeException e) {
				e.printStackTrace();
				throw new ServletException("공지 오류");
			}break;
	    case "notice_Title"	:try {
				list = new NoticeService().selectNoticeTitle(searchKeyword, cPage, numPerPage);
			} catch (NoticeException e) {
				e.printStackTrace();
				throw new ServletException("공지 오류");
			}break;
	/*    case "noticeDate"	:list = new NoticeService().selectNoticeDate(searchKeyword, cPage, numPerPage);break;*/
	    }
		
		//2.3 페이징바 만들기
		String pageBar="";
		int pageBarSize = 5; //페이지바 페이지 개수
		//공식(3) 시작페이지 구하기
		//cPage =5, pageBarSize=5 --> 1
		//cPage =7, pageBarSize=5 --> 6
		//cPage =11, pageBarSize=5 --> 11
		//pageBarSize*(n-1)+1
		
		// (cPage-1)/pageBarSize*pageBarSize+1;
		int pageNo = pageBarSize*((int)Math.ceil((double)cPage/pageBarSize)-1)+1;
		
		//종료페이지 no
		int pageEnd = pageNo+pageBarSize-1;
		
		//[이전]
		if(pageNo ==1) {
			
		} else {
	        pageBar += "<a href='"+request.getContextPath()+"/notice/noticeFinder?searchType="+searchType+"&searchKeyword="+searchKeyword+"&cPage="+(pageNo-1)+"'> < </a> ";
	    }
	        
	    //페이지번호 section
	    while(pageNo<=pageEnd && pageNo<=totalPage) {
	        
	        if(cPage == pageNo ){
	            pageBar += "<span class='cPage'>"+pageNo+"</span> ";
	        } 
	        else {
	            pageBar += "<a href='"+request.getContextPath()+"/notice/noticeFinder?searchType="+searchType+"&searchKeyword="+searchKeyword+"&cPage="+pageNo+"'>"+pageNo+"</a> ";
	        }
	        pageNo++;
	    }
	    
	    if(pageNo > totalPage){
	    	
	    } else {
	        pageBar += "<a href='"+request.getContextPath()+"/notice/notcieFinder?searchType="+searchType+"&searchKeyword="+searchKeyword+"&cPage="+pageNo+"'> > </a>";
	    }
		
		 //4.처리결과 view단 위임
	    request.setAttribute("list", list);
	    request.setAttribute("searchType", searchType);
	    request.setAttribute("searchKeyword", searchKeyword);
	    
	    request.setAttribute("pageBar",pageBar);	
	    request.setAttribute("cPage",cPage);
	    
	    RequestDispatcher reqDispatcher = request.getRequestDispatcher("/WEB-INF/views/notice/noticeFinder.jsp");
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
