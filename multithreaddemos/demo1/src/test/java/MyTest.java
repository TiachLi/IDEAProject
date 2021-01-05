import com.sun.javafx.robot.FXRobotImage;
import org.junit.Test;

import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class MyTest {
    @Test
    public void test1(){
        MyThread myThread =new MyThread();
        myThread.start();
        for (int i = 0; i <10 ; i++) {
            System.out.println("主线程执行"+new Date().getTime());
        }
    }
    @Test
    public void test2() throws InterruptedException {
        Thread thread =new Thread(new MyRunnable());
        thread.start();
        for (int i = 0; i <10 ; i++) {
            Thread.sleep(100);
            System.out.println("主线程执行时间："+new Date().getTime());
        }

    }
    @Test
    public void test3(){
        FutureTask<String> task =new FutureTask<String>(new MyCallable());
        Thread thread =new Thread(task,"MyThread");
        thread.start();
        for (int i = 0; i <10; i++) {
            System.out.println(Thread.currentThread().getName()+"执行时间"+new Date().getTime());
        }
        try {
            String s = task.get();
            System.out.println(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void test4(){
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.execute(new MyRunnable());
        for (int i = 0; i <10 ; i++) {
            System.out.println(Thread.currentThread().getName()+"执行时间"+new Date().getTime());
        }
    }
    @Test
    public void test5(){
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.execute(new MyRunnable());
        for (int i = 0; i <10 ; i++) {
        System.out.println(Thread.currentThread().getName()+"执行时间"+new Date().getTime());
    }
}
}
