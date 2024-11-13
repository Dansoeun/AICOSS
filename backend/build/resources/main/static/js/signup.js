document.addEventListener('DOMContentLoaded', function () {
    const signupForm = document.getElementById('signupForm');

    signupForm.addEventListener('submit', function (event) {
        event.preventDefault(); // 폼 제출 방지

        const username = document.getElementById('username').value;
        const email = document.getElementById('email').value;
        const password = document.getElementById('password').value;
        const confirmPassword = document.getElementById('confirm-password').value;

        // 비밀번호와 비밀번호 확인이 일치하는지 확인
        if (password !== confirmPassword) {
            alert('비밀번호가 일치하지 않습니다. 다시 확인해 주세요.');
            return;
        }

        // 서버에 회원가입 요청 보내기
        fetch('/join', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                'username': username,
                'email': email,
                'password': password,
            }),
        })
            .then(response => response.text()) // 서버로부터 받은 응답을 텍스트로 처리
            .then(result => {
                // 서버 응답이 "회원가입성공"이면 별도 메시지 추가 후 이동
                if (result === "회원가입이 성공하였습니다.") {
                    alert('회원가입이 완료되었습니다!'); // 성공 메시지 표시
                    window.location.href = "/login"; // 로그인 페이지로 이동
                } else {
                    alert(result); // 중복 또는 기타 오류 메시지를 그대로 표시
                }
            })
            .catch(error => {
                console.error('회원가입 오류:', error);
                alert('회원가입 중 오류가 발생했습니다.'); // 네트워크 오류 처리
            });
    });
});
