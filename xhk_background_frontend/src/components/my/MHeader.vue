<template>
  <div class="card" style="height:100vw;">
    <el-card class="box-card" shadow="nerver">
      <el-row>
        <el-col :span="2">
          <el-avatar :src="avatar" shape="square" :size="50"></el-avatar>
        </el-col>
        <el-col :span="5">
          <div class="text item" style="font-size: 20px;">
            {{username}}
          </div>
          <div class="text item" style="font-size: 12px;">
            余额：{{balance}}
          </div>
        </el-col>
      </el-row>
    </el-card>
  </div>
</template>

<script>
export default {
  name: "MHeader",
  data() {
    return {
      avatar: '',
      username: '',
      balance: '',
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
          this.avatar = response.data.data.avatar;
          window.localStorage.setItem("avatar", this.avatar);
          this.username = response.data.data.username;
          this.balance= response.data.data.balance;
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
.text {

  font-weight: bold;
}

.item {
  padding: 2px 0;
}
</style>
