document.addEventListener("DOMContentLoaded", function () {
    const preloader = document.querySelector('.preloader');
    const content = document.querySelector('.content');

    // 랜덤 로딩 시간 (300ms에서 1000ms 사이)
    const randomLoadingTime = Math.random() * 400 + 300; // 300ms(0.3초) ~ 700ms(0.7초)

    // 랜덤 시간 후에 프리로더를 페이드 아웃하고 콘텐츠를 페이드 인
    setTimeout(() => {
        preloader.classList.add('hidden');

        // 0.5초 후에 프리로더를 완전히 제거
        setTimeout(() => {
            preloader.style.display = 'none'; // 프리로더 숨김
            document.body.style.overflow = 'auto'; // 스크롤 복원
        }, 500); // transition duration과 동일하게 설정
    }, randomLoadingTime);
});
