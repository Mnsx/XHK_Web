<template>
  <el-container class="container">
    <el-header class="header">
      <Header></Header>
    </el-header>
    <el-container>
      <el-aside class="aside" width="auto">
        <Aside></Aside>
      </el-aside>
      <el-main class="main">
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import Aside from '../components/Aside'
import Header from '../components/Header'
export default {
  name: "Home",
  components: {
    Aside,
    Header,
  }
};
</script>
<style scoped>
.container {
  height: 100vh;
  font-size: 15px;
}
.main {
  color: #212121;
}
.header {
  background: #212121;
  color: #fff;
}
</style>
