package com.Apocalypse.bookSystem.model;

import java.io.Serializable;

public class StarsBean implements Serializable{
	private static final long serialVersionUID = 1L;	
	private int bookId;
	private String memberId;
	private int starPoint;
	
	public StarsBean() {
	}

	public StarsBean(int bookId, String memberId, int starPoint) {
		this.bookId = bookId;
		this.memberId = memberId;
		this.starPoint = starPoint;
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

	public void setMemberId(String member_id) {
		this.memberId = member_id;
	}

	public int getStarPoint() {
		return starPoint;
	}

	public void setStarPoint(int starPoint) {
		this.starPoint = starPoint;
	}

	@Override
	public String toString() {
		return "StarsBean [bookId=" + bookId + ", memberId=" + memberId + ", starPoint=" + starPoint + "]";
	}
	
}
