package com.Apocalypse.member.model.service;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.Apocalypse.member.bean.MemberBean;
import com.Apocalypse.member.model.dao.jdbc.MemberDAO;


@Service
public class MemberService {
	
	public MemberBean checkIDPassword(String account, String pswd)throws SQLException {
		
		MemberDAO dao = new MemberDAO();
		MemberBean mb = dao.select(account);
        // 如果mb不等於 null 而且參數 password等於mb內的password) {
        if ( mb != null && pswd.equals(mb.getPswd())) {
        	 //mb = dao.changeLastLogin(account,lastLogin,lastLogin_Ip);
        	return mb;
        }
        // 傳回null物件
		return null;
	}
	
	public MemberBean changeLastLogin(String account, java.sql.Timestamp lastLogin, String lastLogin_Ip)throws SQLException {
		
		MemberDAO dao = new MemberDAO();
		MemberBean mb = dao.changeLastLogin(account,lastLogin,lastLogin_Ip);
          	
		return mb;
	}
	
	public MemberBean select_by_email(String email)throws SQLException {
		
		MemberDAO dao = new MemberDAO();
		MemberBean mb = dao.select_by_email(email);
          	
		return mb;
	}
	
	public MemberBean changerole_id(String member_Id,int role_id)throws SQLException {
		
		MemberDAO dao = new MemberDAO();
		MemberBean mb = dao.changerole_id(member_Id, role_id);
          	
		return mb;
	}
	
	public MemberBean changePswd(String member_Id,String Pswd)throws SQLException {
		
		MemberDAO dao = new MemberDAO();
		MemberBean mb = dao.changePswd(member_Id, Pswd);
          	
		return mb;
	}
	
	public int checkAccount(String account)throws SQLException {
		
		MemberDAO dao = new MemberDAO();
		MemberBean mb = dao.select(account);
        
        if ( mb != null) {
            return 1;
        }
        return 0;
	}
	
	public MemberBean changeMember(MemberBean Mb)throws SQLException {
		
		MemberDAO dao = new MemberDAO();
		MemberBean mb = dao.changeMember(Mb);
          	
		return mb;
	}
	
	public MemberBean insertMember(MemberBean Mb)throws SQLException {
		
		MemberDAO dao = new MemberDAO();
		MemberBean mb = dao.insertMember(Mb);
          	
		return mb;
	}
	public List<Integer> select_permission(int role_id)throws SQLException {
			
			MemberDAO dao = new MemberDAO();
			List<Integer> list = dao.select_permission(role_id);
	          	
			return list;
	}
	public String select_role_Name(int role_id)throws SQLException {
		
		MemberDAO dao = new MemberDAO();
		String role_Name = dao.select_role_Name(role_id);
          	
		return role_Name;
	}
	public MemberBean changePicture(InputStream picture ,String picture_Name,String member_Id)throws SQLException {
		
		MemberDAO dao = new MemberDAO();
		MemberBean mb = dao.changePicture(picture, picture_Name, member_Id);
          	
		return mb;
	}
}
