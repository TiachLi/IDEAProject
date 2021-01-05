import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadSafeTest {
    public static void main(String[] args) {
        Ticket ticket =new Ticket();
        Thread ticketThread =new Thread(ticket,"win1");
        Thread ticketThread1 =new Thread(ticket,"win2");
        Thread ticketThread2 =new Thread(ticket,"win3");
        Thread ticketThread3 =new Thread(ticket,"win4");
        Thread ticketThread4 =new Thread(ticket,"win5");
        Thread ticketThread5 =new Thread(ticket,"win6");
        Thread ticketThread6 =new Thread(ticket,"win7");
        Thread ticketThread7 =new Thread(ticket,"win8");
        Thread ticketThread8 =new Thread(ticket,"win9");
        ticketThread.start();
        ticketThread1.start();
        ticketThread2.start();
        ticketThread3.start();
/*      ticketThread4.start();
        ticketThread5.start();
        ticketThread6.start();
        ticketThread7.start();
        ticketThread8.start();*/
    }
}
