package com.Apocalypse.point.bean;

import java.sql.Timestamp;

public class PointTransation {

	private int id;
	private String member_Id;
	private String transationType;
	private int cash;
	private int newBalance;
	private String token;
	private Timestamp time;
	public PointTransation() {
		super();
	}
	public PointTransation(int id, String member_Id, String transationType, int cash, int newBalance, String token) {
		
		this.id = id;
		this.member_Id = member_Id;
		this.transationType = transationType;
		this.cash = cash;
		this.newBalance = newBalance;
		this.token = token;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMember_Id() {
		return member_Id;
	}
	public void setMember_Id(String member_Id) {
		this.member_Id = member_Id;
	}
	public String getTransationType() {
		return transationType;
	}
	public void setTransationType(String transationType) {
		this.transationType = transationType;
	}
	public int getCash() {
		return cash;
	}
	public void setCash(int cash) {
		this.cash = cash;
	}
	public int getNewBalance() {
		return newBalance;
	}
	public void setNewBalance(int newBalance) {
		this.newBalance = newBalance;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	
}
