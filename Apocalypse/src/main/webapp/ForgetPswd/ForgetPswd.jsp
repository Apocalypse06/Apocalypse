<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<center>
	<c:if test="${ empty EmailOK}">
	  <form name="insertMemberFormA" action="<c:url value='/controler/ForgetPswdServlet'/>" method="POST">
	    <table border="1" >
	    	<tr bgcolor='tan' ><th height="60" colspan="2" align="center">請輸入帳號信箱</th></tr>
			<tr bgcolor='tan' >
			    <td width="120" height="40">Email:</td>
			    <td width="600" height="40" align="left" > 
			      	<input id='num' style="text-align:left" name="mEmail" value="${param.mEmail}" placeholder="(e-mail)"type="text" size="14">
			      	<font color='blue' size="-1">&nbsp;&nbsp;(Email)</font> 
			 	    <div style="color:#FF0000; font-size:60%; display: inline">${ErrorMsg.mEmail}</div> 
			 	</td>
			</tr>
			<tr bgcolor='tan' >
			    <td height="50" colspan="2" align="center">
			       <input type="submit" value="送出" >
			    </td>
			</tr>
	    </table>
	  </form>
	 </c:if> 
	 <c:if test="${ !empty EmailOK}">
	 
	   請去信箱收信來更改密碼
	   <a href="<c:url value='../top.jsp' /> ">回到首頁</a>
	 </c:if>
</center>
<c:remove var="LoginOK"/>
<%
  session.invalidate();
%>
</body>
</html>