<template>
    <el-row >
      <el-col>
        <el-upload
          ref="uploadPic"
          class="avatar-uploader"
          :auto-upload="false"
          :action="avatarUrl"
          multiple
          :headers="headers"
          :on-success="handleAvatarSuccess"
          :on-change="handleChange"
          :limit="1"
          :before-upload="beforeAvatarUpload">
          <img v-if="imageUrl" :src="imageUrl" class="avatar">
          <i v-else class="el-icon-plus avatar-uploader-icon"></i>
        </el-upload>
      </el-col>
      <el-col style="margin-top:20px;font-weight: lighter">
          只能上传jpg/png文件，且不超过1MB
      </el-col>
      <el-col style="margin-top:20px;font-weight: lighter">
        点击头像进行上传
      </el-col>
      <el-col>
        <el-button @click="uploadPicture" type="primary" :loading="isUploadPic">上传</el-button>
      </el-col>
    </el-row>
</template>

<script>
export default {
  name: "ModifyAvatar",
  data() {
    return {
      isUploadPic: false,
      imageUrl: '',
      avatarUrl: 'http://localhost:8081/users/update_avatar/' + window.localStorage.getItem("uid"),
    };
  },
  computed: {
    headers() {
      return {
        'Authorization': window.localStorage.getItem("token"),
      }
    },
  },
  methods: {
    uploadPicture() {
      console.log(this.file)
      this.isUploadPic = true;
      this.$axios.post(this.avatarUrl, {
        file: this.file
      }, {
        headers: {
          'Content-Type': 'multipart/form-data',
          'Authorization': window.localStorage.getItem("token"),
        }
      }).then((response) => {
        if (response.status === 200) {
          this.isUploadPic = false;
          this.$router.push('/info')
          location.reload()
          this.$message({
            showClose: false,
            message: '修改成功',
            type: 'success'
          })
        } else {
          this.$message({
            showClose: false,
            message: response.data.message,
            type: 'error'
          })
        }
      })
    },
    handleAvatarSuccess(res, file) {
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg' || 'image/png';
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!');
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!');
      }
      return isJPG && isLt2M;
    },
    handleChange(file, fileList) {
      this.file = file.raw;
    }
  },
  created() {
    this.imageUrl = window.localStorage.getItem("avatar");
  }
}
</script>

<style scoped>
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>
