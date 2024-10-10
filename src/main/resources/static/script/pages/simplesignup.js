let isClick = false;
const phoneInt = document.querySelector(".phone-int");

// 처음 페이지가 로드 됐을 때 호버 이벤트를 추가한다. ( 올바른 제출 예시 )
phoneInt.addEventListener('mouseover', () => {
    phoneInt.style.backgroundImage = "url('/img/jinsiwoo-2.jpg')";
    phoneInt.style.transition = "background-image 0.3s ease-in-out";
});

phoneInt.addEventListener('mouseout', () => {
    phoneInt.style.backgroundImage = "url('/img/jinsiwoo_Everytime.jpg')";
});

// 버튼이 눌렸을 때
document.querySelector(".changeBtn").addEventListener('click' , (event) =>{
	// 페이지 로드 방지
	event.preventDefault();
	
	// 틀린 예시 보기
	if (!isClick){
		document.querySelector(".title").textContent = "에브리타임 사진 예시 2번";
		document.querySelector(".example-view").textContent = "1번 예시 보기";
		
		// 틀린 에브리타임 제출 사진 배경설정
		phoneInt.style.backgroundImage = "url('/img/jinsiwoo-2.jpg')";
		
		// 호버 이벤트
		phoneInt.addEventListener('mouseover', () => {
		    phoneInt.style.backgroundImage = "url('/img/jinsiwoo_Everytime.jpg')";
		});
		
		phoneInt.addEventListener('mouseout', () => {
		    phoneInt.style.backgroundImage = "url('/img/jinsiwoo-2.jpg')";
		    phoneInt.style.transition = "background-image 0.3s ease-in-out";
		});
		
		// 재사용을 위한 true 표시
		isClick = true;
		
	// 올바른 예시 보기
	}else if(isClick){
		document.querySelector(".title").textContent = "에브리타임 사진 예시 1번";
		document.querySelector(".example-view").textContent = "2번 예시 보기";
		
		// 올바른 에브리타임 제출 사진 배경설정
		phoneInt.style.backgroundImage = "url('/img/jinsiwoo_Everytime.jpg')";
		
		// 호버 이벤트
		phoneInt.addEventListener('mouseover', () => {
			phoneInt.style.backgroundImage = "url('/img/jinsiwoo-2.jpg')";
		    phoneInt.style.transition = "background-image 0.3s ease-in-out";
		});
		
		phoneInt.addEventListener('mouseout', () => {
		    phoneInt.style.backgroundImage = "url('/img/jinsiwoo_Everytime.jpg')";
		});
		// 재사용을 위한 false 표시
		isClick = false;
	}
});

function updateFileName(input) {
    const fileName = input.files[0] ? input.files[0].name : '선택된 파일 없음';
    document.getElementById('file-name').textContent = fileName;
}



