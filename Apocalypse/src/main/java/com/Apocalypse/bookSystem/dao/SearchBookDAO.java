package com.Apocalypse.bookSystem.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.Apocalypse.bookSystem.model.BookBean;
import com.Apocalypse.bookSystem.model.BookStateBean;
import com.Apocalypse.bookSystem.model.ClassifyBean;
import com.Apocalypse.bookSystem.util.GlobalService;

public class SearchBookDAO {
	Connection conn;
	DataSource ds = null;
	
	{
		try {
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup(GlobalService.JNDI_DB_NAME); 
			conn = ds.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<BookBean> findBooksByTitle(String title_str) throws SQLException {
		String sql =" SELECT b.book_Id, b.title, b.author_Id, b.book_state, b.publish_Date,"+
		    		" b.intro, b.classify, b.clicks, b.tickets, b.surface_Plot, b.surface_Plot_Name, "+
		    		" a.pen_Name "+
		    		" FROM book b JOIN author a ON (b.author_Id = a.author_Id) "+
                    " WHERE b.title LIKE ? ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,"%"+title_str+"%");
		ResultSet rs = pstmt.executeQuery();
		List<BookBean> bbs = new ArrayList<>();
		while (rs.next()) {
			BookBean bb = new BookBean();
			bb.setBookId(rs.getInt("b.book_Id"));
			bb.setTitle(rs.getString("b.title"));
			bb.setAuthorId(rs.getInt("b.author_Id"));
			bb.setBookState(rs.getString("b.book_state"));
			bb.setPublishDate(rs.getDate("b.publish_Date"));
			bb.setIntro(rs.getString("b.intro"));
			bb.setClassify(rs.getString("b.classify"));
			bb.setClicks(rs.getInt("b.clicks"));
			bb.setTickets(rs.getInt("b.tickets"));
			bb.setSurfacePlot(rs.getBlob("b.surface_Plot"));
			bb.setSurface_Plot_Name(rs.getString("b.surface_Plot_Name"));
			bb.setPenName(rs.getString("a.pen_Name"));
			bbs.add(bb);
		}
		System.out.println(".................");
		rs.close();
		pstmt.close();
		
		if(bbs.isEmpty()) { 
			return null;
		}else {
			return bbs;
		}
	}
	
	
	
	public List<BookBean> findBooksByPenName(String penName_Str) throws SQLException {
		String sql =" SELECT b.book_Id, b.title, b.author_Id, b.book_state, b.publish_Date,"+
		    		" b.intro, b.classify, b.clicks, b.tickets, b.surface_Plot, b.surface_Plot_Name, "+
		    		" a.pen_Name "+
		    		" FROM book b JOIN author a ON (b.author_Id = a.author_Id) "+
                    " WHERE a.pen_Name LIKE ? ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,"%"+penName_Str+"%");
		ResultSet rs = pstmt.executeQuery();
		List<BookBean> bbs = new ArrayList<>();
		while (rs.next()) {
			BookBean bb = new BookBean();
			bb.setBookId(rs.getInt("b.book_Id"));
			bb.setTitle(rs.getString("b.title"));
			bb.setAuthorId(rs.getInt("b.author_Id"));
			bb.setBookState(rs.getString("b.book_state"));
			bb.setPublishDate(rs.getDate("b.publish_Date"));
			bb.setIntro(rs.getString("b.intro"));
			bb.setClassify(rs.getString("b.classify"));
			bb.setClicks(rs.getInt("b.clicks"));
			bb.setTickets(rs.getInt("b.tickets"));
			bb.setSurfacePlot(rs.getBlob("b.surface_Plot"));
			bb.setSurface_Plot_Name(rs.getString("b.surface_Plot_Name"));
			bb.setPenName(rs.getString("a.pen_Name"));
			bbs.add(bb);
		}
		
		rs.close();
		pstmt.close();
		
		if(bbs.isEmpty()) { 
			return null;
		}else {
			return bbs;
		}
	}
	
	
	
	public List<BookBean> findBooksByIntro(String intro_str) throws SQLException {
		String sql =" SELECT b.book_Id, b.title, b.author_Id, b.book_state, b.publish_Date,"+
		    		" b.intro, b.classify, b.clicks, b.tickets, b.surface_Plot, b.surface_Plot_Name, "+
		    		" a.pen_Name "+
		    		" FROM book b JOIN author a ON (b.author_Id = a.author_Id) "+
                    " WHERE b.intro LIKE ? ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,"%"+intro_str+"%");
		ResultSet rs = pstmt.executeQuery();
		List<BookBean> bbs = new ArrayList<>();
		while (rs.next()) {
			BookBean bb = new BookBean();
			bb.setBookId(rs.getInt("b.book_Id"));
			bb.setTitle(rs.getString("b.title"));
			bb.setAuthorId(rs.getInt("b.author_Id"));
			bb.setBookState(rs.getString("b.book_state"));
			bb.setPublishDate(rs.getDate("b.publish_Date"));
			bb.setIntro(rs.getString("b.intro"));
			bb.setClassify(rs.getString("b.classify"));
			bb.setClicks(rs.getInt("b.clicks"));
			bb.setTickets(rs.getInt("b.tickets"));
			bb.setSurfacePlot(rs.getBlob("b.surface_Plot"));
			bb.setSurface_Plot_Name(rs.getString("b.surface_Plot_Name"));
			bb.setPenName(rs.getString("a.pen_Name"));
			bbs.add(bb);
		}
		
		rs.close();
		pstmt.close();
		
		if(bbs.isEmpty()) { 
			return null;
		}else {
			return bbs;
		}
	}
	
	
	public List<BookBean> findBooksByAdvancedRule(String title_str, String penName_Str, String intro_str) throws SQLException {
		String sql =" SELECT b.book_Id, b.title, b.author_Id, b.book_state, b.publish_Date,"+
		    		" b.intro, b.classify, b.clicks, b.tickets, b.surface_Plot, b.surface_Plot_Name, "+
		    		" a.pen_Name "+
		    		" FROM book b JOIN author a ON (b.author_Id = a.author_Id) "+
                    " WHERE (b.title LIKE ?) AND (a.pen_Name LIKE ?) AND (b.intro LIKE ?) ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,"%"+title_str+"%");
		pstmt.setString(2,"%"+penName_Str+"%");
		pstmt.setString(3,"%"+intro_str+"%");
		ResultSet rs = pstmt.executeQuery();
		List<BookBean> bbs = new ArrayList<>();
		while (rs.next()) {
			BookBean bb = new BookBean();
			bb.setBookId(rs.getInt("b.book_Id"));
			bb.setTitle(rs.getString("b.title"));
			bb.setAuthorId(rs.getInt("b.author_Id"));
			bb.setBookState(rs.getString("b.book_state"));
			bb.setPublishDate(rs.getDate("b.publish_Date"));
			bb.setIntro(rs.getString("b.intro"));
			bb.setClassify(rs.getString("b.classify"));
			bb.setClicks(rs.getInt("b.clicks"));
			bb.setTickets(rs.getInt("b.tickets"));
			bb.setSurfacePlot(rs.getBlob("b.surface_Plot"));
			bb.setSurface_Plot_Name(rs.getString("b.surface_Plot_Name"));
			bb.setPenName(rs.getString("a.pen_Name"));
			bbs.add(bb);
		}
		
		rs.close();
		pstmt.close();
		
		if(bbs.isEmpty()) { 
			return null;
		}else {
			return bbs;
		}
	}
	
	
	
	
	
	public List<ClassifyBean> countClassifys(String keyword_str,String table_str) throws SQLException {
		String sql =" SELECT b.classify, COUNT(*) counts"+
		    		" FROM book b JOIN author a ON (b.author_Id = a.author_Id) "+
                    " WHERE "+table_str+" LIKE ? "+
					" GROUP BY b.classify ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,"%"+keyword_str+"%");
		ResultSet rs = pstmt.executeQuery();
		List<ClassifyBean> cbs = new ArrayList<>();
		while (rs.next()) {
			ClassifyBean cb = new ClassifyBean();
			cb.setClassifyType(rs.getString("b.classify"));
			cb.setClassifyNumber(rs.getInt("counts"));
			cbs.add(cb);
		}
		
		rs.close();
		pstmt.close();
		
		if(cbs.isEmpty()) { 
			return null;
		}else {
			return cbs;
		}
	}
	
	
	
	
	public List<BookStateBean> countBookStates(String keyword_str,String table_str) throws SQLException {
		String sql =" SELECT b.book_state, COUNT(*) counts "+
		    		" FROM book b JOIN author a ON (b.author_Id = a.author_Id) "+
                    " WHERE "+table_str+" LIKE ? "+
					" GROUP BY b.book_state ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,"%"+keyword_str+"%");
		ResultSet rs = pstmt.executeQuery();
		List<BookStateBean> bsbs = new ArrayList<>();
		while (rs.next()) {
			BookStateBean bsb = new BookStateBean();
			bsb.setBookStateType((rs.getString("b.book_state")));
			bsb.setBookStateNumber(rs.getInt("counts"));;
			bsbs.add(bsb);
		}
		
		rs.close();
		pstmt.close();
		
		if(bsbs.isEmpty()) { 
			return null;
		}else {
			return bsbs;
		}
	}
	
	
	public List<ClassifyBean> countClassifysAdvanced(String titleStr,String penNameStr,String introStr) throws SQLException {
		String sql =" SELECT b.classify, COUNT(*) counts"+
		    		" FROM book b JOIN author a ON (b.author_Id = a.author_Id) "+
		    		" WHERE (b.title LIKE ?) AND (a.pen_Name LIKE ?) AND (b.intro LIKE ?) "+
					" GROUP BY b.classify ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,"%"+titleStr+"%");
		pstmt.setString(2,"%"+penNameStr+"%");		
		pstmt.setString(3,"%"+introStr+"%");
		ResultSet rs = pstmt.executeQuery();
		List<ClassifyBean> cbs = new ArrayList<>();
		while (rs.next()) {
			ClassifyBean cb = new ClassifyBean();
			cb.setClassifyType(rs.getString("b.classify"));
			cb.setClassifyNumber(rs.getInt("counts"));
			cbs.add(cb);
		}
		
		rs.close();
		pstmt.close();
		
		if(cbs.isEmpty()) { 
			return null;
		}else {
			return cbs;
		}
	}
	
	
	public List<BookStateBean> countBookStatesAdvanced(String titleStr,String penNameStr,String introStr) throws SQLException {
		String sql =" SELECT b.book_state, COUNT(*) counts "+
		    		" FROM book b JOIN author a ON (b.author_Id = a.author_Id) "+
		    		" WHERE (b.title LIKE ?) AND (a.pen_Name LIKE ?) AND (b.intro LIKE ?) "+
					" GROUP BY b.book_state ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,"%"+titleStr+"%");
		pstmt.setString(2,"%"+penNameStr+"%");		
		pstmt.setString(3,"%"+introStr+"%");
		ResultSet rs = pstmt.executeQuery();
		List<BookStateBean> bsbs = new ArrayList<>();
		while (rs.next()) {
			BookStateBean bsb = new BookStateBean();
			bsb.setBookStateType((rs.getString("b.book_state")));
			bsb.setBookStateNumber(rs.getInt("counts"));;
			bsbs.add(bsb);
		}
		
		rs.close();
		pstmt.close();
		
		if(bsbs.isEmpty()) { 
			return null;
		}else {
			return bsbs;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	

	public List<BookBean> filterBooksByClassify(String table_str,String keyword_str, String classify_type) throws SQLException {
		String sql =" SELECT b.book_Id, b.title, b.author_Id, b.book_state, b.publish_Date,"+
	    		" b.intro, b.classify, b.clicks, b.tickets, b.surface_Plot, b.surface_Plot_Name, "+
	    		" a.pen_Name "+
	    		" FROM book b JOIN author a ON (b.author_Id = a.author_Id) "+
                " WHERE "+table_str+" LIKE ?  AND b.classify = ? ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,"%"+keyword_str+"%");
		pstmt.setString(2,classify_type);
		ResultSet rs = pstmt.executeQuery();
		List<BookBean> bbs = new ArrayList<>();
		while (rs.next()) {
			BookBean bb = new BookBean();
			bb.setBookId(rs.getInt("b.book_Id"));
			bb.setTitle(rs.getString("b.title"));
			bb.setAuthorId(rs.getInt("b.author_Id"));
			bb.setBookState(rs.getString("b.book_state"));
			bb.setPublishDate(rs.getDate("b.publish_Date"));
			bb.setIntro(rs.getString("b.intro"));
			bb.setClassify(rs.getString("b.classify"));
			bb.setClicks(rs.getInt("b.clicks"));
			bb.setTickets(rs.getInt("b.tickets"));
			bb.setSurfacePlot(rs.getBlob("b.surface_Plot"));
			bb.setSurface_Plot_Name(rs.getString("b.surface_Plot_Name"));
			bb.setPenName(rs.getString("a.pen_Name"));
			bbs.add(bb);
		}
		
		rs.close();
		pstmt.close();
		
		if(bbs.isEmpty()) { 
			return null;
		}else {
			return bbs;
		}
	}
	
	
	
	
	public List<BookBean> filterBooksByClassifyAdvanced(String[] keywords, String classify_type) throws SQLException {
		String titleStr   = keywords[0];
		String penNameStr = keywords[1];
		String introStr   = keywords[2];
		String sql =" SELECT b.book_Id, b.title, b.author_Id, b.book_state, b.publish_Date,"+
	    		" b.intro, b.classify, b.clicks, b.tickets, b.surface_Plot, b.surface_Plot_Name, "+
	    		" a.pen_Name "+
	    		" FROM book b JOIN author a ON (b.author_Id = a.author_Id) "+
	    		" WHERE (b.title LIKE ?) AND (a.pen_Name LIKE ?) AND (b.intro LIKE ?) AND b.classify = ? ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,"%"+titleStr+"%");
		pstmt.setString(2,"%"+penNameStr+"%");
		pstmt.setString(3,"%"+introStr+"%");
		pstmt.setString(4,classify_type);
		ResultSet rs = pstmt.executeQuery();
		List<BookBean> bbs = new ArrayList<>();
		while (rs.next()) {
			BookBean bb = new BookBean();
			bb.setBookId(rs.getInt("b.book_Id"));
			bb.setTitle(rs.getString("b.title"));
			bb.setAuthorId(rs.getInt("b.author_Id"));
			bb.setBookState(rs.getString("b.book_state"));
			bb.setPublishDate(rs.getDate("b.publish_Date"));
			bb.setIntro(rs.getString("b.intro"));
			bb.setClassify(rs.getString("b.classify"));
			bb.setClicks(rs.getInt("b.clicks"));
			bb.setTickets(rs.getInt("b.tickets"));
			bb.setSurfacePlot(rs.getBlob("b.surface_Plot"));
			bb.setSurface_Plot_Name(rs.getString("b.surface_Plot_Name"));
			bb.setPenName(rs.getString("a.pen_Name"));
			bbs.add(bb);
		}
		
		rs.close();
		pstmt.close();
		
		if(bbs.isEmpty()) { 
			return null;
		}else {
			return bbs;
		}
	}
	
	
	
	public List<BookBean> filterBooksByBookState(String table_str,String keyword_str, String bookstate_type) throws SQLException {
		String sql =" SELECT b.book_Id, b.title, b.author_Id, b.book_state, b.publish_Date,"+
	    		" b.intro, b.classify, b.clicks, b.tickets, b.surface_Plot, b.surface_Plot_Name, "+
	    		" a.pen_Name "+
	    		" FROM book b JOIN author a ON (b.author_Id = a.author_Id) "+
                " WHERE "+table_str+" LIKE ?  AND b.book_state = ? ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,"%"+keyword_str+"%");
		pstmt.setString(2,bookstate_type);
		ResultSet rs = pstmt.executeQuery();
		List<BookBean> bbs = new ArrayList<>();
		while (rs.next()) {
			BookBean bb = new BookBean();
			bb.setBookId(rs.getInt("b.book_Id"));
			bb.setTitle(rs.getString("b.title"));
			bb.setAuthorId(rs.getInt("b.author_Id"));
			bb.setBookState(rs.getString("b.book_state"));
			bb.setPublishDate(rs.getDate("b.publish_Date"));
			bb.setIntro(rs.getString("b.intro"));
			bb.setClassify(rs.getString("b.classify"));
			bb.setClicks(rs.getInt("b.clicks"));
			bb.setTickets(rs.getInt("b.tickets"));
			bb.setSurfacePlot(rs.getBlob("b.surface_Plot"));
			bb.setSurface_Plot_Name(rs.getString("b.surface_Plot_Name"));
			bb.setPenName(rs.getString("a.pen_Name"));
			bbs.add(bb);
		}
		
		rs.close();
		pstmt.close();
		
		if(bbs.isEmpty()) { 
			return null;
		}else {
			return bbs;
		}
	}
	
	
	
	
	public List<BookBean> filterBooksByBookStateAdvanced(String[] keywords, String bookstate_type) throws SQLException {
		String titleStr   = keywords[0];
		String penNameStr = keywords[1];
		String introStr   = keywords[2];
		String sql =" SELECT b.book_Id, b.title, b.author_Id, b.book_state, b.publish_Date,"+
	    		" b.intro, b.classify, b.clicks, b.tickets, b.surface_Plot, b.surface_Plot_Name, "+
	    		" a.pen_Name "+
	    		" FROM book b JOIN author a ON (b.author_Id = a.author_Id) "+
	    		" WHERE (b.title LIKE ?) AND (a.pen_Name LIKE ?) AND (b.intro LIKE ?) AND b.book_state = ? ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,"%"+titleStr+"%");
		pstmt.setString(2,"%"+penNameStr+"%");
		pstmt.setString(3,"%"+introStr+"%");
		pstmt.setString(4,bookstate_type);
		ResultSet rs = pstmt.executeQuery();
		List<BookBean> bbs = new ArrayList<>();
		while (rs.next()) {
			BookBean bb = new BookBean();
			bb.setBookId(rs.getInt("b.book_Id"));
			bb.setTitle(rs.getString("b.title"));
			bb.setAuthorId(rs.getInt("b.author_Id"));
			bb.setBookState(rs.getString("b.book_state"));
			bb.setPublishDate(rs.getDate("b.publish_Date"));
			bb.setIntro(rs.getString("b.intro"));
			bb.setClassify(rs.getString("b.classify"));
			bb.setClicks(rs.getInt("b.clicks"));
			bb.setTickets(rs.getInt("b.tickets"));
			bb.setSurfacePlot(rs.getBlob("b.surface_Plot"));
			bb.setSurface_Plot_Name(rs.getString("b.surface_Plot_Name"));
			bb.setPenName(rs.getString("a.pen_Name"));
			bbs.add(bb);
		}
		
		rs.close();
		pstmt.close();
		
		if(bbs.isEmpty()) { 
			return null;
		}else {
			return bbs;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public List<BookBean> sortBooksByClicks(String table_str,String keyword_str,String sort_factor) throws SQLException {
		String sql =" SELECT b.book_Id, b.title, b.author_Id, b.book_state, b.publish_Date,"+
		    		" b.intro, b.classify, b.clicks, b.tickets, b.surface_Plot, b.surface_Plot_Name, "+
		    		" a.pen_Name "+
		    		" FROM book b JOIN author a ON (b.author_Id = a.author_Id) "+
                    " WHERE "+table_str+" LIKE ? "+
		    		" ORDER BY  b.clicks " + sort_factor + " ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,"%"+keyword_str+"%");
		ResultSet rs = pstmt.executeQuery();
		List<BookBean> bbs = new ArrayList<>();
		while (rs.next()) {
			BookBean bb = new BookBean();
			bb.setBookId(rs.getInt("b.book_Id"));
			bb.setTitle(rs.getString("b.title"));
			bb.setAuthorId(rs.getInt("b.author_Id"));
			bb.setBookState(rs.getString("b.book_state"));
			bb.setPublishDate(rs.getDate("b.publish_Date"));
			bb.setIntro(rs.getString("b.intro"));
			bb.setClassify(rs.getString("b.classify"));
			bb.setClicks(rs.getInt("b.clicks"));
			bb.setTickets(rs.getInt("b.tickets"));
			bb.setSurfacePlot(rs.getBlob("b.surface_Plot"));
			bb.setSurface_Plot_Name(rs.getString("b.surface_Plot_Name"));
			bb.setPenName(rs.getString("a.pen_Name"));
			bbs.add(bb);
		}
		
		rs.close();
		pstmt.close();
		
		if(bbs.isEmpty()) { 
			return null;
		}else {
			return bbs;
		}
	}
	
	
	
	
	
	
	
	
	public List<BookBean> sortBooksByClicksAdvanced(String[] keywords,String sort_factor) throws SQLException {
		String titleStr   = keywords[0];
		String penNameStr = keywords[1];
		String introStr   = keywords[2];		
		String sql =" SELECT b.book_Id, b.title, b.author_Id, b.book_state, b.publish_Date,"+
		    		" b.intro, b.classify, b.clicks, b.tickets, b.surface_Plot, b.surface_Plot_Name, "+
		    		" a.pen_Name "+
		    		" FROM book b JOIN author a ON (b.author_Id = a.author_Id) "+
		    		" WHERE (b.title LIKE ?) AND (a.pen_Name LIKE ?) AND (b.intro LIKE ?) "+
		    		" ORDER BY  b.clicks " + sort_factor + " ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,"%"+titleStr+"%");
		pstmt.setString(2,"%"+penNameStr+"%");
		pstmt.setString(3,"%"+introStr+"%");
		ResultSet rs = pstmt.executeQuery();
		List<BookBean> bbs = new ArrayList<>();
		while (rs.next()) {
			BookBean bb = new BookBean();
			bb.setBookId(rs.getInt("b.book_Id"));
			bb.setTitle(rs.getString("b.title"));
			bb.setAuthorId(rs.getInt("b.author_Id"));
			bb.setBookState(rs.getString("b.book_state"));
			bb.setPublishDate(rs.getDate("b.publish_Date"));
			bb.setIntro(rs.getString("b.intro"));
			bb.setClassify(rs.getString("b.classify"));
			bb.setClicks(rs.getInt("b.clicks"));
			bb.setTickets(rs.getInt("b.tickets"));
			bb.setSurfacePlot(rs.getBlob("b.surface_Plot"));
			bb.setSurface_Plot_Name(rs.getString("b.surface_Plot_Name"));
			bb.setPenName(rs.getString("a.pen_Name"));
			bbs.add(bb);
		}
		
		rs.close();
		pstmt.close();
		
		if(bbs.isEmpty()) { 
			return null;
		}else {
			return bbs;
		}
	}
	
	
	
	
	
	
	
	
	
	
	public List<BookBean> sortBooksByPublishDate(String table_str,String keyword_str,String sort_factor) throws SQLException {
		String sql =" SELECT b.book_Id, b.title, b.author_Id, b.book_state, b.publish_Date,"+
		    		" b.intro, b.classify, b.clicks, b.tickets, b.surface_Plot, b.surface_Plot_Name, "+
		    		" a.pen_Name "+
		    		" FROM book b JOIN author a ON (b.author_Id = a.author_Id) "+
                    " WHERE "+table_str+" LIKE ? "+
		    		" ORDER BY  b.publish_Date " + sort_factor + " ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,"%"+keyword_str+"%");
		ResultSet rs = pstmt.executeQuery();
		List<BookBean> bbs = new ArrayList<>();
		while (rs.next()) {
			BookBean bb = new BookBean();
			bb.setBookId(rs.getInt("b.book_Id"));
			bb.setTitle(rs.getString("b.title"));
			bb.setAuthorId(rs.getInt("b.author_Id"));
			bb.setBookState(rs.getString("b.book_state"));
			bb.setPublishDate(rs.getDate("b.publish_Date"));
			bb.setIntro(rs.getString("b.intro"));
			bb.setClassify(rs.getString("b.classify"));
			bb.setClicks(rs.getInt("b.clicks"));
			bb.setTickets(rs.getInt("b.tickets"));
			bb.setSurfacePlot(rs.getBlob("b.surface_Plot"));
			bb.setSurface_Plot_Name(rs.getString("b.surface_Plot_Name"));
			bb.setPenName(rs.getString("a.pen_Name"));
			bbs.add(bb);
		}
		
		rs.close();
		pstmt.close();
		
		if(bbs.isEmpty()) { 
			return null;
		}else {
			return bbs;
		}
	}
		
	public List<BookBean> sortBooksByPublishDateAdvanced(String[] keywords,String sort_factor) throws SQLException {
		String titleStr   = keywords[0];
		String penNameStr = keywords[1];
		String introStr   = keywords[2];		
		String sql =" SELECT b.book_Id, b.title, b.author_Id, b.book_state, b.publish_Date,"+
		    		" b.intro, b.classify, b.clicks, b.tickets, b.surface_Plot, b.surface_Plot_Name, "+
		    		" a.pen_Name "+
		    		" FROM book b JOIN author a ON (b.author_Id = a.author_Id) "+
		    		" WHERE (b.title LIKE ?) AND (a.pen_Name LIKE ?) AND (b.intro LIKE ?) "+
		    		" ORDER BY  b.publish_Date " + sort_factor + " ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,"%"+titleStr+"%");
		pstmt.setString(2,"%"+penNameStr+"%");
		pstmt.setString(3,"%"+introStr+"%");
		ResultSet rs = pstmt.executeQuery();
		List<BookBean> bbs = new ArrayList<>();
		while (rs.next()) {
			BookBean bb = new BookBean();
			bb.setBookId(rs.getInt("b.book_Id"));
			bb.setTitle(rs.getString("b.title"));
			bb.setAuthorId(rs.getInt("b.author_Id"));
			bb.setBookState(rs.getString("b.book_state"));
			bb.setPublishDate(rs.getDate("b.publish_Date"));
			bb.setIntro(rs.getString("b.intro"));
			bb.setClassify(rs.getString("b.classify"));
			bb.setClicks(rs.getInt("b.clicks"));
			bb.setTickets(rs.getInt("b.tickets"));
			bb.setSurfacePlot(rs.getBlob("b.surface_Plot"));
			bb.setSurface_Plot_Name(rs.getString("b.surface_Plot_Name"));
			bb.setPenName(rs.getString("a.pen_Name"));
			bbs.add(bb);
		}
		
		rs.close();
		pstmt.close();
		
		if(bbs.isEmpty()) { 
			return null;
		}else {
			return bbs;
		}
	}
	
	
	
	public List<BookBean> sortBooksByTickets(String table_str,String keyword_str,String sort_factor) throws SQLException {
		String sql =" SELECT b.book_Id, b.title, b.author_Id, b.book_state, b.publish_Date,"+
		    		" b.intro, b.classify, b.clicks, b.tickets, b.surface_Plot, b.surface_Plot_Name, "+
		    		" a.pen_Name "+
		    		" FROM book b JOIN author a ON (b.author_Id = a.author_Id) "+
                    " WHERE "+table_str+" LIKE ? "+
		    		" ORDER BY  b.tickets " + sort_factor + " ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,"%"+keyword_str+"%");
		ResultSet rs = pstmt.executeQuery();
		List<BookBean> bbs = new ArrayList<>();
		while (rs.next()) {
			BookBean bb = new BookBean();
			bb.setBookId(rs.getInt("b.book_Id"));
			bb.setTitle(rs.getString("b.title"));
			bb.setAuthorId(rs.getInt("b.author_Id"));
			bb.setBookState(rs.getString("b.book_state"));
			bb.setPublishDate(rs.getDate("b.publish_Date"));
			bb.setIntro(rs.getString("b.intro"));
			bb.setClassify(rs.getString("b.classify"));
			bb.setClicks(rs.getInt("b.clicks"));
			bb.setTickets(rs.getInt("b.tickets"));
			bb.setSurfacePlot(rs.getBlob("b.surface_Plot"));
			bb.setSurface_Plot_Name(rs.getString("b.surface_Plot_Name"));
			bb.setPenName(rs.getString("a.pen_Name"));
			bbs.add(bb);
		}
		
		rs.close();
		pstmt.close();
		
		if(bbs.isEmpty()) { 
			return null;
		}else {
			return bbs;
		}
	}
		
	public List<BookBean> sortBooksByTicketsAdvanced(String[] keywords,String sort_factor) throws SQLException {
		String titleStr   = keywords[0];
		String penNameStr = keywords[1];
		String introStr   = keywords[2];		
		String sql =" SELECT b.book_Id, b.title, b.author_Id, b.book_state, b.publish_Date,"+
		    		" b.intro, b.classify, b.clicks, b.tickets, b.surface_Plot, b.surface_Plot_Name, "+
		    		" a.pen_Name "+
		    		" FROM book b JOIN author a ON (b.author_Id = a.author_Id) "+
		    		" WHERE (b.title LIKE ?) AND (a.pen_Name LIKE ?) AND (b.intro LIKE ?) "+
		    		" ORDER BY  b.tickets " + sort_factor + " ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,"%"+titleStr+"%");
		pstmt.setString(2,"%"+penNameStr+"%");
		pstmt.setString(3,"%"+introStr+"%");
		ResultSet rs = pstmt.executeQuery();
		List<BookBean> bbs = new ArrayList<>();
		while (rs.next()) {
			BookBean bb = new BookBean();
			bb.setBookId(rs.getInt("b.book_Id"));
			bb.setTitle(rs.getString("b.title"));
			bb.setAuthorId(rs.getInt("b.author_Id"));
			bb.setBookState(rs.getString("b.book_state"));
			bb.setPublishDate(rs.getDate("b.publish_Date"));
			bb.setIntro(rs.getString("b.intro"));
			bb.setClassify(rs.getString("b.classify"));
			bb.setClicks(rs.getInt("b.clicks"));
			bb.setTickets(rs.getInt("b.tickets"));
			bb.setSurfacePlot(rs.getBlob("b.surface_Plot"));
			bb.setSurface_Plot_Name(rs.getString("b.surface_Plot_Name"));
			bb.setPenName(rs.getString("a.pen_Name"));
			bbs.add(bb);
		}
		
		rs.close();
		pstmt.close();
		
		if(bbs.isEmpty()) { 
			return null;
		}else {
			return bbs;
		}
	}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void connectClose() throws SQLException {
		conn.close();				
	}
	


}
