package com.Apocalypse.point.bean;

import java.sql.Timestamp;

public class OrderBean {

	private String orderId;
	private String account;
	private String productId;
	private String merchantID;
	private String merchantTradeNo;
	private Timestamp merchantTradeDate;
	private String paymentType;
	private int totalAmount;
	private String returnUrl;
	private String orderStatus;
	private String paymentStatus;
}
