<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HTML>
	<Head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="css/alterChapter_style.css">
		<title>修改已上架書本章節內容-章</title>		
	</Head>

	<body>
		<div id="beforeChapter_zone">
			<div id="beforeChapter_contents">
				<h3>舊章節</h3>
				<h4>第${volumeId}卷</h4>

				<form>		
					卷名:${volumeTitle}
					<h4>第${chapterId}章</h4>	
					章名:${chapterTitle}<P/>
					內容:
					<br>
					<textarea disabled="disabled" style="overflow-x:hidden;overflow-y:auto;"name="intro" cols="80" rows="10">${chapterContent}</textarea><P/>
				<br>	
				</form>	

			</div>
		</div>	




		<div id="uploadChapter_zone">
			<div id="uploadChapter_contents">
				<h3>修改章節</h3>
				<h4>第${volumeId}卷</h4>

				<form  action="ManageAuthorService.do" method="POST" >
					<input type="hidden" name="function_type" value="alter_chapter_end">	
					<input type="hidden" name="author_id" value="${authorId}">	
					<input type="hidden" name="book_id" value="${bookId}">	
					<input type="hidden" name="volume_id" value="${volumeId}">	
					卷名:${volumeTitle}
					<input type="hidden" name="volume_title" value="${volumeTitle}"><P/>
					<h4>第${chapterId}章</h4>
					<input type="hidden" name="chapter_id" value="${chapterId}">	
					章名:<input type="text" name="chapter_title" size="32" value="${chapterTitle}"><P/>
					內容:
					<br>
					<textarea style="overflow-x:hidden;overflow-y:auto;"name="chapter_content" cols="80" rows="10">${chapterContent}</textarea><P/>
					<br>	
					<input id="alterChapter_Bt" type="submit" value="確定送出修改章節資料">
				</form>	
				
			</div>
		</div>	

	</body>
</HTML>