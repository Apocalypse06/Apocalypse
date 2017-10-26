<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="js/jquery-3.2.1.min.js"></script>
<title>Insert title here</title>
</head>
<script>
	window.onload = function() {
		var sendData = document.getElementById("sendData");
		// 	var sendSonData = document.getElementById("sendSonData");
		var divtable = document.getElementById("table");
		var comment1 = document.getElementById("comment");
		var comment = document.getElementById("comment").value;

		// 	var subcomment = document.getElementById("subcomment").value;
		id = 94;
		book = 1;

		var xhr1 = new XMLHttpRequest();
		xhr1.open("POST", "WriteComment", true);
		xhr1.setRequestHeader("Content-Type",
				"application/x-www-form-urlencoded");
		xhr1.send("comment=" + comment + "&book_Id=" + book);
		xhr1.onreadystatechange = function() {
			if (xhr1.readyState == 4 && xhr1.status == 200) {
				result = JSON.parse(xhr1.responseText);
				comment1.innerHTML = "";
				
				member = result[0];
				comments = result[1];
				totle = result[2];
				subcomments = result[3];
				submember = result[4];
				table = document.createElement("table");

				table.id = "old";
				for (var i = 0; i < comments.length; i++) {

					tr = document.createElement("tr");
					td1 = document.createElement("td");
					td1.style["vertical-align"] = 'text-top';
					td1.innerHTML = (i + 1) + "樓";
					tr.appendChild(td1);
					td2 = document.createElement("td");
					td2.style.verticalAlign = 'text-top';
					td2.innerHTML = member[i].nick_Name;
					tr.appendChild(td2);
					td3 = document.createElement("td");
					td3.innerHTML = "<div style='width:200px;  border-style:dashed; border-width:1px;   word-wrap:break-word; '  >"
							+ comments[i].comment + "</div>";
					tr.appendChild(td3);
					td4 = document.createElement("td");
					td4.style.verticalAlign = 'text-top';
					var w = "bookcomments.jsp?a=" + i;
					td4.innerHTML = "<a href='<c:url value='"+w+"'/>'>"
							+ totle[i] + "則留言</a>";
					tr.appendChild(td4);
					table.appendChild(tr);

					for (var j = 0; j < subcomments[i].length; j++) {

						div1 = document.createElement("div");
						div1.innerHTML = (submember[i])[j].nick_Name;
						table.appendChild(div1);

						div2 = document.createElement("div");
						div2.innerHTML = "<div style='width:200px;  border-style:dashed; border-width:1px;   word-wrap:break-word; '  >"
								+ (subcomments[i])[j].comments + "</div>";
						table.appendChild(div2);
					}
					if (i == 0) {

						
						div3 = document.createElement("div");
						div3.innerHTML = "請輸入子留言：<textarea  id='subcomment' name='comment' rows='3' maxlength=100 placeholder='最多100字' style='resize:none;' ></textarea>"
								+ "<button id='sendSonData'>送出</button>";

					}

					table.appendChild(div3);
					divtable.appendChild(table);
				}
			}
		}

		//sendData.onclick = function() {
		$(document).on(	"click",'#sendData',function(){	
			var comment = document.getElementById("comment").value;
			if (document.getElementById("old")) {
				divtable.removeChild(old);
			}

			var xhr1 = new XMLHttpRequest();
			xhr1.open("POST", "WriteComment", true);
			xhr1.setRequestHeader("Content-Type",
					"application/x-www-form-urlencoded");
			xhr1.send("comment=" + comment + "&book_Id=" + book);
			xhr1.onreadystatechange = function() {
				if (xhr1.readyState == 4 && xhr1.status == 200) {
					result = JSON.parse(xhr1.responseText);
					
					
					
					member = result[0];
					comments = result[1];
					totle = result[2];
					subcomments = result[3];
					submember = result[4];
					table = document.createElement("table");

					table.id = "old";
					for (var i = 0; i < comments.length; i++) {

						tr = document.createElement("tr");
						td1 = document.createElement("td");
						td1.style["vertical-align"] = 'text-top';
						td1.innerHTML = (i + 1) + "樓";
						tr.appendChild(td1);
						td2 = document.createElement("td");
						td2.style.verticalAlign = 'text-top';
						td2.innerHTML = member[i].nick_Name;
						tr.appendChild(td2);
						td3 = document.createElement("td");
						td3.innerHTML = "<div style='width:200px;  border-style:dashed; border-width:1px;   word-wrap:break-word; '  >"
								+ comments[i].comment + "</div>";
						tr.appendChild(td3);
						td4 = document.createElement("td");
						td4.style.verticalAlign = 'text-top';
						var w = "bookcomments.jsp?a=" + i;
						td4.innerHTML = "<a href='<c:url value='"+w+"'/>'>"
								+ totle[i] + "則留言</a>";
						tr.appendChild(td4);
						table.appendChild(tr);
						for (var j = 0; j < subcomments[i].length; j++) {

							div1 = document.createElement("div");
							div1.innerHTML = (submember[i])[j].nick_Name;
							table.appendChild(div1);

							div2 = document.createElement("div");
							div2.innerHTML = "<div style='width:200px;  border-style:dashed; border-width:1px;   word-wrap:break-word; '  >"
									+ (subcomments[i])[j].comments + "</div>";

							table.appendChild(div2);

						}
						if (i == 0) {

							
							div3 = document.createElement("div");
							div3.innerHTML = "請輸入子留言：<textarea  id='subcomment' name='comment' rows='3' maxlength=100 placeholder='最多100字' style='resize:none;' ></textarea>"
									+ "<button id='sendSonData'>送出</button>";

						}
						
						
						table.appendChild(div3);
						divtable.appendChild(table);
					}
				}
			}
			

			$("#comment").val('');
		});

		// 	sendSonData.onclick = function(){
		$(document).on(	"click",'#sendSonData',function() {

							subcomment = $("#subcomment").val();

							if (old) {
								divtable.removeChild(old);
							}

							var xhr1 = new XMLHttpRequest();
							xhr1.open("POST", "WriteComment", true);
							xhr1.setRequestHeader("Content-Type",
									"application/x-www-form-urlencoded");
							xhr1.send("subcomment=" + subcomment + "&book_Id="
									+ book + "&mcomment_id=" + id);
							xhr1.onreadystatechange = function() {
								if (xhr1.readyState == 4 && xhr1.status == 200) {
									comment1.innerHTML = "";
									
									result = JSON.parse(xhr1.responseText);
									member = result[0];
									comments = result[1];
									totle = result[2];
									subcomments = result[3];
									submember = result[4];

									table = document.createElement("table");

									table.id = "old";
									for (var i = 0; i < comments.length; i++) {

										tr = document.createElement("tr");
										td1 = document.createElement("td");
										td1.style["vertical-align"] = 'text-top';
										td1.innerHTML = (i + 1) + "樓";
										tr.appendChild(td1);
										td2 = document.createElement("td");
										td2.style.verticalAlign = 'text-top';
										td2.innerHTML = member[i].nick_Name;
										tr.appendChild(td2);
										td3 = document.createElement("td");
										td3.innerHTML = "<div style='width:200px;  border-style:dashed; border-width:1px;   word-wrap:break-word; '  >"
												+ comments[i].comment
												+ "</div>";
										tr.appendChild(td3);
										td4 = document.createElement("td");
										td4.style.verticalAlign = 'text-top';
										var w = "bookcomments.jsp?a=" + i;
										td4.innerHTML = "<a href='<c:url value='"+w+"'/>'>"
												+ totle[i] + "則留言</a>";
										tr.appendChild(td4);
										table.appendChild(tr);
										for (var j = 0; j < subcomments[i].length; j++) {

											div1 = document
													.createElement("div");

											div1.innerHTML = (submember[i])[j].nick_Name;
											table.appendChild(div1);

											div2 = document
													.createElement("div");
											div2.innerHTML = "<div style='width:200px;  border-style:dashed; border-width:1px;   word-wrap:break-word; '  >"
													+ (subcomments[i])[j].comments
													+ "</div>";
											table.appendChild(div2);
											

										}
										if (i == 0) {

											
											div3 = document
													.createElement("div");
											div3.innerHTML = "請輸入子留言：<textarea  id='subcomment' name='comment' rows='3' maxlength=100 placeholder='最多100字' style='resize:none;' ></textarea>"
													+ "<button id='sendSonData'>送出</button>";
											

										}
										table.appendChild(div3);
										
										divtable.appendChild(table);
									}
								}
							}
							
						});
		

	}
</script>
<body>
	<center>
		<table>
			<tr>
				<td>樓層</td>
				<td>會員</td>
				<td>評論</td>
			</tr>
		</table>
		<div id="table"></div>
		<div>
			請輸入留言：
			<textarea id="comment" name="comment" rows="3" maxlength=100
				placeholder="最多100字" style="resize: none;"></textarea>
			<button id='sendData'>送出</button>
		</div>


		<!-- 		<div> -->
		<!-- 			請輸入子留言：<textarea id='subcomment' name='comment' rows='3' maxlength=100 placeholder='最多100字' style='resize:none;'></textarea> -->
		<!-- 			<button id='sendSonData'>送出</button> -->
		<!-- 		</div> -->


	</center>
</body>
</html>