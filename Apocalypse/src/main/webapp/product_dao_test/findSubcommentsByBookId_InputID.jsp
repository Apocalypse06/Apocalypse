<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>透過書本ID傳回副評論-01-輸入書本ID</title>
</head>
<body>
<h2>輸入ID</h2>
<br>
	<h2>透過書本ID傳回副評論-01</h2>
	<form action="ProductBookService.do" method="POST">
		書本ID:<input type="text" name="book_id" size="10"><P/>
		      <input type="submit" value="送出查詢"><P/>	
    </form>		
</body>
</html>