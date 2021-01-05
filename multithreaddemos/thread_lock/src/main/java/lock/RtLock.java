package lock;

class RtLock implements SelfDefineLock {
        boolean isLocked = false;
        Thread  lockedBy = null;
        int lockedCount = 0;
        public synchronized void lock() {
            Thread thread = Thread.currentThread();
            while(isLocked && lockedBy != thread){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            isLocked = true;
            lockedCount++;
            lockedBy = thread;
        }
        public synchronized void unlock(){
            if(Thread.currentThread() == this.lockedBy){
                lockedCount--;
                if(lockedCount == 0){
                    isLocked = false;
                    notify();
                }
            }
        }
}
