import request from '@/utils/request'


export default {
  //获取所有影片名
  getAllMovieName(){
    return request({
      url: `/cinema/movie/getAllMovieName`,
      method: 'get',  
    })
  },

  //添加影片信息
  addMovie(movie){
    return request({
      url: `/cinema/movie/addMovie`,
      method: 'post',
      data: movie     
    })
  },
  //更新影片信息
  updateMovie(movie){
    return request({
      url: `/cinema/movie/updateMovie`,
      method: 'post',
      data: movie     
    })
  },
  // 按条件获取影片信息
   getMovies(movieVo,current,limit) {
    return request({
     url: `/cinema/movie/getMovies/${current}/${limit}`,
     method: 'post',
     data: movieVo
    })
  },
  // 根据id删除影片
  removeMovie(movieId) {
    return request({
      url: `/cinema/movie/removeMovie/${movieId}`,
      method: 'delete',    
    })
  },
    // 根据id删除影片
  removeMovie(movieId) {
    return request({
      url: `/cinema/movie/removeMovie/${movieId}`,
      method: 'delete',    
    })
  },
  // 根据id获取影片
  getMovie(movieId) {
    return request({
      url: `/cinema/movie/getMovie/${movieId}`,
      method: 'get',    
    })
  },        
}
