package First.Second.Third;

import org.junit.Test;

public class test {

    @Test
    public void test1(){
        String path = test.class.getResource("jdbc2.properties").getPath();
        System.out.println(path);
        String path1=test.class.getResource("/jdbc.properties").getPath();
        System.out.println(path1);

        String path2=test.class.getClassLoader().getResource("jdbc.properties").getPath();
        System.out.println(path2);

    }

}
