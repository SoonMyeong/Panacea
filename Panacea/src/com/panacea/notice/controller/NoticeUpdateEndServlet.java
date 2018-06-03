package com.panacea.notice.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.panacea.notice.model.exception.NoticeException;
import com.panacea.notice.model.service.NoticeService;
import com.panacea.notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeFormEndServlet
 */
@WebServlet("/notice/noticeUpdateEnd")
public class NoticeUpdateEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeUpdateEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		/********파일 업로드 로직 시작 ************/
		//1. 업로드될 파일의 저장경로
		//servletContext객체 => 절대경로
		String saveDirectory = getServletContext().getRealPath("/upload/notice");
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
		int noticeNo = Integer.parseInt(multiReq.getParameter("no"));
	    String noticeTitle = multiReq.getParameter("title");
	    String noticeWriter = multiReq.getParameter("writer");
	    String noticeContent = multiReq.getParameter("content");
	   
	    String filePath = multiReq.getFilesystemName("up_file");
	    String old_file = multiReq.getParameter("old_file");
	    //시스템에 중복된 이름이 있다면, 새로부여된 파일명을 리턴함.
	    
	 
	    String test = multiReq.getParameter("filedelete");
	    String emphasize = multiReq.getParameter("emphasize");
	    System.out.println("test"+test);
	    
	    
	    System.out.println("filePath="+filePath);
	    
	    //실제업로드된 파일존재여부
	    File f = multiReq.getFile("up_file");
	    //f의 null여부와 파일사이즈를 체크
	    if(f!=null && f.length()>0) {
	    	//첨부한 파일이 있는 경우, 기존 파일은 삭제처리함.
	    	File delFile = new File(saveDirectory+"/"+old_file);
	    	boolean bool = delFile.delete();
	    	System.out.println(bool?"기존파일삭제성공!":"기존파일삭제실패");
	    	
	    }
	     else if(test.equals("2")) {
	    	File delFile = new File(saveDirectory+"/"+old_file);
	    	boolean bool = delFile.delete();
	   }
	    else {
	    	//첨부한 파일이 없는 경우
	    	filePath = old_file;
	    }
	
	
	 
	    //2. 비지니스로직 호출
	    Notice n = new Notice(noticeNo, noticeWriter, noticeTitle, noticeContent, filePath, null, null,null);
	  
	    int result = 0;
		try {
			result = new NoticeService().updateNotice(n);
		} catch (NoticeException e) {
			e.printStackTrace();
			throw new ServletException("공지 오류");
		} 
	    
	    
	    //3. 처리결과에 따른 view단에 처리위임.
	    String view = "/WEB-INF/views/common/msg.jsp";
	    String msg = "";
	    //javascript/html에서 사용할 url은 contextPath를 포함한다.
	    String loc = "/notice/noticeList";

	    if(result>0)
	        msg = "공지사항 수정 성공!";
	    else 
	        msg = "공지사항 수정 실패!";	
	    
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
