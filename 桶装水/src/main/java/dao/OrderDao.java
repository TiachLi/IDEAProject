package dao;
import entity.Order;
import java.util.List;

public interface OrderDao extends BaseDao {
    public List<Order> getAll();
    public List<Order> getOrderByID(String uIP);
    public boolean insertOrder(Order order);
}
