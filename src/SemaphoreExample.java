import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

enum Downloader {
    INSTANCE;

    private Semaphore sempahore = new Semaphore(6,true);

    public void download() {
        try {
            sempahore.acquire();
            downloadDate();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            sempahore.release();
        }
    }

    private void downloadDate() {
        try {
            System.out.println("downloading data from the web..........");
            Thread.sleep(2000);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}

public class SemaphoreExample {

    public static void main(String[] args) {

        ExecutorService service = Executors.newCachedThreadPool();

        for (int i =0; i<24; i++) {
            service.execute(new Runnable() {

                @Override
                public void run() {
                    Downloader.INSTANCE.download();
                }
            });
        }

        //service.shutdown();
    }
}
