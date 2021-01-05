import cn.hutool.core.util.PageUtil;
import org.junit.Test;

public class test {
    @Test
    public void test1(){
        int end = PageUtil.getEnd(2, 5);
        System.out.println(end);
    }

}
