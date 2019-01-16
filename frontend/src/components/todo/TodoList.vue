<template lang="html">
  <div>
      <section>
          <transition-group name="list" tag="ul">
              <li v-for="todo in propsTodoList" v-bind:key="todo" class="shadow">
                  <i class="checkBtn fa fa-check" aria-hidden="true"></i>{{todo}}
                  <span class="removeBtn" type="button" v-on:click="removeTodo(todo)">
                      <i class="fa fa-trash-o" arai-hidden="true"></i>
                  </span>
              </li>
          </transition-group>
      </section>
  </div>
</template>

<script>
export default {
  props: ['propsTodoList'],
  methods: {
    removeTodo: function (todo) {
      console.log('todoList.vue removeTodo : ', todo)
      this.$emit('removeTodo', todo)
    }
  }
}
</script>

<style lang="css" scoped>
ul {
    list-style-type : none;
    padding-left : 0px;
    margin-top: 0;
    text-align: left;
}

li {
    display: flex;
    min-height: 50px;
    height: 50px;
    line-height: 50px;
    margin: 0.5rem 0;
    padding: 0 0.9rem;
    background: white;
    border-radius: 5px;
}

.checkBtn {
    line-height: 45px;
    color: #62acde;
    margin-right: 5px;
}

.removeBtn {
    margin-left: auto;
    color: #de4343;
}

.list-item{
    display: inline-block;
    margin-right: 10px;
}

.list-move{
    transition: transform 1s;
}

.list-enter-active, .list-leave-active{
    transition: all 1s;
}

.list-enter, .list-leave-to{
    opacity: 0;
    transform: translateY(30px);
}
</style>
