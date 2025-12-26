package com.area.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import javax.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class AreaDAO {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB2");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String ISERT_STMT = "INSERT INTO area(city_name,district) VALUES (?,?)";
	private static final String GET_ALL_STMT = "SELECT area_id,city_name,district FROM area order by area_id";
	private static final String GET_ONE_STMT = "SELECT area_id,city_name,district FROM area where area_id = ?";
	private static final String DELETE = "DELETE FROM area where area_id = ?";
	private static final String UPDATE = "UPDATE area set city_name=?,district=? where area_id =?";

	// 新增
	public void insret(AreaVO areaVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(ISERT_STMT);
			pstmt.setString(1, areaVO.getCityname());
			pstmt.setString(2, areaVO.getDistrict());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	// 刪除
	public void delete(Integer areaid) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, areaid);

			pstmt.execute();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	// 使用pk單一查詢
	public AreaVO findByPrimaryKey(Integer areaid) {
		AreaVO areaVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, areaid);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				areaVO = new AreaVO();
				areaVO.setAreaid(rs.getInt("area_id"));
				areaVO.setCityname(rs.getString("city_name"));
				areaVO.setDistrict(rs.getString("district"));
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		return areaVO;
	}

	// 查詢全部
	public List<AreaVO> getAll() {
		List<AreaVO> list = new ArrayList<AreaVO>();
		AreaVO areaVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				areaVO = new AreaVO();
				areaVO.setAreaid(rs.getInt("area_id"));
				areaVO.setCityname(rs.getString("city_name"));
				areaVO.setDistrict(rs.getString("district"));
				list.add(areaVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}

		}
		return list;
	}
}
