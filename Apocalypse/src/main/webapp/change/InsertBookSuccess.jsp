<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script>
   window.onload = function(){
	  var img = document.getElementById("img");	  
		  var xhr = new XMLHttpRequest();
		  img.style.display = "none";
		  xhr.onreadystatechange = function(){
			  if (xhr.readyState === 4 && xhr.status === 200){
				  // src屬性必須俱備下列格式:
		    	  // data:[MIME-TYPE];base64,[經由Base64編碼的非文字檔]
		    	  img.src = xhr.response;
				  //bga.src = window.URL.createObjectURL(xhr.response);
			  }
		  }
		  xhr.open("POST", "/Apocalypse/init/GetImageFromServer", true);
		  xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		  xhr.send("id=1&type=book&useBase64=yes");
		  img.style.display = "inline";
   }
</script>
<title>Lab02_01</title>
</head>
<body>
	<H1>書名 ${ bookBean.title } 的資料新增成功</H1>
	<BR>簡介: ${ bookBean.intro }
	<BR>發布日期: ${ bookBean.publishDate }
	<BR>分類: ${ bookBean.classify }
	<BR>點擊數: ${ bookBean.clicks } 
	<BR>封面圖: <img width="250" height="250" src="${pageContext.servletContext.contextPath}/init/GetImageFromServer?id=1&type=book">
	<BR>封面圖: <img id="img"  width="250" height="250">
</body>
</html>