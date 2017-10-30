package com.Apocalypse.point.bean;

public class MemberPoint {

	private int id;
	private String member_Id;
	private int balance;
	public MemberPoint() {
		super();
	}
	public MemberPoint(int id, String member_Id, int balance) {
		this.id = id;
		this.member_Id = member_Id;
		this.balance = balance;
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
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
}
