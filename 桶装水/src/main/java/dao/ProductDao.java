package dao;

import java.util.List;
import entity.Product;
public interface ProductDao extends BaseDao{
    public List<Product> getAll();
    public boolean updateProduct(Product product);
    public void insertProduct(Product product);
}
