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
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class NoticeFormEndServlet
 */
@WebServlet("/notice/noticeFormEnd")
public class NoticeFormEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeFormEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/********파일 업로드 로직 시작 ************/
		//1. 업로드될 파일의 저장경로
		//request => session => servletContext객체 => 절대경로
		String root = request.getSession().getServletContext().getRealPath("/");
		String saveDirectory = root+"upload/notice";
		System.out.println("saveDirectory="+saveDirectory);
		
		//2.파일최대용량 : cos.jar 무료버젼이 제공하는 파일최대크기는 10MB;
		int maxSize = 1024*1024*10;
		
		//3.MultipartRequest객체 생성
		MultipartRequest multiReq = new MultipartRequest(request, 
												   saveDirectory, 
												   maxSize,
												   "UTF-8",
												   new DefaultFileRenamePolicy());
		
		/********파일 업로드 로직 끝 ************/
		
		//1. 전송값 담기
	    String noticeTitle = multiReq.getParameter("title");
	    String noticeWriter = multiReq.getParameter("writer");
	    String noticeContent = multiReq.getParameter("content");
	    String filePath = multiReq.getFilesystemName("up_file");
	    //시스템에 중복된 이름이 있다면, 새로부여된 파일명을 리턴함.
	    String emphasize = multiReq.getParameter("emphasize");
	    System.out.println("filePath="+filePath);
	    System.out.println(emphasize);
	    if(emphasize.equals("on")) {
	    	emphasize="2";
	    }
	   
	    
	    //insert용 Notice의 생성자를 새로 추가함.
	    Notice n = new Notice(0, noticeWriter, noticeTitle, noticeContent, filePath, null, null ,null);
	    //Notice n = new Notice(noticeTitle, noticeWriter, noticeContent, filePath);
	    
	    
	    //2. 비지니스로직 호출
	    int result =0;
	    switch(emphasize) {
	    case "1" : try {
				result = new NoticeService().insertNotice2(n);
			} catch (NoticeException e) {
				e.printStackTrace();
				throw new ServletException("공지 오류");
			} break;
	    case "2" : try {
				result = new NoticeService().insertNotice(n);
			} catch (NoticeException e) {
				e.printStackTrace();
				throw new ServletException("공지 오류");
			} break;
	    }
	  
	    
	    //3. 처리결과에 따른 view단에 처리위임.
	    String view = "/WEB-INF/views/common/msg.jsp";
	    String msg = "";
	    //javascript/html에서 사용할 url은 contextPath를 포함한다.
	    String loc = "/notice/noticeList";

	    if(result>0)
	        msg = "공지사항 등록 성공!";
	    else 
	        msg = "공지사항 등록 실패!";	
	    
	    request.setAttribute("msg", msg);
	    request.setAttribute("loc", loc);
	    
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
