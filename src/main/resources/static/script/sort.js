const sortButton = document.getElementById('sort');
const dropdown = document.getElementById('dropdown');
const ascButton = document.getElementById('asc');
const descButton = document.getElementById('desc');
const recentButton = document.getElementById('recent')

sortButton.addEventListener('click', function (event) {
    event.stopPropagation();
    dropdown.style.display = dropdown.style.display === 'block' ? 'none' : 'block';
});

ascButton.addEventListener('click', function () {
    console.log('오름차순 정렬');
    dropdown.style.display = 'none';
});

descButton.addEventListener('click', function () {
    console.log('내림차순 정렬');
    dropdown.style.display = 'none';
});

recentButton.addEventListener('click', function () {
    console.log('최근순 정렬');
    dropdown.style.display = 'none';
})

// 페이지에 아무곳을 클릭했을 때 dropdown 을 안 보이게 하는 코드
document.addEventListener('click', function (event) {
    if (!sortButton.contains(event.target) && !dropdown.contains(event.target)) {
        dropdown.style.display = 'none';
    }
});
