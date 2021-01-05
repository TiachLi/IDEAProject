<template>
  <div class="main">
    <div class="title">
      <a class="active" href="/login">登录</a>
      <span>·</span>
      <a href="/register">注册</a>
    </div>

    <div class="sign-up-container">
      <el-form ref="userForm" :model="user">

        <el-form-item class="input-prepend restyle" prop="mobile" :rules="[{ required: true, message: '请输入手机号码', trigger: 'oninput' },{validator: checkPhone, trigger: 'oninput'}]">
          <div >
            <el-input type="text" placeholder="手机号" v-model="user.mobile"/>
            <i class="iconfont icon-phone" />
          </div>
        </el-form-item>

        <el-form-item class="input-prepend" prop="password" :rules="[{ required: true, message: '请输入密码', trigger: 'blur' }]">
          <div>
            <el-input type="password" placeholder="密码" v-model="user.password"/>
            <i class="iconfont icon-password"/>
          </div>
        </el-form-item>

        <div class="btn">
          <input type="button" class="sign-in-button" value="登录" @click="openCheckCode()">
        </div>
      </el-form>
      <!-- 更多登录方式 -->
      <div class="more-sign">
        <h6>社交帐号登录</h6>
        <ul>
          <li><a id="weixin" class="weixin" target="_blank" href="http://qy.free.idcfengye.com/api/ucenter/weixinLogin/login"><i class="iconfont icon-weixin"/></a></li>
          <li><a id="qq" class="qq" target="_blank" href="#"><i class="iconfont icon-qq"/></a></li>
        </ul>
      </div>
    </div>

  <el-dialog :visible.sync="dialogCheckCodeVisible" title="请输入验证码">
  <el-form :model="video" label-width="120px">
    <el-form-item label="验证码">
      <el-input v-model="user.checkCode"/>

    <el-button @click="getCheckCode()">刷新验证码</el-button>
      <div class="demo-image">
    <div class="block" :key="fit">
    <span class="demonstration">{{ fit }}</span>
     <el-image
      style="width: 100px; height: 50px"
      :src="url"
      :fit="fit"></el-image>
  </div>
</div>
    </el-form-item>

  </el-form>

  <div slot="footer" class="dialog-footer">
    <el-button  type="primary" @click="submitLogin()">确 定</el-button>
    <el-button @click="dialogCheckCodeVisible = false">取 消</el-button>
  </div>
</el-dialog>


  </div>
</template>

<script>
  import '~/assets/css/sign.css'
  import '~/assets/css/iconfont.css'
  import cookie from 'js-cookie'
  import loginApi from '@/api/login'

  export default {
    layout: 'sign',

    data () {
      return {
        user:{
          mobile:'',
          password:'',
          checkCode:'',
        },
        fits: 'contain',
        baseCodeUrl:'http://localhost:9001/edumsm/msm/getCheckCode/',
        url: '',
        loginInfo:{},
        dialogCheckCodeVisible:false
      }
    },
    created(){
      this.user.mobile=''
    },
    methods: {
      //打开输入验证码的弹窗
      openCheckCode(){
        //打开弹窗
        this.dialogCheckCodeVisible=true
        //刷新验证码
        this.getCheckCode()
        //清空验证码
        this.user.checkCode=''
      },
      getCheckCode(){
        //刷新图片url
        this.url=this.baseCodeUrl+this.user.mobile+'?time='+new Date().getTime()
      },  
      submitLogin(){
        loginApi.memberLogin(this.user)
        .then(response => {
          if(response.data.code===20002){//如果验证码错误，弹窗不关闭
            this.$message({
              message: '验证码错误',
              type: 'error'
            })
          }
          if(response.data.code===20001){
            //关闭弹窗
            this.dialogCheckCodeVisible=false
            this.$message({
              message:response.data.message ,
              type: 'error'
            })            
          }
          if(response.data.code===20000){
             //获取token字符串放到cookie里面
             //第一个参数cookie名称，第二个参数值，第三个参数作用范围
             cookie.set('guli_token',response.data.data.token,{domain: 'localhost'})
              //根据token获取用户信息，为了首页面显示
              loginApi.getLoginUserInfo()
                .then(response => {
                  this.loginInfo = response.data.data.userInfo
                  //获取返回用户信息，放到cookie里面
                  cookie.set('guli_ucenter',this.loginInfo,{domain: 'localhost'})
                })
              //跳转页面
             window.location.href="/"
          } 
        })
      },

      checkPhone (rule, value, callback) {
        //debugger
        if (!(/^1[34578]\d{9}$/.test(value))) {
          return callback(new Error('手机号码格式不正确'))
        }
        return callback()
      }
    }
  }
</script>
<style>
   .el-form-item__error{
    z-index: 9999999;
  }
</style>