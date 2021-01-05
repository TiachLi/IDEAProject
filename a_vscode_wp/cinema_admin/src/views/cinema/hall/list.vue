<template>
  <div class="app-container">
    <!-- 查询表单 -->
    <!--查询表单-->
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item>
         <el-input v-model="hallQuery.hallName" placeholder="影厅名"/>
      </el-form-item>
      <el-form-item>
         <el-input v-model="hallQuery.hallLevel" placeholder="影厅等级"/>
      </el-form-item>
      <el-button type="primary" icon="el-icon-search" @click="fetchData()">查询</el-button>
      <el-button type="default" @click="resetData()">清空</el-button>
    </el-form>

    <!-- 工具条 -->
    <div>
      <router-link :to="'/cinemaHal/hall/add'">
         <el-button type="primary" size="mini" icon="el-icon-info" v-if="hasPerm('hall.add')">添加</el-button>
      </router-link>
    </div>

    <!-- 影厅列表 -->
    <el-table
      v-loading="listLoading"
      :data="list"
      stripe
      style="width: 100%"
      @selection-change="handleSelectionChange">

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

      <el-table-column prop="hallName" label="影厅名" width="200" />

      <el-table-column prop="hallLevel" label="影厅等级" width="200" />

      <el-table-column prop="seatCount" label="可用座位数" width="100" />

      <el-table-column label="操作"  align="center">
        <template slot-scope="scope">
          <router-link :to="'/cinemaHal/hall/update/'+scope.row.hallId">
            <el-button type="primary" size="mini" icon="el-icon-edit" v-if="hasPerm('hall.update')">修改基本信息</el-button>
          </router-link>

          <router-link :to="'/cinemaHal/seat/update?hn='+scope.row.hallName">
            <el-button type="primary" size="mini" icon="el-icon-edit" v-if="hasPerm('seat.update')">修改座位信息</el-button>
          </router-link>
            
             <el-button type="danger" size="mini" icon="el-icon-delete" @click="open(scope.row.hallId)" v-if="hasPerm('hall.remove')">删除</el-button>
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
import hall from '@/api/cinema/hall'

export default {
  data() {
        return {
          list:null,//查询之后接口返回集合
          page:1,//当前页
          limit:5,//每页记录数
          total:0,//总记录数
          hallQuery:{},//条件封装对象
          deleteVisible:false,
        }
  },

  // 生命周期函数：内存准备完毕，页面尚未渲染
  created() {
    //调用
    this.fetchData(); 
  },
  methods: {
      fetchData(page=1){
            this.page = page
            hall.getHalls(this.hallQuery, this.page,this.limit)
                .then(response =>{//请求成功
                    this.list = response.data.halls
                    this.total = response.data.total
                    console.log(this.list)
                    console.log(this.total)
                })
      },
      resetData() {//清空的方法
        //表单输入项数据清空
            this.hallQuery = {}
            //查询所有讲师数据
            this.fetchData()
        },


       removeDataById(hallId){
         hall.removeHall(hallId)
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
       open(hallId) {
          this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(() => {
              this.removeDataById(hallId)
            })
          }, 
     

  }
}
</script>


