<template>
  <div class="app-container">
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item>
         <el-input v-model="customerPhone" placeholder="手机号"/>
      </el-form-item>
      <el-button type="primary" icon="el-icon-search" @click="fetchData()">查询</el-button>
      <el-button type="default" @click="resetData()">清空</el-button>
    </el-form>


  </div>
</template>
<script>

export default {
  data() {
    return {
        customerPhone:""
    }
  },
  created() { //页面渲染之前执行

  },
  methods:{
    fetchData(){
        this.$router.push({path:'./pay?cPh='+this.customerPhone})
    }
  }
}
</script>
