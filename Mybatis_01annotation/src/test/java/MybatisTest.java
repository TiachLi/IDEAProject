
import Utils.MybatisUtils;
import org.junit.Test;

import java.io.IOException;

public class MybatisTest {
    @Test
    public void test1() throws IOException {
        MybatisUtils mybatisUtils =new MybatisUtils();
        mybatisUtils.findAll();
    }
}
