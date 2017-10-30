package com.Apocalypse.member.model.dao.jdbc;

public class Test {

	public static void main(String[] args) {
		StringBuffer sb=new StringBuffer();
		sb.append("select empid,empname form emp \n");
		sb.append("where empId=").append("emp");
		System.out.println(sb.toString());

	}

}
