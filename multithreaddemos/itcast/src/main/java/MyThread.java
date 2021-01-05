import java.util.Date;

public class MyThread extends Thread {

    public void run(){
        for(int i=0; i<10; i++){
            System.out.println(Thread.currentThread().getName()+"执行时间："+new Date().getTime()+"；执行次数："+i);
        }
    }
}
