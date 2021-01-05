<template>
  <div class="app-container">
    <!-- 查询表单 -->
    <!--查询表单-->
    <el-form :inline="true" class="demo-form-inline">

    <el-form-item label="影片名"  >
     <el-select 
       v-model="queryMovieName"
        placeholder="请选择">
          <el-option
              v-for="name in movieNameList"
                :key="name"
                :label="name"
                :value="name" />
            </el-select>
      </el-form-item> 
     <el-button type="primary" icon="el-icon-search" @click="fetchData()">查询</el-button>
    </el-form>

    <!-- 工具条 -->
    <div>
      <router-link :to="'/cinemaPli/playList/add'">
         <el-button type="primary" size="mini" icon="el-icon-info" v-if="hasPerm('playList.add')">添加</el-button>
      </router-link>
    </div>

    <!-- 场次列表 -->
    <el-table
      :data="list"
      stripe
      style="width: 100%">

      <el-table-column
        type="selection"
        width="55" />

      <el-table-column
        label="序号"
        width="70"
        align="center">
        <template slot-scope="scope">
          {{ (page - 1) * limit + scope.$index + 1 }}
        </template>
      </el-table-column>

      <el-table-column prop="movieName" label="电影名" width="200">
      
      </el-table-column>

      <el-table-column prop="playListTime" label="放映时间" width="200" />

      <el-table-column prop="hallName" label="影厅"/>

      <el-table-column prop="ticketPrice" label="票价"/>

      <el-table-column label="操作" width="300" align="center">
        <template slot-scope="scope">
          <router-link :to="'/cinemaTic/ticket/show/'+scope.row.playListId+'?hn='+scope.row.hallName">
            <el-button type="primary" size="mini" icon="el-icon-edit" v-if="hasPerm('ticket.show')" >选票</el-button>
          </router-link>
          <router-link :to="'/cinemaPli/playList/update/'+scope.row.playListId">
            <el-button type="primary" size="mini" icon="el-icon-edit" v-if="hasPerm('playList.update')">修改</el-button>
          </router-link>
             <el-button type="danger" size="mini" icon="el-icon-delete" @click="open(scope.row.playListId)" v-if="hasPerm('playList.update')">删除</el-button>
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
import playListApi from '@/api/cinema/playList'
import movieApi from '@/api/cinema/movie'

export default {
  data() {
        return {
          list:null,//查询之后接口返回集合
          page:1,//当前页
          limit:5,//每页记录数
          total:0,//总记录数
          movieNameList:"",
          queryMovieName:'',//条件封装对象
        }
  },

  // 生命周期函数：内存准备完毕，页面尚未渲染
  created() {
    this.init()  

  },
  methods: {
    //初始化数据
      init(){
          
        //获取影片名列表  
         //设置影片名
         movieApi.getAllMovieName()
         .then(response=>{
             this.movieNameList=response.data.movieNameList
             this.queryMovieName=response.data.movieNameList[0]
                        //获取页面数据  
                 this.fetchData();        
        }) 
      },


      fetchData(page=1){
            this.page = page
            playListApi.getPlayLists(this.queryMovieName, this.page,this.limit)
                .then(response =>{//请求成功
                    this.list = response.data.playLists
                    this.total = response.data.total
                })
      },

        //删除的方法
       removeDataById(playListId){
         playListApi.removePlayList(playListId)
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
       open(playListId) {
          this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(() => {
              this.removeDataById(playListId)
            })
          }, 
    }
  }
</script>


