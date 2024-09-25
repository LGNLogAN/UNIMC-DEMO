$(function(){
		$("#loginBtn").click(function(){
			let loginData = {
				"uniEmail" : $("[name='uniEmail']").val(),
				"password" : $("[name='password']").val()
			}	
			
			$.ajax({
				url 		: "/login",
				type 		: "post",
				contentType : "application/json",
				data 	 	: JSON.stringify(loginData),
				success 	: function(isValidUser){
					if (isValidUser == "y"){
						location.href = "/";
					}
					else {
						$("#failMsg").html("<span style='color:red;'>아이디와 패스워드를 확인하세요.</span>");
					}
				}
			});
		});
	});