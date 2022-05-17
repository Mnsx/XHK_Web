<template>
<div>
  <!-- 面包屑 -->
  <el-breadcrumb separator-class="el-icon-arrow-right" style="margin-bottom:20px">
    <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
  </el-breadcrumb>

  <!-- 介绍面板 -->
  <el-row :gutter="20">
    <el-col :span="14">
      <el-descriptions class="margin-top" title="网站管理者信息" :column="2" size="" border>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-user"></i>
            用户名
          </template>
          Mnsx_x
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-mobile-phone"></i>
            手机号
          </template>
          19983183167
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-receiving"></i>
            邮箱
          </template>
          xx1527030652@gmail.com
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-tickets"></i>
            GitHub
          </template>
          <el-link type="info">https://github.com/Mnsx</el-link>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-tickets"></i>
            前台网站
          </template>
          <el-link type="info">http://mnsx.top</el-link>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-location-outline"></i>
            居住地
          </template>
          四川成都
        </el-descriptions-item>
      </el-descriptions>

      <!-- 团队介绍卡片 -->
      <el-card class="box-card" style="margin-top:30px;">
        <div slot="header" class="clearfix">
          <span>团队介绍</span>
        </div>
      </el-card>
    </el-col>

    <!-- 项目进度条 -->
    <el-col :span="6">
      <el-timeline :reverse="false">
        <el-timeline-item
          v-for="(activity, index) in activities"
          :key="index"
          :timestamp="activity.timestamp">
          {{activity.content}}
        </el-timeline-item>
      </el-timeline>
    </el-col>
  </el-row>
</div>
</template>

<script>
export default {
  name: "Main",
  methods: {
    errorHandler() {
      return true
    },
  },
  data() {
    return {
      activities: [{
        content: '项目筹备、功能架构',
        timestamp: '2020-04-25'
      }, {
        content: '后台管理系统，开始搭建',
        timestamp: '2020-04-28'
      }, {
        content: '后台管理系统1.0完成搭建',
        timestamp: '2020-05-03'
      },{
        content: '后台管理系统2.0完成搭建',
        timestamp: '2020-05-04'
      },{
        content: '项目前台开始搭建',
        timestamp: '2020-05-15'
      },{
        content: '项目前台1.0完成搭建',
        timestamp: '2020-05-17'
      }]
    }
  }
}
</script>

<style scoped>
.el-carousel__item h3 {
  color: #475669;
  font-size: 18px;
  opacity: 0.75;
  line-height: 300px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n+1) {
  background-color: #d3dce6;
}

.text {
  font-size: 14px;
}

.item {
  padding: 18px 0;
}

.box-card {
  width: 480px;
}

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
