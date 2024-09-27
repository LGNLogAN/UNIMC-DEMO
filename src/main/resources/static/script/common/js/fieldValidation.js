let isAuthentication = false;

function validateUniversityEmail(email) {
	const pattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.ac\.kr$/;
	return pattern.test(email);
}

function checkPasswordMatch() {
	const passwordValue = $("#password").val();
	const passwordCheckValue = $("#password_check").val();

	if (passwordValue === passwordCheckValue) {
		$("#password-match-fail").hide();
		$("#password-match-pass").text("비밀번호가 일치합니다.").show();
	} else {
		$("#password-match-pass").hide();
		$("#password-match-fail").text("비밀번호가 일치하지 않습니다.").show();
	}
}

function checkName(){
	const nameValue = $('#name').val().trim(); // 공백 제거
	const nameRegex = /^[가-힣]{2,4}$/;
	
	if (!nameValue) {
		return false;
		
	} else if (!nameRegex.test(nameValue)) {
		return false;
		
	} else {
		return true;
	}
}

function checkMcName(){
	const mcNameValue = $('#mcname').val().trim();
	const mcNameRegex = /^[a-zA-Z0-9_]{3,16}$/;

	if (!mcNameValue) {
		return false;
	} else if (!mcNameRegex.test(mcNameValue)) {
		return false;
	} else {
		return true;
	}
}

function checkEmail(){
	const emailValue = $("#email").val().trim();
	
	if (!emailValue) {
		return false;
	} else if (!validateUniversityEmail(emailValue)) {
		return false;
	} else {
		return true;
	}
}

function checkAuthCode(){
	const codeValue = $("#code").val().trim();
	const authCodeRegex = /^\d{6}$/;
	
	if (!codeValue) {
		return false;
	} else if (!authCodeRegex.test(codeValue)) {
		return false;
	} else {
		// isAuthentication = true;
		return true;
	}
}

function checkPassword(){
	const passwordValue = $("#password").val();
	const passwordCheckValue = $("#password_check").val();

	if (!passwordValue) {
		return false;
	}else if(!passwordCheckValue){
		return false;
	}else if (passwordValue != passwordCheckValue) {
		return false;
	}else{
		return true;
	}
}

function checkMajor(){
	const majorValue = $("#major").val().trim();
	
	if (!majorValue) {
		return false;
	}else{
		return true;
	}
}

function checkCampus(){
	const campusValue = $("#campus").val().trim();
	
	if (!campusValue) {
		return false;
	}else{
		return true;
	}
}

$(function() {
	// 기본 폼 제출 방지
	$('form').on('keydown', function(event) {
		if (event.key === 'Enter') {
			event.preventDefault();
		}
	});

	$("#btn-send").on('click', function(event) {
		event.preventDefault();
		const emailInput = $('#email');
		const email = emailInput.val().trim();

		if (!validateUniversityEmail(email)) {
			alert("올바른 대학교 이메일을 입력해주세요. (예: example@school.ac.kr)");
			emailInput.focus();

			return; // 이메일 형식이 올바르지 않으면 더 이상 진행하지 않고 함수 종료
		}

		$.ajax({
			type: "GET",
			url: "/emailCheck",
			data: { uniEmail: email },
			dataType: "text",
			success: function(response) {
				if (response == "Duplicate") {
					alert("이미 등록된 이메일입니다.");
					$("#error-message-emailInput").text("본인이 가입한게 아니라면? 디스코드 : mubin_c 로 연락주세요 !");
					emailInput.focus();
				} else if (response == "NotFound") {
					alert("등록되어있지 않은 대학교입니다.");
					$("#error-message-emailInput").text("대학교를 등록하려면 ? 디스코드 : mubin_c 로 연락주세요 !");
					emailInput.focus();
				} else {
					alert("입력하신 이메일이 인증코드를 전송하였습니다 !");
					$(".input-form-auth").css('display', 'block');
				}
			},
			error: function() {
				alert("에러코드 : 500");
			}
		});
	});
	// isAuthentication 이라는 변수 선언하고 인증코드로 인증이 되면 true 로 바꿔줌 위에 변수있음
	$("#btn-check").on('click' , function(event){
		event.preventDefault();
		
		const inputCode = $("#code").val().trim();
		
		let code = "123456";
		
		if(inputCode == code){
			$("#code-match-pass").text("인증되셨습니다");
			
			$("#code-match-pass").css('display', 'block');
			$("#code-match-fail").css('display', 'none');
			
			$("#btn-check").attr('disabled' , true);
			$("#btn-send").attr('disabled' , true);
			
			$("#email").prop('readonly' , true);
			$("#code").attr("disabled" , true);
			
		}else{
			$("#code-match-fail").text("옳지않은 인증코드입니다.");
			
			$("#code-match-fail").css('display', 'block');
			$("#code-match-pass").css('display', 'none');
			
			$("#btn-check").attr('disabled' , false);
			$("#btn-send").attr('disabled' , false);
			
			$("#email").prop('readonly' , false);
			$("#code").attr("disabled" , false);
		}
		
	});

	
	// 공통적 회원가입 요소
	
	// 기본 오류메세지 자동 출력 방지
	$('#error-message-mcname').hide();

	// 마인크래프트 닉네임 입력 규칙
	$('#mcname').on('blur', function() {
		const mcNameValue = $('#mcname').val().trim(); // 공백 제거
		const mcNameRegex = /^[a-zA-Z0-9_]{3,16}$/;

		if (!mcNameValue) {
			$('#error-message-mcname').text("이름은 필수값입니다.").show();

		} else if (!mcNameRegex.test(mcNameValue)) {
			$('#error-message-mcname').text("올바른 마인크래프트 닉네임을 적어주세요 !").show();
		} else {
			$('#error-message-mcname').hide();
			$('#mcname').val(mcNameValue);
		}
	});


	// 비밀번호 확인
	$("#password").on('blur', function() {
		if ($("#password_check").val()) {  // 비밀번호 확인 필드가 비어있지 않은 경우에만 검사
			checkPasswordMatch();
		} else {
			$("#password-match-fail").hide();
			$("#password-match-pass").hide();
		}
	});

	// 비밀번호 확인 
	$("#password_check").on('blur', function() {
		if ($("#password_check").val()) {  // 비밀번호 확인 필드가 비어있지 않은 경우에만 검사
			checkPasswordMatch();
		} else {
			$("#password-match-fail").hide();
			$("#password-match-pass").hide();
		}
	});

	$("#btn-signup").on('click',function(){
		if(checkName() && checkMcName() && checkEmail() && checkAuthCode() && checkPassword() && checkMajor() && checkCampus()){
			alert("회원가입 완료");
			$("#signup-form").submit();
		}else{
			alert("안 돼");
		}
	});
	
});





