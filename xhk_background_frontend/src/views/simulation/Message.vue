<template>
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
</template>

<script>
export default {
  name: "Message"
}
</script>

<style scoped>
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
