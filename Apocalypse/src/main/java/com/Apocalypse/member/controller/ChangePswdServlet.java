package com.Apocalypse.member.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Apocalypse.member.bean.MemberBean;
import com.Apocalypse.member.model.service.MemberService;


@WebServlet("/controler/ChangePswdServlet")
public class ChangePswdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> errorMessage = new HashMap<>();
		request.setAttribute("ErrorMsg", errorMessage);
		// 設定輸入資料的編碼
		request.setCharacterEncoding("UTF-8");
		String member_id = request.getParameter("member_Id");		
		String mPswd = request.getParameter("mPswd");
		MemberService ms = new MemberService();
		MemberBean mb=null;
		String regex = null;		
		boolean flag = true;
		Long nowtime= System.currentTimeMillis();
		Long time = Long.parseLong(request.getParameter("time"));

		if((nowtime-time)>(1000*60*60)) {
			
			request.setAttribute("overtime", (nowtime-time));
			RequestDispatcher rd = request
					.getRequestDispatcher("/top.jsp");
			rd.forward(request, response);
			
		   System.out.println(request.getAttribute("overtime"));
			return;
		}else {
		
		if (mPswd == null || mPswd.trim().length() == 0) {
			errorMessage.put("mPswd","密碼欄必須輸入");
		}else {
			regex = "[a-zA-Z0-9]{8,16}";
		    flag = mPswd.matches(regex);
		    if(!flag){
		    	errorMessage.put("mPswd","密碼欄位輸入的格式不符");
		       }
		   // mPswd=Md5.md5(mPswd);
		}
		
		//如果填寫資料格式有錯或有格子未填寫,交棒給/change/ChangeMemberForm.jsp	
		if (!errorMessage.isEmpty()) {
			RequestDispatcher rd = request
					.getRequestDispatcher("/ForgetPswd/ChangePswd.jsp");
			rd.forward(request, response);
			return;
		}
		
		
		try {
			ms.changePswd(member_id, mPswd);
		} catch (Exception e) {
			errorMessage.put("mPswd","連線有問題");
			System.out.println("執行changePswd()時,連線有問題");
		  }
		
		if (errorMessage.isEmpty()) {

			response.sendRedirect(
		             response.encodeRedirectURL(request.getContextPath()+"/top.jsp"));
			return;
		} else {
			RequestDispatcher rd = request
					.getRequestDispatcher("/ForgetPswd/ChangePswd.jsp");
			rd.forward(request, response);
			return;
		}
		
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
