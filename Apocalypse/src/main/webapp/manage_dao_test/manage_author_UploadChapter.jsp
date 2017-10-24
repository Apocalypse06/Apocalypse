<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HTML>
	<Head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="css/uploadChapter_style.css">
		<title>新增章節資料</title>		
	</Head>

	<body>

		<div id="uploadChapter_zone">
				<div id="uploadChapter_contents">
				<h3>新增章節資料</h3>
				<h4>第${edit_volumeId}卷</h4>				
				<form  action="ManageAuthorService.do" method="POST" >	
				<input type="hidden" name="function_type" value="upload_newchapter_end">							
				<input type="hidden" name="author_id" value="${authorId}">	
				<input type="hidden" name="book_id" value="${edit_bookId}">	
				<input type="hidden" name="volume_id" value="${edit_volumeId}">	
				卷名:${edit_volumeTitle}
				<input type="hidden" name="volume_title" value="${edit_volumeTitle}"><P/>
				<h4>第${edit_chapterId}章</h4>
				<input type="hidden" name="chapter_id" value="${edit_chapterId}">	
				章名:<input type="text" name="chapter_title" size="32"><P/>
				內容:
				<br>
				<textarea style="overflow-x:hidden;overflow-y:auto;"name="chapter_content" cols="80" rows="10"></textarea><P/>
				<br>	
				<input id="uploadChapter_Bt" type="submit" value="確定送出新增章節資料">
				</form>	
				</div>
		</div>	


		</body>
</HTML>