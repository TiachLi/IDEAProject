import request from '@/utils/request'


export default {
    //获取票房
    getTicketSales(movieName){
        return request({
            url:`/cinema/sta/getTicketSales/${movieName}`,
            method:'get'
        })
    },
    //获取营业额
    getFinalTicketPrice(begin,end){
        return request({
            url:`/cinema/sta/getFinalTicketPrice/${begin}/${end}`,
            method:'get'
        })
    }    
}