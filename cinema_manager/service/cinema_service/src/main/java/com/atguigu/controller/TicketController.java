package com.atguigu.controller;

import com.atguigu.commonutils.JwtUtils;
import com.atguigu.commonutils.R;
import com.atguigu.entity.Ticket;
import com.atguigu.entity.vo.TicketInfoVo;
import com.atguigu.entity.vo.TicketVo;
import com.atguigu.service.TicketService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/cinema/ticket/")
public class TicketController {
    @Resource
    TicketService ticketService;

    //生成电影票
    @PostMapping("creatTickets")
    public R creatTickets(@RequestBody TicketVo ticketVo, @RequestHeader(value="token") String token){
        String id = JwtUtils.getUserIdFromToken(token);
        ticketVo.setUserId(id);
        ticketService.saveTickets(ticketVo);
        return R.ok();
    }
    //获得最终确认信息
    @GetMapping("getTicketInfo/{playListId}/{customerPhone}")
    public R getTicketInfo(@PathVariable String playListId,@PathVariable String customerPhone ){
        List<TicketInfoVo> ticketInfoVos=ticketService.getFinalInfo(playListId,customerPhone);
        return R.ok().data("ticketList",ticketInfoVos);
    }
    //删除电影票
    @DeleteMapping("removeTicket/{ticketId}")
    public R removeTicket(@PathVariable String ticketId){
        ticketService.removeTicket(ticketId);
        return R.ok();
    }
    //更新支付状态
    @PostMapping("updateTicket/{ticketId}")
    public R updateIsPaid(@PathVariable String ticketId){
        ticketService.updateTicketPaid(ticketId);
        return R.ok();
    }
    //更新支付状态
    @PostMapping("getTicketCusToCurr/{customerPhone}")
    public R getTicketCusToCurr(@PathVariable String customerPhone){
        List<TicketInfoVo> ticketVoList=ticketService.getTicketCusToCurrent(customerPhone);
        return R.ok().data("ticketList",ticketVoList);
    }
}
