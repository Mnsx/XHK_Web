<template>
  <el-form :model="numberValidateForm" ref="numberValidateForm" label-width="100px" class="demo-ruleForm">
    <el-form-item
      label="注册码"
      prop="code"
      :rules="[
      { required: true, message: '注册码不能为空'},
    ]"
    >
      <el-input  type="text" v-model="numberValidateForm.code" autocomplete="off"></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="submitForm('numberValidateForm')">提交</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
export default {
  name: "Recharge",
  data() {
    return {
      numberValidateForm: {
        code: ''
      }
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$axios.delete('http://localhost:8081/codes/delete_by_code/' + this.numberValidateForm.code + "/" + window.localStorage.getItem("uid"), {
            headers: {
              'Authorization': window.localStorage.getItem("token"),
            },
          }).then((response) => {
            console.log(response)
            if (response.data.state == 200) {
              this.$router.push("/info");
              location.reload();
              this.$message({
                showClose: true,
                message: '使用成功',
                type: 'success'
              })
            } else {
              this.$message({
                showClose: true,
                message: response.data.message,
                type: 'error'
              })
            }
          })
        } else {
          return false;
        }
      });
    },
  }
}
</script>

<style scoped>

</style>
