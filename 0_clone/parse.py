import json
import pandas as pd
import os
import shutil

DATA_DIR = "../data"
DATA_FILE = os.path.join(DATA_DIR, "data.json")
DUMP_FILE = os.path.join(DATA_DIR, "dump.pkl")

# 데이터의 컬럼을 이야기한다.
store_columns = (
    "id",  # 음식점 고유번호
    "store_name",  # 음식점 이름
    "branch",  # 음식점 지점 여부
    "area",  # 음식점 위치
    "tel",  # 음식점 번호
    "address",  # 음식점 주소
    "latitude",  # 음식점 위도
    "longitude",  # 음식점 경도
    "category",  # 음식점 카테고리
    "review_cnt", # 리뷰 수
)

review_columns = (
    "id",  # 리뷰 고유번호
    "store",  # 음식점 고유번호
    "user",  # 유저 고유번호
    "score",  # 평점
    "content",  # 리뷰 내용
    "reg_time",  # 리뷰 등록 시간
)

# # 메뉴 컬럼
# menu_columns = (
#     "id", # 메뉴 고유번호
#     "store", # 음식점 고유번호
#     "menu", # 메뉴 이름
#     "price", # 메뉴 가격
# )

# # 영업시간 목록 컬럼
# bhour_columns = (
#     "type", # 영업시간 종류
#     "week_type", # 주 단위 종류
#     "mon", # 월요일 포함여부
#     "tue", # 화요일 포함여부
#     "wed", # 수요일 포함여부
#     "thu", # 목요일 포함여부
#     "fri", # 금요일 포함여부
#     "sat", # 토요일 포함여부
#     "sun", # 일요일 포함여부
#     "start_time", # 시작 시간
#     "end_time", # 종료 시간
#     "etc", # 기타 정보
# )

def import_data(data_path=DATA_FILE):
    """
    Req. 1-1-1 음식점 데이터 파일을 읽어서 Pandas DataFrame 형태로 저장합니다
    """

    try:
        # json 으로 읽어온다.
        with open(data_path, encoding="utf-8") as f:
            data = json.loads(f.read())
    except FileNotFoundError as e:
        print(f"`{data_path}` 가 존재하지 않습니다.")
        exit(1)

    
    stores = [] # 음식점 테이블
    reviews = [] # 리뷰 테이블
    menus = [] # 메뉴 테이블
    bhours = [] # 영업시간 목록 테이블

    for d in data:
        categories = [c["category"] for c in d["category_list"]]
        stores.append(
            [
                d["id"],
                d["name"],
                d["branch"],
                d["area"],
                d["tel"],
                d["address"],
                d["latitude"],
                d["longitude"],
                # categories 의 인자들 사이사이에 | 를 집어넣는 string
                "|".join(categories),
                d["review_cnt"], # 리뷰 카운트 추가
            ]
        )

        # for m in d["menu_list"]:
        #     menu = m["menu"]
        #     price = m["price"]
        #     menus.append(
        #     )

        for review in d["review_list"]:
            r = review["review_info"]
            u = review["writer_info"]

            reviews.append(
                [r["id"], d["id"], u["id"], r["score"], r["content"], r["reg_time"]]
            )
        
    store_frame = pd.DataFrame(data = stores, columns=store_columns)
    review_frame = pd.DataFrame(data = reviews, columns=review_columns)

    return {"stores" : store_frame, "reviews" : review_frame}

def dump_dataframes(dataframes):
    # 데이터를 pickle 형식으로 저장하는 것
    pd.to_pickle(dataframes, DUMP_FILE)

def load_dataframes():
    # 데이터를 읽는 것
    return pd.read_pickle(DUMP_FILE)


def main():
    print("[*] Parsing data ...")
    data = import_data()
    print("[+] Done")

    print("[*] Dumping data ...")
    dump_dataframes(data)
    print("[+] Done\n")

    data = load_dataframes()

    term_w = shutil.get_terminal_size()[0] - 1
    seperater = "-" * term_w

    print("[음식점]")
    print(f"{seperater}\n")
    print(data["stores"].head(n=20))
    print("@"*30)
    print(data["reviews"].head(n=20))
    print(f"\n{seperater}\n\n")

if __name__ == "__main__":
    main()