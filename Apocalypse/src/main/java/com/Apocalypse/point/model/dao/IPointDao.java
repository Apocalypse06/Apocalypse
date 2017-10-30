package com.Apocalypse.point.model.dao;

import java.util.List;

import com.Apocalypse.point.bean.PointBean;

public interface IPointDao {
	public PointBean getPointById(int pointId) throws Exception;

	public List<PointBean> getPoint() throws Exception;
}