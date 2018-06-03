package com.panacea.doctor.controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.panacea.common.DoctorFileRenamePolicy;
import com.panacea.doctor.model.exception.DoctorException;
import com.panacea.doctor.model.service.DoctorService;
import com.panacea.doctor.model.vo.Doctor;


/**
 * Servlet implementation class PatientUpdateEndServlet
 */
@WebServlet("/doctor/updateEnd")
public class DoctorUpdateEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoctorUpdateEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/***********파일 업로드 로직 시작*************************/
		//1. 업로드될 파일의 저장경로
		//request => session => servletContext객체 => 절대경로
		String root = getServletContext().getRealPath("/");
		//application 저장소의 위치를 뜻함 (getServletContext())
		String saveDirectory = root+"upload\\doctor";
		System.out.println("saveDirectory="+saveDirectory);
		
		//2. 파일최대용량 cos.jar 무료버전이 제공하는 파일 최대 크기는 10MB;
		int maxSize= 1024*1024*10;
		
		
		
		
		//3. MultipartRequest 객체 생성
		MultipartRequest multiReq = new MultipartRequest(request, saveDirectory, maxSize,"UTF-8",
									new DoctorFileRenamePolicy());
		

		String userId = multiReq.getParameter("userId");
		String userName = multiReq.getParameter("userName");
		String partName = multiReq.getParameter("partName");
		String up_file = multiReq.getFilesystemName("up_file");
		String old_file = multiReq.getParameter("old_file");
		String phone = multiReq.getParameter("phone");
		String address = multiReq.getParameter("address");
		int result =0;
		
		//실제업로드된 파일존재여부
		File f = multiReq.getFile("up_file");
		//f의 null여부와 파일사이즈 체크
		if(f!=null && f.length()>0) {
			//첨부한 파일이 있는 경우, 파일 삭제
	/*		File delFile = new File(saveDirectory+"/"+old_file);
			boolean bool = delFile.delete();
			System.out.println(bool?"기존파일삭제 성공":"1.확장자 파일삭제 실패");*/
		}else {
			//첨부한 파일이 없는 경우
			up_file=old_file;
		}
		
		
		Doctor d = new Doctor(userId,userName,partName,up_file,phone,address);
		
		try {
			result = new DoctorService().updateDoctor(d);
		} catch (DoctorException e) {
			e.printStackTrace();
			throw new ServletException("의사 오류");
		}
		
		System.out.println("result@DoctorUpdateServlet="+result);
		
		String view="";
		String loc="";
		String msg="";
		
		if(result>0) {
			view = "/WEB-INF/views/common/msg.jsp";
			msg = "개인정보 변경이 완료되었습니다.";
			loc = "/";
			
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			request.getRequestDispatcher(view).forward(request, response);
		}else {
			view = "/WEB-INF/views/common/msg.jsp";
			msg = "개인정보 변경 중 에러가 발생했습니다. 다시 시도해주세요.";
			loc = "/";
			
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			request.getRequestDispatcher(view).forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
