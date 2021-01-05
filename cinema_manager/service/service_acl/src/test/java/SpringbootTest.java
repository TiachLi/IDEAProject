import com.atguigu.aclservice.ServiceAclApplication;
import com.atguigu.aclservice.entity.User;
import com.atguigu.aclservice.service.UserService;
import com.atguigu.aclservice.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServiceAclApplication.class)
public class SpringbootTest {
    @Resource
    UserService userService;

    @Test
    public void  test1(){
    }
}
