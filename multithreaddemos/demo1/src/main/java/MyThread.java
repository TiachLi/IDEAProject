import java.util.Date;

public class MyThread extends Thread {
    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 10; i++) {
            System.out.println("线程执行"+new Date().getTime());
        }
    }
}
