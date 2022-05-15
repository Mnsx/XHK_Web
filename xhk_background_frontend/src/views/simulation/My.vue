<template>
  <div class="page">
    <el-container class="container">
      <el-header class="header">
        <MHeader></MHeader>
      </el-header>
      <el-container>
        <el-aside class="aside" width="auto">
          <MAside></MAside>
        </el-aside>
        <el-main class="main">
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import MHeader from "../../components/my/MHeader";
import MAside from "../../components/my/MAside";
export default {
  name: "My",
  components: {
    MHeader,
    MAside
  }
}
</script>

<style scoped>
.header {
  width: 50vw;
  margin-bottom: 50px;
}
.page{
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin-top: 20px;
}
</style>
