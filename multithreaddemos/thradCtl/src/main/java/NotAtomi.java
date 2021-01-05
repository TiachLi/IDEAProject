public class NotAtomi {
    static  int n;//执行n++操作的变量
    public static void main(String[] args) {
        Thread thread1 = new Thread() {
            public void run() {
                for (int i = 0; i < 100; i++) {
                    n++;
                }
            }
        };
        Thread thread2 = new Thread() {
            public void run() {
                for (int i = 0; i < 100; i++) {
                    n++;
                }
            };
        };
        thread1.start();
        thread2.start();
    }
}
