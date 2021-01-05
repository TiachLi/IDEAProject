package dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dao.ProductDao;
import entity.Product;
public class ProductDaoImpl extends BaseDaoImpl implements ProductDao {
    public List<Product> getAll() {
        return this.convert(this.executeQuery("select pID,pName,Sales,Price,Allowance from [Product]" ));
    }

    public boolean updateProduct(Product product) {
        String sql = "update [Product] set price = ?,allowance=? where pID = ?";
        String value[] = new String[3];
        value[0] = String.valueOf(product.getPrice());
        value[1] = String.valueOf(product.getAllowance());
        value[2] = String.valueOf(product.getpID());
        executeSql(sql,value);
        return false;
    }

    public void insertProduct(Product product) {
        String sql = "insert into [product] values (?,?,?,?,?)";
        String value[] = new String[5];
        value[0] = String.valueOf(product.getpID());
        value[1] = String.valueOf(product.getpName());
        value[2] = String.valueOf(product.getPrice());
        value[3] = String.valueOf(product.getSales());
        value[4] = String.valueOf(product.getAllowance());
        executeSql(sql,value);
    }

    private List<Product> convert(List<Map<String,Object>> list){
        List<Product> orderList=new ArrayList<Product>();
        for(Map<String,Object> map:list) {
            Product products =new Product();
            products.setpID(String.valueOf(map.get("pID")));
            products.setpName(map.get("pName").toString());
            products.setSales((Integer) map.get("Sales"));
            products.setPrice((Integer) map.get("Price"));
            products.setAllowance((Integer) map.get("Allowance"));
            orderList.add(products);
        }
        return orderList;
    }
}
