package com.Apocalypse.author.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.Apocalypse.author.bean.AuthorBean;

public class AuthorDAO implements IAuthorDAO {

	DataSource ds = null;
	public AuthorDAO() {
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc/MemberDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	//用member_id找出作家資料  (登入時使用,升級成為作家成功時也會使用)
		private static final String SELECT_By_Member_Id = "Select author_Id, pen_Name, member_Id, bank_Account "
				                                    + "  from author where member_Id = ?";

		/* （非 Javadoc）
		 * @see com.Apocalypse.member.model.dao.jdbc.IAuthorDAO#Select_By_Member_Id(java.lang.String)
		 */
		@Override
		public AuthorBean Select_By_Member_Id (String member_Id) throws SQLException  {
			AuthorBean result = null;
			
			try(
				Connection conn = ds.getConnection();
				PreparedStatement stmt = conn.prepareStatement(SELECT_By_Member_Id);
			){
					stmt.setString(1, member_Id);
					try(
						ResultSet rset = stmt.executeQuery();
					){
						if (rset.next()) {
							result = new AuthorBean();
							result.setAuthor_Id(rset.getInt("author_Id"));
							result.setPen_Name(rset.getString("pen_Name"));
							result.setMember_Id(rset.getString("member_Id"));
							result.setBank_Account(rset.getString("bank_Account"));
						
						}
					}
			}
		
			return result;
		}
		
		//用筆名找出作家資料  (升級成作家時用來判斷筆名有沒有重複)
				private static final String SELECT_By_Pen_Name = "Select author_Id, pen_Name, member_Id, bank_Account "
						                                    + "  from author where member_Id = ?";

				/* （非 Javadoc）
				 * @see com.Apocalypse.member.model.dao.jdbc.IAuthorDAO#Select_By_Pen_Name(java.lang.String)
				 */
				@Override
				public AuthorBean Select_By_Pen_Name (String pen_Name) throws SQLException  {
					AuthorBean result = null;
					
					try(
						Connection conn = ds.getConnection();
						PreparedStatement stmt = conn.prepareStatement(SELECT_By_Pen_Name);
					){
							stmt.setString(1, pen_Name);
							try(
								ResultSet rset = stmt.executeQuery();
							){
								if (rset.next()) {
									result = new AuthorBean();
									result.setAuthor_Id(rset.getInt("author_Id"));
									result.setPen_Name(rset.getString("pen_Name"));
									result.setMember_Id(rset.getString("member_Id"));
									result.setBank_Account(rset.getString("bank_Account"));
								
								}
							}
					}
				
					return result;
				}
		
	//新增一筆作家資料(註冊會員)
		private static final String INSERT = "Insert into author (pen_Name, member_Id, bank_Account) values (?, ?, ?)"; 
		/* （非 Javadoc）
		 * @see com.Apocalypse.member.model.dao.jdbc.IAuthorDAO#insertAuthor(com.Apocalypse.member.bean.AuthorBean)
		 */
		@Override
		public AuthorBean insertAuthor(AuthorBean bean) throws SQLException {
			AuthorBean result = null;
			try(
					Connection conn = ds.getConnection();
					PreparedStatement stmt = conn.prepareStatement(INSERT);
			){
				stmt.setString(1, bean.getPen_Name());
				stmt.setString(2, bean.getMember_Id());
				stmt.setString(3, bean.getBank_Account());
				
				
				int i = stmt.executeUpdate();
				if (i == 1) {
					result = this.Select_By_Member_Id(bean.getMember_Id());
				}
			 }
			return result;
	    }
}
