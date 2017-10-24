package com.Apocalypse.bookSystem.model;

import java.io.Serializable;

public class BookStateBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String bookStateType;
	private int    bookStateNumber;
	
	public BookStateBean() {
	}

	public BookStateBean(String bookStateType, int bookStateNumber) {
		this.bookStateType = bookStateType;
		this.bookStateNumber = bookStateNumber;
	}

	
	
	
	public String getBookStateType() {
		return bookStateType;
	}

	public void setBookStateType(String bookStateType) {
		this.bookStateType = bookStateType;
	}

	public int getBookStateNumber() {
		return bookStateNumber;
	}

	public void setBookStateNumber(int bookStateNumber) {
		this.bookStateNumber = bookStateNumber;
	}

	@Override
	public String toString() {
		return "BookStateBean [bookStateType=" + bookStateType + ", bookStateNumber=" + bookStateNumber + "]";
	}
	
	
}
