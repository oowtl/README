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


URL = "http://www.kyobobook.co.kr/index.laf"
file_path = "./book_data.json"

# 에러메시지 해결 : 장치가 작동하지 않습니다.
options = webdriver.ChromeOptions()
options.add_experimental_option("excludeSwitches", ["enable-logging"])


# driver = webdriver.Chrome("/Users/new_2/Desktop/ssafy/crawling/hicrawl/src/chromedriver")
driver = webdriver.Chrome("./chromedriver", options=options) # options 로 추가해줘야한다.
driver.get(url=URL)

domestic_book = driver.find_elements_by_xpath('/html/body/div[4]/div[1]/div[1]/div[3]/ul[1]/li[1]')

# //*[@id="big_banner"]/button
#/html/body/div[4]/div[1]/div[1]/div[4]/button
try:
    home_big_banner = driver.find_element_by_xpath('/html/body/div[4]/div[1]/div[1]/div[4]/button')
    # home_big_banner = driver.find_element_by_xpath('/html/body/div[3]/div[1]/div[1]/div[4]/button')
    # 홈페이지 큰 배너? 팝업창 제거
    if home_big_banner:
        print("------close big banner")
        home_big_banner.click()

except:
    print('------banner pass')
    pass

# 국내도서로 이동
domestic_book[0].click()
print("------국내도서 이동")

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

            # 소분류 입장
            # 한국소설 등에 마우스를 가져다 댄다.
            actions_small = ActionChains(driver)
            actions_small.move_to_element(driver.find_element_by_xpath('//*[@id="main_snb"]/div[1]/ul[{}]/li[{}]/ul/li[{}]/a'.format(i,j,k)))
            actions_small.click()
            actions_small.perform()

            # 소분류로 입장을 하고 나서 대기
            driver.implicitly_wait(time_to_wait=5)

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
                driver.implicitly_wait(time_to_wait=5)

                # 실행

                # 한 페이지에 나오는 책의 갯수를 구한다.
                book_item_all = driver.find_elements_by_xpath('//*[@id="prd_list_type1"]/li')

                # 20개 리스트를 하나씩 클릭한다.
                for m in range(1, 2):
                    # 북 리스트 - 책 한권 클릭
                    action_to_item = ActionChains(driver)
                    action_to_item.move_to_element(driver.find_element_by_xpath('//*[@id="prd_list_type1"]/li[{}]/div/div[1]/div[2]/div[1]/a'.format((m*2)-1)))
                    action_to_item.click()
                    action_to_item.perform()

                    # 페이지 로드 대기(book item)
                    driver.implicitly_wait(time_to_wait=2)

                    try:
                        print(driver.find_element_by_xpath('//*[@id="container"]/div[2]/form/div[1]/h1').text)
                    
                    except:
                        print('title pass')
                    

                    # 현 상황 : book list로 돌아온 상황
                    driver.back()
                    # 페이지 로드 대기(book list)
                    driver.implicitly_wait(time_to_wait=5)

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
            time.sleep(5) # 정량적으로 기다려줘야 알아먹는 경우도 있다.

            # # 다시 마우스를 호버 해줘야 한다.
            print('------back hover')
            actions_small_back = ActionChains(driver)
            actions_small_back.move_to_element(driver.find_element_by_xpath('//*[@id="main_snb"]/div[1]/ul[{}]/li[{}]/a'.format(i, j)))
            actions_small_back.perform()

            # 대기시간
            driver.implicitly_wait(time_to_wait=5)
            
driver.quit()
exit()
