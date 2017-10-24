package com.Apocalypse.bookSystem.model;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Date;

public class BookBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private int    bookId;
	private String title;
	private int    authorId;    
	private String bookState;
	private java.sql.Date publishDate;
	private String intro;
	private String classify;
	private int    clicks;
	private int    tickets;
	private java.sql.Blob surfacePlot;
	private String surface_Plot_Name;
	private String penName;
	
	
	public BookBean() {
	}

	//全部屬性包含之建構子
	public BookBean(int bookId, String title, int authorId, String bookState, Date publishDate, String intro,
			String classify, int clicks, int tickets, Blob surfacePlot, String surface_Plot_Name, String penName) {
		this.bookId = bookId;
		this.title = title;
		this.authorId = authorId;
		this.bookState = bookState;
		this.publishDate = publishDate;
		this.intro = intro;
		this.classify = classify;
		this.clicks = clicks;
		this.tickets = tickets;
		this.surfacePlot = surfacePlot;
		this.surface_Plot_Name = surface_Plot_Name;
		this.penName = penName;
	}

	//測試用insert之建構子
	public BookBean(String title, int authorId, String bookState, Date publishDate, String intro, String classify,
			int clicks, int tickets, String surface_Plot_Name) {
		this.title = title;
		this.authorId = authorId;
		this.bookState = bookState;
		this.publishDate = publishDate;
		this.intro = intro;
		this.classify = classify;
		this.clicks = clicks;
		this.tickets = tickets;
		this.surface_Plot_Name = surface_Plot_Name;
	}
	
	
	//無Blob之建構子
	public BookBean(int bookId, String title, int authorId, String bookState, Date publishDate, String intro,
			String classify, int clicks, int tickets, String surface_Plot_Name, String penName) {
		this.bookId = bookId;
		this.title = title;
		this.authorId = authorId;
		this.bookState = bookState;
		this.publishDate = publishDate;
		this.intro = intro;
		this.classify = classify;
		this.clicks = clicks;
		this.tickets = tickets;
		this.surface_Plot_Name = surface_Plot_Name;
		this.penName = penName;
	}
	
	
	//無Blob之建構子、作者之建構子
	public BookBean(int bookId, String title, int authorId, String bookState, Date publishDate, String intro,
			String classify, int clicks, int tickets, String surface_Plot_Name) {
		super();
		this.bookId = bookId;
		this.title = title;
		this.authorId = authorId;
		this.bookState = bookState;
		this.publishDate = publishDate;
		this.intro = intro;
		this.classify = classify;
		this.clicks = clicks;
		this.tickets = tickets;
		this.surface_Plot_Name = surface_Plot_Name;
	}
	
	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public String getBookState() {
		return bookState;
	}

	public void setBookState(String bookState) {
		this.bookState = bookState;
	}

	public java.sql.Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(java.sql.Date publishDate) {
		this.publishDate = publishDate;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getClassify() {
		return classify;
	}

	public void setClassify(String classify) {
		this.classify = classify;
	}

	public int getClicks() {
		return clicks;
	}

	public void setClicks(int clicks) {
		this.clicks = clicks;
	}

	public int getTickets() {
		return tickets;
	}

	public void setTickets(int tickets) {
		this.tickets = tickets;
	}

	public java.sql.Blob getSurfacePlot() {
		return surfacePlot;
	}

	public void setSurfacePlot(java.sql.Blob surfacePlot) {
		this.surfacePlot = surfacePlot;
	}

	public String getSurface_Plot_Name() {
		return surface_Plot_Name;
	}

	public void setSurface_Plot_Name(String surface_Plot_Name) {
		this.surface_Plot_Name = surface_Plot_Name;
	}

	public String getPenName() {
		return penName;
	}

	public void setPenName(String penName) {
		this.penName = penName;
	}

	@Override
	public String toString() {
		return "BookBean [bookId=" + bookId + ", title=" + title + ", authorId=" + authorId + ", bookState=" + bookState
				+ ", publishDate=" + publishDate + ", intro=" + intro + ", classify=" + classify + ", clicks=" + clicks
				+ ", tickets=" + tickets + ", surface_Plot_Name=" + surface_Plot_Name + ", penName=" + penName + "]";
	}


		
}
