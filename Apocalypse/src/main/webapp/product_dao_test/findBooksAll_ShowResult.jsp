<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查詢全部書本-02-顯示結果</title>
</head>
<body>
		<h2>查詢全部書本-02</h2>
	<c:forEach var="book" items="${bbs}">
		<br>BookID:${book.bookId}
		<br><img src="/Apocalypse//fakedata_resources/${book.surface_Plot_Name}.jpg" width="150px" height="300"	>
		<br>Title:${book.title}
		<br>AuthorId:${book.authorId}
		<br>Author:${book.penName}
		<br>BookState:${book.bookState}
		<br>PublishDate:${book.publishDate}
		<br>Classify:${book.classify}
		<br>Clicks:${book.clicks}
		<br>Tickets:${book.tickets}
		<br>SurfacePlot:${book.surfacePlot}
		<br>SurfacePlotName:${book.surface_Plot_Name}
		<hr>
	</c:forEach>	
</body>
</html>