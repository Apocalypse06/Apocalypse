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
	window.onload = function() {

		
		var table = document.getElementById('table');
		var divpage = document.getElementById('page');
		var uppage = document.getElementById('uppage');
		var nextpage = document.getElementById('nextpage');
			
		var xhr1 = new XMLHttpRequest();
		xhr1.open("POST", "FindBookListServlet", true);
		xhr1.setRequestHeader("Content-Type",
				"application/x-www-form-urlencoded");
		xhr1.send();
		xhr1.onreadystatechange = function() {
			if (xhr1.readyState == 4 && xhr1.status == 200) {

				result = JSON.parse(xhr1.responseText);
                page=parseInt(result[1]);
                book=result[2];
				for (var i = 0; i < result[2].length; i++) {
					tr = document.createElement("tr");
					td1 = document.createElement("td");
						td1.innerHTML = "<a href='<c:url value="https://www.youtube.com/watch?v=kJQP7kiw5Fk"/>'>"
										+ book[i].title + "</a>";
					tr.appendChild(td1);
					td2 = document.createElement("td");
						td2.innerHTML =book[i].classify;
					tr.appendChild(td2);
					table.appendChild(tr);
					
				}
				divpage.innerHTML="第"+result[1]+"頁/共"+result[0]+"頁";
			}
		}
// 		下一頁按鈕
		nextpage.onclick=function(){
			var xhr1 = new XMLHttpRequest();
			xhr1.open("POST", "FindBookListServlet", true);
			xhr1.setRequestHeader("Content-Type",
					"application/x-www-form-urlencoded");
			xhr1.send("pageNumber=" + (page+1)+"&oldpageNumber="+page);
			xhr1.onreadystatechange = function() {
				if (xhr1.readyState == 4 && xhr1.status == 200) {

					result = JSON.parse(xhr1.responseText);
	                page=parseInt(result[1]);
					book=result[2];
					for (var i = 0; i < result[2].length; i++) {
							td1.innerHTML = "<a href='<c:url value="https://www.youtube.com/watch?v=kJQP7kiw5Fk"/>'>"
											+ book[i].title + "</a>";
							td2.innerHTML =book[i].classify;
						
					}
					divpage.innerHTML="第"+result[1]+"頁/共"+result[0]+"頁";
				}
			}
		}
// 		上一頁按鈕
		uppage.onclick=function(){
			var xhr1 = new XMLHttpRequest();
			xhr1.open("POST", "FindBookListServlet", true);
			xhr1.setRequestHeader("Content-Type",
					"application/x-www-form-urlencoded");
			xhr1.send("pageNumber=" + (page-1)+"&oldpageNumber="+page);
			xhr1.onreadystatechange = function() {
				if (xhr1.readyState == 4 && xhr1.status == 200) {

					result = JSON.parse(xhr1.responseText);
	                page=parseInt(result[1]);
					book=result[2];
					for (var i = 0; i < result[2].length; i++) {
							td1.innerHTML = "<a href='<c:url value="https://www.youtube.com/watch?v=kJQP7kiw5Fk"/>'>"
											+ book[i].title + "</a>";
							td2.innerHTML =book[i].classify;
						
					}
					divpage.innerHTML="第"+result[1]+"頁/共"+result[0]+"頁";
				}
			}
		}
		
}	

</script>
<body>
	<center>
		<div>${LoginOK.nick_Name}的書架</div>
			<table id="table">
				<tr>
					<td>書名</td>
					<td>分類</td>
				</tr>
			</table>
			<table>
				<tr>
					<td><button type="button" id="uppage">上一頁</button></td>
					<td id="page">書名</td>
					<td><button type="button" id="nextpage">下一頁</button></td>
				</tr>
			</table>
		
	</center>
</body>
</html>