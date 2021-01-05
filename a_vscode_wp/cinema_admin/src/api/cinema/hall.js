import request from '@/utils/request'


export default {
  //添加影厅信息
  addHall(hall){
    return request({
      url: `/cinema/hall/addHall`,
      method: 'post',
      data: hall     
    })
  },
  //更新影厅信息
  updateHall(hall){
    return request({
      url: `/cinema/hall/updateHall`,
      method: 'post',
      data: hall     
    })
  },
  // 按条件获取影厅信息
   getHalls(hallVo,current,limit) {
    return request({
     url: `/cinema/hall/getHalls/${current}/${limit}`,
     method: 'post',
     data: hallVo
    })
  },
  // 根据id删除影厅
  removeHall(hallId) {
    return request({
      url: `/cinema/hall/removeHall/${hallId}`,
      method: 'delete',    
    })
  },
    // 根据id删除影厅
  removeHall(hallId) {
    return request({
      url: `/cinema/hall/removeHall/${hallId}`,
      method: 'delete',    
    })
  },
  // 根据id获取影厅
  getHall(hallId) {
    return request({
      url: `/cinema/hall/getHall/${hallId}`,
      method: 'get',    
    })
  },
  //获取全部影厅名
  getAllHallNameList(){
    return request({
      url: `/cinema/hall/getAllHallNameList`,
      method: 'get',   
    })    
  }        
}