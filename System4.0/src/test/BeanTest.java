package test;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;
import User.user;

import java.lang.reflect.InvocationTargetException;

public class BeanTest {
    @Test
    public void test(){

        user use = new user();
        try {
            BeanUtils.setProperty(use,"Username","wl");
            System.out.println(use);

            String gender = BeanUtils.getProperty(use, "Username");
            System.out.println(gender);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }





    }
}
