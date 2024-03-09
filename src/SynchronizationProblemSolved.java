public class SynchronizationProblemSolved {

    public static int counter = 0;


    //method should only be executed by a single thread at a time
    public static synchronized void increment() {
        counter++;
    }

    public static void process() throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i =0; i<300000; i++) {
                    increment();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i =0; i<300000; i++) {
                    increment();
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }

    public static void main(String[] args) throws InterruptedException {

        process();
        System.out.println("the counter is " + counter);
    }
}
