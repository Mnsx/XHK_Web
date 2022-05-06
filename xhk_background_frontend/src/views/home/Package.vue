<template>
  <div>
    <!-- 面包屑 -->
    <el-breadcrumb separator-class="el-icon-arrow-right" style="margin-bottom:20px">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>数据管理</el-breadcrumb-item>
      <el-breadcrumb-item>券包管理</el-breadcrumb-item>
    </el-breadcrumb>

    <!-- 头部 -->
    <el-row :gutter="20">
      <el-col :span="12">
        <el-button type="primary" @click="drawer2 = true" plain>新建</el-button>
      </el-col>
      <el-col :span="6">
        <el-input
          placeholder="关键字搜索"
          prefix-icon="el-icon-search"
          v-model="searchTest">
        </el-input>
      </el-col>
      <el-col :span="6">
        <el-button @click="showData" icon="el-icon-search" circle></el-button>
      </el-col>
    </el-row>
    <div class="demo-input-suffix">
    </div>

    <!-- 主体 -->
    <el-table
      :data="tableData"
      border
      style="width: 100%">
      <el-table-column
        fixed
        prop="uid"
        label="编号"
        width="150">
      </el-table-column>
      <el-table-column
        prop="username"
        label="用户名"
        width="120">
      </el-table-column>
      <el-table-column
        prop="code"
        label="密钥"
        width="500">
      </el-table-column>
      <el-table-column
        prop="balance"
        label="余额"
        width="120">
      </el-table-column>
      <el-table-column
        prop="phone"
        label="电话"
        width="300">
      </el-table-column>
      <el-table-column
        prop="email"
        label="邮箱"
        width="350">
      </el-table-column>
      <el-table-column
        prop="gender"
        label="性别"
        width="120">
      </el-table-column>
      <el-table-column
        prop="avatar"
        label="头像路径"
        width="350">
      </el-table-column>
      <el-table-column
        prop="deleted"
        label="状态"
        width="120">
      </el-table-column>
      <el-table-column
        prop="createdUser"
        label="创建人"
        width="120">
      </el-table-column>
      <el-table-column
        prop="createdTime"
        label="创建时间"
        width="350">
      </el-table-column>
      <el-table-column
        prop="modifiedUser"
        label="修改人"
        width="120">
      </el-table-column>
      <el-table-column
        prop="modifiedTime"
        label="修改时间"
        width="350">
      </el-table-column>
      <el-table-column
        prop="role"
        label="角色"
        width="120">
      </el-table-column>
      <el-table-column
        fixed="right"
        label="操作"
        width="150">
        <template slot-scope="scope">
          <el-button @click="handleOpen(scope.row.uid)" type="text" size="small">修改</el-button>
          <el-button type="text" @click="handleDelete(scope.row.uid)" size="small">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 尾部 -->
    <el-pagination
      background
      layout="prev, pager, next"
      :page-size='pageSize'
      @current-change="handleCurrentChange"
      :current-page="curPage"
      :total="total" style="margin-top: 10px;">
    </el-pagination>

    <!-- 抽屉 -->
    <el-drawer
      title="用户数据——修改"
      :visible.sync="drawer1"
      :direction="direction"
      :before-close="handleClose">
      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="用户名">
          <el-input v-model="form.username" placeholder="请输入用户名"></el-input>
        </el-form-item>
        <el-form-item label="余额">
          <el-input v-model="form.balance" placeholder="请输入余额"></el-input>
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
        <el-form-item label="图片路径">
          <el-input v-model="form.avatar" placeholder="请输入图片路径"></el-input>
        </el-form-item>
        <el-form-item label="用户状态">
          <el-select v-model="form.deleted" placeholder="请选择用户状态">
            <el-option label="注销" value="1"></el-option>
            <el-option label="正常" value="0"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">保存修改</el-button>
        </el-form-item>
      </el-form>
    </el-drawer>
    <el-drawer
      title="用户数据——新增"
      :visible.sync="drawer2"
      :direction="direction"
      :before-close="handleClose">
      <el-form ref="form" :model="newForm" label-width="80px">
        <el-form-item label="用户名">
          <el-input v-model="newForm.username" placeholder="请输入用户名"></el-input>
        </el-form-item>
        <el-form-item label="密码">
          <el-input v-model="newForm.password" placeholder="请输入密码"></el-input>
        </el-form-item>
        <el-form-item label="电话">
          <el-input v-model="newForm.phone" placeholder="请输入电话"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleNew">新增用户</el-button>
        </el-form-item>
      </el-form>
    </el-drawer>
  </div>
</template>

