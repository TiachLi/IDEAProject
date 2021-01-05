import com.sun.corba.se.impl.orbutil.CorbaResourceUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Son extends Father {

    String name="son";
    public Son(){
        System.out.println("son constructor");
    }

    public void sonTest(){
        System.out.println("son only");
    }


    public void sonEat(ert ert){
        System.out.println("son Eat");
    }


    public int aFun(int a, int b){
        return 1;
    }

    public static void main(String[] args) {
        Son s =new Son();
        System.out.println(s.aFun(1,1));
        System.out.println(s.aFun(1f,2f));


    }
}
