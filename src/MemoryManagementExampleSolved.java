public class MemoryManagementExampleSolved {

    public static int counter = 0;

    //but this synchronized keyword has a problem. what problem?
    //only a single thread can execute this method at one time. threads have to wait for each other
    //whats happening under the hood when we use the synchronized keyword on a given method
    //every object in Java has an intrinsic (monitor) lock
    //a thread that needs exclusive access to an object's fields, has to acquire the intrinsic lock
    //then it will release the intrinsic lock when its done with the computations
    //the problem is that every object has a single monitor lock
    public static synchronized void increment() {
        counter++;
    }

    public static void process() throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                for(int i=0; i<100000; i++) {
                    increment();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {

            @Override
            public void run() {
                for(int i=0; i<100000; i++) {
                    increment();
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
