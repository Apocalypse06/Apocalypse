package com.Apocalypse.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Apocalypse.member.bean.CommentsBean;
import com.Apocalypse.member.bean.MemberBean;
import com.Apocalypse.member.bean.Sub_commentsBean;
import com.Apocalypse.member.model.service.BookService;
import com.Apocalypse.member.model.service.MemberService;
import com.google.gson.Gson;




@WebServlet("/login/WriteComment")
public class WriteComment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		HttpSession session = request.getSession(false);
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		
		java.sql.Timestamp comment_time= new java.sql.Timestamp(System.currentTimeMillis());
		String comment = request.getParameter("comment");
		String subcomment = request.getParameter("subcomment");
	
		String get = request.getParameter("mcomment_id");
	   
		int mcomment_id=0;
		if(get != null) {
	    	 mcomment_id = Integer.parseInt(get);
	    	 
	    }
		//System.out.println(mcomment_id);
		String get2 = request.getParameter("book_Id");
		 int book_Id = Integer.parseInt(get2);
	    	 
	
		
		MemberBean mb=null;
		MemberBean mb1=null;
		BookService bs= new BookService();
		MemberService ms= new MemberService();
		mb=(MemberBean)session.getAttribute("LoginOK");
		
		//List<MemberBean> submemberlist=new ArrayList<>();
		List<Sub_commentsBean> subcommentslist=new ArrayList<>();
		List<List<Sub_commentsBean>> allsubcommentslist=new ArrayList<>();
		
		List<MemberBean> memberlist=new ArrayList<>();
		List<MemberBean> submemberlist=new ArrayList<>();
		List<List<MemberBean>> allsubcommentsMemberlist=new ArrayList<>();
		
		List<CommentsBean> commentlist=new ArrayList<>();
		List<Integer> totlelist=new ArrayList<>();
		List<Object> list=new ArrayList<>();
		//list[memberlist(MemberBean)
		//	   ,commentlist(CommentsBean)
		//     ,totlelist(totle)
		//     ,allsubcommentslist(subcommentslist(Sub_commentsBean))
		//     ,allsubcommentsMemberlist(submemberlist(MemberBean))]
		int totle=0; 
		
		
		
		if (comment != null && comment.trim().length() != 0) {
			
			CommentsBean cb=new CommentsBean(book_Id, mb.getMember_Id(), comment_time, comment);	
			try {
				bs.insertComments(cb);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("insertComments()方法出錯");
			}
			//System.out.println(cb.toString());
		}
		if(subcomment != null && subcomment.trim().length() != 0) {
			
			Sub_commentsBean scb=new Sub_commentsBean(book_Id,mcomment_id, mb.getMember_Id(), comment_time, subcomment);	
			//System.out.println(scb);
			try {
				bs.insertSub_comments(scb);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("insertsubComments()方法出錯");
			}
			//System.out.println(scb.toString());
		}
		
		try {
			commentlist=bs.selectCommets_by_book_id(book_Id);
			//System.out.println(commentlist);
			for(CommentsBean cb:commentlist) {
				
				totle=bs.getSub_commentsBean(cb.getComment_Id());
				totlelist.add(totle);
				subcommentslist=bs.selectSub_Commets_by_Comments_Id(cb.getComment_Id(),book_Id);
				allsubcommentslist.add(subcommentslist);
				
				for(Sub_commentsBean scb:subcommentslist) {
					mb1= ms.select_by_id(scb.getMember_Id());
					//System.out.println(scb.getMember_Id());
					submemberlist.add(mb1);
					allsubcommentsMemberlist.add(submemberlist);
				}
				
			}
			//System.out.println(commentlist);
			for(CommentsBean cb :commentlist) {
				memberlist.add(ms.select_by_id(cb.getMember_Id()));
			   // System.out.println(ms.select_by_id(cb.getMember_Id()));
			}
			//System.out.println("666"+subcommentslist);
			
			list.add(memberlist);
			list.add(commentlist);
			list.add(totlelist);
			list.add(allsubcommentslist);
			list.add(allsubcommentsMemberlist);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("selectCommets_by_book_id()方法出錯");
		}
		
		
		
		
		out.println(gson.toJson(list));
			out.close();
			return;
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
