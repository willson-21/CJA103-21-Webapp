package com.area.controller;

import java.util.*;

import com.area.model.*;

import java.io.*;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class AreaServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException{
		doPost(req,res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException{
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("getOne_For_Display".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			
			
			req.setAttribute("errorMsgs", errorMsgs);
			
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			String str = req.getParameter("areaId");
			if(str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入地址編號");
			}
			
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/area/select_page.jsp");
				failureView.forward(req,res);
				return;
			}
			
			Integer areaId = null;
			try {
				areaId = Integer.valueOf(str);
			} catch(Exception e) {
				errorMsgs.add("地區編號格式不正確");
			}
			
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/area/select_page.jsp");
				failureView.forward(req, res);
				return;
			}
			
			/***************************2.開始查詢資料*****************************************/
			AreaService areaSvc = new AreaService();
			AreaVO areaVO = areaSvc.getOneArea(areaId);
			if(areaVO == null) {
				errorMsgs.add("查無資料");
			}
			
			if(! errorMsgs.isEmpty()){
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/area/select_page.jsp");
				failureView.forward(req, res);
				return;
			}
			
			/***************************3.查詢完成,準備轉交(Send the Success view)*************/
			req.setAttribute("areaVO", areaVO);
			String url = "/back_end/area/listOneArea.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		if("getOne_For_Update".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			
			/***************************1.接收請求參數****************************************/
			Integer areaId = Integer.valueOf(req.getParameter("areaId"));
			/***************************2.開始查詢資料****************************************/
			AreaService areaSvc = new AreaService();
			AreaVO areaVO = areaSvc.getOneArea(areaId);
			
			/***************************3.查詢完成,準備轉交(Send the Success view)************/
			req.setAttribute("areaVO", areaVO);        
			String url = "/back_end/area/update_area_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		if ("update".equals(action)) {
		    // 1. 建立錯誤訊息容器
		    List<String> errorMsgs = new LinkedList<>();
		    req.setAttribute("errorMsgs", errorMsgs);

		    // 2. 取得並驗證 areaId
		    Integer areaId = null;
		    try {
		        String strId = req.getParameter("areaId");
		        if (strId == null || strId.trim().isEmpty()) {
		            errorMsgs.add("地區編號必須存在");
		        } else {
		            areaId = Integer.valueOf(strId.trim());
		        }
		    } catch (NumberFormatException e) {
		        errorMsgs.add("地區編號格式不正確");
		    }

		    // 3. 取得並驗證 cityName
		    String cityName = req.getParameter("cityName");
		    if (cityName == null || cityName.trim().isEmpty()) {
		        errorMsgs.add("縣市名稱: 請勿空白");
		    } else if (!cityName.trim().matches("^[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{2,10}$")) {
		        errorMsgs.add("縣市名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
		    }

		    // 4. 取得並驗證 district
		    String district = req.getParameter("district");
		    if (district == null || district.trim().isEmpty()) {
		        errorMsgs.add("地區名稱: 請勿空白");
		    } else if (!district.trim().matches("^[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{2,10}$")) {
		        errorMsgs.add("地區名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
		    }

		    // 5. 建立 AreaVO 用於回表單
		    AreaVO areaVO = new AreaVO();
		    areaVO.setAreaId(areaId);
		    areaVO.setCityName(cityName);
		    areaVO.setDistrict(district);

		    // 6. 若有錯誤訊息，回表單
		    if (!errorMsgs.isEmpty()) {
		        req.setAttribute("areaVO", areaVO);
		        RequestDispatcher failureView = req.getRequestDispatcher("/back_end/area/update_area_input.jsp");
		        failureView.forward(req, res);
		        return;
		    }

		    // 7. 修改資料
		    try {
		        AreaService areaSvc = new AreaService();
		        areaVO = areaSvc.updateArea(areaId, cityName, district);

		        // 8. 成功，轉交單筆查詢頁
		        req.setAttribute("areaVO", areaVO);
		        String url = "/back_end/area/listOneArea.jsp";
		        RequestDispatcher successView = req.getRequestDispatcher(url);
		        successView.forward(req, res);
		    } catch (Exception e) {
		        errorMsgs.add("修改資料失敗: " + e.getMessage());
		        req.setAttribute("areaVO", areaVO);
		        RequestDispatcher failureView = req.getRequestDispatcher("/back_end/area/update_area_input.jsp");
		        failureView.forward(req, res);
		    }
		}
		
		if ("insert".equals(action)) {

		    // 1. 建立錯誤訊息容器
		    List<String> errorMsgs = new LinkedList<>();
		    req.setAttribute("errorMsgs", errorMsgs);

		    // 2. 取得並驗證 cityName
		    String cityName = req.getParameter("cityName");
		    if (cityName == null || cityName.trim().isEmpty()) {
		        errorMsgs.add("縣市名稱: 請勿空白");
		    } else if (!cityName.trim().matches("^[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{2,10}$")) {
		        errorMsgs.add("縣市名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
		    }

		    // 3. 取得並驗證 district
		    String district = req.getParameter("district");
		    if (district == null || district.trim().isEmpty()) {
		        errorMsgs.add("地區名稱: 請勿空白");
		    } else if (!district.trim().matches("^[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{2,10}$")) {
		        errorMsgs.add("地區名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
		    }

		    // 4. 建立 AreaVO 回表單
		    AreaVO areaVO = new AreaVO();
		    areaVO.setCityName(cityName);
		    areaVO.setDistrict(district);

		    // 5. 若有錯誤訊息，回表單
		    if (!errorMsgs.isEmpty()) {
		        req.setAttribute("areaVO", areaVO);
		        RequestDispatcher failureView = req.getRequestDispatcher("/back_end/area/addArea.jsp");
		        failureView.forward(req, res);
		        return;
		    }

		    // 6. 新增資料前，檢查是否重複
		    try {
		        AreaService areaSvc = new AreaService();

		        // 重複檢查：若該縣市+地區已存在，直接回表單
		        if (areaSvc.isDuplicateArea(cityName, district)) {
		            errorMsgs.add("該縣市與地區的組合已存在，請勿重複新增");
		            req.setAttribute("areaVO", areaVO);
		            RequestDispatcher failureView = req.getRequestDispatcher("/back_end/area/addArea.jsp");
		            failureView.forward(req, res);
		            return;
		        }

		        // 7. 執行新增
		        areaVO = areaSvc.addArea(cityName, district);

		        // 8. 成功，轉交列表頁
		        String url = "/back_end/area/listAllArea.jsp";
		        RequestDispatcher successView = req.getRequestDispatcher(url);
		        successView.forward(req, res);

		    } catch (Exception e) {
		        // 例外處理，避免 500
		        errorMsgs.add("新增資料失敗: " + e.getMessage());
		        req.setAttribute("areaVO", areaVO);
		        RequestDispatcher failureView = req.getRequestDispatcher("/back_end/area/addArea.jsp");
		        failureView.forward(req, res);
		    }
		}
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
				/***************************1.接收請求參數***************************************/
				Integer areaId = Integer.valueOf(req.getParameter("areaId"));
				
				/***************************2.開始刪除資料***************************************/
				AreaService areaSvc = new AreaService();
				areaSvc.deleteArea(areaId);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back_end/area/listAllArea.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
		}
	}
		
}
