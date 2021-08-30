# Sub1 정리 Note



## analyze.py



### shutil

> 파일, 디렉토리에 대한 복사 이동 삭제 등에 관한 기능 제공



#### `get_terminal_size()`

```python
term_w =shutil.get_terminal_size()[0] - 1
```

- 터미널 창의 크기를 가져온다.



### pandas



#### `iterrows()`

```python
for i, store in stores_most_scored.iterrows():
```

- 순회하는 것
- 첫 번재에 idx 를 받는다.



