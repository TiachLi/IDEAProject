package com;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)  {
        Scanner scanner =new Scanner(System.in);
        String command;
        String methodName;
        String methodPara;
        FileSystem fileSystem=new FileSystem("c");
        do {
            command=scanner.next();
            String reg="<|>";
            String[] split = command.split(reg);
            if (split.length>1){
                methodName=split[0];
                methodPara=split[1];
            } else {
                methodName=split[0];
                methodPara=null;
            }
                Method method;
                try {
                    method = FileSystem.class.getDeclaredMethod(methodName, String.class);
                    method.invoke(fileSystem,methodPara);
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }while (!command.equals("exit"));
    }
}
