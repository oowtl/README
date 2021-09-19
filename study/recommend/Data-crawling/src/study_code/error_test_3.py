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



# 소설 마지막 페이지
URL = 'http://www.kyobobook.co.kr/product/detailViewKor.laf?ejkGb=KOR&mallGb=KOR&barcode=9788954681179&orderClick=LEa&Kc='

file_path = "./error_test_2.json"

# 에러메시지 해결 : 장치가 작동하지 않습니다.
options = webdriver.ChromeOptions()
options.add_experimental_option("excludeSwitches", ["enable-logging"])


# driver = webdriver.Chrome("/Users/new_2/Desktop/ssafy/crawling/hicrawl/src/chromedriver")
driver = webdriver.Chrome("./chromedriver", options=options) # options 로 추가해줘야한다.
driver.get(url=URL)

# //*[@id="container"]/div[5]/div[1]/div[3]/div[4]

driver.implicitly_wait(time_to_wait=5)

story_dic_1 = {}
story_dic_2 = {}

story = driver.find_element_by_xpath('//*[@id="container"]/div[5]/div[1]/div[3]/div[4]')

print('------')
story_dic_1['content'] = story.text
print(story_dic_1)

print('------')
# 이건 이스케이프문자로 안되는 것 같다. 그냥 적으면 된다.
story_dic_2['content'] = story.text.replace('\n', '@@@@')
print(story_dic_2)
