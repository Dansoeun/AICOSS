document.addEventListener('DOMContentLoaded', function() {
    const loginForm = document.querySelector('form');

    loginForm.addEventListener('submit', function(event) {
        event.preventDefault(); // 폼 제출 방지

        const email = document.getElementById('email').value;
        const password = document.getElementById('password').value;

        if (email && password) {
            // 서버로 로그인 요청 보내기
            fetch('/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                body: new URLSearchParams({
                    'email': email,
                    'password': password
                })
            })
                .then(response => {
                    if (response.ok) {
                        // 로그인 성공
                        alert('로그인 성공!');
                        window.location.href = "/";
                    } else {
                        // 로그인 실패
                        alert('로그인 실패. 다시 시도하세요.');
                    }
                })
                .catch(error => {
                    console.error('로그인 오류:', error);
                    alert('로그인 중 오류가 발생했습니다.');
                });
        } else {
            alert("모든 필드를 입력해주세요.");
        }
    });
});


// document.addEventListener('DOMContentLoaded', function() {
//     const loginTab = document.getElementById('loginTab');
//     const signupTab = document.getElementById('signupTab');
//     const loginForm = document.getElementById('loginForm');
//     const signupForm = document.getElementById('signupForm');
//
//     loginTab.addEventListener('click', function() {
//         loginForm.classList.add('active');
//         signupForm.classList.remove('active');
//         loginTab.classList.add('active');
//         signupTab.classList.remove('active');
//     });
//
//     signupTab.addEventListener('click', function() {
//         signupForm.classList.add('active');
//         loginForm.classList.remove('active');
//         signupTab.classList.add('active');
//         loginTab.classList.remove('active');
//     });
// });
//
// function login(event) {
//     event.preventDefault(); // 폼 제출 방지
//
//     const username = document.getElementById('username').value;
//     const email = document.getElementById('loginEmail').value;
//     const password = document.getElementById('loginPassword').value;
//
//     if (username && email && password) {
//         // 서버로 로그인 요청 보내기
//         fetch('/login', {
//             method: 'POST',
//             headers: {
//                 'Content-Type': 'application/x-www-form-urlencoded'
//             },
//             body: new URLSearchParams({
//                 'username': username,
//                 'email': email,
//                 'password': password
//             })
//         })
//             .then(response => {
//                 if (response.ok) {
//                     // 로그인 성공
//                     alert('로그인 성공!');
//                     window.location.href = "/";
//                 } else {
//                     // 로그인 실패
//                     alert('로그인 실패. 다시 시도하세요.');
//                 }
//             })
//             .catch(error => {
//                 console.error('로그인 오류:', error);
//                 alert('로그인 중 오류가 발생했습니다.');
//             });
//     } else {
//         alert("모든 필드를 입력해주세요.");
//     }
// }

//     if (nickname && email) {
//         // 로그인 상태를 true로 설정
//         sessionStorage.setItem("isLoggedIn", true);
//         sessionStorage.setItem("username", username);
//         sessionStorage.setItem("Email", email);
//
//         // 로그인 후 마이페이지로 이동
//         window.location.href = "mypage";
//     } else {
//         alert("이메일과 비밀번호를 입력해주세요.");
//     }
// }
