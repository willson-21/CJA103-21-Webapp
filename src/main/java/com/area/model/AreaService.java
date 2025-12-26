package com.area.model;

import java.util.List;

public class AreaService {
	
	private AreaDAO_interface dao;
	
	public AreaVO addArea(String cityname, String district) {
		
		AreaVO areaVO = new AreaVO();
		
		areaVO.setCityName(cityname);
		areaVO.setDistrict(district);
		dao.insert(areaVO);
		
		return areaVO;
	}
	
	public AreaVO updateArea(Integer areaid, String cityname, String district) {
		
		AreaVO areaVO = new AreaVO();
		
		areaVO.setAreaId(areaid);
		areaVO.setCityName(cityname);
		areaVO.setDistrict(district);
		dao.update(areaVO);
		
		return areaVO;
	}
	
	public void deleteArea(Integer areaid) {
		dao.delete(areaid);
	}
	
	public AreaVO getOneArea(Integer areaid) {
		return dao.findByPrimaryKey(areaid);
	}
	
	public List<AreaVO> getAll(){
		return dao.getAll();
	}
}
