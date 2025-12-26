package com.area.model;

import java.io.Serializable;

public class AreaVO implements Serializable {

    private Integer areaid;     // 對應資料庫欄位 area_id
    private String cityname;    // 對應資料庫欄位 city_name
    private String district;    // 對應資料庫欄位 district

    public AreaVO() {}

    public Integer getAreaId() {
        return areaid;
    }

    public void setAreaId(Integer areaid) {
        this.areaid = areaid;
    }

    public String getCityName() {
        return cityname;
    }

    public void setCityName(String cityname) {
        this.cityname = cityname;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }
}