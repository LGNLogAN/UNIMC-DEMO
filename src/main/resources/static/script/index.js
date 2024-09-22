// Main Title Animation
let hc_main_title_observer = new IntersectionObserver((e)=>{
    e.forEach((i)=>{
        if(i.isIntersecting){
            i.target.style.opacity = '1';
        }else{
            // i.target.style.opacity = '0'; 
            // 관측이 안 되면 다시 0 으로 돌아감 ( 해당기능은 웹사이트를 난잡하게 해서 비활 ) 
        }
    });
});
let hc_main_title = document.getElementById('hc-main-title');
hc_main_title_observer.observe(hc_main_title);
// Main Title Animation

// Line Animation
let hc_main_line_observer = new IntersectionObserver((e)=>{
    e.forEach((i)=>{
        if(i.isIntersecting){
            i.target.style.width = '1300px';
        }else{
            // i.target.style.width = '0px';
            // 관측이 안 되면 다시 0 으로 돌아감 ( 해당기능은 웹사이트를 난잡하게 해서 비활 ) 
        }
    });
});
let hc_main_line = document.getElementById('hc-main-line');
hc_main_line_observer.observe(hc_main_line);
// Line Animation


// SubTitle Animation
const textTypingSpeed = 80;
let autoIncrease = 0;
let hcMainSubTitleText = "간단한 회원가입으로 접속하세요 !";
let hcMainSubTitle = document.getElementById('hc-main-subTitle');

let hcMainSubTitleObserver = new IntersectionObserver((e)=>{
    e.forEach(i => {
        if(i.isIntersecting){
            textTypingAnimation();
        }else{
            // mainSubtitle.textContent = '';
            // autoIncrease = 0;
            // 관측이 안 되면 다시 0 으로 돌아감 ( 해당기능은 웹사이트를 난잡하게 해서 비활 ) 
        }
    })
});

hcMainSubTitleObserver.observe(hcMainSubTitle);

function textTypingAnimation(){
    if(autoIncrease < hcMainSubTitleText.length){
        hcMainSubTitle.textContent += hcMainSubTitleText[autoIncrease];
        autoIncrease++;
        setTimeout(textTypingAnimation, textTypingSpeed);
    }
}
// SubTitle Animation


document.addEventListener("DOMContentLoaded", () => {
    // dotlottie-player 요소 선택
    const handshakeElement = document.querySelector(".rc-handshake dotlottie-player");

    const observerOptions = {
        root: null, // viewport 기준
        rootMargin: "0px",
        threshold: 0.5 // 요소가 50% 이상 보일 때 실행
    };

    const observer = new IntersectionObserver((entries) => {
        entries.forEach(entry => {
            if (entry.isIntersecting) {
                // 화면에 보일 때 애니메이션 재생
                handshakeElement.setAttribute('autoplay', '');
                handshakeElement.play();
            } else {
                // 화면에서 벗어나면 애니메이션 정지
                // handshakeElement.stop();
                // handshakeElement.removeAttribute('autoplay');
            }
        });
    }, observerOptions);

    // 관찰 시작
    observer.observe(handshakeElement);
});

document.addEventListener("DOMContentLoaded", () => {
    // mic_icon 내의 dotlottie-player 요소 선택
    const micIconElement = document.querySelector(".mic-icon dotlottie-player");

    const observerOptions = {
        root: null, // viewport 기준
        rootMargin: "0px",
        threshold: 0.5 // 요소가 50% 이상 보일 때 실행
    };

    const observer = new IntersectionObserver((entries) => {
        entries.forEach(entry => {
            if (entry.isIntersecting) {
                // 요소가 화면에 보일 때 애니메이션 재생
                micIconElement.setAttribute('autoplay', '');
                micIconElement.play();
            } else {
                // // 요소가 화면에서 벗어날 때 애니메이션 정지
                // micIconElement.stop();
                // micIconElement.removeAttribute('autoplay');
            }
        });
    }, observerOptions);

    // micIconElement에 옵저버 등록
    observer.observe(micIconElement);
});

document.addEventListener("DOMContentLoaded", () => {
    // mic_icon 내의 dotlottie-player 요소 선택
    const sendMailElement = document.querySelector(".send-mail dotlottie-player");

    const observerOptions = {
        root: null, // viewport 기준
        rootMargin: "0px",
        threshold: 0.5 // 요소가 50% 이상 보일 때 실행
    };

    const observer = new IntersectionObserver((entries) => {
        entries.forEach(entry => {
            if (entry.isIntersecting) {
                // 요소가 화면에 보일 때 애니메이션 재생
                sendMailElement.setAttribute('autoplay', '');
                sendMailElement.play();
            } else {
                // // 요소가 화면에서 벗어날 때 애니메이션 정지
                // micIconElement.stop();
                // micIconElement.removeAttribute('autoplay');
            }
        });
    }, observerOptions);

    // micIconElement에 옵저버 등록
    observer.observe(sendMailElement);
});


particlesJS("particles", {
    particles: {
        number: {
            value: 60,  // 입자 개수 설정
            density: {
                enable: true,
                value_area: 800
            }
        },
        color: {
            value: "#000000" // 입자 색상 설정
        },
        shape: {
            type: "circle", // 입자의 모양 설정 (circle, edge, triangle 등)
            stroke: {
                width: 0,
                color: "#000000"
            }
        },
        opacity: {
            value: 0.7, // 입자 투명도 설정 (0.1 - 매우 투명, 1 - 불투명)
            random: true,
            anim: {
                enable: false,
                speed: 1,
                opacity_min: 0.1,
                sync: false
            }
        },
        size: {
            value: 8,  // 입자의 기본 크기
            random: true,
            anim: {
                enable: false,
                speed: 40,
                size_min: 0.1,
                sync: false
            }
        },
        line_linked: {
            enable: true,
            distance: 150,
            color: "#000000",
            opacity: 0.4,
            width: 1
        },
        move: {
            enable: true,
            speed: 2,  // 입자 이동 속도 설정 (낮을수록 느림)
            direction: "none",
            random: false,
            straight: false,
            out_mode: "out",
            bounce: false,
            attract: {
                enable: false,
                rotateX: 600,
                rotateY: 1200
            }
        }
    },
    interactivity: {
        detect_on: "canvas",
        events: {
            onhover: {
                enable: true,
                mode: "repulse"
            },
            onclick: {
                enable: true,
                mode: "push"
            },
            resize: true
        },
        modes: {
            grab: {
                distance: 400,
                line_linked: {
                    opacity: 1
                }
            },
            bubble: {
                distance: 400,
                size: 40,
                duration: 2,
                opacity: 8,
                speed: 3
            },
            repulse: {
                distance: 200,
                duration: 0.4
            },
            push: {
                particles_nb: 4
            },
            remove: {
                particles_nb: 2
            }
        }
    },
    retina_detect: true
});