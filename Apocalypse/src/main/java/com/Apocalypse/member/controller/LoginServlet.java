package com.Apocalypse.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Apocalypse.author.bean.AuthorBean;
import com.Apocalypse.author.model.service.AuthorService;
import com.Apocalypse.member.bean.MemberBean;
import com.Apocalypse.member.model.service.MemberService;
import com.google.gson.Gson;


@WebServlet("/login/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		HttpSession session = request.getSession();
		Map<String, String> errorMsgMap = new HashMap<String, String>();
		
		request.setAttribute("ErrorMsgKey", errorMsgMap);
		String account = request.getParameter("account");
		String pswd = request.getParameter("pswd");
		java.sql.Timestamp lastLogin= new java.sql.Timestamp(System.currentTimeMillis());
		String lastLogin_Ip=request.getRemoteAddr();
		MemberService ms = new MemberService();
		MemberBean mb=null;
		AuthorService as = new AuthorService();
		AuthorBean ab = null;
		
		List<Integer> list = null;
		String role_Name= null;
				
		if (account == null || account.trim().length() == 0) {
			errorMsgMap.put("AccountEmptyError", "帳號欄必須輸入");
		}
		
		if (pswd == null || pswd.trim().length() == 0) {
			errorMsgMap.put("PswdEmptyError", "密碼欄必須輸入");
		}
		
		// 如果輸入資料有錯誤，送回前端，請使用者修正
				if (!errorMsgMap.isEmpty()) {
					out.println(gson.toJson(errorMsgMap));
					out.close();
					return;
				}

		
		try {
				mb = ms.checkIDPassword(account,pswd);
				if (mb == null) {
					errorMsgMap.put("LoginError", "該帳號不存在或密碼錯誤");
				}else {
					 mb = ms.changeLastLogin(account,lastLogin,lastLogin_Ip);
					 session.setAttribute("LoginOK", mb);
					 list = ms.select_permission(mb.getRole_id());
					 session.setAttribute("My_permission", list);
					 role_Name = ms.select_role_Name(mb.getRole_id());
					 session.setAttribute("My_role_Name", role_Name);
					 ab = as.Select_By_Member_Id(mb.getMember_Id());
					 session.setAttribute("LoginOK_Author", ab);
					 errorMsgMap.put("Success", "登入成功");
				  }
		}catch(SQLException e){
			errorMsgMap.put("LoginError", "執行登入時,連線有問題");
			System.out.println("執行登入時,連線有問題");
		}
		
			out.println(gson.toJson(errorMsgMap));
			out.close();
			return;

		}
		
	
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
