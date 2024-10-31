# 프로젝트 구성   
---
   
## 구성 요소   
> 전국 대학교 챗봇 제작이 목표이나, 프로토타입에 대해선 경북대학교 챗봇을 제작하도록 함.   
> [대학교 챗봇 사이트](https://dansoeun.github.io/AICOSS/)   
> **LLM**: 경북대 학교 정보 안내 LLM. (사용 모델:[beomi/Llama-3-Open-Ko-8B](https://huggingface.co/beomi/Llama-3-Open-Ko-8B?text=%EB%84%8C+%EB%A9%8D%EC%B2%AD%EC%9D%B4))   
> **경북대 챗봇**: 경북대 학사, 학과 정보 등 전반적인 정보 대한 챗봇 구현.   
> **로그인 기능**: 사용자 질의 응답 저장을 위한 로그인 기능 구현.   
> **Vector DB (Postgresql DB -> Chroma DB)**: 응답 속도를 향상시키기 위한 DB.   

   
## 프로젝트 방향성   
---
###  **창업**
```
- 경북대 학교 정보 안내로 제공
- 해당 서비스의 수요가 증가하면 다른 학교들도 추가하는 방향으로 구상
- 수요조사 결과 사용 의향: 97.6% (40/41)
- 일부 유료화 시 사용 의향: 26.8% (11/41)
- 지분 분담 혹은 아이템 판매 형식으로 아이디어 구상을 가짐.
- 대상 유저 : 경북대학교 학생(특히 컴퓨터학부생)
```
<details>
  <summary>
    <b>프로젝트 일정</b>
  </summary>
  
    1. Must Have 기능(M.H.)
    - 로그인, 회원가입
    - 챗봇과 대화하는 UI
  
    2. Should Have 기능(S.H.)
    - 답변 생성하는 기능
    - 사이트를 알려주는 기능

    3. Could Have 기능(S.H.)
    - 답변에 대한 만족도 확인
    - etc
  
  
</details>
    
- [마케팅 전략 수립](https://drive.google.com/file/d/1E-rJd5Q95UxkyQdFHfdx9jGoICQNmp-n/view?usp=sharing)
- [비즈니스 모델 안내](https://drive.google.com/file/d/1eWWlbrqP0N_yit9yIVZ54RBloyCxHIQr/view?usp=sharing)
- [PRD 문생
</details>

   
## 참고자료
---
- **LLM**:
- [llama-index 공식문서](https://www.llamaindex.ai/)
- [Zero-Shot Code Representation Learning via Prompt Tuning](https://arxiv.org/pdf/2404.08947)
- [LARGE LANGUAGE MODELS AS OPTIMIZERS](https://arxiv.org/pdf/2309.03409)
- [Large Language Models are Zero-Shot Reasoners](https://arxiv.org/pdf/2205.11916)
- [Searching for Best Practices in Retrieval-Augmented Generation](https://arxiv.org/pdf/2407.01219)
- [BM25S: Orders of magnitude faster lexical search via eager sparse scoring](https://arxiv.org/pdf/2407.03618)
- [EDA: Easy Data Augmentation Techniques for Boosting Performance on Text Classification Tasks](https://arxiv.org/pdf/1901.11196)
- **백엔드**: [스프링부트 공식문서](https://docs.spring.io/spring-framework/reference/index.html) [llama-rest api connect](https://www.bentoml.com/blog/serving-a-llamaindex-rag-app-as-rest-apis)
- **챗봇** : [프로그래밍 교육 기초 학습자를 위한 교육 콘텐츠생성 GPTs 챗봇 구성](https://www-dbpia-co-kr.libproxy.knu.ac.kr/journal/articleDetail?nodeId=NODE11858670)

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
- Colab 실행 코드를 Port Forwarding으로 GPU서버에 전송하는 방법법
- 
