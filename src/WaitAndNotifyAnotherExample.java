import java.util.ArrayList;
import java.util.List;

class Processor {

    private List<Integer> list = new ArrayList<>();
    private static final int UPPER_LIMIT= 5;
    private static final int LOWER_LIMIT= 0;
    private final Object lock = new Object();
    private int value = 0;

    public void producer() throws InterruptedException {

        synchronized(lock) {
            while(true) {
                if (list.size() == UPPER_LIMIT) {
                    System.out.println("waiting for removing items");
                    lock.wait();
                } else {
                    System.out.println("adding a new item with the value  ");
                    Thread.sleep(1000);
                    list.add(value);
                    value++;
                    // the other thread will be notified only when it is in a waiting state
                    lock.notify();
                }
            }
        }

    }

    public void consumer() throws InterruptedException {

        synchronized(lock) {
            while(true) {
                if (list.size() == LOWER_LIMIT) {
                    System.out.println("waiting for adding items");
                    value=0;
                    lock.wait();
                } else {
                    Thread.sleep(1000);
                    System.out.println("removing  " + list.remove(list.size() -1));

                    lock.notify();
                }
            }
        }

    }


}

public class WaitAndNotifyAnotherExample {
    public static void main(String[] args) {
        //threads that lock on the SAME INTRINSIC LOCK can release the lock until the other thread calls notify

        Processor processor =  new Processor();

        Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    processor.producer();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });


        Thread t2 = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    processor.consumer();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        t1.start();
        t2.start();



    }
}
