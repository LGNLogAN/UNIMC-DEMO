// 이메일 형식 검사 함수
function validateUniversityEmail(email) {
    const pattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.ac\.kr$/;
    return pattern.test(email);
}

// 버튼 클릭시 정규식으로 이메일 확인
document.querySelector('.btn-send').addEventListener('click', (event) =>  {
	event.preventDefault();
    const emailInput = document.getElementById('email');
    const email = emailInput.value.trim();
	
    if (validateUniversityEmail(email)) {
        alert("코드가 전송되었습니다! 이메일을 확인해주세요.");
        
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