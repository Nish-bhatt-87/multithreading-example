
class Process {
    public void produce() throws InterruptedException {
        synchronized(this) {
            System.out.println("running the produce method");
            wait();
            System.out.println("again in the producer method");
        }
    }

    public void consume() throws InterruptedException {

        Thread.sleep(1000);
        synchronized(this) {
            System.out.println("consume method is executed");
            notify();
        }
    }


}

public class WaitAndNotify {
    public static void main(String[] args) {
        //threads that lock on the SAME INTRINSIC LOCK can release the lock until the other thread calls notify

        Process process =  new Process();

        Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    process.produce();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });


        Thread t2 = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    process.consume();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        t1.start();
        t2.start();



    }
}
