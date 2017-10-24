<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>指定書本ID傳回章訊息-02-顯示結果</title>
</head>
<body>
		<h2>指定書本ID傳回章訊息-02</h2>
	<c:forEach var="chapter" items="${cbs}">
		<br>BookID:${chapter.bookId}
		<br>VolumeId:${chapter.volumeId}
		<br>ChapterId:${chapter.chapterId}
		<br>ChapterTitle:${chapter.chapterTitle}
		<br>PublishTime:${chapter.publishTime}
		<br>LastModified:${chapter.lastModified}
		<br>Price:${chapter.price}
		<hr>
	</c:forEach>	
</body>
</html>