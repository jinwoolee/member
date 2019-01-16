<template lang="html">
  <div class="inputBox shadow">
      <input type="text" ref="todoInput" v-model="todoInput" placeholder="input new todo" v-on:keyup.enter="addTodo" >
      <span class="addContainer" >
          <i class="addBtn fa fa-plus" aria-hidden="true" v-on:click="addTodo"></i>
      </span>

      <modal v-if="showModal">
          <h3 slot="header">경고</h3>
          <span slot="footer">
              할일을 입력하세요
              <i class="closeModalBtn fa fa-times" aria-hidden="true"></i>
          </span>
      </modal>
  </div>
</template>

<script>
import modal from '../common/Modal.vue'
import {mapState} from 'vuex'

export default {

  // vue 파일에서 data 속성은 function 이어야 한다. (not object)
  // object 형태로 할경우 해당 속성을 모든 spa에서 공유하게됨 --> 문제 발생
  data: function () {
    return {
      todoInput: '',
      showModal: false
    }
  },

  props: [
    'todoInputFocus'
  ],
  watch: {
    // 상위 컴포넌트의 속성 todoInputFocus 가 변경될때 할일 입력 input에 포커스 전달
    todoInputFocus: function () {
      this.$refs.todoInput.focus()
    }
  },

  /* computed: mapState([
    "count"
    ]), */

  /* 위와 동일
    data(){
      return {
          newTodoItem : ''
      }
  } */

  // 안되는 케이스
  // data : {
  //     newTodoItem : 'test'
  // }
  mounted: function () {
    this.$refs.todoInput.focus()
  },
  /* watch :{
      todoInput : function(){
          if(this.todoInput == "")
            this.$refs.todoInput.focus();
      }
  }, */
  methods: {
    addTodo: function () {
      if (this.todoInput != '') {
        this.$emit('parentAddTodo', this.todoInput)
        this.todoInput = ''
        this.$refs.todoInput.focus()
      }
    },
    clearInput: function () {

    }
  },
  components: {
    modal: modal
  }
}
</script>

<style lang="css" scoped>
input:focus{
    outline: none;
}

.inputBox{
    background: white;
    height: 50px;
    line-height: 50px;
    border-radius: 5px;
}

.inputBox input{
    border-style: none;
    font-size: 0.9rem;
}

.addContainer{
    float: right;
    background: linear-gradient(to right, #6478FB, #8763FB);
    display : inline-block;
    width: 3rem;
    border-radius: 0 5px 5px 0;
}

.addBtn {
    color: white;
    vertical-align: middle;
}
</style>
