<template>
<div class="app-container">

    <el-steps :active="3" process-status="wait" align-center style="margin-bottom: 40px;">
      <el-step title="设置影厅基本信息"/>
      <el-step title="添加座位总数"/>
      <el-step title="设置不可用的座位"/>
      <el-step title="最终确认"/>
    </el-steps>
  <div style="margin-top: 20px">
          <div v-for="(lineSeat,index) in seatList" :key="index" size="small">
            
            <span @click="choseSeat(seat)" v-for="seat in lineSeat" :key="seat.seatId" >
              <span v-if="seat.isUsable===0">
              <el-checkbox  checked :label="getSeatNum(seat.seatLine,seat.seatCow)" border></el-checkbox>
              </span>
              <span v-else>
              <el-checkbox   :label="getSeatNum(seat.seatLine,seat.seatCow)" border ></el-checkbox>  
                </span>  
            </span>
          </div>
      <br/>
      <br/>
      <br/>
      <el-form :rules="seatValidateRules" :inline="true" class="demo-form-inline">
          <el-form-item prop="customerPhone" >
          <router-link :to="'/cinemaHal/seat/add?hn='+this.hallName">
            <el-button type="primary"  v-if="hasPerm('seat.addTotal')">返回修改</el-button>
          </router-link>
          </el-form-item>
         <el-button type="primary" @click="setSeatDisable()" v-if="hasPerm('seat.seatDisable')">确认</el-button>
    </el-form>
 
   </div>
    
</div>
</template>

<script>
import seatApi from '@/api/cinema/seat'

 export default {
    data () {
      return {
        checked:false,
        seatList:"",//座位列表
        selected:"",//选中的状态 每点击一次出发两次click 偶数未选中 奇数选中

        hallName:"",//影厅名
        isDisabled:"",//是否禁用
        selectedSeat:[],//选中座位列表
        seatValidateRules: {
        customerPhone: [{required: true, trigger: 'blur', message: '请输入顾客手机号'}],
      }
      };
    },
    created(){
        if (this.$route.query.hn) {          
          this.hallName=this.$route.query.hn
          //初始化数据
          this.init()
        }

    },
    methods:{
      //设置座位不可用状态
      setSeatDisable(){
       seatApi.setIsUsable(this.selectedSeat)
       .then(response=>{
          this.$router.push({ path: '/cinemaHal/seat/final?hn='+this.hallName})
       })
     },

      init(){
        //初始化数据
          this.fetchData();
      },

      fetchData(){
        //获取座位列表
           seatApi.getAllSeats(this.hallName)
           .then(response=>{
             this.seatList=response.data.seatList
            console.log(this.seatList)
           })
       
      
      },
      //设置行列号
      getSeatNum(seatLine,seatCow){
        let seatNo=seatLine+"行"+seatCow+"列"
        return seatNo
      },
    //选择座位，待优化
    choseSeat(seat){
        let isExist=0;
        this.selectedSeat.push(seat.seatId)
          if (seat.isUsable===1) {
              this.selectedSeat.forEach((element,index) => {
                if(element===seat.seatId){
                    isExist++
                }
              });
              if(isExist>3){
                while(isExist>0){
                  var index = this.selectedSeat.findIndex(item =>{
    　　　　　　　　　　if(item==seat.seatId){
    　　　　　　　　　　　　return true
    　　　　　　　　　　}
    　　　　　　　　})
    　　　　　　　　this.selectedSeat.splice(index,1)
                  isExist--
                }
              }
          }
    },

}


}
</script>