package com.company;

import org.junit.Test;
import sun.reflect.generics.tree.Tree;

import java.util.*;

public class Main {
@Test
public  void Set(){
    HashSet<String> hashSet =new HashSet();
    LinkedHashSet<String>  linkedHashSet =new LinkedHashSet();
    TreeSet<String> treeSet =new TreeSet();

    ArrayList<String> list =new ArrayList<String>();
    list.add("A");
    list.add("C");
    list.add("D");
    list.add("B");
    list.add("BD");
    for(String a:list){
        hashSet.add(a);
        linkedHashSet.add(a);
        treeSet.add(a);
    }
    //不保证有序
    System.out.println("Ordering in HashSet :" + hashSet);
    //FIFO保证安装插入顺序排序
    System.out.println("Order of element in LinkedHashSet :" + linkedHashSet);
    //内部实现排序
    System.out.println("Order of objects in TreeSet :" + treeSet);
}

@Test
    public void testMap(){
    HashMap<String,String[]> hashmap =new HashMap<String, String[]>();
    String[] a={"a","b"};
    String[] b={ "c","d"};
    String[] c={ "e","f"};
    hashmap.put("1",a);
    hashmap.put("2",b);
    hashmap.put("3",c);
    //entrySet(),把所有的Map打包成一个set集合
    /*Set<Map.Entry<String, String[]>> entrySet = hashmap.entrySet();
    System.out.println(entrySet);*/

    /*
    values()获取所有的value值
     */
   /*Collection<String[]> values = hashmap.values();
    Iterator it =values.iterator();
    while (it.hasNext()){
        System.out.println(it.next());
    }
    for(String[] strings:hashmap.values()){
      for(int i=0; i< strings.length ;i++)
    System.out.println(strings[i]);
          }*/


    /*
    获取所有的key
     */
  /*  Set<String> keySet = hashmap.keySet();
    Iterator<String> iterator = keySet.iterator();
    while (iterator.hasNext()){
        System.out.println(iterator.next());
    }*/
     //遍历Map

    Set<Map.Entry<String, String[]>> entrySet = hashmap.entrySet();
  /*  Iterator<Map.Entry<String, String[]>> entryIterator = entrySet.iterator();
    while (entryIterator.hasNext()){
        Map.Entry<String, String[]> next = entryIterator.next();
        System.out.println("key"+next.getKey());
        String[] value = next.getValue();
        for(int i=0; i< value.length ;i++)
            System.out.println(value[i]);
    }*/
   for(Map.Entry<String,String[]> entry: hashmap.entrySet()){
       System.out.println(entry.getKey());
       for (String value:  entry.getValue()){
           System.out.println(value);
       }
   }
}
}
