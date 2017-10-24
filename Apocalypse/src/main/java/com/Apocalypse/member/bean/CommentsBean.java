package com.Apocalypse.member.bean;


import java.sql.Timestamp;

public class CommentsBean {
	private static final long serialVersionUID = 1L;
	private int book_Id;
	private int comment_Id;
	private String member_Id; 
	private java.sql.Timestamp comment_time;
	private java.sql.Timestamp update_time;
	private String comment;
	


	public CommentsBean() {
		
	}
		
	

	public CommentsBean(int book_Id, String member_Id, Timestamp comment_time, String comment) {
		super();
		this.book_Id = book_Id;
		this.member_Id = member_Id;
		this.comment_time = comment_time;
		this.comment = comment;
	}





	@Override
	public String toString() {
		return "CommentsBean [book_Id=" + book_Id + ", comment_Id=" + comment_Id + ", member_Id=" + member_Id
				+ ", comment_time=" + comment_time + ", update_time=" + update_time + ", comment=" + comment + "]";
	}



	public int getBook_Id() {
		return book_Id;
	}
	public void setBook_Id(int book_Id) {
		this.book_Id = book_Id;
	}
	public int getComment_Id() {
		return comment_Id;
	}
	public void setComment_Id(int comment_Id) {
		this.comment_Id = comment_Id;
	}
	public String getMember_Id() {
		return member_Id;
	}
	public void setMember_Id(String member_Id) {
		this.member_Id = member_Id;
	}
	
	public java.sql.Timestamp getComment_time() {
		return comment_time;
	}

	public void setComment_time(java.sql.Timestamp comment_time) {
		this.comment_time = comment_time;
	}

	public java.sql.Timestamp getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(java.sql.Timestamp update_time) {
		this.update_time = update_time;
	}

	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	} 
	
	
	
}
