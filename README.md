# 프로젝트 구성
--- 
## 구성 요소
---
- **LLM**: 각 계열사의 정보를 안내.
- **날씨 챗봇**: 날씨 정보에 대한 챗봇 구현.
- **로그인 기능**: 사용자 질의 응답 저장을 위한 로그인 기능 구현.

## 프로젝트 방향성
---
### 1. **개발용역**
- 관련 계열사 정보 크롤링하여 챗봇 형태로 제공하는 방안 검토 ex)WiseNut 등 챗봇 전문 회사와 협업하여 납품하는 방향성도 존재.
- 한 사이트에 여러 개의 챗봇이 들어갈 수 있는 경량화 기법(Tensor RT)을 고안.
### 2. **창업**
- 기존 개발품을 사업화. 
- [비즈니스 모델 안내](https://drive.google.com/file/d/1BmPgCuYY-XsLpPHRYTZ7x-b6TMMGXyjG/view?usp=sharing)

## 프로젝트 기술
---
**1. LLM**: llama-index와 PostgreSQL DB 연동 완료.
- PEFT 데이터셋: Hugging Face에 업로드. [kakao QnA dataset](https://huggingface.co/datasets/Dansoeun/Kakao_fine_tun_dataset), [naver QnA dataset](https://huggingface.co/datasets/Dansoeun/Naver_fine_tun_dataset)
- GPU 서버: PEFT-Lora 진행 중.
-  학습 데이터셋 점진적 확장 후 학습시키는 방식으로 진행 예정.
-  정확도를 최소점까지 끌어올린 후 경량화 초점으로 맞춰 갈지 논의해야할 사항(정확도 VS 경량화).

**2. ChatBot**: 크롤링 + Transformer 모델 구축으로 연결 예정.
- 크롤링 모델을 고안했지만 아직 세부 정보까지 끌어오진 못했습니다.

**3. Back_End**: Flask-AI를 통한 모델 서빙, 세션 기반 로그인 구현 예정 
- Flask_AI 연동을 시도하였으나, python과 ipynb의 연동 문제 발생.
- Spring Boot를 이용하여 회원가입 및 로그인 기능 구현 완료.
- AWS와 Mysql을 이용하여 홈페이지에서 로그인, 회원가입할 경우 DB에 정보들이 저장되는 것을 확인.
- Front_End 쪽 코드가 제대로 백엔드에 적용이 안되는 것을 확인하여 임시로 웹페이지를 제작하였으며, 추후에 수정할 예정.

## 참고자료
---
- **LLM**: llama-index 공식 문서 활용.
- **백엔드**: [스프링부트 공식문서](https://docs.spring.io/spring-framework/reference/index.html)

<div align="center">
    <h1>📚 STACKS</h1>
</div>
<div align="center">
    <img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white">
    <img src="https://img.shields.io/badge/python-3776AB?style=for-the-badge&logo=python&logoColor=white">
    <img src="https://img.shields.io/badge/html5-E34F26?style=for-the-badge&logo=html5&logoColor=white">
    <img src="https://img.shields.io/badge/css-1572B6?style=for-the-badge&logo=css3&logoColor=white">
    <img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black">
    <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white">
    <img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">
    <img src="https://img.shields.io/badge/flask-000000?style=for-the-badge&logo=flask&logoColor=white">
    <img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">
    <img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white">
</div>


## Need | Support
-----
- 데이터셋을 많이 구비해야하는데, 직접 만들기 보다 자동화할 수 있거나 구할 수 있는 곳이 있을지 문의드리고 싶습니다. 
- 현재 Flask-AI 연동을 해야하는데 flask에서 ipynb 접근 방식을 어떻게 처리해야 할지 문의드립니다.
