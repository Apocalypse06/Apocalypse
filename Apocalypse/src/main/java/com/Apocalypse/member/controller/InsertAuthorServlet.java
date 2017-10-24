package com.Apocalypse.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Apocalypse.member.bean.AuthorBean;
import com.Apocalypse.member.bean.MemberBean;
import com.Apocalypse.member.model.service.AuthorService;
import com.Apocalypse.member.model.service.MemberService;
import com.google.gson.Gson;


@WebServlet("/insert/InsertAuthorServlet")
public class InsertAuthorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
				// 定義存放錯誤訊息的 Collection物件
				Map<String, String> errorMessage = new HashMap<>();
				HttpSession session = request.getSession();
				request.setAttribute("ErrorMsg", errorMessage);
				MemberBean MB= (MemberBean)session.getAttribute("LoginOK");
				
		        // 設定輸入資料的編碼
		        request.setCharacterEncoding("UTF-8");
				response.setContentType("application/json; charset=UTF-8");
				PrintWriter out = response.getWriter();
				Gson gson = new Gson();
						
				
				String mpen_Name = request.getParameter("mpen_Name");
				if (mpen_Name == null || mpen_Name.trim().length() == 0) {
					errorMessage.put("mpen_Name","筆名欄位必須輸入");
				}
				
				String mbank_Account = request.getParameter("mbank_Account");
				if (mbank_Account == null || mbank_Account.trim().length() == 0) {
					errorMessage.put("mbank_Account","銀行帳號欄位必須輸入");
				}
				
				
					
				// 3-1. 如果輸入資料有錯誤，送回前端，請使用者修正
				if (!errorMessage.isEmpty()) {
					out.println(gson.toJson(errorMessage));
					out.close();
					return;
				}
				
				
				AuthorBean AB = new AuthorBean(mpen_Name, MB.getMember_Id(), mbank_Account);
				AuthorService as = new AuthorService();
				MemberService ms = new MemberService();
				AuthorBean ab = null;
				MemberBean mb = null;
				
				
				try {
					int i = as.check_Pen_Name(mpen_Name);
					if(i==1) {
						     errorMessage.put("mpen_Name","筆名重複，請重新輸入筆名");
						    
					}else {
						   ab=as.insertMember(AB);
						   session.setAttribute("LoginOK_Author", ab); 
						   mb=ms.changerole_id(MB.getMember_Id(),3);
						   session.setAttribute("LoginOK", mb); 
					}
				}catch(SQLException e){
					errorMessage.put("MySQL","執行註冊時,連線有問題");
					System.out.println("執行註冊時,連線有問題");
				}
				
				if (errorMessage.isEmpty()) {
					
					errorMessage.put("Success", "升級成為作家通過 ");
				} 

				out.println(gson.toJson(errorMessage));
				out.close();
				return;
			
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
