package com.Apocalypse.member.bean;

import java.sql.Blob;

public class BookBean {
	private static final long serialVersionUID = 1L;
	private int book_Id;   					//流水號主鍵
	private String title;					//書名
	private int author_Id;   			   	//對應作者ID 
	private String book_state;           	//新書上傳
	private java.sql.Date publish_Date;	    //發布日期 
	private String intro;     				//簡介
	private String classify;                //分類 
	private int clicks;                     //點擊數
	private int tickets;                    //獲得月票數目
	//private Blob surface_Plot;			//封面圖  
	private String surface_Plot_Name;		//封面圖名稱 
	
	
public BookBean() {
		
	}
	
	
	@Override
	public String toString() {
		return "BookBean [book_Id=" + book_Id + ", title=" + title + ", author_Id=" + author_Id + ", book_state="
				+ book_state + ", publish_Date=" + publish_Date + ", intro=" + intro + ", classify=" + classify
				+ ", clicks=" + clicks + ", tickets=" + tickets + ", surface_Plot_Name=" + surface_Plot_Name + "]";
	}
	
	public int getBook_Id() {
		return book_Id;
	}
	public void setBook_Id(int book_Id) {
		this.book_Id = book_Id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getAuthor_Id() {
		return author_Id;
	}
	public void setAuthor_Id(int author_Id) {
		this.author_Id = author_Id;
	}
	public String getBook_state() {
		return book_state;
	}
	public void setBook_state(String book_state) {
		this.book_state = book_state;
	}
	public java.sql.Date getPublish_Date() {
		return publish_Date;
	}
	public void setPublish_Date(java.sql.Date publish_Date) {
		this.publish_Date = publish_Date;
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
//	public Blob getSurface_Plot() {
//		return surface_Plot;
//	}
//	public void setSurface_Plot(Blob surface_Plot) {
//		this.surface_Plot = surface_Plot;
//	}
	public String getSurface_Plot_Name() {
		return surface_Plot_Name;
	}
	public void setSurface_Plot_Name(String surface_Plot_Name) {
		this.surface_Plot_Name = surface_Plot_Name;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
