<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>IRead愛閱讀</title>
</head>
<script>
window.onload = function(){
   
	var div1 =document.getElementById("alert1");
	   if(div1){
		   alert("請去收信,成為正式會員");
	}
   var div2 =document.getElementById("alert2");
   if(div2){
	   alert("此封驗證信已逾時,請重新再寄送一封驗證信");
   }
   
   var sendData =document.getElementById("sendData");
   sendData.onclick = function(){
	   var xhr1 = new XMLHttpRequest();
		xhr1.open("POST", "/MemberSystem/insert/InsertMemberServlet", true);
		xhr1.setRequestHeader("Content-Type",
				"application/x-www-form-urlencoded");
		xhr1.send("email=email" );
		xhr1.onreadystatechange = function(){
			if (xhr1.readyState == 4 && xhr1.status == 200) {
				result = JSON.parse(xhr1.responseText);
				alert("寄信成功");
			}
		
		}
	}
 }
   

</script>
<body>
${ LoginOK.lastLogin}
${ LoginOK_Author.pen_Name}

<center> 
     <table>
     	
     		<tr>
 				 <td>
 				   <div>
 				        <c:if test="${ empty LoginOK}">
 				       		<a href="<c:url value='/insert/InsertMemberForm.jsp'/>">
 				           	註冊
 				           	</a>
 				         </c:if>
 				       		
 				   </div>
 				 </td>
 				 <td>
 				   <div>
 				       <a href="<c:url value='/index.jsp' />">
 				                                儲值
 				       </a>
 				   </div>
 				 </td>
 				 <td>
 				   <div >
 				      <c:if test="${ empty LoginOK}">
						<a href="<c:url value='/login/Login.jsp?a=${ empty LoginOK}'/>">
						     登入 
						</a>
		              </c:if>
 				   </div>
 				 </td> 
 				 
 				 <td>
 				   <div>
 				      <c:if test="${! empty LoginOK && LoginOK.role_id !=1}">
 				      	<a href="<c:url value='/login/Member.jsp' />">
						      會員
						</a>
		              </c:if>
		               <c:if test="${ LoginOK.role_id==1}">
							<div id="alert1"></div>
					 	</c:if>
		              <c:if test="${ ! empty overtime}">
							<div id="alert2"></div>
					 	</c:if>
		              					 	
 				   </div>
 				   <div>
 				   		<c:if test="${! empty LoginOK && LoginOK.role_id ==1}">
 				  		   <button id="sendData">送出</button>
 				  		</c:if>
 				   </div>
 				   
 				 </td>   	 
 				 <td>
 				   <div>
 				      <c:if test="${! empty LoginOK}">
 				      	<a href="<c:url value='/login/Logout.jsp' />">
						     登出 
						</a>
		              </c:if>
 				   </div>
 				 </td>   	  	
     		</tr>
     		
     </table>
    
</center>     
</body>
</html>