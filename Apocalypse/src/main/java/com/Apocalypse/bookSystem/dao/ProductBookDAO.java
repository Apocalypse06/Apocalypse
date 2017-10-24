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
import com.Apocalypse.bookSystem.model.ChapterBean;
import com.Apocalypse.bookSystem.model.CommentsBean;
import com.Apocalypse.bookSystem.model.StarsBean;
import com.Apocalypse.bookSystem.model.SubcommentsBean;
import com.Apocalypse.bookSystem.model.VolumeBean;
import com.Apocalypse.bookSystem.util.GlobalService;

public class ProductBookDAO {
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

	public BookBean findBookByBookId(int book_Id) throws SQLException {
		String sql =" SELECT b.book_Id, b.title, b.author_Id, b.book_state, b.publish_Date,"+
				    " b.intro, b.classify, b.clicks, b.tickets, b.surface_Plot, b.surface_Plot_Name, "+
				    " a.pen_Name "+
				    " FROM book b JOIN author a ON (b.author_Id = a.author_Id) "+
				    " WHERE b.book_Id = ? ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, book_Id);
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
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
			
			rs.close();
			pstmt.close();
						
			return bb;
		}else {
			return null;
		}	
	}
	

	public List<BookBean> findBooksAll() throws SQLException{
		String sql =" SELECT b.book_Id, b.title, b.author_Id, b.book_state, b.publish_Date,"+
			    	" b.intro, b.classify, b.clicks, b.tickets, b.surface_Plot, b.surface_Plot_Name, "+
			    	" a.pen_Name "+
			    	" FROM book b JOIN author a ON (b.author_Id = a.author_Id) ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		List<BookBean> bbs = new ArrayList<>();
		while(rs.next()) {
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
	
	
	public List<StarsBean> findStarPointsByBookId(int book_Id) throws SQLException{
		String sql ="SELECT book_Id,member_Id,starPoint "+
	                " FROM stars  WHERE book_Id = ? ";	
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1,book_Id);
		ResultSet rs = pstmt.executeQuery();
		List<StarsBean> sbs = new ArrayList<>();
		while(rs.next()) {
			StarsBean sb = new StarsBean();
			sb.setBookId(rs.getInt("book_Id"));
			sb.setMemberId(rs.getString("member_Id"));
			sb.setStarPoint(rs.getInt("starPoint"));
			sbs.add(sb);
		}
		
		rs.close();
		pstmt.close();
		
		if(sbs.isEmpty()) { 
			return null;
		}else {
			return sbs;
		}
	}
	
	
	
	public List<CommentsBean> findCommentsByBookId(int book_Id) throws SQLException{
		String sql ="SELECT book_Id,comment_Id,member_Id,comment "+
	                " FROM comments  WHERE book_Id = ? ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1,book_Id);
		ResultSet rs = pstmt.executeQuery();
		List<CommentsBean> cbs = new ArrayList<>();
		while(rs.next()) {
			CommentsBean cb = new CommentsBean();
			cb.setBookId(rs.getInt("book_Id"));
			cb.setCommentId(rs.getInt("comment_Id"));
			cb.setMemberId(rs.getString("member_Id"));
			cb.setComment(rs.getString("comment"));
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
	
	
	public List<SubcommentsBean> findSubcommentsByBookId(int book_Id) throws SQLException{
		String sql ="SELECT sub_comment_Id,comment_Id,book_Id, member_Id,comments "+
	                " FROM sub_comments  WHERE book_Id = ? ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1,book_Id);
		ResultSet rs = pstmt.executeQuery();
		List<SubcommentsBean> scbs = new ArrayList<>();
		while(rs.next()) {
			SubcommentsBean scb = new SubcommentsBean();
			scb.setSubcommentId(rs.getInt("sub_comment_Id"));
			scb.setCommentId(rs.getInt("comment_Id"));
			scb.setBookId(rs.getInt("book_Id"));
			scb.setMemberId(rs.getString("member_Id"));
			scb.setComments(rs.getString("comments"));
			scbs.add(scb);
		}
		
		rs.close();
		pstmt.close();
		
		if(scbs.isEmpty()) { 
			return null;
		}else {
			return scbs;
		}
	}
	
	
	public List<VolumeBean> findVolumesByBookId(int book_Id) throws SQLException{
		String sql ="SELECT book_Id,volume_Id,volume_Title "+
	                " FROM volume  WHERE book_Id = ? ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1,book_Id);
		ResultSet rs = pstmt.executeQuery();
		List<VolumeBean> vbs = new ArrayList<>();
		while(rs.next()) {
			VolumeBean vb = new VolumeBean();
			vb.setBookId(rs.getInt("book_Id"));
			vb.setVolumeId(rs.getInt("volume_Id"));
			vb.setVolumeTitle(rs.getString("volume_Title"));
			vbs.add(vb);
		}
		
		rs.close();
		pstmt.close();
		
		if(vbs.isEmpty()) { 
			return null;
		}else {
			return vbs;
		}
	}
	
	
	public List<ChapterBean> findChaptersByBookId(int book_Id) throws SQLException{
		String sql =" SELECT book_Id,volume_Id,chapter_Id,chapter_title,content_Name, "+
					" publish_time, last_modified, price "+
	                " FROM chapter  WHERE book_Id = ? ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1,book_Id);
		ResultSet rs = pstmt.executeQuery();
		List<ChapterBean> cbs = new ArrayList<>();
		while(rs.next()) {
			ChapterBean cb = new ChapterBean();
			cb.setBookId(rs.getInt("book_Id"));
			cb.setVolumeId(rs.getInt("volume_Id"));
			cb.setChapterId(rs.getInt("chapter_Id"));
			cb.setChapterTitle(rs.getString("chapter_title"));
			cb.setContentName(rs.getString("content_Name"));
			cb.setPublishTime(rs.getTimestamp("publish_time"));
			cb.setLastModified(rs.getTimestamp("last_modified"));
			cb.setPrice(rs.getInt("price"));
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
	
	public void connectClose() throws SQLException {
		conn.close();				
	}
	
	


}
