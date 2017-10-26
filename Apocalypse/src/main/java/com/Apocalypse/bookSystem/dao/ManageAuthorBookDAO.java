package com.Apocalypse.bookSystem.dao;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
import com.Apocalypse.bookSystem.model.VolumeBean;
import com.Apocalypse.bookSystem.util.GlobalService;

public class ManageAuthorBookDAO {
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
	
	
	public List<BookBean> findBooksByAuthorId(int author_Id) throws SQLException{
		String sql =" SELECT b.book_Id, b.title, b.author_Id, b.book_state, b.publish_Date,"+
			    	" b.intro, b.classify, b.clicks, b.tickets, b.surface_Plot, b.surface_Plot_Name, "+
			    	" a.pen_Name "+
			    	" FROM book b JOIN author a ON (b.author_Id = a.author_Id) "+
			    	" WHERE b.author_Id = ? ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1,author_Id);
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
	
	
	public List<BookBean> findCheckBooksByAuthorId(int author_Id) throws SQLException{
		String sql =" SELECT b.book_Id, b.title, b.author_Id, b.book_state, b.publish_Date,"+
			    	" b.intro, b.classify, b.clicks, b.tickets, b.surface_Plot, b.surface_Plot_Name, "+
			    	" a.pen_Name "+
			    	" FROM check_book b JOIN author a ON (b.author_Id = a.author_Id) "+
			    	" WHERE b.author_Id = ? ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1,author_Id);
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
	
	
	public String findPenNameByAuthorId(int author_Id) throws SQLException{
		String sql =" SELECT pen_Name "+
			    	" FROM author "+
			    	" WHERE author_Id = ? ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1,author_Id);
		ResultSet rs = pstmt.executeQuery();
		String penName = "Nameless";
		if(rs.next()) {
			penName = rs.getString("pen_Name");
		}
		
		rs.close();
		pstmt.close();
		
		if(penName.equals(null)) { 
			return null;
		}else {
			return penName;
		}
	}
	
	
	public BookBean findBookByAuthorIdBookId(int author_Id,int book_Id) throws SQLException{
		String sql =" SELECT book_Id,title,classify,intro,surface_Plot_Name"+
			    	" FROM book "+
			    	" WHERE author_Id = ? AND book_Id=? ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1,author_Id);
		pstmt.setInt(2,book_Id);
		ResultSet rs = pstmt.executeQuery();
		BookBean bb = new BookBean();

		if(rs.next()) {
			bb.setBookId(rs.getInt("book_Id"));
			bb.setTitle(rs.getString("title"));
			bb.setClassify(rs.getString("classify"));
			bb.setIntro(rs.getString("intro"));
			bb.setSurface_Plot_Name(rs.getString("surface_Plot_Name"));
		}
		
		rs.close();
		pstmt.close();
		
		if(bb.equals(null)) { 
			return null;
		}else {
			return bb;
		}
	}
	
	
	public int insertBookAsAuthor(BookBean bb) throws SQLException, IOException{
		String sql = "INSERT INTO "+
		        "check_book(title,author_Id,book_state,publish_Date,intro,classify,surface_Plot,surface_Plot_Name)"+
			    "VALUES(?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
	    File file1 = new File("D:/Users/PC-26/git1/Apocalypse/src/main/webapp/fakedata_resources/"+bb.getSurface_Plot_Name()+".jpg");	    
	    int length1 = (int) file1.length();
	    InputStream fin1 = new FileInputStream(file1);
	    
        pstmt.setString(1,bb.getTitle());
        pstmt.setInt(2,bb.getAuthorId());
		pstmt.setString(3,bb.getBookState());
		pstmt.setDate(4,bb.getPublishDate());
		pstmt.setString(5,bb.getIntro());
		pstmt.setString(6,bb.getClassify());
		pstmt.setBinaryStream (7, fin1, length1);
		pstmt.setString(8,bb.getSurface_Plot_Name());		
        int result = pstmt.executeUpdate();
        pstmt.clearParameters();
        pstmt.close();
        fin1.close();
        
        System.out.println(result);

		if(result == 1) { 
			return 1;
		}else {
			return -1;
		}
				
	}
	
	
	//功能:找出選取的書本目前連載的最大卷數      呼叫方:             送至: 
	public int findMaxVolumeNumber(int book_Id) throws SQLException{
		String sql =" SELECT MAX(volume_id) "+
			    	" FROM volume "+
			    	" WHERE book_Id = ? ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1,book_Id);
		ResultSet rs = pstmt.executeQuery();
		int max_VolumeNumber = 0;
		if(rs.next()) {
			max_VolumeNumber = rs.getInt(1);
		}
		rs.close();
		pstmt.close();
		
		if(max_VolumeNumber == 0) { 
			return 0;
		}else {
			return max_VolumeNumber;
		}
	}
	
	
	public String findVolumeTitle(int book_Id, int max_VolumeNumber) throws SQLException{
		String sql =" SELECT volume_title "+
			    	" FROM volume "+
			    	" WHERE book_Id = ?  AND volume_Id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1,book_Id);
		pstmt.setInt(2,max_VolumeNumber);
		ResultSet rs = pstmt.executeQuery();
		String volume_Title = null;
		if(rs.next()) {
			volume_Title = rs.getString("volume_title");
		}
		rs.close();
		pstmt.close();
		
		if(volume_Title.equals(null)) { 
			return null;
		}else {
			return volume_Title;
		}
	}
	
	
	public int findMaxChapterNumber(int book_Id,int max_VolumeNumber) throws SQLException{
		String sql =" SELECT MAX(chapter_Id) "+
			    	" FROM chapter "+
			    	" WHERE book_Id = ? AND volume_Id = ? ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1,book_Id);
		pstmt.setInt(2,max_VolumeNumber);
		ResultSet rs = pstmt.executeQuery();
		int max_ChapterNumber = 0;
		if(rs.next()) {
			max_ChapterNumber = rs.getInt(1);
		}
		rs.close();
		pstmt.close();
		
		if(max_ChapterNumber == 0) { 
			return 0;
		}else {
			return max_ChapterNumber;
		}
	}
	
	
	public String findChapterTitle(int book_Id, int max_VolumeNumber,int max_ChapterNumber) throws SQLException{
		String sql =" SELECT chapter_title "+
			    	" FROM chapter "+
			    	" WHERE book_Id = ?  AND volume_Id = ? AND chapter_Id = ? ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1,book_Id);
		pstmt.setInt(2,max_VolumeNumber);
		pstmt.setInt(3,max_ChapterNumber);
		ResultSet rs = pstmt.executeQuery();
		String chapter_Title = null;
		if(rs.next()) {
			chapter_Title = rs.getString("chapter_title");
		}
		rs.close();
		pstmt.close();
		
		if(chapter_Title.equals(null)) { 
			return null;
		}else {
			return chapter_Title;
		}
	}
	
	
	public int insertVolumeAsAuthor(VolumeBean vbUp) throws SQLException, IOException{
		String sql = "INSERT INTO "+
		        "check_volume(book_Id,volume_Id,volume_Title) "+
			    " VALUES(?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,vbUp.getBookId());
        pstmt.setInt(2,vbUp.getVolumeId());
		pstmt.setString(3,vbUp.getVolumeTitle());
        int result = pstmt.executeUpdate();
        pstmt.clearParameters();
        pstmt.close();       
        System.out.println(result);

		if(result == 1) { 
			return 1;
		}else {
			return -1;
		}			
	}
	
	
	public int insertChapterAsAuthor(ChapterBean cbUp) throws SQLException, IOException{
		String sql = "INSERT INTO "+
		        "check_chapter(book_Id,volume_Id,chapter_id,chapter_title,chapter_content,content_Name,price) "+
			    " VALUES(?,?,?,?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,cbUp.getBookId());
        pstmt.setInt(2,cbUp.getVolumeId());
        pstmt.setInt(3,cbUp.getChapterId());
		pstmt.setString(4,cbUp.getChapterTitle());
		pstmt.setString(5,cbUp.getChapterContent());
		pstmt.setString(6,cbUp.getContentName());
		pstmt.setInt(7,cbUp.getPrice());
        int result = pstmt.executeUpdate();
        pstmt.clearParameters();
        pstmt.close();       
        System.out.println(result);
        
		if(result == 1) { 
			return 1;
		}else {
			return -1;
		}			
	}
	
	
	public int alterBookAsAuthor(BookBean bb) throws SQLException, IOException{
		String sql = "INSERT INTO "+
		        "edit_book(book_Id,title,author_Id,intro,classify,surface_Plot,surface_Plot_Name)"+
			    "VALUES(?,?,?,?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
	    File file1 = new File("D:/_JSP/workspace/Booksystem/src/main/webapp/edit_bookcover_resources/"+bb.getSurface_Plot_Name()+".jpg");	    
	    int length1 = (int) file1.length();
	    InputStream fin1 = new FileInputStream(file1);
	    
	    pstmt.setInt(1,bb.getBookId());
        pstmt.setString(2,bb.getTitle());        
        pstmt.setInt(3,bb.getAuthorId());        
		pstmt.setString(4,bb.getIntro());
		pstmt.setString(5,bb.getClassify());
		pstmt.setBinaryStream (6, fin1, length1);
		pstmt.setString(7,bb.getSurface_Plot_Name());		
        int result = pstmt.executeUpdate();
        pstmt.clearParameters();
        pstmt.close();
        fin1.close();
        
        System.out.println(result);

		if(result == 1) { 
			return 1;
		}else {
			return -1;
		}		
	}
	

	public int deleteCheckVolumeAsAuthor(VolumeBean vb) throws SQLException, IOException{
		String sql = "DELETE "+
					 "FROM check_volume "+	
					 "WHERE book_Id = ? AND volume_Id = ? ";

		PreparedStatement pstmt = conn.prepareStatement(sql);
	    pstmt.setInt(1,vb.getBookId());
	    pstmt.setInt(2,vb.getVolumeId());
        int result = pstmt.executeUpdate();
        pstmt.clearParameters();
        pstmt.close();
        System.out.println(result);

		if(result == 1) { 
			return 1;
		}else {
			return -1;
		}		
	}
	
	
	public int deleteEditBookAsAuthor(BookBean bb) throws SQLException, IOException{
		String sql = "DELETE "+
					 "FROM edit_book "+	
					 "WHERE book_Id = ? ";

		PreparedStatement pstmt = conn.prepareStatement(sql);

	    pstmt.setInt(1,bb.getBookId());
        int result = pstmt.executeUpdate();
        pstmt.clearParameters();
        pstmt.close();
        System.out.println(result);

		if(result == 1) { 
			return 1;
		}else {
			return -1;
		}		
	}
	
	
	public int deleteEditVolumeAsAuthor(VolumeBean vb) throws SQLException, IOException{
		String sql = "DELETE "+
					 "FROM edit_volume "+	
					 "WHERE book_Id = ? AND volume_Id = ? ";

		PreparedStatement pstmt = conn.prepareStatement(sql);
	    pstmt.setInt(1,vb.getBookId());
	    pstmt.setInt(2,vb.getVolumeId());
        int result = pstmt.executeUpdate();
        pstmt.clearParameters();
        pstmt.close();
        System.out.println(result);

		if(result == 1) { 
			return 1;
		}else {
			return -1;
		}		
	}
	
	
	
	public int deleteEditChapterAsAuthor(ChapterBean cb) throws SQLException, IOException{
		String sql = "DELETE "+
					 "FROM edit_chapter "+	
					 "WHERE book_Id = ? AND volume_Id = ? AND chapter_Id = ?";

		PreparedStatement pstmt = conn.prepareStatement(sql);
	    pstmt.setInt(1,cb.getBookId());
	    pstmt.setInt(2,cb.getVolumeId());
	    pstmt.setInt(3,cb.getChapterId());
        int result = pstmt.executeUpdate();
        pstmt.clearParameters();
        pstmt.close();
        System.out.println(result);

		if(result == 1) { 
			return 1;
		}else {
			return -1;
		}		
	}
	
	
	
	public int deleteCheckChapterAsAuthor(ChapterBean cb) throws SQLException, IOException{
		String sql = "DELETE "+
					 "FROM check_chapter "+	
					 "WHERE book_Id = ? AND volume_Id = ? AND chapter_Id = ?";

		PreparedStatement pstmt = conn.prepareStatement(sql);
	    pstmt.setInt(1,cb.getBookId());
	    pstmt.setInt(2,cb.getVolumeId());
	    pstmt.setInt(3,cb.getChapterId());
        int result = pstmt.executeUpdate();
        pstmt.clearParameters();
        pstmt.close();
        System.out.println(result);

		if(result == 1) { 
			return 1;
		}else {
			return -1;
		}		
	}
	
	
	
	
	public String findChapterContent(int book_Id, int volume_Id,int chapter_Id) throws SQLException{
		String sql =" SELECT chapter_content "+
			    	" FROM chapter "+
			    	" WHERE book_Id = ?  AND volume_Id = ? AND chapter_Id = ? ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1,book_Id);
		pstmt.setInt(2,volume_Id);
		pstmt.setInt(3,chapter_Id);
		ResultSet rs = pstmt.executeQuery();
		String chapter_Content = null;
		if(rs.next()) {
			chapter_Content = rs.getString("chapter_content");
		}
		rs.close();
		pstmt.close();
		
		if(chapter_Content.equals(null)) { 
			return null;
		}else {
			return chapter_Content;
		}
	}
	

	public int alterVolumeAsAuthor(VolumeBean vb) throws SQLException, IOException{
		String sql = "INSERT INTO "+
		        "edit_volume(book_Id,volume_Id,volume_Title) "+
			    "VALUES(?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
    
	    pstmt.setInt(1,vb.getBookId());       
        pstmt.setInt(2,vb.getVolumeId());        
		pstmt.setString(3,vb.getVolumeTitle());
		
        int result = pstmt.executeUpdate();
        pstmt.clearParameters();
        pstmt.close();
        
        System.out.println(result);

		if(result == 1) { 
			return 1;
		}else {
			return -1;
		}		
	}
	

	public int alterChapterAsAuthor(ChapterBean cb) throws SQLException, IOException{
		String sql = "INSERT INTO "+
		        "edit_chapter(book_Id,volume_Id,chapter_id,chapter_Title,chapter_content) "+
			    "VALUES(?,?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);

	    pstmt.setInt(1,cb.getBookId());       
        pstmt.setInt(2,cb.getVolumeId());  
        pstmt.setInt(3,cb.getChapterId());
		pstmt.setString(4,cb.getChapterTitle());
		pstmt.setString(5,cb.getChapterContent());
		
        int result = pstmt.executeUpdate();
        pstmt.clearParameters();
        pstmt.close();
        
        System.out.println(result);

		if(result == 1) { 
			return 1;
		}else {
			return -1;
		}		
	}
	

	public void cancelAutoCommit() throws SQLException {
		conn.setAutoCommit(false);
	}
	
	public void transactionCommit() throws SQLException {
		conn.commit();
	}

	public void transactionRollback() throws SQLException {
		conn.rollback();
	}
	
	
	public void connectClose() throws SQLException {
		conn.close();				
	}

	
}
