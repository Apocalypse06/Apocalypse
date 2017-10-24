<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>        
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script> 
// window.onload = function(){
// 	alert("${param.time}");
// }

</script> 
<body>
  <center>
  
    <form name="insertMemberFormA" action="<c:url value='/controler/ChangePswdServlet'/>" method="POST">
	    <table border="1" >
	    	
	    	<tr style="display:none">
			    <td width="120" height="40">member_Id</td>
			    <td width="600" height="40" align="left" > 
			      	<input id='num' style="text-align:left" name="member_Id" value="${param.member_Id}" type="text" size="64">
				</td>
			</tr>
			<tr style="display:none">
			    <td width="120" height="40">time</td>
			    <td width="600" height="40" align="left" > 
			      	<input id='time' style="text-align:left" name="time" value="${param.time}" type="text" size="64">
				</td>
			</tr>
	    	<tr>
			    <td width="120" height="40">請輸入新的密碼:</td>
			    <td width="600" height="40" align="left" > 
			      	<input id='num' style="text-align:left" name="mPswd" value="${param.mPswd}" placeholder="8-16位(只可有英文和數字)"type="password" size="20">
					 <div style="color:#FF0000; font-size:60%; display: inline">${ErrorMsg.mPswd}</div>		    
			    </td>
			</tr>
			<tr bgcolor='tan' >
			    <td height="50" colspan="2" align="center">
			       <input type="submit" value="送出" >
			    </td>
			</tr>
	    </table>
	  </form>
	</center>
</body>
</html>