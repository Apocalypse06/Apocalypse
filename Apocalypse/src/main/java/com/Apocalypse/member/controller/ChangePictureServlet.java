package com.Apocalypse.member.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.Apocalypse.init.GlobalService;
import com.Apocalypse.member.bean.MemberBean;
import com.Apocalypse.member.model.service.MemberService;


@WebServlet("/controler/ChangePictureServlet")
@MultipartConfig(maxFileSize=16777215)
public class ChangePictureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);
		MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
		Part part=request.getPart("mPicture");
		InputStream is = part.getInputStream();
		mb.setPicture_Name(mb.getMember_Id()+GlobalService.getFileType(part));
//		response.setContentType(part.getContentType());
		int len = 0;
		byte b[] = new byte[819200];
		try (
			 FileOutputStream fos = new FileOutputStream(GlobalService.HEAD_SHOT_PATH+mb.getPicture_Name());
		){
			while((len = is.read(b)) != -1) {
				fos.write(b, 0, len);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		is=part.getInputStream();
		MemberService ms = new MemberService();
		try {
			ms.changePicture(is, mb.getPicture_Name(), mb.getMember_Id());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect(getServletContext().getContextPath()+"/top.jsp");
		return;
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
