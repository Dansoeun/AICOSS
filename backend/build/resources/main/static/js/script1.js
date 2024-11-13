document.addEventListener('DOMContentLoaded', function() {
    const loginBtn = document.getElementById('loginBtn');
    const signupBtn = document.getElementById('signupBtn');
    const userMenu = document.getElementById('userMenu');
    const profileLink = document.getElementById('profileLink');
    const questionsLink = document.querySelector('nav ul li a[href="questions"]');
    const questionForm = document.getElementById('questionForm');
    const questionInput = document.getElementById('questionInput');
    const responseMessage = document.getElementById('responseMessage');

    questionForm.addEventListener('submit', async function(event) {
        event.preventDefault();  // 폼 제출 방지

        const question = questionInput.value;
        if (question.trim() === "") return;  // 질문이 빈 경우

        // 질문을 서버에 전송
        try {
            const response = await fetch(`/getAnswer?question=${encodeURIComponent(question)}`);
            const answer = await response.text();

            // 답변을 화면에 표시
            responseMessage.innerHTML = `<p><strong>답변:</strong> ${answer}</p>`;
        } catch (error) {
            console.error("Error fetching answer:", error);
            responseMessage.innerHTML = `<p><strong>오류:</strong> 답변을 가져오는데 실패했습니다.</p>`;
        }

        // 입력란 비우기
        questionInput.value = "";
    });

    let isLoggedIn = true;  // 예시로 기본값을 false로 설정
    let currentPath = window.location.pathname;
    let newPath;

    if (isLoggedIn) {
        if (currentPath.endsWith('index.html')) {
            newPath = 'indexLogin.html';
        } else if (currentPath.endsWith('faq.html')) {
            newPath = 'faqLogin.html';
        } else if (currentPath.endsWith('about.html')) {
            newPath = 'aboutLogin.html';
        }
    } else {
        if (currentPath.endsWith('indexLogin.html')) {
            newPath = 'index.html';
        } else if (currentPath.endsWith('faqLogin.html')) {
            newPath = 'faq.html';
        } else if (currentPath.endsWith('aboutLogin.html')) {
            newPath = 'about.html';
        }
    }

    if (newPath && currentPath !== newPath) {
        window.location.href = newPath;
    }

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

