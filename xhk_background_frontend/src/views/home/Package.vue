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
        <el-button type="primary" @click="drawer2 = true" plain>新建券包数据</el-button>
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
        prop="p.pid"
        label="编号"
        width="150">
      </el-table-column>
      <el-table-column
        prop="username"
        label="用户名"
        width="120">
      </el-table-column>
      <el-table-column
        prop="storeName"
        label="店铺名称"
        width="120">
      </el-table-column>
      <el-table-column
        prop="tid"
        label="优惠券编号"
        width="120">
      </el-table-column>
      <el-table-column
        prop="p.deleted"
        label="状态"
        width="120">
      </el-table-column>
      <el-table-column
        prop="p.createdUser"
        label="创建人"
        width="120">
      </el-table-column>
      <el-table-column
        prop="p.createdTime"
        label="创建时间"
        width="350">
      </el-table-column>
      <el-table-column
        prop="p.modifiedUser"
        label="修改人"
        width="120">
      </el-table-column>
      <el-table-column
        prop="p.modifiedTime"
        label="修改时间"
        width="350">
      </el-table-column>
      <el-table-column
        fixed="right"
        label="操作"
        width="150">
        <template slot-scope="scope">
          <el-button type="text" @click="handleDelete(scope.row.p.pid)" size="small">删除</el-button>
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
      title="券包数据——新增"
      :visible.sync="drawer2"
      :direction="direction"
      :before-close="handleClose">
      <el-form ref="form" :model="newForm" label-width="80px">
        <el-form-item label="用户名">
          <el-input v-model="newForm.username" placeholder="请输入用户名"></el-input>
        </el-form-item>
        <el-form-item label="优惠券id">
          <el-input v-model="newForm.tid" placeholder="请输入优惠券编号"></el-input>
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
      this.$axios.post('http://localhost:8081/packages/add_package', {
        'username':this.newForm.username,
        'tid':this.newForm.tid,
        'createdUser':window.localStorage.getItem("username"),
      },{
        headers: {
          'Authorization': window.localStorage.getItem("token"),
        }
      }).then((response) => {
        if (response.data.state === 200) {
          this.$message({
            showClose: true,
            message: "新增成功",
            type: 'success'
          });
          this.drawer2 = false;
          this.clearData();
          this.showData();
        }  else {
          this.$message({
            showClose: true,
            message: response.data.message,
            type: 'error'
          });
        }
      })
    },
    handleCurrentChange(val) {
      this.curPage = val;
      this.clearData();
      this.showData();
    },
    handleDelete(pid) {
      this.$confirm("确定删除编号为" + pid + "的数据?")
        .then(_ => {
          this.$axios.delete('http://localhost:8081/packages/remove_package/' + pid, {
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
            }  else {
              this.$message({
                showClose: true,
                message: response.data.message,
                type: 'error'
              });
            }
          })
        })
    },
    handleClose(done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done();
        })
        .catch(_ => {});
    },
    showData() {
      this.$axios.get('http://localhost:8081/packages/list_all/' + (this.curPage - 1), {
        headers: {
          'Authorization': window.localStorage.getItem("token"),
        },
      }).then((response) => {
        if (response.data.state === 200) {
          this.clearData()
          for (let i = 0; i < response.data.data.length; ++i) {
            this.tableData.push(response.data.data[i]);
          }
          this.total = response.data.count / 7;
          console.log(this.tableData)
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
      newForm: {
        username: '',
        tid: '',
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
