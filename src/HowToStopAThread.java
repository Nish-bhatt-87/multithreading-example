
class WorkerVolatile implements Runnable {

    private /*volatile*/ boolean terminated;

    @Override
    public void run() {

        while (!terminated) {
            System.out.println("terminated is " + terminated);
            System.out.println("working class is running");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public boolean isTerminated() {
        return terminated;
    }

    public void setTerminated(boolean terminated) {
        this.terminated = terminated;
    }
}

public class HowToStopAThread {

    public static void main(String[] args) throws InterruptedException {

        WorkerVolatile workerVolatile = new WorkerVolatile();

        Thread t1 = new Thread(workerVolatile);
        t1.start();
        Thread.sleep(8000);

        workerVolatile.setTerminated(true);
        System.out.println("algo is terminated");
        System.out.println("terminated is " + workerVolatile.isTerminated());
    }
}
