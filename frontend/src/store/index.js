import Vue from "vue";
import Vuex from "vuex";
import http from "@/utils/http.js";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    // 회원가입시 ID, Nickname, Password State
    signupCheck: {
      userId: {
        flag: false,
        condition: {
          flag: false,
          msg: "ID는 4자 이상이여야 합니다."
        },
        duplicate: {
          flag: false,
          msg: "중복된 ID가 존재합니다."
        },
      },
      nickname: {
        flag: false,
        condition: {
          flag: false,
          msg: "닉네임은 3자 이상이여야 합니다."
        },
        duplicate: {
          flag: false,
          msg: "중복된 닉네임이 존재합니다."
        },
      },
      password: {
        flag: false,
        check: false,
        condition: {
          flag: false,
          msg: "Password는 6자 이상이여야 합니다."
        }
      }
    },
    signUpForm: {
      userId: String,
      nickname: String,
      password: String,
      mbti: ['I','S','T','J']
    },
    bookPropensity: [
      {"title": "달러구트 꿈 백화점. 2 단골손님을 찾습니다 | 이미예 장편소설", "author": "이미예", "publisher": "팩토리나인", "genre": ["소설", "판타지소설", "장르소설", "한국소설"], "topic": ["공감", "위로", "판타지", "민원", "한국소설", "판타지소설", "힐링", "페니"], "price": "13,800", "story": "100만 독자를 사로잡은 《달러구트 꿈 백화점》, 그 두 번째 이야기어느덧 페니가 달러구트 꿈 백화점에서 일한 지도 1년이 넘었다.재고가 부족한 꿈을 관리하고, 꿈값 창고에서 감정으로 가득 찬 병을 옮기고, 프런트의 수많은 눈꺼풀 저울을 관리하는 일에 능숙해진 페니는 자신감이 넘친다. 게다가 꿈 산업 종사자로 인정을 받아야만 드나들 수 있는 ‘컴퍼니 구역’에도 가게 된 페니는 기쁜 마음을 감출 수 없다.하지만 그곳에서 페니를 기다리고 있는 건, 꿈에 대한 불만을 털어놓는 사람들로 가득한 ‘민원관리국’이었다. 설상가상 달러구트는 아주 심각한 민원 하나를 통째로 페니에게 맡기는데…“왜 저에게서 꿈까지 뺏어가려고 하시나요?”라는 알쏭달쏭한 민원을 남기고 발길을 끊어버린 792번 단골손님.페니는 과연 달러구트 꿈 백화점의 오랜 단골손님을 되찾을 수 있을까?", "img": "http://image.kyobobook.co.kr/images/book/large/729/l9791165343729.jpg", "id": 1},
      {"title": "달러구트 꿈 백화점 주문하신 꿈은 매진입니다 | 이미예 장편소설", "author": "이미예", "publisher": "팩토리나인", "genre": ["소설", "판타지소설", "장르소설", "한국소설"], "topic": ["공감", "청소년소설", "별점", "판타지", "위로", "한국소설", "판타지소설", "힐링"], "price": "13,800", "story": "잠들어야만 입장 가능한 꿈 백화점에서 일어나는 비밀스럽고도 기묘하며 가슴 뭉클한 판타지 소설", "img": "http://image.kyobobook.co.kr/images/book/large/909/l9791165341909.jpg", "id": 2 },
      {"title": "불편한 편의점 김호연 장편소설", "author": "김호연", "publisher": "나무옆의자", "genre": ["한국소설일반", "소설", "한국소설"], "topic": ["인경", "여사", "사내", "위로", "소설", "독고"], "price": "14,000", "story": "2013년 세계문학상 우수상 수상작 『망원동 브라더스』로 데뷔한 후 일상적 현실을 위트 있게 그린 경쾌한 작품과 인간의 내밀한 욕망을 기발한 상상력으로 풀어낸 스릴러 장르를 오가며 독자적인 작품 세계를 쌓아올린 작가 김호연. 그의 다섯 번째 장편소설 『불편한 편의점』이 나무옆의자에서 출간되었다. 『불편한 편의점』은 청파동 골목 모퉁이에 자리 잡은 작은 편의점을 무대로 힘겨운 시대를 살아가는 우리 이웃들의 삶의 속내와 희로애락을 따뜻하고 유머러스하게 담아낸 작품이다. 『망원동 브라더스』에서 망원동이라는 공간의 체험적 지리지를 잘 활용해 유쾌한 재미와 공감을 이끌어냈듯 이번에는 서울의 오래된 동네 청파동에 대한 공감각을 생생하게 포착해 또 하나의 흥미진진한 ‘동네 이야기’를 탄생시켰다.서울역에서 노숙인 생활을 하던 독고라는 남자가 어느 날 70대 여성의 지갑을 주워준 인연으로 그녀가 운영하는 편의점에서 야간 알바를 하면서 이야기가 시작된다. 덩치가 곰 같은 이 사내는 알코올성 치매로 과거를 기억하지 못하는 데다 말도 어눌하고 행동도 굼떠 과연 손님을 제대로 상대할 수 있을까 의구심을 갖게 하는데 웬걸, 의외로 그는 일을 꽤 잘해낼 뿐 아니라 주변 사람들을 묘하게 사로잡으면서 편의점의 밤을 지키는 든든한 일꾼이 되어간다.현실감 넘치는 캐릭터와 그들 간의 상호작용을 점입가경으로 형상화하는 데 일가견이 있는 작가의 작품답게 이 소설에서도 독특한 개성과 사연을 지닌 인물들이 차례로 등장해 서로 티격태격하며 별난 관계를 형성해간다. 고등학교에서 역사를 가르치다 정년퇴임하여 매사에 교사 본능이 발동하는 편의점 사장 염 여사를 필두로 20대 취준생 알바 시현, 50대 생계형 알바 오 여사, 매일 밤 야외 테이블에서 참참참(참깨라면, 참치김밥, 참이슬) 세트로 혼술을 하며 하루의 스트레스를 푸는 회사원 경만, 마지막이라는 각오로 청파동에 글을 쓰러 들어온 30대 희곡작가 인경, 호시탐탐 편의점을 팔아치울 기회를 엿보는 염 여사의 아들 민식, 민식의 의뢰를 받아 독고의 뒤를 캐는 사설탐정 곽이 그들이다. 제각기 녹록지 않은 인생의 무게와 현실적 문제를 안고 있는 이들은 각자의 시선으로 독고를 관찰하는데, 그 과정에서 발생하는 오해와 대립, 충돌과 반전, 이해와 공감은 자주 폭소를 자아내고 어느 순간 울컥 눈시울이 붉어지게 한다. 그렇게 골목길의 작은 편의점은 불편하기 짝이 없는 곳이었다가 고단한 삶을 위로하고 웃음을 나누는 특별한 공간이 된다.", "img": "http://image.kyobobook.co.kr/images/book/large/188/l9791161571188.jpg", "id": 3 }
    ],
    MainPageInfo: {
      modal: false
      
    },
    ModalBookInfo: {
      
    }
  },
  getters: {
    getSignupCheck(state) {
      return state.signupCheck;
    },
    getSignupForm(state) {
      return state.signUpForm
    },
    getBookPropensity(state) {
      return state.bookPropensity;
    },
    getMainPageInfo(state) {
      return state.MainPageInfo;
    },
    getModalBookInfo(state) {
      return state.ModalBookInfo;
    },
  },
  mutations: {
    // 회원가입 검사
    SIGNUP_CHECK(state, result) {
      if (result.type != 'password') {
        state.signupCheck[result.type].duplicate.flag = result.duplicate;
      }
      state.signUpForm[result.type] = result.content
      state.signupCheck[result.type].condition.flag = result.condition;
      state.signupCheck[result.type].flag = result.duplicate && result.condition;
    },
    PASSWORDCHECK(state, flag) {
      state.signupCheck.password.check = flag;
    },
    SETPROPENSITY(state, propensity) {
      state.signUpForm.mbti[propensity["index"]] = propensity["panel"];
    },
    SETMAINMODAL(state, flag) {
      state.MainPageInfo.modal = flag;
    },
    SETBOOKPROPENSITY(state, contents) {
      state.bookPropensity = contents;
    }
  },
  actions: {
    // 회원가입시 ID, Nickname, Password 검사
    signupCheck({ commit }, req) {
      var result = {
        'type': req.val,
        'condition': req.condition,
        'duplicate': false,
        'content' : req.content
      };
      if (req.val !== 'password' && req.condition) {
        http
          .get('/users/valDuplicated/' + req.val + '/' + req.content)
          .then(({ data }) => {
            result.duplicate = data.result;
            commit("SIGNUP_CHECK", result);
          })
          .catch(() => {
            console.log("500에러");
          })
      } else if (req.val === 'password') {
        result.duplicate = true;
        commit("SIGNUP_CHECK", result);
      }
    },
    // 비밀번호 확인 검사
    passwordCheck({ commit }, flag) {
      commit("PASSWORDCHECK", flag);
    },
    // MBTI 입력
    setPropensity({ commit }, propensity) {
      commit("SETPROPENSITY", propensity)
    },
    // 회원 가입
    signUp(signupForm) {
      http
        .post('/users/create', signupForm)
        .then(() => {
          alert("회원 가입 성공");
          return true;
        })
        .catch(() => {
          alert("회원 가입 실패");
          return false;
        })
    },
    // 모달 창 유무
    mainModalFlag({ commit }, flag) {
      commit("SETMAINMODAL", flag);
    },
    setBookPropensity({ commit }) {
      http
        .get('/users/profile/tendency')
        .then(({ data}) => {
          commit("SETBOOKPROPENSITY", data.contents);
        })
        .catch(() => {
          
        })
    }
  }
});
