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