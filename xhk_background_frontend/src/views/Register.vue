<template>
  <div class="login-wrap">
    <el-form
      class="login-form"
      label-position="top"
      label-width="80px"
      :model="formdata"
    >
      <h2>用户注册</h2>
      <el-form-item label="用户名">
        <el-input v-model="formdata.username"></el-input>
      </el-form-item>
      <el-form-item label="密码">
        <el-input type="password" v-model="formdata.password"></el-input>
      </el-form-item>
      <el-form-item label="电话">
        <el-input v-model="formdata.phone"></el-input>
      </el-form-item>
      <el-button class="login-btn" type="primary" @click="handleLodin()">登录</el-button>
      <p style="font-size:10px;font-weight: lighter" @click="turnPage('/')">已有账号？点击这里登录</p>
    </el-form>
  </div>
</template>

<script>
export default {
  name: "Register",
  data() {
    return {
      formdata: {
        username: "",
        password: "",
        phone: '',
      },
    };
  },
  methods: {
    turnPage(page) {
      this.$router.push(page);
    },
    handleLodin(){
      if(this.formdata.username !== '' && this.formdata.password !== ''){
        this.$axios.post('http://localhost:8081/users/save_user', {
          username:this.formdata.username,
          password:this.formdata.password,
          phone: this.formdata.phone,
        }).then((response) => {
          console.log(response)
          if (response.data.state === '200') {
            this.$router.push({path:'/'})
            this.$message({
              showClose: true,
              message: '注册成功',
              type: 'success'
            });
          } else {
            this.$message({
              showClose: true,
              message: response.data.message,
              type: 'error'
            });
          }
        })
      }else {
        this.$message({
          showClose: true,
          message: '个人信息不能为空',
          type: 'error'
        });
      }
    }
  }
}
</script>

<style scoped>
.login-wrap {
  height: 100vh;
  background-color: #99a9bf;
  display: flex;
  justify-content: center;
  align-items: center;
}
.login-form {
  width: 400px;
  background: #fff;
  border-radius: 5px;
  padding: 30px;
}
.login-btn {
  width: 100%;
}
</style>
