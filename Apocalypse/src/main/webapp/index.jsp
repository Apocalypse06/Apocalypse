<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${empty LoginOK}"> 
     <c:redirect url="/login/Login.jsp" />
</c:if>   
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Home</title>
</head>
<body>
  <center>
	<h1>尚未開發</h1>
	<a href="<c:url value='/top.jsp' />">回到首頁</a>
  </center>
</body>
</html>
