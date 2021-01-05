import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ThreadAtomicDemo {
//    static  int n;//执行n++操作的变量
    static AtomicInteger atomicInteger1;
    static AtomicStampedReference<Integer> atomicInteger;

    public static void main(String[] args) throws InterruptedException {
        int j = 0;
        while(j < 100){
            atomicInteger = new AtomicStampedReference(0, 0);//创建原子整数，初始值是0
            Thread thread1 = new Thread(){
                public void run(){
                    for(int i=0; i<1000; i++){
//                        n++;
                        int stamp;
                        Integer reference;
                        do{
                            stamp = atomicInteger.getStamp();
                            reference = atomicInteger.getReference();
                        }while(!atomicInteger.compareAndSet(reference, reference+1, stamp, stamp+1));
                    }
                }
            };
            Thread thread2 = new Thread(){
                public void run(){
                    for(int i=0; i<1000; i++){
//                        n++;
                        int stamp;
                        Integer reference;
                        do{
                            stamp = atomicInteger.getStamp();
                            reference = atomicInteger.getReference();
                        }while(!atomicInteger.compareAndSet(reference, reference+1, stamp, stamp+1));
                    }
                }
            };
            thread1.start();
            thread2.start();
            thread1.join();
            thread2.join();
            System.out.println("n的最终值是："+atomicInteger.getReference());
            j++;
        }

    }
}
