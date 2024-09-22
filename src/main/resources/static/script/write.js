const category = document.getElementById('category');
const additionalFields = document.getElementById('additional-fields');

category.addEventListener('change', function () {
    // 초기화
    additionalFields.innerHTML = '';

    // 카테고리에 따라 다른 필드 추가
    if (this.value === 'announce') {
        // 이벤트 카테고리일 때
        additionalFields.innerHTML = `
                <div class="form-group">
                    <label for="announceTitle">제목</label>
                    <input type="text" id="announceTitle" name="announceTitle" placeholder='서버 점검 안내 | 패치 내역 등..' required>
                </div>
    
                <div class="form-group">
                    <label for="announceMeta">작성자 및 작성일자</label>
                    <input type="text" id="announceMeta" name="announceMeta" placeholder='작성일: YYYY년 MM월 DD일 | 작성자: 관리자이름' required>
                </div>
            `;
    } 
    else if (this.value === '') {} 
    else if (this.value === '') {}
});