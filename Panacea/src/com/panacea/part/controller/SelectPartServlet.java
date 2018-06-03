package com.panacea.part.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.panacea.part.model.exception.PartException;
import com.panacea.part.model.service.PartService;
import com.panacea.part.model.vo.MedicalPart;

/**
 * Servlet implementation class SelectPartServlet
 */
@WebServlet("/part/selectPart")
public class SelectPartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectPartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String partId= request.getParameter("partId");
		  
		MedicalPart part = null;
		try {
			part = new PartService().selectPart(partId);
		} catch (PartException e) {
			e.printStackTrace();
			throw new ServletException("파트 검색 오류");
		}
		  
		response.setContentType("application/json; charset=utf-8");
		  
		new Gson().toJson(part,response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
