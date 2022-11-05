let cross = document.querySelector('.cross');
let navBar = document.querySelector('.navbar');
let menu = document.querySelector('.menu');

menu.addEventListener('click', ()=>{
    navBar.style.transform = 'translateX(0vw)';
})

cross.addEventListener('click', ()=>{
    navBar.style.transform = 'translateX(-20vw)';
    menu.style.display = 'block';
})
