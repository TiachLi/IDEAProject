<template>
  <div class="app-container">
    <!-- 查询表单 -->
    <!--查询表单-->
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item>
         <el-input v-model="movieQuery.movieName" placeholder="影片名"/>
      </el-form-item>
      <el-form-item>
         <el-input v-model="movieQuery.movieType" placeholder="影片类型"/>
      </el-form-item>
      <el-button type="primary" icon="el-icon-search" @click="fetchData()">查询</el-button>
      <el-button type="default" @click="resetData()">清空</el-button>
    </el-form>

    <!-- 工具条 -->
    <div>
      <router-link :to="'/cinemaMoi/movie/add'">
         <el-button type="primary" size="mini" icon="el-icon-info" v-if="hasPerm('movie.add')">添加</el-button>
      </router-link>
    </div>

    <!-- 影片列表 -->
    <el-table
      v-loading="listLoading"
      :data="list"
      stripe
      style="width: 100%"
      @selection-change="handleSelectionChange">

      <el-table-column
        label="序号"
        width="70"
        align="center">
        <template slot-scope="scope">
          {{ (page - 1) * limit + scope.$index + 1 }}
        </template>
      </el-table-column>

      <el-table-column prop="movieName" label="影片名" width="200" />

      <el-table-column prop="movieType" label="影片类型" width="200" />

      <el-table-column prop="movieIntroduction" label="影片简介"/>

      <el-table-column label="操作" width="230" align="center">
        <template slot-scope="scope">
          <router-link :to="'/cinemaMoi/movie/update/'+scope.row.movieId">
            <el-button type="primary" size="mini" icon="el-icon-edit" v-if="hasPerm('movie.update')">修改</el-button>
          </router-link>
      
              <!-- <el-button type="danger" 
              size="mini" icon="el-icon-delete" slot="reference" v-if="hasPerm('user.remove')"></el-button> -->
      
             <el-button type="danger" size="mini" icon="el-icon-delete" @click="open(scope.row.movieId)" v-if="hasPerm('movie.update')">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页组件 -->
    <el-pagination
      :current-page="page"
      :total="total"
      :page-size="limit"
      style="padding: 30px 0; text-align: center;"
      layout="total, prev, pager, next, jumper"
      @current-change="fetchData"
    />
  </div>
</template>

<script>
import movie from '@/api/cinema/movie'

export default {
  data() {
        return {
          list:null,//查询之后接口返回集合
          page:1,//当前页
          limit:5,//每页记录数
          total:0,//总记录数
          movieQuery:{},//条件封装对象
        }
  },

  // 生命周期函数：内存准备完毕，页面尚未渲染
  created() {
    //调用
    this.fetchData(); 
  },
  methods: {
      //初始化数据
      fetchData(page=1){
            this.page = page
            movie.getMovies(this.movieQuery, this.page,this.limit)
                .then(response =>{//请求成功
                    this.list = response.data.movies
                    this.total = response.data.total
                    console.log(this.list)
                    console.log(this.total)
                })
      },
      resetData() {//清空的方法
        //表单输入项数据清空
            this.movieQuery = {}
            //查询所有讲师数据
            this.fetchData()
        },
        //删除的方法
       removeDataById(movieId){
         movie.removeMovie(movieId)
         .then(response=>{
            //提示信息
            this.$message({
            type: 'success',
            message: '删除成功!'
            });
           //回到列表页面
          this.fetchData();           
         })
       },
       //确认删除
       open(movieId) {
          this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(() => {
              this.removeDataById(movieId)
            })
          }, 
    }
  }
</script>


