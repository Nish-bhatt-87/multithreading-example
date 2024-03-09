import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

class Worker implements Runnable {

    @Override
    public void run() {

            try {
                Thread.sleep(56000);
                System.out.println("worker threads running..........");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        System.out.println("normal thread finishes execution");

    }
}

class DaemonThread implements Runnable {

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("daemon thread interrupted and finishing");
                throw new RuntimeException(e);
            }
            System.out.println("daemon thread running");
        }

    }
}

public class WorkersAndDaemons {
    public static void main(String[] args) {
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        String processID = runtimeMXBean.getName().split("@")[0];
        System.out.println("Process ID: " + processID);
        //String name = Thread.currentThread().getName();
        //System.out.println(name);

        Thread t1 = new Thread(new Worker());
        t1.start();
        Thread t2 = new Thread(new DaemonThread());
        t2.setDaemon(true);
        t2.start();

        //when worker ends, so will daemon
        //uncomment the below and see what happens in task manager -- find the pid and the number of threads in the details section.
        //        try {
        //            t1.join();
        //            t2.join();
        //        }catch (Exception e) {
        //
        //        }
        //

        System.out.println("main finishing");

    }
}
