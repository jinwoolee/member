import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/login/login.vue'
import Todo from '@/components/todo/Todo.vue'

Vue.use(Router)

export default new Router({
  //mode : 'history',
  routes: [{
      path: '/',
      name: 'login',
      component: Login
    },
    {
      path: '/todo',
      name: 'todo',
      component: Todo
    }]
})
