public class ThreadLocalDemo {
    //1.创建银行对象：钱，取款，存款
    static class Bank {
        private ThreadLocal<String> threadLocal = new ThreadLocal<String>(){
            @Override
            protected String initialValue() {
                return super.initialValue();
            }
        };

        public String get(){
            return threadLocal.get();
        }

        public void set(String money){
            threadLocal.set(threadLocal.get()+money);
        }
    }
    //2.创建转账对象：从银行中取钱，转账，保存到帐户
    static class Transfer implements Runnable{
        private Bank bank;
        public Transfer(Bank bank){
            this.bank = bank;
        }
        public void run() {
            for(int i=0; i<10; i++){
                bank.set("1");
                System.out.println(Thread.currentThread().getName()+"balance "+bank.get());
            }
        }
    }
    //3.在main方法中使用两个对象模拟转账
    public static void main(String[] args){
        Bank bank = new Bank();
        Transfer transfer = new Transfer(bank);
        Thread thread1 = new Thread(transfer, "user1 ");
        Thread thread2 = new Thread(transfer, "user2 ");

        thread1.start();
        thread2.start();
    }
}
