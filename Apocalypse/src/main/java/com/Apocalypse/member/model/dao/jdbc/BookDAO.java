package com.Apocalypse.member.model.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.Apocalypse.member.bean.BookBean;
import com.Apocalypse.member.bean.CommentsBean;
import com.Apocalypse.member.bean.Sub_commentsBean;

public class BookDAO {
	
	DataSource ds = null;
	
	public BookDAO() throws SQLException{
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc/MemberDB");
			 
			 
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}	
	
//	 //用member_id找出會員書架中的book_id資料  
//	private static final String SELECT_BOOKID_BY_MEMBERID = "Select book_Id from booklist where member_Id = ? ";
//
//	public ArrayList<Integer> selectbook_by_id(String member_Id) throws SQLException {
//		ArrayList<Integer> list = new ArrayList<>();
//
//		try(
//			Connection conn = ds.getConnection();
//			PreparedStatement stmt = conn.prepareStatement(SELECT_BOOKID_BY_MEMBERID);
//		){
//			stmt.setString(1, member_Id);
//			try(
//					ResultSet rset = stmt.executeQuery();
//			){
//				   while(rset.next()) {
//						int book_Id 	= rset.getInt("book_Id");
//						list.add(book_Id);
//					}
//			  }
//		}
//		return list;
//	}
	 
	 //用member_id找出會員書架中的book_id資料  (先找出每頁需要的那幾本就行了)
	 //ps:參數page是書架的第幾頁,number每頁分別有幾本書
		private static final String SELECT_BOOKID_BY_MEMBERID = "Select book_Id from booklist where member_Id = ? limit ?, ?";

		public ArrayList<Integer> selectbook_by_id(String member_Id, int page, int number) throws SQLException {
			ArrayList<Integer> list = new ArrayList<>();

			try(
				Connection conn = ds.getConnection();
				PreparedStatement stmt = conn.prepareStatement(SELECT_BOOKID_BY_MEMBERID);
			){
				stmt.setString(1, member_Id);
				stmt.setInt(2, (page-1)*number);
				stmt.setInt(3,number);
				try(
						ResultSet rset = stmt.executeQuery();
				){
					   while(rset.next()) {
							int book_Id 	= rset.getInt("book_Id");
							list.add(book_Id);
						}
				  }
			}
			return list;
		}
	
	//用book_id找出書本資料  
			private static final String SELECT_BY_ID = "Select * from book where book_Id = ?";

