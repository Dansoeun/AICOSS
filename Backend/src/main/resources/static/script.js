document.addEventListener('DOMContentLoaded', function() {
    const questionForm = document.getElementById('questionForm');
    const questionInput = document.getElementById('questionInput');
    const chatArea = document.getElementById('chatArea');
    const initialMessage = document.getElementById('initialMessage');

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

        const botResponseElement = document.createElement('div');
        botResponseElement.className = 'message bot-message';
        botResponseElement.innerHTML = `<p>여기에 챗봇의 답변이 표시됩니다.</p>`;
        chatArea.appendChild(botResponseElement);

        chatArea.scrollTop = chatArea.scrollHeight;

        questionInput.value = '';
    });
});



document.addEventListener('DOMContentLoaded', function() {
    const loginBtn = document.getElementById('loginBtn');
    const signupBtn = document.getElementById('signupBtn');
    const userMenu = document.getElementById('userMenu');
    const profileLink = document.getElementById('profileLink');
    const questionsLink = document.querySelector('nav ul li a[href="questions.html"]');

    let isLoggedIn = false;  // 예시로 기본값을 false로 설정

    if (profileLink) {
        profileLink.addEventListener('click', function(event) {
            if (!isLoggedIn) {
                event.preventDefault();
                window.location.href = 'login.html';
            }
        });
    }

    if (questionsLink) {
        questionsLink.addEventListener('click', function(event) {
            if (!isLoggedIn) {
                event.preventDefault();
                window.location.href = 'login.html';
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