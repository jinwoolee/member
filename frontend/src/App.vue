<template>
<div id="app">
  <transition name="slide-left" mode="out-in">
    <router-view/>
  </transition>
</div>
</template>

<script>
import Vue from 'vue'
import BootstrapVue from 'bootstrap-vue'
Vue.use(BootstrapVue);

export default {}

</script>

<style>
body {
  /* display: -ms-flexbox;
  display: flex;
  -ms-flex-align: center;
  align-items: center; */
  padding-top: 40px;
  padding-bottom: 40px;
  text-align: center;
  /* background-color: #f5f5f5; */
  background-color: #F6F6F8;
}

input[type=text] {
  border-style: groove;
  width: 200px;
}

button {
  border-style: groove;
}

.shadow {
  box-shadow: 5px 10px 1px rgba(0, 0, 0, 0.03)
}


/*slide transition*/
.slide-left-enter-active,
.slide-left-leave-active,
.slide-right-enter-active,
.slide-right-leave-active {
  transition-duration: 0.5s;
  transition-property: height, opacity, transform;
  transition-timing-function: cubic-bezier(0.55, 0, 0.1, 1);
  overflow: hidden;
}

.slide-left-enter,
.slide-right-leave-active {
  opacity: 0;
  transform: translate(2em, 0);
}

.slide-left-leave-active,
.slide-right-enter {
  opacity: 0;
  transform: translate(-2em, 0);
}
/*slide transition*/
</style>
