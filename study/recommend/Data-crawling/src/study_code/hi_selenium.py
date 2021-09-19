# 시간관련
import time

# json
import json

import selenium
from selenium import webdriver

# 여러 개의 동작을 체인으로 묶어서 저장하고 실행할 수 있도록 하는 것
# 마우스 이동, 클릭, 키보드누르기, 드래그앤 드롭 가능
from selenium.webdriver import ActionChains
from selenium.webdriver.common import action_chains

# keys : 키보드에 키를 제공하는 것 같다.
from selenium.webdriver.common.keys import Keys
# By : 요소를 찾는 데 사용하는 속성
from selenium.webdriver.common.by import By

# expected_conditions : 예상조건지원 이라고 한다.
from selenium.webdriver.support import expected_conditions as EC
# select 요소를 처리하는데 도움을 주는 것 같다. 일일이 선택하지 않아도 되도록 하는 것 같음
from selenium.webdriver.support.ui import Select
# WebDriverWait : 대기지원, 요소가 나타나 때까지 대기를 하는 것
from selenium.webdriver.support.ui import WebDriverWait

# 도서정보 id
book_id = 1

# 함수
# item 페이지에서 data를 수집하는 함수
def search_item_page():
    global book_id

    #웹페이지 로드 기다리기
    driver.implicitly_wait(time_to_wait=5)

    # 수집해야할 데이터
    # title, author, publisher, genre, topic, price, story, img

    # 도서정보를 담아둘 곳
    detail = {}

    # title
    # 작별하지 않는다    : //*[@id="container"]/div[2]/form/div[1]/h1
    # 달러구트 꿈 백화점 : //*[@id="container"]/div[2]/form/div[1]/h1
    try:
        book_item_page_title = driver.find_element_by_xpath('//*[@id="container"]/div[2]/form/div[1]/h1')
        # print(book_item_page_title.text)
        # title = book_item_page_title.text
        detail['title'] = book_item_page_title.text
    except:
        # detail['title'] = ''
        print('book detail - title error')
        return 0

    # author
    # 작별하지 않는다    : //*[@id="container"]/div[2]/form/div[1]/div[2]/span[1]/a
    #                      #container > div:nth-child(4) > form > div.box_detail_point > div.author > span:nth-child(1) > a
    # 달러구트 꿈 백화점 : //*[@id="container"]/div[2]/form/div[1]/div[2]/span[1]/a
    # 홍천기 세트        : //*[@id="container"]/div[2]/form/div[1]/div[3]/span[1]/a
    #                      #container > div:nth-child(4) > form > div.box_detail_point > div.author > span:nth-child(1) > a
    # 우리가 빛의 속도로 : //*[@id="container"]/div[2]/form/div[1]/div[3]/span[1]/a
    #                      #container > div:nth-child(4) > form > div.box_detail_point > div.author > span:nth-child(1) > a

    try:
        # book_item_page_author = driver.find_element_by_xpath('//*[@id="container"]/div[2]/form/div[1]/div[2]/span[1]/a')
        # css selector 로 교체
        book_item_page_author = driver.find_element_by_css_selector('#container > div:nth-child(4) > form > div.box_detail_point > div.author > span:nth-child(1) > a')
        
        # print(book_item_page_author.text)
        # author = book_item_page_author.text
        detail['author'] = book_item_page_author.text
    except:
        # detail['author'] = ''
        print('book detail - author error')
        return 0

    # publisher
    # 작별하지 않는다    : //*[@id="container"]/div[2]/form/div[1]/div[2]/span[3]/a
    #                      #container > div:nth-child(4) > form > div.box_detail_point > div.author > span:nth-child(3) > a
    # 달러구트 꿈 백화점 : //*[@id="container"]/div[2]/form/div[1]/div[2]/span[3]/a
    #                      #container > div:nth-child(4) > form > div.box_detail_point > div.author > span:nth-child(3) > a
    # 홍천기             : //*[@id="container"]/div[2]/form/div[1]/div[3]/span[3]/a
    #                      #container > div:nth-child(4) > form > div.box_detail_point > div.author > span:nth-child(3) > a
    # 우리가 빛의 속도로 : //*[@id="container"]/div[2]/form/div[1]/div[3]/span[3]/a
    #                      #container > div:nth-child(4) > form > div.box_detail_point > div.author > span:nth-child(3) > a

    try:
        # book_item_page_publisher = driver.find_element_by_xpath('//*[@id="container"]/div[2]/form/div[1]/div[2]/span[3]/a')
        book_item_page_publisher = driver.find_element_by_css_selector('#container > div:nth-child(4) > form > div.box_detail_point > div.author > span:nth-child(3) > a')
        # print(book_item_page_publisher.text)
        # publisher = book_item_page_publisher.text
        detail['publisher'] = book_item_page_publisher.text
    except:
        # detail['publisher'] = ''
        # pass
        print('book detail - publisher error')
        return 0

    # genre
    # 작별하지 않는다 - 1   : //*[@id="container"]/div[5]/div[1]/div[3]/ul/li/a[1]
    # 작별하지 않는다 - 2   : //*[@id="container"]/div[5]/div[1]/div[3]/ul/li/a[2]
    # 작별하지 않는다 - 3   : //*[@id="container"]/div[5]/div[1]/div[3]/ul/li/a[3]
    # 달러구트 꿈 백화점 1-1: //*[@id="container"]/div[5]/div[1]/div[3]/ul/li[1]/a[1]
    # 달러구트 꿈 백화점 1-2: //*[@id="container"]/div[5]/div[1]/div[3]/ul/li[1]/a[2]
    # 달러구트 꿈 백화점 1-3: //*[@id="container"]/div[5]/div[1]/div[3]/ul/li[1]/a[3]
    # 달러구트 꿈 백화점 2-1: //*[@id="container"]/div[5]/div[1]/div[3]/ul/li[2]/a[1]
    # 달러구트 꿈 백화점 2-2: //*[@id="container"]/div[5]/div[1]/div[3]/ul/li[2]/a[2]
    # 달러구트 꿈 백화점 2-3: //*[@id="container"]/div[5]/div[1]/div[3]/ul/li[2]/a[3]
    # 바깥은 여름        1-1: //*[@id="container"]/div[5]/div[1]/div[2]/ul/li/a[1]
    # 바깥은 여름        1-2: //*[@id="container"]/div[5]/div[1]/div[2]/ul/li/a[2]
    # 바깥은 여름        1-2: //*[@id="container"]/div[5]/div[1]/div[2]/ul/li/a[3]
    # 위기를 기회로 만든 1-1: //*[@id="container"]/div[5]/div[1]/div[2]/ul/li/a[1]
    # 위기를 기회로 만든 1-2: //*[@id="container"]/div[5]/div[1]/div[2]/ul/li/a[2]
    # 위기를 기회로 만든 1-3: //*[@id="container"]/div[5]/div[1]/div[2]/ul/li/a[3]
    # 동대문 패션 그곳에 1-1: //*[@id="container"]/div[5]/div[1]/div[2]/ul/li/a[1]
    
    try:
        # 장르가 몇개인지 확인
        book_item_page_genre = driver.find_elements_by_xpath('//*[@id="container"]/div[5]/div[1]/div[3]/ul/li')
        # 중복 없애기
        book_genre = set()

        # review 가 올라오지 못하도록 막기
        for i in range(0, (len(book_item_page_genre))):
            if ('|' in book_item_page_genre[i].text): # review 이면 | 가 존재한다.
                # 다른 곳으로 바꿔준다.
                book_item_page_genre = driver.find_elements_by_xpath('//*[@id="container"]/div[5]/div[1]/div[2]/ul/li')
                break

        for i in range(0, (len(book_item_page_genre))):
            # > 로 나눠주기
            genres = book_item_page_genre[i].text.split('>')

            for j in range(0, len(genres)):
                # 공백 제거해서 넣어주기
                book_genre.add(genres[j].strip())

        # genre 없으면?
        if len(book_genre) == 0:
            print('book deatil - genre error')
            return 0
        
        # 결과!
        detail['genre'] = list(book_genre)
                
    except:
        # detail['genre'] = ''
        # pass
        print('book detail - genre error')
        return 0

    # topic
    # 불편한 편의점 - 키워드 pick -1 : //*[@id="container"]/div[2]/form/div[2]/div[3]/div[2]/a[1]
    # 불편한 편의점 - 키워드 pick -2 : //*[@id="container"]/div[2]/form/div[2]/div[3]/div[2]/a[2]
    # 불편한 편의점 - 키워드 pick -3 : //*[@id="container"]/div[2]/form/div[2]/div[3]/div[2]/a[3]
    
    # 키워드 pick 추출

    topic = set()

    try:
        book_keyword_picks = driver.find_elements_by_xpath('//*[@id="container"]/div[2]/form/div[2]/div[3]/div[2]/a')
        # print('find : len : {}'.format(len(book_keyword_picks))) # 0도 나오고 한다

        if len(book_keyword_picks) == 0:
            pass
        # 키워드 pick 존재
        else:
            for i in range(1, len(book_keyword_picks)+1):
                # 이 태그는 다 있는데, 내용이 없다.
                pick = driver.find_element_by_xpath('//*[@id="container"]/div[2]/form/div[2]/div[3]/div[2]/a[{}]'.format(i)).text
                topic.add(pick)
    except:
        # 없다는 뜻
        # print('------keyword pass')
        # pass

        print('book detail - keyword error')

        return 0
            
    # 달러구트 백화점.2 주제어 -1 : //*[@id="container"]/div[5]/div[1]/div[3]/div[2]/a[1]/span/em
    # 달러구트 백화점.2 주제어 -2 : //*[@id="container"]/div[5]/div[1]/div[3]/div[2]/a[2]/span/em
    # 달러구트 백화점.2 주제어 -3 : //*[@id="container"]/div[5]/div[1]/div[3]/div[2]/a[3]/span/em
    # 달러구트 백화점.2 주제어 -4 : //*[@id="container"]/div[5]/div[1]/div[3]/div[2]/a[4]/span/em
    # 달러구트 백화점.2 주제어 -4 : //*[@id="container"]/div[5]/div[1]/div[3]/div[2]/a[5]/span/em
    
    try:
        book_item_topic = driver.find_elements_by_xpath('//*[@id="container"]/div[5]/div[1]/div[3]/div[2]/a')
        if len(book_item_topic) == 0:
            pass
        else:
            for i in range(1, len(book_item_topic)+1):
                item_topic = driver.find_element_by_xpath('//*[@id="container"]/div[5]/div[1]/div[3]/div[2]/a[{}]/span'.format(i)).text

                # 해시태그 삭제
                if item_topic.find('#') >= 0:
                    item_topic = item_topic.replace('#', '')
                
                topic.add(item_topic)
    except:
        # 없다?
        # print('------topic pass')
        # pass
        
        print('book detail - topic error')

        return 0

    # topic이 없는 경우도 있어서..
    if len(topic)==0:
        print('book detail - not topic')
        return 0
    else:
        detail['topic'] = list(topic)


    # price
    # 작별하지 않는다       : //*[@id="container"]/div[2]/form/div[3]/div[1]/ul/li[1]/span[1]
    # 달러구트 꿈 백화점. 2 : //*[@id="container"]/div[2]/form/div[3]/div[1]/ul/li[1]/span[1]
    
    try:
        book_item_price = driver.find_element_by_xpath('//*[@id="container"]/div[2]/form/div[3]/div[1]/ul/li[1]/span[1]').text

        # '원' 없애주기
        book_item_price = book_item_price.replace('원', '')
        detail['price'] = book_item_price

    except:
        # detail['price'] = ''
        # pass

        print('book detail - price error')
        return 0

    # story
    # 작별하지 않는다      : //*[@id="container"]/div[5]/div[1]/div[3]/div[2]
    # 작별하지 않는다      : //*[@id="container"]/div[5]/div[1]/div[3]/div[3]
    #                      : #container > div:nth-child(7) > div.content_left > div:nth-child(5) > div:nth-child(8)
    # 달러구트 꿈 백화점.2 : //*[@id="container"]/div[5]/div[1]/div[3]/div[3]
    #                      : #container > div:nth-child(7) > div.content_left > div:nth-child(5) > div:nth-child(8)
    # 여주인공의 오빠를 지 : //*[@id="container"]/div[5]/div[1]/div[2]/div[3]
    #                      : #container > div:nth-child(7) > div.content_left > div:nth-child(3) > div:nth-child(8)
    # 늑대                 : //*[@id="container"]/div[5]/div[1]/div[2]/div[2]
    #                      : #container > div:nth-child(7) > div.content_left > div:nth-child(3) > div:nth-child(6)
    # 원미동 사람들        : //*[@id="container"]/div[5]/div[1]/div[2]/div[4]
    #                      : #container > div:nth-child(7) > div.content_left > div:nth-child(3) > div:nth-child(10)
    # 멍에를 벗어나기 위한 : //*[@id="container"]/div[5]/div[1]/div[2]/div[3]
    #                      : #container > div:nth-child(7) > div.content_left > div:nth-child(3) > div:nth-child(8)

    try:
        # 3 3
        book_item_story = driver.find_element_by_xpath('//*[@id="container"]/div[5]/div[1]/div[3]/div[3]')

        if len(book_item_story.text) == 0:
            book_item_story_try = story_crawl()
            if book_item_story_try == 0:
                return 0
            else:
                detail['story'] = book_item_story_try.replace("\n", "")
        else:
            detail['story'] = book_item_story.text.replace("\n", "")
    except:
        # detail['story'] = ''
        # pass

        book_item_story_try = story_crawl()

        if book_item_story_try == 0:
            print('book detail - story error')
            return 0

        detail['story'] = book_item_story_try.replace("\n", "")
        
    # img
    # 작별하지 않는다       : //*[@id="container"]/div[2]/form/div[2]/div[1]/div/a/img
    # 달러구트 꿈 백화점. 2 : //*[@id="container"]/div[2]/form/div[2]/div[1]/div/a/img
    try:
        book_item_img_tag = driver.find_element_by_xpath('//*[@id="container"]/div[2]/form/div[2]/div[1]/div/a/img')
        book_item_img = book_item_img_tag.get_attribute('src')
        detail['img'] = book_item_img

    except:
        print('book detail - img error')
        return 0

    # print(detail)

    detail['id'] = book_id
    book_id += 1
    print("------ book detail ok")

    # 결과값==0 : 원하는 데이터가 아닙니다.
    # 결과값!=0 : 원하는 데이터   입니다.
    print(detail)
    return detail


