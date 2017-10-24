<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登入</title>
</head>
<script> 
window.onload = function() {
	
		var sendData = document.getElementById("sendData");
				
		sendData.onclick = function() {
			// 讀取欄位資料	  
			var account = document.getElementById("account").value;
			var pswd = document.getElementById("pswd").value;
			

			var hasError = false; // 設定判斷有無錯誤的旗標
			var div0 = document.getElementById('result_account');
			var div1 = document.getElementById('result_pswd');
			var divResult = document.getElementById('result');
			var divtop = document.getElementById('top');
			var divtable = document.getElementById('table');
			
			var xhr1 = new XMLHttpRequest();
			xhr1.open("POST", "LoginServlet", true);
			xhr1.setRequestHeader("Content-Type",
					"application/x-www-form-urlencoded");
			xhr1.send("account=" + account + "&pswd=" + pswd);
			xhr1.onreadystatechange = function() {
				// 伺服器請求完成
				if (xhr1.readyState == 4 && xhr1.status == 200) {
					result = JSON.parse(xhr1.responseText);
					if (result.LoginError) {
						divResult.innerHTML = "<font color='red' >"
							+ result.LoginError + "</font>";
							
 						div0.innerHTML = "";	
						div1.innerHTML = "";
						
					} else {
						divResult.innerHTML = "";
						if (result.AccountEmptyError) {
							
							div0.innerHTML = "<font color='green' size='-2'>"
									+ result.AccountEmptyError + "</font>";
							
						} else {
							div0.innerHTML = "";
						}
						if (result.PswdEmptyError) {
							div1.innerHTML = "<font color='green' size='-2'>"
									+ result.PswdEmptyError + "</font>";
						} else {
							div1.innerHTML = "";
						}
						
						
					}
					if (result.Success){
						divtop.innerHTML = "<font color='green' size='-2'>"
							+ result.Success + "</font><br>"
							+"<a href="+"<c:url value='/top.jsp' />"+">"
							     +"首頁"+ "</a>";
						divtable.style.display='none';
					}
				} 
			}
		}
		
	}


</script>
<body>
	<CENTER>
		<div id="table">
			<h2>登入${key}${param.A}</h2>
			
			<fieldset style='display: inline-block;'>
				<legend>填寫下列資料</legend>
				帳號: <input type="text" name="account" id='account'><br>
				<div id='result_account' style="height: 10px;"></div>
				<br> 密碼: <input type="password" name="pswd" id='pswd'><br>
				<div id='result_pswd' style="height: 10px;"></div>
				<br>
				<div id='result' style="height: 18px;"></div>
			    <br>
			    <div>
 				     <a href="<c:url value='/ForgetPswd/ForgetPswd.jsp' />">
 				           	忘記密碼
 				     </a>
 				</div>
				<button id='sendData'>送出</button>
			</fieldset>
			 		
     		<div>
     			<img id="img" width="100" height="100" src="https://chart.googleapis.com/chart?chs=300x300&cht=qr&chl=https://google.com&choe=UTF-8">
     		</div>
     	
		</div>
		<div id='top' style="height: 18px;"></div>
		

</CENTER>
</body>

</html>
