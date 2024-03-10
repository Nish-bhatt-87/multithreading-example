import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class WorkerLocker {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void produce() throws InterruptedException {
        lock.lock();
        System.out.println("producer method");
        condition.await();
        System.out.println("again producer method");
        lock.unlock();

    }

    public void consume() throws InterruptedException {

        Thread.sleep(2000);

        lock.lock();
        System.out.println("in consumer method");
        Thread.sleep(3000);
        condition.signal();
        lock.unlock();
    }

}

public class LockExampleAnother {
    public static void main(String[] args) throws InterruptedException {
        WorkerLocker wl = new WorkerLocker();
        Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {


                try {
                    wl.consume();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {

            @Override
            public void run() {

                try {
                    wl.produce();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}