# 19금 도서 alert 창 회피하기
def void_alert():  
    try:
        # alert 전환
        alert = driver.switch_to_alert()

        # alert 창 - 확인버튼
        alert.accept() # 확인된 것은 확인창만 존재하는 alert 이니까 확인만 넣어놓고 돌려보자.
        # # alert 창 - 끄기버튼
        # alert.dismiss()
        return 1
    # 에러의 이유는 switch_to_alert 라는 것에만 한정지어서 생각함.
    except:
        return 0
    
def story_crawl():
    # 2 3
    try:
        book_item_story_2_3 = driver.find_element_by_xpath('//*[@id="container"]/div[5]/div[1]/div[2]/div[3]')
        # 아무것도 없으면 문제가 생긴다.
        if len(book_item_story_2_3.text) == 0:
            raise
        return book_item_story_2_3.text
    except:
        # 2 4
        try:
            book_item_story_2_4 = driver.find_element_by_xpath('//*[@id="container"]/div[5]/div[1]/div[2]/div[4]')
            if len(book_item_story_2_4.text) == 0:
                raise
            return book_item_story_2_4.text
        except:
            # 2 2
            try:
                book_item_story_2_2 = driver.find_element_by_xpath('//*[@id="container"]/div[5]/div[1]/div[2]/div[2]')
                if len(book_item_story_2_2.text) == 0:
                    raise
                return book_item_story_2_2.text
            except:
                print('book detail - story error')
                return 0

