<template>
  <div>
    <!-- 面包屑 -->
    <el-breadcrumb separator-class="el-icon-arrow-right" style="margin-bottom:20px">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>图片管理</el-breadcrumb-item>
    </el-breadcrumb>

    <el-upload
      class="upload-demo"
      drag
      :headers="headers"
      name="data"
      action="http://localhost:8081/ads/save_ad"
      :on-success="success"
      :before-upload="beforeAvatarUpload"
      multiple>
      <i class="el-icon-upload"></i>
      <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
      <div class="el-upload__tip" slot="tip">只能上传jpg/png文件，且不超过1MB</div>
    </el-upload>

    <div v-for="item in adData" :key="item.aid" style="margin-top:20px">
        <el-col :span="2">
          <el-button type="danger" icon="el-icon-delete" circle @click="remove(item.aid)"></el-button>
        </el-col>
        <el-col :span="4">
          <img
            style="width: 150px; height: 90px"
            :src="item.url"
          />
        </el-col>
    </div>
  </div>
</template>

<script>
export default {
  name: "Advertising",
  data() {
    return {
      adData: [],
    }
  },
  computed: {
    headers() {
      return {
        'Authorization': window.localStorage.getItem("token"),
      }
    },
  },
  methods: {
    remove(aid) {
      this.$axios.delete('http://localhost:8081/ads/remove_ad/' + aid, {
        headers: {
          'Authorization': window.localStorage.getItem("token"),
        },
      }).then((response) => {
        if (response.data.state === 200) {
          this.$message({
            showClose: true,
            message: "删除成功",
            type: 'success'
          })
          this.showData()
        } else {
          this.$message({
            showClose: true,
            message: response.data.message,
            type: 'error'
          });
        }
      })
    },
    showData() {
      this.$axios.get('http://localhost:8081/ads/list_all', {
        headers: {
          'Authorization': window.localStorage.getItem("token"),
        },
      }).then((response) => {
        if (response.data.state === 200) {
          this.adData = [];
          for (let i = 0; i < response.data.data.length; ++i) {
            this.adData.push(response.data.data[i]);
            console.log(response.data.data[i].url)
          }
        } else {
          this.$message({
            showClose: true,
            message: response.data.message,
            type: 'error'
          });
        }
      })
    },
    success(response) {
      if (response.state !== 200) {
        this.$message({
          showClose: true,
          message: "文件大小超过1MB或者文件类型错误",
          type: 'error'
        })
      } else {
        this.$message({
          showClose: true,
          message: "文件上传成功",
          type: 'success'
        })
      }
      this.showData();
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg';
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!');
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!');
      }
      return isJPG && isLt2M;
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
