<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="java.util.*" %>
<%@ page import="com.area.model.*"%>

<%
	AreaService areaSvc = new AreaService();
	List<AreaVO> list = areaSvc.getAll();
	pageContext.setAttribute("list",list);
%>

<html>
<head>

<title>所有縣市地區資料 </title>
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
	width: 800px;
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
<body bgcolor='white'>


<table id="table-1">
	<tr><td>
		 <h3>所有縣市地區資料 - listAllArea.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="<%=request.getContextPath()%>/resourcess/images/backcat.jpg" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>編號</th>
		<th>縣市</th>
		<th>地區</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="areaVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${areaVO.areaId}</td>
			<td>${areaVO.cityName}</td>
			<td>${areaVO.district}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/area/area.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="areaId"  value="${areaVO.areaId}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/area/area.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="areaId"  value="${areaVO.areaId}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>