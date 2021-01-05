package com.sql;

import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlSelectQueryBlock;
import com.domain.House;
import com.domain.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.jdbc.SQL;

public class sqlCondition {


    public String queryOrderByParam(User user) {

        return new SQL(){
            {
                SELECT("userId,userName");
                FROM("USER");
                if (user.getUserName()!=null){
                    WHERE("userName=#{name}");
                }
            }

        }.toString();
    }
    public String queryWithCondition(int begin, int end,User user){
        String sql=new SQL(){
            {
                SELECT("*");
                FROM("user");
                if (user.getUserName()!=null&&!user.getUserName().equals("null")&&user.getUserName().length()>0){
                    WHERE(" userName like  "+"\'%"+user.getUserName()+"%\'");
                }
                if (user.getUserTel()!=null&&!user.getUserTel().equals("null")&&user.getUserTel().length()>0){
                    WHERE(" userTel = "+"\'"+user.getUserTel()+"\'");
                }
                if (user.getUserAddress()!=null&&!user.getUserAddress().equals("null")&&user.getUserAddress().length()>0){
                    WHERE(" userAddress like "+"\'%"+user.getUserAddress()+"%\'");
                }
                if (user.getUserAge()!=0){
                    WHERE(" userAge= "+user.getUserAge());
                }
                if (user.getUserEmail()!=null&&!user.getUserEmail().equals("null")&&user.getUserEmail().length()>0){
                    WHERE("userEmail like "+"\'%"+user.getUserEmail()+"%\'");
                }
                if (user.getUserGender()!=null&&!user.getUserGender().equals("null")&&user.getUserGender().length()>0){
                    WHERE("userGender like "+"\'%"+user.getUserGender()+"%\'");
                }
            }
        }.toString()+" limit "+begin+", "+end;
        return sql;
    }

    public String findTotalCountsWithCondition(User user){
        String sql=new SQL(){
            {
                SELECT("count(*)");
                FROM("user");
                if (user.getUserName()!=null&&!user.getUserName().equals("null")&&user.getUserName().length()>0){
                    WHERE(" userName like  "+"\'%"+user.getUserName()+"%\'");
                }
                if (user.getUserTel()!=null&&!user.getUserTel().equals("null")&&user.getUserTel().length()>0){
                    WHERE(" userTel = "+"\'"+user.getUserTel()+"\'");
                }
                if (user.getUserAddress()!=null&&!user.getUserAddress().equals("null")&&user.getUserAddress().length()>0){
                    WHERE(" userAddress like "+"\'%"+user.getUserAddress()+"%\'");
                }
                if (user.getUserAge()!=0){
                    WHERE(" userAge= "+user.getUserAge());
                }
                if (user.getUserEmail()!=null&&!user.getUserEmail().equals("null")&&user.getUserEmail().length()>0){
                    WHERE("userEmail like "+"\'%"+user.getUserEmail()+"%\'");
                }
                if (user.getUserGender()!=null&&!user.getUserGender().equals("null")&&user.getUserGender().length()>0){
                    WHERE("userGender like "+"\'%"+user.getUserGender()+"%\'");
                }
            }
        }.toString();
        return sql;
    }

    public String house_queryWithCondition(int begin, int end,House house){
        String sql=new SQL(){
            {
                SELECT("*");
                FROM("houses ");
                if (house.getHouseName()!=null&&!house.getHouseName().equals("null")&&house.getHouseName().length()>0){
                    WHERE(" houseName like  "+"\'%"+house.getHouseName()+"%\'");
                }
                if (house.getHousePrice()!=null&&!house.getHousePrice().equals("null")&&house.getHousePrice().length()>0){

                    WHERE(" housePrice like "+"\'%"+house.getHousePrice()+"%\'");
                }
                if (house.getHouseAddress()!=null&&!house.getHouseAddress().equals("null")&&house.getHouseAddress().length()>0){
                    WHERE(" houseAddress like "+"\'%"+house.getHouseAddress()+"%\'");
                }
                if (house.getHouseType()!=null&&!house.getHouseType().equals("null")&&house.getHouseType().length()>0){
                    WHERE(" houseType like "+"\'%"+house.getHouseType()+"%\'");
                }
                if (house.getHouseTel()!=null&&!house.getHouseTel().equals("null")&&house.getHouseTel().length()>0){
                    WHERE(" houseTel = "+"\'"+house.getHouseTel()+"\'");
                }
                if (house.getSubTel()!=null&&!house.getSubTel().equals("null")&&house.getSubTel().length()>0){
                    WHERE("subTel = "+"\'"+house.getSubTel()+"\'");
                }
            }
        }.toString()+" limit "+begin+" , "+end;
        return sql;
    }

    public String house_findTotalCountsWithCondition(House house){
        String sql=new SQL(){
            {
                SELECT("count(*)");
                FROM("houses");
                if (house.getHouseName()!=null&&!house.getHouseName().equals("null")&&house.getHouseName().length()>0){
                    WHERE(" houseName like  "+"\'%"+house.getHouseName()+"%\'");
                }
                if (house.getHousePrice()!=null&&!house.getHousePrice().equals("null")&&house.getHousePrice().length()>0){

                    WHERE(" housePrice like "+"\'%"+house.getHousePrice()+"%\'");
                }
                if (house.getHouseAddress()!=null&&!house.getHouseAddress().equals("null")&&house.getHouseAddress().length()>0){
                    WHERE(" houseAddress like "+"\'%"+house.getHouseAddress()+"%\'");
                }
                if (house.getHouseType()!=null&&!house.getHouseType().equals("null")&&house.getHouseType().length()>0){
                    WHERE(" houseType like "+"\'%"+house.getHouseType()+"%\'");
                }
                if (house.getHouseTel()!=null&&!house.getHouseTel().equals("null")&&house.getHouseTel().length()>0){
                    WHERE(" houseTel = "+"\'"+house.getHouseTel()+"\'");
                }
                if (house.getSubTel()!=null&&!house.getSubTel().equals("null")&&house.getSubTel().length()>0){
                    WHERE("subTel = "+"\'"+house.getSubTel()+"\'");
                }
            }
        }.toString();
        return sql;
    }
}
