package com.atguigu.mapper;

import com.atguigu.entity.Ticket;
import com.atguigu.entity.vo.SelectedSeatVo;
import com.atguigu.entity.vo.TicketInfoVo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface TicketMapper {
    //添加电影票信息
    @Insert("insert into ticket(user_id,seat_id,playList_id,final_price,is_paid,customer_phone,sell_time) " +
            "values(#{userId},#{seatId}," +
            " #{playListId},#{finalPrice}," +
            " #{isPaid}, #{customerPhone} ,#{sellTime})")
    void insertTicket(Ticket ticket);
    //根据id删除电影票信息
    @Delete("delete from ticket where ticket_id=#{ticketId}")
    void deleteById(String ticketId);
    //获取全部电影票信息
    @Select("select * from ticket")
    List<Ticket> getAllTickets();
    //根据影厅名查询
    @Select("select * from ticket where hall_name=#{hallName}")
    List<Ticket> selectByHallName(String hallName);
    //获得最终电影票信息
    @Select("call selectFinalTicketInfo (#{playListId},#{customerPhone})")
    @Options(statementType= StatementType.CALLABLE)
    List<TicketInfoVo> selectFinalTicketInfo(@Param("playListId") String playListId, @Param("customerPhone") String customerPhone);
    //设置票据为支付状态
    @Update("update ticket set is_paid=1 where ticket_id =#{ticketId}")
    void updateIsPaid(String ticketId);
    //根据顾客手机号查询大于当前时间的票据信息
    @Select("call selectToCurrentWithCustomer (#{customerPhone},#{currentTime})")
    @Options(statementType= StatementType.CALLABLE)
    List<TicketInfoVo> selectToCurrentWithCustomer( @Param("customerPhone") String customerPhone,@Param("currentTime") String currentTime);


}
