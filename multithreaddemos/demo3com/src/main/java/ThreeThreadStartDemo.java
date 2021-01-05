import java.util.Date;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class ThreeThreadStartDemo {
    private CyclicBarrier cyclicBarrier = new CyclicBarrier(3);//参数是参与CyclicBarrier的线程数

    public void startThread(){
        //1.打印线程准备启动
        String name = Thread.currentThread().getName();
        System.out.println(name+" preparing");
        //2.调用CyclicBarrier的await方法登台线程全部准备完成
        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        //3.打印线程启动完毕信息
        System.out.println(name+" work done: "+new Date().getTime());
    }

    public static void main(String[] args){
        final ThreeThreadStartDemo threeThreadStartDemo = new ThreeThreadStartDemo();
        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                threeThreadStartDemo.startThread();
            }
        },"player1");
        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                threeThreadStartDemo.startThread();
            }
        },"player2");
        Thread thread3 = new Thread(new Runnable() {
            public void run() {
                threeThreadStartDemo.startThread();
            }
        },"player3");

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
