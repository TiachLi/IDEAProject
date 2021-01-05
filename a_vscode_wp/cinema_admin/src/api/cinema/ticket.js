import request from '@/utils/request'


export default {
    //生成电影票
    creatTickets(ticketVo ){
        return request({
            url: `/cinema/ticket/creatTickets`,
            method: 'post',
            data: ticketVo     
          })
    },
    //获取最终票据信息
    getFinalTicketInfo(playListId,customerPhone){
        return request({
            url: `/cinema/ticket/getTicketInfo/${playListId}/${customerPhone}`,
            method: 'get',  
          })        
    },
    //删除票据
    removeTicket(ticketId){
        return request({
            url: `/cinema/ticket/removeTicket/${ticketId}`,
            method:'delete',  
          })      
    },
    //更新支付状态
    updateTicket(ticketId){
        return request({
            url: `/cinema/ticket/updateTicket/${ticketId}`,
            method:'post',  
          })      
    },
    //获取当前用户已选票
    getTicketWithCustomerPhone(customerPhone){
        return request({
            url: `/cinema/ticket/getTicketCusToCurr/${customerPhone}`,
            method:'post',  
          })
    }
}