URL = "http://www.kyobobook.co.kr/index.laf"
file_path = "./book_data_2t.json"

# 에러메시지 해결 : 장치가 작동하지 않습니다.
options = webdriver.ChromeOptions()
options.add_experimental_option("excludeSwitches", ["enable-logging"])


# driver = webdriver.Chrome("/Users/new_2/Desktop/ssafy/crawling/hicrawl/src/chromedriver")
driver = webdriver.Chrome("./chromedriver", options=options) # options 로 추가해줘야한다.
driver.get(url=URL)

# driver.maximize_window()

# 암묵적 대기
# driver.implicitly_wait(time_to_wait=5)
# driver.quit()

# 명시적 대기
# try:
#   element = WebDriverWait(driver, 5).until(
#     EC.presence_of_element_located((By.CLASS_NAME, 'box_welcome_personal'))
#   )
# finally:
#   driver.quit()

# 엘레멘트 찾기

# driver.find_element_by_xpath('html/body/div[2]/div[1]') # Xpath 로 접근하기
# driver.find_element_by_class_name('gnb_main') # 클래스 이름 / 클래스는 한 개씩 하도록!
# driver.find_element_by_id('id_name') # id
# driver.find_element_by_link_text('국내도서') # 링크가 달려있는 텍스트에 접근하다.
# driver.find_element_by_css_selector('.gnb_main > .item_1 > a') # css 선택자
# driver.find_element_by_tag_name('ul') # 태그 이름으로 접근

