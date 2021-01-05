import request from '@/utils/request'


export default {
  //获取所有会员名
  getAllVipName(){
    return request({
      url: `/cinema/vip/getAllVip`,
      method: 'get',  
    })
  },

  //添加会员信息
  addVip(vip){
    return request({
      url: `/cinema/vip/addVip`,
      method: 'post',
      data: vip     
    })
  },
  //更新会员信息
  updateVip(vip){
    return request({
      url: `/cinema/vip/updateVip`,
      method: 'post',
      data: vip     
    })
  },
  // 按条件获取会员信息
   getVips(vipVo,current,limit) {
    return request({
     url: `/cinema/vip/getVips/${current}/${limit}`,
     method: 'post',
     data: vipVo
    })
  },
  // 根据id删除会员
  removeVip(vipId) {
    return request({
      url: `/cinema/vip/removeVip/${vipId}`,
      method: 'delete',    
    })
  },
    // 根据id删除会员
  removeVip(vipId) {
    return request({
      url: `/cinema/vip/removeVip/${vipId}`,
      method: 'delete',    
    })
  },
  // 根据id获取会员
  getVip(vipId) {
    return request({
      url: `/cinema/vip/getVip/${vipId}`,
      method: 'get',    
    })
  },        
}
