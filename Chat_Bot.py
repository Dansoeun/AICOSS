#!/usr/bin/env python
# coding: utf-8

# <a href="https://colab.research.google.com/github/Dansoeun/AICOSS/blob/ChatBot/ChatBot.ipynb" target="_parent"><img src="https://colab.research.google.com/assets/colab-badge.svg" alt="Open In Colab"/></a>

# In[1]:


import os
import json
import pandas as pd


# In[2]:


def counting(path):
    cnt = 0
    data_dir = path

    for path in os.listdir(data_dir):
        if os.path.isfile(os.path.join(data_dir, path)):
            cnt += 1

    print(f'데이터 개수 = {cnt}')
    return cnt


# # 데이터셋 csv로 변환
# 
# 1.   용도별 목적 데이터
# 2.   주제별 텍스트 데이터
# 
# 

# In[ ]:


get_ipython().system('unzip "/raid/coss35/sehee/AICOSS/021.용도별 목적대화 데이터/01.데이터/1.Training/라벨링데이터/TL_1.shopping.zip"')
get_ipython().system('unzip "/raid/coss35/sehee/AICOSS/021.용도별 목적대화 데이터/01.데이터/1.Training/라벨링데이터/TL_2.civil complaint.zip"')
get_ipython().system('unzip "/raid/coss35/sehee/AICOSS/021.용도별 목적대화 데이터/01.데이터/1.Training/라벨링데이터/TL_3.education.zip"')
get_ipython().system('unzip "/raid/coss35/sehee/AICOSS/021.용도별 목적대화 데이터/01.데이터/1.Training/라벨링데이터/TL_4.tourism.zip"')

get_ipython().system('unzip "/raid/coss35/sehee/AICOSS/020.주제별 텍스트 일상 대화 데이터/01.데이터/1.Training/라벨링데이터/TL_01. KAKAO(1).zip"')
get_ipython().system('unzip "/raid/coss35/sehee/AICOSS/020.주제별 텍스트 일상 대화 데이터/01.데이터/1.Training/라벨링데이터/TL_01. KAKAO(2).zip"')
get_ipython().system('unzip "/raid/coss35/sehee/AICOSS/020.주제별 텍스트 일상 대화 데이터/01.데이터/1.Training/라벨링데이터/TL_01. KAKAO(3).zip"')
get_ipython().system('unzip "/raid/coss35/sehee/AICOSS/020.주제별 텍스트 일상 대화 데이터/01.데이터/1.Training/라벨링데이터/TL_01. KAKAO(4).zip"')
get_ipython().system('unzip "/raid/coss35/sehee/AICOSS/020.주제별 텍스트 일상 대화 데이터/01.데이터/1.Training/라벨링데이터/TL_02. FACEBOOK.zip"')
get_ipython().system('unzip "/raid/coss35/sehee/AICOSS/020.주제별 텍스트 일상 대화 데이터/01.데이터/1.Training/라벨링데이터/TL_03. INSTAGRAM.zip"')
get_ipython().system('unzip "/raid/coss35/sehee/AICOSS/020.주제별 텍스트 일상 대화 데이터/01.데이터/1.Training/라벨링데이터/TL_04. BAND.zip"')
get_ipython().system('unzip "/raid/coss35/sehee/AICOSS/020.주제별 텍스트 일상 대화 데이터/01.데이터/1.Training/라벨링데이터/TL_05. NATEON.zip"')


# In[4]:


