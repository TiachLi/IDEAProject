<template>
  <div class="app-container">
     <el-form label-width="120px">
      <el-form-item label="会员名称">
        <el-input v-model="vip.vipName"/>
      </el-form-item>
      <el-form-item label="会员手机号">
        <el-input v-model="vip.vipPhone" controls-position="right" min="0"/>
      </el-form-item>
      <el-form-item label="会员折扣">
        <el-input v-model="vip.vipDiscount"  type="text"/>
      </el-form-item>


      <el-form-item>
        <el-button :disabled="saveBtnDisabled" type="primary" @click="saveOrUpdate" v-if="hasPerm('vip.add')||hasPerm('vip.update')">保存</el-button>
      </el-form-item>
    </el-form>

  </div>
</template>
<script>
import vipApi from '@/api/cinema/vip'

export default {
  data() {
    return {
      vip:{
        vipId:'',
        vipName: '',
        vipPhone:'',
        vipDiscount:'',
      },

      saveBtnDisabled:false  // 保存按钮是否禁用,
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
    init() {
      //判断路径有id值,做修改
      if(this.$route.params && this.$route.params.id) {
          //从路径获取id值
          const id = this.$route.params.id
          //调用根据id查询的方法
          this.getInfo(id)
      } else { //路径没有id值，做添加
        //清空表单
        this.vip = {
              vipId:'',
              vipName: '',
              vipType:'',
              vipInstroduction:'',
              vipCover:'https://guli-1121.oss-cn-beijing.aliyuncs.com/2020/11/01/665c76434d094b5a8037eac9635884ec0.jpg',
          }
      }
    },
    //根据会员id查询的方法
    getInfo(id) {
      vipApi.getVip(id)
        .then(response => {
          this.vip = response.data.vip
        })
    },
    saveOrUpdate() {
      //判断修改还是添加
      //根据teacher是否有id
      this.saveBtnDisabled=true
      if(!this.vip.vipId) {
        //添加
        this.saveVip()
      } else {
        //修改
        this.updateVip()
      }
    },
    //修改会员的方法
    updateVip() {
      vipApi.updateVip(this.vip)
        .then(response => {
          //提示信息
          this.$message({
              type: 'success',
              message: '修改成功!'
          });
          //回到列表页面 路由跳转
          this.$router.push({path:'/cinemaV/vip/list'})
        })
        .catch(error=>{
          this.saveBtnDisabled=false          
        })
    },
    //添加会员的方法
    saveVip() {
      vipApi.addVip(this.vip)
        .then(response => {//添加成功
          //提示信息
          this.$message({
              type: 'success',
              message: '添加成功!'
          });
          //回到列表页面 路由跳转
          this.$router.push({path:'/cinemaV/vip/list'})
        }).catch(error=>{
          this.saveBtnDisabled=false          
        })
    }

  }
}
</script>
