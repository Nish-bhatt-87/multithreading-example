import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class WorkThreadProol implements Runnable {

    @Override
    public void run() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("printing thread name" + Thread.currentThread().getName());
    }
}

public class ThreadPoolExample {

//    private static void print() {
//        System.out.println("printing thread name" + Thread.currentThread().getId());
//    }

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        System.out.println("printing thread name" + Thread.currentThread().getName());
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        //change the above 6 to 3 and see what happens to the endTime-startTime
        for (int i =0; i<6; i++) {
            executorService.execute(new WorkThreadProol());
        }


        executorService.shutdown();

        executorService.awaitTermination(25000,TimeUnit.MILLISECONDS);

        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
    }
}
