package com.Apocalypse.point.bean;

import java.util.List;

public interface IPointService {

	public int addProduct(String productName) throws Exception;

	public List<PointBean> getProduct(String productName) throws Exception;

	public PointBean getProductDetail(String productName) throws Exception;

	public PointBean getProductName(String productName) throws Exception;

	public PointBean getProductPrice(String productName) throws Exception;

	public PointBean getProductDesc(String productName) throws Exception;

	public PointBean getProductPicture(String picturePath) throws Exception;

	public int updateProduct(String productName, String price, String productDesc) throws Exception;
}