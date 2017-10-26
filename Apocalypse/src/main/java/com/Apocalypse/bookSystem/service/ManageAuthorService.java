package com.Apocalypse.bookSystem.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.Apocalypse.bookSystem.dao.ManageAuthorBookDAO;
import com.Apocalypse.bookSystem.model.BookBean;
import com.Apocalypse.bookSystem.model.ChapterBean;
import com.Apocalypse.bookSystem.model.VolumeBean;
import com.Apocalypse.member.bean.AuthorBean;

/**
 * Servlet implementation class ManageAuthorService
 */

@WebServlet(urlPatterns= {"/service/ManageAuthorService","/manage_dao_test/ManageAuthorService.do"})
@MultipartConfig(maxFileSize=-1)


public class ManageAuthorService extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static String messageBase = "";
	private static String messageAdd = "";

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
	
	
	private void processSwitch(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {		
		String path = request.getHeader("referer").trim();
		String path_other = request.getHeader("referer").trim();
		String delet_path = "http://localhost:8080/Booksystem/manage_dao_test/";
		String function_type = request.getParameter("function_type");
		path = path.substring(delet_path.length()).trim();
		
		//System.out.println(path_other);
		
		System.out.println("Path:"+path);
		System.out.println("FunctionType:"+function_type);


		if (path.equals("manage_author_Login.jsp")) {
			processRequest_00(request,response);
		}else if(path.equals("manage_author_Index.jsp")&&(function_type.equals("upload_newbook"))){	
			processRequest_01(request,response);
		}else if(path.equals("manage_author_Index.jsp")&&(function_type.equals("upload_newcontent_intro"))){
			processRequest_02(request,response);	
		}else if(path.equals("manage_author_Index.jsp")&&(function_type.equals("upload_newvolume_mid"))){	
			processRequest_03(request,response);
		}else if(path.equals("manage_author_UploadVolume.jsp")&&(function_type.equals("upload_newvolume_end"))){	
			processRequest_04(request,response);	
		}else if(path.equals("manage_author_Index.jsp")&&(function_type.equals("upload_newchapter_mid"))){	
			processRequest_05(request,response);		
		}else if(path.equals("manage_author_UploadChapter.jsp")&&(function_type.equals("upload_newchapter_end"))){	
			processRequest_04(request,response);		
		}else if(path.equals("manage_author_Index.jsp")&&(function_type.equals("alter_book_mid"))){	
			processRequest_06(request,response);
		}else if(path.equals("manage_author_AlterBook.jsp")&&(function_type.equals("alter_book_end"))){	
			processRequest_07(request,response);	
		}else if(path.equals("manage_author_Index.jsp")&&(function_type.equals("alter_newcontent_intro"))){	
			processRequest_08(request,response);
		}else if(path.equals("manage_author_Index.jsp")&&(function_type.equals("alter_chapter_intro"))){	
			processRequest_11(request,response);				
		}else if(path.equals("manage_author_Index.jsp")&&(function_type.equals("alter_volume_mid"))){	
			processRequest_12(request,response);
		}else if(path.equals("manage_author_AlterVolume.jsp")&&(function_type.equals("alter_volume_end"))){	
			processRequest_13(request,response);	
		}else if(path.equals("manage_author_Index.jsp")&&(function_type.equals("alter_chapter_mid"))){	
			processRequest_14(request,response);				
		}else if(path.equals("manage_author_AlterChapter.jsp")&&(function_type.equals("alter_chapter_end"))){	
			processRequest_15(request,response);				
		}else if(path.equals("manage_author_Index.jsp")&&(function_type.equals("refresh_booktable"))){
			processRequest_09(request,response);
		}else if(path.equals("manage_author_Index.jsp")&&(function_type.equals("refresh_checkbooktable"))){
			processRequest_10(request,response);
		}
			else if(path_other.equals("http://localhost:8080/Apocalypse/login/Member.jsp")){
			processRequest_99(request,response);	
			
			
		}else {
						
			System.out.println("cant found jsp source!");
		}
		
		
	}
	private void processRequest_99(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		AuthorBean as = (AuthorBean)session.getAttribute("LoginOK_Author"); 
		int    author_Id = as.getAuthor_Id(); 
		//int    author_Id = Integer.valueOf(authorId);		
		List<BookBean> bbs = new ArrayList<>();
		List<BookBean> cbbs = new ArrayList<>();
		String pen_Name;
		ManageAuthorBookDAO mabd = new ManageAuthorBookDAO();
		bbs      = mabd.findBooksByAuthorId(author_Id);
		cbbs      = mabd.findCheckBooksByAuthorId(author_Id);
		pen_Name = mabd.findPenNameByAuthorId(author_Id);
		mabd.connectClose();
		
		messageBase = "歡迎~"+pen_Name;
		messageAdd  = ""; 
		//暫時性輸出
		request.getSession().setAttribute("penName",pen_Name);
		request.getSession().setAttribute("authorId",author_Id);
		request.getSession().setAttribute("bbs", bbs);
		request.getSession().setAttribute("cbbs", cbbs);
		request.getSession().setAttribute("messageBase",messageBase);
		request.getSession().setAttribute("messageAdd",messageAdd);	
		response.sendRedirect(getServletContext().getContextPath()+"/manage_dao_test/manage_author_Index.jsp");
		return;	
	}

	private void processRequest_00(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		request.setCharacterEncoding("UTF-8");		
		String authorId = request.getParameter("author_id"); 
		int    author_Id = Integer.valueOf(authorId);
				
		List<BookBean> bbs = new ArrayList<>();
		List<BookBean> cbbs = new ArrayList<>();
		String pen_Name;
		ManageAuthorBookDAO mabd = new ManageAuthorBookDAO();
		bbs      = mabd.findBooksByAuthorId(author_Id);
		cbbs      = mabd.findCheckBooksByAuthorId(author_Id);
		pen_Name = mabd.findPenNameByAuthorId(author_Id);
		mabd.connectClose();
		
		messageBase = "歡迎~"+pen_Name;
		messageAdd  = ""; 
		//暫時性輸出
		request.getSession().setAttribute("penName",pen_Name);
		request.getSession().setAttribute("authorId",author_Id);
		request.getSession().setAttribute("bbs", bbs);
		request.getSession().setAttribute("cbbs", cbbs);
		request.getSession().setAttribute("messageBase",messageBase);
		request.getSession().setAttribute("messageAdd",messageAdd);	
		response.sendRedirect(getServletContext().getContextPath()+"/manage_dao_test/manage_author_Index.jsp");
		return;	
	}
	
	
	private void processRequest_01(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		String authorId = request.getParameter("author_id"); 
		int    author_Id = Integer.valueOf(authorId);
		Long   timeDot =  new Date().getTime();
		java.sql.Date publishDate = new java.sql.Date(timeDot);
		String setSurfacePlotName = "A"+authorId+"T"+String.valueOf(timeDot);		
		String defaultPicPath = "D:/Users/PC-26/git1/Apocalypse/src/main/webapp/fakedata_resources/default.jpg";

		if (request.getPart("surface_plot").getSize() != 0) {
			Part filePart1 = request.getPart("surface_plot");
	//        String header = filePart1.getHeader("Content-Disposition");
	//        String filename = header.substring(header.indexOf("filename=\"") + 10, header.lastIndexOf("\""));
	        InputStream in = filePart1.getInputStream();	
	        OutputStream out = new FileOutputStream("D:/Users/PC-26/git1/Apocalypse/src/main/webapp/fakedata_resources/" + setSurfacePlotName +".jpg");		
	        byte[] buffer = new byte[1024];
	        int length = -1;
	        while ((length = in.read(buffer)) != -1) {
	            out.write(buffer, 0, length);
	        }
	        in.close();
	        out.close();
		}else{
			File file = new File(defaultPicPath);
			InputStream in = new FileInputStream(file);
			OutputStream out = new FileOutputStream("D:/Users/PC-26/git1/Apocalypse/src/main/webapp/fakedata_resources/" + setSurfacePlotName +".jpg");		
	        byte[] buffer = new byte[1024];
	        int length = -1;
	        while ((length = in.read(buffer)) != -1) {
	            out.write(buffer, 0, length);
	        }
	        in.close();
	        out.close();
		}	


		
        BookBean bbUp = new BookBean();
        bbUp.setTitle(request.getParameter("title"));
        bbUp.setAuthorId(author_Id);
        bbUp.setBookState("待審查");
        bbUp.setPublishDate(publishDate);
        bbUp.setIntro(request.getParameter("intro"));
        bbUp.setClassify(request.getParameter("classity"));
        bbUp.setSurface_Plot_Name(setSurfacePlotName);
		

        
        List<BookBean> bbs = new ArrayList<>();
        List<BookBean> cbbs = new ArrayList<>();
		String pen_Name;
		ManageAuthorBookDAO mabd = new ManageAuthorBookDAO();

		int result = mabd.insertBookAsAuthor(bbUp);
		bbs      = mabd.findBooksByAuthorId(author_Id);
		cbbs      = mabd.findCheckBooksByAuthorId(author_Id);
		pen_Name = mabd.findPenNameByAuthorId(author_Id);
		mabd.connectClose();
					
		if (result == 1) {
			messageBase = messageBase + messageAdd;
			messageAdd = "\r\n"+"已經成功提出新書申請，請靜候審查結果"; 
		}else{
			messageBase = messageBase + messageAdd;
			messageAdd = "\r\n"+"提出新書申請失敗，請確認輸入資訊內容";
		}		
		//暫時性輸出
		request.getSession().setAttribute("penName",pen_Name);
		request.getSession().setAttribute("authorId",author_Id);
		request.getSession().setAttribute("bbs", bbs);
		request.getSession().setAttribute("cbbs", cbbs);
		request.getSession().setAttribute("messageBase",messageBase);
		request.getSession().setAttribute("messageAdd",messageAdd);	
		response.sendRedirect(getServletContext().getContextPath()+"/manage_dao_test/manage_author_Index.jsp");
		return;	
	}	
	
	
	private void processRequest_02(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		request.setCharacterEncoding("UTF-8");
		String authorId = request.getParameter("author_id");
		int    author_Id = Integer.valueOf(authorId);
		String bookId = request.getParameter("book_id");
		int    book_Id = Integer.valueOf(bookId);

		int	    max_VolumeNumber;
		String  volume_Title    ;
		int	    max_ChapterNumber;	
		String  chapter_Title;
		
		ManageAuthorBookDAO mabd = new ManageAuthorBookDAO();
		max_VolumeNumber = mabd.findMaxVolumeNumber(book_Id);
		if(max_VolumeNumber == 0) {
			System.out.println("本作品目前無任何章節內容");
			volume_Title=null;
			max_ChapterNumber=0;
			chapter_Title=null;
			
		}else {		
		volume_Title     = mabd.findVolumeTitle(book_Id,max_VolumeNumber);
		
		max_ChapterNumber = mabd.findMaxChapterNumber(book_Id,max_VolumeNumber);
		chapter_Title     = mabd.findChapterTitle(book_Id,max_VolumeNumber,max_ChapterNumber);
		
		System.out.println("本作品目前連載至:");
		System.out.println("第"+max_VolumeNumber+"卷"+", 卷名:"+volume_Title);
		System.out.println("第"+max_ChapterNumber+"章"+", 章名:"+chapter_Title);
		}
		
		String All_in = String.valueOf(max_VolumeNumber)+"|"+volume_Title+"|"+String.valueOf(max_ChapterNumber)+"|"+chapter_Title;
		mabd.connectClose();
				
		response.setContentType("text/plain; charset=UTF-8");		
		PrintWriter out = response.getWriter();
		out.println(All_in);
		out.flush();		
		return;	
	}	
	
	
	private void processRequest_03(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		request.setCharacterEncoding("UTF-8");
		String authorId = request.getParameter("author_id"); 
		int    author_Id = Integer.valueOf(authorId);
		String bookId = request.getParameter("book_id");
		int    book_Id = Integer.valueOf(bookId);
		String volumeId = request.getParameter("volume_number");
		int    volume_Id = Integer.valueOf(volumeId);
		String chapterId = request.getParameter("chapter_number");
		int    chapter_Id = Integer.valueOf(chapterId);
		
		//暫時性輸出
		request.getSession().setAttribute("authorId",author_Id);
		request.getSession().setAttribute("edit_bookId",book_Id);
		request.getSession().setAttribute("edit_volumeId",volume_Id);
		request.getSession().setAttribute("edit_chapterId",chapter_Id);
		response.sendRedirect(getServletContext().getContextPath()+"/manage_dao_test/manage_author_UploadVolume.jsp");
		return;	
	}
	
	
	private void processRequest_04(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		request.setCharacterEncoding("UTF-8");
		String authorId = request.getParameter("author_id"); 
		int    author_Id = Integer.valueOf(authorId);
		String pen_Name;
		List<BookBean> bbs = new ArrayList<>();
		List<BookBean> cbbs = new ArrayList<>();
		
		//準備寫入的資料的volume、chapter bean
		String bookId = request.getParameter("book_id");
		int    book_Id = Integer.valueOf(bookId);
		String volumeId = request.getParameter("volume_id");
		int    volume_Id = Integer.valueOf(volumeId);
		String volume_Title = request.getParameter("volume_title");		
		String chapterId = request.getParameter("chapter_id");
		int    chapter_Id = Integer.valueOf(chapterId);
		String chapter_Title = request.getParameter("chapter_title");
		String chapter_Content = request.getParameter("chapter_content");
		String content_Name    = "A"+authorId+"B"+bookId+"V"+volumeId+"C"+chapterId;
		int    chapter_Price = 5;
		
		VolumeBean vbUp = new VolumeBean();
		ChapterBean cbUp = new ChapterBean();
		vbUp.setBookId(book_Id);
		vbUp.setVolumeId(volume_Id);
		vbUp.setVolumeTitle(volume_Title);
		cbUp.setBookId(book_Id);
		cbUp.setVolumeId(volume_Id);
		cbUp.setChapterId(chapter_Id);
		cbUp.setChapterTitle(chapter_Title);
		cbUp.setChapterContent(chapter_Content);
		cbUp.setContentName(content_Name);
		cbUp.setPrice(chapter_Price);;
		
		//交易區塊(開始)
		ManageAuthorBookDAO mabd_tran = new ManageAuthorBookDAO();
		try {
			mabd_tran.cancelAutoCommit();		
			mabd_tran.deleteCheckChapterAsAuthor(cbUp);
			mabd_tran.deleteCheckVolumeAsAuthor(vbUp);
			mabd_tran.insertVolumeAsAuthor(vbUp);
			mabd_tran.insertChapterAsAuthor(cbUp);
			mabd_tran.transactionCommit();
			messageBase = messageBase + messageAdd;
			messageAdd = "\r\n"+"已經成功提出新章節申請，請靜候審查結果";
		} catch(Exception e) {	
			mabd_tran.transactionRollback();
			messageBase = messageBase + messageAdd;
			messageAdd = "\r\n"+"新章節申請失敗，請洽系統管理員";
		} finally {
			mabd_tran.connectClose();	
		}
		//交易區塊(結束)

		//背景資訊區塊(開始)
		ManageAuthorBookDAO mabd = new ManageAuthorBookDAO();
		bbs      = mabd.findBooksByAuthorId(author_Id);
		cbbs      = mabd.findCheckBooksByAuthorId(author_Id);
		pen_Name = mabd.findPenNameByAuthorId(author_Id);
		mabd.connectClose();
		//背景資訊區塊(結束)
		//暫時性輸出
		request.getSession().setAttribute("penName",pen_Name);
		request.getSession().setAttribute("authorId",author_Id);
		request.getSession().setAttribute("bbs", bbs);
		request.getSession().setAttribute("cbbs", cbbs);
		request.getSession().setAttribute("messageBase",messageBase);
		request.getSession().setAttribute("messageAdd",messageAdd);
		response.sendRedirect(getServletContext().getContextPath()+"/manage_dao_test/manage_author_Index.jsp");
		return;	
	}
	

	private void processRequest_05(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		request.setCharacterEncoding("UTF-8");
		String authorId = request.getParameter("author_id"); 
		int    author_Id = Integer.valueOf(authorId);
		String bookId = request.getParameter("book_id");
		int    book_Id = Integer.valueOf(bookId);
		String volumeId = request.getParameter("volume_number");
		int    volume_Id = Integer.valueOf(volumeId);
		String volume_Title = request.getParameter("volume_title");
		String chapterId = request.getParameter("chapter_number");
		int    chapter_Id = Integer.valueOf(chapterId);
		//暫時性輸出
		request.getSession().setAttribute("authorId",author_Id);
		request.getSession().setAttribute("edit_bookId",book_Id);
		request.getSession().setAttribute("edit_volumeId",volume_Id);
		request.getSession().setAttribute("edit_volumeTitle",volume_Title);
		request.getSession().setAttribute("edit_chapterId",chapter_Id);
		response.sendRedirect(getServletContext().getContextPath()+"/manage_dao_test/manage_author_UploadChapter.jsp");
		return;	
	}
	
	
	private void processRequest_06(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		request.setCharacterEncoding("UTF-8");
		String authorId = request.getParameter("author_id"); 
		int    author_Id = Integer.valueOf(authorId);
		String bookId = request.getParameter("edit_bookid"); 
		int    book_Id = Integer.valueOf(bookId);

		BookBean bb_edit = new BookBean();
			
		ManageAuthorBookDAO mabd = new ManageAuthorBookDAO();
		bb_edit      = mabd.findBookByAuthorIdBookId(author_Id,book_Id);
		mabd.connectClose();
		
		//暫時性輸出
		request.getSession().setAttribute("bb_edit",bb_edit);
		response.sendRedirect(getServletContext().getContextPath()+"/manage_dao_test/manage_author_AlterBook.jsp");
		return;	
	}
	

	private void processRequest_07(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		String authorId = request.getParameter("author_id"); 
		int    author_Id = Integer.valueOf(authorId);
		String bookId = request.getParameter("book_id"); 
		int    book_Id = Integer.valueOf(bookId);
		
		String setSurfacePlotName = request.getParameter("surface_plot_name");		
		String defaultPicPath = "D:/_JSP/workspace/Booksystem/src/main/webapp/edit_bookcover_resources/default.jpg";

		if (request.getPart("edit_surface_plot").getSize() != 0) {
			Part filePart1 = request.getPart("edit_surface_plot");
	        InputStream in = filePart1.getInputStream();	
	        OutputStream out = new FileOutputStream("D:/_JSP/workspace/Booksystem/src/main/webapp/edit_bookcover_resources/" + setSurfacePlotName +".jpg");		
	        byte[] buffer = new byte[1024];
	        int length = -1;
	        while ((length = in.read(buffer)) != -1) {
	            out.write(buffer, 0, length);
	        }
	        in.close();
	        out.close();
		}else{
			File file = new File(defaultPicPath);
			InputStream in = new FileInputStream(file);
			OutputStream out = new FileOutputStream("D:/_JSP/workspace/Booksystem/src/main/webapp/edit_bookcover_resources/" + setSurfacePlotName +".jpg");		
	        byte[] buffer = new byte[1024];
	        int length = -1;
	        while ((length = in.read(buffer)) != -1) {
	            out.write(buffer, 0, length);
	        }
	        in.close();
	        out.close();
		}	

        BookBean edit_bbUp = new BookBean();
        edit_bbUp.setBookId(book_Id);
        edit_bbUp.setTitle(request.getParameter("title"));
        edit_bbUp.setAuthorId(author_Id);
        edit_bbUp.setIntro(request.getParameter("intro"));
        edit_bbUp.setClassify(request.getParameter("classity"));
        edit_bbUp.setSurface_Plot_Name(setSurfacePlotName);
		
        List<BookBean> bbs = new ArrayList<>();
        List<BookBean> cbbs = new ArrayList<>();
		String pen_Name;
		ManageAuthorBookDAO mabd = new ManageAuthorBookDAO();

		mabd.deleteEditBookAsAuthor(edit_bbUp);
		int result = mabd.alterBookAsAuthor(edit_bbUp);
		bbs      = mabd.findBooksByAuthorId(author_Id);
		cbbs      = mabd.findCheckBooksByAuthorId(author_Id);
		pen_Name = mabd.findPenNameByAuthorId(author_Id);
		mabd.connectClose();
					
		if (result == 1) {
			messageBase = messageBase + messageAdd;
			messageAdd = "\r\n"+"上架書本資料修改申請成功，請靜候審查結果"; 
		}else{
			messageBase = messageBase + messageAdd;
			messageAdd = "\r\n"+"上架書本資料修改申請失敗，請洽系統管理員";
		}		
		//暫時性輸出
		request.getSession().setAttribute("penName",pen_Name);
		request.getSession().setAttribute("authorId",author_Id);
		request.getSession().setAttribute("bbs", bbs);
		request.getSession().setAttribute("cbbs", cbbs);
		request.getSession().setAttribute("messageBase",messageBase);
		request.getSession().setAttribute("messageAdd",messageAdd);	
		response.sendRedirect(getServletContext().getContextPath()+"/manage_dao_test/manage_author_Index.jsp");
		return;	
	}
	

	private void processRequest_08(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		request.setCharacterEncoding("UTF-8");
		String authorId = request.getParameter("author_id");
		int    author_Id = Integer.valueOf(authorId);
		String bookId = request.getParameter("book_id");
		int    book_Id = Integer.valueOf(bookId);

		int	    max_VolumeNumber;
		String  volume_Title    ;
		int	    max_ChapterNumber;	
		String  chapter_Title;
		
		ManageAuthorBookDAO mabd = new ManageAuthorBookDAO();
		max_VolumeNumber = mabd.findMaxVolumeNumber(book_Id);
		if(max_VolumeNumber == 0) {
			System.out.println("本作品目前無任何章節內容");
			volume_Title=null;
			max_ChapterNumber=0;
			chapter_Title=null;
			
		}else {		
		volume_Title     = mabd.findVolumeTitle(book_Id,max_VolumeNumber);
		
		max_ChapterNumber = mabd.findMaxChapterNumber(book_Id,max_VolumeNumber);
		chapter_Title     = mabd.findChapterTitle(book_Id,max_VolumeNumber,max_ChapterNumber);
		
		System.out.println("本作品目前連載至:");
		System.out.println("第"+max_VolumeNumber+"卷"+", 卷名:"+volume_Title);
		System.out.println("第"+max_ChapterNumber+"章"+", 章名:"+chapter_Title);
		}
		String All_in = String.valueOf(max_VolumeNumber)+"|"+volume_Title+"|"+String.valueOf(max_ChapterNumber)+"|"+chapter_Title;
		mabd.connectClose();
		
		response.setContentType("text/plain; charset=UTF-8");		
		PrintWriter out = response.getWriter();
		out.println(All_in);
		out.flush();		
		return;	
	}
	
	
	private void processRequest_11(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		request.setCharacterEncoding("UTF-8");
		String authorId = request.getParameter("author_id"); 
		int    author_Id = Integer.valueOf(authorId);
		String bookId = request.getParameter("book_id"); 
		int    book_Id = Integer.valueOf(bookId);
		String volumeId = request.getParameter("volume_id"); 
		int    volume_Id = Integer.valueOf(volumeId);
		int maxChapterNumber;
		
		ManageAuthorBookDAO mabd = new ManageAuthorBookDAO();
		maxChapterNumber = mabd.findMaxChapterNumber(book_Id,volume_Id);
		String All_in = String.valueOf(maxChapterNumber);
		mabd.connectClose();
		
		response.setContentType("text/plain; charset=UTF-8");		
		PrintWriter out = response.getWriter();
		out.println(All_in);
		out.flush();
		return;

	}
	
	
	private void processRequest_12(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		request.setCharacterEncoding("UTF-8");
		String authorId = request.getParameter("author_id");
		int    author_Id = Integer.valueOf(authorId);
		String bookId = request.getParameter("book_id");
		int    book_Id = Integer.valueOf(bookId);
		String volumeId = request.getParameter("alter_volumeId");
		int    volume_Id = Integer.valueOf(volumeId);
		String volume_Title;

		ManageAuthorBookDAO mabd = new ManageAuthorBookDAO();
		volume_Title     = mabd.findVolumeTitle(book_Id,volume_Id);
		mabd.connectClose();
		
		request.getSession().setAttribute("authorId",author_Id);
		request.getSession().setAttribute("bookId", book_Id);
		request.getSession().setAttribute("volumeId", volume_Id);
		request.getSession().setAttribute("volumeTitle", volume_Title);
		response.sendRedirect(getServletContext().getContextPath()+"/manage_dao_test/manage_author_AlterVolume.jsp");
		return;	
	
	}	
	
	
	private void processRequest_14(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		request.setCharacterEncoding("UTF-8");
		String authorId = request.getParameter("author_id");
		int    author_Id = Integer.valueOf(authorId);
		String bookId = request.getParameter("book_id");
		int    book_Id = Integer.valueOf(bookId);
		String volumeId = request.getParameter("alter_volumeId");
		int    volume_Id = Integer.valueOf(volumeId);
		String chapterId = request.getParameter("alter_chapterId");
		int    chapter_Id = Integer.valueOf(chapterId);
		
		String volume_Title;
		String chapter_Title;
		String chapter_Content;
		
		ManageAuthorBookDAO mabd = new ManageAuthorBookDAO();
		volume_Title     = mabd.findVolumeTitle(book_Id,volume_Id);
		chapter_Title     = mabd.findChapterTitle(book_Id,volume_Id,chapter_Id);
		chapter_Content   = mabd.findChapterContent(book_Id,volume_Id,chapter_Id);
		mabd.connectClose();
		
		request.getSession().setAttribute("authorId",author_Id);
		request.getSession().setAttribute("bookId",book_Id);
		request.getSession().setAttribute("volumeId",volume_Id);
		request.getSession().setAttribute("volumeTitle",volume_Title);
		request.getSession().setAttribute("chapterId",chapter_Id);
		request.getSession().setAttribute("chapterTitle",chapter_Title);
		request.getSession().setAttribute("chapterContent",chapter_Content);
		response.sendRedirect(getServletContext().getContextPath()+"/manage_dao_test/manage_author_AlterChapter.jsp");
		return;	
	
	}
	
	
	
	private void processRequest_13(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		String authorId = request.getParameter("author_id"); 
		int    author_Id = Integer.valueOf(authorId);
		String bookId = request.getParameter("book_id"); 
		int    book_Id = Integer.valueOf(bookId);
		String volumeId = request.getParameter("volume_id"); 
		int    volume_Id = Integer.valueOf(volumeId);
		String volumeTitle = request.getParameter("volume_title");
		
		VolumeBean edit_vb = new VolumeBean();
		edit_vb.setBookId(book_Id);
		edit_vb.setVolumeId(volume_Id);
		edit_vb.setVolumeTitle(volumeTitle);

        List<BookBean> bbs = new ArrayList<>();
        List<BookBean> cbbs = new ArrayList<>();
		String pen_Name;
		ManageAuthorBookDAO mabd = new ManageAuthorBookDAO();
		
		mabd.deleteEditVolumeAsAuthor(edit_vb);
		int result = mabd.alterVolumeAsAuthor(edit_vb);
		bbs      = mabd.findBooksByAuthorId(author_Id);
		cbbs      = mabd.findCheckBooksByAuthorId(author_Id);
		pen_Name = mabd.findPenNameByAuthorId(author_Id);
		mabd.connectClose();
					
		if (result == 1) {
			messageBase = messageBase + messageAdd;
			messageAdd = "\r\n"+"上架書本之第"+volumeId+"卷標題修改申請成功，請靜候審查結果"; 
		}else{
			messageBase = messageBase + messageAdd;
			messageAdd = "\r\n"+"上架書本之第"+volumeId+"卷標題修改申請失敗，請洽系統管理員";
		}		
		//暫時性輸出
		request.getSession().setAttribute("penName",pen_Name);
		request.getSession().setAttribute("authorId",author_Id);
		request.getSession().setAttribute("bbs", bbs);
		request.getSession().setAttribute("cbbs", cbbs);
		request.getSession().setAttribute("messageBase",messageBase);
		request.getSession().setAttribute("messageAdd",messageAdd);	
		response.sendRedirect(getServletContext().getContextPath()+"/manage_dao_test/manage_author_Index.jsp");
		return;	
	}
	
	
	private void processRequest_15(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		String authorId = request.getParameter("author_id"); 
		int    author_Id = Integer.valueOf(authorId);
		String bookId = request.getParameter("book_id"); 
		int    book_Id = Integer.valueOf(bookId);
		String volumeId = request.getParameter("volume_id"); 
		int    volume_Id = Integer.valueOf(volumeId);
		String volumeTitle = request.getParameter("volume_title");
		String chapterId = request.getParameter("chapter_id"); 
		int    chapter_Id = Integer.valueOf(chapterId);
		String chapterTitle = request.getParameter("chapter_title");
		String chapterContent = request.getParameter("chapter_content");
		
		ChapterBean edit_cb = new ChapterBean();
		edit_cb.setBookId(book_Id);
		edit_cb.setVolumeId(volume_Id);
		edit_cb.setChapterId(chapter_Id);
		edit_cb.setChapterTitle(chapterTitle);
		edit_cb.setChapterContent(chapterContent);

        List<BookBean> bbs = new ArrayList<>();
        List<BookBean> cbbs = new ArrayList<>();
		String pen_Name;
		ManageAuthorBookDAO mabd = new ManageAuthorBookDAO();
		mabd.deleteEditChapterAsAuthor(edit_cb);
		int result = mabd.alterChapterAsAuthor(edit_cb);
		bbs      = mabd.findBooksByAuthorId(author_Id);
		cbbs      = mabd.findCheckBooksByAuthorId(author_Id);
		pen_Name = mabd.findPenNameByAuthorId(author_Id);
		mabd.connectClose();
					
		if (result == 1) {
			messageBase = messageBase + messageAdd;
			messageAdd = "\r\n"+"上架書本之第"+volumeId+"卷--第"+chapterId+"章資料修改申請成功，請靜候審查結果"; 
		}else{
			messageBase = messageBase + messageAdd;
			messageAdd = "\r\n"+"上架書本之第"+volumeId+"卷--第"+chapterId+"章資料修改申請失敗，請洽系統管理員";
		}		
		//暫時性輸出
		request.getSession().setAttribute("penName",pen_Name);
		request.getSession().setAttribute("authorId",author_Id);
		request.getSession().setAttribute("bbs", bbs);
		request.getSession().setAttribute("cbbs", cbbs);
		request.getSession().setAttribute("messageBase",messageBase);
		request.getSession().setAttribute("messageAdd",messageAdd);	
		response.sendRedirect(getServletContext().getContextPath()+"/manage_dao_test/manage_author_Index.jsp");
		return;	
	}
	

	private void processRequest_09(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		request.setCharacterEncoding("UTF-8");
		String authorId = request.getParameter("author_id"); 
		int    author_Id = Integer.valueOf(authorId);		
		List<BookBean> bbs = new ArrayList<>();
		List<BookBean> cbbs = new ArrayList<>();
		String pen_Name;
		ManageAuthorBookDAO mabd = new ManageAuthorBookDAO();
		bbs      = mabd.findBooksByAuthorId(author_Id);
		cbbs      = mabd.findCheckBooksByAuthorId(author_Id);
		pen_Name = mabd.findPenNameByAuthorId(author_Id);
		mabd.connectClose();
		
		messageBase = messageBase + messageAdd;
		messageAdd  = "\r\n"+"提出更新著作書本清單";
		//暫時性輸出
		request.getSession().setAttribute("penName",pen_Name);
		request.getSession().setAttribute("authorId",author_Id);
		request.getSession().setAttribute("bbs", bbs);
		request.getSession().setAttribute("cbbs", cbbs);
		request.getSession().setAttribute("messageBase",messageBase);
		request.getSession().setAttribute("messageAdd",messageAdd);	
		response.sendRedirect(getServletContext().getContextPath()+"/manage_dao_test/manage_author_Index.jsp");
		return;	
	}
	
	
	private void processRequest_10(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		request.setCharacterEncoding("UTF-8");
		String authorId = request.getParameter("author_id"); 
		int    author_Id = Integer.valueOf(authorId);		
		List<BookBean> bbs = new ArrayList<>();
		List<BookBean> cbbs = new ArrayList<>();
		String pen_Name;
		ManageAuthorBookDAO mabd = new ManageAuthorBookDAO();
		bbs      = mabd.findCheckBooksByAuthorId(author_Id);
		cbbs      = mabd.findCheckBooksByAuthorId(author_Id);
		pen_Name = mabd.findPenNameByAuthorId(author_Id);
		mabd.connectClose();
		
		messageBase = messageBase + messageAdd;
		messageAdd  = "\r\n"+"提出更新待審書本清單";
		//暫時性輸出
		request.getSession().setAttribute("penName",pen_Name);
		request.getSession().setAttribute("authorId",author_Id);
		request.getSession().setAttribute("bbs", bbs);
		request.getSession().setAttribute("cbbs", cbbs);
		request.getSession().setAttribute("messageBase",messageBase);
		request.getSession().setAttribute("messageAdd",messageAdd);	
		response.sendRedirect(getServletContext().getContextPath()+"/manage_dao_test/manage_author_Index.jsp");
		return;	
	}
		
	
}
