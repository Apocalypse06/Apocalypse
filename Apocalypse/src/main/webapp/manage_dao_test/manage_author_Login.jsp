<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>作家管理功能測試登入頁</title>
</head>
<body>
	<h1>作家作品管理套件功能測試</h1>
	<hr>
	<h2>登入作家管理系統</h2>
	<form action="ManageAuthorService.do" method="POST">			
		輸入作家ID:<input type="text" name="author_id" size="10"><P/>
		<input type="submit" value="登入"><P/>	
    </form>
</body>
</html>