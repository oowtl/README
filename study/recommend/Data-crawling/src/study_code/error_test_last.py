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

# 소설 마지막 페이지
URL = 'http://www.kyobobook.co.kr/categoryRenewal/categoryMain.laf?linkClass=0101&mallGb=KOR&orderClick=sga'

file_path = "./error_test_2.json"

# 에러메시지 해결 : 장치가 작동하지 않습니다.
options = webdriver.ChromeOptions()
options.add_experimental_option("excludeSwitches", ["enable-logging"])


# driver = webdriver.Chrome("/Users/new_2/Desktop/ssafy/crawling/hicrawl/src/chromedriver")
driver = webdriver.Chrome("./chromedriver", options=options) # options 로 추가해줘야한다.
driver.get(url=URL)


# 대기시간
driver.implicitly_wait(time_to_wait=5)

last_item = driver.find_element_by_xpath('//*[@id="prd_list_type1"]/li[39]/div/div[1]/div[2]/div[1]/a/strong')
action_move_last_item = ActionChains(driver)
action_move_last_item.move_to_element(last_item)
action_move_last_item.click()
action_move_last_item.perform()

# 대기시간
driver.implicitly_wait(time_to_wait=5)
try:
    book_item_page_title = driver.find_element_by_xpath('//*[@id="container"]/div[2]/form/div[1]/h1')
    # print(book_item_page_title.text)
    # title = book_item_page_title.text
    print(book_item_page_title.text)
except:
    # detail['title'] = ''
    print('book detail - title error')

print('---item done')
driver.back()

# 대기시간
driver.implicitly_wait(time_to_wait=5)

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
driver.implicitly_wait(time_to_wait=5)

# # 다시 마우스를 호버 해줘야 한다.
print('------back hover')
actions_small_back = ActionChains(driver)
actions_small_back.move_to_element(driver.find_element_by_xpath('//*[@id="main_snb"]/div[1]/ul[{}]/li[{}]/a'.format(i, j)))
actions_small_back.perform()
