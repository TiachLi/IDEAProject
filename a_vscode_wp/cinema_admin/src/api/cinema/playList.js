import request from '@/utils/request'


export default {
  //添加场次信息
  addPlayList(playList){
    return request({
      url: `/cinema/playList/addPlayList`,
      method: 'post',
      data: playList     
    })
  },
  //更新场次信息
  updatePlayList(playList){
    return request({
      url: `/cinema/playList/updatePlayList`,
      method: 'post',
      data: playList     
    })
  },
  // 按条件获取场次信息
   getPlayLists(movieName,current,limit) {
    return request({
     url: `/cinema/playList/getPlayLists/${current}/${limit}`,
     params: {movieName}
    })
  },
  // 根据id删除场次
  removePlayList(playListId) {
    return request({
      url: `/cinema/playList/removePlayList/${playListId}`,
      method: 'delete',    
    })
  },
  // 根据id获取场次
  getPlayList(playListId) {
    return request({
      url: `/cinema/playList/getPlayList/${playListId}`,
      method: 'get',    
    })
  },        
}
