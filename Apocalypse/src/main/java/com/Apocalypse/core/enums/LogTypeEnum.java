package com.Apocalypse.core.enums;

public enum LogTypeEnum {
    COMMONLOG(1, "COMMON", "一般"),
    SYSLOG(2, "SYS", "系統"),
	LOGINLOG(3, "LOGIN", "登入"),
	USERLOG(4, "USER", "操作者");
	
    private int value;
    private String ehName;
    private String chName;
    
    private LogTypeEnum(int value, String ehName, String chName) {
    	this.value = value;
    	this.ehName = ehName;
    	this.chName = chName;
	}

	public int getValue() {
		return value;
	}

	public String getEhName() {
		return ehName;
	}

	public String getChName() {
		return chName;
	}

	public static void main(String[] args){
	   	for(LogTypeEnum name :LogTypeEnum.values()){
	   		System.out.println(name+" : "+name.getValue()+"/"+name.getEhName()+"/"+name.getChName());
	   	}
    }
}
