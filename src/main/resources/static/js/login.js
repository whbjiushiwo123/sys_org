var $j = jQuery.noConflict();


$j(document).ready(function(c) {
	login();
	show(); 
});

function show(){
	$j('.close').on('click', function(c){
		$j('.login-form').fadeOut('slow', function(c){
	  		$j('.login-form').remove();
		});
	});	
}
function login(){
	$j("input[name='Login']").click(function(){
		var name=$j("input[name='name']").val();
		var password=$j("input[name='password']").val();
		var data={ "name":name, "password":password};
	    $j.ajax({
	    	url:"/user/login",
	    	method: "POST",
	    	contentType : "application/json",
	    	dataType:"json",
	    	data:JSON.stringify(data),
	    	success:function(result){
	    		if(result.success==true){
	    			location.href=result.url;
	    		}else if(result.success==false){
	    			alert("用户名或者密码不正确");
	    		}
	    	}
	    });
	});
}