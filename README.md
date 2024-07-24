##프로젝트 구성
--- 
+ 계열사 정보 안내 llm , 날씨 챗봇 , 로그인 기능 구현 
+ 로그인 기능으로 기존 질의응답 셋 저장




##프로젝트 방향성
----
1. **개발용역**:
*한 계열사 정보를 크롤링해서 모은 다음 챗봇 형식으로 납품하는 것도 생각했습니다.
ex)와이즈넛 등 챗봇 전문 회사에 납품하는 방향성도 존재.
*한 사이트에 여러개의 챗봇이 들어갈 수 있게 경량화 기법(Tensor RT) 기술력을 고안하는 방향도 있습니다.
2. **창업**: 기존 개발품을 사업화. [비즈니스 모델 안내](https://drive.google.com/file/d/1BmPgCuYY-XsLpPHRYTZ7x-b6TMMGXyjG/view?usp=sharing)

###프로젝트 기술
---
1. LLM: llama-index + postgresSQL DB 연결하는 것을 완료했습니다.
* PEFT 데이터셋 hugging face에 업로드 [kakao QnA dataset](https://huggingface.co/datasets/Dansoeun/Kakao_fine_tun_dataset), [naver QnA dataset](https://huggingface.co/datasets/Dansoeun/Naver_fine_tun_dataset)
*  GPU 서버로 PEFT-Lora 진행중입니다.
*  기존 데이터셋은 적으나 점차 늘려서 학습하는 방식으로 진행할 예정입니다.
*  정확도를 최소점까지 끌어올린 후 경량화 초점으로 맞춰 갈지 논의해야할 사항입니다.
*  기술문서 및 논문은 참조중입니다. llama-index 공식문서를 활용을 주로 해왔습니다.
2. ChatBot: 크롤링 + Transformer 모델 구축으로 연결할 예정입니다.
* 크롤링 모델을 고안했지만 아직 세부 정보까지 끌어오진 못했습니다.


3. BE: flask-ai 모델 서빙 하는 것, 로그인 코드를 작성중입니다. 세션 로그인 방식으로 완성할 예정입니다. 
* 스프링부트를 이용하여 회원가입 및 로그인 기능 구현 완료했습니다. 참고 자료: [스프링부트 공식문서](https://docs.spring.io/spring-framework/reference/index.html)
* AWS와 Mysql을 이용해 홈페이지에서 로그인, 회원가입할 경우 DB에 정보들이 저장되는 것을 확인했습니다.
* 프론트엔드쪽 코드가 제대로 백엔드에 적용이 안되는 것을 확인하여 임시로 웹페이지를 만들어놨으며 수정할 예정입니다.


##Need | Support
-----
*데이터셋을 많이 구비해야하는데, 직접 만들기 보다 자동화할 수 있거나 구할 수 있는 곳이 있을지 문의드리고 싶습니다. 
