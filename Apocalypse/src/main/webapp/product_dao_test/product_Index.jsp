<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>產品展示功能測試主頁</title>
</head>
<body>
	<h1>產品套件功能測試主頁</h1>
	<hr>
	<h5>透過書本ID查詢書本</h5>
		<a href="findBookByBookId_InputID.jsp">輸入書本ID,查詢書本基本資料</a>
	<br>
	<h5>查詢全部書本</h5>
		<a href="findBooksAll_Middle.jsp">查詢全部書本</a>
	<br>
	<h5>透過書本ID查詢書本評分</h5>
		<a href="findStarPointsByBookId_InputID.jsp">輸入書本ID,查詢書本評分</a>
	<br>
	<h5>透過書本ID傳回主評論</h5>
		<a href="findCommentsByBookId_InputID.jsp">輸入書本ID,查詢書本主評論</a>
	<br>
	<h5>透過書本ID傳回副評論</h5>
		<a href="findSubcommentsByBookId_InputID.jsp">輸入書本ID,查詢書本副評論</a>
	<br>	
	<h5>指定書本ID傳回卷訊息</h5>
		<a href="findVolumesByBookId_InputID.jsp">指定書本ID,傳回卷訊息</a>
	<br>	
	<h5>指定書本ID傳回章訊息</h5>
		<a href="findChaptersByBookId_InputID.jsp">指定書本ID,傳回章訊息</a>
	<br>

</body>
</html>