document.addEventListener('DOMContentLoaded', function() {
    const questionForm = document.getElementById('questionForm');
    const questionInput = document.getElementById('questionInput');
    const chatArea = document.getElementById('chatArea');
    const initialMessage = document.getElementById('initialMessage');

    // 웹소켓 연결 설정
    const socket = new WebSocket('ws://localhost:8080/ws');

    socket.onopen = function(event) {
        console.log("WebSocket 연결 성공");
    };

    socket.onmessage = function(event) {
        const botResponseElement = document.createElement('div');
        botResponseElement.className = 'message bot-message';
        botResponseElement.innerHTML = `<p>${event.data}</p>`;
        chatArea.appendChild(botResponseElement);
        chatArea.scrollTop = chatArea.scrollHeight;
    };

    socket.onclose = function(event) {
        console.log("WebSocket 연결 종료");
    };

    socket.onerror = function(error) {
        console.error("WebSocket 오류:", error);
    };

    questionForm.addEventListener('submit', function(event) {
        event.preventDefault();
        if (initialMessage && chatArea.contains(initialMessage)) {
            chatArea.removeChild(initialMessage);
        }
        const userMessage = questionInput.value.trim();
        if (userMessage === '') {
            return;
        }

        const userMessageElement = document.createElement('div');
        userMessageElement.className = 'message user-message';
        userMessageElement.innerHTML = `<p>${userMessage}</p>`;
        chatArea.appendChild(userMessageElement);

        // 사용자 메시지를 웹소켓 서버로 전송
        socket.send(userMessage);

        questionInput.value = '';
    });
});

document.addEventListener('DOMContentLoaded', function() {
    const loginBtn = document.getElementById('loginBtn');
    const signupBtn = document.getElementById('signupBtn');
    const userMenu = document.getElementById('userMenu');
    const profileLink = document.getElementById('profileLink');
    const questionsLink = document.querySelector('nav ul li a[href="questions"]');

    let isLoggedIn = false;  // 예시로 기본값을 false로 설정

    if (profileLink) {
        profileLink.addEventListener('click', function(event) {
            if (!isLoggedIn) {
                event.preventDefault();
                window.location.href = 'login';
            }
        });
    }

    if (questionsLink) {
        questionsLink.addEventListener('click', function(event) {
            if (!isLoggedIn) {
                event.preventDefault();
                window.location.href = 'login';
            }
        });
    }
    if (isLoggedIn) {
        loginBtn.style.display = 'none';
        signupBtn.style.display = 'none';
        userMenu.style.display = 'block';
    } else {
        loginBtn.style.display = 'inline-block';
        signupBtn.style.display = 'inline-block';
        userMenu.style.display = 'none';
    }
    const logoutBtn = document.getElementById('logoutBtn');
    if (logoutBtn) {
        logoutBtn.addEventListener('click', function() {
            alert("로그아웃 되었습니다.");
            loginBtn.style.display = 'inline-block';
            signupBtn.style.display = 'inline-block';
            userMenu.style.display = 'none';
        });
    }
});

document.addEventListener('DOMContentLoaded', function() {
    const faqItems = document.querySelectorAll('.faq-item');

    faqItems.forEach(item => {
        item.querySelector('.faq-question').addEventListener('click', function() {
            const answer = item.querySelector('.faq-answer');
            answer.style.display = answer.style.display === 'block' ? 'none' : 'block';
        });
    });
});
