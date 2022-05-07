<template>
<div>
  <!-- 面包屑 -->
  <el-breadcrumb separator-class="el-icon-arrow-right" style="margin-bottom:20px">
    <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
    <el-breadcrumb-item>数据管理</el-breadcrumb-item>
    <el-breadcrumb-item>商家管理</el-breadcrumb-item>
  </el-breadcrumb>

  <!-- 头部 -->
  <el-row :gutter="20">
    <el-col :span="12">
      <el-button type="primary" @click="drawer2 = true" plain>新建商家</el-button>
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
      prop="store.sid"
      label="编号"
      width="150">
    </el-table-column>
    <el-table-column
      prop="username"
      label="用户名"
      width="120">
    </el-table-column>
    <el-table-column
      prop="store.storeName"
      label="店名"
      width="120">
    </el-table-column>
    <el-table-column
      prop="store.photoUrl"
      label="图片路径"
      width="300">
    </el-table-column>
    <el-table-column
      prop="store.phone"
      label="电话"
      width="300">
    </el-table-column>
    <el-table-column
      prop="store.location"
      label="店铺位置"
      width="350">
    </el-table-column>
    <el-table-column
      prop="store.description"
      label="简介"
      width="500">
    </el-table-column>
    <el-table-column
      prop="store.deleted"
      label="状态"
      width="120">
    </el-table-column>
    <el-table-column
      prop="store.createdUser"
      label="创建人"
      width="120">
    </el-table-column>
    <el-table-column
      prop="store.createdTime"
      label="创建时间"
      width="350">
    </el-table-column>
    <el-table-column
      prop="store.modifiedUser"
      label="修改人"
      width="120">
    </el-table-column>
    <el-table-column
      prop="store.modifiedTime"
      label="修改时间"
      width="350">
    </el-table-column>
    <el-table-column
      fixed="right"
      label="操作"
      width="150">
      <template slot-scope="scope">
        <el-button @click="handleOpen(scope.row.store.sid)" type="text" size="small">修改</el-button>
        <el-button type="text" @click="handleDelete(scope.row.store.sid)" size="small">删除</el-button>
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
    title="商家数据——修改"
    :visible.sync="drawer1"
    :direction="direction"
    :before-close="handleClose">
    <el-form ref="form" :model="form" label-width="80px">
      <el-form-item label="用户名">
        <el-input v-model="form.username" placeholder="请输入用户名"></el-input>
      </el-form-item>
      <el-form-item label="店名">
        <el-input v-model="form.storeName" placeholder="请输入店名"></el-input>
      </el-form-item>
      <el-form-item label="电话">
        <el-input v-model="form.phone" placeholder="请输入电话"></el-input>
      </el-form-item>
      <el-form-item label="图片路径">
        <el-input v-model="form.photoUrl" placeholder="请输入图片路径"></el-input>
      </el-form-item>
      <el-form-item label="地址">
        <el-input v-model="form.location" placeholder="请输入地址"></el-input>
      </el-form-item>
      <el-form-item label="简介">
        <el-input v-model="form.description" placeholder="请输入简介"></el-input>
      </el-form-item>
      <el-form-item label="店铺状态">
        <el-select v-model="form.deleted" placeholder="请选择店铺状态">
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
    title="商家数据——新增"
    :visible.sync="drawer2"
    :direction="direction"
    :before-close="handleClose">
    <el-form ref="form" :model="newForm" label-width="80px">
      <el-form-item label="用户名">
        <el-input v-model="newForm.username" placeholder="请输入用户名"></el-input>
      </el-form-item>
      <el-form-item label="店名">
        <el-input v-model="newForm.storeName" placeholder="请输入店名"></el-input>
      </el-form-item>
      <el-form-item label="电话">
        <el-input v-model="newForm.phone" placeholder="请输入电话"></el-input>
      </el-form-item>
      <el-form-item label="地址">
        <el-input v-model="newForm.location" placeholder="请输入地址"></el-input>
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
  name: "Store",
  methods: {
    handleNew() {
      this.$axios.post('http://localhost:8081/stores/add_store', {
        'username':this.newForm.username,
        'storeName':this.newForm.storeName,
        'phone':this.newForm.phone,
        'location':this.newForm.location
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
        }
      })
    },
    handleCurrentChange(val) {
      this.curPage = val;
      this.clearData();
      this.showData();
    },
    handleDelete(sid) {
      this.$confirm("确定删除编号为" + sid + "的数据?")
        .then(_ => {
          this.$axios.delete('http://localhost:8081/stores/remove_store_by_sid/' + sid, {
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
    handleOpen(sid) {
      this.drawer1 = true;
      this.$axios.get('http://localhost:8081/stores/find_one_by_sid/' + sid, {
        headers: {
          'Authorization': window.localStorage.getItem("token"),
        }
      }).then((response) => {
        if (response.data.state === 200) {
          console.log(response);
          this.form.username = response.data.data.username;
          this.form.storeName = response.data.data.store.storeName;
          this.form.photoUrl = response.data.data.store.photoUrl;
          this.form.location= response.data.data.store.location;
          this.form.phone= response.data.data.store.phone;
          this.form.description= response.data.data.store.description;
          this.form.deleted = response.data.data.store.deleted === 0 ? '正常' : '注销';
          this.form.sid = response.data.data.store.sid;
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
      this.$axios.put('http://localhost:8081/stores/modified_store_by_sid/' + this.form.username , {
        'sid': this.form.sid,
        'storeName': this.form.storeName,
        'photoUrl': this.form.photoUrl,
        'location': this.form.location,
        'phone': this.form.phone,
        'description': this.form.description,
        'deleted': this.form.deleted === '正常' || '0' ?  0 : 1,
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
          this.drawer1 = false;
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
      this.$axios.get('http://localhost:8081/stores/list_by_search/' + (this.curPage - 1), {
        headers: {
          'Authorization': window.localStorage.getItem("token"),
        },
        params: {
          'searchText': this.searchTest
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
        username: '',
        storeName: '',
        photoUrl: '',
        location: '',
        phone: '',
        description: '',
        deleted: '',
        sid: ''
      },
      newForm: {
        username: '',
        storeName: '',
        phone: '',
        location: '',
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
