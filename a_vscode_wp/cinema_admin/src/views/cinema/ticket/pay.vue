<template>
  <div class="app-container">


    <!-- 场次列表 -->
    <el-table
      :data="list"
      stripe
      style="width: 100%">

      <el-table-column
        label="序号"
        width="70"
        align="center">
        <template slot-scope="scope">
          {{scope.row.ticketId}}
        </template>
      </el-table-column>

      <el-table-column prop="movieName" label="电影名" width="180"></el-table-column>

      <el-table-column prop="hallName" label="影厅"/>

     <el-table-column label="座位号" width="100" align="center">
        <template slot-scope="scope">
            <span v-html="getSeatNo(scope.row.seatLine,scope.row.seatCow)"/> 
        </template>
      </el-table-column>

        <el-table-column prop="playTime" label="放映时间" width="150"/>
        <el-table-column prop="customerPhone" label="顾客手机号" width="120"/>
        <el-table-column prop="ticketPrice" label="票价(元)" width="100"/>      
        <el-table-column prop="finalPrice" label="会员价(元)" width="100"/>

     <el-table-column label="是否支付" width="100" align="center">
        <template slot-scope="scope">
            <span v-html="getPaidMeg(scope.row.isPaid)"/> 
        </template>
      </el-table-column>
        

      <el-table-column label="操作" width="200" align="center">
        <template slot-scope="scope">  
            <el-button @click="openRemove(scope.row.ticketId)" type="primary" size="mini" icon="el-icon-info" v-if="hasPerm('ticket.remove')">退票</el-button>
            <el-button @click="openUpdate(scope.row.ticketId)" type="primary" size="mini" icon="el-icon-info" >支付</el-button>
        </template>
      </el-table-column>
    </el-table>

  </div>
</template>

<script>
import playListApi from '@/api/cinema/playList'
import ticketApi from '@/api/cinema/ticket'

export default {
  data() {
        return {
          list:null,//查询之后接口返回集合
        }
  },

  // 生命周期函数：内存准备完毕，页面尚未渲染
  created() {

    this.init()  

  },
  methods: {
    //初始化数据
      init(){ 
        if(this.$route.query.cPh){
            if(this.$route.query.pId){
                //选票支付，  
                ticketApi.getFinalTicketInfo(this.$route.query.pId,this.$route.query.cPh)
                .then(response=>{
                    this.list=response.data.ticketList
                })
            }else{
                this.canPay=false
                 //退票选票，  
                ticketApi.getTicketWithCustomerPhone(this.$route.query.cPh)
                .then(response=>{
                    this.list=response.data.ticketList
                })               
            }
        }
     
      },
      //设置支付状态
      getPaidMeg(flag){
          console.log("支付状态"+flag)
          if(flag==1){
              return "已支付"
          }else{
              return "未支付"
          }
      },
      //设置行号
      getSeatNo(seatLine,seatCow){
          return seatLine+"排"+seatCow+"号"
      },          
      //跟新支付状态
         updateDataById(ticketId){
         ticketApi.updateTicket(ticketId)
         .then(response=>{
            //提示信息
            this.$message({
            type: 'success',
            message: '更新成功!'
            });
           //回到列表页面
          this.init();           
         })             

        }, 
        //删除的方法
       removeDataById(ticketId){
         ticketApi.removeTicket(ticketId)
         .then(response=>{
            //提示信息
            this.$message({
            type: 'success',
            message: '删除成功!'
            });
           //回到列表页面
          this.init();           
         })
       },


       //确认删除
       openRemove(ticketId) {
          this.$confirm('是否删除该票据信息, 是否继续?', '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(() => {
              this.removeDataById(ticketId)
            })
          }, 
        //确认支付
       openUpdate(ticketId) {
          this.$confirm('确认更新支付状态?', '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(() => {
              this.updateDataById(ticketId)
            })
          },

    }
  }
</script>


