package com.Apocalypse.bookSystem.model;

import java.io.Serializable;

public class SubcommentsBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private int subcommentId;
	private int commentId;	
	private int bookId;
	private String memberId;
	private String comments;
	
	public SubcommentsBean() {
	}
	
	public SubcommentsBean(int subcommentId, int commentId, int bookId, String memberId, String comments) {
		this.subcommentId = subcommentId;
		this.commentId = commentId;
		this.bookId = bookId;
		this.memberId = memberId;
		this.comments = comments;
	}


	public int getSubcommentId() {
		return subcommentId;
	}
	public void setSubcommentId(int subcommentId) {
		this.subcommentId = subcommentId;
	}
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	
	@Override
	public String toString() {
		return "SubcommentsBean [subcommentId=" + subcommentId + ", commentId=" + commentId + ", bookId=" + bookId
				+ ", memberId=" + memberId + ", comments=" + comments + "]";
	}


}
