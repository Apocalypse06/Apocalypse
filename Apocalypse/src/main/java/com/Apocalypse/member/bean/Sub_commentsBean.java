package com.Apocalypse.member.bean;


import java.sql.Timestamp;

public class Sub_commentsBean {
	private static final long serialVersionUID = 1L;
	private int sub_comment_id;

	private int comment_Id;
	private String member_Id;
	private java.sql.Timestamp comment_time;
	private java.sql.Timestamp update_time;
	private String comments;

	@Override
	public String toString() {
		return "Sub_commentsBean [sub_comment_id=" + sub_comment_id + ", comment_Id=" + comment_Id + ", member_Id="
				+ member_Id + ", comment_time=" + comment_time + ", update_time=" + update_time + ", comments=" + comments
				+ "]";
	}

	public Sub_commentsBean(int comment_Id, String member_Id, Timestamp comment_time, String comments) {
		super();
		this.comment_Id = comment_Id;
		this.member_Id = member_Id;
		this.comment_time = comment_time;
		this.comments = comments;
	}

	public Sub_commentsBean() {

	}

	public int getSub_comment_id() {
		return sub_comment_id;
	}

	public void setSub_comment_id(int sub_comment_id) {
		this.sub_comment_id = sub_comment_id;
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

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
