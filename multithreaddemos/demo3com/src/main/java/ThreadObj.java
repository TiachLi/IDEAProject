import java.util.Date;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadObj {
    int i=0;
    Object object =new Object();
    Lock lock =new ReentrantLock();
    Condition condition =lock.newCondition();
/*    public void odd(){
        while (i<10) {
            synchronized (object) {
                if (i % 2 == 1) {
                    System.out.println(i+" odd: " + new Date().getTime());
                    i++;
                    object.notify();
                }else {
                    try {
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    public void even(){
        while (i<10) {
            synchronized (object) {
                if (i % 2 == 0) {
                    System.out.println(i+" even: " + new Date().getTime());
                    i++;
                    object.notify();
                }else {
                    try {
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }*/
public void odd(){
    while (i<10) {
        lock.lock();
        try {
            if (i % 2 == 1) {
                System.out.println(i+" odd: " + new Date().getTime());
                i++;
                Thread.sleep(1);
                condition.signal();
            }else {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
    public void even() {
        while (i<10) {
            lock.lock();
            try {
                if (i % 2 == 0) {
                    System.out.println(i+" even: " + new Date().getTime());
                    i++;
                    Thread.sleep(1);
                    condition.signal();
                }else {
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
    public static void main(String[] args) {
        final ThreadObj threadObj =new ThreadObj();
        Thread thread1=new Thread(new Runnable() {
            public void run() {
                threadObj.odd();
            }
        });

        Thread thread2=new Thread(new Runnable() {
            public void run() {
                threadObj.even();
            }
        });
        thread1.start();
        thread2.start();
    }
}
