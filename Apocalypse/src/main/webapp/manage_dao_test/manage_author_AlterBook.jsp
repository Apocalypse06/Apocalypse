<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HTML>
	<Head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="css/alterBook_style.css">
		<script src="js/jquery-3.2.1.min.js"></script>
		<script src="js/alterBook_script.js"></script>
		<title>修改已上架書本資料</title>		
	</Head>

	<body>

		<div id="operate_zone">
				<div id="operate_contents">
					<h3>修改前</h3>	
					書名:${bb_edit.title}<P/>
					類型:${bb_edit.classify}<P/>
					封面圖:<p/>
					<br>
					<br>
					
					<img src="/Booksystem/fakedata_resources/${bb_edit.surface_Plot_Name}.jpg" width="150px" height="300px"><P/>					
					簡介:<p/>
					<textarea disabled="disabled" style="overflow-x:hidden;overflow-y:auto;" 
					name="intro" cols="80" rows="10">${bb_edit.intro}</textarea><P/>
					<br>
				</div>
		</div>	



		<div id="dispTxt_zone">
				<div id="dispTxt_contents">
					<h3>輸入修改資料</h3>
							<form  id="AlterBook_form" action="ManageAuthorService.do" method="POST" enctype="multipart/form-data">
							<input type="hidden" name="function_type" value="alter_book_end">
							<input type="hidden" name="author_id" value="${authorId}">
							<input type="hidden" name="book_id" value="${bb_edit.bookId}">
							<input type="hidden" name="surface_plot_name" value="${bb_edit.surface_Plot_Name}">	
							書名:<input type="text" name="title" size="32" value="${bb_edit.title}"><P/>
							類型:<input type="text" name="classity" size="32" value="${bb_edit.classify}" ><P/>
							封面圖:<p/>
							<input id="alter_cover" type="file" name="edit_surface_plot" accept=".jpg"><P/>
							<img id="alter_cover_disp" src="/Booksystem/fakedata_resources/${bb_edit.surface_Plot_Name}.jpg" width="150px" height="300px"><P/>

							簡介:<p/>
							<textarea style="overflow-x:hidden;overflow-y:auto;" 
							name="intro" cols="80" rows="10">${bb_edit.intro}</textarea><P/>
							<br>
							<input id="AlterBook_Bt" type="submit" value="確定送出修改資料">
						</form>
				</div>
		</div>


		</body>
</HTML>