# driver.find_elements_by_xpath('/html/body/div[3]/div[1]/div[1]/div[3]/ul[1]') # Xpath 로 접근하기
# driver.find_elements_by_class_name('gnb_main') # 클래스 이름 / 클래스는 한 개씩 하도록!
# driver.find_elements_by_id('id_name') # id
# driver.find_elements_by_link_text('국내도서') # 링크가 달려있는 텍스트에 접근하다.
# driver.find_elements_by_css_selector('.gnb_main > .item_1 > a') # css 선택자
# driver.find_elements_by_tag_name('ul') # 태그 이름으로 접근

# //*[@id="big_banner"]/button
#/html/body/div[4]/div[1]/div[1]/div[4]/button
try:
    home_big_banner = driver.find_element_by_xpath('/html/body/div[3]/div[1]/div[1]/div[4]/button')
    # home_big_banner = driver.find_element_by_xpath('/html/body/div[3]/div[1]/div[1]/div[4]/button')
    # /html/body/div[4]/div[1]/div[1]/div[4]/button
    
    # 홈페이지 큰 배너? 팝업창 제거
    if home_big_banner:
        print("------close big banner")
        home_big_banner.click()

except:
    # 태그가 바뀜
    # /html/body/div[4]/div[1]/div[1]/div[4]/button
    try: 
        home_big_banner = driver.find_element_by_xpath('/html/body/div[4]/div[1]/div[1]/div[4]/button')

        # 홈페이지 큰 배너? 팝업창 제거
        if home_big_banner:
            print("------close big banner")
            home_big_banner.click()
    except:
        print('------banner pass')

