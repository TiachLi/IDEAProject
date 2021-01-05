import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class ThreadExtendDemo {
    
    public static void main(String[] args) throws InterruptedException {
        //1.继承thread类创建线程
//        MyThread myThread = new MyThread();
//        myThread.start();//启动自定义线程，自动调用myThread类中的run方法
//        //在main主线程中打印信息
//        for(int i=0; i<10; i++){
//            System.out.println(Thread.currentThread().getName()+"执行时间："+new Date().getTime()+"；执行次数是："+i);
//        }
        //2.实现runnable接口
        //2.1在main主线程打印信息
//        for(int i=0; i<10; i++){
//            System.out.println(Thread.currentThread().getName()+"执行时间："+new Date().getTime()+"；执行次数是："+i);
//        }
//        //2.2通过thread类执行MyRunnable类
//        Thread thread = new Thread(new MyRunnable(), "MyRunnable");
//        thread.start();
        //3.实现Callable接口
        //3.1创建FutureTask实例，创建MyCallable实例
//        FutureTask<String> task = new FutureTask<String>(new MyCallable());
//        //3.2创建Thread实例，执行FutureTask
//        Thread thread = new Thread(task, "MyCallable");
//        thread.start();
//        //3.3在主线程打印信息
//        for (int i=0; i<10; i++){
//            System.out.println(Thread.currentThread().getName()+"执行时间："+new Date().getTime()+"；执行次数是："+i);
//        }
//        //3.4获取并打印MyCallable执行结果
//        try {
//            String result = task.get();
//            System.out.println("MyCallable执行结果是："+result);
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
        //4.使用线程池创建线程
        //4.1使用Executors获取线程池对象
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        //4.2通过线程池对象获取线程并执行MyRunnable实例
        executorService.execute(new MyRunnable());
        //4.3主线程打印信息
        for (int i=0; i<10; i++){
            System.out.println(Thread.currentThread().getName()+"执行时间："+new Date().getTime()+"；执行次数是："+i);
        }
    }
}
