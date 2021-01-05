import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {

    private Map<String, String> map = new HashMap<String, String>();//操作的map对象
    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();//读操作锁
    private ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();//写操作锁

    public String get(String key){
        readLock.lock();//读操作加锁
        try{
            System.out.println(Thread.currentThread().getName()+" reading "+new Date().getTime());
            Thread.sleep(1000);
            return map.get(key);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        } finally {
            System.out.println(Thread.currentThread().getName()+" read end "+new Date().getTime());
            readLock.unlock();
        }
    }

    public void put(String key, String value){
        writeLock.lock();
        try{
            System.out.println(Thread.currentThread().getName()+" writing "+new Date().getTime());
            map.put(key, value);
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName()+" write end "+new Date().getTime());
            writeLock.unlock();
        }
    }

    public static void main(String[] args){
        final ReadWriteLockDemo readWriteLockDemo = new ReadWriteLockDemo();
        new Thread("write1 "){
            @Override
            public void run() {
                readWriteLockDemo.put("key1", "value1");
            }
        }.start();
        new Thread("write2 "){
            @Override
            public void run() {
                readWriteLockDemo.put("key2", "value1");
            }
        }.start();
        new Thread("write3 "){
            @Override
            public void run() {
                readWriteLockDemo.put("key3", "value1");
            }
        }.start();
        new Thread("read1"){
          public void run(){
              System.out.println(readWriteLockDemo.get("key1"));
          }
        }.start();
        new Thread("read2"){
            public void run(){
                System.out.println(readWriteLockDemo.get("key1"));
            }
        }.start();
        new Thread("read3"){
            public void run(){
                System.out.println(readWriteLockDemo.get("key1"));
            }
        }.start();
    }
}
