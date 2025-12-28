<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.area.model.*"%>

<%
	AreaVO areaVO = (AreaVO) request.getAttribute("areaVO");
%>
<html>
<head>

<title>縣市地區單一資料</title>

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
	width: 600px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
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

<body bgcolor="white">

<h4>採用Script的寫法取值</h4>
<table id="table-1">
	<tr><td>
		 <h3>縣市地區單一資料</h3>
		 <h4><a href="<%=request.getContextPath()%>/back_end/area/select_page.jsp"><img src="<%=request.getContextPath()%>/resourcess/images/dog.jpg" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>編號</th>
		<th>縣市</th>
		<th>地區</th>
	</tr>
	<tr>
		<td><%=areaVO.getAreaId() %></td>
		<td><%=areaVO.getCityName() %></td>
		<td><%=areaVO.getDistrict() %></td>
	</tr>
</table>
</body>
</html>