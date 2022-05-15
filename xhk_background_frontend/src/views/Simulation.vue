<template>
  <el-container class="container">
    <el-header class="header">
      <SHeader/>
    </el-header>
    <el-main class="main">
      <router-view/>
    </el-main>
  </el-container>
</template>

<script>
import SHeader from "../components/simulation/SHeader";
export default {
  name: "Simulation",
  components: {SHeader}
}
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
