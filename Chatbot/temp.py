import pandas as pd
import tensorflow as tf
from tensorflow.keras.preprocessing.text import Tokenizer
from tensorflow.keras.preprocessing.sequence import pad_sequences
from tensorflow.keras import preprocessing
from tensorflow.keras.models import Model
from tensorflow.keras.layers import Input, Embedding, Dense, Dropout, Conv1D, GlobalMaxPool1D, concatenate
from sklearn.preprocessing import LabelEncoder
from sklearn.model_selection import train_test_split

# 업로드된 파일의 경로
file_path = 'C:/Users/rlask/AICOSS/knu_dataset_csv.csv'

# 데이터셋 불러오기
df = pd.read_csv(file_path)

# 데이터 확인
#print(df.head())
#데이터 전처리
# Clear any previous sessions
tf.keras.backend.clear_session()

# 입력 텍스트와 레이블 추출
texts = df['Question']  # 실제 텍스트가 있는 열 이름으로 변경
labels = df['Answer']  # 실제 레이블이 있는 열 이름으로 변경

# 텍스트를 시퀀스로 변환
tokenizer = Tokenizer(num_words=10000)
tokenizer.fit_on_texts(texts)
sequences = tokenizer.texts_to_sequences(texts)

# 패딩
maxlen = 100
X = pad_sequences(sequences, maxlen=maxlen)

# 레이블 인코딩
encoder = LabelEncoder()
y = encoder.fit_transform(labels)

# 데이터셋을 훈련과 테스트로 나누기
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)


##모델 구축
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Embedding, LSTM, Dense, Dropout

# 모델 생성
model = Sequential()
model.add(Embedding(input_dim=10000, output_dim=128, input_length=maxlen))
model.add(LSTM(128, dropout=0.2, recurrent_dropout=0.2))
model.add(Dense(64, activation='relu'))
model.add(Dropout(0.5))
model.add(Dense(len(encoder.classes_), activation='softmax'))

# 모델 컴파일
model.compile(loss='sparse_categorical_crossentropy',
              optimizer='adam',
              metrics=['accuracy'])

# 모델 요약
model.summary()

# 모델 학습
history = model.fit(X_train, y_train,
                    epochs=5,
                    batch_size=32,
                    validation_data=(X_test, y_test))
# 새로운 문장 예측
def predict_intent(text):
    sequence = tokenizer.texts_to_sequences([text])
    padded_sequence = pad_sequences(sequence, maxlen=maxlen)
    prediction = model.predict(padded_sequence)
    predicted_class = encoder.inverse_transform([tf.argmax(prediction[0])])
    return predicted_class[0]

# 예시 예측
new_text = "컴학 사이트가 뭐야?."
print(predict_intent(new_text))