import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Ticket implements Runnable {
    private static int ticketNum=100;
    private Lock lock = new ReentrantLock(true);
    public void run() {
        System.out.println(Thread.currentThread().getName()+":"+new Date().getTime());
        saleTicket();
    }

    private /*synchronized*/ void saleTicket(){
        System.out.println(Thread.currentThread().getName()+"run: "+new Date().getTime()+new Date().getTime());
        while (ticketNum > 0) {
                try {
                    Thread.sleep(100);
                    System.out.println(Thread.currentThread().getName() + " ticketNum: " + ticketNum--+" "+new Date().getTime());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
/*    private  void saleTicket(){
            while (true) {
                lock.lock();
                try {
                    if (ticketNum>0)
                        try {
                            Thread.sleep(100);
                            System.out.println(Thread.currentThread().getName() + " ticketNum: " + ticketNum-- +" "+new Date().getTime());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                }finally {
                    lock.unlock();
                }
            }
        }*/

    }

