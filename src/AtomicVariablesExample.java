import java.util.concurrent.atomic.AtomicInteger;

public class AtomicVariablesExample {

    //private static int counter = 0;

    private static AtomicInteger counter = new AtomicInteger(0);

    public static void main(String[] args) {

        //AtomicVariablesExample atomicVariablesExample = new AtomicVariablesExample();

        //new Thread(atomicVariablesExample::increment,"thread1").start();
        //new Thread(atomicVariablesExample::increment,"thread2").start();


        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                increment();
            }
        });

        Thread thread1 = new Thread(new Runnable() {

            @Override
            public void run() {
                increment();
            }
        });

        thread.start();
        thread1.start();

        try {
            thread.join();
            thread1.join();
        } catch (Exception e) {

        }

        System.out.println(counter);

    }

    //notice no need to be synchronized
    public static void increment() {
        for (int i =0; i<100000; i++) {
            counter.addAndGet(1);
            //counter++;
        }

    }
}
