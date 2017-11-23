package com.Apocalypse.bookSystem.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Apocalypse.bookSystem.dao.SearchBookDAO;
import com.Apocalypse.bookSystem.model.BookBean;
import com.Apocalypse.bookSystem.model.BookStateBean;
import com.Apocalypse.bookSystem.model.ClassifyBean;

@WebServlet(
		urlPatterns= {"/service/SearchBookService","/search_dao_test/SearchBookService.do"}			
)

public class SearchBookService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processSwitch(request,response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processSwitch(request,response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void processSwitch(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String processFlag = request.getParameter("form_number");
		if(processFlag.equals("form_01")) {
			processRequest_01(request,response);
		}else if(processFlag.equals("form_02")) {
			processRequest_02(request,response);
		}else if(processFlag.equals("form_03")){	
			processRequest_03(request,response);
		}else if(processFlag.equals("form_04")){
			processRequest_04(request,response);
		}else {
			System.out.println("can't found form source!");
		}
		
	}
	
	private void processRequest_01(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		request.setCharacterEncoding("UTF-8");
//		String titleStr = new String(request.getParameter("title_str").getBytes("ISO-8859-1"),"UTF-8"); 
		String searchType = "書名";
		String titleStr = request.getParameter("title_str");
		List<BookBean> bbs = new ArrayList<>();
		List<ClassifyBean> cbs = new ArrayList<>();
		List<BookStateBean> bsbs = new ArrayList<>();
		SearchBookDAO  sbd = new SearchBookDAO();
		bbs  = sbd.findBooksByTitle(titleStr);
		cbs  = sbd.countClassifys(titleStr,"b.title");
		bsbs = sbd.countBookStates(titleStr,"b.title");
		sbd.connectClose();
		//暫時性輸出
		request.getSession().setAttribute("bbs", bbs);
		request.getSession().setAttribute("keyWord", titleStr);
		request.getSession().setAttribute("searchType", searchType);
		request.getSession().setAttribute("cbs", cbs);
		request.getSession().setAttribute("bsbs", bsbs);
		response.sendRedirect(getServletContext().getContextPath()+"/search_dao_test/search_Result.jsp");
		return;	
		
	}
	
	private void processRequest_02(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		request.setCharacterEncoding("UTF-8");
//		String titleStr = new String(request.getParameter("title_str").getBytes("ISO-8859-1"),"UTF-8"); 
		String searchType = "作者名";
		String penNameStr = request.getParameter("penName_str");
		List<BookBean> bbs = new ArrayList<>();
		List<ClassifyBean> cbs = new ArrayList<>();
		List<BookStateBean> bsbs = new ArrayList<>();						
		SearchBookDAO  sbd = new SearchBookDAO();
		bbs = sbd.findBooksByPenName(penNameStr);
		cbs = sbd.countClassifys(penNameStr,"a.pen_Name");
		bsbs = sbd.countBookStates(penNameStr,"a.pen_Name");
		sbd.connectClose();
		//暫時性輸出
		request.getSession().setAttribute("bbs", bbs);
		request.getSession().setAttribute("keyWord", penNameStr);
		request.getSession().setAttribute("searchType", searchType);
		request.getSession().setAttribute("cbs", cbs);
		request.getSession().setAttribute("bsbs", bsbs);
		response.sendRedirect(getServletContext().getContextPath()+"/search_dao_test/search_Result.jsp");
		return;	
		
	
	}
	
	private void processRequest_03(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		request.setCharacterEncoding("UTF-8");
//		String titleStr = new String(request.getParameter("title_str").getBytes("ISO-8859-1"),"UTF-8"); 
		String searchType = "簡介內容";
		String introStr = request.getParameter("intro_str");
		List<BookBean> bbs = new ArrayList<>();
		List<ClassifyBean> cbs = new ArrayList<>();
		List<BookStateBean> bsbs = new ArrayList<>();		
		SearchBookDAO  sbd = new SearchBookDAO();
		bbs = sbd.findBooksByIntro(introStr);
		cbs = sbd.countClassifys(introStr,"b.intro");
		bsbs = sbd.countBookStates(introStr,"b.intro");
		sbd.connectClose();
		//暫時性輸出
		request.getSession().setAttribute("bbs", bbs);
		request.getSession().setAttribute("keyWord", introStr);
		request.getSession().setAttribute("searchType",searchType);
		request.getSession().setAttribute("cbs", cbs);
		request.getSession().setAttribute("bsbs", bsbs);
		response.sendRedirect(getServletContext().getContextPath()+"/search_dao_test/search_Result.jsp");
		return;	
		
	}
	
	private void processRequest_04(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		request.setCharacterEncoding("UTF-8");
//		String titleStr = new String(request.getParameter("title_str").getBytes("ISO-8859-1"),"UTF-8"); 
		String searchType = "進階搜尋(書名、作者名、簡介內容)";
		String titleStr = request.getParameter("title_str");
		String penNameStr = request.getParameter("penName_str");
		String introStr = request.getParameter("intro_str");
		List<BookBean> bbs = new ArrayList<>();
		List<ClassifyBean> cbs = new ArrayList<>();
		List<BookStateBean> bsbs = new ArrayList<>();
		SearchBookDAO  sbd = new SearchBookDAO();
		bbs = sbd.findBooksByAdvancedRule(titleStr,penNameStr,introStr);
		cbs = sbd.countClassifysAdvanced(titleStr,penNameStr,introStr);
		bsbs = sbd.countBookStatesAdvanced(titleStr,penNameStr,introStr);
		sbd.connectClose();
		//暫時性輸出
		request.getSession().setAttribute("bbs", bbs);
		request.getSession().setAttribute("keyWord", titleStr+":"+penNameStr+":"+introStr);
		request.getSession().setAttribute("searchType",searchType);
		request.getSession().setAttribute("cbs", cbs);
		request.getSession().setAttribute("bsbs", bsbs);
		response.sendRedirect(getServletContext().getContextPath()+"/search_dao_test/search_Result.jsp");
		return;	
		
	}
	
	
}
