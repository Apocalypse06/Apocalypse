<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改會員資料</title>
</head>
<script> 
window.onload = function() {
	
		var sendData = document.getElementById("sendData");
				
		sendData.onclick = function() {
			// 讀取欄位資料	  
			
			var mEmail = document.getElementById("mEmail").value;
			var mPswd = document.getElementById("mPswd").value;
			var mNick_Name = document.getElementById("mNick_Name").value;
			var mBirthday = document.getElementById("mBirthday").value;
			var mCellphone = document.getElementById("mCellphone").value;
			var M = document.getElementById("M");
			var F = document.getElementById("F");
			if(M){
				for (var i=0; i<M.mGenderM.length; i++)
				{
				   if (M.mGenderM[i].checked)
				   {
				      var mGender = M.mGenderM[i].value;
				      
				   }
				}
			}else if(F){
				for (var i=0; i<F.mGenderF.length; i++)
				{
				   if (F.mGenderF[i].checked)
				   {
				      var mGender = F.mGenderF[i].value;
				      
				   }
				}
			}
			
			
			// 			var mGender1 = document.getElementById("mGender1")
// 			var mGender2 = document.getElementById("mGender2")
// 			var mGender3 = document.getElementById("mGender3")
// 			var mGender4 = document.getElementById("mGender4")
// 			for (var i=1; i<=4; i++)
// 			{
// 			   if (("mGender"+i).checked)
// 			   {
// 			      var mGender = ("mGender"+i).value;
// 			      break;
// 			   }
// 			}
			
			var hasError = false; // 設定判斷有無錯誤的旗標
			var div1 = document.getElementById('result_mEmail');
			var div2 = document.getElementById('result_mPswd');
			var div3 = document.getElementById('result_mNick_Name');
			var div4 = document.getElementById('result_mBirthday');
			var div5 = document.getElementById('result_mCellphone');
			var div6 = document.getElementById('result_mGender');
			var divResult = document.getElementById('result');
			var divtop = document.getElementById('top');
			var divtable = document.getElementById('table');
					
			
			var xhr1 = new XMLHttpRequest();
			xhr1.open("POST", "ChangeMemberServlet", true);
			xhr1.setRequestHeader("Content-Type",
					"application/x-www-form-urlencoded");
			xhr1.send("mEmail=" + mEmail + "&mPswd=" + mPswd+ "&mNick_Name=" + mNick_Name
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
						if (result.mEmail) {
							div1.innerHTML = "<font color='green' size='-2'>"
									+ result.mEmail + "</font>"+"${aaa}";
						} else {
							div1.innerHTML = "";
						}
						if (result.mPswd) {
							div2.innerHTML = "<font color='green' size='-2'>"
									+ result.mPswd + "</font>"+"${bbb}";
						} else {
							div2.innerHTML = "";
						}
						if (result.mNick_Name) {
							div3.innerHTML = "<font color='green' size='-2'>"
									+ result.mNick_Name + "</font>";
						} else {
							div3.innerHTML = "";
						}
						if (result.mBirthday||result.mBday) {
							div4.innerHTML = "<font color='green' size='-2'>"
									+ result.mBirthday + "</font>";
							div4.innerHTML = "<font color='green' size='-2'>"
								+ result.mBday + "</font>";
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
		<div id="table">
			<h2>修改會員資料</h2>

			<fieldset style='display: inline-block;'>
				<legend>可修改下列資料</legend>
				Email: <input type="text" name="mEmail" id='mEmail'value="${LoginOK.email}"placeholder="(e-mail)"><br>
				<div id='result_mEmail' style="height: 10px;"></div><br>
				 密碼: <input type="password" name="mPswd" id='mPswd'value="${LoginOK.pswd}"placeholder="8-16位(只可有英文和數字)"><br>
				<div id='result_mPswd' style="height: 10px;"></div><br>
				<div style="display:none">
				暱稱: <input type="text" name="mNick_Name" id='mNick_Name'value="${LoginOK.nick_Name}"placeholder="2-10位(只可有中英文和數字)" style="display:none"><br>
				<div id='result_mNick_Name' style="height: 10px;"></div><br>
				</div>
				生日: <input type="text" name="mBirthday" id='mBirthday'value="${LoginOK.birthday}"placeholder="(yyyy-mm-dd)"><br>
				<div id='result_mBirthday' style="height: 10px;"></div><br>
				手機: <input type="text" name="mCellphone" id='mCellphone'value="${LoginOK.cellphone}"placeholder="(台灣手機門號)"><br>
				<div id='result_mCellphone' style="height: 10px;"></div><br>
				<c:choose>
					<c:when test='${LoginOK.gender == "M"}'>
						<form id="M">
							性別:<input type="radio"name="mGenderM"class="mGender" value="M"  size="10" checked>男
				       			<input type="radio"name="mGenderM" class="mGender"value="F"  size="10">女
					 	</form>
					</c:when>
					<c:when test='${LoginOK.gender == "F"}'>
						<form id="F">
							性別:<input type="radio"name="mGenderF" class="mGender"value="M"  size="10">男
				       			<input type="radio"name="mGenderF" class="mGender"value="F"  size="10" checked>女
						</form>	
					</c:when>
				</c:choose><br>
				<div id='result_mGender' style="height: 10px;"></div><br>
				<div id='result' style="height: 18px;"></div><br>
				<button id='sendData'>送出</button>
			</fieldset>
		</div>
		<div id='top' style="height: 18px;"></div>

	</center>
</body>
</html>