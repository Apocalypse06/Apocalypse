package com.Apocalypse.member.bean;

public class BookListBean {
	private static final long serialVersionUID = 1L;
	private String member_Id;   					//哪一個會員
	private int book_Id;							//對應之書本ID
	
	
	
	public BookListBean() {
		
	}
	
	
	public String getMember_Id() {
		return member_Id;
	}
	public void setMember_Id(String member_Id) {
		this.member_Id = member_Id;
	}
	public int getBook_Id() {
		return book_Id;
	}
	public void setBook_Id(int book_Id) {
		this.book_Id = book_Id;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

}