<script>
export default {
  name: "Package",
  methods: {
    handleNew() {
      this.$axios.post('http://localhost:8081/users/save_user', {
        'username':this.newForm.username,
        'password':this.newForm.password,
        'phone':this.newForm.phone,
      },{
        headers: {
          'Authorization': window.localStorage.getItem("token"),
        }
      }).then((response) => {
        if (response.data.state === '200') {
          this.$message({
            showClose: true,
            message: "新增成功",
            type: 'success'
          });
          this.drawer2 = false;
          this.clearData();
          this.showData();
        }
      })
    },
    handleCurrentChange(val) {
      this.curPage = val;
      this.clearData();
      this.showData();
    },
    handleDelete(uid) {
      this.$confirm("确定删除编号为" + uid + "的数据?")
        .then(_ => {
          this.$axios.delete('http://localhost:8081/users/delete_by_uid/' + uid, {
            headers: {
              'Authorization': window.localStorage.getItem("token"),
            }
          }).then((response) => {
            if (response.data.state === 200) {
              this.$message({
                showClose: true,
                message: "删除成功",
                type: 'success'
              });
              if (this.tableData.length === 0) {
                this.curPage--;
              }
              this.clearData();
              this.showData();
            }
          })
        })
    },
    handleOpen(uid) {
      this.drawer1 = true;
      this.$axios.get('http://localhost:8081/users/find_user_by_uid/' + uid, {
        headers: {
          'Authorization': window.localStorage.getItem("token"),
        }
      }).then((response) => {
        if (response.data.state === 200) {
          this.form.username = response.data.data.username;
          this.form.code = response.data.data.code;
          this.form.balance = response.data.data.balance;
          this.form.phone = response.data.data.phone;
          this.form.email = response.data.data.email;
          this.form.gender = response.data.data.gender;
          this.form.avatar = response.data.data.avatar;
          this.form.deleted = response.data.data.deleted;
          this.form.uid = response.data.data.uid;
        } else {
          this.$message({
            showClose: true,
            message: response.data.message,
            type: 'error'
          });
        }
      })
    },
    handleClose(done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done();
        })
        .catch(_ => {});
    },
    onSubmit() {
      this.$axios.put('http://localhost:8081/users/modified_user_by_uid', {
        'uid':this.form.uid,
        'username':this.form.username,
        'balance':this.form.balance,
        'phone':this.form.phone,
        'email':this.form.email,
        'gender':this.form.gender,
        'avatar':this.form.avatar,
        'deleted':this.form.deleted,
      }, {
        headers: {
          'Authorization': window.localStorage.getItem("token"),
        },
      }).then((response) => {
        if (response.data.state === 200) {
          this.$message({
            showClose: true,
            message: "修改成功",
            type: 'success'
          });
          this.drawer = false;
        } else {
          this.$message({
            showClose: true,
            message: response.data.message,
            type: 'error'
          });
        }
        this.clearData()
        this.showData()
      })
    },
    showData() {
      this.$axios.get('http://localhost:8081/users/list_by_search/' + (this.curPage - 1), {
        headers: {
          'Authorization': window.localStorage.getItem("token"),
        },
        params: {
          'username': this.searchTest
        }
      }).then((response) => {
        if (response.data.state === 200) {
          this.clearData()
          for (let i = 0; i < response.data.data.length; ++i) {
            this.tableData.push(response.data.data[i]);
          }
          this.total = response.data.count / 7;
        } else {
          this.$message({
            showClose: true,
            message: response.data.message,
            type: 'error'
          });
        }
      })
    },
    clearData() {
      this.tableData = [];
    },
    /*search() {
      this.$axios.get('http://localhost:8081/users/find_user_by_search', {
        params: {
          'username': this.searchTest
        },
        headers: {
          'Authorization': window.localStorage.getItem("token"),
        }
      }).then((response) => {
        if (response.data.state === 200) {
          this.clearData();
          for (let i = 0; i < response.data.data.length; ++i) {
            this.tableData.push(response.data.data[i]);
          }
          this.total = response.data.data.length / 7;
        } else {
          this.$message({
            showClose: true,
            message: response.data.message,
            type: 'error'
          });
        }
      })
    }*/
  },
  data() {
    return {
      curPage: 1,
      curData: [],
      tableData: [],
      drawer1: false,
      drawer2: false,
      direction: 'rtl',
      pageSize: 1,
      total: 1,
      searchTest: '',
      form: {
        id: '',
        username: '',
        balance: '',
        phone: '',
        email: '',
        gender: '',
        avatar: '',
        deleted: '',
      },
      newForm: {
        username: '',
        password: '',
        phone: '',
      }
    }
  },
  created() {
    this.showData()
  }
}
</script>

<style scoped>
.el-row {
  margin-bottom: 20px;

}
.el-col {
  border-radius: 4px;
}
.bg-purple-dark {
  background: #99a9bf;
}
.bg-purple {
  background: #d3dce6;
}
.bg-purple-light {
  background: #e5e9f2;
}
.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
.row-bg {
  padding: 10px 0;
  background-color: #f9fafc;
}
</style>