# 클릭하기
# //*[@id="header"]/div[3]/ul[1]/li[1]
# /html/body/div[4]/div[1]/div[1]/div[3]/ul[1]/li[1]
# domestic_book = driver.find_elements_by_xpath('/html/body/div[3]/div[1]/div[1]/div[3]/ul[1]/li[1]/a')
# //*[@id="header"]/div[3]/ul[1]/li[1]/a
# /html/body/div[4]/div[1]/div[1]/div[3]/ul[1]/li[1]
# /html/body/div[3]/div[1]/div[1]/div[3]/ul[1]/li[1]/a
domestic_book = driver.find_elements_by_xpath('/html/body/div[3]/div[1]/div[1]/div[3]/ul[1]/li[1]')

try: 
    # 국내도서로 이동
    domestic_book[0].click()
    print("------국내도서 이동")
except IndexError:
    domestic_book = driver.find_elements_by_xpath('/html/body/div[4]/div[1]/div[1]/div[3]/ul[1]/li[1]/a')
    domestic_book[0].click()
    print("------국내도서 이동")


# domestic_novels = driver.find_elements_by_xpath('/html/body/div[2]/div[1]/div[2]/div/div[1]/div[1]/ul[1]/li[1]/ul/li/a')
# domestic_novels = driver.find_elements_by_xpath('/html/body/div[2]/div[1]/div[2]/div/div[1]/div[1]/ul[1]/li[1]/ul/li[1]')

# for i in domestic_novels:
#     print(i.text)


# t1 = driver.find_elements_by_xpath('//*[@id="main_snb"]/div[1]/ul[1]/li[1]/a')
# print(t1[0].text)

# 소설 하나에 대한 xpath
# domestic_menu = driver.find_element_by_xpath('//*[@id="main_snb"]/div[1]/ul[1]/li[1]/a')

