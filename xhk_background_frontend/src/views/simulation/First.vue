<template>
  <div class="father">
    <div class="focus-fix fixed">

    </div>
    <el-carousel class="focus-fix" indicator-position="none" arrow="never" height="400px">
      <el-carousel-item v-for="item in focus" :key="item.file">
        <img :src="item.url" alt="" style="width:100%;height: 100%;"/>
      </el-carousel-item>
    </el-carousel>
    <el-carousel class="focus" indicator-position="none" style="width: 50vw" arrow="never" height="400px">
      <el-carousel-item v-for="item in focus" :key="item.file">
        <img :src="item.url" alt="" style="width:100%;height: 100%;"/>
      </el-carousel-item>
    </el-carousel>

    <div class="cards">
      <div class="card" v-for="item in tickets" :key="item.pid">
        <el-card :body-style="{ padding: '0px' }"  shadow="hover">
          <img :src="item.store.photoUrl" class="image">
          <div style="padding: 14px;">
            <el-popover
              :data-html = "true"
              placement="top-start"
              :title="item.store.storeName"
              width="200"
              trigger="hover">
              <p>地址：{{item.store.location}}</p>
              <p>电话：{{item.store.phone}}</p>
              <p>简介：{{item.store.description}}</p>
            <h3 style="font-weight: bolder" slot="reference">{{item.store.storeName}}</h3>
            </el-popover>
            <div class="bottom clearfix">
              <span style="font-weight: lighter">满{{item.ticket.demand}}减{{item.ticket.discount}}</span>
              <el-popover
                :data-html = "true"
                placement="top-start"
                :title="item.store.storeName"
                width="200"
                trigger="click">
                <el-image
                  style="width: 100px; height: 100px"
                  :src="qrCode"
                  fit="fit"
                ></el-image>
                <el-button type="text" @click="use(item.pid)" slot="reference" class="button">使用</el-button>
              </el-popover>
            </div>
          </div>
        </el-card>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "First",
  data() {
    return {
      focus: [],
      tickets: [],
      qrCode: '',
    }
  },
  methods: {
    showData() {
      this.$axios.get('http://localhost:8081/ads/get_all', {
        headers: {
          'Authorization': window.localStorage.getItem("token"),
        }
      }).then((response) => {
        for (let i = 0; i < response.data.data.length; ++i) {
          this.focus.push(response.data.data[i]);
        }
      })
      this.$axios.get('http://localhost:8081/packages/find_all/' + window.localStorage.getItem('uid'), {
        headers: {
          'Authorization': window.localStorage.getItem("token"),
        }
      }).then((response) => {
        for (let i = 0; i < response.data.data.length; ++i) {
          this.tickets.push(response.data.data[i]);
        }
      })
    },
    use(pid) {
      this.$axios.get('http://localhost:8081/qrcode/' + pid, {
        headers: {
          'Authorization': window.localStorage.getItem("token"),
        }
      }).then((response) => {
        this.qrCode = response.config.url;
      })
    }
  },
  created() {
    this.showData();
  }
}
</script>

<style scoped>
.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 200px;
  margin: 0;
}

.box-card {
  width: 20vw;
}

.father {
  position: relative;
}

.fixed {
  filter: blur(5);
  z-index: -10!important;
  background-color: #000f;
  /*background-image: linear-gradient(to top, #f1f2b5, #135058);*/
  opacity: 0.5;
  height: 400px;
}

.focus-fix {
  position: absolute;
  left: 0;
  top: 0;
  right: 0;
  z-index: -100;
}

.focus {
  margin: 0 auto;
  pointer-events: none;
}

.cards {
  margin-left: 10vw;
  margin-right: 10vw;
  display: grid;
  justify-content: space-around;
  grid-template-columns: repeat(3, 20%);
}

.card {
  margin-top: 20px;
}

.time {
  font-size: 13px;
  color: #999;
}

.bottom {
  margin-top: 13px;
  line-height: 12px;
}

.button {
  padding: 0;
  float: right;
}

.image {
  width: 100%;
  display: block;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both
}
</style>
