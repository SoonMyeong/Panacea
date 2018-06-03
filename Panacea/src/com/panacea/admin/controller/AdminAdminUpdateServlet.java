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
import com.panacea.admin.model.vo.Admin;


/**
 * Servlet implementation class AdminAdminUpdateServlet
 */
@WebServlet("/admin/adminUpdate")
public class AdminAdminUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminAdminUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		
		
		Admin list=null;
		try {
			list = new AdminService().selectAdmin(userId);
		} catch (AdminException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServletException("관리자 조회 오류");
		}
		
		System.out.println("userId="+userId);
		
		System.out.println("list"+list);
		request.setAttribute("list", list);
		request.setAttribute("selectedSubMenu", "adminView");
		request.getRequestDispatcher("/WEB-INF/views/admin/adminUpdate.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
