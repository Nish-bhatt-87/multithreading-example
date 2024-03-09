

class RunnerOne implements Runnable{

    @Override
    public void run() {
        System.out.println("printing current thread" + Thread.currentThread());
        for (int i =0; i<10; i++) {
            System.out.println("runner1: " + i);
        }
    }
}

class RunnerTwo implements Runnable{

    @Override
    public void run() {
        System.out.println("printing current Thread:" + Thread.currentThread());
        for (int i =0; i<10; i++) {
            System.out.println("runner2: " + i);
        }
    }
}

public class AppTwoExample {

    public static void main(String[] args) {
        System.out.println("printing current Thread: " + Thread.currentThread());
        //time slicing

        Thread thread1 = new Thread(new RunnerOne());
        Thread thread2 = new Thread(new RunnerTwo());

        thread1.start();
        thread2.start();

        System.out.println(Thread.currentThread() + "main method finishing");
    }
}
