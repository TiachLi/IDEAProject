import request from '@/utils/request'

export default {

  //登录的方法
  memberLogin(formItem) {
    return request({
      url: `/educenter/member/login`,
      method: 'post',
      data: formItem
    })
  },
  //根据token获取用户信息
  getLoginUserInfo() {
    return request({
      url: `/educenter/member/getMemberInfo`,
      method: 'get'
    })
  }
}