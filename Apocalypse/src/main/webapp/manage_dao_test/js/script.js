$(function(){

	
	$('#operate_contents div[id != "op_tab1"]').hide();
	$('#operate_tabs li a').click(function(){

		$('#operate_contents div').hide();
		$($(this).attr('href')).show();
		$(".operateCurrent").removeClass("operateCurrent");
		$(this).addClass("operateCurrent");
		return false;
	});

	
	$('#dispTable_contents div[id != "dt_tab1"]').hide();
	$('#dt_tab1').show();
	$('#dt_tab1 div').show();
	$("#dispTable_tabs li a").click(function(){
		$("#dispTable_contents div").hide();
		$($(this).attr("href")).show();		
		$($(this).attr("href")).children().show();
		$($(this).attr("href")).children().children('div').show();
		$(".dispTableCurrent").removeClass("dispTableCurrent");
		$(this).addClass("dispTableCurrent");
		return false;
	});

		
	$('#NewChapter_chooseBookId').change(function(){
		if($('#NewChapter_chooseBookId').val() != 'none'){
			
//			alert($('#NewChapter_author_id').val());
			
			var xhr = null;
			if(window.XMLHttpRequest){
				xhr = new XMLHttpRequest();
			}else{
				xhr = new ActiveXObject("Microsoft.XMLHTTP");
			}
			xhr.open("POST","ManageAuthorService.do",true);
			xhr.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
			var data = "function_type=upload_newcontent_intro&"+"author_id="+$('#NewChapter_author_id').val()
			+"&book_id="+$('#NewChapter_chooseBookId').val();
			xhr.send(data);
			

			
			xhr.onreadystatechange = function() {
				if (xhr.readyState === 4){
					if (xhr.status === 200){
						$('#op_tab2').children('div').detach();
						var add_div = document.createElement("div");
						//新增動態按鈕和表單，需附加判斷:新卷第一章或者是舊卷新章
						var in_pool = xhr.responseText.split("|",4);
						var volume_number  = in_pool[0];
						var volume_title   = in_pool[1];
						var chapter_number = in_pool[2];
						var chapter_title  = in_pool[3];
						var author_id      = $('#NewChapter_author_id').val();
						var book_id        = $('#NewChapter_chooseBookId').val();				
						var state_info;
						
						if(volume_number == 0){
							state_info ="<h3>"+"目前該作品無連載章節"+"<h3>";
							var select_form_01 = '<form  action="ManageAuthorService.do" method="POST" >'
												+'<input type="hidden" name="function_type" value="upload_newvolume_mid">'	
												+'<input type="hidden" name="author_id" value="'+author_id+'">'
												+'<input type="hidden" name="book_id" value="'+book_id+'">'
												+'<input type="hidden" name="volume_number" value="'+1+'">'
												+'<input type="hidden" name="chapter_number" value="'+1+'">'
												+'<input type="submit" value="新增第1卷第1章">'
												+'</form>';						
							add_div.innerHTML = state_info +'<br>'+ select_form_01;
						}else{
							state_info = "<h3>"+"目前該作品連載至:<h3><br>"
						    			+"第"+volume_number+"卷"+" "+volume_title +" -- "
						    			+"第"+chapter_number+"章"+" "+chapter_title+"<br>";
							var select_form_01 = '<form  action="ManageAuthorService.do" method="POST">'
												+'<input type="hidden" name="function_type" value="upload_newvolume_mid">'
												+'<input type="hidden" name="author_id" value="'+author_id+'">'
												+'<input type="hidden" name="book_id" value="'+book_id+'">'
												+'<input type="hidden" name="volume_number"  value="'+String(parseInt(volume_number)+1)+'">'
												+'<input type="hidden" name="chapter_number" value="'+1+'">'
//												+'<input type="hidden" name="volume_title"   value="'+volume_title+'">'
//												+'<input type="hidden" name="chapter_title"  value="'+未決定+'">'
												+'<input type="submit" value="新增第'+String(parseInt(volume_number)+1)+'卷第1章">'
												+'</form>';
							var select_form_02 = '<form  action="ManageAuthorService.do" method="POST" >'
												+'<input type="hidden" name="function_type" value="upload_newchapter_mid">'	
												+'<input type="hidden" name="author_id" value="'+author_id+'">'
												+'<input type="hidden" name="book_id" value="'+book_id+'">'
												+'<input type="hidden" name="volume_number" value="'+volume_number+'">'
												+'<input type="hidden" name="chapter_number" value="'+String(parseInt(chapter_number)+1)+'">'
												+'<input type="hidden" name="volume_title"   value="'+volume_title+'">'
//												+'<input type="hidden" name="chapter_title"  value="'+chapter_title+'">'				
												+'<input type="submit" value="新增第'+volume_number+'卷第'+String(parseInt(chapter_number)+1)+'章">'
												+'</form>';	
							add_div.innerHTML = state_info +'<br>'+ select_form_01 +'<br>'+ select_form_02;	
						}
						document.getElementById("op_tab2").appendChild(add_div);
					}
				}								
				
			};
		}else{
			$('#op_tab2').children('div').detach();
		}	
	});	
	

	$('#EditContent_chooseBookId').change(function(){		
		if($('#EditContent_chooseBookId').val() != 'none'){
//			alert("!!!");
			var xhr = null;
			if(window.XMLHttpRequest){
				xhr = new XMLHttpRequest();
			}else{
				xhr = new ActiveXObject("Microsoft.XMLHTTP");
			}
			xhr.open("POST","ManageAuthorService.do",true);
			xhr.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
			var data = "function_type=alter_newcontent_intro&"+"author_id="+$('#EditContent_author_id').val()
			+"&book_id="+$('#EditContent_chooseBookId').val();
			xhr.send(data);
			
			xhr.onreadystatechange = function() {
				if (xhr.readyState === 4){
					if (xhr.status === 200){
						$('#op_tab4').children('div').detach();
						var add_div = document.createElement("div");
						//新增動態按鈕和表單，需附加判斷:新卷第一章或者是舊卷新章
						var in_pool = xhr.responseText.split("|",4);
						var volume_number  = in_pool[0];
						var volume_title   = in_pool[1];
						var chapter_number = in_pool[2];
						var chapter_title  = in_pool[3];
						var author_id      = $('#EditContent_author_id').val();
						var book_id        = $('#EditContent_chooseBookId').val();				
						var state_info;
						if(volume_number == 0){
							alter_state_info ="<h3>"+"目前該作品無連載章節，無法修改內容"+"<h3>";						
							add_div.innerHTML = alter_state_info +'<br>';
						}else{
							alter_state_info = "<h3>"+"目前該作品連載至:<h3><br>"
						    			+"第"+volume_number+"卷"+" "+"第"+chapter_number+"章"+" "+"<br>";
							var select_form_01_01 = '<h4>請選擇要修改標題的卷次:</h4>'
												   +'<form action="ManageAuthorService.do" method="POST">'
												   +'<input type="hidden" name="function_type" value="alter_volume_mid">'
												   +'<input type="hidden" name="author_id" value="'+author_id+'">'
												   +'<input type="hidden" name="book_id" value="'+book_id+'">'
												   +'第'
												   +'<select name="alter_volumeId">';
							var select01_opt;
							var select01_temp;					   
							for(var i=1;i<=volume_number;i++){
								select01_temp = '<option value ="'+i+'">'+i+'</option>';
								select01_opt = select01_opt + select01_temp;
							}					   				
							var select_form_01_02= '</select>'
												  +'卷'
												  +'<br>'
												  +'<input type="submit" value="修改卷標題">'
												  +'</form>';


												
							var select_form_02_01 = '<h4>請選擇要修改內容的章節:</h4>'
												+'<form action="ManageAuthorService.do" method="POST">'
												+'<input type="hidden" name="function_type" value="alter_chapter_mid">'
												+'<input type="hidden" name="author_id" value="'+author_id+'">'
												+'<input type="hidden" name="book_id" value="'+book_id+'">'
												+'第'
												+'<select id="select_vol" name="alter_volumeId">'
												+'<option id="option_temp"  value ="">---</option>';
							var select02_opt;
							var select02_temp;					   
							for(var i=1;i<=volume_number;i++){
								select02_temp = '<option value ="'+i+'">'+i+'</option>';
								select02_opt = select02_opt + select02_temp;
							}
							var select_form_02_02 =	'</select>'
								  				   +'卷'
								  				   +' 第'				
								  				   +'<select id="select_ch" name="alter_chapterId">';
								  				   
							var select03_opt  = '<option  value ="">---</option>';

							var select_form_02_03 = '</select>'
								  				    +'章'
								  				    +'<br>'
													+'<input type="submit" value="修改章節內容">'
													+'</form>';	
							add_div.innerHTML = alter_state_info +'<br>'+select_form_01_01+select01_opt+select_form_01_02+'<br>'+'<hr>'
											  	+select_form_02_01+select02_opt+select_form_02_02+select03_opt+select_form_02_03;	
						}
						document.getElementById("op_tab4").appendChild(add_div);
					}
				}								
				
			};
		}else{
			$('#op_tab4').children('div').detach();
		}	
	});	
		
	
	$(document).on('change','#select_vol',function(event){
		$('#option_temp').remove();

		var xhr = null;
		if(window.XMLHttpRequest){
			xhr = new XMLHttpRequest();
		}else{
			xhr = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xhr.open("POST","ManageAuthorService.do",true);
		xhr.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
		var data = "function_type=alter_chapter_intro&"+"author_id="+$('#EditContent_author_id').val()
		+"&book_id="+$('#EditContent_chooseBookId').val()+"&volume_id="+$('#select_vol').val();
		xhr.send(data);
		
		var Max_chapter_number;
		
		xhr.onreadystatechange = function() {
			if (xhr.readyState === 4){
				if (xhr.status === 200){
					Max_chapter_number=xhr.responseText;
	
	//				alert(Max_chapter_number);
					var Refine_select_temp;
					var Refine_select_opt;
					for(var i=1;i<=Max_chapter_number;i++){
						Refine_select_temp = '<option value ="'+i+'">'+i+'</option>';
						Refine_select_opt = Refine_select_opt + Refine_select_temp;
				}

				$('#select_ch').empty();
				$('#select_ch').append(Refine_select_opt);
			
				}
			}
		}

	
	});
	

});

