import Vue from "vue";
import Vuex from "vuex";
import http from "@/utils/http.js";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    duplicateCheck: {
      id: false,
      nickname: false
    }
  },
  mutations: {
    // ID 중복 유무
    DUPLICATE_CHECK_ID(state, result) {
      state.duplicateCheck.id = result;
    },
    // Nickname 중복 유무
    DUPLICATE_CHECK_NAME(state, result) {
      state.duplicateCheck.nickname = result;
    }
  },
  actions: {
    // 중복 체크
    duplicateCheck({ commit }, data) {
        http
          .get('/auth/user/valDuplicated', data)
          .then(({ res }) => {
            if (data.val === 'id') { // ID 중복 유무
              commit('DUPLICATE_CHECK_ID', res.result); 
            } else { // // ID 중복 유무
              commit('DUPLICATE_CHECK_NAME', res.result);
            }
          })
          .catch(() => {
            console.log("500에러");
          })
      }
    }
});
