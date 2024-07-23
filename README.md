##프로젝트 구성
--- 
+ 계열사 정보 안내 llm , 날씨 챗봇 , 로그인 기능 구현 
+ 로그인 기능으로 기존 질의응답 셋 저장




##프로젝트 방향성
----
1. **개발용역**:
*한 계열사 정보를 크롤링해서 모은 다음 챗봇 형식으로 납품.
*와이즈넛 등 챗봇 전문 회사에 납품하는 방향성도 존재.
*한 사이트에 여러개의 챗봇이 들어갈 수 있게 경량화 기법(Tensor RT) 고안 
2. **창업**: 기존 개발품을 사업화. [비즈니스 모델 안내](https://drive.google.com/file/d/1BmPgCuYY-XsLpPHRYTZ7x-b6TMMGXyjG/view?usp=sharing)

###프로젝트 기술
---
1. LLM: llama-index + postgresSQL DB 완료.
* PEFT 데이터셋 hugging face에 업로드 [kakao QnA dataset](https://huggingface.co/datasets/Dansoeun/Kakao_fine_tun_dataset), [naver QnA dataset](https://huggingface.co/datasets/Dansoeun/Naver_fine_tun_dataset)
*  GPU 서버로 PEFT를 진행하려 했으나 사용에 에러가 있어 잠시 차질이 있음.
*  기존 데이터셋은 적으나 점차 늘려서 학습하는 방식으로 진행할 예정
*  정확도를 끌어올린 이후에는 경량화 초점으로 맞춰 갈지 논의해야할 사항.
4. ChatBot: 크롤링 + Transformer 모델 구축으로 연결할 예정
* 크롤링 모델을 고안했지만 아직 미숙함이 있음.
* 
