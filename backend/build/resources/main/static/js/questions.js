document.addEventListener('DOMContentLoaded', function() {
    const questionsList = document.getElementById('questionsList');
    const questionDetails = document.getElementById('questionDetails');

    // 예시 DATA
    const questions = [
        { date: '2024-08-01', question: '안녕', answer: '안녕하세요 저는 대학교 정보를 안내해주는 챗봇입니다 무엇을 도와드릴까요?.' },
        { date: '2024-07-15', question: '경북대학교는 어디에 있니?', answer: '경북대학교는 대구광역시 북구 대학로 80에 위치하고 있습니다.' }

    ];

    // 날짜별로 생성
    const dates = [...new Set(questions.map(item => item.date))];
    dates.forEach(date => {
        const dateButton = document.createElement('button');
        dateButton.className = 'date-button';
        dateButton.textContent = date;
        dateButton.dataset.date = date;
        questionsList.appendChild(dateButton);

        // 날짜 버튼 클릭 시 해당 날짜의 질문과 답변 표시
        dateButton.addEventListener('click', function(event) {
            const selectedDate = event.target.dataset.date;
            const filteredQuestions = questions.filter(item => item.date === selectedDate);
            questionDetails.innerHTML = '';

            filteredQuestions.forEach(item => {
                const questionDiv = document.createElement('div');
                questionDiv.className = 'question-item';
                questionDiv.innerHTML = `<p><strong>질문:</strong> ${item.question}</p><p><strong>답변:</strong> ${item.answer}</p>`;
                questionDetails.appendChild(questionDiv);
            });
        });
    });
});