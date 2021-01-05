package cn.itcast.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Ticket implements Runnable {
    private int ticketNum = 100;//电影票数据量，默认100张
    private Object obj = new Object();
    private Lock lock = new ReentrantLock(true);//参数是是否公平锁：true-公平锁，多个线程都公平拥有执行权；false-非公平，独占锁，默认值

    public void run() {
        while(true){
            lock.lock();
            try{
                if(ticketNum > 0){//判断是否有票，ticketNum>0
                    //有票，让线程睡眠100毫秒
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //打印当前售出的票数字和线程名，票数减一
                    String name = Thread.currentThread().getName();
                    System.out.println("线程"+name+"销售电影票："+ticketNum--);
                }
            } finally {
                lock.unlock();
            }

        }
    }

    private  synchronized void saleTickt(){
        if(ticketNum > 0){//判断是否有票，ticketNum>0
            //有票，让线程睡眠100毫秒
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //打印当前售出的票数字和线程名，票数减一
            String name = Thread.currentThread().getName();
            System.out.println("线程"+name+"销售电影票："+ticketNum--);
        }
    }
}
