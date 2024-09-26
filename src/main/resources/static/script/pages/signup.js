// 입력한 값이 10글자가 넘을 경우 표시
//	const maxLength = 10;
//	name.on('input', function() {
//		let currentValue = $(this).val(); // 현재 값 가져오기
//        
//        if (currentValue.length > maxLength) {
//            $(this).val(currentValue.slice(0, maxLength)); // 최대 길이 초과 시 잘라내기
//            name_Error_message.text("최대 10자까지 가능합니다").show(); // 메시지 보여주기
//        } else {
//            name_Error_message.hide(); // 길이 제한이 지켜질 때 메시지 숨기기
//        }
//	});

$(function() {

	// 기본 폼 제출 방지
	$('form').on('keydown', function(event) {
		if (event.key === 'Enter') {
			event.preventDefault();
		}
	});
	const name = $('#name');
	const name_Error_message = $('#error-message-name');

	const major = $('#major');
	const campus = $('#campus');

	// 기본 오류메세지 자동 출력 방지
	name_Error_message.hide();

	// 이름 입력 규칙
	name.on('blur', function() {
		const nameValue = name.val().trim(); // 공백 제거
		const nameRegex = /^[가-힣]{2,4}$/;
		if (!nameValue) {
			// 중복된 메시지 설정 방지
			if (name_Error_message.text() !== "이름은 필수값입니다.") {
				name_Error_message.text("이름은 필수값입니다.").show();
			}
		} else if (!nameRegex.test(nameValue)) {
			name_Error_message.text("이름은 2자에서 4자 사이여야합니다").show();
		} else {
			name_Error_message.hide();
			name.val(nameValue);
		}
	});

	

});