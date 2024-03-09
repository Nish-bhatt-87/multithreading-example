

class WorkerPri implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getPriority());
        for (int i =0; i<10; i++) {
            System.out.println(i);
        }
    }
}


public class ThreadPriorityExample {
    public static void main(String[] args) {

        //System.out.println(Thread.currentThread().getPriority());

        Thread t  = new Thread(new WorkerPri());
        t.setPriority(8);
        t.start();
        System.out.println("This is in the main thread");

    }
}
