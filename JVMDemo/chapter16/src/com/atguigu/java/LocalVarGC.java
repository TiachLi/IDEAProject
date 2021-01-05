package com.atguigu.java;

/**
 * @author shkstart  shkstart@126.com
 * @create 2020  14:57
 */
public class LocalVarGC {
    public void localvarGC1() {
        byte[] buffer = new byte[10 * 1024 * 1024];//10MB
        System.gc();
    }

    public void localvarGC2() {
        byte[] buffer = new byte[10 * 1024 * 1024];
        buffer = null;
        System.gc();
    }

    public void localvarGC3() {
        {
            byte[] buffer = new byte[10 * 1024 * 1024];
        }
        System.gc();
    }

    public String localvarGC4() {
        {
            byte[] buffer = new byte[10 * 1024 * 1024];
        }
        String value= new String("a") ;
        System.out.println(value.hashCode());
        return value;
    }

    public void localvarGC5() {
        localvarGC1();
        System.gc();
    }

    public static void main(String[] args) {
        LocalVarGC local = new LocalVarGC();
        String s = local.localvarGC4();
        System.out.println(s);
    }
}
