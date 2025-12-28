package com.area.model;

import java.io.Serializable;

public class AreaVO implements Serializable {

    private Integer areaId;     // 對應資料庫欄位 area_id
    private String cityName;    // 對應資料庫欄位 city_name
    private String district;    // 對應資料庫欄位 district

    public AreaVO() {}

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }
}