

class RunnerSatu implements Runnable{

    @Override
    public void run() {
        System.out.println("printing current thread" + Thread.currentThread());
        for (int i =0; i<10; i++) {
            System.out.println("runner1: " + i);
        }
    }
}

class RunnerDua implements Runnable{

    @Override
    public void run() {
        System.out.println("printing current Thread:" + Thread.currentThread());
        for (int i =0; i<10; i++) {
            System.out.println("runner2: " + i);
        }
    }
}

public class WeWantMainToWaitForTheOtherTwoThreads {

    public static void main(String[] args) {
        System.out.println("printing current Thread: " + Thread.currentThread());
        //time slicing

        Thread thread1 = new Thread(new RunnerSatu());
        Thread thread2 = new Thread(new RunnerDua());

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (Exception e) {

        }

        System.out.println(Thread.currentThread() + " we want main to wait for these 2 stupid threads to finish");
        System.out.println(Thread.currentThread() + " main method finishing");
    }
}
