package com.area.model;

import java.util.*;

public interface AreaDAO_interface {

    void insert(AreaVO areaVO);
    void update(AreaVO areaVO);
    void delete(Integer areaId);
    AreaVO findByPrimaryKey(Integer areaId);
    List<AreaVO> getAll();

    //  新增：檢查縣市 + 地區是否已存在
    AreaVO findByCityAndDistrict(String cityName, String district);
}