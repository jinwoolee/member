<template>
<div>
  <todoHeader></todoHeader>
  <todoInput v-bind:todoInputFocus="todoInputFocus" v-on:parentAddTodo="addTodo"></todoInput>
  <todoList v-bind:propsTodoList="todoList" v-on:removeTodo="removeTodo"></todoList>
  <todoFooter v-on:clearAll="clearAll"></todoFooter>
</div>
</template>

<script>
import todoHeader from './todoHeader.vue'
import todoInput from './todoInput.vue'
import todoList from './todoList.vue'
import todoFooter from './todoFooter.vue'
import {
  mapGetters,
  mapActions,
  mapState
} from 'vuex'

export default {
  name: 'App',
  components: {
    'todoHeader': todoHeader,
    'todoInput': todoInput,
    'todoList': todoList,
    'todoFooter': todoFooter
  },

  data: function () {
    return {
      todoList: [],
      count: this.$store.state.count,
      todoInputFocus: 0
    }
  },

  // computed: mapState([
  //   "count"
  //   ]),

  methods: {
    ...mapActions([
      'increment',
      'decrement'
    ]),
    addTodo: function (todo) {
      localStorage.setItem(todo, todo)
      this.todoList.push(todo)
      this.$store.dispatch('increment')
    },

    removeTodo: function (todo) {
      console.log('App.vue removeTodo : ', todo)

      for (var i in this.todoList) {
        var t = this.todoList[i]
        if (t == todo) {
          localStorage.removeItem(todo)
          this.todoList.splice(i, 1)
        }
      }
    },

    clearAll: function () {
      localStorage.clear()
      this.todoList = []
      this.todoInputFocus++
      // this.$refs.todoInput.focus();
    }
  },

  created: function () {
    // 데이터 초기화
    for (var i = 0; i < localStorage.length; i++) {
      var value = localStorage.key(i)
      if (value.indexOf('log') < 0) { this.todoList.push(value) }
    }
  }
}
</script>

<style>
input {
  padding: 0;
}
</style>
