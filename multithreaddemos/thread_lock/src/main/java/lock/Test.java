package lock;

public class Test {
    static void test_nortlock(){
        final SelfDefineLock lock = new NoRtLock();
        new Thread(){
            public void run(){
                lock.lock();
                lock.lock();
                lock.unlock();
                lock.unlock();
            }
        }.start();
    }
    static void test_rtlock(){
        final SelfDefineLock lock = new RtLock();
        new Thread(){
            public void run(){
                lock.lock();
                lock.lock();
                lock.unlock();
                lock.unlock();
            }
        }.start();
    }
    public static void main(String[] args){
   /*     test_nortlock();*/
        test_rtlock();
    }
}

