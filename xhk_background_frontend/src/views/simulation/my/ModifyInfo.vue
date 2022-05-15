<template>
  <el-form ref="form" :model="form" label-width="80px">
    <el-form-item label="用户名">
      <el-input v-model="form.username" placeholder="请输入用户名"></el-input>
    </el-form-item>
    <el-form-item label="电话">
      <el-input v-model="form.phone" placeholder="请输入电话"></el-input>
    </el-form-item>
    <el-form-item label="邮箱">
      <el-input v-model="form.email" placeholder="请输入邮箱"></el-input>
    </el-form-item>
    <el-form-item label="性别">
      <el-select v-model="form.gender" placeholder="请选择性别">
        <el-option label="男" value="1"></el-option>
        <el-option label="女" value="0"></el-option>
      </el-select>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="onSubmit">保存修改</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
export default {
  name: "ModifyInfo",
  data() {
    return {
      form: {
        uid: '',
        username: '',
        phone: '',
        email: '',
        gender: '',
      }
    }
  },
  methods: {
    showData() {
      this.$axios.get('http://localhost:8081/users/find_user_by_uid/' + window.localStorage.getItem('uid'), {
        headers: {
          'Authorization': window.localStorage.getItem("token"),
        },
      }).then((response) => {
        if (response.data.state === 200) {
          let user = response.data.data;
          this.form.uid = user.uid;
          this.form.username = user.username;
          this.form.phone = user.phone;
          this.form.email = user.email;
          this.form.gender = user.gender === 1 ? '男' : '女';
        } else {
          this.$message({
            showClose: true,
            message: response.data.message,
            type: 'error'
          });
        }
      })
    },
    onSubmit() {
      console.log(this.form.gender)
      this.$axios.put('http://localhost:8081/users/modified_user_by_uid', {
        'uid':this.form.uid,
        'username':this.form.username,
        'phone':this.form.phone,
        'email':this.form.email,
        'gender':(this.form.gender === '女' || this.form.gender === '0') ?  0 : 1,
      }, {
        headers: {
          'Authorization': window.localStorage.getItem("token"),
        },
      }).then((response) => {
        if (response.data.state === 200) {
          this.$router.push('/info')
          location.reload();
          this.$message({
            showClose: true,
            message: "修改成功",
            type: 'success'
          });
          this.drawer1 = false;
        } else {
          this.$message({
            showClose: true,
            message: response.data.message,
            type: 'error'
          });
        }
        this.showData()
      })
    },
  },
  created() {
    this.showData();
  }
}
</script>

<style scoped>

</style>
