package com.Apocalypse.member.bean;

import java.io.Serializable;

public class AuthorBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int author_Id;   				//流水號主鍵
	private String pen_Name;				//筆名
	private String member_Id;   			//會員流水號(會員的主鍵)
	private String bank_Account; 			//銀行帳號
	
	
	
	
			
	public AuthorBean(String pen_Name, String member_Id, String bank_Account) {
		
		this.pen_Name = pen_Name;
		this.member_Id = member_Id;
		this.bank_Account = bank_Account;
	}


	public AuthorBean() {
		
	}
	
	
	public int getAuthor_Id() {
		return author_Id;
	}
	public void setAuthor_Id(int author_Id) {
		this.author_Id = author_Id;
	}
	public String getPen_Name() {
		return pen_Name;
	}
	public void setPen_Name(String pen_Name) {
		this.pen_Name = pen_Name;
	}
	public String getMember_Id() {
		return member_Id;
	}
	public void setMember_Id(String member_Id) {
		this.member_Id = member_Id;
	}
	public String getBank_Account() {
		return bank_Account;
	}
	public void setBank_Account(String bank_Account) {
		this.bank_Account = bank_Account;
	}
	
	
}
