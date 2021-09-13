# PYTHON 으로 JSON 파일 읽고 쓰기



## open()

```python
open('파일 이름', '파일 열기 모드')
```

| 파일 열기 모드 | 요약      | 설명                         |
| -------------- | --------- | ---------------------------- |
| r              | 읽기 모드 | 파일을 읽을 때 사용          |
| w              | 쓰기 모드 | 파일에 내용을 작성할 때 사용 |
| a              | 추가 모드 | 파일에 내용을 추가할 때 사용 |

- 파이썬에서 기본적으로 사용하는 파일을 읽는 것이다.
- 주의할 점
  - 쓰기모드로 열 시에는해당 파일이 이미 존재할 경우 작성된 내용이 모두 사라지고 새로 작성된다.
    해당 파일이 존재하지 않을 경우에는 신규로 생성된다.
  - 파일 이름에 경로를 지정해줘야하며, 경로를 지정하지 않으면 `.py`파일이 실행되는 경로에 기본적으로 생성된다.



## JSON 으로 저장하기

```python
import json

# 파일 경로 설정
file_path = "./sample_test.json"

# 넣어줄 데이터
data = {}
data['posts'] = []
data['posts'].append({
    "title" : "i'm title",
    "overview" : "just do it!"
})

data['posts'].append({
    "title" : "i'm second title",
    "overview" : "just do it! try again!"
})

print(data)

# json 파일로 저장하기
with open(file_path, 'w') as outfile:
    json.dump(data, outfile, indent = 4)
```

- 설명

  - data 에는 json 의 형식에 맞도록 객체를 구성해서 넣어준다.

    

### json.dump()

> Python 객체를 JSON 파일에 저장한다.

- `json.dump( indent = n)`
  - indent를 주는 것으로 json 이 예쁘게 나온다.

- `json.dumps()`는 python 객체를 json 문자열로 반환하는 것이다.



## JSON 파일 읽기



### 기존 JSON 파일에 내용을 추가해서 다시 저장하기

> 과정은 별거 없다.
>
> 기존에 있는 JSON을 들고 와서 열어서 사용한다.

```python
# 기존에 있는 json 파일 불러오기
with open(file_path, "r") as json_file:
    json_data = json.load(json_file)

# 불러온 곳에 추가해서 넣어준다.
json_data['posts'].append({
    "title" : "my third title",
    "overview" : "just do it! we can"
})

# 다시 저장한다.
with open(file_path, 'w') as outfile:
    json.dump(json_data, outfile, indent=4)
```







## 출처

- https://kibua20.tistory.com/114 [모바일 SW 개발자가 운영하는 블로그]
- https://codechacha.com/ko/python-read-write-json-file/
- https://ossian.tistory.com/67