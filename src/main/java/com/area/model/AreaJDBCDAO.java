package com.area.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AreaJDBCDAO implements AreaDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/cja103g3?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "357753";
	
	private static final String INSERT_STMT = "INSERT INTO area(city_name,district) VALUES (?,?)";
	private static final String GET_ALL_STMT = "SELECT area_id,city_name,district FROM area order by area_id";
	private static final String GET_ONE_STMT = "SELECT area_id,city_name,district FROM area where area_id = ?";
	private static final String DELETE = "DELETE FROM area where area_id = ?";
	private static final String UPDATE = "UPDATE area set city_name=?,district=? where area_id =?";
	
	@Override
	public void insert(AreaVO areaVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, areaVO.getCityName());
			pstmt.setString(2, areaVO.getDistrict());
			
			pstmt.executeUpdate();
			
		}catch(ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+e.getMessage());
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
	@Override
	public void update(AreaVO areaVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
		
			pstmt.setString(1, areaVO.getCityName());
			pstmt.setString(2, areaVO.getDistrict());
			pstmt.setInt(3, areaVO.getAreaId());
			
			pstmt.executeUpdate();
			
		}catch(ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+e.getMessage());
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
	@Override
	public void delete(Integer areaid) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, areaid);
			
			pstmt.executeUpdate();
			
		}catch(ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+e.getMessage());
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
	@Override
	public AreaVO findByPrimaryKey(Integer areaid) {
		
		AreaVO areaVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, areaid);
			
			rs = pstmt.executeQuery();
			
			
			while(rs.next()) {
				areaVO = new AreaVO();
				areaVO.setAreaId(rs.getInt("area_id"));
				areaVO.setCityName(rs.getString("city_name"));
				areaVO.setDistrict(rs.getString("district"));
				
			}
		}catch(ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+e.getMessage());
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
		
		return areaVO;
	}
	@Override
	public List<AreaVO> getAll() {
		List<AreaVO> list = new ArrayList<AreaVO>();
		AreaVO areaVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				areaVO = new AreaVO();
				areaVO.setAreaId(rs.getInt("area_id"));
				areaVO.setCityName(rs.getString("city_name"));
				areaVO.setDistrict(rs.getString("district"));
				list.add(areaVO);
				
			}
		}catch(ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+e.getMessage());
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
		
		return list;
	}
	
	@Override
	public AreaVO findByCityAndDistrict(String cityName, String district) {

	    AreaVO areaVO = null;
	    Connection con = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;

	    try {
	        
	        Class.forName(driver);
	        con = DriverManager.getConnection(url, userid, passwd);

	        String sql = "SELECT area_id, city_name, district "
	                   + "FROM area WHERE city_name = ? AND district = ?";

	        pstmt = con.prepareStatement(sql);
	        pstmt.setString(1, cityName);
	        pstmt.setString(2, district);

	        rs = pstmt.executeQuery();

	        if (rs.next()) {
	            areaVO = new AreaVO();
	            areaVO.setAreaId(rs.getInt("area_id"));
	            areaVO.setCityName(rs.getString("city_name"));
	            areaVO.setDistrict(rs.getString("district"));
	        }

	    } catch (ClassNotFoundException e) {
	        throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
	    } catch (SQLException se) {
	        throw new RuntimeException("A database error occured. " + se.getMessage());
	    } finally {
	        if (rs != null) try { rs.close(); } catch (SQLException e) {}
	        if (pstmt != null) try { pstmt.close(); } catch (SQLException e) {}
	        if (con != null) try { con.close(); } catch (SQLException e) {}
	    }

	    return areaVO;
	}
	public static void main(String[] args) {
		AreaJDBCDAO dao = new AreaJDBCDAO();
		
		//新增
//		AreaVO areaVO1 = new AreaVO();
//		areaVO1.setCityName("桃園市");
//		areaVO1.setDistrict("大園區");
//		dao.insert(areaVO1);
		
		//修改
//		AreaVO areaVO2 = new AreaVO();
//		areaVO2.setAreaId(6);
//		areaVO2.setCityName("臺南市");
//		areaVO2.setDistrict("新化區");
//		dao.update(areaVO2);
		
		//刪除
//		dao.delete(6);
		
		//單一查詢
//		AreaVO areaVO3 = dao.findByPrimaryKey(5);
//		System.out.print(areaVO3.getAreaId() + ".vu");
//		System.out.print(areaVO3.getCityName() + ",");
//		System.out.print(areaVO3.getDistrict() );
		
		//查詢全部
		List<AreaVO> list = dao.getAll();
		for(AreaVO xArea : list) {
			System.out.print(xArea.getAreaId()+".");
			System.out.print(xArea.getCityName()+",");
			System.out.print(xArea.getDistrict()+"\n");
		}
				
	}
	
}
