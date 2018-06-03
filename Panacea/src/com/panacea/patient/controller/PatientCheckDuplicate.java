package com.panacea.patient.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.panacea.patient.model.exception.PatientException;
import com.panacea.patient.model.service.PatientService;
import com.panacea.patient.model.vo.Patient;

/**
 * Servlet implementation class PatientCheckDuplicate
 */
@WebServlet("/patient/checkDuplicate")
public class PatientCheckDuplicate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PatientCheckDuplicate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userId= request.getParameter("id");
		
		Patient p = null;
		try {
			p = new PatientService().selectPatient(userId);
		} catch (PatientException e) {
			e.printStackTrace();
			throw new ServletException("환자 회원가입 오류");
		}
		
		//2.응답객체 출력
		response.setContentType("application/json; charset=utf-8");
		
		boolean isUsable = p==null?true:false;
		System.out.println(userId+" : isUsable?"+isUsable);
//		Gson gson = new Gson();
//		String jsonStr = gson.toJson(userList);
//		System.out.println(jsonStr);
//		response.getWriter().append(jsonStr);
		new Gson().toJson(p,response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