target_AS = "/raid/coss35/sehee/01.AS문의"
target_dep = "/raid/coss35//sehee/01.부서안내"
target_lodg = "/raid/coss35/sehee/01.숙박"
target_program = "/raid/coss35/sehee/01.프로그램문의"
target_traffic = "/raid/coss35/sehee/02.교통"
target_regist = "/raid/coss35/sehee/02.등록문의"
target_proce = "/raid/coss35/sehee/02.절차문의"
target_product = "/raid/coss35/sehee/02.제품사용문의"
target_refund = "/raid/coss35/sehee/03.비용환불문의"
target_docu = "/raid/coss35/sehee/03.서류문의"
target_restaurant = "/raid/coss35/sehee/03.식당"
target_pay = "/raid/coss35/sehee/03.주문결제"
target_leisure = "/raid/coss35/sehee/04.레저"
target_complain = "/raid/coss35/sehee/04.민원신고"
target_deli = "/raid/coss35/sehee/04.배송"
target_schedule = "/raid/coss35/sehee/04.일정문의"
target_sight = "/raid/coss35/sehee/05.관광"
target_change = "/raid/coss35/sehee/05.환불반품교환"
target_event = "/raid/coss35/sehee/06.이벤트"
target_onoff = "/raid/coss35/sehee/07.온오프라인안내"


# In[5]:


target_paths = [
    target_AS,
    target_dep,
    target_lodg,
    target_program,
    target_traffic,
    target_regist,
    target_proce,
    target_product,
    target_refund,
    target_docu,
    target_restaurant,
    target_pay,
    target_leisure,
    target_complain,
    target_deli,
    target_schedule,
    target_sight,
    target_change,
    target_event,
    target_onoff
]


# In[6]:


def counting(path):
    path_list = os.listdir(path)
    return len(path_list)

# 전체 데이터 개수를 구하는 코드
total_data = 0
for target_path in target_paths:
    cnt = counting(target_path)
    total_data += cnt
    print(f"데이터 개수 = {cnt}")  # 각 경로의 데이터 개수 출력

print(f'총 데이터 개수 = {total_data}')  # 총 데이터 개수 출력


# In[7]:


purpose = []
for target_path in target_paths:
    try:
        files = os.listdir(target_path)  # 해당 경로의 파일 리스트를 가져옴
        for file in files:
            final_path = os.path.join(target_path, file)  # 경로와 파일명을 결합
            try:
                with open(final_path, encoding="UTF-8") as target_file:  # 파일을 염
                    # 파일 내용이 유효한 JSON인지 확인
                    try:
                        target_data = json.load(target_file)  # JSON 파일 로드
                    except json.JSONDecodeError:
                        print(f"Invalid JSON format in file: {final_path}")
                        continue  # 파일을 건너뛰기

                    # annotations 안의 lines의 각 항목의 norm_text에서 값을 추출하여 purpose에 추가
                    for annotation in target_data['info'][0]['annotations']['lines']:
                        purpose.append(annotation['norm_text'][2:])  # norm_text의 앞 2글자를 제외하고 저장
            except Exception as e:
                print(f"Error processing file: {final_path} - {e}")
    except Exception as e:
        print(f"Error accessing path: {target_path} - {e}")


# In[8]:


len(purpose)


# In[9]:


purpose_df = pd.DataFrame({'text':purpose})


# In[10]:


purpose_df.head()


# In[11]:


purpose_df.tail()


# In[13]:


purpose_df.to_csv("/raid/coss35/sehee/AIHUB대화데이터.csv", index=False)


# # 일반상식 데이터 csv로 변환

# In[ ]:


get_ipython().system('unzip "/raid/coss35/sehee/AICOSS/02_squad_질문_답변_제시문_말뭉치.zip"')


# In[16]:


common_sense = open(f"/raid/coss35/sehee/ko_wiki_v1_squad.json", encoding="UTF-8")
common_sense = json.loads(common_sense.read())


# In[17]:


len(common_sense['data'])


# In[18]:


common_sense['data'][0]


# In[19]:


common_sense['data'][0]['paragraphs'][0]['qas'][0]


# In[20]:


query = []
answer = []
for i in range(len(common_sense['data'])):
    query.append(common_sense['data'][i]['paragraphs'][0]['qas'][0]['question'])
    answer.append(common_sense['data'][i]['paragraphs'][0]['qas'][0]['answers'][0]['text'])


# In[21]:


len(query)


