<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" type="text/css">	
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>搜尋功能測試結果顯示頁</title>
</head>
<body>
	
	<div style="position:absolute;top:10px;left:1px;width:150px;height:800px;border:3px solid red;">
	
		<h3>搜尋結果測試頁</h3>
		<p>
			<a href="../index.jsp">回測試主頁</a>
		<br>
			<a href="search_Index.jsp">回搜尋功能測試主頁</a>
		<br>
		
		
		<h3>依各屬性排序</h3>
		<br>
			<form action="SearchBookRefineService.do" method="POST">
				<input type="hidden" name="refine_type" value="sort_Clicks">
				<input type="hidden" name="sort_type" value="desc">
				<input type="hidden" name="key_word" value="${keyWord}">
				<input type="hidden" name="search_type" value="${searchType}">
				<input type="submit" value="依照點擊數排序(↑)">
			</form>			
			<form action="SearchBookRefineService.do" method="POST">
				<input type="hidden" name="refine_type" value="sort_Clicks">
				<input type="hidden" name="sort_type" value="asc">
				<input type="hidden" name="key_word" value="${keyWord}">
				<input type="hidden" name="search_type" value="${searchType}">
				<input type="submit" value="依照點擊數排序(↓)">
			</form>			
		<br>
			<form action="SearchBookRefineService.do" method="POST">
				<input type="hidden" name="refine_type" value="sort_PublishDate">
				<input type="hidden" name="sort_type" value="desc">
				<input type="hidden" name="key_word" value="${keyWord}">
				<input type="hidden" name="search_type" value="${searchType}">
				<input type="submit" value="依照上架日期排序(↑)">
			</form>			

			<form action="SearchBookRefineService.do" method="POST">
				<input type="hidden" name="refine_type" value="sort_PublishDate">
				<input type="hidden" name="sort_type" value="asc">
				<input type="hidden" name="key_word" value="${keyWord}">
				<input type="hidden" name="search_type" value="${searchType}">
				<input type="submit" value="依照上架日期排序(↓)">
			</form>			
		<br>
			<form action="SearchBookRefineService.do" method="POST">
				<input type="hidden" name="refine_type" value="sort_Tickets">
				<input type="hidden" name="sort_type" value="desc">
				<input type="hidden" name="key_word" value="${keyWord}">
				<input type="hidden" name="search_type" value="${searchType}">
				<input type="submit" value="依照月票數排序(↑)">
			</form>			

			<form action="SearchBookRefineService.do" method="POST">
				<input type="hidden" name="refine_type" value="sort_Tickets">
				<input type="hidden" name="sort_type" value="asc">
				<input type="hidden" name="key_word" value="${keyWord}">
				<input type="hidden" name="search_type" value="${searchType}">
				<input type="submit" value="依照月票數排序(↓)">
			</form>			
		<br>


		
		
		
		
		
		
		
		
		<h3>依類型篩選</h3>
		<c:forEach var="classify" varStatus="status" items="${cbs}">
			<br>
			<form action="SearchBookRefineService.do" method="POST">
				<input type="hidden" name="refine_type" value="filter_classify">
				<input type="hidden" name="key_word" value="${keyWord}">
				<input type="hidden" name="search_type" value="${searchType}">
				<input type="hidden" name="classify_type" value="${classify.classifyType}">			
				<input type="submit" value="${classify.classifyType}(${classify.classifyNumber})">	
    		</form>
			<hr>
		</c:forEach>
		
		
		<h3>依連載狀況篩選</h3>
		<c:forEach var="bookstate" varStatus="status" items="${bsbs}">
			<br>
			<form action="SearchBookRefineService.do" method="POST">
				<input type="hidden" name="refine_type" value="filter_bookstate">
				<input type="hidden" name="key_word" value="${keyWord}">
				<input type="hidden" name="search_type" value="${searchType}">
				<input type="hidden" name="bookstate_type" value="${bookstate.bookStateType}">			
				<input type="submit" value="${bookstate.bookStateType}(${bookstate.bookStateNumber})">	
    		</form>
			<hr>
		</c:forEach>
		
		
		
		
		
	
	</div>


	<div style="position:absolute;top:10px;left:180px;width:800px;height:800px;border:3px solid green;
		overflow-x:hidden;overflow-y:auto;">
		<h3>&nbsp; 搜尋關鍵字為:&nbsp;${keyWord} &nbsp; 搜尋項目為:&nbsp;${searchType}</h3>
		<h3>&nbsp; 共有 ${bbs.size()} 項結果</h3>		
		<h3>&nbsp; 符合條件的搜尋結果如下:</h3>
		<c:forEach var="book" varStatus="status" items="${bbs}">
			<div style="position:absolute;top:${430*status.count-300}px;left:10px; width:750px;height:400px;border:3px solid purple;">
				<div>
					<br>BookID:${book.bookId}
					<br><img src="/Apocalypse//fakedata_resources/${book.surface_Plot_Name}.jpg" width="150px" height="300px">
				</div>
				<div style="position:absolute;top:30px;left:200px;width:500px;height:300px;border:3px solid blue;">
					<br>Title:${book.title}
					<br>AuthorId:${book.authorId}
					<br>Author:${book.penName}
					<br>BookState:${book.bookState}
					<br>PublishDate:${book.publishDate}
					<br>Classify:${book.classify}
					<br>Clicks:${book.clicks}
					<br>Tickets:${book.tickets}
					<br>SurfacePlotName:${book.surface_Plot_Name}
				</div>
			</div>
	
		</c:forEach>
	</div>
	
	
	
		


	
	
	
</body>
</html>