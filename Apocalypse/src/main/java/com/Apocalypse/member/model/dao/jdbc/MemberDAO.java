package com.Apocalypse.member.model.dao.jdbc;


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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.Apocalypse.member.bean.MemberBean;


@Repository
public class MemberDAO {

	
	@Autowired
	public JdbcTemplate jdbcTemplate;
	
	private MemberBean memberBean;
	//用帳號找出會員資料  (登入時使用,註冊成功時也會使用)
	private static final String SELECT_BY_ACCOUNT = "Select member_Id, account, pswd, nick_Name, birthday, cellphone, email, gender, picture_Name, points, tickets, role_id, creditCardNo, reg_date, lastLogin, lastLogin_Ip "
			                                    + "  from member where account = ?";

	public MemberBean select (String account) throws SQLException  {
		RowMapper<MemberBean> result=new BeanPropertyRowMapper<>(MemberBean.class);
		memberBean=(MemberBean) jdbcTemplate.queryForObject(SELECT_BY_ACCOUNT, result,account);
		
		return memberBean;
	}
	
	    //用member_id找出會員資料  
		private static final String SELECT_BY_ID = "Select member_Id, account, pswd, nick_Name, birthday, cellphone, email, gender, picture_Name, points, tickets, role_id, creditCardNo, reg_date, lastLogin, lastLogin_Ip "
				                                    + "  from member where member_Id = ?";

		public MemberBean select_by_id(String member_Id) throws SQLException {
			RowMapper<MemberBean> result=new BeanPropertyRowMapper<>(MemberBean.class);
			memberBean=(MemberBean) jdbcTemplate.queryForObject(SELECT_BY_ID, result,member_Id);
			
			return memberBean;
		}
		
		//用email找出會員資料  (忘記密碼時使用)
		private static final String SELECT_BY_EMAIL = "Select member_Id, account, pswd, nick_Name, birthday, cellphone, email, gender, picture_Name, points, tickets, role_id, creditCardNo, reg_date, lastLogin, lastLogin_Ip "
				                                    + "  from member where email = ?";

		public MemberBean select_by_email(String email) throws SQLException {
			RowMapper<MemberBean> result=new BeanPropertyRowMapper<>(MemberBean.class);
			memberBean=(MemberBean) jdbcTemplate.queryForObject(SELECT_BY_EMAIL, result,email);
			
			return memberBean;
			}
	//更新會員最後登入時間跟最後登入ip (登入時使用)
	private static final String UPDATE = "Update member set lastLogin=?, lastLogin_Ip=? where account=?";

	public MemberBean changeLastLogin(String account, java.sql.Timestamp lastLogin, String lastLogin_Ip) throws SQLException {
		MemberBean result = null;
		
		
		return result;
    }
	//新增一筆會員資料(註冊會員)
	private static final String INSERT = "Insert into member (member_Id, account, pswd, nick_Name, birthday, cellphone, gender, email, reg_date) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	public MemberBean insertMember(MemberBean bean) throws SQLException {
		MemberBean result = null;
		
		 
		return result;
    }
	//更改會員資料
	private static final String UPDATE2 = "Update member set email=?, pswd=?, nick_Name=?, birthday=?, cellphone=?, gender=? where account=?";

	public MemberBean changeMember(MemberBean bean) throws SQLException {
		MemberBean result = null;
		
		return result;
    }
	
	//點擊驗證信後,由臨時會員變成正式會員 
	private static final String UPDATE3 = "Update member set role_id=? where member_Id=?";

	public MemberBean changerole_id(String member_Id,int role_id) throws SQLException {
		MemberBean result = null;
		
		return result;
    }
	//更改會員密碼 (忘記密碼時使用)
	private static final String UPDATE4 = "Update member set Pswd=? where member_Id=?";
	
	public MemberBean changePswd(String member_Id,String Pswd) throws SQLException{
		MemberBean result = null;
		
		return result;
    }
	//找出會員的權限
	private static final String select_permission = "SELECT permission FROM role_permission WHERE role_id=?";
	public List<Integer> select_permission (int role_id) throws SQLException  {
		List<Integer> list = new ArrayList<>();
				
		
		return list;
	}
	//找出會員的等級名稱
	private static final String select_role_Name = "SELECT role_Name FROM role WHERE role_id=?";
	public String select_role_Name (int role_id) throws SQLException  {
	String role_Name=null;					
	
		return role_Name;
	}
	
	//更改會員密碼 (忘記密碼時使用)
		private static final String UPDATE6 = "Update member set picture=? ,picture_Name=? where member_Id=?";
		
		public MemberBean changePicture(InputStream picture ,String picture_Name,String member_Id) throws SQLException{
			MemberBean result = null;
		
			return result;
	    }
	
}
