<template>
  <el-descriptions title="个人信息" direction="vertical" :column="4" border>
    <el-descriptions-item label="编号">{{uid}}</el-descriptions-item>
    <el-descriptions-item label="用户名">{{username}}</el-descriptions-item>
    <el-descriptions-item label="余额" :span="2">{{balance}}</el-descriptions-item>
    <el-descriptions-item label="电话" :span="2">{{phone}}</el-descriptions-item>
    <el-descriptions-item label="邮箱" :span="2">{{email}}</el-descriptions-item>
    <el-descriptions-item label="性别" :span="2">{{gender}}</el-descriptions-item>
    <el-descriptions-item label="">
      <el-tag size="small">{{role}}</el-tag>
    </el-descriptions-item>
  </el-descriptions>
</template>

<script>
export default {
  name: "Info",
  data() {
    return {
      uid: '',
      username: '',
      balance: '',
      phone: '',
      email: '',
      gender: '',
      role: '',
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
          this.uid = user.uid;
          this.username = user.username;
          this.balance = user.balance;
          this.phone = user.phone;
          this.email = user.email;
          this.gender = user.gender === 1 ? '男' : '女';
          this.role = user.role;
        } else {
          this.$message({
            showClose: true,
            message: response.data.message,
            type: 'error'
          });
        }
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
