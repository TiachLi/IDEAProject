package com.atguigu.mapper;

import com.atguigu.entity.Vip;
import com.atguigu.entity.Vip;
import com.atguigu.entity.vo.VipVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface VipMapper {
    //添加会员信息
    @Insert("insert into vip values(#{vipId},#{vipName},#{vipPhone},#{vipDiscount})")
    void insertVip(Vip vip);
    //更新会员信息
    @Update("update vip set vip_name=#{vipName},vip_phone=#{vipPhone},vip_discount=#{vipDiscount} where vip_id=#{vipId}")
    void updateVip(Vip vip);
    //根据会员id删除会员信息
    @Delete("delete from vip where vip_id=#{vipId}")
    void deleteByVipId(String vipId);
    //获取全部会员信息
    @Select("select * from vip")
    List<Vip> getAllVips();
    //根据会员id查询
    @Select("select * from vip where vip_id=#{vipId}")
    Vip selectByVipId(String vipId);
    //根据会员手机号查询
    @Select("select * from vip where vip_phone=#{vipPhone}")
    Vip selectByVipPhone(String vipPhone);

    //条件查询会员
    @SelectProvider(type = com.atguigu.helpler.VipSqlHelper.class,method = "getVipsByVo")
    List<Vip> selectWithCondition(@Param("vipVo") VipVo vipVo, @Param("begin") int begin, @Param("end") int limit);
    //条件查询会员数量
    @SelectProvider(type = com.atguigu.helpler.VipSqlHelper.class,method = "getVipCountsByVo")
    Integer selectCountWithCondition(@Param("vipVo") VipVo vipVo);
}
