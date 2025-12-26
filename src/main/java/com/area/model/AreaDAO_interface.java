package com.area.model;

import java.util.*;

public interface AreaDAO_interface {
		public void insert(AreaVO areaVO);
		public void update(AreaVO areaVO);
		public void delete(Integer areaid);
		public AreaVO findByPrimaryKey(Integer areaid);
		
		public List<AreaVO> getAll();
}
