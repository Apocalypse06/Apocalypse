<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HTML>
	<Head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="css/alterVolume_style.css">
		<title>修改已上架書本章節內容-卷</title>		
	</Head>

	<body>
		<div id="beforeVolume_zone">
			<div id="beforeVolume_contents">
				<h3>舊卷</h3>
				<h4>第${volumeId}卷</h4>
				卷名:${volumeTitle}
			</div>
		</div>	

		<div id="uploadVolume_zone">
			<div id="uploadVolume_contents">
			<h3>修改卷</h3>
			<h4>第${volumeId}卷</h4>

			<form  action="ManageAuthorService.do" method="POST" >
				<input type="hidden" name="function_type" value="alter_volume_end">
				<input type="hidden" name="author_id" value="${authorId}">	
				<input type="hidden" name="book_id" value="${bookId}">	
				<input type="hidden" name="volume_id" value="${volumeId}">	
				卷名:	
				<input type="text" name="volume_title" size="32" value="${volumeTitle}"><P/>	
				<input id="alterVolume_Bt" type="submit" value="確定送出修改卷資料">
			</form>	

			</div>
		</div>	


		</body>
</HTML>