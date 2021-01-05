package com.atguigu.service;

import com.atguigu.entity.Ticket;
import com.atguigu.entity.vo.TicketInfoVo;
import com.atguigu.entity.vo.TicketVo;

import java.util.List;
import java.util.Map;

public interface TicketService {
    //添加电影票信息
    void saveTickets(TicketVo ticketVo);
    //获得影票最终确认信息
    List<TicketInfoVo> getFinalInfo(String playListId, String customerPhone);
    //删除电影票
    void removeTicket(String ticketId);
    //更新支付状态
    void updateTicketPaid(String ticketId);
    //根据顾客手机号获取大于当前时间的票据信息
    List<TicketInfoVo> getTicketCusToCurrent(String customerPhone);
}
