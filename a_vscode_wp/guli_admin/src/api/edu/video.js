import request from '@/utils/request'
export default {

    //添加小节
    addVideo(video) {
        return request({
            url: '/eduservice/video/addVideo',
            method: 'post',
            data: video
          })
    },
    
    //删除小节
    deleteVideo(id) {
        return request({
            url: '/eduservice/video/'+id,
            method: 'delete'
          })
    },
    //根据id查询小节
    getVideoById(videoId) {
        return request({
            url: '/eduservice/video/'+videoId,
            method: 'get'
          })
    },
    //修改章节
    updateVideo(video) {
        return request({
            url: '/eduservice/video/updateVideo',
            method: 'post',
            data: video
          })
    },
    //删除视频
    removeVideoSource(videoSourceId) {
        return request({
            url: `/eduvod/video/removeAlyVideo/${videoSourceId}`,
            method: 'delete',
          })
    }    
}