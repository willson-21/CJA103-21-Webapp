package com.area.model;

import java.sql.*;
import java.util.*;
import javax.naming.*;
import javax.sql.DataSource;

public class AreaDAO {

    private static DataSource ds = null;
    static {
        try {
            Context ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/cja103g3");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    private static final String INSERT_STMT =
            "INSERT INTO area(city_name, district) VALUES (?, ?)";
    private static final String GET_ALL_STMT =
            "SELECT area_id, city_name, district FROM area ORDER BY area_id";
    private static final String GET_ONE_STMT =
            "SELECT area_id, city_name, district FROM area WHERE area_id = ?";
    private static final String DELETE =
            "DELETE FROM area WHERE area_id = ?";
    private static final String UPDATE =
            "UPDATE area SET city_name=?, district=? WHERE area_id=?";


    public void insert(AreaVO areaVO) {
        try (
            Connection con = ds.getConnection();
            PreparedStatement pstmt = con.prepareStatement(INSERT_STMT)
        ) {
            pstmt.setString(1, areaVO.getCityName());
            pstmt.setString(2, areaVO.getDistrict());
            pstmt.executeUpdate();
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured: " + se.getMessage());
        }
    }

    // ===== 修改 =====
    public void update(AreaVO areaVO) {
        try (
            Connection con = ds.getConnection();
            PreparedStatement pstmt = con.prepareStatement(UPDATE)
        ) {
            pstmt.setString(1, areaVO.getCityName());
            pstmt.setString(2, areaVO.getDistrict());
            pstmt.setInt(3, areaVO.getAreaId());
            pstmt.executeUpdate();
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured: " + se.getMessage());
        }
    }

    // ===== 刪除 =====
    public void delete(Integer areaId) {
        try (
            Connection con = ds.getConnection();
            PreparedStatement pstmt = con.prepareStatement(DELETE)
        ) {
            pstmt.setInt(1, areaId);
            pstmt.executeUpdate();
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured: " + se.getMessage());
        }
    }

    // ===== PK 查詢 =====
    public AreaVO findByPrimaryKey(Integer areaId) {
        AreaVO areaVO = null;
        try (
            Connection con = ds.getConnection();
            PreparedStatement pstmt = con.prepareStatement(GET_ONE_STMT)
        ) {
            pstmt.setInt(1, areaId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                areaVO = new AreaVO();
                areaVO.setAreaId(rs.getInt("area_id"));
                areaVO.setCityName(rs.getString("city_name"));
                areaVO.setDistrict(rs.getString("district"));
            }
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured: " + se.getMessage());
        }
        return areaVO;
    }

    // ===== 查詢全部 =====
    public List<AreaVO> getAll() {
        List<AreaVO> list = new ArrayList<>();
        try (
            Connection con = ds.getConnection();
            PreparedStatement pstmt = con.prepareStatement(GET_ALL_STMT);
            ResultSet rs = pstmt.executeQuery()
        ) {
            while (rs.next()) {
                AreaVO areaVO = new AreaVO();
                areaVO.setAreaId(rs.getInt("area_id"));
                areaVO.setCityName(rs.getString("city_name"));
                areaVO.setDistrict(rs.getString("district"));
                list.add(areaVO);
            }
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured: " + se.getMessage());
        }
        return list;
    }

    // ===== city + district 查詢（重複檢查核心）=====
    public AreaVO findByCityAndDistrict(String cityName, String district) {
        AreaVO areaVO = null;
        String sql =
            "SELECT area_id, city_name, district FROM area WHERE city_name=? AND district=?";

        try (
            Connection con = ds.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)
        ) {
            pstmt.setString(1, cityName);
            pstmt.setString(2, district);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                areaVO = new AreaVO();
                areaVO.setAreaId(rs.getInt("area_id"));
                areaVO.setCityName(rs.getString("city_name"));
                areaVO.setDistrict(rs.getString("district"));
            }
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured: " + se.getMessage());
        }
        return areaVO;
    }

    // ===== 重複檢查 =====
    public boolean isDuplicateArea(String cityName, String district) {
        return findByCityAndDistrict(cityName, district) != null;
    }
}
