package com.Apocalypse.bookSystem.model;

import java.io.Serializable;

public class ClassifyBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String classifyType;
	private int    classifyNumber;
	
	public ClassifyBean() {

	}

	public ClassifyBean(String classifyType, int classifyNumber) {
		this.classifyType = classifyType;
		this.classifyNumber = classifyNumber;
	}

	
	public String getClassifyType() {
		return classifyType;
	}

	public void setClassifyType(String classifyType) {
		this.classifyType = classifyType;
	}

	public int getClassifyNumber() {
		return classifyNumber;
	}

	public void setClassifyNumber(int classifyNumber) {
		this.classifyNumber = classifyNumber;
	}

	@Override
	public String toString() {
		return "ClassifyBean [classifyType=" + classifyType + ", classifyNumber=" + classifyNumber + "]";
	}
		
}
