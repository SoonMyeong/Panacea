package com.panacea.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.panacea.admin.model.exception.AdminException;
import com.panacea.admin.model.service.AdminService;
import com.panacea.common.DoctorFileRenamePolicy;
import com.panacea.common.wrapper.EncryptWrapper;
import com.panacea.doctor.model.vo.Doctor;

/**
 * Servlet implementation class adminDoctorJoinEndServlet
 */
@WebServlet(name="adminDoctorJoinEndServlet", urlPatterns="/admin/doctorJoinEnd")
public class adminDoctorJoinEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public adminDoctorJoinEndServlet() {
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
		String userPw = multiReq.getParameter("userPw");
		String userName = multiReq.getParameter("userName");
		String part = multiReq.getParameter("partName");
		String up_file = multiReq.getFilesystemName("up_file");
		String phone = multiReq.getParameter("phone");
		String address = multiReq.getParameter("address");
		String introduce = multiReq.getParameter("introduce");
		String ssd  = multiReq.getParameter("ssd");
		int result =0;
		
		String[] partName = part.split("-");
		
		
		String password = new EncryptWrapper(request).getPassword(userPw);
		
		
		Doctor d = new Doctor();
		d.setDoctorId(userId);
		d.setPassword(password);
		d.setDoctorName(userName);
		d.setPartName(partName[0]);
		d.setPartId(partName[1]);
		d.setDoctorProfile(up_file);
		d.setPhone(phone);
		d.setAddress(address);
		d.setDoctorIntoduce(introduce);
		d.setSsd(ssd);
		
		System.out.println("doctor@doctorJoinServlet="+d);
		
		try {
			result = new AdminService().insertDoctor(d);
		} catch (AdminException e) {
			e.printStackTrace();
			throw new ServletException("관리자 오류");
		}
		
		String view="";
		String loc="";
		String msg="";
		
		if(result>0) {
			view = "/WEB-INF/views/common/msg.jsp";
			msg = "의사등록이 완료되었습니다.";
			loc = "/admin/doctorList";
			
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			request.getRequestDispatcher(view).forward(request, response);
		}else {
			view = "/WEB-INF/views/common/msg.jsp";
			msg = "의사 등록 중 에러가 발생했습니다. 다시 시도해주세요.";
			loc = "/admin/adminDoctorJoin";
			
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
