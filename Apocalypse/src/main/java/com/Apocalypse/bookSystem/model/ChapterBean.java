package com.Apocalypse.bookSystem.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class ChapterBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private int bookId;
	private int volumeId;
	private int chapterId;
	private String chapterTitle;
	private String chapterContent;
	private String contentName;
	private Timestamp publishTime;
	private Timestamp lastModified;
	private int price;
	//private Clob chapterContent;
	
	public ChapterBean() {		
	}

	//全部屬性包含之建構子(無內容)
	public ChapterBean(int bookId, int volumeId, int chapterId, String chapterTitle,String contentName,
			Timestamp publishTime, Timestamp lastModified, int price) {

		this.bookId = bookId;
		this.volumeId = volumeId;
		this.chapterId = chapterId;
		this.chapterTitle = chapterTitle;
		this.contentName = contentName;
		this.publishTime = publishTime;
		this.lastModified = lastModified;
		this.price = price;
	}
	
	//寫入dao用
	public ChapterBean(int bookId, int volumeId, int chapterId, String chapterTitle, String chapterContent,
			 String contentName, int price) {
		super();
		this.bookId = bookId;
		this.volumeId = volumeId;
		this.chapterId = chapterId;
		this.chapterTitle = chapterTitle;
		this.chapterContent = chapterContent;
		this.contentName = contentName;
		this.price = price;
	}
	
	
	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getVolumeId() {
		return volumeId;
	}

	public void setVolumeId(int volumeId) {
		this.volumeId = volumeId;
	}

	public int getChapterId() {
		return chapterId;
	}

	public void setChapterId(int chapterId) {
		this.chapterId = chapterId;
	}

	public String getChapterTitle() {
		return chapterTitle;
	}

	public void setChapterTitle(String chapterTitle) {
		this.chapterTitle = chapterTitle;
	}

	public String getChapterContent() {
		return chapterContent;
	}

	public void setChapterContent(String chapterContent) {
		this.chapterContent = chapterContent;
	}

	public String getContentName() {
		return contentName;
	}

	public void setContentName(String contentName) {
		this.contentName = contentName;
	}

	public Timestamp getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Timestamp publishTime) {
		this.publishTime = publishTime;
	}

	public Timestamp getLastModified() {
		return lastModified;
	}

	public void setLastModified(Timestamp lastModified) {
		this.lastModified = lastModified;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	




	@Override
	public String toString() {
		return "ChapterBean [bookId=" + bookId + ", volumeId=" + volumeId + ", chapterId=" + chapterId
				+ ", chapterTitle=" + chapterTitle + ", chapterContent=" + chapterContent
				+ ", contentName=" + contentName + ", publishTime=" + publishTime + ", lastModified=" + lastModified
				+ ", price=" + price + "]";
	}

	
}

