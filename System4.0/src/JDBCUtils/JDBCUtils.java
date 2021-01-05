package JDBCUtils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtils {
    //创建一个空的连接池对象
    private static DataSource ds;
  static {
      //加载配置文件

      try {
          Properties pro =new Properties();
        pro.load(JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties"));
          //初始化连接池对象
          ds = DruidDataSourceFactory.createDataSource(pro);
      } catch (IOException e) {
        e.printStackTrace();
      } catch (Exception e) {
          e.printStackTrace();
      }

  }

    //返回连接池对象
    public static DataSource getDatasource(){
      return ds;
    }
    //创建连接
   public static Connection getConnectino() throws SQLException {
    return ds.getConnection();
  }
}
