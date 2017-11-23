package com.Apocalypse.member.bean;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Timestamp;
import java.sql.Date;

public class MemberBean implements Serializable{
      
	private static final long serialVersionUID = 1L;
	private String member_Id;   					//流水號主鍵
	private String account;							//帳號 這裡是Email
	private String pswd;   			   			 	//密碼
	private String nick_Name;           			//暱稱
	private java.sql.Date birthday;	   			    //生日
	private String cellphone;     					//手機
	private String email;                           //email 
	private String gender;                          //性別
	//private Blob picture;							//大頭貼
	private String picture_Name;					//大頭貼檔名 
	private int points;                     		//現有點數 
	private int tickets;                  		    //月票
	private int role_id;                    	    //角色等級 
	private String creditCardNo;             		//信用卡帳號
	private java.sql.Date reg_date;			        //註冊日期
	private java.sql.Timestamp lastLogin;	        //最後登入時間
	private String lastLogin_Ip;					//最後登入ip
	
	
	
	public MemberBean() {
		super();
	}
 


	public MemberBean(String member_Id, String account, String pswd, String nick_Name, Date birthday, String cellphone,
			String email, String gender, Date reg_date) {
		super();
		this.member_Id = member_Id;
		this.account = account;
		this.pswd = pswd;
		this.nick_Name = nick_Name;
		this.birthday = birthday;
		this.cellphone = cellphone;
		this.email = email;
		this.gender = gender;
		this.reg_date = reg_date;
	}



	public MemberBean(String member_Id, String account, String pswd, String nick_Name, Date birthday, String cellphone,
			String email, String gender, String picture_Name, int points, int tickets, int role_id,
			String creditCardNo, Date reg_date, Timestamp lastLogin, String lastLogin_Ip) {
		super();
		this.member_Id = member_Id;
		this.account = account;
		this.pswd = pswd;
		this.nick_Name = nick_Name;
		this.birthday = birthday;
		this.cellphone = cellphone;
		this.email = email;
		this.gender = gender;
		this.picture_Name = picture_Name;
		this.points = points;
		this.tickets = tickets;
		this.role_id = role_id;
		this.creditCardNo = creditCardNo;
		this.reg_date = reg_date;
		this.lastLogin = lastLogin;
		this.lastLogin_Ip = lastLogin_Ip;
	}



	public String getMember_Id() {
		return member_Id;
	}



	public void setMember_Id(String member_Id) {
		this.member_Id = member_Id;
	}



	public String getAccount() {
		return account;
	}



	public void setAccount(String account) {
		this.account = account;
	}



	public String getPswd() {
		return pswd;
	}



	public void setPswd(String pswd) {
		this.pswd = pswd;
	}



	public String getNick_Name() {
		return nick_Name;
	}



	public void setNick_Name(String nick_Name) {
		this.nick_Name = nick_Name;
	}



	public java.sql.Date getBirthday() {
		return birthday;
	}



	public void setBirthday(java.sql.Date birthday) {
		this.birthday = birthday;
	}



	public String getCellphone() {
		return cellphone;
	}



	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getGender() {
		return gender;
	}



	public void setGender(String gender) {
		this.gender = gender;
	}



	

	public String getPicture_Name() {
		return picture_Name;
	}



	public void setPicture_Name(String picture_Name) {
		this.picture_Name = picture_Name;
	}



	public int getPoints() {
		return points;
	}



	public void setPoints(int points) {
		this.points = points;
	}



	public int getTickets() {
		return tickets;
	}



	public void setTickets(int tickets) {
		this.tickets = tickets;
	}



	public int getRole_id() {
		return role_id;
	}



	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}



	public String getCreditCardNo() {
		return creditCardNo;
	}



	public void setCreditCardNo(String creditCardNo) {
		this.creditCardNo = creditCardNo;
	}



	public java.sql.Date getReg_date() {
		return reg_date;
	}



	public void setReg_date(java.sql.Date reg_date) {
		this.reg_date = reg_date;
	}



	public java.sql.Timestamp getLastLogin() {
		return lastLogin;
	}



	public void setLastLogin(java.sql.Timestamp lastLogin) {
		this.lastLogin = lastLogin;
	}



	public String getLastLogin_Ip() {
		return lastLogin_Ip;
	}



	public void setLastLogin_Ip(String lastLogin_Ip) {
		this.lastLogin_Ip = lastLogin_Ip;
	}

	
	 
}
