package com.panacea.patient.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.panacea.patient.model.exception.PatientException;
import com.panacea.patient.model.service.PatientService;

/**
 * Servlet implementation class UpdatePasswordEndServlet
 */
@WebServlet(name="PatientUpdatePasswordEndServlet",urlPatterns="/patient/updatePasswordEnd")
public class PatientUpdatePasswordEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PatientUpdatePasswordEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String now_password = request.getParameter("now_password");
		String new_password = request.getParameter("new_password");
		
		System.out.println("userId="+userId);
		System.out.println("now_password="+now_password);
		System.out.println("new_password="+new_password);
		
		
		int result = 0;
		try {
			result = new PatientService().loginCheck(userId, now_password);
		} catch (PatientException e) {
			e.printStackTrace();
			throw new ServletException("환자 오류");
		}
		
		//4. 받은 결과에 따라 view단 분기
				String view="";
				String msg="";
				String loc="/";

				if(result==PatientService.LOGIN_OK) {
					//로그인 성공했을 경우
					view ="/WEB-INF/views/common/msg.jsp";
						
					int result_new = 0;
					try {
						result_new = new PatientService().updatePassword(userId,new_password);
					} catch (PatientException e) {
						e.printStackTrace();
						throw new ServletException("환자 오류");
					}
					if(result_new>0) {
						msg="비밀번호 변경 성공!";
					}else {
						msg="정보 변경 실패.. 관리자에게 문의해주세요.";
					}
					request.setAttribute("msg", msg);
					request.setAttribute("loc", loc);
					
					RequestDispatcher reqDispatcher = request.getRequestDispatcher(view);
					reqDispatcher.forward(request, response);

						
				
				}else {
					//로그인 실패했을 경우
					view ="/WEB-INF/views/common/msg.jsp";
					
					if(result==PatientService.WRONG_PASSWORD) {
						msg = "현재 비밀번호를 잘못 입력하셨습니다.";
					}else if(result==PatientService.ID_NOT_EXIST) {
						msg = "아이디 오류 발생! 다시 시도하세요..";
					}
				
					request.setAttribute("msg", msg);
					request.setAttribute("loc", loc);
					
					RequestDispatcher reqDispatcher = request.getRequestDispatcher(view);
					reqDispatcher.forward(request, response);
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
