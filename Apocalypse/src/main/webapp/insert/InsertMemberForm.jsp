<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>IRead愛閱讀</title>
</head>
<script> 
window.onload = function() {
	
		var sendData = document.getElementById("sendData");
				
		sendData.onclick = function() {
			// 讀取欄位資料	  
			
			var all = document.getElementById("all");
			var mAccount = document.getElementById("mAccount").value;
			var mPswd = document.getElementById("mPswd").value;
			var mNick_Name = document.getElementById("mNick_Name").value;
			var mBirthday = document.getElementById("mBirthday").value;
			var mCellphone = document.getElementById("mCellphone").value;
			
				
			
			
			for (var i=0; i<all.mGender.length; i++)
			{
			   if (all.mGender[i].checked)
			   {
			      var mGender = all.mGender[i].value;
			      
			   }
			}
			
			var hasError = false; // 設定判斷有無錯誤的旗標
			var div1 = document.getElementById('result_mAccount');
			var div2 = document.getElementById('result_mPswd');
			var div3 = document.getElementById('result_mNick_Name');
			var div4 = document.getElementById('result_mBirthday');
			var div5 = document.getElementById('result_mCellphone');
			var div6 = document.getElementById('result_mGender');
			var divResult = document.getElementById('result');
			var divtop = document.getElementById('top');
			var divtable = document.getElementById('table');
					
			
			var xhr1 = new XMLHttpRequest();
			xhr1.open("POST", "InsertMemberServlet", true);
			xhr1.setRequestHeader("Content-Type",
					"application/x-www-form-urlencoded");
			xhr1.send("mAccount=" + mAccount + "&mPswd=" + mPswd+ "&mNick_Name=" + mNick_Name
					+ "&mBirthday=" + mBirthday+ "&mCellphone=" + mCellphone+ "&mGender=" + mGender);
			
			xhr1.onreadystatechange = function() {
				// 伺服器請求完成
				if (xhr1.readyState == 4 && xhr1.status == 200) {
					result = JSON.parse(xhr1.responseText);
					if (result.MySQL) {
						divResult.innerHTML = "<font color='red' >"
							+ result.MySQL + "</font>";
							
 						div1.innerHTML = "";	
						div2.innerHTML = "";
						div3.innerHTML = "";
						div4.innerHTML = "";
						div5.innerHTML = "";
						div6.innerHTML = "";
						
					} else {
						divResult.innerHTML = "";
						if (result.mAccount) {
							div1.innerHTML = "<font color='green' size='-2'>"
									+ result.mAccount + "</font>";
						} else {
							div1.innerHTML = "";
						}
						if (result.mPswd) {
							div2.innerHTML = "<font color='green' size='-2'>"
									+ result.mPswd + "</font>";
						} else {
							div2.innerHTML = "";
						}
						if (result.mNick_Name) {
							div3.innerHTML = "<font color='green' size='-2'>"
									+ result.mNick_Name + "</font>";
						} else {
							div3.innerHTML = "";
						}
						if (result.mBirthday) {
							div4.innerHTML = "<font color='green' size='-2'>"
									+ result.mBirthday + "</font>";
							
						} else {
							div4.innerHTML = "";
						}
						if (result.mCellphone) {
							div5.innerHTML = "<font color='green' size='-2'>"
									+ result.mCellphone + "</font>";
						} else {
							div5.innerHTML = "";
						}
						if (result.mGender) {
							div6.innerHTML = "<font color='green' size='-2'>"
									+ result.mGender + "</font>";
						} else {
							div6.innerHTML = "";
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
			<h2>註冊帳號</h2>

			<fieldset style='display: inline-block;'>
				<legend>下列資料都必填</legend>
				帳號: <input type="text" name="mAccount" id='mAccount'value="${param.mAccount}"placeholder="(e-mail)"><br>
				<div id='result_mAccount' style="height: 10px;"></div><br>
				 密碼: <input type="password" name="mPswd" id='mPswd'value="${param.mPswd}"placeholder="8-16位(只可有英文和數字)"><br>
				<div id='result_mPswd' style="height: 10px;"></div><br>
				暱稱: <input type="text" name="mNick_Name" id='mNick_Name'value="${param.mNick_Name}"placeholder="2-10位(只可有中英文和數字)"><br>
				<div id='result_mNick_Name' style="height: 10px;"></div><br>
				生日: <input type="text" name="mBirthday" id='mBirthday'value="${param.mBirthday}"placeholder="(yyyy-mm-dd)"><br>
				<div id='result_mBirthday' style="height: 10px;"></div><br>
				手機: <input type="text" name="mCellphone" id='mCellphone'value="${param.mCellphone}"placeholder="(台灣手機門號)"><br>
				<div id='result_mCellphone' style="height: 10px;"></div><br>
				<form id="all">
					性別: <label><input type="radio"name="mGender" class="mGender"value="M"  size="10" checked >男</label>
				         <label><input type="radio"name="mGender" class="mGender"value="F"  size="10">女</label><br>
						 <div id='result_mGender' style="height: 10px;"></div><br>
				</form>
				<div id='result' style="height: 18px;"></div><br>
				<button id='sendData'>送出</button>
				<a href="InsertMemberForm.jsp?mAccount=taipeijava006@gmail.com&mPswd=a1234567&mNick_Name=Java006&mBirthday=2017-05-10&mCellphone=0919612456">
					小幫手
				</a>
			</fieldset>
		</div>	
		<div id='top' style="height: 18px;"></div>
		






</center>
</body>
</html>