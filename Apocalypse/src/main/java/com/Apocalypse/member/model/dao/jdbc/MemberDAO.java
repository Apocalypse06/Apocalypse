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

import com.Apocalypse.member.bean.MemberBean;



public class MemberDAO {

	
	
	DataSource ds = null;
	public MemberDAO() throws SQLException{
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc/MemberDB");
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}	
	
	
	//用帳號找出會員資料  (登入時使用,註冊成功時也會使用)
	private static final String SELECT_BY_ACCOUNT = "Select member_Id, account, pswd, nick_Name, birthday, cellphone, email, gender, picture_Name, points, tickets, role_id, creditCardNo, reg_date, lastLogin, lastLogin_Ip "
			                                    + "  from member where account = ?";

	public MemberBean select (String account) throws SQLException  {
		MemberBean result = null;
		
		try(
			Connection conn = ds.getConnection();
			PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ACCOUNT);
		){
				stmt.setString(1, account);
				try(
					ResultSet rset = stmt.executeQuery();
				){
					if (rset.next()) {
						result = new MemberBean();
						result.setMember_Id(rset.getString("member_Id"));
						result.setAccount(rset.getString("account"));
						result.setPswd(rset.getString("pswd"));
						result.setNick_Name(rset.getString("nick_Name"));
						result.setBirthday(rset.getDate("birthday"));
						result.setCellphone(rset.getString("cellphone"));
						result.setEmail(rset.getString("email"));
						result.setGender(rset.getString("gender"));
						//result.setPicture(rset.getBlob("picture"));
						result.setPicture_Name(rset.getString("picture_Name"));
						result.setPoints(rset.getInt("points"));
						result.setTickets(rset.getInt("tickets"));
						result.setRole_id(rset.getInt("role_id"));
						result.setCreditCardNo(rset.getString("creditCardNo"));
						result.setReg_date(rset.getDate("reg_date"));
						result.setLastLogin(rset.getTimestamp("lastLogin"));
						result.setLastLogin_Ip(rset.getString("lastLogin_Ip"));
					}
				}
		
		}
	