# navigator 전체에 대한 css selector
# domestic_all_book = driver.find_elements_by_css_selector('#main_snb > div.nav_category > ul > li')

# 국내 도서 메뉴별 xpath 의 패턴
# 소설             : //*[@id="main_snb"]/div[1]/ul[1]/li[1]/a
# 시/에세이        : //*[@id="main_snb"]/div[1]/ul[1]/li[2]/a
# 경제/경영        : //*[@id="main_snb"]/div[1]/ul[1]/li[3]/a

# 인문             : //*[@id="main_snb"]/div[1]/ul[2]/li[1]/a
# 역사/문화        : //*[@id="main_snb"]/div[1]/ul[2]/li[2]/a

# 끝(한국소개도서) : //*[@id="main_snb"]/div[1]/ul[7]/li/a

# 패턴접근
print('------pattern')

# 대기
driver.implicitly_wait(time_to_wait=5)

# 카테고리 부분이 몇개나 있는지
book_menu_all = driver.find_elements_by_xpath('//*[@id="main_snb"]/div[1]/ul')

# 국내 도서 카테고리 선택으로 돌아오기

# 1번 : //*[@id="gnb_category"]/a
# 2번 : //*[@id="gnb_menu01"]/div[1]
# 3번 : 클릭


print('------mouse')
# 각 장르 별로 하나 씩 해야 한다.
for i in range(1, len(book_menu_all)):
    # 중분류에 손을 올리고 소분류를 받아서 들어간다.

    # ul > li(여기에 해당하는게 소설, 인문 등)
    book_menu_middle = driver.find_elements_by_xpath('//*[@id="main_snb"]/div[1]/ul[{}]/li'.format(i))

    # action ( 중분류 마우스 대기)
    for j in range(1, len(book_menu_middle)+1):
        # 소설, 시 등에 마우스 가져다 댄다.
        actions_middle = ActionChains(driver)
        actions_middle.move_to_element(driver.find_element_by_xpath('//*[@id="main_snb"]/div[1]/ul[{}]/li[{}]/a'.format(i, j)))
        actions_middle.perform()

        book_menu_small = driver.find_elements_by_xpath('//*[@id="main_snb"]/div[1]/ul[{}]/li[{}]/ul/li'.format(i, j))

        # 소분류(마우스 호버)
        for k in range(1, len(book_menu_small)):

            # json 불러오기
            # 처음에는 data 가 없어서 load 할 것이 없다.
            if k==1 and j==1 and i==1: # 완전 처음이면 하나 만들어서 넣어보자.
                print('------json write mode')
                with open(file_path, 'w', encoding="UTF-8") as json_file:
                    json_data = []
            else:
                print('------json read mode')
                with open(file_path, 'r', encoding="UTF-8") as json_file:
                    json_data = json.load(json_file)

            # 대기
            time.sleep(3)

            # 소분류 입장
            # 한국소설 등에 마우스를 가져다 댄다.
            actions_small = ActionChains(driver)
            actions_small.move_to_element(driver.find_element_by_xpath('//*[@id="main_snb"]/div[1]/ul[{}]/li[{}]/ul/li[{}]/a'.format(i,j,k)))
            actions_small.click()
            actions_small.perform()

            # 소분류로 입장을 하고 나서 대기
            driver.implicitly_wait(time_to_wait=2)

            # 페이지를 파악하자
            # 이 페이지는 페이지네이션이 2개가 들어갑니다.
            # 현재 xpath 는 상단에 들어가있는 것입니다.
            book_item_pagination = driver.find_elements_by_xpath('//*[@id="eventPaging"]/div/ul/li')

            # print(len(book_item_pagination))

            for l in range(1, (len(book_item_pagination)//2)+1):

                # 페이지네이션으로 들어 간다.
                action_to_pagination = ActionChains(driver)
                action_to_pagination.move_to_element(driver.find_element_by_xpath('//*[@id="eventPaging"]/div/ul/li[{}]'.format(l)))
                action_to_pagination.click()
                action_to_pagination.perform()

                # 대기시간
                driver.implicitly_wait(time_to_wait=2)

                # 실행

                # 한 페이지에 나오는 책의 갯수를 구한다.
                book_item_all = driver.find_elements_by_xpath('//*[@id="prd_list_type1"]/li')

                # 20개 리스트를 하나씩 클릭한다.
                for m in range(1, (len(book_item_all)//2)+1):
                    # 북 리스트 - 책 한권 클릭
                    action_to_item = ActionChains(driver)
                    action_to_item.move_to_element(driver.find_element_by_xpath('//*[@id="prd_list_type1"]/li[{}]/div/div[1]/div[2]/div[1]/a'.format((m*2)-1)))
                    action_to_item.click()
                    action_to_item.perform()

                    # 페이지 로드 대기(book item)
                    driver.implicitly_wait(time_to_wait=1)

                    # 19금 도서 alert 회피하기
                    alert_status = void_alert() # 0 이면 정상도서, 1 이면 다음도서로 넘어가야한다.
                    if alert_status == 1:
                        continue

                    # 데이터 수집, json 에 넣어줄 것들
                    # 자체적으로 기다리도록 하겠음
                    detail = search_item_page()
                    if detail == 0:
                        print('---detail fail')
                    else:
                        json_data.append(detail)

                    # 현 상황 : book list로 돌아온 상황
                    driver.back()
                    # 페이지 로드 대기(book list)
                    driver.implicitly_wait(time_to_wait=2)

                    
                # 10이면 '>' 선택하고 다시 페이지네이션 돌려야 합니다.
                if l == 10:
                    # 하드코딩으로 극복

                    # '>' 가 있으면 실행하고 없으면 break를 해서 넘어간다.
                    try:
                        go_to_next_page = driver.find_element_by_xpath('//*[@id="eventPaging"]/div/a[2]')
                        action_to_pagination_go_plus = ActionChains(driver)
                        action_to_pagination_go_plus.move_to_element(go_to_next_page)
                        action_to_pagination_go_plus.click()
                        action_to_pagination_go_plus.perform()
                    
                    # 에러가 나면 어떻게 할 것인가??
                    except:
                        break
                    
                    # 대기시간
                    driver.implicitly_wait(time_to_wait=2)

                    book_item_plus_pagination = driver.find_elements_by_xpath('//*[@id="eventPaging"]/div/ul/li')

                    # print(len(book_item_plus_pagination))
                    
                    # 페이지네이션에 들어가기
                    for z in range(1, (len(book_item_plus_pagination)//2+1)):

                        # 페이지네이션 선택
                        action_to_pagination_in_plus = ActionChains(driver)
                        action_to_pagination_in_plus.move_to_element(driver.find_element_by_xpath('//*[@id="eventPaging"]/div/ul/li[{}]'.format(z)))
                        action_to_pagination_in_plus.click()
                        action_to_pagination_in_plus.perform()

                        # 대기시간
                        driver.implicitly_wait(time_to_wait=2)

                        # 실행

                        # 한 페이지에 나오는 책의 갯수를 구한다.
                        book_item_all = driver.find_elements_by_xpath('//*[@id="prd_list_type1"]/li')

                        # 20개 리스트를 하나씩 클릭한다.
                        for t in range(1, (len(book_item_all)//2)+1):
                            # 북 리스트 - 책 한권 클릭
                            action_to_item = ActionChains(driver)
                            action_to_item.move_to_element(driver.find_element_by_xpath('//*[@id="prd_list_type1"]/li[{}]/div/div[1]/div[2]/div[1]/a'.format((t*2)-1)))
                            action_to_item.click()
                            action_to_item.perform()

                            # 페이지 로드 대기(book item)
                            driver.implicitly_wait(time_to_wait=1)

                            
                            # 19금 도서 alert 회피하기
                            alert_status = void_alert() # 0 이면 정상도서, 1 이면 다음도서로 넘어가야한다.
                            if alert_status == 1:
                                continue

                            # 데이터 수집, json 에 넣어줄 것들
                            # 자체적으로 기다리도록 하겠음
                            detail = search_item_page()
                            if detail == 0:
                                print('---detail fail')
                            else:
                                json_data.append(detail)

                            # 현 상황 : book list로 돌아온 상황
                            driver.back()
                            # 페이지 로드 대기(book list)
                            driver.implicitly_wait(time_to_wait=2)

            # json 저장한다! (한 소분류가 끝나면 하는 것으로!)
            with open(file_path, 'w', encoding="UTF-8")as outfile:
                print('------save json')
                json.dump(json_data, outfile, ensure_ascii=False)

            # 대기시간
            time.sleep(3)

            # 다시 호버를 할 수 있는 카테고리로 돌아가야 합니다.
            # 국내도서 카테고리로 돌아가기 : //*[@id="header"]/div[3]/ul[1]/li[1]/a
            # 메뉴 아이콘                  : //*[@id="gnb_category"]/a
            #                              : //*[@id="gnb_menu01"]/div[1]/a
            print('------back category')
            action_to_category = ActionChains(driver)
            # action_to_category.move_to_element(driver.find_element_by_xpath('//*[@id="header"]/div[3]/ul[1]/li[1]/a'))
            action_to_category.move_to_element(driver.find_element_by_xpath('//*[@id="gnb_category"]/a'))
            action_to_category.move_to_element(driver.find_element_by_xpath('//*[@id="gnb_menu01"]/div[1]/a'))
            action_to_category.click()
            action_to_category.perform()

            # 대기시간
            # driver.implicitly_wait(time_to_wait=5)
            time.sleep(3) # 정량적으로 기다려줘야 알아먹는 경우도 있다.

            # # 다시 마우스를 호버 해줘야 한다.
            print('------back hover')
            actions_small_back = ActionChains(driver)
            actions_small_back.move_to_element(driver.find_element_by_xpath('//*[@id="main_snb"]/div[1]/ul[{}]/li[{}]/a'.format(i, j)))
            actions_small_back.perform()

            # 대기시간
            time.sleep(3)


print('------done------')
# driver.quit()
# exit()

        # 각 호버되는 것들의 패턴
        # 소설 - 한국소설    : //*[@id="main_snb"]/div[1]/ul[1]/li[1]/ul/li[1]/a
        # 소설 - 영미소설    : //*[@id="main_snb"]/div[1]/ul[1]/li[1]/ul/li[2]/a
        # 소설 - 일본소설    : //*[@id="main_snb"]/div[1]/ul[1]/li[1]/ul/li[3]/a

        # 시/에세이 - 한국시 : //*[@id="main_snb"]/div[1]/ul[1]/li[2]/ul/li[1]/a
        # 시/에세이 - 해외시 : //*[@id="main_snb"]/div[1]/ul[1]/li[2]/ul/li[2]/a
        # 시/에세이 - 테마시 : //*[@id="main_snb"]/div[1]/ul[1]/li[2]/ul/li[3]/a

        # 인문 - 인문학 일반 : //*[@id="main_snb"]/div[1]/ul[2]/li[1]/ul/li[1]/a
        # 인문 - 심리학      : //*[@id="main_snb"]/div[1]/ul[2]/li[1]/ul/li[2]/a


        # 책 목록 패턴 (한국소설)
        # 1번  : //*[@id="prd_list_type1"]/li[1]/div/div[1]/div[2]/div[1]/a
        # 2번  : //*[@id="prd_list_type1"]/li[3]/div/div[1]/div[2]/div[1]/a
        # 3번  : //*[@id="prd_list_type1"]/li[5]/div/div[1]/div[2]/div[1]/a
        # 4번  : //*[@id="prd_list_type1"]/li[7]/div/div[1]/div[2]/div[1]/a
        # 20번 : //*[@id="prd_list_type1"]/li[39]/div/div[1]/div[2]/div[1]/a


        # 페이지네이션 패턴 (한국소설)
        # 1번  : //*[@id="eventPaging"]/div/ul/li[1]/a (//*[@id="eventPaging"]/div/ul/li[1]/strong/a)
        # 2번  : //*[@id="eventPaging"]/div/ul/li[2]/a
        # 3번  : //*[@id="eventPaging"]/div/ul/li[3]/a
        # 10번 : //*[@id="eventPaging"]/div/ul/li[10]/a
        # >    : //*[@id="eventPaging"]/div/a[2]
        # 11번 : //*[@id="eventPaging"]/div/ul/li[1]/a (//*[@id="eventPaging"]/div/ul/li[1]/strong/a)
        # 12번 : //*[@id="eventPaging"]/div/ul/li[2]/a