# In[22]:


len(answer)


# In[23]:


common_sense_df = pd.DataFrame({'intent':['일반상식']*len(query), 'query':query, 'answer':answer})


# In[24]:


common_sense_df.head()


# In[25]:


common_sense_df.tail()


# In[27]:


common_sense_df.to_csv("/raid/coss35/sehee/일반상식.csv", index=False)


# # Tokenizer

# In[28]:


get_ipython().system('pip install konlpy')


# In[29]:


from konlpy.tag import Komoran
komoran = Komoran()


# In[30]:


text = "자연어 처리 공부는 흥미롭다."


# In[31]:


morphs = komoran.morphs(text)
print(morphs)


# In[32]:


pos = komoran.pos(text)
print(pos)


# In[33]:


nouns = komoran.nouns(text)
print(nouns)


# In[34]:


from konlpy.tag import Kkma
kkma = Kkma()
text = "자연어 처리 공부는 흥미롭다."


# In[35]:


morphs = kkma.morphs(text)
print(morphs)

pos = kkma.pos(text)
print(pos)

nouns = kkma.nouns(text)
print(nouns)


# In[36]:


sentences = "올해는 진짜 춥겠지? 롱패딩 사야지."
s = kkma.sentences(sentences)
print(s)


# In[37]:


from konlpy.tag import Okt
okt = Okt()
text = "자연어 처리 공부는 흥미롭다."

morphs = okt.morphs(text)
print(morphs)

pos = okt.pos(text)
print(pos)

nouns = okt.nouns(text)
print(nouns)


# In[38]:


text = "자연어 처리 재밌엌ㅋㅋ"
print(okt.normalize(text))
print(okt.phrases(text))


# # 텍스트 전처리

# In[39]:


from konlpy.tag import Komoran

class Preprocess:
  def __init__(self, userdic=None): # userdic 인자에는 사용자 정의 사전 파일 경로 입력 가능
    # 형태소 분석기 초기화
    self.komoran = Komoran(userdic=userdic)

    # 제외할 품사
    self.exclusion_tags = [
      'JKS', 'JKC', 'JKG', 'JKO', 'JKB', 'JKV', 'JKQ',
      # 주격조사, 보격조사, 관형격조사, 목적격조사, 부사격조사, 호격조사, 인용격조사,
      'JX', 'JC'
      # 보조사, 접속조사
      'SF', 'SP', 'SS', 'SE', 'SO',
      # 마침표, 물음표, 느낌표(SF), 쉼표, 가운뎃점, 콜론, 빗금(SP), 따옴표, 괄호표, 줄표(SS), 줄임표(SE), 붙임표(물결, 숨김, 빠짐)(SO)
      'EP', 'EF', 'EC', 'ETN', 'ETM'
      # 선어말어미, 종결어미, 연결어미, 명사형전성어미, 관형형전성어미
      'XSN', 'XSV', 'XSA'
      # 명사파생접미사, 동사파생접미사, 형용사파생접미사
  ]

  # Komoran POS tagging
  def pos(self, sentence) :
    return self.komoran.pos(sentence)

  # 불용어 제거
  def get_keywords(self, pos, without_tag=False) :
    f = lambda x: x in self.exclusion_tags
    word_list = []
    for p in pos :
      if f(p[1]) is False :
        word_list.append(p if without_tag is False else p[0])
    return word_list
  
  # 키워드를 단어 인덱스 시퀀스로 변환
  def get_wordidx_sequence(self, keywords):
      if self.word_index is None:
          return []
      w2i = []
      for word in keywords:
          try:
              w2i.append(self.word_index[word])
          except KeyError:
              # 해당 단어가 사전에 없는 경우 OOV 처리
              w2i.append(self.word_index['OOV'])
      return w2i
    


# In[2]:


from konlpy.tag import Komoran
import pandas as pd
import tensorflow as tf
from tensorflow.keras import preprocessing
import pickle

