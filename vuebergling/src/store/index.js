import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    userInfo: {}
  },
  mutations: {
    SET_USERINFO: (state, userInfo) => {
      state.userInfo = userInfo;
      sessionStorage.setItem("user", JSON.stringify(userInfo));
    }
  },
  getters: {
    getUserInfo: state => {
      return state.userInfo;
    }
  },
  actions: {
  },
  modules: {
  }
})
