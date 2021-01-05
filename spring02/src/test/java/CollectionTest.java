import domain.Account;
import domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "classpath:spring.xml")
public class CollectionTest {
    @Resource
    User collection;

    @Autowired
    Account account;
    @Test
    public void test1(){
        List<String> list =new ArrayList<>();
        String name = account.getName();
        System.out.println(name);
    }
}
