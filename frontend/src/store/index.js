import Vue from "vue";
import Vuex from "vuex";
import http from "@/utils/http.js";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    // 회원가입시 ID, Nickname, Password State
    signupCheck: {
      id: {
        flag : false,
        condition: {
          flag : false,
          msg : "ID는 4자 이상이여야 합니다."
        },
        duplicate : {
          flag : false,
          msg : "중복된 ID가 존재합니다."
        },
      },
      nickname: {
        flag : false,
        condition: {
          flag : false,
          msg : "닉네임은 3자 이상이여야 합니다."
        },
        duplicate : {
          flag : false,
          msg : "중복된 닉네임이 존재합니다."
        },
      },
      password: {
        flag : false,
        check : false,
        condition : {
          flag : false,
          msg : "Password는 6자 이상이여야 합니다."
        }
      }
    },
    signUpForm: {
      id: String,
      nickname : String,
      password: String,
      propensity: [
        "I", "S", "T", "J" 
      ]
    }
  },
  getters: {
    getSignupCheck(state) {
      return state.signupCheck;
    },
    getPropensity(state) {
      return state.signUpForm.propensity;
    }
  },
  mutations: {
    // 회원가입 검사
    SIGNUP_CHECK(state, result) {
      if (result.type != 'password') {
        state.signupCheck[result.type].duplicate.flag = result.duplicate;
      }
      state.signupCheck[result.type].condition.flag = result.condition;
      state.signupCheck[result.type].flag = result.duplicate && result.condition;
    },
    PASSWORDCHECK(state, flag) {
      state.signupCheck.password.check = flag;
    },
    SETPROPENSITY(state, propensity) {
      state.signUpForm.propensity[propensity["index"]] = propensity["panel"];
    }
  },
  actions: {
    // 회원가입시 ID, Nickname, Password 검사
    signupCheck({ commit }, req) {
      var result = {
        type: req.val,
        condition: req.condition,
        duplicate: false
      };
      if (req.val !== 'password' && req.condition) {
        http
          .get('/auth/user/valDuplicated', req)
          .then(({ res }) => {
              result["duplicate"] = res.result;
          })
          .catch(() => {
            console.log("500에러");
          })
      } else if (req.val === 'password') {
        result.duplicate = true;
      }
      commit("SIGNUP_CHECK", result);
    },
    // 비밀번호 확인 검사
    passwordCheck({ commit }, flag) {
      commit("PASSWORDCHECK", flag);
    },
    // MBTI 입력
    setPropensity({ commit }, propensity) {
      commit("SETPROPENSITY", propensity)
    }
  }
});
