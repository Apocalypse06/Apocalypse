<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>透過書本ID查詢書本-02</title>
</head>
<body>
	<h2>透過書本ID查詢書本-02</h2>
	<h1>書本查詢結果:</h1>
	<br>${result}
	<hr>
	<br>BookId:${bb.bookId}
  	<br><img src="/Booksystem//fakedata_resources/${bb.surface_Plot_Name}.jpg" width="150px" height="300"	> 
	<br>Title:${bb.title}
	<br>AuthorId:${bb.authorId}
	<br>Author:${bb.penName}
	<br>BookState:${bb.bookState}
	<br>PublishDate:${bb.publishDate}
	<br>Classify:${bb.classify}
	<br>Clicks:${bb.clicks}
	<br>Tickets:${bb.tickets}
	<br>SurfacePlot:${bb.surfacePlot}
	<br>SurfacePlotName:${bb.surface_Plot_Name}
</body>
</html>