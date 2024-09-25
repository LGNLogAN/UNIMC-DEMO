const togglebtn = document.querySelector('.navbarBtn');
const nc_list = document.querySelector('.nc-list');
const nc_account = document.querySelector('.nc-account');

togglebtn.addEventListener("click",function (){
    nc_list.classList.toggle('active');
    nc_account.classList.toggle('active');
})