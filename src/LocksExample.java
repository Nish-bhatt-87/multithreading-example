import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LocksExample {

    private static int counter = 0;
    private static final Lock lock = new ReentrantLock(); //fairness parameter is TRUE

    private static void increment() {

        lock.lock();

        try {

            for (int i = 0; i < 600000; i++) {
                counter++;
            }

        } finally {
            //this will be executed even if there is an execption in the try block
            lock.unlock();
        }


    }

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                increment();
            }
        });

        Thread t2 = new Thread(new Runnable() {

            @Override
            public void run() {
                increment();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(counter);
    }
}
