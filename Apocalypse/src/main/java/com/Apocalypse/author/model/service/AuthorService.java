package com.Apocalypse.author.model.service;

import java.sql.SQLException;

import com.Apocalypse.author.bean.AuthorBean;
import com.Apocalypse.author.model.dao.AuthorDAO;
import com.Apocalypse.author.model.dao.IAuthorDAO;

public class AuthorService {
public int check_Pen_Name(String pen_Name)throws SQLException {
		
	    IAuthorDAO dao = new AuthorDAO();
		AuthorBean ab = dao.Select_By_Pen_Name(pen_Name);
        //ab不為null則表示此筆名已存在
        if ( ab != null ) {
         
        	return 1;
        }
        // 傳回null物件,表示該筆名沒人使用
		return 0;
	}
public AuthorBean Select_By_Member_Id(String member_Id)throws SQLException {
	
    IAuthorDAO dao = new AuthorDAO();
	AuthorBean ab = dao.Select_By_Member_Id(member_Id);
    //ab不為null則表示此筆名已存在
    
	return ab;
}
public AuthorBean insertMember(AuthorBean AB)throws SQLException {
	
	IAuthorDAO dao = new AuthorDAO();
	AuthorBean ab = dao.insertAuthor(AB);
      	
	return ab;
 }
}
