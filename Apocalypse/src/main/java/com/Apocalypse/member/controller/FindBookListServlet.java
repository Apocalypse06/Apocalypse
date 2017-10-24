package com.Apocalypse.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Apocalypse.member.bean.BookBean;
import com.Apocalypse.member.bean.MemberBean;
import com.Apocalypse.member.model.service.BookService;
import com.google.gson.Gson;

/**
 * Servlet implementation class FindBookList
 */
@WebServlet("/login/FindBookListServlet")
public class FindBookListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Map<String, String> errorMessage = new HashMap<>();
		HttpSession session = request.getSession(false);
		//request.setAttribute("ErrorMsg", errorMessage);
		
		request.setCharacterEncoding("UTF-8");
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			Gson gson = new Gson();
			MemberBean mb=(MemberBean)session.getAttribute("LoginOK");
			String member_Id=mb.getMember_Id();
			BookService bs=new BookService();
			List<Integer> intlist=new ArrayList<>();
			List<BookBean> booklist=new ArrayList<>();
			//list裝[總共幾頁,正在第幾頁,booklist物件裝有當前頁面的所有BookBean物件]
			List<Object> list=new ArrayList<>();
			BookBean bb=null;
			int total=0;
			int totalpage=0;
			int page=0;
			//每頁有幾本書
			int number=1;
			
          try {
        	  total=bs.getBookCounts(member_Id);
        	 
		} catch (Exception e) {
			System.out.println("getBookCounts()方法出錯");
			e.printStackTrace();
		}
        
          if(total%number==0) {
        	  totalpage=(total/number);
          }else {
        	  totalpage=(total/number)+1;
          }
          //放入總共幾頁
          list.add(totalpage);
          
          String pageNumber = request.getParameter("pageNumber");
          //System.out.println(pageNumber);
          if(pageNumber == null) {
        	 page=1;
        	 
          }else if(Integer.parseInt(pageNumber)<=0||Integer.parseInt(pageNumber)>totalpage){
        	  page=Integer.parseInt(request.getParameter("oldpageNumber"));
          }else {
        	  page=Integer.parseInt(request.getParameter("pageNumber"));
          }
        //放入正在第幾頁
          list.add(page);
//          System.out.println(page);
			
         
          
          	
          try {
				intlist= bs.SELECT_BOOKID_BY_MEMBERID(member_Id,page,number);
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("SELECT_BOOKID_BY_MEMBERID()方法出錯");
			}
         
          try {
				for (int i : intlist) {
					bb = bs.select_by_id(i);
					booklist.add(bb);
				} 
				//放入booklist物件裝有當前頁面的所有BookBean物件
				list.add(booklist);
//				for(BookBean i:booklist) {
//					System.out.println(i);
//				}
			} catch (Exception e) {
				System.out.println("select_by_id()方法出錯");
			}
          
          
          
//			try {
//				intlist= bs.SELECT_BOOKID_BY_MEMBERID(member_Id);
//			} catch (SQLException e) {
//				e.printStackTrace();
//				System.out.println("SELECT_BOOKID_BY_MEMBERID()方法出錯");
//			}
////         以下2方法皆可取出會員書架中的book_id
//			
////			for(int i=0;i<intlist.size();i++) {
////			System.out.println(list.get(i));
////			}
//			
////			for(int i:intlist) {
////				System.out.println(i);
////			}
////		
//			
//			try {
//				for (int i : intlist) {
//					bb = bs.select_by_id(i);
//					booklist.add(bb);
//				} 
////				for(BookBean i:booklist) {
////					System.out.println(i);
////				}
//			} catch (Exception e) {
//				System.out.println("select_by_id()方法出錯");
//			}
			
			
			
			//System.out.println(list);
			out.println(gson.toJson(list));
			out.close();
			return;
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
