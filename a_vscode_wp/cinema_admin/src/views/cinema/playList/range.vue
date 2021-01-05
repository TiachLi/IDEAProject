<template>
  <div class="app-container">

 <el-form ref="seat" label-width="120px">

     <el-form-item label="放映时间">
      <el-date-picker
         v-model="playList.playListTime"
         type="datetime"
         format="yyyy-MM-dd HH:mm"
         value-format="yyyy-MM-dd HH:mm"
        placeholder="选择日期时间">
        </el-date-picker>
    </el-form-item> 

    <el-form-item label="影厅名" >
     <el-select 
       v-model="playList.hallName"
        placeholder="请选择">
          <el-option
              v-for="name in hallNameList"
                :key="name"
                :label="name"
                :value="name"/>
            </el-select>
      </el-form-item> 
    <el-form-item label="影片名" >
     <el-select 
       v-model="playList.movieName"
        placeholder="请选择">
          <el-option
              v-for="name in movieNameList"
                :key="name"
                :label="name"
                :value="name"/>
            </el-select>
      </el-form-item>
    
      <el-form-item label="票价(元)" width="50px">
        <el-input v-model="playList.ticketPrice" type="text"/>
      </el-form-item> 

        <el-form-item>
        <el-button :disabled="saveBtnDisabled" type="primary" @click="saveOrUpdate()">保存</el-button>
      </el-form-item>
    </el-form>   
  </div>
</template>
<script>
import movieApi from '@/api/cinema/movie'
import hallApi from '@/api/cinema/hall'
import playListApi from '@/api/cinema/playList'

export default {

  data() {
    return {
      playList:{
        playListId:"",
        playListTime:"",
        hallName:"",
        movieName:"",
        ticketPrice:"",
      },
      hallNameList:"",//影厅姓名列表
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
      if (this.$route.params && this.$route.params.id) {
        const id = this.$route.params.id
        this.fetchDataById(id)
        this.getInitData();
      } else {
        // 初始化数据
        this.getInitData();  
       }
      },
     getInitData(){
        // 初始化数据
        //清空表单
         //设置影厅名
         hallApi.getAllHallNameList()
         .then(response=>{
             this.hallNameList=response.data.hallNameList
             console.log(this.hallNameList)   
         })
         //设置影片名
         movieApi.getAllMovieName()
         .then(response=>{
             this.movieNameList=response.data.movieNameList   
        })  
     },


     //回显数据
     fetchDataById(id){
       playListApi.getPlayList(id)
       .then(response=>{
          this.playList=response.data.playList
       })

     }, 


    saveOrUpdate() {
      this.saveBtnDisabled=true
     if (!this.playList.playListId) {
      this.saveData()
      } else {
      this.updateData()
      }
    },
    // 保存信息
    saveData() {
      playListApi.addPlayList(this.playList).then(response => {
          this.$message({
            type: 'success',
            message: '添加成功'
          })
          this.$router.push({ path: '/cinemaPli/playList/info' })
      }).catch(error=>{
            this.saveBtnDisabled=false
      })
    },

    // 根据id更新记录
    updateData() {
      // 数据的获取
      playListApi.updatePlayList(this.playList).then(response => {
          this.$message({
            type: 'success',
            message: '更新成功'
          })
          this.$router.push({ path: '/cinemaPli/playList/info' })
      }).catch(error=>{
            this.saveBtnDisabled=false
          })
    },
  }
}
</script>
