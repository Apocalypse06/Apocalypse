<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
		  xhr.open("POST", "/picture/GetImageFromServer", true);
		  xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		  xhr.send("id=1&type=book&useBase64=yes");
		  img.style.display = "inline";
   }
</script>
</head>
<body>

<center>
                         
	<table>
			<tr>
 				 <td>
 				   <div>
 				    <img id="img" width="50" height="50" src="${pageContext.servletContext.contextPath}/picture/GetImageFromServer?id=${LoginOK.member_Id}&type=MEMBER">
 				             會員${LoginOK.nick_Name}
 				             身份${My_role_Name}
 				   </div>
 				 </td>
 			</tr>
 			<tr>
 				 <td>
 				   <div>
 				       <a href="<c:url value='/change/ChangePicture.jsp' />">
 				           	更改會員頭象
 				       </a>
 				   </div>
 				 </td>
 			</tr>	 
     		<tr>
 				 <td>
 				   <div>
 				       <a href="<c:url value='/change/ChangeMemberForm.jsp' />">
 				           	修改會員資料
 				       </a>
 				   </div>
 				 </td>
 			</tr>
 			<tr>
 				 <td>
 				   <div>
 				       <a href="<c:url value='BookList.jsp' />">
 				           	我的書庫
 				       </a>
 				   </div>
 				 </td>
 			</tr>
 			<tr>
 				 <td>
 				   <div>
 				       <a href="<c:url value='/index.jsp' />">
 				           	我的交易紀錄
 				       </a>
 				   </div>
 				 </td>
 			</tr>
 			<tr>
 				 <td>
 				   <div>
	 				   <c:if test="${LoginOK.role_id==2}">
	 				       <a href="<c:url value='/insert/InsertAuthorForm.jsp' />">
	 				           	升級成為作者
	 				       </a>
	 				   </c:if>   
 				   </div>
 				 </td>
 			</tr>
 			<tr>
 				 <td>
 				   <div>
	 				   <c:if test="${LoginOK.role_id==3}">
	 				       <a href="<c:url value='/index.jsp' />">
	 				           	我的作品
	 				       </a>
	 				   </c:if>   
 				   </div>
 				 </td>
 			</tr>
 			<tr>
 				 <td>
 				   <div>
 				       <a href="<c:url value='/login/bookcomments.jsp' />">
 				          	 留言板
 				       </a>
 				   </div>
 				 </td>
 			</tr>
 			<tr>
 				 <td>
 				   <div>
 				       <a href="<c:url value='/top.jsp' />">
 				          	 回到首頁
 				       </a>
 				   </div>
 				 </td>
 			</tr>
 	</table>		
</center> 				 
</body>
</html>