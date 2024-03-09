public class SynchronizationProblem {

    public static int counter = 0;

    public static void process() throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i =0; i<100000; i++) {
                    counter++;
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i =0; i<100000; i++) {
                    counter++;
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
