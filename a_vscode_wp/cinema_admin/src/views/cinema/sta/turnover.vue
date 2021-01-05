<template>
  <div class="app-container">
   <el-form ref="seat" label-width="120px"> 
    <el-form-item label="选择日期" >
    <div class="block">
    <el-date-picker
      v-model="value2"
      type="daterange"
      align="right"
      format="yyyy-MM-dd"
      value-format="yyyy-MM-dd"      
      unlink-panels
      range-separator="至"
      start-placeholder="开始日期"
      end-placeholder="结束日期"
      :picker-options="pickerOptions">
    </el-date-picker>
  </div>
    </el-form-item>

        <el-form-item>
        <el-button  v-if="hasPerm('ticket.search')" type="primary" @click="getFinalTicketPrice()">查询</el-button>
       </el-form-item>

  
      <el-form-item label="总营业额" width="50px">
        <el-input disabled v-model="finalTicketPrice" type="text"/>
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
      finalTicketPrice:"",
      movieName:"",//查询的影片名
      movieNameList:"",//
      saveBtnDisabled: false, // 保存按钮是否禁用,
      value2:[],
       pickerOptions: {
          shortcuts: [{
            text: '最近一周',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit('pick', [start, end]);
            }
          }, {
            text: '最近一个月',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
              picker.$emit('pick', [start, end]);
            }
          }, {
            text: '最近三个月',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
              picker.$emit('pick', [start, end]);
            }
          }]
        },
    }
  },
  created() { //页面渲染之前执行
  
  },

  methods:{

      getFinalTicketPrice(){
        staApi.getFinalTicketPrice(this.value2[0],this.value2[1])
        .then(response=>{
          this.finalTicketPrice=response.data.finalTicketPrice
        })
      }
  }
}
</script>
