<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>        
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script> 
window.onload = function() {
	
		var sendData = document.getElementById("sendData");
				
		sendData.onclick = function() {
			// 讀取欄位資料	  
			
			
			var mpen_Name = document.getElementById("mpen_Name").value;
			var mbank_Account = document.getElementById("mbank_Account").value;
			
			
			var div1 = document.getElementById('result_mpen_Name');
			var div2 = document.getElementById('result_mbank_Account');
			var divResult = document.getElementById('result');
			var divtop = document.getElementById('top');
			var divtable = document.getElementById('table');

			
			var xhr1 = new XMLHttpRequest();
			xhr1.open("POST", "InsertAuthorServlet", true);
			xhr1.setRequestHeader("Content-Type",
					"application/x-www-form-urlencoded");
			xhr1.send("mpen_Name=" + mpen_Name + "&mbank_Account=" + mbank_Account);
			xhr1.onreadystatechange = function() {
				// 伺服器請求完成
				if (xhr1.readyState == 4 && xhr1.status == 200) {
					result = JSON.parse(xhr1.responseText);
					if (result.MySQL) {
						divResult.innerHTML = "<font color='red' >"
							+ result.MySQL + "</font>";
							
 						div1.innerHTML = "";	
						div2.innerHTML = "";
					
					} else {
						divResult.innerHTML = "";
						if (result.mpen_Name) {
							div1.innerHTML = "<font color='green' size='-2'>"
									+ result.mpen_Name + "</font>";
						} else {
							div1.innerHTML = "";
						}
						if (result.mbank_Account) {
							div2.innerHTML = "<font color='green' size='-2'>"
									+ result.mbank_Account + "</font>";
						} else {
							div2.innerHTML = "";
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
	<center>
	
		<div id='table'>
			<h2>升級成為作家</h2>

			<fieldset style='display: inline-block;'>
				<legend>下列資料都必填</legend>
				筆名: <input type="text" name="mpen_Name" id='mpen_Name'><br>
				<div id='result_mpen_Name' style="height: 10px;"></div><br>
				 銀行帳戶: <input type="password" name="mbank_Account" id='mbank_Account'><br>
				<div id='result_mbank_Account' style="height: 10px;"></div><br>
				
				<div id='result' style="height: 18px;"></div><br>
				<button id='sendData'>送出</button>
			</fieldset>
		</div>	
		<div id='top' style="height: 18px;"></div>

	</center>
</body>
</html>