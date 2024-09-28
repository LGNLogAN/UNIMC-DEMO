$(function() {
	// 기본 폼 제출 방지
	$('form').on('keydown', function(event) {
		if (event.key === 'Enter') {
			event.preventDefault();
		}
	});
});





