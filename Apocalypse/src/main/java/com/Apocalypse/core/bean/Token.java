package com.Apocalypse.core.bean;

import java.sql.Timestamp;

public class Token {
	private String token;
	private String userId;
	private String deviceId;
	private Timestamp exprieDate;
	private String osType;
	private String msgUid;
	private Timestamp createDate;
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public Timestamp getExprieDate() {
		return exprieDate;
	}
	public void setExprieDate(Timestamp exprieDate) {
		this.exprieDate = exprieDate;
	}
	public String getOsType() {
		return osType;
	}
	public void setOsType(String osType) {
		this.osType = osType;
	}
	public String getMsgUid() {
		return msgUid;
	}
	public void setMsgUid(String msgUid) {
		this.msgUid = msgUid;
	}
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
}
