<template>
  <el-menu default-active="2" class="el-menu-vertical-demo" background-color="#fff" text-color="#000" active-text-color="#8aceff">
    <el-menu-item index="2" @click="toPage('/info')">
      <i class="el-icon-user"></i>
      <span slot="title">个人信息</span>
    </el-menu-item>
    <el-menu-item index="1" @click="toPage('/modifyInfo')">
      <i class="el-icon-set-up"></i>
      <span slot="title">修改信息</span>
    </el-menu-item>
    <el-menu-item index="5" @click="toPage('/modifyAvatar')">
      <i class="el-icon-picture-outline-round"></i>
      <span slot="title">修改头像</span>
    </el-menu-item>
    <el-menu-item index="3" @click="toPage('/modifyPasswd')">
      <i class="el-icon-key"></i>
      <span slot="title">修改密码</span>
    </el-menu-item>
    <el-menu-item index="4" @click="toPage('/recharge')">
      <i class="el-icon-shopping-cart-2"></i>
      <span slot="title">券包充值</span>
    </el-menu-item>
  </el-menu>
</template>

<script>
export default {
  name: "MAside",
  methods: {
    toPage(item) {
      this.$router.push(item);
    },
  }
}
</script>

<style scoped>
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: 400px;
  height: 100%;
}
</style>
