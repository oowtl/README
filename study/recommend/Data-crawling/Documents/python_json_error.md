# Error



- UnicodeDecodeError: 'cp949' codec can't decode byte 0xeb in position 12: illegal multibyte sequence

  - python3 부터 ANSI 로 인코딩 되는 것을 읽어온다. 따로 설정을 해줘야한다.

  - ```python
    with open(file_path, "r", encoding='UTF-8') as json_file:
        book_data = json.load(json_file)
        print(book_data[0])
    ```







- pymysql.err.OperationalError: (1136, "Column count doesn't match value count at row 1")
  - 넣을 수 있는 최대크기 오버
  - 참조
    - https://reference-m1.tistory.com/293



- pymysql.err.DataError: (1265, "Data truncated for column 'price' at row 1")
  - 예상으로는 int 들어와야하는데 string 와서 그런 것 같다.



