const togglebtn = document.querySelector('.navbarBtn');
const nc_list = document.querySelector('.nc-list');
const nc_account = document.querySelector('.nc-account');

togglebtn.addEventListener("click",function (){
    nc_list.classList.toggle('active');
    nc_account.classList.toggle('active');
})

$(document).ready(function() {
	// 페이지 로딩 시 로딩 화면 표시
	$("#loading-screen").fadeIn();

	// 페이지 로드 후 로딩 화면 숨기기
	$(window).on("load", function() {
		$("#loading-screen").fadeOut();
	});

	// 링크 클릭 시 로딩 화면 표시
	$("a").on("click", function(e) {
		e.preventDefault(); // 기본 링크 동작 방지
		$("#loading-screen").fadeIn();

		// 링크 이동
		const url = $(this).attr("href");
		setTimeout(function() {
			window.location.href = url; // 지정된 URL로 이동
		}, 500); // 로딩 시간 설정
	});
});