			public BookBean select_by_id(int book_Id) throws SQLException {
				BookBean result = null;

				try(
					Connection conn = ds.getConnection();
					PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID);
				){
					stmt.setInt(1, book_Id);
					try(
							ResultSet rset = stmt.executeQuery();
					){
							if (rset.next()) {
								result = new BookBean();
								result.setBook_Id(rset.getInt("book_Id"));
								result.setTitle(rset.getString("title"));
								result.setAuthor_Id(rset.getInt("author_Id"));
								result.setBook_state(rset.getString("book_state"));
								result.setPublish_Date(rset.getDate("publish_Date"));
								result.setIntro(rset.getString("intro"));
								result.setClassify(rset.getString("classify"));
								result.setClicks(rset.getInt("clicks"));
								result.setTickets(rset.getInt("tickets"));
								//result.setSurface_Plot(rset.getBlob("surface_Plot"));
								result.setSurface_Plot_Name(rset.getString("surface_Plot_Name"));
						
							}
					  }
				}
				return result;
			}
	//用member_Id找出他書架總共有幾本書(用來判斷書架總共有幾頁)
			private static final String getBookCounts = "select count(*) from booklist where member_Id = ?";

			public int getBookCounts(String member_Id) throws SQLException {
				int result = 0;

				try(
					Connection conn = ds.getConnection();
					PreparedStatement stmt = conn.prepareStatement(getBookCounts);
				){
					stmt.setString(1, member_Id);
					try(
							ResultSet rset = stmt.executeQuery();
					){
							if (rset.next()) {
								result = rset.getInt(1);
							}
					  }
				}
				return result;
			}
			//新增一個主留言
			private static final String INSERT2 = "Insert into Comments (book_Id, member_Id, comment_time, comment) values (?, ?, ?, ?)";
			
			public int insertComments(CommentsBean bean) throws SQLException {
				int result = 0;
				try(
						Connection conn = ds.getConnection();
						PreparedStatement stmt = conn.prepareStatement(INSERT2);
				){
					stmt.setInt(1, bean.getBook_Id());
					stmt.setString(2, bean.getMember_Id());
					stmt.setTimestamp(3, bean.getComment_time());
					stmt.setString(4, bean.getComment());
					
					result = stmt.executeUpdate();
					
				 }
				return result;
		    }
			 //用book_id找出此書的Comments資料  
			private static final String SELECT_COMMENTS_BY_BOOKID = "Select * from Comments where book_Id = ? ";
		
			public ArrayList<CommentsBean> selectCommets_by_book_id(int book_Id) throws SQLException {
				ArrayList<CommentsBean> list = new ArrayList<>();
				
				try(
					Connection conn = ds.getConnection();
					PreparedStatement stmt = conn.prepareStatement(SELECT_COMMENTS_BY_BOOKID);
				){
					stmt.setInt(1, book_Id);
					try(
							ResultSet rset = stmt.executeQuery();
					){
						   while(rset.next()) {
							   CommentsBean result = new CommentsBean() ;
							    result.setBook_Id(rset.getInt("book_Id"));
								result.setComment_Id(rset.getInt("comment_Id"));
								result.setMember_Id(rset.getString("member_Id"));
								result.setComment_time(rset.getTimestamp("comment_time"));
								result.setUpdate_time(rset.getTimestamp("update_time"));
								result.setComment(rset.getString("comment"));
								
								list.add(result);
							}
					  }
				}
				
				return list;
			}
			//用comment_Id找出此書的SUB_COMMENTS資料總共有幾個
			private static final String getSub_commentsBean = "select count(*) from Sub_comments where comment_Id = ?";

			public int getSub_commentsBean(int comment_Id) throws SQLException {
				int result = 0;

				try(
					Connection conn = ds.getConnection();
					PreparedStatement stmt = conn.prepareStatement(getSub_commentsBean);
				){
					stmt.setInt(1, comment_Id);
					try(
							ResultSet rset = stmt.executeQuery();
					){
							if (rset.next()) {
								result = rset.getInt(1);
							}
					  }
				}
				return result;
			}
			//用comment_Id找出此書的SUB_COMMENTS資料 
			private static final String SELECT_SUB_COMMENTS_BY_COMMENTS_ID = "Select * from Sub_comments where comment_Id = ? ";
			
			public ArrayList<Sub_commentsBean> selectSub_Commets_by_Comments_Id(int comment_Id) throws SQLException {
				ArrayList<Sub_commentsBean> list = new ArrayList<>();
				
				try(
					Connection conn = ds.getConnection();
					PreparedStatement stmt = conn.prepareStatement(SELECT_SUB_COMMENTS_BY_COMMENTS_ID);
				){
					stmt.setInt(1, comment_Id);
					try(
							ResultSet rset = stmt.executeQuery();
					){
						   while(rset.next()) {
							   Sub_commentsBean result = new Sub_commentsBean() ;
							   	result.setSub_comment_id(rset.getInt("sub_comment_id"));
							   	result.setComment_Id(rset.getInt("comment_Id"));
								result.setMember_Id(rset.getString("member_Id"));
								result.setComment_time(rset.getTimestamp("comment_time"));
								result.setUpdate_time(rset.getTimestamp("update_time"));
								result.setComments(rset.getString("comments"));
								
								list.add(result);
							}
					  }
				}
				
				return list;
			}
			//新增一個副留言
			private static final String INSERT3 = "Insert into Sub_comments ( comment_Id, member_Id, comment_time, comments) values (?, ?, ?, ?)";
			
			public int insertSub_comments(Sub_commentsBean bean) throws SQLException {
				int result = 0;
				try(
						Connection conn = ds.getConnection();
						PreparedStatement stmt = conn.prepareStatement(INSERT3);
				){
					
					stmt.setInt(1, bean.getComment_Id());
					stmt.setString(2, bean.getMember_Id());
					stmt.setTimestamp(3, bean.getComment_time());
					stmt.setString(4, bean.getComments());
					
					result = stmt.executeUpdate();
					
				 }
				return result;
		    }
	
}
