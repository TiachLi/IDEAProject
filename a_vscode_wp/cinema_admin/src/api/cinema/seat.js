import request from '@/utils/request'


export default {
  //添加座位信息
  addSeats(seavVo){
    return request({
      url: `/cinema/seat/addSeats`,
      method: 'post',
      data: seavVo     
    })
  },
  //更新座位信息
  updateSeats(seavVo){
    return request({
      url: `/cinema/seat/updateSeats`,
      method: 'post',
      data: seavVo     
    })
  },
  //获取某场次全部座位信息
  getAllSeatsWithPl(playListId,hallName){
    return request({
        url: `/cinema/seat/getAllSeatsWithPl/${playListId}/${hallName}`,
        method: 'get',     
      })     
  },
  //获取全部座位信息    
  getAllSeats(hallName){
    return request({
        url: `/cinema/seat/getAllSeats/${hallName}`,
        method: 'get',     
      })     
  },
  
    //设置座位不可用座位信息    
    setIsUsable(ids){
      return request({
          url: `/cinema/seat/setIsUsable`,
          method: 'post',
          data:ids     
        })     
    }, 
}
