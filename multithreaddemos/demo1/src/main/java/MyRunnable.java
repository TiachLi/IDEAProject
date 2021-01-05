import java.util.Date;

public class MyRunnable implements Runnable {
    public void run() {
        for (int i = 0; i <10 ; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("自定义线程执行："+new Date().getTime());
        }
    }
}
