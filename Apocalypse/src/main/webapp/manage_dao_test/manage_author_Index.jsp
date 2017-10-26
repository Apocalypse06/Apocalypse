<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HTML>
	<Head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="css/style.css">
		<script src="js/jquery-3.2.1.min.js"></script>
		<script src="js/script.js"></script>
		<title>作家管理功能測試主頁</title>		
	</Head>

	<body>

		<div id="operate_zone">
				<ul id="operate_tabs">
					<li><a href="#op_tab1" class="operateCurrent">新書上傳</a></li>		
					<li><a href="#op_tab2">新章節上傳</a></li>		
					<li><a href="#op_tab3">書本資料修改</a></li>
					<li><a href="#op_tab4">章節內容修改</a></li>
					<li><a href="#op_tab5">聯絡系統管理員</a></li>		
				</ul>

				<div id="operate_contents">
					<div id="op_tab1">
						<h3>新書基本資訊</h3>	
						<form  id="NewBook_form" action="ManageAuthorService.do" method="POST" enctype="multipart/form-data">
							<input type="hidden" name="author_id" value="${authorId}">
							<input type="hidden" name="function_type" value="upload_newbook">	
							書名：<input type="text" name="title" size="32"><P/>
							類型:<input type="text" name="classity" size="32"><P/>
							封面圖:<p/>
							<input type="file" name="surface_plot" accept=".jpg"><P/>							
							簡介:<p/>
							<textarea style="overflow-x:hidden;overflow-y:auto;" 
							name="intro" cols="80" rows="10"></textarea><P/>
							<br>
							<input id="bookNext_Bt" type="submit" value="送出新書基本資料並繼續下一步">
						</form>	
					</div>
					
					<div id="op_tab2">
						<form id="NewChapter_chooseBook_form">
							<input id="NewChapter_author_id" type="hidden" name="author_id" value="${authorId}">
							<h3>請選擇要編輯的書本:</h3>
							<select id="NewChapter_chooseBookId" name="edit_bookid">
								<option value="none"></option>
								<c:forEach var="book" varStatus="status" items="${bbs}">
								<c:if test="${book.bookState == '連載中'}">
								<option value="${book.bookId}">${book.title} | ${book.classify} | ${book.publishDate}</option>
								</c:if>
								</c:forEach>
							</select>
						</form>
					</div>
					
										
					<div id="op_tab3">
						<form id="EditBook_chooseBook_form" action="ManageAuthorService.do" method="POST" >
							<input type="hidden" name="function_type" value="alter_book_mid">
							<input id="EditBook_author_id" type="hidden" name="author_id" value="${authorId}">
							<h3>請選擇要修改基本資料的書本:</h3>
							<select id="EditBook_chooseBookId" name="edit_bookid">
								<c:forEach var="book" varStatus="status" items="${bbs}">
								<c:if test="${book.bookState == '連載中'}">
								<option value="${book.bookId}">${book.title} | ${book.classify} | ${book.publishDate}</option>
								</c:if>
								</c:forEach>
							</select>
							<input id="EditBookNext_Bt" type="submit" value="確定並繼續下一步">
						</form>
					</div>
					
					
					<div id="op_tab4">
						<form id="EditContent_chooseBook_form">
							<input id="EditContent_author_id" type="hidden" name="author_id" value="${authorId}">
							<h3>請選擇要修改內容的已上架書本:</h3>
							<select id="EditContent_chooseBookId" name="edit_bookid">
								<option value="none"></option>
								<c:forEach var="book" varStatus="status" items="${bbs}">
								<c:if test="${book.bookState == '連載中'}">
								<option value="${book.bookId}">${book.title} | ${book.classify} | ${book.publishDate}</option>
								</c:if>
								</c:forEach>
							</select>
						</form>
					</div>
					
					
					<div id="op_tab5"></div>
				</div>
		</div>	


		<div id="dispTxt_zone">
				<div id="dispTxt_contents">
					<h3>訊息攔</h3>
					
					<c:if test="${messageAdd != null}">
						<c:set var="messageOut"   value="${messageBase}${messageAdd}" />					
					</c:if>
					<c:if test="${messageAdd == null}">
						<c:set var="messageOut"   value="${messageBase}"/>					
					</c:if>
					
					
					<textarea  id="dispTxt_contents_pad" disabled="disabled" style="overflow-x:hidden;overflow-y:auto;">${messageOut}</textarea>
				</div>
		</div>




		<div id="dispTable_zone">
				<ul id="dispTable_tabs"> 
					<li><a href="#dt_tab1" class="dispTableCurrent">書本表格</a></li>	
					<li><a href="#dt_tab2">待審上架<br>書本表格</a></li>				
				</ul>

				<div id="dispTable_contents"  style="position:relative; overflow-x:hidden; overflow-y:auto;" >
					<div id="dt_tab1">
								
					<c:forEach var="book" varStatus="status" items="${bbs}">
 						<div style="position:absolute;top:${25+400*status.count-400}px;left:10px;width:550px;height:500x;border:3px solid blue;"> 
							<br>BookID:${book.bookId}
							<br><img src="/Apocalypse//fakedata_resources/${book.surface_Plot_Name}.jpg" width="150px" height="300px">						
	  						 <div style="position:absolute;top:20px;left:200px;width:300px;height:310px;border:3px solid purple;"> 
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
					
					
					
					
					
					
						<div id="dt_tab2">										
							<c:forEach var="cbook" varStatus="status" items="${cbbs}">
	 						<div style="position:absolute;top:${25+400*status.count-400}px;left:10px;width:550px;height:500x;border:3px solid blue;"> 
								<br>BookID:${cbook.bookId}
								<br><img src="/Apocalypse//fakedata_resources/${cbook.surface_Plot_Name}.jpg" width="150px" height="300px">
							
		  						 <div style="position:absolute;top:20px;left:200px;width:300px;height:310px;border:3px solid purple;"> 
									<br>Title:${cbook.title}
									<br>AuthorId:${cbook.authorId}
									<br>Author:${cbook.penName}
									<br>BookState:${cbook.bookState}
									<br>PublishDate:${cbook.publishDate}
									<br>Classify:${cbook.classify}
									<br>Clicks:${cbook.clicks}
									<br>Tickets:${cbook.tickets}
									<br>SurfacePlotName:${cbook.surface_Plot_Name}
								</div>
							</div>	
							</c:forEach>
						</div>
						
				</div>
		</div>


	</body>
</HTML>