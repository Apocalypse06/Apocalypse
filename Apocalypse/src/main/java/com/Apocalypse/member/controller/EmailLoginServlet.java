package com.Apocalypse.member.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Apocalypse.member.bean.MemberBean;
import com.Apocalypse.member.model.service.MemberService;


@WebServlet("/controler/EmailLogInServlet")
public class EmailLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String contextPath = getServletContext().getContextPath();
		String member_Id = request.getParameter("member_Id");
		int role_id = Integer.parseInt(request.getParameter("role_id"));
		MemberService ms = new MemberService();
		MemberBean mb=null;
		
		try { 
			mb = ms.changerole_id(member_Id,role_id);
			
		}catch(SQLException e) {
			System.out.println("執行changerole_id()時,連線有問題");
		}
		session.setAttribute("LoginOK", mb);
		response.sendRedirect(
			    response.encodeRedirectURL(contextPath + "/top.jsp" ));
	   return;
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}