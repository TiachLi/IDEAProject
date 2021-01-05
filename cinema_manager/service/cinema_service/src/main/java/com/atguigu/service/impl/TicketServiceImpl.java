package com.atguigu.service.impl;

import com.atguigu.entity.PlayList;
import com.atguigu.entity.Ticket;
import com.atguigu.entity.Vip;
import com.atguigu.entity.vo.TicketInfoVo;
import com.atguigu.entity.vo.TicketVo;
import com.atguigu.mapper.PlayListMapper;
import com.atguigu.mapper.TicketMapper;
import com.atguigu.mapper.VipMapper;
import com.atguigu.service.TicketService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("ticketService")
public class TicketServiceImpl implements TicketService {
    @Resource
    TicketMapper ticketMapper;
    @Resource
    VipMapper vipMapper;
    @Resource
    PlayListMapper playListMapper;

    //添加电影票
    @Override
    public void saveTickets(TicketVo ticketVo) {
        List<String> selectedSeatList = ticketVo.getSelectedSeat();
        //转成set去重
        Set<String> selectedSeats = new HashSet<>(selectedSeatList);
        //根据场次获得票价
        float ticketPrice = playListMapper.selectTicketPrice(ticketVo.getPlayListId());
        for (String seatId : selectedSeats) {
            Ticket ticket = new Ticket();
            ticket.setCustomerPhone(ticketVo.getCustomerPhone());
            ticket.setIsPaid(0);
            ticket.setPlayListId(ticketVo.getPlayListId());
            ticket.setUserId(ticketVo.getUserId());
            ticket.setSeatId(seatId);
            //判断是否会员 设置最终票价
            Vip vip = vipMapper.selectByVipPhone(ticketVo.getCustomerPhone());
            if (vip!=null){
                float vipDiscount = vip.getVipDiscount();
                ticket.setFinalPrice(ticketPrice*vipDiscount/10);
            }else {
                ticket.setFinalPrice(ticketPrice);
            }
            Date current = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String format = simpleDateFormat.format(current);
            ticket.setSellTime(format);
            ticketMapper.insertTicket(ticket);
        }
    }
    //获取最终确认信息
    @Override
    public List<TicketInfoVo> getFinalInfo(String playListId, String customerPhone) {
        List<TicketInfoVo> ticketInfoVos = ticketMapper.selectFinalTicketInfo(playListId, customerPhone);
        return ticketInfoVos;
    }
    //删除影票
    @Override
    public void removeTicket(String ticketId) {
        ticketMapper.deleteById(ticketId);
    }
    //更新支付状态
    @Override
    public void updateTicketPaid(String ticketId) {
        ticketMapper.updateIsPaid(ticketId);
    }
    //

    @Override
    public List<TicketInfoVo> getTicketCusToCurrent(String customerPhone) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(date);
        return ticketMapper.selectToCurrentWithCustomer(customerPhone,format);
    }
}
