<template>
  <div class="app-container">

 <el-form ref="seat" label-width="120px">

    <el-form-item label="影片名" >
     <el-select 
       v-model="movieName"
        placeholder="请选择">
          <el-option
              v-for="name in movieNameList"
                :key="name"
                :label="name"
                :value="name"/>
            </el-select>
      </el-form-item>

        <el-form-item>
        <el-button  v-if="hasPerm('ticket.search')" type="primary" @click="getTicketSales()">查询</el-button>
       </el-form-item>

  
      <el-form-item label="总票房" width="50px">
        <el-input disabled v-model="ticketSales" type="text"/>
      </el-form-item>  

    </el-form>   
  </div>
</template>
<script>
import movieApi from '@/api/cinema/movie'
import staApi from '@/api/cinema/sta'

export default {

  data() {
    return {
      ticketSales:"",
      movieName:"",//查询的影片名
      movieNameList:"",//
      saveBtnDisabled: false, // 保存按钮是否禁用,
    }
  },
  created() { //页面渲染之前执行
    this.init()
  },
  watch: {  //监听
    $route(to, from) { //路由变化方式，路由发生变化，方法就会执行
      this.init()
    }
  },
  methods:{
      init(){
        // 初始化数据
        this.getInitData();  
      },
     getInitData(){
        // 初始化数据
        //清空表单
         //设置影厅名
         //设置影片名
         movieApi.getAllMovieName()
         .then(response=>{
             this.movieNameList=response.data.movieNameList   
        })  
     },
      getTicketSales(){
        staApi.getTicketSales(this.movieName)
        .then(response=>{
          this.ticketSales=response.data.ticketSales
        })
      }
  }
}
</script>
