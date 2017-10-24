package com.Apocalypse.bookSystem.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		urlPatterns= {"/service/SearchBookRefineService","/search_dao_test/SearchBookRefineService.do"}			
)

public class SearchBookRefineService extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Map<String, String> searchStyle_map = new HashMap<>();

	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processSwitch(request,response);
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processSwitch(request,response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	private void processSwitch(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String processFlag = request.getParameter("refine_type");
		String searchType  = request.getParameter("search_type");
		
		searchStyle_map.put("書名", "b.title");
		searchStyle_map.put("作者名", "a.pen_Name");		
		searchStyle_map.put("簡介內容", "b.intro");

		if(processFlag.equals("filter_classify")&&(!searchType.equals("進階搜尋(書名、作者名、簡介內容)"))) {
			processRequest_01(request,response);
		}else if(processFlag.equals("filter_classify")&&(searchType.equals("進階搜尋(書名、作者名、簡介內容)"))){	
			processRequest_02(request,response);	
			
		}else if(processFlag.equals("filter_bookstate")&&(!searchType.equals("進階搜尋(書名、作者名、簡介內容)"))){	
			processRequest_03(request,response);
		}else if(processFlag.equals("filter_bookstate")&&(searchType.equals("進階搜尋(書名、作者名、簡介內容)"))){	
			processRequest_04(request,response);	
			
			

		}else if(processFlag.equals("sort_Clicks")&&(!searchType.equals("進階搜尋(書名、作者名、簡介內容)"))){	
			processRequest_05(request,response);
		}else if(processFlag.equals("sort_Clicks")&&(searchType.equals("進階搜尋(書名、作者名、簡介內容)"))){
			processRequest_06(request,response);
	
		}else if(processFlag.equals("sort_PublishDate")&&(!searchType.equals("進階搜尋(書名、作者名、簡介內容)"))){	
			processRequest_07(request,response);
		}else if(processFlag.equals("sort_PublishDate")&&(searchType.equals("進階搜尋(書名、作者名、簡介內容)"))){
			processRequest_08(request,response);

		}else if(processFlag.equals("sort_Tickets")&&(!searchType.equals("進階搜尋(書名、作者名、簡介內容)"))){	
			processRequest_09(request,response);
		}else if(processFlag.equals("sort_Tickets")&&(searchType.equals("進階搜尋(書名、作者名、簡介內容)"))){
			processRequest_10(request,response);
	
			
			
			
			
			
			
			
			
			
			
		}else {
			System.out.println("cant found form source!(Refine)");
		}
		
		
	}
	
	
	private void processRequest_01(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		request.setCharacterEncoding("UTF-8");
//		String titleStr = new String(request.getParameter("title_str").getBytes("ISO-8859-1"),"UTF-8"); 
		String searchType  = request.getParameter("search_type");
		String keyWord = request.getParameter("key_word");
		String classifyType = request.getParameter("classify_type");
		List<BookBean> bbs = new ArrayList<>();
		List<ClassifyBean> cbs = new ArrayList<>();
		List<BookStateBean> bsbs = new ArrayList<>();
		SearchBookDAO  sbd = new SearchBookDAO();
		bbs  = sbd.filterBooksByClassify(searchStyle_map.get(searchType),keyWord,classifyType);
		cbs  = sbd.countClassifys(keyWord,searchStyle_map.get(searchType));
		bsbs = sbd.countBookStates(keyWord,searchStyle_map.get(searchType));
		sbd.connectClose();
		//暫時性輸出
		request.getSession().setAttribute("bbs", bbs);
		request.getSession().setAttribute("keyWord", keyWord);
		request.getSession().setAttribute("searchType", searchType);
		request.getSession().setAttribute("cbs", cbs);
		request.getSession().setAttribute("bsbs", bsbs);
		response.sendRedirect(getServletContext().getContextPath()+"/search_dao_test/search_Result.jsp");
		return;	
	}
	
	private void processRequest_02(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		request.setCharacterEncoding("UTF-8");
//		String titleStr = new String(request.getParameter("title_str").getBytes("ISO-8859-1"),"UTF-8"); 
		String searchType  = request.getParameter("search_type");	
		String[] keywords = request.getParameter("key_word").split(":",3);
		String titleStr   = keywords[0];
		String penNameStr = keywords[1];
		String introStr   = keywords[2];
		String classifyType = request.getParameter("classify_type");
		List<BookBean> bbs = new ArrayList<>();
		List<ClassifyBean> cbs = new ArrayList<>();
		List<BookStateBean> bsbs = new ArrayList<>();
		SearchBookDAO  sbd = new SearchBookDAO();
		bbs = sbd.filterBooksByClassifyAdvanced(keywords,classifyType);
		cbs = sbd.countClassifysAdvanced(titleStr,penNameStr,introStr);
		bsbs = sbd.countBookStatesAdvanced(titleStr,penNameStr,introStr);
		sbd.connectClose();
		//暫時性輸出
		request.getSession().setAttribute("bbs", bbs);
		request.getSession().setAttribute("keyWord", titleStr+":"+penNameStr+":"+introStr);
		request.getSession().setAttribute("searchType", searchType);
		request.getSession().setAttribute("cbs", cbs);
		request.getSession().setAttribute("bsbs", bsbs);
		response.sendRedirect(getServletContext().getContextPath()+"/search_dao_test/search_Result.jsp");
		return;	

	}
	
	
	
	
	
	
	
	
	
	private void processRequest_03(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		request.setCharacterEncoding("UTF-8");
//		String titleStr = new String(request.getParameter("title_str").getBytes("ISO-8859-1"),"UTF-8"); 
		String searchType  = request.getParameter("search_type");
		String keyWord = request.getParameter("key_word");
		String bookstateType = request.getParameter("bookstate_type");
		List<BookBean> bbs = new ArrayList<>();
		List<ClassifyBean> cbs = new ArrayList<>();
		List<BookStateBean> bsbs = new ArrayList<>();
		SearchBookDAO  sbd = new SearchBookDAO();
		bbs = sbd.filterBooksByBookState(searchStyle_map.get(searchType),keyWord,bookstateType);
		cbs = sbd.countClassifys(keyWord,searchStyle_map.get(searchType));
		bsbs = sbd.countBookStates(keyWord,searchStyle_map.get(searchType));
		sbd.connectClose();
		//暫時性輸出
		request.getSession().setAttribute("bbs", bbs);
		request.getSession().setAttribute("keyWord", keyWord);
		request.getSession().setAttribute("searchType", searchType);
		request.getSession().setAttribute("cbs", cbs);
		request.getSession().setAttribute("bsbs", bsbs);
		response.sendRedirect(getServletContext().getContextPath()+"/search_dao_test/search_Result.jsp");
		return;	
	}
	
	
	private void processRequest_04(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		request.setCharacterEncoding("UTF-8");
//		String titleStr = new String(request.getParameter("title_str").getBytes("ISO-8859-1"),"UTF-8"); 
		String searchType  = request.getParameter("search_type");
		String[] keywords = request.getParameter("key_word").split(":",3);
		String titleStr   = keywords[0];
		String penNameStr = keywords[1];
		String introStr   = keywords[2];
		String bookstateType = request.getParameter("bookstate_type");
		List<BookBean> bbs = new ArrayList<>();
		List<ClassifyBean> cbs = new ArrayList<>();
		List<BookStateBean> bsbs = new ArrayList<>();
		SearchBookDAO  sbd = new SearchBookDAO();
		bbs = sbd.filterBooksByBookStateAdvanced(keywords,bookstateType);
		cbs = sbd.countClassifysAdvanced(titleStr,penNameStr,introStr);
		bsbs = sbd.countBookStatesAdvanced(titleStr,penNameStr,introStr);
		sbd.connectClose();
		//暫時性輸出
		request.getSession().setAttribute("bbs", bbs);
		request.getSession().setAttribute("keyWord", titleStr+":"+penNameStr+":"+introStr);
		request.getSession().setAttribute("searchType", searchType);
		request.getSession().setAttribute("cbs", cbs);
		request.getSession().setAttribute("bsbs", bsbs);
		response.sendRedirect(getServletContext().getContextPath()+"/search_dao_test/search_Result.jsp");
		return;	
	}
	
	
	

	private void processRequest_05(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		request.setCharacterEncoding("UTF-8");
//		String titleStr = new String(request.getParameter("title_str").getBytes("ISO-8859-1"),"UTF-8"); 
		String searchType  = request.getParameter("search_type");		
		String keyWord = request.getParameter("key_word");
		String sortFactor = request.getParameter("sort_type");
		List<BookBean> bbs = new ArrayList<>();
		List<ClassifyBean> cbs = new ArrayList<>();
		List<BookStateBean> bsbs = new ArrayList<>();
		SearchBookDAO  sbd = new SearchBookDAO();
		bbs = sbd.sortBooksByClicks(searchStyle_map.get(searchType),keyWord,sortFactor);
		cbs = sbd.countClassifys(keyWord,searchStyle_map.get(searchType));
		bsbs = sbd.countBookStates(keyWord,searchStyle_map.get(searchType));
		sbd.connectClose();
		//暫時性輸出
		request.getSession().setAttribute("bbs", bbs);
		request.getSession().setAttribute("keyWord", keyWord);
		request.getSession().setAttribute("searchType", searchType);
		request.getSession().setAttribute("cbs", cbs);
		request.getSession().setAttribute("bsbs", bsbs);
		response.sendRedirect(getServletContext().getContextPath()+"/search_dao_test/search_Result.jsp");
		return;	
	}
	
	

	
	
	
	private void processRequest_06(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		request.setCharacterEncoding("UTF-8");
//		String titleStr = new String(request.getParameter("title_str").getBytes("ISO-8859-1"),"UTF-8"); 
		String searchType  = request.getParameter("search_type");	
		String[] keywords = request.getParameter("key_word").split(":",3);
		String titleStr   = keywords[0];
		String penNameStr = keywords[1];
		String introStr   = keywords[2];
		String sortFactor = request.getParameter("sort_type");
		List<BookBean> bbs = new ArrayList<>();
		List<ClassifyBean> cbs = new ArrayList<>();
		List<BookStateBean> bsbs = new ArrayList<>();
		SearchBookDAO  sbd = new SearchBookDAO();
		bbs = sbd.sortBooksByClicksAdvanced(keywords,sortFactor);
		cbs = sbd.countClassifysAdvanced(titleStr,penNameStr,introStr);
		bsbs = sbd.countBookStatesAdvanced(titleStr,penNameStr,introStr);
		sbd.connectClose();
		//暫時性輸出
		request.getSession().setAttribute("bbs", bbs);
		request.getSession().setAttribute("keyWord", titleStr+":"+penNameStr+":"+introStr);
		request.getSession().setAttribute("searchType", searchType);
		request.getSession().setAttribute("cbs", cbs);
		request.getSession().setAttribute("bsbs", bsbs);
		response.sendRedirect(getServletContext().getContextPath()+"/search_dao_test/search_Result.jsp");
		return;	
	}
	
	
	
	
	private void processRequest_07(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		request.setCharacterEncoding("UTF-8");
//		String titleStr = new String(request.getParameter("title_str").getBytes("ISO-8859-1"),"UTF-8"); 
		String searchType  = request.getParameter("search_type");		
		String keyWord = request.getParameter("key_word");
		String sortFactor = request.getParameter("sort_type");
		List<BookBean> bbs = new ArrayList<>();
		List<ClassifyBean> cbs = new ArrayList<>();
		List<BookStateBean> bsbs = new ArrayList<>();
		SearchBookDAO  sbd = new SearchBookDAO();
		bbs = sbd.sortBooksByPublishDate(searchStyle_map.get(searchType),keyWord,sortFactor);
		cbs = sbd.countClassifys(keyWord,searchStyle_map.get(searchType));
		bsbs = sbd.countBookStates(keyWord,searchStyle_map.get(searchType));
		sbd.connectClose();
		//暫時性輸出
		request.getSession().setAttribute("bbs", bbs);
		request.getSession().setAttribute("keyWord", keyWord);
		request.getSession().setAttribute("searchType", searchType);
		request.getSession().setAttribute("cbs", cbs);
		request.getSession().setAttribute("bsbs", bsbs);
		response.sendRedirect(getServletContext().getContextPath()+"/search_dao_test/search_Result.jsp");
		return;	
	}
	
	

	
	
	
	private void processRequest_08(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		request.setCharacterEncoding("UTF-8");
//		String titleStr = new String(request.getParameter("title_str").getBytes("ISO-8859-1"),"UTF-8"); 
		String searchType  = request.getParameter("search_type");	
		String[] keywords = request.getParameter("key_word").split(":",3);
		String titleStr   = keywords[0];
		String penNameStr = keywords[1];
		String introStr   = keywords[2];
		String sortFactor = request.getParameter("sort_type");
		List<BookBean> bbs = new ArrayList<>();
		List<ClassifyBean> cbs = new ArrayList<>();
		List<BookStateBean> bsbs = new ArrayList<>();
		SearchBookDAO  sbd = new SearchBookDAO();
		bbs = sbd.sortBooksByPublishDateAdvanced(keywords,sortFactor);
		cbs = sbd.countClassifysAdvanced(titleStr,penNameStr,introStr);
		bsbs = sbd.countBookStatesAdvanced(titleStr,penNameStr,introStr);
		sbd.connectClose();
		//暫時性輸出
		request.getSession().setAttribute("bbs", bbs);
		request.getSession().setAttribute("keyWord", titleStr+":"+penNameStr+":"+introStr);
		request.getSession().setAttribute("searchType", searchType);
		request.getSession().setAttribute("cbs", cbs);
		request.getSession().setAttribute("bsbs", bsbs);
		response.sendRedirect(getServletContext().getContextPath()+"/search_dao_test/search_Result.jsp");
		return;	
	}

	
	
	

	private void processRequest_09(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		request.setCharacterEncoding("UTF-8");
//		String titleStr = new String(request.getParameter("title_str").getBytes("ISO-8859-1"),"UTF-8"); 
		String searchType  = request.getParameter("search_type");		
		String keyWord = request.getParameter("key_word");
		String sortFactor = request.getParameter("sort_type");
		List<BookBean> bbs = new ArrayList<>();
		List<ClassifyBean> cbs = new ArrayList<>();
		List<BookStateBean> bsbs = new ArrayList<>();
		SearchBookDAO  sbd = new SearchBookDAO();
		bbs = sbd.sortBooksByTickets(searchStyle_map.get(searchType),keyWord,sortFactor);
		cbs = sbd.countClassifys(keyWord,searchStyle_map.get(searchType));
		bsbs = sbd.countBookStates(keyWord,searchStyle_map.get(searchType));
		sbd.connectClose();
		//暫時性輸出
		request.getSession().setAttribute("bbs", bbs);
		request.getSession().setAttribute("keyWord", keyWord);
		request.getSession().setAttribute("searchType", searchType);
		request.getSession().setAttribute("cbs", cbs);
		request.getSession().setAttribute("bsbs", bsbs);
		response.sendRedirect(getServletContext().getContextPath()+"/search_dao_test/search_Result.jsp");
		return;	
	}
	
	

	
	
	
	private void processRequest_10(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		request.setCharacterEncoding("UTF-8");
//		String titleStr = new String(request.getParameter("title_str").getBytes("ISO-8859-1"),"UTF-8"); 
		String searchType  = request.getParameter("search_type");	
		String[] keywords = request.getParameter("key_word").split(":",3);
		String titleStr   = keywords[0];
		String penNameStr = keywords[1];
		String introStr   = keywords[2];
		String sortFactor = request.getParameter("sort_type");
		List<BookBean> bbs = new ArrayList<>();
		List<ClassifyBean> cbs = new ArrayList<>();
		List<BookStateBean> bsbs = new ArrayList<>();
		SearchBookDAO  sbd = new SearchBookDAO();
		bbs = sbd.sortBooksByTicketsAdvanced(keywords,sortFactor);
		cbs = sbd.countClassifysAdvanced(titleStr,penNameStr,introStr);
		bsbs = sbd.countBookStatesAdvanced(titleStr,penNameStr,introStr);
		sbd.connectClose();
		//暫時性輸出
		request.getSession().setAttribute("bbs", bbs);
		request.getSession().setAttribute("keyWord", titleStr+":"+penNameStr+":"+introStr);
		request.getSession().setAttribute("searchType", searchType);
		request.getSession().setAttribute("cbs", cbs);
		request.getSession().setAttribute("bsbs", bsbs);
		response.sendRedirect(getServletContext().getContextPath()+"/search_dao_test/search_Result.jsp");
		return;	
	}
	
	
	
	

}
