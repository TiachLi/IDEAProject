import java.util.concurrent.CountDownLatch;

public class RacerAndCoach  {

    CountDownLatch countDownLatch= new CountDownLatch(3);
    public void racer(){
        //1.获取运动员线程名称
        String name = Thread.currentThread().getName();
        //2.运动员开始准备：打印准备信息
        System.out.println(name+"preparing...");
        //3.线程睡眠1000毫秒，表示运动员在准备
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //4.运动员准备完毕：打印准备完毕信息，同时计数-1
        System.out.println(name+"work done!");
        countDownLatch.countDown();
    }
    public void coach(){
        //1.获取教练线程名称
        String name = Thread.currentThread().getName();
        //2.教练开始准备：打印准备信息
        System.out.println(name+"coach preparing...");
        try {
           countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //4.教练员准备完毕：打印准备完毕信息
        System.out.println(name+"work done! ");
    }
    public static void main(String[] args){
        //1.创建CoachRacerDemo实例
       final RacerAndCoach coachRacerDemo =new RacerAndCoach();
        //2.创建三个线程对象，调用CoachRacerDemo的racer方法
        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                coachRacerDemo.racer();
            }
        }, "racer1 ");
        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                coachRacerDemo.racer();
            }
        }, "racer2 ");
        Thread thread3 = new Thread(new Runnable() {
            public void run() {
                coachRacerDemo.racer();
            }
        }, "racer3 ");
        //3.创建一个线程对象，调用CoachRacerDemo的coach方法
        Thread thread4 = new Thread(new Runnable() {
            public void run() {
                coachRacerDemo.coach();
            }
        }, "coach ");

        try {
            thread4.join();
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread4.start();
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
