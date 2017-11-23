package com.Apocalypse.core.bean;

import java.sql.Timestamp;

public class ExceptionLog {
	private String exceptionLogOid;
	private String userId;
	private String errRootMsg;
	private String errMsg;
	private String errDate;
	private Timestamp errDatetime;
	private String errAction;
	private String param;
	private String osType;
	
	public String getExceptionLogOid() {
		return exceptionLogOid;
	}
	public void setExceptionLogOid(String exceptionLogOid) {
		this.exceptionLogOid = exceptionLogOid;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getErrRootMsg() {
		return errRootMsg;
	}
	public void setErrRootMsg(String errRootMsg) {
		this.errRootMsg = errRootMsg;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	public String getErrDate() {
		return errDate;
	}
	public void setErrDate(String errDate) {
		this.errDate = errDate;
	}
	public Timestamp getErrDatetime() {
		return errDatetime;
	}
	public void setErrDatetime(Timestamp errDatetime) {
		this.errDatetime = errDatetime;
	}
	public String getErrAction() {
		return errAction;
	}
	public void setErrAction(String errAction) {
		this.errAction = errAction;
	}
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	public String getOsType() {
		return osType;
	}
	public void setOsType(String osType) {
		this.osType = osType;
	}
	
}
