package com.Apocalypse.bookSystem.model;
import java.io.Serializable;
import java.util.List;

public class VolumeBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private int bookId;
	private int volumeId;	
	private String volumeTitle;	
	private List<ChapterBean> chapterList;
	
	public VolumeBean() {
	}

	//包含全部屬性之建構子
	public VolumeBean(int bookId, int volumeId, String volumeTitle, List<ChapterBean> chapterList) {
		this.bookId = bookId;
		this.volumeId = volumeId;
		this.volumeTitle = volumeTitle;
		this.chapterList = chapterList;
	}	
	
	//不包含chapterList之建構子
	public VolumeBean(int bookId, int volumeId, String volumeTitle) {
		this.bookId = bookId;
		this.volumeId = volumeId;
		this.volumeTitle = volumeTitle;
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

	public String getVolumeTitle() {
		return volumeTitle;
	}

	public void setVolumeTitle(String volumeTitle) {
		this.volumeTitle = volumeTitle;
	}

	public List<ChapterBean> getChapterList() {
		return chapterList;
	}

	public void setChapterList(List<ChapterBean> chapterList) {
		this.chapterList = chapterList;
	}

	@Override
	public String toString() {
		return "VolumeBean [bookId=" + bookId + ", volumeId=" + volumeId + ", volumeTitle=" + volumeTitle + "]";
	}

	public String toStringAll() {
		return "VolumeBean [bookId=" + bookId + ", volumeId=" + volumeId + ", volumeTitle=" + volumeTitle
				+ ", chapterList=" + chapterList.toString() + "]";
	}	
	
	
	
	

	

	
}
