<template lang="html">
    <form class="form-signin" >
        <img class="mb-4" src="https://getbootstrap.com/docs/4.2/assets/brand/bootstrap-solid.svg" alt="" width="72" height="72">
        <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
        <label for="inputEmail" class="sr-only">Email address</label>
        <input type="email" id="inputEmail" class="form-control" placeholder="Email address" required autofocus value="jwlee1728@naver.com">
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" id="inputPassword" class="form-control" placeholder="Password" required value="password1234">
        <div class="checkbox mb-3">
            <label>
                <input type="checkbox" value="remember-me"> Remember me
            </label>
        </div>
        <!-- <router-link to="/todo"><button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button></router-link> -->
        <button v-on:click.prevent="signin" class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
        <p class="mt-5 mb-3 text-muted">&copy; 2017-2018</p>
    </form>
</template>

<script>
import Vue from 'vue'
import BootstrapVue from 'bootstrap-vue'
Vue.use(BootstrapVue)

// javascript import를 할경우 전역에 걸쳐 적용된다
// import 'bootstrap/dist/css/bootstrap.css'
// import 'bootstrap-vue/dist/bootstrap-vue.css'

export default {
  beforeCreate: function () {
    console.log('login.vue before')
    document.getElementsByTagName('body')[0].classList.add('text-center')
  },
  methods: {
    signin () {
      console.log('login.vue signin click')
      this.$router.push('/todo')
    }
  }
}
</script>

<!--아래와 같이 style 태그 혹은 @import 구문을 통해 외부 css를 적용-->
<!-- <style scoped src="bootstrap/dist/css/bootstrap.css"></style> -->
<!-- <style scoped src="bootstrap-vue/dist/bootstrap-vue.css"></style> -->
<style lang="css" scoped>
@import "bootstrap/dist/css/bootstrap.css";
@import "bootstrap-vue/dist/bootstrap-vue.css";
@import "./signin.css";

</style>
