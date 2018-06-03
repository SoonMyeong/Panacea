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
 * Servlet implementation class NoticeListServlet
 */
@WebServlet("/notice/noticeList")
public class NoticeListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
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
		try {
			totalMember = new NoticeService().selectListCount();
		} catch (NoticeException e1) {
			e1.printStackTrace();
			throw new ServletException("공지 오류");
		}
		//공식(1) totalPage
		//100건이면 totalPage= 20; 101건이면, totalPage=21;
		int totalPage= (int)Math.ceil(((double)totalMember/numPerPage));
		
		//2.2 페이징된 회원리스트 가져오기
		List<Notice> list = null;
		try {
			list = new NoticeService().selectList(cPage,numPerPage);
		} catch (NoticeException e) {
			e.printStackTrace();
			throw new ServletException("공지 오류");
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
			
		}else {
//			pageBar += "<span>[이전]</span>";
			pageBar += "<div class = 'prevBtn'><a href='"+request.getContextPath()+"/notice/noticeList?cPage="+(pageNo-1)+"'> < </a></div> ";
		}
		//[pageNo]
		while(pageNo <= pageEnd && pageNo <= totalPage) {
            if(pageNo==cPage) {
               pageBar +="<span class='cPage'>"+pageNo+"</span>";               
            }
            else {
               pageBar += "<a href="+request.getContextPath()+"/notice/noticeList?cPage="+pageNo+">"+pageNo+"</a>";
            }
            pageNo++;
         }
		
		//[다음]
		if(pageNo > totalPage) {
			
		}else {
			pageBar += "<a href='"+request.getContextPath()+"/notice/noticeList?cPage="+pageNo+"'> > </a>";
			
		}
	
		//System.out.println("list@NoticeListServlet="+list);
				/*List<Notice> list = new NoticeService().selectList();*/
		//2.view단 처리위임
		
		
		
		
		
		
		request.setAttribute("list", list);
		request.setAttribute("pageBar", pageBar);
		request.setAttribute("cPage", cPage);
		
		RequestDispatcher reqDispatcher = request.getRequestDispatcher("/WEB-INF/views/notice/noticeList.jsp");
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
