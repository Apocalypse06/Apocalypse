package com.Apocalypse.point.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.Apocalypse.init.GlobalService;
import com.Apocalypse.point.bean.PointBean;

public class PointDao implements IPointDao {
	private DataSource ds = null;

	public PointDao() throws NamingException {
		Context ctx = new InitialContext();
		ds = (DataSource) ctx.lookup(GlobalService.JNDI_DB_NAME);
	}

	@Override
	public PointBean getPointById(int pointId) throws Exception {
		PointBean result = null;

		ResultSet rs = null;
		String sql = "select * from point where pointId=?";
		try (Connection conn = ds.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {

			ps.setInt(1, pointId);
			rs = ps.executeQuery();
			if (rs.next()) {
				result = new PointBean();
				result.setpointId(rs.getInt(1));
				result.setpointName(rs.getString(2));
				result.setPrice(rs.getInt(3));
				result.setProductDesc(rs.getString(4));

			}

		}
		return result;
	}
	//顯示所有點數商品
	@Override
	public List<PointBean> getPoint() throws Exception {
		List<PointBean> pointlist=new ArrayList<>();
		ResultSet rs = null;
		String sql = "select * from point ";
		try (Connection conn = ds.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {

			rs = ps.executeQuery();
			while(rs.next()) {
				PointBean result=new PointBean();
				result = new PointBean();
				result.setpointId(rs.getInt(1));
				result.setpointName(rs.getString(2));
				result.setPrice(rs.getInt(3));
				result.setProductDesc(rs.getString(4));
				
				pointlist.add(result);
			}
		return pointlist;
	}

}
}
