$(function(){








    $("body").on("change", "#alter_cover", function (){
        
        if (this.files && this.files[0]) {
        	var reader = new FileReader();    
        	reader.onload = function (e) {
            	$('#alter_cover_disp').attr('src', e.target.result);
        	}

        	reader.readAsDataURL(this.files[0]);
		} 

    })

});


