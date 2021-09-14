import Vue from "vue";
import Vuex from "vuex";
import http from "@/utils/http.js";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    // 회원가입시 ID, Nickname, Password State
    signupCheck: {
      id: {
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
        condition: {
          flag : false,
          msg : "Password는 6자 이상이여야 합니다."
        }
      }
    }
  },
  getters: {
    getSignupCheck(state) {
      return state.signupCheck;
    }
  },
  mutations: {
    // 회원가입 검사
    SIGNUP_CHECK(state, result) {
      if (result.type != 'password') {
        state.signupCheck[result.type].duplicate.flag = result.duplicate;
      }
      state.signupCheck[result.type].condition.flag = result.condition;
    }
  },
  actions: {
    // 회원가입시 ID, Nickname, Password 검사
    signupCheck({ commit }, req) {
      var result = {
        type: req.val,
        condition: req.condition
      };
      if (req.val !== 'password') {
        http
          .get('/auth/user/valDuplicated', req)
          .then(({ res }) => {
              result["duplicate"] = res.result;
          })
          .catch(() => {
            console.log("500에러");
          })
      }
      commit("SIGNUP_CHECK", result);
    }
  }
});
