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

import com.Apocalypse.bookSystem.dao.ProductBookDAO;
import com.Apocalypse.bookSystem.model.BookBean;
import com.Apocalypse.bookSystem.model.ChapterBean;
import com.Apocalypse.bookSystem.model.CommentsBean;
import com.Apocalypse.bookSystem.model.StarsBean;
import com.Apocalypse.bookSystem.model.SubcommentsBean;
import com.Apocalypse.bookSystem.model.VolumeBean;

/**
 * Servlet implementation class BookSearchService
 */
@WebServlet(
	urlPatterns= {"/service/ProductBookService","/product_dao_test/ProductBookService.do"}			
)

public class ProductBookService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processSwitch(request,response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processSwitch(request,response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private void processSwitch(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String path = request.getHeader("referer").trim();
		String delet_path = "http://localhost:8080/Booksystem/product_dao_test/";
		path = path.substring(delet_path.length()).trim();
//		System.out.println(path);
		if (path.equals("findBookByBookId_InputID.jsp")) {
			processRequest_01(request,response);	
		}else if (path.equals("findBooksAll_Middle.jsp")){
			processRequest_02(request,response);		
		}else if(path.equals("findStarPointsByBookId_InputID.jsp")) {
			processRequest_03(request,response);
		}else if(path.equals("findCommentsByBookId_InputID.jsp")) {	
			processRequest_04(request,response);
		}else if(path.equals("findSubcommentsByBookId_InputID.jsp")) {		
			processRequest_05(request,response);
		}else if(path.equals("findVolumesByBookId_InputID.jsp")) {	
			processRequest_06(request,response);
		}else if(path.equals("findChaptersByBookId_InputID.jsp")) {		
			processRequest_07(request,response);
		}else {
			System.out.println("cant found jsp source!");
		}
		
		
	}
	
	
	private void processRequest_01(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		request.setCharacterEncoding("UTF-8");
		String bookId = request.getParameter("book_id"); 
		int    book_Id = Integer.valueOf(bookId);		
		BookBean bb = new BookBean();
		ProductBookDAO pbd = new ProductBookDAO();
		bb = pbd.findBookByBookId(book_Id);
		pbd.connectClose();
		//暫時性輸出
		String result = bb.toString();
		request.getSession().setAttribute("result", result);
		request.getSession().setAttribute("bb", bb);
		response.sendRedirect(getServletContext().getContextPath()+"/product_dao_test/findBookByBookId_ShowResult.jsp");
		return;	
	}
	
	private void processRequest_02(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		request.setCharacterEncoding("UTF-8");		
		List<BookBean> bbs = new ArrayList<>();
		ProductBookDAO pbd = new ProductBookDAO();
		bbs = pbd.findBooksAll();
		pbd.connectClose();
		//暫時性輸出
		request.getSession().setAttribute("bbs", bbs);
		response.sendRedirect(getServletContext().getContextPath()+"/product_dao_test/findBooksAll_ShowResult.jsp");
		return;	
	}
	
	private void processRequest_03(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		request.setCharacterEncoding("UTF-8");
		String bookId = request.getParameter("book_id");
		int    book_Id = Integer.valueOf(bookId);
		List<StarsBean> sbs = new ArrayList<>();
		ProductBookDAO pbd = new ProductBookDAO();
		sbs = pbd.findStarPointsByBookId(book_Id);
		pbd.connectClose();
		//暫時性輸出
		request.getSession().setAttribute("sbs", sbs);
		response.sendRedirect(getServletContext().getContextPath()+"/product_dao_test/findStarPointsByBookId_ShowResult.jsp");
		return;	
	}
	
	
	
	private void processRequest_04(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		request.setCharacterEncoding("UTF-8");
		String bookId = request.getParameter("book_id");
		int    book_Id = Integer.valueOf(bookId);
		List<CommentsBean> cbs = new ArrayList<>();
		ProductBookDAO pbd = new ProductBookDAO();
		cbs = pbd.findCommentsByBookId(book_Id);
		pbd.connectClose();
		//暫時性輸出
		request.getSession().setAttribute("cbs", cbs);
		response.sendRedirect(getServletContext().getContextPath()+"/product_dao_test/findCommentsByBookId_ShowResult.jsp");
		return;	
	}
	
	private void processRequest_05(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		request.setCharacterEncoding("UTF-8");
		String bookId = request.getParameter("book_id");
		int    book_Id = Integer.valueOf(bookId);
		List<SubcommentsBean> scbs = new ArrayList<>();
		ProductBookDAO pbd = new ProductBookDAO();
		scbs = pbd.findSubcommentsByBookId(book_Id);
		pbd.connectClose();
		//暫時性輸出
		request.getSession().setAttribute("scbs", scbs);
		response.sendRedirect(getServletContext().getContextPath()+"/product_dao_test/findSubcommentsByBookId_ShowResult.jsp");
		return;	
	}
	
	
	private void processRequest_06(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		request.setCharacterEncoding("UTF-8");
		String bookId = request.getParameter("book_id");
		int    book_Id = Integer.valueOf(bookId);
		List<VolumeBean> vbs = new ArrayList<>();
		ProductBookDAO pbd = new ProductBookDAO();
		vbs = pbd.findVolumesByBookId(book_Id);
		pbd.connectClose();
		//暫時性輸出
		request.getSession().setAttribute("vbs", vbs);
		response.sendRedirect(getServletContext().getContextPath()+"/product_dao_test/findVolumesByBookId_ShowResult.jsp");
		return;	
	}
	
	
	private void processRequest_07(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		request.setCharacterEncoding("UTF-8");
		String bookId = request.getParameter("book_id");
		int    book_Id = Integer.valueOf(bookId);
		List<ChapterBean> cbs = new ArrayList<>();
		ProductBookDAO pbd = new ProductBookDAO();
		cbs = pbd.findChaptersByBookId(book_Id);
		pbd.connectClose();
		//暫時性輸出
		request.getSession().setAttribute("cbs", cbs);
		response.sendRedirect(getServletContext().getContextPath()+"/product_dao_test/findChaptersByBookId_ShowResult.jsp");
		return;	
	}
	
	
	
	
	
	

}
