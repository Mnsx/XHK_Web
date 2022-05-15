<template>
  <el-menu default-active="2" :default-openeds="opened" class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose" :collapse="isCollapse" background-color="#545c64" text-color="#fff" active-text-color="#ffd04b">
    <el-menu-item index="2" @click="toHome()">
      <i class="el-icon-menu"></i>
      <span slot="title">首页</span>
    </el-menu-item>
    <el-submenu index="1">
      <template slot="title">
        <i class="el-icon-location"></i>
        <span slot="title">数据管理</span>
      </template>
      <el-menu-item-group>
        <el-menu-item v-for="item in menu" :index="item.path" @click="toPage(item.name)" :key="item.path">{{item.title}}</el-menu-item>
      </el-menu-item-group>
    </el-submenu>
    <el-menu-item index="3" @click="toPage('/advertising')">
      <i class="el-icon-upload"></i>
      <span slot="title">图片管理</span>
    </el-menu-item>
    <el-menu-item index="4" @click="toPage('/first')">
      <i class="el-icon-s-help"></i>
      <span slot="title">功能模拟</span>
    </el-menu-item>
  </el-menu>
</template>

<script>
export default {
  name: "Aside",
  data() {
    return {
      isCollapse: false,
      opened: ["1"],
      menu: [
        {
          title: '用户管理',
          path: '/user',
          name: '/user'
        },
        {
          title: '商家管理',
          path: '/store',
          name: '/store'
        },
/*        {
          title: '商品管理',
          path: '/product',
          name: '/product'
        },*/
        {
          title: '券包管理',
          path: '/package',
          name: '/package'
        },
        {
          title: '优惠券管理',
          path: '/ticket',
          name: '/ticket'
        },
        {
          title: '注册码管理',
          path: '/code',
          name: '/code'
        },
      ]
    };
  },
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
    toPage(item) {
      this.$router.push(item);
    },
    toHome() {
      this.$router.push("/home");
    }
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