class Preprocess_bin:
    def __init__(self, word2index_dic=None ,userdic=None): # userdic 인자에는 사용자 정의 사전 파일 경로 입력가능
        # 단어 인덱스 사전 불러오기 추가
        if(word2index_dic != ''):
            f = open(word2index_dic, "rb")
            self.word_index = pickle.load(f)
            f.close()
            print("단어 사전 로드 완료..")
        else:
            self.word_index = None
            print("단어 사전 로드 실패..")

        # 형태소 분석기 초기화
        self.komoran = Komoran(userdic=userdic)

        # 제외할 품사
        # 관계언 제거, 기호 제거
        # 어미 제거
        # 접미사 제거
        self.exclusion_tags = [
            'JKS', 'JKC', 'JKG', 'JKO', 'JKB', 'JKV', 'JKQ',
            # 주격조사, 보격조사, 관형격조사, 목적격조사, 부사격조사, 호격조사, 인용격조사
            'JX', 'JC',
            # 보조사, 접속조사
            'SF', 'SP', 'SS', 'SE', 'SO',
            # 마침표,물음표,느낌표(SF), 쉼표,가운뎃점,콜론,빗금(SP), 따옴표,괄호표,줄표(SS), 줄임표(SE), 붙임표(물결,숨김,빠짐)(SO)
            'EP', 'EF', 'EC', 'ETN', 'ETM',
            # 선어말어미, 종결어미, 연결어미, 명사형전성어미, 관형형전성어미
            'XSN', 'XSV', 'XSA'
            # 명사파생접미사, 동사파생접미사, 형용사파생접미사
        ]


    # 형태소 분석기 POS 태거
    def pos(self, sentence):
        return self.komoran.pos(sentence)

    # 불용어 제거 후 필요한 품사 정보만 가져오기
    def get_keywords(self, pos, without_tag=False):
        f = lambda x: x in self.exclusion_tags
        word_list = []
        for p in pos:
            if f(p[1]) is False:
                word_list.append(p if without_tag is False else p[0])
        return word_list

    # 키워드를 단어 인덱스 시퀀스로 변환
    def get_wordidx_sequence(self, keywords):
        if self.word_index is None:
            return []
        w2i = []
        for word in keywords:
            try:
                w2i.append(self.word_index[word])
            except KeyError:
                # 해당 단어가 사전에 없는 경우 OOV 처리
                w2i.append(self.word_index['OOV'])
        return w2i


# In[41]:


sent = "컴공 과사 번호를 알려줘?...!"

# 전처리 객체 생성
p = Preprocess(userdic = '/raid/coss35/sehee/AICOSS/knu_dataset_csv.csv')

# 형태소 분석기 실행
pos = p.pos(sent)

# 품사 태그와 같이 키워드 출력
ret = p.get_keywords(pos, without_tag=False)
print(ret)

# 품사 태그 없이 키워드 출력
ret = p.get_keywords(pos, without_tag=True)
print(ret)


# # 단어 사전 구축

# In[42]:


#from tensorflow.keras.preprocessing import text
from tensorflow.keras.preprocessing.text import Tokenizer
import pickle
import pandas as pd

# 말뭉치 데이터 읽어오기
topic = pd.read_csv('/raid/coss35/sehee/AIHUB대화데이터.csv')
common_sense = pd.read_csv('/raid/coss35/sehee/일반상식.csv')

topic.dropna(inplace=True)
common_sense.dropna(inplace=True)

text1 = list(topic['text'])
text2 = list(common_sense['query']) + list(common_sense['answer'])

corpus_data = text1 + text2

# 말뭉치 데이터에서 키워드만 추출해서 사전 리스트 생성
p = Preprocess()
dict = []
for c in corpus_data:
  pos = p.pos(c)
  for k in pos:
    dict.append(k[0])

