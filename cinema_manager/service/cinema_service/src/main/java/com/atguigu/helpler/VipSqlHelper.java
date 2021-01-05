package com.atguigu.helpler;

import com.atguigu.entity.vo.VipVo;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

public class VipSqlHelper {
    //按条件查询会员信息
    public String getVipsByVo(VipVo vipVo, int begin, int end){
        String sql =new SQL(){
            {
                SELECT("* ");
                FROM("vip ");
                if (!StringUtils.isEmpty(vipVo.getVipName())){
                    WHERE(" vip_name like '%"+vipVo.getVipName()+"%'");
                }
                if (!StringUtils.isEmpty(vipVo.getVipPhone())){
                    WHERE(" vip_phone like '%"+vipVo.getVipPhone()+"%'");
                }
            }

        }.toString()+"limit "+ begin+","+end;
        return sql;
    }

    //按条件查询会员总量
    public String getVipCountsByVo(VipVo vipVo){
        String sql =new SQL(){
            {
                SELECT("count(*) ");
                FROM("vip ");
                if (!StringUtils.isEmpty(vipVo.getVipName())){
                    WHERE(" vip_name like '%"+vipVo.getVipName()+"%'");
                }
                if (!StringUtils.isEmpty(vipVo.getVipPhone())){
                    WHERE(" vip_phone like '%"+vipVo.getVipPhone()+"%'");
                }
            }

        }.toString();
        return sql;
    }
}
