package com.Apocalypse.member.model.service;

import java.sql.SQLException;
import java.util.List;

import com.Apocalypse.member.bean.BookBean;
import com.Apocalypse.member.bean.CommentsBean;
import com.Apocalypse.member.bean.Sub_commentsBean;
import com.Apocalypse.member.model.dao.jdbc.BookDAO;

public class BookService {
//	public List<Integer> SELECT_BOOKID_BY_MEMBERID(String member_Id)throws SQLException {
//		
//		BookDAO dao = new BookDAO();
//		List<Integer> list = dao.selectbook_by_id(member_Id);
//          	
//		return list;
//	}
	public List<Integer> SELECT_BOOKID_BY_MEMBERID(String member_Id, int page, int number)throws SQLException {
		
		BookDAO dao = new BookDAO();
		List<Integer> list = dao.selectbook_by_id(member_Id, page, number);
          	
		return list;
	}
	public BookBean select_by_id(int book_Id)throws SQLException {
			
		BookDAO dao = new BookDAO();
			BookBean bb = dao.select_by_id(book_Id);
	          	
			return bb;
	}
	public int getBookCounts(String member_Id)throws SQLException {
		
		BookDAO dao = new BookDAO();
			int total = dao.getBookCounts(member_Id);
	          	
			return total;
	}
	public int insertComments(CommentsBean bean)throws SQLException {
		
		BookDAO dao = new BookDAO();
			int result = dao.insertComments(bean);
	          	
			return result;
	}
	public List<CommentsBean> selectCommets_by_book_id(int book_Id)throws SQLException {
		
		BookDAO dao = new BookDAO();
		List<CommentsBean> list = dao.selectCommets_by_book_id(book_Id);
          	
		return list;
	}
	public List<Sub_commentsBean> selectSub_Commets_by_Comments_Id(int comment_Id , int book_Id)throws SQLException {
		
		BookDAO dao = new BookDAO();
		List<Sub_commentsBean> list = dao.selectSub_Commets_by_Comments_Id(comment_Id , book_Id);
          	
		return list;
	}
	public int getSub_commentsBean(int comment_Id)throws SQLException {
		
		BookDAO dao = new BookDAO();
			int total = dao.getSub_commentsBean(comment_Id);
	          	
			return total;
	}
	public int insertSub_comments(Sub_commentsBean bean)throws SQLException {
		
		BookDAO dao = new BookDAO();
		int result = dao.insertSub_comments(bean);
	          	
			return result;
	}

}
