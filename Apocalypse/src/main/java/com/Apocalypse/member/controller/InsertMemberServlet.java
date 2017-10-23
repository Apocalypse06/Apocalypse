package com.Apocalypse.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.Apocalypse.member.Email.JavaMailMain;
import com.Apocalypse.member.bean.MemberBean;
import com.Apocalypse.member.model.service.MemberService;
import com.google.gson.Gson;

@WebServlet("/insert/InsertMemberServlet")
public class InsertMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
						
		
		// 定義存放錯誤訊息的 Collection物件
		Map<String, String> errorMessage = new HashMap<>();
		HttpSession session = request.getSession();
		request.setAttribute("ErrorMsg", errorMessage);
        String member_Id= UUID.randomUUID().toString().replace("-", StringUtils.EMPTY).trim();
        java.sql.Date reg_date=new java.sql.Date(System.currentTimeMillis());
               
		// 設定輸入資料的編碼
        request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		String regex = null;		
		boolean flag = true;
		
		//檢查帳號欄位格式
		String mAccount = request.getParameter("mAccount");
		if (mAccount == null || mAccount.trim().length() == 0) {
			errorMessage.put("mAccount","帳號欄位必須輸入");
		}else {
			 regex = "[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)";
		     flag = mAccount.matches(regex);
		    if(!flag){
		    	errorMessage.put("mAccount","帳號欄位輸入的格式不符");
		       }
			
		}
		
		//檢查暱稱欄位格式
		String mNick_Name = request.getParameter("mNick_Name");
		if (mNick_Name == null || mNick_Name.trim().length() == 0) {
			errorMessage.put("mNick_Name","暱稱欄位必須輸入");
		}else {
			regex = "[a-zA-Z0-9\\x{4e00}-\\x{9fa5}]{2,10}";
		    flag = mNick_Name.matches(regex);
		    if(!flag){
		    	errorMessage.put("mNick_Name","暱稱欄位輸入的格式不符");
		       }
		}
		
		//檢查密碼欄位格式
		String mPswd = request.getParameter("mPswd");
		if (mPswd == null || mPswd.trim().length() == 0) {
			errorMessage.put("mPswd","密碼欄位必須輸入");
		}else {
			regex = "[a-zA-Z0-9]{8,16}";
		    flag = mPswd.matches(regex);
		    if(!flag){
		    	errorMessage.put("mPswd","密碼欄位輸入的格式不符");
		       }
		}
		
		//檢查生日欄位格式
		String mBirthday = request.getParameter("mBirthday");
		if (mBirthday == null || mBirthday.trim().length() == 0) {
			errorMessage.put("mBirthday","生日欄位必須輸入");
		}
		java.sql.Date date = null;
		if (mBirthday != null && mBirthday.trim().length() > 0) {
			try {
				//將生日由字串轉成日期格式
				date = java.sql.Date.valueOf(mBirthday);
			} catch (IllegalArgumentException e) {
				errorMessage.put("mBirthday","生日欄位輸入的格式錯誤");
			}
		}
		//檢查性別欄位格式	
		String mGender = request.getParameter("mGender");
		if (mGender == null || mGender.trim().length() == 0) {
			errorMessage.put("mGender","性別欄位必須輸入");
		}
		
		//檢查手機欄位格式
		String mCellphone = request.getParameter("mCellphone");
		if (mCellphone == null || mCellphone.trim().length() == 0) {
			errorMessage.put("mCellphone","手機欄位必須輸入");
		}else {
			regex = "[0-9]{10}";
		    flag = mCellphone.matches(regex);
		    if(!flag){
		    	errorMessage.put("mCellphone","手機欄位輸入的格式不符");
		       }
		} 
		
			
		// 如果輸入資料有錯誤，送回前端，請使用者修正
		if (!errorMessage.isEmpty()) {
			out.println(gson.toJson(errorMessage));
			out.close();
			return;
		}
	

		MemberBean Mb = new MemberBean(member_Id, mAccount, mPswd, mNick_Name, date, mCellphone, mAccount, mGender, reg_date);
		MemberService ms = new MemberService();
		MemberBean mb=null;
		try {
			int i = ms.checkAccount(mAccount);
			if(i==1) {
				     errorMessage.put("mAccount","帳號重複，請重新輸入帳號");
				    
			}else {
				   mb=ms.insertMember(Mb);
				   session.setAttribute("LoginOK", mb); 
			}
		}catch(SQLException e){
			errorMessage.put("MySQL","執行註冊時,連線有問題");
			System.out.println("執行註冊時,連線有問題");
		}
		
		if (errorMessage.isEmpty()) {
			JavaMailMain mail=new JavaMailMain();
			mail.sendmail_Changerole_id(member_Id,mAccount);
			errorMessage.put("Success", "註冊成功");
		} 

		out.println(gson.toJson(errorMessage));
		out.close();
		return;
	
	  }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}
	
}
