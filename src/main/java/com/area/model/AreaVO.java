package com.area.model;

import java.io.Serializable;

public class AreaVO implements Serializable {

    private Integer areaId;     
    private String cityName;    
    private String district;    

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