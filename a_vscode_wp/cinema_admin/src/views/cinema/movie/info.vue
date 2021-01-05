<template>
  <div class="app-container">
     <el-form label-width="120px">
      <el-form-item label="影片名称">
        <el-input v-model="movie.movieName"/>
      </el-form-item>
      <el-form-item label="影片类型">
        <el-input v-model="movie.movieType" controls-position="right" min="0"/>
      </el-form-item>
      <el-form-item label="影片简介">
        <el-input v-model="movie.movieIntroduction" :rows="10" type="textarea"/>
      </el-form-item>
      
    
       <!-- 影片封面 -->
      <el-form-item label="影片封面">

      <div class="demo-image__placeholder">
          <div class="block">
        <el-image :src="movie.movieCover"></el-image>
           </div>
       </div>
                <!-- 文件上传按钮 -->
      <el-button type="primary" icon="el-icon-upload" @click="imagecropperShow=true">更换封面
      </el-button>
              <image-cropper
                        v-show="imagecropperShow"
                        :width="300"
                        :height="300"
                        :key="imagecropperKey"
                        :url="BASE_API+'/oss/fileoss'"
                        field="file"
                        @close="close"
                        @crop-upload-success="cropSuccess"/>        
      </el-form-item>

      <el-form-item>
        <el-button :disabled="saveBtnDisabled" type="primary" @click="saveOrUpdate" v-if="hasPerm('movie.add')||hasPerm('movie.update')">保存</el-button>
      </el-form-item>
    </el-form>

  </div>
</template>
<script>
import ImageCropper from '@/components/ImageCropper'
import PanThumb from '@/components/PanThumb'
import movieApi from '@/api/cinema/movie'

export default {
  components: { ImageCropper, PanThumb },
  data() {
    return {
      movie:{
        movieId:'',
        movieName: '',
        movieType:'',
        movieInstroduction:'',
        movieCover:'',
      },

      //上传弹框组件是否显示
      imagecropperShow:false,
      imagecropperKey:0,//上传组件key值
      BASE_API: process.env.VUE_APP_BASE_API, //获取dev.env.js里面地址
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
    close() { //关闭上传弹框的方法
        this.imagecropperShow=false
        //上传组件初始化
        this.imagecropperKey = this.imagecropperKey+1
    },
    //上传成功方法
    cropSuccess(data) {
      this.imagecropperShow=false
      //上传之后接口返回图片地址
      this.movie.movieCover = data.url
      this.imagecropperKey = this.imagecropperKey+1
    },
    init() {
      //判断路径有id值,做修改
      if(this.$route.params && this.$route.params.id) {
          //从路径获取id值
          const id = this.$route.params.id
          //调用根据id查询的方法
          this.getInfo(id)
      } else { //路径没有id值，做添加
        //清空表单
        this.movie = {
              movieId:'',
              movieName: '',
              movieType:'',
              movieInstroduction:'',
              movieCover:'https://guli-1121.oss-cn-beijing.aliyuncs.com/2020/11/01/665c76434d094b5a8037eac9635884ec0.jpg',
          }
      }
    },
    //根据影片id查询的方法
    getInfo(id) {
      movieApi.getMovie(id)
        .then(response => {
          this.movie = response.data.movie
        })
    },
    saveOrUpdate() {
      //判断修改还是添加
      //根据teacher是否有id
      this.saveBtnDisabled=true
      if(!this.movie.movieId) {
        //添加
        this.saveMovie()
      } else {
        //修改
        this.updateMovie()
      }
    },
    //修改影片的方法
    updateMovie() {
      movieApi.updateMovie(this.movie)
        .then(response => {
          //提示信息
          this.$message({
              type: 'success',
              message: '修改成功!'
          });
          //回到列表页面 路由跳转
          this.$router.push({path:'/cinemaMoi/movie/list'})
        })
        .catch(error=>{
          this.saveBtnDisabled=false          
        })
    },
    //添加影片的方法
    saveMovie() {
      movieApi.addMovie(this.movie)
        .then(response => {//添加成功
          //提示信息
          this.$message({
              type: 'success',
              message: '添加成功!'
          });
          //回到列表页面 路由跳转
          this.$router.push({path:'/cinemaMoi/movie/list'})
        }).catch(error=>{
          this.saveBtnDisabled=false          
        })
    }

  }
}
</script>
