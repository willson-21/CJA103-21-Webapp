package com.area.model;

import java.util.List;

public class AreaService {

    private AreaDAO_interface dao;

    public AreaService() {
        
        dao = new AreaJDBCDAO();
    }

    public AreaVO addArea(String cityName, String district) {

        AreaVO areaVO = new AreaVO();
        areaVO.setCityName(cityName);
        areaVO.setDistrict(district);

        dao.insert(areaVO);
        return areaVO;
    }

    public AreaVO updateArea(Integer areaId, String cityName, String district) {

        AreaVO areaVO = new AreaVO();
        areaVO.setAreaId(areaId);
        areaVO.setCityName(cityName);
        areaVO.setDistrict(district);

        dao.update(areaVO);
        return areaVO;
    }

    public void deleteArea(Integer areaId) {
        dao.delete(areaId);
    }

    public AreaVO getOneArea(Integer areaId) {
        return dao.findByPrimaryKey(areaId);
    }

    public List<AreaVO> getAll() {
        return dao.getAll();
    }

    //  重複檢查
    public boolean isDuplicateArea(String cityName, String district) {
        return dao.findByCityAndDistrict(cityName, district) != null;
    }
}
