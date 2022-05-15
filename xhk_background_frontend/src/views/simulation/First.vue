<template>
  <div >
    <el-carousel :interval="4000" type="card" height="350px" >
      <el-carousel-item v-for="item in focus" :key="item.file">
        <img :src="item.url" alt="" style="width:100%;height: 100%;"/>
      </el-carousel-item>
    </el-carousel>

    <div class="cards">
      <div class="card" v-for="o in 10" :key="o">
        <el-card class="box-card" shadow="hover">
          <el-row>
            <el-col>

            </el-col>
            <el-col>
              <el-button type="info" icon="el-icon-s-ticket" circle></el-button>
            </el-col>
          </el-row>
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
    }
  },
  methods: {
    showData() {
      this.$axios.get('http://localhost:8081/ads/get_all', {
        headers: {
          'Authorization': window.localStorage.getItem("token"),
        }
      }).then((response) => {
        console.log(response);
        for (let i = 0; i < response.data.data.length; ++i) {
          this.focus.push(response.data.data[i]);
        }
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
  width: 50vw;
}

.card {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin-top: 20px;
}
</style>