# 사전에 사용될 word2index 생성
# 사전의 첫 번째 인덱스에는 OOV 사용
#tokenizer = text.Tokenizer(oov_token='OOV', num_words=100000)
tokenizer = Tokenizer(oov_token='OOV', num_words=100000)
tokenizer.fit_on_texts(dict)
word_index = tokenizer.word_index
print(len(word_index))

# 사전 파일 생성
f = open("chatbot_dict.bin", "wb")
try:
  pickle.dump(word_index, f)
except Exception as e:
  print(e)
finally:
  f.close()


# In[43]:


import pickle

# 단어 사전 불러오기
f = open("/raid/coss35/sehee/chatbot_dict.bin", "rb")
word_index = pickle.load(f)
f.close()

sent = "안녕하세요. 테스트 코드입니다. 단어사전 만들기 성공!"

# 전처리 객체 생성
p = Preprocess(userdic="/content/user_dic.tsv")

# 형태소 분석기 실행
pos = p.pos(sent)

# 품사 태그 없이 키워드 출력
keywords = p.get_keywords(pos, without_tag=True)
for word in keywords:
  try:
    print(word, word_index[word])
  except KeyError:
    # 해당 단어가 사전에 없는 경우 OOV 처리
    print(word, word_index['OOV'])


# # 의도 분류 모델 학습 데이터 생성하기

# In[22]:


import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import seaborn as sns
from konlpy.tag import Komoran
import matplotlib.pyplot as plt


# In[45]:


topic = pd.read_csv("/raid/coss35/sehee/AIHUB대화데이터.csv")
common_sense = pd.read_csv("/raid/coss35/sehee/일반상식.csv")
knu_data=pd.read_csv("/raid/coss35/sehee/AICOSS/knu_dataset_csv.csv")

topic.dropna(inplace=True)
common_sense.dropna(inplace=True)
knu_data.dropna(inplace=True)


# In[46]:


print(f"topic shape => {topic.shape}\n\
common_sence shape => {common_sense.shape}\n\
knu_data shape=> {knu_data.shape}")


# In[47]:


topic.columns


# In[48]:


common_sense.columns


# In[49]:


knu_data.columns


# In[50]:


all_data = list(topic['text']) + list(common_sense['query']) + list(common_sense['answer'])+list(knu_data['Question'])+list(knu_data['Answer'])


# In[51]:


len(all_data)


# In[52]:


# 통합본 생성하고 저장하기
total = pd.DataFrame({'text': all_data})
total.to_csv("/raid/coss35/sehee/통합본데이터.csv", index=False)


# In[53]:


number = []
place = []
time = []
etc = []


# In[54]:


for i in all_data:
    if ('어디' or '장소' or '위치' or '주소') in i: place.append(i)
    elif ('번호' or '전화') in i: number.append(i)
    elif ('시작' or '마감' or '언제' or '기간' or '시간') in i: time.append(i)
    else: etc.append(i)


# In[55]:


len(number)


# In[56]:


number_label = []
for _ in range(len(number)):
    number_label.append(0)
len(number_label)


# In[57]:


len(place)


# In[58]:


place_label = []
for _ in range(len(place)):
    place_label.append(1)
len(place_label)


# In[59]:


len(time)


# In[60]:


time_label = []
for _ in range(len(time)):
    time_label.append(2)
len(time_label)


# In[ ]:


#import random
#random.seed(42)
#etc_sample = random.sample(etc, 20000)


# In[ ]:


#etc_sample_label = []
#for _ in range(len(etc_sample)):
#    etc_sample_label.append(3)
#len(etc_sample_label)


# In[61]:


train_df = pd.DataFrame({'text':number+place+time,
                         'label':number_label+place_label+time_label})


# In[62]:


train_df.head()


# In[63]:


train_df.tail()


# In[64]:


train_df[train_df['label']==0]


# In[65]:


train_df[train_df['label']==1]


# In[66]:


train_df[train_df['label']==2]


# In[ ]:


#train_df[train_df['label']==3]


# In[67]:


train_df.reset_index(drop=True, inplace=True)


