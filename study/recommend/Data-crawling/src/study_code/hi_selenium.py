# 시간관련
import time

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

# 클릭하기
# //*[@id="header"]/div[3]/ul[1]/li[1]
# /html/body/div[4]/div[1]/div[1]/div[3]/ul[1]/li[1]
# domestic_book = driver.find_elements_by_xpath('/html/body/div[3]/div[1]/div[1]/div[3]/ul[1]/li[1]/a')
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

book_menu_all = driver.find_elements_by_xpath('//*[@id="main_snb"]/div[1]/ul')
print(len(book_menu_all))

print('------mouse')
# 각 장르 별로 하나 씩 해야 한다.
for i in range(1, len(book_menu_all)):
    # 중분류에 손을 올리고 소분류를 받아서 들어간다.

    # ul > li(여기에 해당하는게 소설, 인문 등)
    book_menu_middle = driver.find_elements_by_xpath('//*[@id="main_snb"]/div[1]/ul[{}]/li'.format(i))

    # action ( 중분류 마우스 대기)
    for j in range(1, len(book_menu_middle)+1):
        actions_middle = ActionChains(driver)
        actions_middle.move_to_element(driver.find_element_by_xpath('//*[@id="main_snb"]/div[1]/ul[{}]/li[{}]/a'.format(i, j)))
        actions_middle.perform()

        book_menu_small = driver.find_elements_by_xpath('//*[@id="main_snb"]/div[1]/ul[{}]/li[{}]/ul/li'.format(i, j))

        # 소분류(마우스 호버)
        for k in range(1, len(book_menu_small)):

            # 소분류 입장
            actions_small = ActionChains(driver)
            actions_small.move_to_element(driver.find_element_by_xpath('//*[@id="main_snb"]/div[1]/ul[{}]/li[{}]/ul/li[{}]/a'.format(i,j,k)))
            actions_small.click()
            actions_small.perform()

            driver.implicitly_wait(time_to_wait=5)
            
            # 사이트 나오기
            driver.back()


            # 다시 선택하기?
            actions_small_back = ActionChains(driver)
            actions_small_back.move_to_element(driver.find_element_by_xpath('//*[@id="main_snb"]/div[1]/ul[{}]/li[{}]/a'.format(i, j)))
            actions_small_back.perform()

            
        # 각 호버되는 것들의 패턴
        # 소설 - 한국소설    : //*[@id="main_snb"]/div[1]/ul[1]/li[1]/ul/li[1]/a
        # 소설 - 영미소설    : //*[@id="main_snb"]/div[1]/ul[1]/li[1]/ul/li[2]/a
        # 소설 - 일본소설    : //*[@id="main_snb"]/div[1]/ul[1]/li[1]/ul/li[3]/a

        # 시/에세이 - 한국시 : //*[@id="main_snb"]/div[1]/ul[1]/li[2]/ul/li[1]/a
        # 시/에세이 - 해외시 : //*[@id="main_snb"]/div[1]/ul[1]/li[2]/ul/li[2]/a
        # 시/에세이 - 테마시 : //*[@id="main_snb"]/div[1]/ul[1]/li[2]/ul/li[3]/a

        # 인문 - 인문학 일반 : //*[@id="main_snb"]/div[1]/ul[2]/li[1]/ul/li[1]/a
        # 인문 - 심리학      : //*[@id="main_snb"]/div[1]/ul[2]/li[1]/ul/li[2]/a

        


# 필요하다... 이건 웹에서 보여야 하는 것 같다. html 으로는 아닌듯
# actions = ActionChains(driver)
# actions.move_to_element(domestic_menu)
# actions.perform()
# print("move mouse")


# hover 된 메뉴 파악
# //*[@id="main_snb"]/div[1]/ul[1]/li[1]/ul/li[1]
# t2 = driver.find_elements_by_xpath('//*[@id="main_snb"]/div[1]/ul[1]/li[1]/ul/li[1]/a')

# t2 = driver.find_elements_by_xpath('//*[@id="main_snb"]/div[1]/ul[1]/li[1]/ul/li')
# for i in t2:
#     # print("{} 이동".format(i.text))
#     # i.click()
#     # break

#     print("{} : check".format(i.text))


# driver.quit()