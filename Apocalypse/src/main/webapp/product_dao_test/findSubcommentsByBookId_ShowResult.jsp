<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>透過書本ID傳回副評論-02-顯示結果</title>
</head>
<body>
		<h2>透過書本ID傳回副評論-02</h2>
	<c:forEach var="comment" items="${scbs}">
		<br>SubcommentId:${comment.subcommentId}
		<br>CommentId:${comment.commentId}
		<br>BookID:${comment.bookId}
		<br>MemberId:${comment.memberId}
		<br>Comments:${comment.comments}
		<hr>
	</c:forEach>	
</body>
</html>