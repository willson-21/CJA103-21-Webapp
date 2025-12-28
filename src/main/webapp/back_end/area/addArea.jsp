<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.area.model.*"%>

<%
	AreaVO areaVO = (AreaVO) request.getAttribute("areaVO");

%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrom=1">
<meta charset="UTF-8">
<title>縣市地區資料新增 -addArea.jsp</title>

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
</style>

</head>
<body bgcolor='yellow'>

<table id="table-1">
	<tr><td>
		 <h3>縣市地區資料新增</h3></td><td>
		 <h4><a href="<%=request.getContextPath() %>/back_end/area/select_page.jsp"><img src="<%=request.getContextPath()%>/resourcess/images/bocat.gif" width="100" height="100" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>新增資料</h3>

<%-- 錯誤列表 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正錯誤</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
			</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath() %>/area/area.do" name="form1">
<table>


	<tr>
		<td>縣市:</td>
		<td><input type="TEXT" name="cityName" value="<%= (areaVO==null)? "桃園市": areaVO.getCityName()%>" size="45"/></td>
	</tr>
	<tr>
		<td>地區:</td>
		<td><input type="TEXT" name="district" value="<%= (areaVO==null)? "八德區" : areaVO.getDistrict() %>" size="45"/></td>
	</tr>
	
</table>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>

</body>
</html>