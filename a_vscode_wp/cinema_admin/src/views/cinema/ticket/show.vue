<template>
<div class="app-container">
  
  <div style="margin-top: 20px">
          <div v-for="(lineSeat,index) in seatList" :key="index" size="small">
            
            <span @click="choseSeat(seat)" v-for="seat in lineSeat" :key="seat.seatId" >
              <span v-if="seat.isUsable===0">
              <el-checkbox  disabled   :label="getSeatNum(seat.seatLine,seat.seatCow)" border></el-checkbox>
              </span>

              <span v-else-if="seat.isSelected===1">
              <el-checkbox   disabled  :checked="true"  :label="getSeatNum(seat.seatLine,seat.seatCow)" border ></el-checkbox>
              </span>
              <span v-else>
              <el-checkbox    :label="getSeatNum(seat.seatLine,seat.seatCow)" border ></el-checkbox>  
                </span>  
            </span>
          </div>
      <br/>
      <br/>
      <br/>
      <el-form :rules="seatValidateRules" :inline="true" class="demo-form-inline">
          <el-form-item prop="customerPhone" >
            <el-input v-model="ticketVo.customerPhone" placeholder="顾客手机号"/>
          </el-form-item>
         <el-button type="primary" @click="creatTickets()">确认</el-button>
    </el-form>
 
   </div>
    

   </div>
</div>
</template>

<script>
import seatApi from '@/api/cinema/seat'
import ticketApi from '@/api/cinema/ticket'

 export default {
    data () {
      return {
        checked:false,
        seatList:"",//座位列表
        selected:"",//选中的状态 每点击一次出发两次click 偶数未选中 奇数选中
        checkList:"",
        hallName:"",//影厅名
        isDisabled:"",//是否禁用
        ticketVo:{
          playListId:"",//当前场次id
          selectedSeat:[],//选中座位列表
          customerPhone:""
        },
        seatValidateRules: {
        customerPhone: [{required: true, trigger: 'blur', message: '请输入顾客手机号'}],
      }
      };
    },
    created(){
        if (this.$route.params && this.$route.params.id && this.$route.query.hn) {          
          this.hallName=this.$route.query.hn
          this.ticketVo.playListId=this.$route.params.id
          //初始化数据
          this.init()

        }

    },
    methods:{
      //生成电影票
      creatTickets(){
       ticketApi.creatTickets(this.ticketVo)
       .then(response=>{
          this.$router.push({ path: '/cinemaTic/ticket/pay?pId='+this.ticketVo.playListId+'&cPh='+this.ticketVo.customerPhone })
       })


     },
      init(){
        //初始化数据
          this.fetchData();
      },

      fetchData(){
        //获取座位列表
           seatApi.getAllSeatsWithPl(this.ticketVo.playListId,this.hallName)
           .then(response=>{
             this.seatList=response.data.seatList
             console.log(this.seatList)
           })
       
      
      },
      //设置行列号
      getSeatNum(seatLine,seatCow,isSelected){
        let seatNo=seatLine+"行"+seatCow+"列"
        return seatNo
      },
    //选择座位，待优化
    choseSeat(seat){
        let isExist=0;
        this.ticketVo.selectedSeat.push(seat.seatId)
          if (seat.isUsable===1&&seat.isSelected===0) {
              this.ticketVo.selectedSeat.forEach((element,index) => {
                if(element===seat.seatId){
                    isExist++
                }
              });
              if(isExist>3){
                while(isExist>0){
                  var index = this.ticketVo.selectedSeat.findIndex(item =>{
    　　　　　　　　　　if(item==seat.seatId){
    　　　　　　　　　　　　return true
    　　　　　　　　　　}
    　　　　　　　　})
    　　　　　　　　this.ticketVo.selectedSeat.splice(index,1)
                  isExist--
                }
              }
          }
    },

}


}
</script>