# In[68]:


train_df.tail()


# In[69]:


train_df.to_csv("train_data.csv", index=False)


# In[70]:


data = pd.read_csv('train_data.csv')


# In[71]:


data.shape


# In[72]:


tokenizer = Komoran()


# In[73]:


data_tokenized = [[token+"/"+POS for token, POS in tokenizer.pos(text_)] for text_ in data['text']]

exclusion_tags = [
    'JKS', 'JKC', 'JKG', 'JKO', 'JKB', 'JKV', 'JKQ',
            'JX', 'JC',
            'SF', 'SP', 'SS', 'SE', 'SO',
            'EP', 'EF', 'EC', 'ETN', 'ETM',
            'XSN', 'XSV', 'XSA'
]

f = lambda x: x in exclusion_tags

data_list = []
for i in range(len(data_tokenized)):
        temp = []
        for j in range(len(data_tokenized[i])):
            if f(data_tokenized[i][j].split('/')[1]) is False:
                temp.append(data_tokenized[i][j].split('/')[0])
        data_list.append(temp)


# In[74]:


num_tokens = [len(tokens) for tokens in data_list]
num_tokens = np.array(num_tokens)

# 평균값, 최댓값, 표준편차
print(f"토큰 길이 평균: {np.mean(num_tokens)}")
print(f"토큰 길이 최대: {np.max(num_tokens)}")
print(f"토큰 길이 표준편차: {np.std(num_tokens)}")


# In[75]:


plt.title('all text length')
plt.hist(num_tokens, bins=100)
plt.xlabel('length of samples')
plt.ylabel('number of samples')
plt.show()


# In[76]:


select_length = 25

def below_threshold_len(max_len, nested_list):
    cnt = 0
    for s in nested_list:
        if(len(s) <= max_len):
            cnt = cnt + 1

    print('전체 샘플 중 길이가 %s 이하인 샘플의 비율: %s'%(max_len, (cnt / len(nested_list))))

below_threshold_len(select_length, data_list)


# In[77]:


# 단어 시퀀스 벡터 크기
MAX_SEQ_LEN = 25

def Globalparams():
  global MAX_SEQ_LEN


# ### CNN 구현

# In[ ]:


from collections.abc import Sequence
import pandas as pd
import tensorflow as tf
from tensorflow.keras import preprocessing
from tensorflow.keras.models import Model
from tensorflow.keras.layers import Input, Embedding, Dense, Dropout, Conv1D, GlobalMaxPool1D, concatenate
from konlpy.tag import Komoran

# Load Data
#data = pd.read_csv("train_data.csv")
data = pd.read_csv("/raid/coss35/sehee/train_data.csv")
text = data['text'].tolist()
label = data['label'].tolist()

# Load preprocessor (word2index_dic 제거)
#p = Preprocess(userdic="/raid/coss35/sehee/train_data.csv")
p=Preprocess_bin(word2index_dic='/raid/coss35/sehee/chatbot_dict.bin')


# In[4]:


# Data preprocess
sequences = []
for sentence in text:
    pos = p.pos(sentence)
    keywords = p.get_keywords(pos, without_tag=True)
    seq = p.get_wordidx_sequence(keywords)
    sequences.append(seq)


# In[5]:


# 추가로 메모리 해제 후 처리
import gc
gc.collect()


# In[6]:


# 단어 시퀀스 벡터 크기
MAX_SEQ_LEN = 15


def GlobalParams():
    global MAX_SEQ_LEN


# In[7]:


# Set padding length & pad to sequences
padded_seqs = preprocessing.sequence.pad_sequences(sequences, maxlen=MAX_SEQ_LEN, padding='post')

# Data to tensor
ds = tf.data.Dataset.from_tensor_slices((padded_seqs, label))
ds = ds.shuffle(len(text))

# Set train, validation, and test size
train_size = int(len(padded_seqs) * 0.7)
val_size = int(len(padded_seqs) * 0.2)
test_size = int(len(padded_seqs) * 0.1)

