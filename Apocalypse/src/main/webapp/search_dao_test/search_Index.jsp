<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>搜尋功能測試主頁</title>
</head>
<body>
	<h1>搜尋頁套件功能測試主頁</h1>
	<hr>
	<h2>透過書名關鍵字查詢書本</h2>
	<form name="function_01" action="SearchBookService.do" method="POST">
		<input type="hidden" name="form_number" value="form_01">			
		輸入書名關鍵字:<input type="text" name="title_str" size="10"><P/>
		<input type="submit" value="送出查詢"><P/>	
    </form>
	<hr>
	<h2>透過作者名關鍵字查詢書本</h2>
	<form name="function_02" action="SearchBookService.do" method="POST">
		<input type="hidden" name="form_number" value="form_02">			
		輸入作者名關鍵字:<input type="text" name="penName_str" size="10"><P/>
		<input type="submit" value="送出查詢"><P/>	
    </form>
	<hr>
	<h2>透過簡介關鍵字查詢書本</h2>
	<form name="function_03" action="SearchBookService.do" method="POST">
		<input type="hidden" name="form_number" value="form_03">			
		輸入簡介關鍵字:<input type="text" name="intro_str" size="10"><P/>
		<input type="submit" value="送出查詢"><P/>	
    </form>
	<hr>
	<h2>透過進階搜尋查詢書本</h2>
	<form name="function_04" action="SearchBookService.do" method="POST">
		<input type="hidden" name="form_number" value="form_04">
		輸入書名關鍵字:<input type="text" name="title_str" size="10"><P/>
		輸入作者名關鍵字:<input type="text" name="penName_str" size="10"><P/>					
		輸入簡介關鍵字:<input type="text" name="intro_str" size="10"><P/>
		<input type="submit" value="送出查詢"><P/>	
    </form>


</body>
</html>