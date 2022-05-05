<template>
  <el-row>
    <el-col :span="5" class="headerlogo">
      <div class="grid-content bg-purple">
        <span style="color:#fff">校惠卡后台管理系统</span>
      </div>
    </el-col>
    <el-col :span="7" class="rightsection">
      <div class="grid-content bg-purple-light">
        <span class="el-dropdown-link">欢迎您，{{username}}</span>
        <el-button type="info" round @click="signout" style="margin-left:50px" size="mini">退出登录</el-button>
      </div>
    </el-col>
  </el-row>
</template>

<script>
export default {
  name: "Header",
  data() {
    return {
      username:localStorage.getItem('username'),
    }
  },
  methods: {
    //退出
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
    },
  },
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
.userinfo-inner:hover{
  color: #ffd04b;
}
</style>
