import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const state = {
  count: 0
}

const mutations = {
  increment (state) {
    console.log('mutations increment')
    state.count++
  },
  decrement (state) {
    state.count--
  }
}

const actions = {
  increment: ({ commit }) => {
    commit('increment')
  },
  decrement: ({ commit }) => commit('decrement')
}

export default new Vuex.Store({
  state,
  mutations,
  actions
})
