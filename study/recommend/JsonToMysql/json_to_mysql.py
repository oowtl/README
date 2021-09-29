import pymysql
from pymysql import cursors
import json

# 데이터 경로
file_path = "./book_data_2t.json"

# db 접속
mydb = pymysql.connect(
    user='root',
    passwd='root',
    host='127.0.0.1',
    db='ssafy_pjt',
    charset='utf8'
)

cursor = mydb.cursor()

# book 추가
def add_book (book):
    book_price = int(book['price'].replace(',', ''))
    book_sql = f"INSERT INTO book (title, publisher, story, price, img) VALUES ('{book['title']}', '{book['publisher']}', '{book['story']}', '{book_price}', '{book['img']}');"
    cursor.execute(book_sql)
    mydb.commit()
    return cursor.lastrowid

# table_author 추가
def add_author (book_author, a_set, book_id):
    if type(book_author)==str:
        if book_author not in a_set:
            a_set.add(book_author)
            author_sql = f"INSERT INTO table_author (author) VALUES ('{book_author}');"
            cursor.execute(author_sql)
            mydb.commit()
            # book_author
            book_author_sql = f"INSERT INTO book_author (book_bid, author_aid) VALUES ('{book_id}', '{cursor.lastrowid}');"
            cursor.execute(book_author_sql)
            mydb.commit()
        else:
            author_sql = f"SELECT * FROM table_author WHERE author = '{book_author}';"
            cursor.execute(author_sql)
            result = cursor.fetchall()
            book_author_sql = f"INSERT INTO book_author (book_bid, author_id) VALUES ('{book_id}', '{result[0][0]}');"
            cursor.execute(book_author_sql)
            mydb.commit()


    else:
        for au in book_author:
            if au not in a_set:
                a_set.add(au)
                author_sql = f"INSERT INTO table_author (author) VALUES ('{au}');"
                cursor.execute(author_sql)
                mydb.commit()
                # book_author   
                book_author_sql = f"INSERT INTO book_author (book_bid, author_aid) VALUES ('{book_id}', '{cursor.lastrowid}');"
                cursor.execute(book_author_sql)
                mydb.commit()
            else:
                author_sql = f"SELECT * FROM table_author WHERE author = '{book_author}';"
                cursor.execute(author_sql)
                result = cursor.fetchall()
                book_author_sql = f"INSERT INTO book_author (book_bid, author_id) VALUES ('{book_id}', '{result[0][0]}');"
                cursor.execute(book_author_sql)
                mydb.commit()
    

# table_genre 추가
def add_genre (book_genre, g_set, book_id):
    for gen in book_genre:
        if gen not in g_set:
            g_set.add(gen)
            table_genre_sql  = f"INSERT INTO table_genre (genre) VALUES ('{gen}');"
            cursor.execute(table_genre_sql)
            mydb.commit()
            # book_genre
            book_genre_sql = f"INSERT INTO book_genre (book_bid, genre_gid) VALUES ('{book_id}', '{cursor.lastrowid}');"
            cursor.execute(book_genre_sql)
            mydb.commit()
        else:
            genre_sql = f"SELECT * FROM table_genre WHERE genre = '{book_genre};"
            cursor.execute(genre_sql)
            result = cursor.fetchall()
            book_genre_sql = f"INSERT INRO book_genre (book_id, genre_id) VALUES ('{book_id}', '{result[0][0]}');"


# table_keyword 추가
def add_kw (book_kw, kw_set, book_id):
    for kw in book_kw:
        if kw not in kw_set:
            kw_set.add(kw)
            table_kw_sql = f"INSERT INTO table_keyword (content) VALUES ('{kw}');"
            cursor.execute(table_kw_sql)
            mydb.commit()

            book_kw_sql = f"INSERT INTO book_keyword (book_bid, keyword_kid) VALUES ('{book_id}', '{cursor.lastrowid}');"
            cursor.execute(book_kw_sql)
            mydb.commit()


# author
a_set = set()
# genre
g_set = set()
# keyword
kw_set = set()


with open(file_path, "r", encoding='UTF-8') as json_file:
    book_data = json.load(json_file)

    num = 0

    for book in book_data:

        if book['id'] >= 10:
            break
        
        try:
            # book 테이블 : title, story, publisher, price, img
            book_id = add_book(book)

            # table_author 테이블 : author
            add_author(book['author'], a_set, book_id)

            # table_genre 테이블 genre
            add_genre(book['genre'], g_set, book_id)

            # table_keyword 테이블
            add_kw(book['topic'], kw_set, book_id)


        except Exception as error:
            print(error)
    
        num += 1