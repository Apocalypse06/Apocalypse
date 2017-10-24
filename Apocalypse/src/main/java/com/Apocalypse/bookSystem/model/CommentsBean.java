package com.Apocalypse.bookSystem.model;

import java.io.Serializable;

public class CommentsBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private int bookId;
	private int commentId;
	private String memberId;
	private String comment;
	
	public CommentsBean() {
	}	
	
	//全部屬性包含之建構子
	public CommentsBean(int bookId, int commentId, String memberId, String comment) {
		this.bookId = bookId;
		this.commentId = commentId;
		this.memberId = memberId;
		this.comment = comment;
	}


	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	@Override
	public String toString() {
		return "CommentsBean [bookId=" + bookId + ", commentId=" + commentId + ", memberId=" + memberId + ", comment="
				+ comment + "]";
	}

}
