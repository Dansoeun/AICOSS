<h1 align = "center"> <p><b> 🤖프로젝트 구성 </b></p>
<h3 align = "center"> <p>경북대학교 전용 chatgpt</p>
  
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
- [PRD 문서 안내](https://jannet.notion.site/PRD-AIus-83c925effc534ef39530a4b5706a96cd?pvs=4)
- [MRD 문서 안내]()
- [figma 웹 디자인](https://www.figma.com/design/MaQShoLO1MJuDzTTMkHTSi/AIus_ChatBot_Design?node-id=0-1&t=pZ2HDL6bcLoMcU0i-1)

## 프로젝트 기술
---
**1. LLM**: [llama기반 ko-model](https://huggingface.co/beomi/Llama-3-Open-Ko-8B) PEFT-Lora 적용 한 모델과 Chroma DB 연결 완료
<details>
   <summary><b>progress</b></summary>
   <ul>
      <li>PEFT-Lora 데이터셋: Hugging Face에 업로드. <a href="https://huggingface.co/datasets/Dansoeun/Knu_fine_tun_dataset">[KNU dataset]</a></li>
      <li>추가 데이터셋 확보 예정 (<a href="https://github.com/catSirup/KorEDA/blob/master/eda.py">한국어 EDA 도움</a>)</li>
      <li>PEFT-Lora로 학습 시킨 모델과 Chroma DB 연결 완료</li>
      <li>Hybrid Search: BM25 algorithm으로 검색 능력 향상</li>
      <li>Hyde 기법 적용 예정</li>
      <li>few-shot + CoT prompt 적용 예정 (few-shot prompt는 제작 완료)</li>
      <li>필요시, Prompt Compression을 할 예정입니다.</li>
   </ul>
</details>
<b>2. ChatBot</b>: 크롤링 + 챗봇 엔진 모델 구축
<details>
   <summary>데이터셋 : AIHUB, KNU dataset</summary>
<a href="https://aihub.or.kr/aihubdata/data/view.do?currMenu=&topMenu=&aihubDataSe=data&dataSetSn=106">[AIHUB - 일반상식]<br>
<a href="https://aihub.or.kr/aihubdata/data/view.do?currMenu=&topMenu=&aihubDataSe=data&dataSetSn=544">[AIHUB - 용도별 목적 대화 데이터]<br>
<a href="https://aihub.or.kr/aihubdata/data/view.do?currMenu=&topMenu=&aihubDataSe=data&dataSetSn=543">[AIHUB - 주제별 텍스트 일상 데이터]<br>
</details>
<details>
   <summary><b>progress</b></summary>
  
<b>스크래핑(Chat-Bot Scrapping)</b>
- [x] 학사일정
- [x] 컴퓨터학부 교수진
- [ ] 경북대학교 공지사항
- [x] 컴퓨터학부 교육/커뮤니티/학생활동
- [x] 경북대학교 컴퓨터학부 공지사항
- [ ] 경북대학교 연락처
<b>챗봇</b> : Transformer 기반 모델을 구축하고 훈련하는 데 중점을 둠
- [x] 데이터 전처리
- [ ] 딥러닝 기반 모델 개발
</details>
   
**3. Back_End**: AI와 연결 시도중
<details>
   <summary><b>progress</b></summary>
- Flask_AI 연동을 시도하였으나, python과 ipynb의 연동 문제 발생.<br>
- Spring Boot를 이용하여 회원가입 및 로그인 기능 구현 완료.<br>   
- AWS와 Mysql을 이용하여 홈페이지에서 로그인, 회원가입할 경우 DB에 정보들이 저장되는 것을 확인.<br>   
- Front_End 쪽 코드가 제대로 백엔드에 적용되도록 수정 완료.<br> 
- API와 스프링부트에 Python스크립트 호출을 이용하여 챗봇과 연결 시도 중.<br>
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
    <img src="https://img.shields.io/badge/Medium-12100E?style=for-the-badge&logo=medium&logoColor=white">
    <img src="https://img.shields.io/badge/AWS-%23FF9900.svg?style=for-the-badge&logo=amazonwebservices&logoColor=white">
    <img src="https://img.shields.io/badge/TensorFlow-%23FF6F00.svg?style=for-the-badge&logo=TensorFlow&logoColor=white">
    <img src="https://img.shields.io/badge/Keras-%23D00000.svg?style=for-the-badge&logo=Keras&logoColor=white">
    <img src="https://img.shields.io/badge/Matplotlib-%23ffffff.svg?style=for-the-badge&logo=Matplotlib&logoColor=black">
    <img src="https://img.shields.io/badge/numpy-%23013243.svg?style=for-the-badge&logo=numpy&logoColor=white">
    <img src="https://img.shields.io/badge/pandas-%23150458.svg?style=for-the-badge&logo=pandas&logoColor=white">
    <img src="https://img.shields.io/badge/jupyter-%23FA0F00.svg?style=for-the-badge&logo=jupyter&logoColor=white">
    <img alt="Static Badge" src="https://img.shields.io/badge/Hugging%20Face-yellow.svg?style=for-the-badge&logo=huggingface&logoColor=white&color=%23FFD21E">
    <img alt="Static Badge" src="https://img.shields.io/badge/Lang%20Chain-1C3C3C.svg?style=for-the-badge&logo=langchain&logoColor=white">
    <img alt="Static Badge" src="https://img.shields.io/badge/llama3-000000.svg?style=for-the-badge&logo=ollama&logoColor=white">

  
</div>




## Need | Support
-----
- Colab 실행 코드를 Port Forwarding으로 GPU서버에 전송하는 방법
-
