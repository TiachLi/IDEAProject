<template>
  <div class="app-container">
    <!-- 查询表单 -->
    <!--查询表单-->
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item>
         <el-input v-model="vipQuery.vipName" placeholder="会员名"/>
      </el-form-item>
      <el-form-item>
         <el-input v-model="vipQuery.vipPhone" placeholder="会员手机号"/>
      </el-form-item>
      <el-button type="primary" icon="el-icon-search" @click="fetchData()">查询</el-button>
      <el-button type="default" @click="resetData()">清空</el-button>
    </el-form>

    <!-- 工具条 -->
    <div>
      <router-link :to="'/cinemaV/vip/add'">
         <el-button type="primary" size="mini" icon="el-icon-info" v-if="hasPerm('vip.add')">添加</el-button>
      </router-link>
    </div>

    <!-- 会员列表 -->
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

      <el-table-column prop="vipName" label="会员名" width="200" />

      <el-table-column prop="vipPhone" label="会员手机号" width="200" />

      <el-table-column prop="vipDiscount" label="会员折扣"/>

      <el-table-column label="操作" width="230" align="center">
        <template slot-scope="scope">
          <router-link :to="'/cinemaV/vip/update/'+scope.row.vipId">
            <el-button type="primary" size="mini" icon="el-icon-edit" v-if="hasPerm('vip.update')">修改</el-button>
          </router-link>
      
              <!-- <el-button type="danger" 
              size="mini" icon="el-icon-delete" slot="reference" v-if="hasPerm('user.remove')"></el-button> -->
      
             <el-button type="danger" size="mini" icon="el-icon-delete" @click="open(scope.row.vipId)" v-if="hasPerm('vip.update')">删除</el-button>
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
import vip from '@/api/cinema/vip'

export default {
  data() {
        return {
          list:null,//查询之后接口返回集合
          page:1,//当前页
          limit:5,//每页记录数
          total:0,//总记录数
          vipQuery:{},//条件封装对象
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
            vip.getVips(this.vipQuery, this.page,this.limit)
                .then(response =>{//请求成功
                    this.list = response.data.vips
                    this.total = response.data.total
                    console.log(this.list)
                    console.log(this.total)
                })
      },
      resetData() {//清空的方法
        //表单输入项数据清空
            this.vipQuery = {}
            //查询所有会员数据
            this.fetchData()
        },
        //删除的方法
       removeDataById(vipId){
         vip.removeVip(vipId)
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
       open(vipId) {
          this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(() => {
              this.removeDataById(vipId)
            })
          }, 
    }
  }
</script>


