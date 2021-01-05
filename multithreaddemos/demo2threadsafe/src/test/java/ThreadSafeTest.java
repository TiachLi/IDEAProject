import org.junit.Test;

public class ThreadSafeTest {
    @Test
    public void test1(){
        Ticket ticket =new Ticket();
        Thread ticketThread =new Thread(ticket,"窗口1");
        Thread ticketThread1 =new Thread(ticket,"窗口2");
        Thread ticketThread2 =new Thread(ticket,"窗口3");
        ticketThread.start();
        ticketThread1.start();
        ticketThread2.start();
    }
}
