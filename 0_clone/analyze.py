from parse import load_dataframes
import pandas as pd
# 파일, 디렉토리에 대한 복사 이동 삭제 등에 관한 기능 제공
import shutil

def sort_stores_by_score(dataframes, n=20, min_reviews=30):

    """
    Req. 1-2-1 각 음식점의 평균 평점을 계산하여 높은 평점의 음식점 순으로 `n`개의 음식점을 정렬하여 리턴합니다
    Req. 1-2-2 리뷰 개수가 `min_reviews` 미만인 음식점은 제외합니다.
    """
    
    stores_reviews = pd.merge(
        dataframes["stores"], dataframes["reviews"], left_on="id", right_on="store"
    )

    scores_group = stores_reviews.groupby(["store", "store_name"])
    scores = scores_group.mean()

    # print(scores.tail(n=20))
    
    # print(scores_group.head())
    # print(scores.head()) 
    # print(f'\n{len(scores)}')


    # Req. 1-2-1 각 음식점의 평균 평점을 계산하여 높은 평점의 음식점 순으로 `n`개의 음식점을 정렬하여 리턴합니다

    print('@' * 20)

    # print(scores.sort_values().head(n=20))
    # sort_score = stores_reviews.groupby(["store", "store_name"]).mean().groupby(["store_name"]).mean().sort_values('score', ascending=False)
    # print(sort_score.head())
    # print(len(sort_score))
    
    

    return scores.head(n=n).reset_index()


def get_most_reviewed_stores(dataframes, n=20):
    """
    Req. 1-2-3 가장 많은 리뷰를 받은 `n`개의 음식점을 정렬하여 리턴합니다
    """
    raise NotImplementedError


def main():
    data = load_dataframes()

    term_w =shutil.get_terminal_size()[0] - 1
    seperater = "-" * term_w

    stores_most_scored = sort_stores_by_score(data)

    print("[최고 평점 음식점]")
    print(f"{seperater}\n")

    for i, store in stores_most_scored.iterrows():
        print(
            "{rank}위 : {store}({score}점)"
            .format(rank = i + 1, store=store.store_name, score=store.score)
        )
    print(f"\n{seperater}\n\n")


if __name__ == "__main__":
    main()