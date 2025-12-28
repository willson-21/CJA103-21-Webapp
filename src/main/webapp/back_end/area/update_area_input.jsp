<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.area.model.*"%>
<% //見com.emp.controller.EmpServlet.java第163行存入req的empVO物件 (此為從資料庫取出的empVO, 也可以是輸入格式有錯誤時的empVO物件)
   AreaVO areaVO = (AreaVO) request.getAttribute("areaVO");
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>縣市地區資料修改 </title>
<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
  
    img {
  width: 100px; /* 或 height: 200px; 或 width: 50%; */
  /* 保持比例：只寫 width 或 height 即可 */
  /* 調整比例：同時設定 width 和 height */
  /* 讓圖片填滿容器但不要裁切 (響應式)： */
  max-width: 100%;
  height: auto;
}
</style>

</head>
<body bgcolor='white'>

<tr><td>
		 <h3>縣市地區資料修改</h3>
		 <h4><a href="<%=request.getContextPath()%>/back_end/area/select_page.jsp"><img src="<%=request.getContextPath()%>/resourcess/images/catt.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath() %>/area/area.do" name="form1"><table>
	<tr>
		<td>編號:<font color=red><b>*</b></font></td>
		<td><%=areaVO.getAreaId()%></td>
	</tr>
	<tr>
		<td>縣市:</td>
		<td><input type="TEXT" name="cityName" value="<%= areaVO != null && areaVO.getCityName() != null ? areaVO.getCityName() : "" %>" size="45"/></td>
	</tr>
	<tr>
		<td>地區:</td>
		<td><input type="TEXT" name="district" value="<%= areaVO != null && areaVO.getDistrict() != null ? areaVO.getDistrict() : "" %>" size="45"/></td>
	</tr>
</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="areaId" value="<%=areaVO.getAreaId()%>">
<input type="submit" value="送出修改"></FORM>
</body>
</html>