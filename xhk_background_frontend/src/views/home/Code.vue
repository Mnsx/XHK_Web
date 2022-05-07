<template>
  <div>
    <!-- 面包屑 -->
    <el-breadcrumb separator-class="el-icon-arrow-right" style="margin-bottom:20px">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>数据管理</el-breadcrumb-item>
      <el-breadcrumb-item>注册码管理</el-breadcrumb-item>
    </el-breadcrumb>

    <!-- 头部 -->
    <el-row :gutter="20">
      <el-col :span="12">
        <el-button type="primary" @click="drawer2 = true" plain>新建注册码</el-button>
      </el-col>
    </el-row>
    <div class="demo-input-suffix">
    </div>

    <!-- 主体 -->
    <el-table
      :data="tableData"
      style="width: auto">
      <el-table-column
        prop="cid"
        label="编号"
        width="150">
      </el-table-column>
      <el-table-column
        prop="code"
        label="注册码"
        width="500">
      </el-table-column>
      <el-table-column
        label="操作"
        width="150">
        <template slot-scope="scope">
          <el-button type="text" @click="handleDelete(scope.row.cid)" size="small">删除</el-button>
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
      title="注册码——新增"
      :visible.sync="drawer2"
      :direction="direction"
      :before-close="handleClose">
      <el-form ref="form" :model="newForm" label-width="80px">
        <el-form-item label="注册码">
          <el-input v-model="newForm.code" placeholder="请输入注册码"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleNew">新增注册码</el-button>
        </el-form-item>
      </el-form>
    </el-drawer>
  </div>
</template>

<script>
export default {
  name: "Code",
  methods: {
    handleNew() {
      this.$axios.post('http://localhost:8081/codes/add_code', {
      },{
        headers: {
          'Authorization': window.localStorage.getItem("token"),
        },
        params: {
          'code':this.newForm.code,
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
        }
      })
    },
    handleCurrentChange(val) {
      this.curPage = val;
      this.clearData();
      this.showData();
    },
    handleDelete(cid) {
      this.$confirm("确定删除编号为" + cid + "的数据?")
        .then(_ => {
          this.$axios.delete('http://localhost:8081/codes/delete_by_cid/' + cid, {
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
    handleClose(done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done();
        })
        .catch(_ => {});
    },
    showData() {
      this.$axios.get('http://localhost:8081/codes/list_code/' + (this.curPage - 1), {
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
        code: '',
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
