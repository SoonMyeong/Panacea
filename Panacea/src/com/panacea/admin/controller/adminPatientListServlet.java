package com.panacea.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.panacea.admin.model.exception.AdminException;
import com.panacea.admin.model.service.AdminService;
import com.panacea.patient.model.vo.Patient;

/**
 * Servlet implementation class AdminMyPageServlet
 */
@WebServlet("/admin/patientList")
public class adminPatientListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public adminPatientListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1. 파라미터값 변수에 담기
		int numPerPage = 10;//한페이지당 수
		int cPage;//요청페이지
		try{
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch(NumberFormatException e){
			cPage = 1;
		}				
			
		List<Patient> list = null;
		try {
			list = new AdminService().selectPatientList(cPage,numPerPage);
		} catch (AdminException e) {
			e.printStackTrace();
			throw new ServletException("관리자 오류");
		}
		System.out.println("list@patientListServlet="+list);
		
		
		int totalPatientCount = 0;
		try {
			totalPatientCount = new AdminService().patientListCount();
		} catch (AdminException e) {
			e.printStackTrace();
			throw new ServletException("관리자 오류");
		}
		int totalPage = (int)Math.ceil((double)totalPatientCount/numPerPage);
		System.out.println("totalPateintListCount="+totalPatientCount+", totalPage="+totalPage);
		
		
		//2.3 페이지바구성
		String pageBar = "";	
		int pageBarSize = 10;
		//(공식3)시작페이지 번호 세팅
		//cPage=5,pageBarSize=5 -> 1
		//cPage=6,pageBarSize=5 -> 6
		int pageNo = ((cPage - 1)/pageBarSize) * pageBarSize +1;
		//종료페이지 번호 세팅
		int pageEnd = pageNo+pageBarSize-1;
		System.out.println("pageStart["+pageNo+"] ~ pageEnd["+pageEnd+"]");
		
		//[이전] section
		if(pageNo == 1 ){
			//pageBar += "<span>[이전]</span>"; 
		}
		else {
			pageBar += "<a href='"+request.getContextPath()+"/admin/patientList?cPage="+(pageNo-1)+"'>[이전]</a> ";
		}
			
		// pageNo section
		// 보통 !(빠져나가는 조건식)으로 많이 쓴다.
//		while(pageNo<=pageEnd && pageNo<=totalPage){
		while(!(pageNo>pageEnd || pageNo > totalPage)){
			
			if(cPage == pageNo ){
				pageBar += "<span class='cPage'>"+pageNo+"</span> ";
			} 
			else {
				pageBar += "<a href='"+request.getContextPath()+"/admin/patientList?cPage="+pageNo+"'>"+pageNo+"</a> ";
			}
			pageNo++;
		}
		
		//[다음] section
		if(pageNo > totalPage){
			//pageBar += "<span>[다음]</span>";
		} else {
			pageBar += "<a href='"+request.getContextPath()+"/admin/patientList?cPage="+pageNo+"'>[다음]</a>";
		}
		
			
		
		request.setAttribute("list", list);
		request.setAttribute("selectedMenu", "mypage");
		request.setAttribute("selectedSubMenu", "patientList");
		request.setAttribute("pageBar", pageBar);
		request.getRequestDispatcher("/WEB-INF/views/admin/patientList.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
