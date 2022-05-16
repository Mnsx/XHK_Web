<template>
  <el-row>
    <el-col :span="5" class="headerlogo">
      <div class="grid-content bg-purple">
        <span style="color:#fff">校惠卡</span>
      </div>
    </el-col>
    <el-col :span="7" class="rightsection">
      <div class="grid-content bg-purple-light">
        <el-menu
          :default-active="'1'"
          class="el-menu-demo"
          mode="horizontal"
          background-color="#212121"
          text-color="#fff"
          active-text-color="#ffd04b">
          <el-menu-item index="1" @click="toPage('/first')">首页</el-menu-item>
          <el-menu-item index="2" @click="toPage('/message')">消息</el-menu-item>
          <el-menu-item index="3" @click="toPage('/info')">我的</el-menu-item>
          <el-submenu index="4">
            <template slot="title">其他</template>
            <el-menu-item index="2-1" @click="toPage('/home')" :disabled="disabled">后台</el-menu-item>
            <el-menu-item index="2-2" @click="signout">退出</el-menu-item>
          </el-submenu>
        </el-menu>
      </div>
    </el-col>
  </el-row>
</template>

<script>
export default {
  name: "SHeader",
  data() {
    return {
      disabled: true,
    }
  },
  created() {
    if (window.localStorage.getItem("role") === 'root') {
      this.disabled = false;
    }
  },
  methods: {
    toPage(item) {
      this.$router.push(item);
    },
    signout() {
      this.$confirm("退出登录, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        localStorage.removeItem('token');
        localStorage.removeItem('uid');
        localStorage.removeItem('username');
        this.$router.push({ path: "/" });
      });
    }
  }
}
</script>

<style scoped>
.headerlogo {
  line-height: 60px;
  font-weight: bold;
}
.rightsection {
  margin-left: 50%;

  text-align: center;
  line-height: 60px;
  color: #fff;
}
</style>
