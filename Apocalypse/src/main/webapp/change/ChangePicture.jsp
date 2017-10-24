<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>天啟書城 上傳小說</title>
</head>
<!-- onload="javascript:document.insertMemberFormA.mId.focus();" -->
<body>
	<center>
		<form action="<c:url value='/controler/ChangePictureServlet'/>" enctype="multipart/form-data" method="POST">
			<table >
				<tr>
					<td>目前圖片</td>
					<td><img height='120' width='96'
						src='${pageContext.servletContext.contextPath}/picture/GetImageFromServer?id=${LoginOK.member_Id}&type=MEMBER' />
					</td>
				</tr>
				<tr>	
					<td>更換圖片:</td>
					<td colspan='2'><input type="file" name="mPicture"
						size="40" /> <font color='red' size='-1'>
							${ErrMsg.errPicture} </font>
					</td>
				</tr>
				<tr>
						
				    <td height="50" align="center">
				       <input type="submit" value="送出" >
				    </td>
				</tr>
			</table>
		</form>
	</center>
</body>
</html>