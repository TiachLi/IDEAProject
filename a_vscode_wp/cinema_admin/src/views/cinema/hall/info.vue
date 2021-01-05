<template>
  <div class="app-container">

    <el-steps v-if="!(this.$route.params && this.$route.params.id)" :active="1" process-status="wait" align-center style="margin-bottom: 40px;">
      <el-step title="设置影厅基本信息"/>
      <el-step title="添加座位总数"/>
      <el-step title="设置不可用的座位"/>
      <el-step title="最终确认"/>
    </el-steps>

    <el-form ref="user" label-width="120px">
      <el-form-item label="影厅名" prop="hallName">
        <el-input v-model="hall.hallName"/>
      </el-form-item>
      <el-form-item label="影厅等级">
        <el-input v-model="hall.hallLevel"/>
      </el-form-item>
     
      <el-form-item>
        <el-button :disabled="saveBtnDisabled" type="primary" @click="saveOrUpdate()">保存</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>

import hallApi from '@/api/cinema/hall'

const defaultForm = {
  hallId:"",  
  hallName: '',
  hallLevel: '',
}
export default {
  data() {
    return {
      hall: defaultForm,
      saveBtnDisabled: false, // 保存按钮是否禁用,
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

    // 表单初始化
    init() {
      if (this.$route.params && this.$route.params.id) {
        const id = this.$route.params.id
        this.fetchDataById(id)
      } else {
        // 对象拓展运算符：拷贝对象，而不是赋值对象的引用
        this.hall = { }
      }
    },

    saveOrUpdate() {
       this.saveBtnDisabled = true     
          if (!this.hall.hallId) {
            this.saveData()
          } else {
            this.updateData()
          }
    },

    // 保存信息
    saveData() {
      hallApi.addHall(this.hall).then(response => {
          this.$message({
            type: 'success',
            message: '添加成功'
          })

          this.$router.push({ path: '/cinemaHal/seat/update?hn='+this.hall.hallName })
      }).catch(error=>{
      this.saveBtnDisabled = false
        }
      )
    },

    // 根据id更新记录
    updateData() {
      // teacher数据的获取
      hallApi.updateHall(this.hall).then(response => {
          this.$message({
            type: 'success',
            message: '添加成功'
          })
          this.$router.push({ path: '/cinemaHal/hall/list' })
      }).catch(error=>{
        this.saveBtnDisabled = false
        }
      )
    },

    // 根据id查询记录
    fetchDataById(id) {
      hallApi.getHall(id).then(response => {
        this.hall = response.data.hall
      })
    }

  }
}
</script>
