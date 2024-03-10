public class MemoryManagementExample {

    public static int counter = 0;

    public static void process() throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                for(int i=0; i<100000; i++) {
                    counter++;
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {

            @Override
            public void run() {
                for(int i=0; i<100000; i++) {
                    counter++;
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(counter);
    }

    public static void main(String[] args) throws InterruptedException {
        //every thread has its own stack memory but all threads share the heap memory (shared memory space)
        //stack memory - local variables , method arguments and method calls
        // objects on heap memory
        //threads of the same process run in a shared memory space
        //processes run in separate memory spaces


        //this is why we need to deal with synchronization

        process();

    }
}