train_ds = ds.take(train_size).batch(100)
val_ds = ds.skip(train_size).take(val_size).batch(100)
test_ds = ds.skip(train_size + val_size).take(test_size).batch(100)

# Hyperparameters
dropout_prob = 0.5
EMB_SIZE = 128
EPOCH = 3
VOCAB_SIZE = len(p.word_index) + 1

# CNN model definition
input_layer = Input(shape=(MAX_SEQ_LEN, ))
embedding_layer = Embedding(VOCAB_SIZE, EMB_SIZE, input_length=MAX_SEQ_LEN)(input_layer)
dropout_emb = Dropout(rate=dropout_prob)(embedding_layer)

conv1 = Conv1D(filters=128, kernel_size=3, padding='valid', activation=tf.nn.relu)(dropout_emb)
pool1 = GlobalMaxPool1D()(conv1)

conv2 = Conv1D(filters=128, kernel_size=4, padding='valid', activation=tf.nn.relu)(dropout_emb)
pool2 = GlobalMaxPool1D()(conv2)

conv3 = Conv1D(filters=128, kernel_size=5, padding='valid', activation=tf.nn.relu)(dropout_emb)
pool3 = GlobalMaxPool1D()(conv3)

concat = concatenate([pool1, pool2, pool3])

hidden = Dense(128, activation=tf.nn.relu)(concat)
dropout_hidden = Dropout(rate=dropout_prob)(hidden)
logits = Dense(3, name='logits')(dropout_hidden)
predictions = Dense(3, activation=tf.nn.softmax)(logits)

# CNN model create
model = Model(inputs=input_layer, outputs=predictions)
model.compile(optimizer='adam', loss='sparse_categorical_crossentropy', metrics=['accuracy'])

# Train model
model.fit(train_ds, validation_data=val_ds, epochs=EPOCH, verbose=1)

# Evaluate model
loss, accuracy = model.evaluate(test_ds, verbose=1)
print("Accuracy: %f" % (accuracy * 100))
print("Loss: %f" % (loss))

# Save model
model.save('intent_model.h5')


# ## Model Load and Test
# ---

# In[8]:


import tensorflow as tf
from tensorflow.keras.models import Model, load_model
from tensorflow.keras import preprocessing
import gc

class IntentModel:
    def __init__(self, model_name, preprocess):

        # 의도 클래스별 레이블블
        self.labels = {0: "번호", 1: "장소", 2: "시간"}

        # 의도 분류 모델 불러오기
        self.model = load_model(model_name)

        # 챗봇 텍스트 전처리기
        self.p = preprocess

    # 의도 클래스 예측
    def predict_class(self, query):
        # 형태소 분석
        pos = self.p.pos(query)

        # 문장내 키워드 추출(불용어 제거)
        keywords = self.p.get_keywords(pos, without_tag=True)
        sequences = [self.p.get_wordidx_sequence(keywords)]

        # 단어 시퀀스 벡터 크기 : MAX_SEQ_LEN

        # 패딩처리
        padded_seqs = preprocessing.sequence.pad_sequences(sequences, maxlen=MAX_SEQ_LEN, padding='post')

        predict = self.model.predict(padded_seqs)
        predict_class = tf.math.argmax(predict, axis=1)
        return predict_class.numpy()[0]


# In[9]:


from tensorflow import keras

p=Preprocess_bin(word2index_dic='/raid/coss35/sehee/chatbot_dict.bin')

#model = keras.models.load_model('/raid/coss35/sehee/intent_model.h5')
intent=IntentModel(model_name='/raid/coss35/sehee/intent_model.h5',preprocess=p)


# In[10]:


query="우리 학교 은행위치 알려줘"
predict=intent.predict_class(query)
preict_label=intent.labels[predict]
print("="*30)
print(query)
print("의도 예측 클래스 : ",predict)
print("의도 예측 레이블 : ",preict_label)