		return result;
	}
	
	    //用member_id找出會員資料  
		private static final String SELECT_BY_ID = "Select member_Id, account, pswd, nick_Name, birthday, cellphone, email, gender, picture_Name, points, tickets, role_id, creditCardNo, reg_date, lastLogin, lastLogin_Ip "
				                                    + "  from member where member_Id = ?";

		public MemberBean select_by_id(String member_Id) throws SQLException {
			MemberBean result = null;

			try(
				Connection conn = ds.getConnection();
				PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID);
			){
				stmt.setString(1, member_Id);
				try(
						ResultSet rset = stmt.executeQuery();
				){
						if (rset.next()) {
							result = new MemberBean();
							result.setMember_Id(rset.getString("member_Id"));
							result.setAccount(rset.getString("account"));
							result.setPswd(rset.getString("pswd"));
							result.setNick_Name(rset.getString("nick_Name"));
							result.setBirthday(rset.getDate("birthday"));
							result.setCellphone(rset.getString("cellphone"));
							result.setEmail(rset.getString("email"));
							result.setGender(rset.getString("gender"));
							//result.setPicture(rset.getBlob("picture"));
							result.setPicture_Name(rset.getString("picture_Name"));
							result.setPoints(rset.getInt("points"));
							result.setTickets(rset.getInt("tickets"));
							result.setRole_id(rset.getInt("role_id"));
							result.setCreditCardNo(rset.getString("creditCardNo"));
							result.setReg_date(rset.getDate("reg_date"));
							result.setLastLogin(rset.getTimestamp("lastLogin"));
							result.setLastLogin_Ip(rset.getString("lastLogin_Ip"));
						}
				  }
				
			}
			
			return result;
		}
		
		
		
		//用email找出會員資料  (忘記密碼時使用)
		private static final String SELECT_BY_EMAIL = "Select member_Id, account, pswd, nick_Name, birthday, cellphone, email, gender, picture_Name, points, tickets, role_id, creditCardNo, reg_date, lastLogin, lastLogin_Ip "
				                                    + "  from member where email = ?";

		public MemberBean select_by_email(String email) throws SQLException {
			MemberBean result = null;
								
			try(
					Connection conn = ds.getConnection();
					PreparedStatement stmt = conn.prepareStatement(SELECT_BY_EMAIL);
				){
						stmt.setString(1, email);
						try(
								ResultSet rset = stmt.executeQuery();
						){
							if (rset.next()) {
								result = new MemberBean();
								result.setMember_Id(rset.getString("member_Id"));
								result.setAccount(rset.getString("account"));
								result.setPswd(rset.getString("pswd"));
								result.setNick_Name(rset.getString("nick_Name"));
								result.setBirthday(rset.getDate("birthday"));
								result.setCellphone(rset.getString("cellphone"));
								result.setEmail(rset.getString("email"));
								result.setGender(rset.getString("gender"));
								//result.setPicture(rset.getBlob("picture"));
								result.setPicture_Name(rset.getString("picture_Name"));
								result.setPoints(rset.getInt("points"));
								result.setTickets(rset.getInt("tickets"));
								result.setRole_id(rset.getInt("role_id"));
								result.setCreditCardNo(rset.getString("creditCardNo"));
								result.setReg_date(rset.getDate("reg_date"));
								result.setLastLogin(rset.getTimestamp("lastLogin"));
								result.setLastLogin_Ip(rset.getString("lastLogin_Ip"));
							}
						}
						
				}
					
				return result;
			}
	//更新會員最後登入時間跟最後登入ip (登入時使用)
	private static final String UPDATE = "Update member set lastLogin=?, lastLogin_Ip=? where account=?";

	public MemberBean changeLastLogin(String account, java.sql.Timestamp lastLogin, String lastLogin_Ip) throws SQLException {
		MemberBean result = null;
		
		try (
			Connection conn = ds.getConnection();
			PreparedStatement stmt = conn.prepareStatement(UPDATE);
		){
				stmt.setTimestamp(1, lastLogin);
				stmt.setString(2,lastLogin_Ip);
				stmt.setString(3, account);
				
				int i = stmt.executeUpdate();
				if (i == 1) {
					result = this.select(account);
				}			
		 } 
		return result;
    }
	//每月更新成3張月票
	private static final String UPDATE1 = "Update member set tickets=3 where account=?";

	public MemberBean changetickets(String account) throws SQLException {
		MemberBean result = null;
		
		try (
			Connection conn = ds.getConnection();
			PreparedStatement stmt = conn.prepareStatement(UPDATE1);
		){
				
				stmt.setString(1, account);
				
				int i = stmt.executeUpdate();
				if (i == 1) {
					result = this.select(account);
				}			
		 } 
		return result;
    }
	//新增一筆會員資料(註冊會員)
	private static final String INSERT = "Insert into member (member_Id, account, pswd, nick_Name, birthday, cellphone, gender, email, reg_date) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	public MemberBean insertMember(MemberBean bean) throws SQLException {
		MemberBean result = null;
		try(
				Connection conn = ds.getConnection();
				PreparedStatement stmt = conn.prepareStatement(INSERT);
		){
			stmt.setString(1, bean.getMember_Id());
			stmt.setString(2, bean.getAccount());
			stmt.setString(3, bean.getPswd());
			stmt.setString(4, bean.getNick_Name());
			stmt.setDate(5, bean.getBirthday());
			stmt.setString(6, bean.getCellphone());
			stmt.setString(7, bean.getGender());
			stmt.setString(8, bean.getEmail());
			stmt.setDate(9, bean.getReg_date());
			
			int i = stmt.executeUpdate();
			if (i == 1) {
				result = this.select(bean.getAccount());
			}
		 }
		return result;
    }
	//更改會員資料
	private static final String UPDATE2 = "Update member set email=?, pswd=?, nick_Name=?, birthday=?, cellphone=?, gender=? where account=?";

	public MemberBean changeMember(MemberBean bean) throws SQLException {
		MemberBean result = null;
		try (
			Connection conn = ds.getConnection();
			PreparedStatement stmt = conn.prepareStatement(UPDATE2);	
		){
			stmt.setString(1, bean.getEmail());
			stmt.setString(2, bean.getPswd());
			stmt.setString(3, bean.getNick_Name());
			stmt.setDate(4, bean.getBirthday());
			stmt.setString(5, bean.getCellphone());
			stmt.setString(6, bean.getGender());
			stmt.setString(7, bean.getAccount());
			
			int i = stmt.executeUpdate();
			if (i == 1) {
				result = this.select(bean.getAccount());
			}
		 }  
		return result;
    }
	
	//點擊驗證信後,由臨時會員變成制式會員 
	private static final String UPDATE3 = "Update member set role_id=? where member_Id=?";

	public MemberBean changerole_id(String member_Id,int role_id) throws SQLException {
		MemberBean result = null;
		try (
			Connection conn = ds.getConnection();
			PreparedStatement stmt = conn.prepareStatement(UPDATE3);
		){
			stmt.setInt(1, role_id);
			stmt.setString(2, member_Id);
						
			int i = stmt.executeUpdate();
			if (i == 1) {
				result = this.select_by_id(member_Id);
			  }
		}  
		return result;
    }
	//更改會員密碼 (忘記密碼時使用)
	private static final String UPDATE4 = "Update member set Pswd=? where member_Id=?";
	
	public MemberBean changePswd(String member_Id,String Pswd) throws SQLException{
		MemberBean result = null;
		try (
			Connection conn = ds.getConnection();
			PreparedStatement stmt = conn.prepareStatement(UPDATE4);
		){
			stmt.setString(1, Pswd);
			stmt.setString(2, member_Id);
						
			int i = stmt.executeUpdate();
			if (i == 1) {
				result = this.select_by_id(member_Id);
			  }
		}  
		return result;
    }
	//找出會員的權限
	private static final String select_permission = "SELECT permission FROM role_permission WHERE role_id=?";
	public List<Integer> select_permission (int role_id) throws SQLException  {
		List<Integer> list = new ArrayList<>();
		
				
		try(
			Connection conn = ds.getConnection();
			PreparedStatement stmt = conn.prepareStatement(select_permission);
		){
				stmt.setInt(1, role_id);
				try(
					ResultSet rset = stmt.executeQuery();
				){
				  while(rset.next()){
												
						int permission 	= rset.getInt("permission");
						list.add(permission);
					}
				 }
		}
		return list;
	}
	//找出會員的等級名稱
	private static final String select_role_Name = "SELECT role_Name FROM role WHERE role_id=?";
	public String select_role_Name (int role_id) throws SQLException  {
	String role_Name=null;					
		try(
			Connection conn = ds.getConnection();
			PreparedStatement stmt = conn.prepareStatement(select_role_Name);
		){
				stmt.setInt(1, role_id);
				try(
					ResultSet rset = stmt.executeQuery();
				){
					if (rset.next()) {
						 role_Name = rset.getString("role_Name");
							
					}
				 }
		}
		return role_Name;
	}
	
	//更改會員密碼 (忘記密碼時使用)
		private static final String UPDATE6 = "Update member set picture=? ,picture_Name=? where member_Id=?";
		
		public MemberBean changePicture(InputStream picture ,String picture_Name,String member_Id) throws SQLException{
			MemberBean result = null;
			try (
				Connection conn = ds.getConnection();
				PreparedStatement stmt = conn.prepareStatement(UPDATE6);
			){
				stmt.setBinaryStream(1, picture);
				stmt.setString(2, picture_Name);
				stmt.setString(3, member_Id);
							
				int i = stmt.executeUpdate();
				if (i == 1) {
					result = this.select_by_id(member_Id);
				  }
			}  
			return result;
	    }
	
	
}
