// 이메일 형식 검사 함수
function validateUniversityEmail(email) {
	const pattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.ac\.kr$/;
	return pattern.test(email);
}

// 버튼 클릭시 정규식으로 이메일 확인
document.querySelector('.btn-send').addEventListener('click', (event) => {
	event.preventDefault();
	const emailInput = document.getElementById('email');
	const email = emailInput.value.trim();

	if (validateUniversityEmail(email)) {
		alert("코드가 전송되었습니다! 이메일을 확인해주세요.");
		document.querySelector('.input-form-auth').style.display = 'block';

	} else {
		alert("올바른 대학교 이메일을 입력해주세요. (예: example@school.ac.kr)");
		emailInput.focus();
	}
});

// 인증코드 확인 버튼 클릭 이벤트 ( 단순 예시 기능 구현 필요 )
document.querySelector('.btn-check').addEventListener('click', (event) => {
	event.preventDefault();
	const codeInput = document.getElementById('code');
	const inputCode = codeInput.value.trim();

	const correctCode = "123456";

	if (inputCode === correctCode) {
		document.querySelector('.authentication-pass').style.display = 'inline';
		document.querySelector('.authentication-fail').style.display = 'none';
	} else {
		document.querySelector('.authentication-pass').style.display = 'none';
		document.querySelector('.authentication-fail').style.display = 'inline';
	}
});


// 기본 폼 제출 방지
$('form').on('keydown', function(event) {
	if (event.key === 'Enter') {
		event.preventDefault();
	}
});

// 공통적 회원가입 요소
const mcName = $('#mcname');
const mcName_Error_message = $('#error-message-mcname');

const password = $('#password');
const password_check = $('#password_check');

// 기본 오류메세지 자동 출력 방지
mcName_Error_message.hide();

// 마인크래프트 닉네임 입력 규칙
mcName.on('blur', function() {
	const mcNameValue = mcName.val().trim(); // 공백 제거
	const mcNameRegex = /^[a-zA-Z0-9_]{3,16}$/;

	if (!mcNameValue) {
		mcName_Error_message.text("이름은 필수값입니다.").show();

	} else if (!mcNameRegex.test(mcNameValue)) {
		mcName_Error_message.text("올바른 마인크래프트 닉네임을 적어주세요 !").show();
	} else {
		mcName_Error_message.hide();
		mcName.val(mcNameValue);
	}
});

// 비밀번호 일치 확인
function checkPasswordMatch() {
	const passwordValue = password.val();
	const passwordCheckValue = password_check.val();

	if (passwordValue === passwordCheckValue) {
		$("#password-match-fail").hide();
		$("#password-match-pass").text("비밀번호가 일치합니다.").show();
	} else {
		$("#password-match-pass").hide();
		$("#password-match-fail").text("비밀번호가 일치하지 않습니다.").show();
	}
}

// 비밀번호 확인
password.on('blur', function() {
	if (password_check.val()) {  // 비밀번호 확인 필드가 비어있지 않은 경우에만 검사
		checkPasswordMatch();
	} else {
		$("#password-match-fail").hide();
		$("#password-match-pass").hide();
	}
});

// 비밀번호 확인 
password_check.on('blur', function() {
	if (password_check.val()) {  // 비밀번호 확인 필드가 비어있지 않은 경우에만 검사
		checkPasswordMatch();
	} else {
		$("#password-match-fail").hide();
		$("#password-match-pass").hide();
	}
});