# In[12]:


from tensorflow import keras

p=Preprocess_bin(word2index_dic='/raid/coss35/sehee/chatbot_dict.bin')

#model = keras.models.load_model('/raid/coss35/sehee/intent_model.h5')
intent=IntentModel(model_name='/raid/coss35/sehee/intent_model.h5',preprocess=p)

query = "경북대 홍보단 알려줘"
predict = intent.predict_class(query)
predict_label = intent.labels[predict]
print("="*30)
print(query)
print("의도 예측 클래스 : ", predict)
print("의도 예측 레이블 : ", predict_label)

query = "연구산학처 사이트 알려줘"
predict = intent.predict_class(query)
predict_label = intent.labels[predict]
print("="*30)
print(query)
print("의도 예측 클래스 : ", predict)
print("의도 예측 레이블 : ", predict_label)

query = "도서관 스터디룸 예약하고 싶어"
predict = intent.predict_class(query)
predict_label = intent.labels[predict]
print("="*30)
print(query)
print("의도 예측 클래스 : ", predict)
print("의도 예측 레이블 : ", predict_label)

query = "신문방송사에 대해 알려줘"
predict = intent.predict_class(query)
predict_label = intent.labels[predict]
print("="*30)
print(query)
print("의도 예측 클래스 : ", predict)
print("의도 예측 레이블 : ", predict_label)

query = "학사일정에 대해 알려줘"
predict = intent.predict_class(query)
predict_label = intent.labels[predict]
print("="*30)
print(query)
print("의도 예측 클래스 : ", predict)
print("의도 예측 레이블 : ", predict_label)

query = "나노신소재공학과에 대해 알려줘"
predict = intent.predict_class(query)
predict_label = intent.labels[predict]
print("="*30)
print(query)
print("의도 예측 클래스 : ", predict)
print("의도 예측 레이블 : ", predict_label)
print("="*30)


# In[17]:


get_ipython().system('pip install openpyxl')


# In[25]:


from tqdm import tqdm
tqdm.pandas()

import torch
from sentence_transformers import SentenceTransformer

train_file = "/raid/coss35/sehee/AICOSS/knu_dataset_csv.csv"
model = SentenceTransformer('snunlp/KR-SBERT-V40K-klueNLI-augSTS')

df = pd.read_csv(train_file)
df['embedding_vector'] = df['Question'].progress_map(lambda x : model.encode(x))
df.to_excel("train_data_embedding.xlsx", index=False)

embedding_data = torch.tensor(df['embedding_vector'].tolist())
torch.save(embedding_data, 'embedding_data.pt')


# In[42]:


from sentence_transformers import SentenceTransformer, util

model = SentenceTransformer('snunlp/KR-SBERT-V40K-klueNLI-augSTS')
embedding_data = torch.load('/raid/coss35/sehee/embedding_data.pt')
df = pd.read_csv('/raid/coss35/sehee/AICOSS/knu_dataset_csv.csv')

sentence = "상징 알려줘"
print("질문 문장 : ",sentence)
sentence = sentence.replace(" ", "")
print("공백 제거 문장 : ", sentence)

sentence_encode = model.encode(sentence)
sentence_tensor = torch.tensor(sentence_encode)

cos_sim = util.cos_sim(sentence_tensor, embedding_data)
print(f"가장 높은 코사인 유사도 idx : {int(np.argmax(cos_sim))}")

best_sim_idx = int(np.argmax(cos_sim))
selected_qes = df['Question'][best_sim_idx]
print(f"선택된 질문 = {selected_qes}")

selected_qes_encode = model.encode(selected_qes)

score = np.dot(sentence_tensor, selected_qes_encode) / (np.linalg.norm(sentence_tensor) * np.linalg.norm(selected_qes_encode))
print(f"선택된 질문과의 유사도 = {score}")

answer = df['Answer'][best_sim_idx]
print(f"\n답변 : {answer}\n")

