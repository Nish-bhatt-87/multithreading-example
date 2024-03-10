public class MemoryManagementLockProblemSimulatedGoodPracticeBetterImpl {

    public static int counter = 0;
    public static int counter2 = 0;

    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();



    //but this synchronized keyword has a problem. what problem?
    //only a single thread can execute this method at one time. threads have to wait for each other
    //whats happening under the hood when we use the synchronized keyword on a given method
    //every object in Java has an intrinsic (monitor) lock
    //a thread that needs exclusive access to an object's fields, has to acquire the intrinsic lock
    //then it will release the intrinsic lock when its done with the computations
    //the problem is that every object has a single monitor lock
    //the application becomes slower!
    //object level locking
    //class level locking - lock associated with the class itself  - below is an example of class level locking
    //it is a good practise to use synchronized blocks
    //below solves the issue where independent operations are present and we dont want to lock the entire class

    //in this block, we lock on the different object -- thread will lock on the first object and so the 2nd thread does not need to use the same intrinsic lock!
    //they can execute these methods independently

    public static void increment() {
        synchronized(lock1) {
            counter++;
        }
    }
    public static void increment2() {
        synchronized(lock2) {
            counter2++;
        }
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
                    increment2();
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(counter);
        System.out.println(counter2);
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
