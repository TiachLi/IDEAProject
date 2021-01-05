package dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import dao.OrderDao;
import entity.Order;

public class OrderDaoImpl extends BaseDaoImpl implements OrderDao {
    public List<Order> getAll() {
        return this.convert(this.executeQuery("select orderID,[Order].pID,pName,uID,num,allPrice ,date from [Order],[Product] where [Order].pID = [Product].pID"));
    }

    public List<Order> getOrderByID(String uID) {
        return this.convert(this.executeQuery("select orderID,[Order].pID,pName,uID,num, allPrice,date from [Order],[Product] where uID = " + "'" + uID + "'" + "and [Order].pID = [Product].pID"));
    }

    public boolean insertOrder(Order order) {
        String sql = "insert into [Order](pID,uID,num,allPrice,date) values(?,?,?,?,?);";
        String updatesql = "UPDATE [Product] SET allowance = (allowance - ?) ,sales = (sales + ?) WHERE pID = ?";
        String getProductSQL = "select price from [Product] where pID = ?";
        Boolean IsSuccess = false;
        String ProductValue[] = new String[3];
        ProductValue[0] = String.valueOf(order.getNum());
        ProductValue[1] = String.valueOf(order.getNum());
        ProductValue[2] = order.getpID();
        String value[] = new String[5];
        String getProductValue[] = new String[1];
        getProductValue[0] = order.getpID();
        value[0] = order.getpID();
        value[1] = order.getuID();
        value[2] = String.valueOf(order.getNum());
        value[4] =  new Timestamp(new Date().getTime()).toString();
        try {
            boolean autoCommit = conn.getAutoCommit();
            if (autoCommit)
            conn.setAutoCommit(false);
            List<Map<String,Object>> list = executeQuery(getProductSQL,getProductValue);
            value[3] = String.valueOf((Integer) list.get(0).get("price") * order.getNum());
            executeSql(sql,value);
            executeSql(updatesql,ProductValue);
            conn.commit();
            conn.setAutoCommit(autoCommit);
            IsSuccess = true;
        } catch (SQLException throwables) {
            IsSuccess = false;
            if (conn!=null){
                try {
                    conn.rollback();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            throwables.printStackTrace();
        }
        return IsSuccess;
    }
    private List<Order> convert(List<Map<String,Object>> list){
        List<Order> orderList=new ArrayList<Order>();
        for(Map<String,Object> map:list) {
            Order order=new Order();
            order.setuID(String.valueOf(map.get("uID")));
            order.setpID(map.get("pID").toString());
            order.setOrderID(String.valueOf(map.get("orderID")));
            order.setAllprice((Integer) map.get("allPrice"));
            order.setNum((Integer) map.get("num"));
            order.setDate(((Date)map.get("date")).toString());
            order.setProductName((map.get("pName").toString()));
            orderList.add(order);
        }
        return orderList;
    }

}
