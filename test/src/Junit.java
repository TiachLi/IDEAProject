import com.sun.org.apache.xalan.internal.xsltc.dom.SortingIterator;
import org.junit.Test;

import java.io.*;
import java.lang.reflect.Method;
import java.util.*;

public class Junit {
    @Test
    public void test1() throws Exception {
        Father father=new Son();

        System.out.println(father instanceof Father);

        Son son =new Son();
        son.sonEat(father);

        father.eat();
    }

    @Test
    public void test2(){
        (new Father(){
            public void eatHide()
            {
                System.out.println("hide name");
            }
        }).eatHide();
    }
    @Test
    public void test3(){

        try {
           int a= 1/0;
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("internal");
    }

    public class testExp extends Exception{

    }
    @Test
    public void test4(){
        String s="abcde";
        int a=0;
        StringBuffer s1=new StringBuffer("abcde");
        System.out.println(s.equals(a));
        System.out.println(s1.equals(s));
    }

    @Test
    public void test5(){
        int i , j ;
        int a[ ] = { 5,9,6,8,7};
        for ( i = 0 ; i < a.length-1; i ++ ) {

            int k = i;
            for ( j = i ; j < a.length ; j++ )
                if ( a[j]<a[k] ) k = j;
            int temp =a[i];
            a[i] = a[k];
            a[k] = temp;
        }
        for ( i =0 ; i<a.length; i++ )
            System.out.print(a[i]+" ");
        System.out.println( );
        }

     @Test
    public void test6() throws IOException {

    File f =new File("text.txt");
    f.createNewFile();


    String[] a =new String[4];
    System.out.println(a.length);
    a[0]="abv";
    a[0].length();
    }

    @Test
    public void test7(){
        String s1="abc";
        s1+=100;
        int len =s1.length();
        System.out.println(s1);

        int a=0;
        int b=1;
        if (a!=b){

        }
        Integer c =1;
        System.out.println(c instanceof Integer);
    }

    @Test
    public void test8(){
        List list =new ArrayList();
        list.add("a");
        list.add("b");


        Set set=new HashSet();
        set.add("1");
        set.add("1");

        Map<String,Object> map =new HashMap();
        map.put("a","1");
        map.put("b","2");
        map.put("b","3");
        map.forEach((key, value) -> {
            System.out.println(key);
        });

    }

}
