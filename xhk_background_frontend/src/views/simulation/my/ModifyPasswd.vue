<template>
  <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
    <el-form-item label="旧密码" prop="oldPass">
      <el-input type="password" v-model="ruleForm.oldPass"></el-input>
    </el-form-item>
    <el-form-item label="密码" prop="pass">
      <el-input type="password" v-model="ruleForm.pass" autocomplete="off"></el-input>
    </el-form-item>
    <el-form-item label="确认密码" prop="checkPass">
      <el-input type="password" v-model="ruleForm.checkPass" autocomplete="off"></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
export default {
  name: "ModifyPasswd",
  data() {
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入新密码'));
      } else {
        if (this.ruleForm.checkPass !== '') {
          this.$refs.ruleForm.validateField('checkPass');
        }
        callback();
      }
    };
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.ruleForm.pass) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };
    return {
      ruleForm: {
        pass: '',
        checkPass: '',
        oldPass: ''
      },
      rules: {
        pass: [
          { validator: validatePass, trigger: 'blur' }
        ],
        checkPass: [
          { validator: validatePass2, trigger: 'blur' }
        ],
      }
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$axios.put('http://localhost:8081/users/modified_user_pass/' + window.localStorage.getItem("uid"), {
            pass: this.ruleForm.pass,
            checkPass: this.ruleForm.checkPass,
            oldPass: this.ruleForm.oldPass
          }, {
            headers: {
              'Authorization': window.localStorage.getItem("token"),
            },
          }).then((response) => {
            if (response.data.state === 200) {
              this.$router.push('/info');
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
          });
        } else {
          return false;
        }
      })
    }
  },
}
</script>

<style scoped>

</style>
