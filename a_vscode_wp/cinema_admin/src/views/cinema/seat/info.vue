<template>
  <div class="app-container">

    <el-steps :active="2" process-status="wait" align-center style="margin-bottom: 40px;">
      <el-step title="设置影厅基本信息"/>
      <el-step title="添加座位总数"/>
      <el-step title="设置不可用的座位"/>
      <el-step title="最终确认"/>
    </el-steps>

    <el-form ref="seat" label-width="120px" :rules="seatValidateRules">
      
            <el-form-item label="影厅名" >
            <el-input v-if="this.$route.query && this.$route.query.hn" v-model="seatVo.hallName" :disabled="true"/>
            <el-select
                v-model="seatVo.hallName"
                placeholder="请选择" v-else>
                <el-option
                    v-for="name in hallNameList"
                    :key="name"
                    :label="name"
                    :value="name"/>
            </el-select>

          </el-form-item> 
          
          <el-form-item label="总行数" prop="totalLines" width='100'>
            <el-input v-model="seatVo.totalLines"/>
          </el-form-item>
          <el-form-item label="总列数" prop="totalCows">
            <el-input v-model="seatVo.totalCows"/>
          </el-form-item>


          <el-form-item>
            <el-button v-if="hasPerm('seat.addTotal')" :disabled="saveBtnDisabled" type="primary" @click="saveOrUpdate()">保存</el-button>
          </el-form-item>
    </el-form>
  </div>
</template>

<script>

import hallApi from '@/api/cinema/hall'
import seatApi from '@/api/cinema/seat'

export default {
  data() {
    return {
      hallNameList:'',  
      seatVo:{
        hallName:'',
        totalLines:'',
        totalCows:'',
      } ,
      saveBtnDisabled: false, // 保存按钮是否禁用,
      seatValidateRules: {
        totalLines: [{required: true, trigger: 'blur', message: '总行数必须输入'}],
        totalCows: [{required: true, trigger: 'blur', message: '总列数必须输入'}]
      }
    }
  },

  // 监听器
  watch: {
    $route(to, from) {
      console.log('路由变化......')
      console.log(to)
      console.log(from)
      this.init()
    }
  },

  // 生命周期方法（在路由切换，组件不变的情况下不会被调用）
  created() {
    console.log('form created ......')
    this.init()
  },

  methods: {
    //获取影厅列表  
    getHallNameList(){
        hallApi.getAllHallNameList()
        .then(response=>{
            this.hallNameList=response.data.hallNameList
        })    
    },

    // 表单初始化
    init() {
      this.getHallNameList() 

      if (this.$route.query.hn) {
        this.seatVo.hallName=this.$route.query.hn 
      } else {
        // 对象拓展运算符：拷贝对象，而不是赋值对象的引用
        this.seatVo= { }
      }
    },

    saveOrUpdate() {
          if (this.$route.query.hn) {
            this.updateData()
          } else {
            this.saveData()
          }
    },

    // 保存信息
    saveData() {
      seatApi.addSeats(this.seatVo).then(response => {
          this.$router.push({ path: '/cinemaHal/seat/disable?hn='+this.seatVo.hallName})
      })
    },

    // 根据id更新记录
    updateData() {
      // teacher数据的获取
      seatApi.updateSeats(this.seatVo).then(response => {
          this.$message({
            type: 'success',
            message: '更新成功'
          })
          this.$router.push({ path: '/cinemaHal/seat/disable?hn='+this.seatVo.hallName})
      })
    },

  }
}
</script>
