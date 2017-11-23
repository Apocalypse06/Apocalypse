package com.Apocalypse.product.model.dao;

import java.util.List;

import com.Apocalypse.product.bean.ProductBean;

public interface IProductDao {
	public ProductBean getProductById()throws Exception;
	public List<ProductBean>getProduct()throws Exception;
}