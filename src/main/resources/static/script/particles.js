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