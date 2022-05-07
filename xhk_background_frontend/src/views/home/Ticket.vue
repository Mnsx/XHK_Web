<template>
  <div>
    <!-- 面包屑 -->
    <el-breadcrumb separator-class="el-icon-arrow-right" style="margin-bottom:20px">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>数据管理</el-breadcrumb-item>
      <el-breadcrumb-item>优惠券管理</el-breadcrumb-item>
    </el-breadcrumb>

    <!-- 头部 -->
    <el-row :gutter="20">
      <el-col :span="12">
        <el-button type="primary" @click="drawer2 = true" plain>新建优惠券</el-button>
      </el-col>
<!--      <el-col :span="6">
        <el-input
          placeholder="关键字搜索"
          prefix-icon="el-icon-search"
          v-model="searchTest">
        </el-input>
      </el-col>
      <el-col :span="6">
        <el-button @click="showData" icon="el-icon-search" circle></el-button>
      </el-col>-->
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
        prop="ticket.tid"
        label="编号"
        width="150">
      </el-table-column>
      <el-table-column
        prop="storeName"
        label="商家名称"
        width="200">
      </el-table-column>
      <el-table-column
        prop="ticket.demand"
        label="需求"
        width="120">
      </el-table-column>
      <el-table-column
        prop="ticket.discount"
        label="折扣"
        width="150">
      </el-table-column>
      <el-table-column
        prop="ticket.deleted"
        label="状态"
        width="120">
      </el-table-column>
      <el-table-column
        prop="ticket.createdUser"
        label="创建人"
        width="120">
      </el-table-column>
      <el-table-column
        prop="ticket.createdTime"
        label="创建时间"
        width="350">
      </el-table-column>
      <el-table-column
        prop="ticket.modifiedUser"
        label="修改人"
        width="120">
      </el-table-column>
      <el-table-column
        prop="ticket.modifiedTime"
        label="修改时间"
        width="350">
      </el-table-column>
      <el-table-column
        fixed="right"
        label="操作"
        width="150">
        <template slot-scope="scope">
          <el-button @click="handleOpen(scope.row.ticket.tid)" type="text" size="small">修改</el-button>
          <el-button type="text" @click="handleDelete(scope.row.ticket.tid)" size="small">删除</el-button>
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
      title="优惠券数据——修改"
      :visible.sync="drawer1"
      :direction="direction"
      :before-close="handleClose">
      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="商家编号">
          <el-input v-model="form.sid" placeholder="请输入商家编号"></el-input>
        </el-form-item>
        <el-form-item label="条件需求">
          <el-input v-model="form.demand" placeholder="请输入条件需求"></el-input>
        </el-form-item>
        <el-form-item label="优惠">
          <el-input v-model="form.discount" placeholder="请输入优惠"></el-input>
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
      title="优惠券数据——新增"
      :visible.sync="drawer2"
      :direction="direction"
      :before-close="handleClose">
      <el-form ref="form" :model="newForm" label-width="80px">
        <el-form-item label="商家编号">
          <el-input v-model="newForm.sid" placeholder="请输入商家编号"></el-input>
        </el-form-item>
        <el-form-item label="条件需求">
          <el-input v-model="newForm.demand" placeholder="请输入条件需求"></el-input>
        </el-form-item>
        <el-form-item label="折扣条件">
          <el-input v-model="newForm.discount" placeholder="请输入折扣条件"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleNew">新增优惠券</el-button>
        </el-form-item>
      </el-form>
    </el-drawer>
  </div>
</template>

<script>
export default {
  name: "Ticket",
  methods: {
    handleNew() {
      this.$axios.post('http://localhost:8081/tickets/add_ticket', {
        'sid':this.newForm.sid,
        'demand':this.newForm.demand,
        'discount':this.newForm.discount,
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
    handleDelete(tid) {
      this.$confirm("确定删除编号为" + tid + "的数据?")
        .then(_ => {
          this.$axios.delete('http://localhost:8081/tickets/remove_ticket_by_tid/' + tid, {
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
    handleOpen(tid) {
      this.drawer1 = true;
      this.$axios.get('http://localhost:8081/tickets/get_ticket_by_tid/' + tid, {
        headers: {
          'Authorization': window.localStorage.getItem("token"),
        }
      }).then((response) => {
        if (response.data.state === 200) {
          this.form.sid= response.data.data.sid;
          this.form.demand= response.data.data.demand;
          this.form.discount= response.data.data.discount;
          this.form.deleted = response.data.data.deleted === 0 ? '正常' : '注销';
          this.form.tid = response.data.data.tid;
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
      this.$axios.put('http://localhost:8081/tickets/modified_ticket', {
        'tid':this.form.tid,
        'sid':this.form.sid,
        'demand':this.form.demand,
        'discount':this.form.discount,
        'deleted': this.form.deleted === '正常' || '0' ?  0 : 1,
        'modifiedUser': window.localStorage.getItem("username")
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
      this.$axios.get('http://localhost:8081/tickets/list_all/' + (this.curPage - 1), {
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
      form: {
        tid: '',
        sid: '',
        demand: '',
        discount: '',
        deleted: '',
      },
      newForm: {
        sid: '',
        demand: '',
        discount: '',
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
